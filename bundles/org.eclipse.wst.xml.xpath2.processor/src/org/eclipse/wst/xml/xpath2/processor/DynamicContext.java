/*******************************************************************************
 * Copyright (c) 2005, 2009 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0
 *     Mukul Gandhi - bug 273760 - wrong namespace for functions and data types
 *     David Carver - bug 282223 - implementation of xs:duration data types. 
 *     Jesper Moller- bug 281159 - fix document loading and resolving URIs 
 *     Jesper Moller- bug 286452 - always return the stable date/time from dynamic context
 *     Jesper Moller- bug 275610 - Avoid big time and memory overhead for externals
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

import org.eclipse.wst.xml.xpath2.processor.internal.Focus;
import org.eclipse.wst.xml.xpath2.processor.internal.types.*;

import java.net.URI;
import java.util.*;
import org.w3c.dom.*;

/**
 * Interface for dynamic context.
 */
public interface DynamicContext extends StaticContext {

	/**
	 * Get context item.
	 * 
	 * @return the context item.
	 */
	public AnyType context_item();

	/**
	 * Get context node position.
	 * 
	 * @return position of context node.
	 */
	public int context_position();

	/**
	 * Get position of last item.
	 * 
	 * @return last item position.
	 */
	public int last();

	/**
	 * Get variable.
	 * 
	 * @param name
	 *            is the name of the variable.
	 * @return variable.
	 */
	public AnyType get_variable(QName name);
	
	/**
	 * Set variable.
	 * 
	 * @param var
	 *            is name of the variable.
	 * @param val
	 *            is the value to be set for the variable.
	 */
	public void set_variable(QName var, AnyType val);

	/**
	 * Evaluate the function of the arguments.
	 * 
	 * @param name
	 *            is the name.
	 * @param args
	 *            are the arguments.
	 * @throws DynamicError
	 *             dynamic error.
	 * @return result of the function evaluation.
	 */
	public ResultSequence evaluate_function(QName name, Collection args)
			throws DynamicError;

	/**
	 * Reads the day from a TimeDuration type
	 * 
	 * @return current date time and implicit timezone.
	 * @since 1.1
	 */
	public XSDuration tz();

	/**
	 * Get document.
	 * 
	 * @param uri
	 *            is the URI of the document.
	 * @return document.
	 * @since 1.1
	 */
	// available doc
	public ResultSequence get_doc(URI uri);

	/**
	 * Resolve an URI
	 * 
	 * @param uri
	 *            is the possibly relative URI to resolve
	 * @return the absolutized, resolved URI.
	 * @since 1.1
	 */
	public URI resolve_uri(String uri);

	// available collections

	// default collection

	/**
	 * Returns the current date time using the GregorianCalendar.
	 * 
	 * @return The current date and time, which will always be same for the dynamic context.
	 * @since 1.1
	 */
	public GregorianCalendar current_date_time();
	
	/**
	 * Set focus.
	 * 
	 * @param focus
	 *            is focus to be set.
	 */
	// Other functions
	public void set_focus(Focus focus);

	/**
	 * Return focus.
	 * 
	 * @return Focus
	 */
	public Focus focus();


}
