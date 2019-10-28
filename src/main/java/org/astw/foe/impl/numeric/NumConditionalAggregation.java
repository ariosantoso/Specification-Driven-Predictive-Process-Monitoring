package org.astw.foe.impl.numeric;

import java.util.HashMap;
import java.util.Set;

import org.astw.foe.Formula;
import org.astw.foe.IndexExp;
import org.astw.foe.NumExp;
import org.astw.util.Const;
import org.astw.util.Const.NumAggregationType;
import org.astw.util.Const.NumberType;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

import gnu.trove.iterator.TIntIterator;
import gnu.trove.set.hash.TIntHashSet;

public class NumConditionalAggregation implements NumExp{

//	private String component;
	private NumExp aggSourceValue;
	private IndexExp aggStartRange; 
	private IndexExp aggEndRange; 
	private Formula aggCondition;
	private String aggVariable = null;

	private NumAggregationType numAggregationType = NumAggregationType.UNKNOWN;
	private double value = Const.UNDEFINED_NUM_VAL;

	private NumberType numType = NumberType.DOUBLE;
	private XESDataType xesDataType = XESDataType.XES_DOUBLE;
	private ValueType valueType = ValueType.NUMERIC_EXP;//numeric or non-numeric

	
	public NumConditionalAggregation(
			NumExp aggSourceValue, IndexExp aggStartRange, IndexExp aggEndRange, Formula aggCondition) throws Exception {

		this.aggSourceValue = aggSourceValue; 
		this.aggStartRange = aggStartRange;
		this.aggEndRange = aggEndRange; 
		this.aggCondition = aggCondition;
		
		Set<String> setOfVariables = this.getVariables();

		//throw exception if there are more than 1 aggregation variables
		if(setOfVariables.size() > 1)
			throw new Exception("The aggregate function '"+this.toString()+"' contains more than 1 variable (and this is not allowed)");
		
		if(setOfVariables.size() == 1)
			this.aggVariable = setOfVariables.iterator().next();
	}

	/**
	 * This constructor is only for the clonning
	 * 
	 * @param aggSourceValue
	 * @param aggStartRange
	 * @param aggEndRange
	 * @param aggCondition
	 * @param dummy
	 */
	private NumConditionalAggregation(
			NumExp aggSourceValue, IndexExp aggStartRange, IndexExp aggEndRange, Formula aggCondition, 
			NumAggregationType numAggregationType, double value, String aggVariable, NumberType numType, XESDataType xesDataType) {

		this.aggSourceValue = aggSourceValue; 
		this.aggStartRange = aggStartRange;
		this.aggEndRange = aggEndRange; 
		this.aggCondition = aggCondition;
		this.numAggregationType = numAggregationType;
		this.value = value;
		this.aggVariable = aggVariable;
		this.numType = numType;
		this.xesDataType = xesDataType;
	}

	/**
	 * Assign the corresponding XES Data Type to each AttributeAccessor 
	 */
	@Override
	public void assignQueryXESDataType(HashMap<String, XESDataType> attributeNamesAndTypes) throws Exception {
		this.aggSourceValue.assignQueryXESDataType(attributeNamesAndTypes);
		this.aggStartRange.assignQueryXESDataType(attributeNamesAndTypes);
		this.aggEndRange.assignQueryXESDataType(attributeNamesAndTypes);
		this.aggCondition.assignQueryXESDataType(attributeNamesAndTypes);
		
		if(!isTheSourceValueNumeric())
			throw new Exception("The numeric aggregate function '"+this.toString()+"' contains a non-numeric source value");
		
	}
	
	/**
	 * Check whether the source values of this aggregate function is numeric
	 * @return
	 * @throws Exception 
	 */
	private boolean isTheSourceValueNumeric() throws Exception{
		
		return (aggSourceValue.getValueType() == ValueType.NUMERIC_EXP) &&
				(aggSourceValue.ensureTypeCorrectness() == ValueType.NUMERIC_EXP);
	}

	/**
	 * Set the numeric aggregation type (i.e., either SUM, AVG, MAX or MIN)
	 * 
	 * @param aggType
	 */
	public void setAggregationType(NumAggregationType aggType){
		
		this.numAggregationType = aggType;
		
		if(this.numAggregationType == NumAggregationType.COUNT){
			
			//this.numType = NumberType.INT;
			this.xesDataType = XESDataType.XES_LONG;
		}
	}

	public NumAggregationType getAggregationType(){
		
		return this.numAggregationType;
	}

	public String toString(){
	
		if(this.numAggregationType == NumAggregationType.COUNT)
			return
					this.numAggregationType+"("+this.aggCondition+" # "+this.aggStartRange+":"+this.aggEndRange+")";
		else return 
			this.numAggregationType+"("+this.aggSourceValue+" # "+this.aggStartRange+":"+this.aggEndRange+" # "+this.aggCondition+")";
		
	}
	
	/**
	 * Up to this version, the aggregation functions should not contain another variables 
	 * except the corresponding aggregation variable, hence it does not need to implement 
	 * this method. The substitution of variables will be based on the aggregation range
	 * 
	 */
	@Override
	public void substituteVar(String varName, int value) {
	}

	/**
	 * We don't need this because the query evaluation will need also to be based on the aggregation range.
	 */
	@Override
	public void evaluateQuery(XTrace xtrace) throws Exception {
	}

	
	@Override
	public void evaluateSpecialIndex(int current, int last) {

		// just propagate further to evaluate the special index inside this component
		this.aggSourceValue.evaluateSpecialIndex(current, last);
		this.aggStartRange.evaluateSpecialIndex(current, last);
		this.aggEndRange.evaluateSpecialIndex(current, last);
		this.aggCondition.evaluateSpecialIndex(current, last);
	}

	@Override
	public String prettyFormat(String tab) {
		return this.toString();
	}

	@Override
	public NumExp clone() {

		
		return 
			new NumConditionalAggregation(this.aggSourceValue, this.aggStartRange, this.aggEndRange, this.aggCondition, 
					this.numAggregationType, this.value, this.aggVariable, this.numType, this.xesDataType);
	}

	@Override
	public ValueType getValueType() {
		
		return this.valueType;
	}

	@Override
	public XESDataType getXESDataType() {

		return this.xesDataType;
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// AGGREGATION COMPUTATION
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Compute the aggregation
	 * 
	 * Note: this method assumes that all special indices has been substituted with concrete values
	 * 
	 */
	@Override
	public void evaluateAggregateFunction(XTrace xtrace) throws Exception{

		int startRange = this.aggStartRange.getIdxValue();
		int endRange = this.aggEndRange.getIdxValue();
		
		if(startRange > endRange)
			throw new Exception("Wrong Aggregation Range in '"+this.toString()+"'. The start range is greater than the end range");

		switch(this.numAggregationType){
		
			case SUM: this.value = computeSummation(xtrace, startRange, endRange);
				break;

			case MIN: this.value = computeMin(xtrace, startRange, endRange);
				break;

			case MAX: this.value = computeMax(xtrace, startRange, endRange);
				break;

			case AVG: this.value = computeAverage(xtrace, startRange, endRange);
				break;

			case COUNT: this.value = computeCount(xtrace, startRange, endRange);
				break;

			default: this.value = Const.UNDEFINED_NUM_VAL;
		}

		//System.out.println("Evaluation of '"+this.toString()+"' = "+this.value);
		
	}
	
	private double computeSummation(XTrace xtrace, int startRange, int endRange) throws Exception{
		
		TIntHashSet setOfValidIndices = computeSetOfValidIndices(xtrace, startRange, endRange);
		//System.out.println("setOfValidIndices: "+setOfValidIndices);
		
		//if the aggregation condition is never satisfied, return undefined value
		if(setOfValidIndices.size() == 0)
			return Const.UNDEFINED_NUM_VAL;
		
		//compute the aggregation over the valid indices
		TIntIterator it = setOfValidIndices.iterator();
		double sum = 0;
		while(it.hasNext()){
			 
			int curIdx = it.next();
			//compute the aggregation source w.r.t. the index curIdx
			NumExp aggSourceClone = this.aggSourceValue.clone();
			aggSourceClone.substituteVar(this.aggVariable, curIdx);
			aggSourceClone.evaluateQuery(xtrace);
			sum += aggSourceClone.getNumericValue();
		}
		
		return sum;
	}

	private double computeAverage(XTrace xtrace, int startRange, int endRange) throws Exception{
		
		TIntHashSet setOfValidIndices = computeSetOfValidIndices(xtrace, startRange, endRange);
		//System.out.println("setOfValidIndices: "+setOfValidIndices);
		
		int numValidIndices = setOfValidIndices.size();
		
		//if the aggregation condition is never satisfied, return undefined value
		if(numValidIndices == 0)
			return Const.UNDEFINED_NUM_VAL;
		
		//compute the aggregation over the valid indices
		TIntIterator it = setOfValidIndices.iterator();
		double sum = 0;
		while(it.hasNext()){
			 
			int curIdx = it.next();
			//compute the aggregation source w.r.t. the index curIdx
			NumExp aggSourceClone = this.aggSourceValue.clone();
			aggSourceClone.substituteVar(this.aggVariable, curIdx);
			aggSourceClone.evaluateQuery(xtrace);
			sum += aggSourceClone.getNumericValue();
		}
		
		return (sum/numValidIndices);
	}

	private double computeMin(XTrace xtrace, int startRange, int endRange) throws Exception{
		
		TIntHashSet setOfValidIndices = computeSetOfValidIndices(xtrace, startRange, endRange);
		//System.out.println("setOfValidIndices: "+setOfValidIndices);
		
		//if the aggregation condition is never satisfied, return undefined value
		if(setOfValidIndices.size() == 0)
			return Const.UNDEFINED_NUM_VAL;
		
		//compute the aggregation over the valid indices
		TIntIterator it = setOfValidIndices.iterator();
		double min = Double.MAX_VALUE;
		while(it.hasNext()){
			 
			int curIdx = it.next();
			//compute the aggregation source w.r.t. the index curIdx
			NumExp aggSourceClone = this.aggSourceValue.clone();
			aggSourceClone.substituteVar(this.aggVariable, curIdx);
			aggSourceClone.evaluateQuery(xtrace);
			double minCandidate = aggSourceClone.getNumericValue();
			if(min > minCandidate)
				min = minCandidate;
		}
		
		if(min == Double.MAX_VALUE)
			return Const.UNDEFINED_NUM_VAL;
		
		return min;
	}

	private double computeMax(XTrace xtrace, int startRange, int endRange) throws Exception{
		
		TIntHashSet setOfValidIndices = computeSetOfValidIndices(xtrace, startRange, endRange);
		//System.out.println("setOfValidIndices: "+setOfValidIndices);
		
		//if the aggregation condition is never satisfied, return undefined value
		if(setOfValidIndices.size() == 0)
			return Const.UNDEFINED_NUM_VAL;
		
		//compute the aggregation over the valid indices
		TIntIterator it = setOfValidIndices.iterator();
		double max = Double.MIN_VALUE;
		while(it.hasNext()){
			 
			int curIdx = it.next();
			//compute the aggregation source w.r.t. the index curIdx
			NumExp aggSourceClone = this.aggSourceValue.clone();
			aggSourceClone.substituteVar(this.aggVariable, curIdx);
			aggSourceClone.evaluateQuery(xtrace);
			double maxCandidate = aggSourceClone.getNumericValue();
			if(max < maxCandidate)
				max = maxCandidate;
		}
		
		if(max == Double.MIN_VALUE)
			return Const.UNDEFINED_NUM_VAL;
		
		return max;
	}

	private double computeCount(XTrace xtrace, int startRange, int endRange) throws Exception{
		
		TIntHashSet setOfValidIndices = computeSetOfValidIndices(xtrace, startRange, endRange);
		//System.out.println("setOfValidIndices: "+setOfValidIndices);
		
		return setOfValidIndices.size();
	}

	private TIntHashSet computeSetOfValidIndices(XTrace xtrace, int startRange, int endRange) throws Exception{
	
		TIntHashSet setOfValidIndices = new TIntHashSet();
		
		//if there exists aggregation variable
		if(this.aggVariable != null){
		
			//collect the valid indices within the aggregation range
			for(int ii = startRange; ii <= endRange; ii++){

				//process the aggregation condition
				Formula aggCondClone = this.aggCondition.clone();
				aggCondClone.substituteVar(this.aggVariable, ii);
				aggCondClone.evaluateQuery(xtrace);

				//compute the aggregation source w.r.t. the index curIdx
				NumExp aggSourceClone = this.aggSourceValue.clone();
				aggSourceClone.substituteVar(this.aggVariable, ii);
				aggSourceClone.evaluateQuery(xtrace);
				
//				System.out.println("ii: "+aggSourceClone.getNumericValue() + " "+Const.UNDEFINED_NUM_VAL+" "+
//						(!Double.isNaN(aggSourceClone.getNumericValue())));
				
				//if the aggregation condition holds for the index ii, add ii to setOfValidIndices
				//and the evaluation of the aggregation source for the index ii is not undefined
				if(aggCondClone.evaluateGroundedFormula() && !Double.isNaN(aggSourceClone.getNumericValue()))
					setOfValidIndices.add(ii);
			}				
		}else{// if there is no aggregation variable
			
			this.aggCondition.evaluateQuery(xtrace);
			
			if(this.aggCondition.evaluateGroundedFormula()){
				//collect the valid indices within the aggregation range
				for(int ii = startRange; ii <= endRange; ii++){

					//compute the aggregation source w.r.t. the index curIdx
					NumExp aggSourceClone = this.aggSourceValue.clone();
					aggSourceClone.substituteVar(this.aggVariable, ii);
					aggSourceClone.evaluateQuery(xtrace);
					
//					System.out.println("ii: "+aggSourceClone.getNumericValue() + " "+Const.UNDEFINED_NUM_VAL+" "+
//							(!Double.isNaN(aggSourceClone.getNumericValue())));
					
					//if the evaluation of the aggregation source for the index ii is not undefined
					if(!Double.isNaN(aggSourceClone.getNumericValue()))
						setOfValidIndices.add(ii);
				}				

			}
		}
		
//		System.out.println("setOfValidIndices: "+setOfValidIndices);

		return setOfValidIndices;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	// END OF AGGREGATION COMPUTATION
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	@Override
	public double getNumericValue() {
		
		return this.value;
	}

	
	@Override
	public ValueType ensureTypeCorrectness() throws Exception {

		boolean allFine = true;
		
		ValueType aggSourceType = this.aggSourceValue.ensureTypeCorrectness();
		ValueType aggStartRangeType = this.aggStartRange.ensureTypeCorrectness();
		ValueType aggEndRangeType = this.aggEndRange.ensureTypeCorrectness();

		if(aggSourceType != ValueType.NUMERIC_EXP ||
				aggStartRangeType != ValueType.NUMERIC_EXP ||
						aggEndRangeType != ValueType.NUMERIC_EXP)
			allFine = false;
		
		if(allFine)
			return ValueType.NUMERIC_EXP;
		else
			throw new Exception("The numeric aggregate function '"+this.toString()+"' has a typing problem");
	}

	@Override
	public Set<String> getVariables(){
		
		Set<String> aggSourceVars = this.aggSourceValue.getVariables();
		Set<String> aggConditionVars = this.aggCondition.getVariables();
		Set<String> aggStartRangeVars = this.aggStartRange.getVariables();
		Set<String> aggEndRangeVars = this.aggEndRange.getVariables();
		
		aggSourceVars.addAll(aggConditionVars);
		aggSourceVars.addAll(aggStartRangeVars);
		aggSourceVars.addAll(aggEndRangeVars);
		
		return aggSourceVars;
	}

}
