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

package org.eclipse.wst.xml.xpath2.processor.function;

import org.eclipse.wst.xml.xpath2.processor.*;
import org.eclipse.wst.xml.xpath2.processor.types.*;

import java.util.*;

/**
 * Returns an xs:integer indicating the number of items in the sequence of items
 * currently being processed. If the context item is undefined, an error is
 * raised [err:FONC0001].
 */
public class FnLast extends Function {
	/**
	 * Constructor for FnLast.
	 */
	public FnLast() {
		super(new QName("last"), 0);
	}

	/**
	 * Evaluate arguments.
	 * 
	 * @param args
	 *            argument expressions.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of evaluation.
	 */
	@Override
	public ResultSequence evaluate(Collection args) throws DynamicError {
		return last(args, dynamic_context());
	}

	/**
	 * Last operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @param dc
	 *            Result of dynamic context operation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:last operation.
	 */
	public static ResultSequence last(Collection args, DynamicContext dc)
			throws DynamicError {
		assert args.size() == 0;

		int last = dc.last();

		assert last != 0;

		return ResultSequenceFactory.create_new(new XSInteger(last));
	}
}