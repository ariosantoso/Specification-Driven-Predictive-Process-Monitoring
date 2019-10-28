// Generated from NumCondAggregation.g4 by ANTLR 4.7

package org.astw.parser.numConditionalAggregation.antlr;

import org.astw.foe.*;
import org.astw.foe.impl.*;
import org.astw.foe.impl.eventexp.*;
import org.astw.foe.impl.frm.*;
import org.astw.foe.impl.nonnumeric.*;
import org.astw.foe.impl.numeric.*;
import org.astw.foe.impl.numeric.indexexp.*;
import org.astw.*;
import org.astw.util.*;
import org.astw.util.Const;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NumCondAggregationParser}.
 */
public interface NumCondAggregationListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#parseCountAggregateFunc}.
	 * @param ctx the parse tree
	 */
	void enterParseCountAggregateFunc(NumCondAggregationParser.ParseCountAggregateFuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#parseCountAggregateFunc}.
	 * @param ctx the parse tree
	 */
	void exitParseCountAggregateFunc(NumCondAggregationParser.ParseCountAggregateFuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#cntAggContent}.
	 * @param ctx the parse tree
	 */
	void enterCntAggContent(NumCondAggregationParser.CntAggContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#cntAggContent}.
	 * @param ctx the parse tree
	 */
	void exitCntAggContent(NumCondAggregationParser.CntAggContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#parseConditionalAggregation}.
	 * @param ctx the parse tree
	 */
	void enterParseConditionalAggregation(NumCondAggregationParser.ParseConditionalAggregationContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#parseConditionalAggregation}.
	 * @param ctx the parse tree
	 */
	void exitParseConditionalAggregation(NumCondAggregationParser.ParseConditionalAggregationContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#numAggContent}.
	 * @param ctx the parse tree
	 */
	void enterNumAggContent(NumCondAggregationParser.NumAggContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#numAggContent}.
	 * @param ctx the parse tree
	 */
	void exitNumAggContent(NumCondAggregationParser.NumAggContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#aggSource}.
	 * @param ctx the parse tree
	 */
	void enterAggSource(NumCondAggregationParser.AggSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#aggSource}.
	 * @param ctx the parse tree
	 */
	void exitAggSource(NumCondAggregationParser.AggSourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#aggRange}.
	 * @param ctx the parse tree
	 */
	void enterAggRange(NumCondAggregationParser.AggRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#aggRange}.
	 * @param ctx the parse tree
	 */
	void exitAggRange(NumCondAggregationParser.AggRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#aggCondition}.
	 * @param ctx the parse tree
	 */
	void enterAggCondition(NumCondAggregationParser.AggConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#aggCondition}.
	 * @param ctx the parse tree
	 */
	void exitAggCondition(NumCondAggregationParser.AggConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#eventExp}.
	 * @param ctx the parse tree
	 */
	void enterEventExp(NumCondAggregationParser.EventExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#eventExp}.
	 * @param ctx the parse tree
	 */
	void exitEventExp(NumCondAggregationParser.EventExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#nonNumExp}.
	 * @param ctx the parse tree
	 */
	void enterNonNumExp(NumCondAggregationParser.NonNumExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#nonNumExp}.
	 * @param ctx the parse tree
	 */
	void exitNonNumExp(NumCondAggregationParser.NonNumExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#numExp}.
	 * @param ctx the parse tree
	 */
	void enterNumExp(NumCondAggregationParser.NumExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#numExp}.
	 * @param ctx the parse tree
	 */
	void exitNumExp(NumCondAggregationParser.NumExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#indexExp}.
	 * @param ctx the parse tree
	 */
	void enterIndexExp(NumCondAggregationParser.IndexExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#indexExp}.
	 * @param ctx the parse tree
	 */
	void exitIndexExp(NumCondAggregationParser.IndexExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(NumCondAggregationParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(NumCondAggregationParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#queryNumeric}.
	 * @param ctx the parse tree
	 */
	void enterQueryNumeric(NumCondAggregationParser.QueryNumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#queryNumeric}.
	 * @param ctx the parse tree
	 */
	void exitQueryNumeric(NumCondAggregationParser.QueryNumericContext ctx);
	/**
	 * Enter a parse tree produced by {@link NumCondAggregationParser#realNumber}.
	 * @param ctx the parse tree
	 */
	void enterRealNumber(NumCondAggregationParser.RealNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link NumCondAggregationParser#realNumber}.
	 * @param ctx the parse tree
	 */
	void exitRealNumber(NumCondAggregationParser.RealNumberContext ctx);
}