/*
 * Copyright 2012 - Six Dimensions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.sixdimensions.wcm.cq.cqex.tags;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.tldgen.annotations.Attribute;

/**
 * Abstract class for tags which set an attribute to extend. I am limiting the
 * variable scope to page and request as using the session is discouraged in
 * Adobe CQ and setting an application variable from JSP seems like a very bad
 * idea.
 * 
 * @author dklco
 */
public abstract class AttributeSettingTag extends TagSupport {
	/**
	 * The UID for this class.
	 */
	private static final long serialVersionUID = -7483515583932692390L;

	/**
	 * The scope name for the page context.
	 */
	public static final String PAGE_SCOPE = "page";

	/**
	 * The scope name for the current request.
	 */
	public static final String REQUEST_SCOPE = "request";

	/**
	 * The scope in which to save the attribute. May be either one of page or
	 * request.
	 */
	@Attribute
	private String scope = "session";

	/**
	 * The page context variable to save the value.
	 */
	@Attribute(required = true)
	private String var;

	/**
	 * Get the scope in which the variable will be saved.
	 * 
	 * @return the scope
	 */
	public String getScope() {
		return this.scope;
	}

	/**
	 * Get the variable name in which to save the variable.
	 * 
	 * @return the variable name
	 */
	public String getVar() {
		return this.var;
	}

	/**
	 * Save the variable into either the request or page scope.
	 * 
	 * @param name
	 *            the name of the variable to save
	 * @param value
	 *            the value to save in the variable
	 */
	public void setAttribute(final String name, final Object value) {
		int iScope = PageContext.PAGE_SCOPE;
		if (this.scope.equals(REQUEST_SCOPE)) {
			iScope = PageContext.REQUEST_SCOPE;
		}

		this.pageContext.setAttribute(name, value, iScope);
	}

	/**
	 * Set the scope that will be used to determine where the variable is saved.
	 * 
	 * @param varScope
	 *            the scope, can be either 'page' or 'request'
	 */
	public void setScope(final String varScope) {
		this.scope = varScope;
	}

	/**
	 * Set the variable name in which to save the value.
	 * 
	 * @param varName
	 *            the variable name
	 */
	public void setVar(final String varName) {
		this.var = varName;
	}
}
