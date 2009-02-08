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

import org.eclipse.wst.xml.xpath2.processor.types.*;

/**
 * Class for Function Call support.
 */
public class FunctionCall extends PrimaryExpr {
	private QName _name;
	private Collection _args;

	/**
	 * Constructor for FunctionCall.
	 * 
	 * @param name
	 *            QName.
	 * @param args
	 *            Collection of arguments.
	 */
	public FunctionCall(QName name, Collection args) {
		_name = name;
		_args = args;
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
	 * Support for QName interface.
	 * 
	 * @return Result of QName operation.
	 */
	public QName name() {
		return _name;
	}

	/**
	 * Support for Iterator interface.
	 * 
	 * @return Result of Iterator operation.
	 */
	public Iterator iterator() {
		return _args.iterator();
	}

	/**
	 * Support for Arity interface.
	 * 
	 * @return Result of Arity operation.
	 */
	public int arity() {
		return _args.size();
	}
}