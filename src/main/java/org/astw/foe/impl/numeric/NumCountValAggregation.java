package org.astw.foe.impl.numeric;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.astw.foe.Formula;
import org.astw.foe.IndexExp;
import org.astw.foe.NumExp;
import org.astw.util.Const;
import org.astw.util.Const.NumAggregationType;
import org.astw.util.Const.NumberType;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XTrace;

import gnu.trove.iterator.TIntIterator;
import gnu.trove.set.hash.TIntHashSet;

public class NumCountValAggregation implements NumExp{

//	private String component;
	private String attName;
	private IndexExp aggStartRange; 
	private IndexExp aggEndRange; 

	private double value = Const.UNDEFINED_NUM_VAL;

	private NumberType numType = NumberType.DOUBLE;
	private XESDataType xesDataType = XESDataType.XES_DOUBLE;
	private ValueType valueType = ValueType.NUMERIC_EXP;//numeric or non-numeric

	
	public NumCountValAggregation(String attName, IndexExp aggStartRange, IndexExp aggEndRange) throws Exception {

		this.attName = attName; 
		this.aggStartRange = aggStartRange;
		this.aggEndRange = aggEndRange; 
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
	private NumCountValAggregation(
			String attName, IndexExp aggStartRange, IndexExp aggEndRange, 
			double value, NumberType numType, XESDataType xesDataType) {

		this.attName = attName; 
		this.aggStartRange = aggStartRange;
		this.aggEndRange = aggEndRange; 
		this.value = value;
		this.numType = numType;
		this.xesDataType = xesDataType;
	}

	/**
	 * Assign the corresponding XES Data Type to each AttributeAccessor 
	 */
	@Override
	public void assignQueryXESDataType(HashMap<String, XESDataType> attributeNamesAndTypes) throws Exception {
		
	}
	
	public String toString(){
	
		return "COUNTVAL("+this.attName+" # "+this.aggStartRange+":"+this.aggEndRange+")";
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
		this.aggStartRange.evaluateSpecialIndex(current, last);
		this.aggEndRange.evaluateSpecialIndex(current, last);
	}

	@Override
	public String prettyFormat(String tab) {
		return this.toString();
	}

	@Override
	public NumExp clone() {

		return new NumCountValAggregation(	this.attName, this.aggStartRange, this.aggEndRange,
											this.value, this.numType, this.xesDataType);
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

		HashSet<String> setOfAllPossibleValues = new HashSet<String>();
		
		for(int ii = (startRange - 1); ii < endRange; ii++ ){
			
			XAttribute xatt = xtrace.get(ii).getAttributes().get(this.attName);
			if(xatt != null)
				setOfAllPossibleValues.add(xatt.toString());
		}
		
		this.value = setOfAllPossibleValues.size();

		//System.out.println("Evaluation of '"+this.toString()+"' = "+this.value);
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
		
		ValueType aggStartRangeType = this.aggStartRange.ensureTypeCorrectness();
		ValueType aggEndRangeType = this.aggEndRange.ensureTypeCorrectness();

		if(aggStartRangeType != ValueType.NUMERIC_EXP || aggEndRangeType != ValueType.NUMERIC_EXP)
			allFine = false;
		
		if(allFine)
			return ValueType.NUMERIC_EXP;
		else
			throw new Exception("The numeric aggregate function '"+this.toString()+"' has a typing problem");
	}

	@Override
	public Set<String> getVariables(){
		
		return new HashSet<String>();
	}

}
