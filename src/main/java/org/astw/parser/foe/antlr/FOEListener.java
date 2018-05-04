// Generated from FOE.g4 by ANTLR 4.7

package org.astw.parser.foe.antlr;

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
 * {@link FOEParser}.
 */
public interface FOEListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FOEParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(FOEParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOEParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(FOEParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOEParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(FOEParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOEParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(FOEParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOEParser#quantification}.
	 * @param ctx the parse tree
	 */
	void enterQuantification(FOEParser.QuantificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOEParser#quantification}.
	 * @param ctx the parse tree
	 */
	void exitQuantification(FOEParser.QuantificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOEParser#eventExp}.
	 * @param ctx the parse tree
	 */
	void enterEventExp(FOEParser.EventExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOEParser#eventExp}.
	 * @param ctx the parse tree
	 */
	void exitEventExp(FOEParser.EventExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOEParser#nonNumExp}.
	 * @param ctx the parse tree
	 */
	void enterNonNumExp(FOEParser.NonNumExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOEParser#nonNumExp}.
	 * @param ctx the parse tree
	 */
	void exitNonNumExp(FOEParser.NonNumExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOEParser#numExp}.
	 * @param ctx the parse tree
	 */
	void enterNumExp(FOEParser.NumExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOEParser#numExp}.
	 * @param ctx the parse tree
	 */
	void exitNumExp(FOEParser.NumExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOEParser#indexExp}.
	 * @param ctx the parse tree
	 */
	void enterIndexExp(FOEParser.IndexExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOEParser#indexExp}.
	 * @param ctx the parse tree
	 */
	void exitIndexExp(FOEParser.IndexExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOEParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(FOEParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOEParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(FOEParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link FOEParser#queryNumeric}.
	 * @param ctx the parse tree
	 */
	void enterQueryNumeric(FOEParser.QueryNumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link FOEParser#queryNumeric}.
	 * @param ctx the parse tree
	 */
	void exitQueryNumeric(FOEParser.QueryNumericContext ctx);
}