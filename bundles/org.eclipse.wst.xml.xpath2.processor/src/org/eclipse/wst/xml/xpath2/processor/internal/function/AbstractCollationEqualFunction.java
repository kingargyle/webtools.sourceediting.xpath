/*******************************************************************************
 * Copyright (c) 2009 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     David Carver (STAR) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.math.BigInteger;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.processor.DynamicContext;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NumericType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBoolean;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDateTime;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDouble;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDuration;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSFloat;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSUntypedAtomic;

public abstract class AbstractCollationEqualFunction extends Function {

	public AbstractCollationEqualFunction(QName name, int arity) {
		super(name, arity);
		// TODO Auto-generated constructor stub
	}

	public AbstractCollationEqualFunction(QName name, int min_arity,
			int max_arity) {
		super(name, min_arity, max_arity);
	}


	protected static boolean hasValue(AnyType itema, AnyType itemb, DynamicContext context, String collationURI) throws DynamicError {
		XSString itemStr = new XSString(itema.string_value());
		if (isBoolean(itema, itemb)) {
			XSBoolean boolat = (XSBoolean) itema;
			if (boolat.eq(itemb, context)) {
				return true;
			}
		}

		if (isNumeric(itema, itemb)) {
			NumericType numericat = (NumericType) itema;
			if (numericat.eq(itemb, context)) {
				return true;
			}
		}

		if (isDuration(itema, itemb)) {
			XSDuration durat = (XSDuration) itema;
			if (durat.eq(itemb, context)) {
				return true;
			}
		}

		if (needsStringComparison(itema, itemb)) {
			XSString xstr1 = new XSString(itema.string_value());
			if (FnCompare.compare_string(collationURI, xstr1, itemStr,
					context).equals(BigInteger.ZERO)) {
				return true;
			}
		}
		return false;
	}
	
	protected static boolean hasValue(ResultSequence rs, AnyAtomicType item,
			DynamicContext context, String collationURI) throws DynamicError {
		XSString itemStr = new XSString(item.string_value());

		for (Iterator i = rs.iterator(); i.hasNext();) {
			AnyType at = (AnyType) i.next();

			if (!(at instanceof CmpEq))
				continue;

			if (isBoolean(item, at)) {
				XSBoolean boolat = (XSBoolean) at;
				if (boolat.eq(item, context)) {
					return true;
				}
			}

			if (isNumeric(item, at)) {
				NumericType numericat = (NumericType) at;
				if (numericat.eq(item, context)) {
					return true;
				}
			}

			if (isDuration(item, at)) {
				XSDuration durat = (XSDuration) at;
				if (durat.eq(item, context)) {
					return true;
				}
			}

			if (needsStringComparison(item, at)) {
				XSString xstr1 = new XSString(at.string_value());
				if (FnCompare.compare_string(collationURI, xstr1, itemStr,
						context).equals(BigInteger.ZERO)) {
					return true;
				}
			}
		}
		return false;
	}

	protected static boolean isDuration(AnyAtomicType item, AnyType at) {
		return at instanceof XSDuration && item instanceof XSDuration;
	}

	protected static boolean isBoolean(AnyAtomicType item, AnyType at) {
		return at instanceof XSBoolean && item instanceof XSBoolean;
	}

	protected static boolean isNumeric(AnyAtomicType item, AnyType at) {
		return at instanceof NumericType && item instanceof NumericType;
	}

	protected static boolean needsStringComparison(AnyAtomicType item,
			AnyType at) {
		return (at instanceof XSString
				&& (!(item instanceof NumericType) || item instanceof XSUntypedAtomic) || ((at instanceof XSFloat || at instanceof XSDouble) && (item instanceof XSFloat || item instanceof XSDouble))
				&& (!(item instanceof XSDateTime)));
	}

	protected static boolean isDuration(AnyType item, AnyType at) {
		return at instanceof XSDuration && item instanceof XSDuration;
	}
	
	protected static boolean isDate(AnyType item, AnyType at) {
		return at instanceof XSDateTime && item instanceof XSDateTime;
	}
	

	protected static boolean isBoolean(AnyType cmptype, AnyType at) {
		return at instanceof XSBoolean && cmptype instanceof XSBoolean;
	}

	protected static boolean isNumeric(AnyType item, AnyType at) {
		return at instanceof NumericType && item instanceof NumericType;
	}

	protected static boolean needsStringComparison(AnyType item, AnyType at) {
		return (at instanceof XSString && (!(item instanceof NumericType) || item instanceof XSUntypedAtomic)
			|| ((at instanceof XSFloat || at instanceof XSDouble) && (item instanceof XSFloat || item instanceof XSDouble))	
			&& (!(item instanceof XSDateTime)));
	}

}