/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.tags;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.tldgen.annotations.Attribute;

/**
 * Abstract class for tags which set a variable to extend.
 * 
 * @author dklco
 */
public abstract class AttributeSettingTag extends TagSupport {
	private static final long serialVersionUID = -7483515583932692390L;
	public static final String PAGE_SCOPE = "page";
	public static final String REQUEST_SCOPE = "request";

	/**
	 * The scope in which to save the attribute. May be either one of page or
	 * request.
	 */
	@Attribute
	private String scope = "session";

	/**
	 * The page context variable to save the value
	 */
	@Attribute(required = true)
	protected String var;

	public String getScope() {
		return this.scope;
	}

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
	public void setAttribute(String name, Object value) {
		int iScope = PageContext.PAGE_SCOPE;
		if (this.scope.equals(REQUEST_SCOPE)) {
			iScope = PageContext.REQUEST_SCOPE;
		}

		this.pageContext.setAttribute(name, value, iScope);
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setVar(String var) {
		this.var = var;
	}
}
