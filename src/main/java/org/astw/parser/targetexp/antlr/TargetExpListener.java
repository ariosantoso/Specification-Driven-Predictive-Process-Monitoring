// Generated from TargetExp.g4 by ANTLR 4.7

package org.astw.parser.targetexp.antlr;

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
import org.astw.analyticrules.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TargetExpParser}.
 */
public interface TargetExpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TargetExpParser#targetExp}.
	 * @param ctx the parse tree
	 */
	void enterTargetExp(TargetExpParser.TargetExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link TargetExpParser#targetExp}.
	 * @param ctx the parse tree
	 */
	void exitTargetExp(TargetExpParser.TargetExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link TargetExpParser#nonNumExp}.
	 * @param ctx the parse tree
	 */
	void enterNonNumExp(TargetExpParser.NonNumExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link TargetExpParser#nonNumExp}.
	 * @param ctx the parse tree
	 */
	void exitNonNumExp(TargetExpParser.NonNumExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link TargetExpParser#numExp}.
	 * @param ctx the parse tree
	 */
	void enterNumExp(TargetExpParser.NumExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link TargetExpParser#numExp}.
	 * @param ctx the parse tree
	 */
	void exitNumExp(TargetExpParser.NumExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link TargetExpParser#indexExp}.
	 * @param ctx the parse tree
	 */
	void enterIndexExp(TargetExpParser.IndexExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link TargetExpParser#indexExp}.
	 * @param ctx the parse tree
	 */
	void exitIndexExp(TargetExpParser.IndexExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link TargetExpParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(TargetExpParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link TargetExpParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(TargetExpParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link TargetExpParser#queryNumeric}.
	 * @param ctx the parse tree
	 */
	void enterQueryNumeric(TargetExpParser.QueryNumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link TargetExpParser#queryNumeric}.
	 * @param ctx the parse tree
	 */
	void exitQueryNumeric(TargetExpParser.QueryNumericContext ctx);
}