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
 * Returns an expanded-QName for node kinds that can have names. For other kinds
 * of nodes it returns the empty sequence. If $arg is the empty sequence, the
 * empty sequence is returned.
 */
public class FnNodeName extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnNodeName.
	 */
	public FnNodeName() {
		super(new QName("node-name"), 1);
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
		return node_name(args);
	}

	/**
	 * Node-Name operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:node-name operation.
	 */
	public static ResultSequence node_name(Collection args) throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());

		ResultSequence rs = ResultSequenceFactory.create_new();

		ResultSequence arg1 = (ResultSequence) cargs.iterator().next();
		if (arg1.empty())
			return rs;

		NodeType nt = (NodeType) arg1.first();

		QName nodename = nt.node_name();
		if (nodename == null)
			return rs;

		rs.add(nodename);

		return rs;
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args.add(new SeqType(SeqType.OCC_QMARK));
		}

		return _expected_args;
	}
}