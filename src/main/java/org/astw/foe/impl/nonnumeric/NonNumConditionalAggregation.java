package org.astw.foe.impl.nonnumeric;

import java.util.HashMap;
import java.util.Set;

import org.astw.foe.Formula;
import org.astw.foe.IndexExp;
import org.astw.foe.NonNumExp;
import org.astw.foe.NumExp;
import org.astw.util.Const;
import org.astw.util.Const.NonNumAggregationType;
import org.astw.util.Const.NumAggregationType;
import org.astw.util.Const.NumberType;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.set.hash.TIntHashSet;

public class NonNumConditionalAggregation implements NonNumExp{

//	private String component;
	private NonNumExp aggSourceValue;
	private IndexExp aggStartRange; 
	private IndexExp aggEndRange; 
	private Formula aggCondition;
	private String aggVariable = null;

	private NonNumAggregationType nonNumAggregationType = NonNumAggregationType.UNKNOWN;
	private String value = Const.UNDEFINED_NON_NUM_VAL;

	private XESDataType xesDataType = XESDataType.XES_STRING;
	private ValueType valueType = ValueType.NON_NUMERIC_EXP;//numeric or non-numeric

	
	
	
	
	public NonNumConditionalAggregation(
			NonNumExp aggSourceValue, IndexExp aggStartRange, IndexExp aggEndRange, Formula aggCondition) throws Exception {

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
	private NonNumConditionalAggregation(
			NonNumExp aggSourceValue, IndexExp aggStartRange, IndexExp aggEndRange, Formula aggCondition, 
			NonNumAggregationType nonNumAggregationType, String value, String aggVariable, XESDataType xesDataType) {

		this.aggSourceValue = aggSourceValue; 
		this.aggStartRange = aggStartRange;
		this.aggEndRange = aggEndRange; 
		this.aggCondition = aggCondition;
		this.nonNumAggregationType = nonNumAggregationType;
		this.value = value;
		this.aggVariable = aggVariable;
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
		
		if(!isTheSourceValueNonNumeric())
			throw new Exception("The non-numeric aggregate function '"+this.toString()+"' contains a numeric source value");
		
	}
	
	/**
	 * Check whether the source values of this aggregate function is numeric
	 * @return
	 * @throws Exception 
	 */
	private boolean isTheSourceValueNonNumeric() throws Exception{
		
		return (aggSourceValue.getValueType() == ValueType.NON_NUMERIC_EXP) &&
				(aggSourceValue.ensureTypeCorrectness() == ValueType.NON_NUMERIC_EXP);
	}

	/**
	 * Set the numeric aggregation type (i.e., either SUM, AVG, MAX or MIN)
	 * 
	 * @param aggType
	 */
	public void setAggregationType(NonNumAggregationType aggType){
		
		this.nonNumAggregationType = aggType;
	}

	public NonNumAggregationType getAggregationType(){
		
		return this.nonNumAggregationType;
	}

	public String toString(){
	
		return 
			this.nonNumAggregationType+"("+this.aggSourceValue+" # "+this.aggStartRange+":"+this.aggEndRange+" # "+this.aggCondition+")";
		
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
	public NonNumExp clone() {
		
		return 
			new NonNumConditionalAggregation(this.aggSourceValue, this.aggStartRange, this.aggEndRange, this.aggCondition, 
					this.nonNumAggregationType, this.value, this.aggVariable, this.xesDataType);
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

		
		this.value = "hahahaha";
		switch(this.nonNumAggregationType){
		
			case CONCAT: this.value = computeConcat(xtrace, startRange, endRange);
				break;

			default: this.value = Const.UNDEFINED_NON_NUM_VAL;
		}

		System.out.println("Evaluation of '"+this.toString()+"' = "+this.value);
		
	}
	
	private String computeConcat(XTrace xtrace, int startRange, int endRange) throws Exception{
		
		TIntArrayList setOfValidIndices = computeSetOfValidIndices(xtrace, startRange, endRange);
		//System.out.println("setOfValidIndices: "+setOfValidIndices);
		
		//if the aggregation condition is never satisfied, return undefined value
		if(setOfValidIndices.size() == 0)
			return Const.UNDEFINED_NON_NUM_VAL;
		
		
		//compute the aggregation over the valid indices
		TIntIterator it = setOfValidIndices.iterator();
		String concatResult = "";
		while(it.hasNext()){
			 
			int curIdx = it.next();
			//compute the aggregation source w.r.t. the index curIdx
			NonNumExp aggSourceClone = this.aggSourceValue.clone();
			aggSourceClone.substituteVar(this.aggVariable, curIdx);
			aggSourceClone.evaluateQuery(xtrace);
			concatResult += aggSourceClone.getNonNumericValue();
		}
		
		return concatResult;
	}


	private TIntArrayList computeSetOfValidIndices(XTrace xtrace, int startRange, int endRange) throws Exception{
		
		TIntArrayList setOfValidIndices = new TIntArrayList();
		
		//if there exists aggregation variable
		if(this.aggVariable != null){
		
			//collect the valid indices within the aggregation range
			for(int ii = startRange; ii <= endRange; ii++){

				//process the aggregation condition
				Formula aggCondClone = this.aggCondition.clone();
				aggCondClone.substituteVar(this.aggVariable, ii);
				aggCondClone.evaluateQuery(xtrace);

				//compute the aggregation source w.r.t. the index curIdx
				NonNumExp aggSourceClone = this.aggSourceValue.clone();
				aggSourceClone.substituteVar(this.aggVariable, ii);
				aggSourceClone.evaluateQuery(xtrace);
				
//				System.out.println("ii: "+aggSourceClone.getNumericValue() + " "+Const.UNDEFINED_NUM_VAL+" "+
//						(!Double.isNaN(aggSourceClone.getNumericValue())));
				
				//if the aggregation condition holds for the index ii, add ii to setOfValidIndices
				//and the evaluation of the aggregation source for the index ii is not undefined
				if(aggCondClone.evaluateGroundedFormula() && 
						!aggSourceClone.getNonNumericValue().equals(Const.UNDEFINED_NON_NUM_VAL))
					setOfValidIndices.add(ii);
			}				
		}else{// if there is no aggregation variable
			
			this.aggCondition.evaluateQuery(xtrace);
			
			if(this.aggCondition.evaluateGroundedFormula()){
				//collect the valid indices within the aggregation range
				for(int ii = startRange; ii <= endRange; ii++){

					//compute the aggregation source w.r.t. the index curIdx
					NonNumExp aggSourceClone = this.aggSourceValue.clone();
					aggSourceClone.substituteVar(this.aggVariable, ii);
					aggSourceClone.evaluateQuery(xtrace);
					
//					System.out.println("ii: "+aggSourceClone.getNumericValue() + " "+Const.UNDEFINED_NUM_VAL+" "+
//							(!Double.isNaN(aggSourceClone.getNumericValue())));
					
					//if the evaluation of the aggregation source for the index ii is not undefined
					if(!aggSourceClone.getNonNumericValue().equals(Const.UNDEFINED_NON_NUM_VAL))
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
	public ValueType ensureTypeCorrectness() throws Exception {

		boolean allFine = true;
		
		ValueType aggSourceType = this.aggSourceValue.ensureTypeCorrectness();
		ValueType aggStartRangeType = this.aggStartRange.ensureTypeCorrectness();
		ValueType aggEndRangeType = this.aggEndRange.ensureTypeCorrectness();

		if(aggSourceType != ValueType.NON_NUMERIC_EXP ||
				aggStartRangeType != ValueType.NUMERIC_EXP ||
						aggEndRangeType != ValueType.NUMERIC_EXP)
			allFine = false;
		
		if(allFine)
			return ValueType.NON_NUMERIC_EXP;
		else
			throw new Exception("The non-numeric aggregate function '"+this.toString()+"' has a typing problem");
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

	@Override
	public String getNonNumericValue() {
		
		return this.value;
	}

}
