/*******************************************************************************
 * Copyright (c) 2005, 2009 Andrea Bittau and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.ast;

import java.util.*;

/**
 * Class for AxisStep, this generates a sequence of zero or more nodes. These
 * nodes are always returned in Document Order. This can be Forward Step or
 * Reverse Step.
 */
public class AxisStep extends StepExpr {
	private Step _step;
	private Collection _exprs;

	/**
	 * Constructor for AxisStep.
	 * 
	 * @param step
	 *            Defines forward/reverse step.
	 * @param exprs
	 *            Collection of xpath expressions.
	 */
	public AxisStep(Step step, Collection exprs) {
		_step = step;
		_exprs = exprs;
	}

	/**
	 * Support for Visitor interface.
	 * 
	 * @return Result of Visitor operation.
	 */
	@Override
	public Object accept(XPathVisitor v) {
		return v.visit(this);
	}

	/**
	 * Advances to next step.
	 * 
	 * @return Previous step.
	 */
	public Step step() {
		return _step;
	}

	/**
	 * Set the step direction.
	 */
	public void set_step(Step s) {
		_step = s;
	}

	/**
	 * Interator.
	 * 
	 * @return Iterated expressions.
	 */
	public Iterator iterator() {
		return _exprs.iterator();
	}

	/**
	 * Determines size of expressions.
	 * 
	 * @return Size of expressions.
	 */
	public int predicate_count() {
		return _exprs.size();
	}
}