// Generated from NonNumCondAggregation.g4 by ANTLR 4.7

package org.astw.parser.nonNumConditinoalAggregation.antlr;

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
 * {@link NonNumCondAggregationParser}.
 */
public interface NonNumCondAggregationListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#parseConditionalAggregation}.
	 * @param ctx the parse tree
	 */
	void enterParseConditionalAggregation(NonNumCondAggregationParser.ParseConditionalAggregationContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#parseConditionalAggregation}.
	 * @param ctx the parse tree
	 */
	void exitParseConditionalAggregation(NonNumCondAggregationParser.ParseConditionalAggregationContext ctx);
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#nonNumAggContent}.
	 * @param ctx the parse tree
	 */
	void enterNonNumAggContent(NonNumCondAggregationParser.NonNumAggContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#nonNumAggContent}.
	 * @param ctx the parse tree
	 */
	void exitNonNumAggContent(NonNumCondAggregationParser.NonNumAggContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#aggSource}.
	 * @param ctx the parse tree
	 */
	void enterAggSource(NonNumCondAggregationParser.AggSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#aggSource}.
	 * @param ctx the parse tree
	 */
	void exitAggSource(NonNumCondAggregationParser.AggSourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#aggRange}.
	 * @param ctx the parse tree
	 */
	void enterAggRange(NonNumCondAggregationParser.AggRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#aggRange}.
	 * @param ctx the parse tree
	 */
	void exitAggRange(NonNumCondAggregationParser.AggRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#aggCondition}.
	 * @param ctx the parse tree
	 */
	void enterAggCondition(NonNumCondAggregationParser.AggConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#aggCondition}.
	 * @param ctx the parse tree
	 */
	void exitAggCondition(NonNumCondAggregationParser.AggConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#eventExp}.
	 * @param ctx the parse tree
	 */
	void enterEventExp(NonNumCondAggregationParser.EventExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#eventExp}.
	 * @param ctx the parse tree
	 */
	void exitEventExp(NonNumCondAggregationParser.EventExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#nonNumExp}.
	 * @param ctx the parse tree
	 */
	void enterNonNumExp(NonNumCondAggregationParser.NonNumExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#nonNumExp}.
	 * @param ctx the parse tree
	 */
	void exitNonNumExp(NonNumCondAggregationParser.NonNumExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#numExp}.
	 * @param ctx the parse tree
	 */
	void enterNumExp(NonNumCondAggregationParser.NumExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#numExp}.
	 * @param ctx the parse tree
	 */
	void exitNumExp(NonNumCondAggregationParser.NumExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#indexExp}.
	 * @param ctx the parse tree
	 */
	void enterIndexExp(NonNumCondAggregationParser.IndexExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#indexExp}.
	 * @param ctx the parse tree
	 */
	void exitIndexExp(NonNumCondAggregationParser.IndexExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(NonNumCondAggregationParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(NonNumCondAggregationParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#queryNumeric}.
	 * @param ctx the parse tree
	 */
	void enterQueryNumeric(NonNumCondAggregationParser.QueryNumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#queryNumeric}.
	 * @param ctx the parse tree
	 */
	void exitQueryNumeric(NonNumCondAggregationParser.QueryNumericContext ctx);
	/**
	 * Enter a parse tree produced by {@link NonNumCondAggregationParser#realNumber}.
	 * @param ctx the parse tree
	 */
	void enterRealNumber(NonNumCondAggregationParser.RealNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link NonNumCondAggregationParser#realNumber}.
	 * @param ctx the parse tree
	 */
	void exitRealNumber(NonNumCondAggregationParser.RealNumberContext ctx);
}