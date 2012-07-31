/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tldgen.annotations.Attribute;
import org.tldgen.annotations.BodyContent;
import org.tldgen.annotations.Tag;

/**
 * Tag for retrieving property from a ValueMap, allowing the specification of a
 * default value.
 * 
 * @author dklco
 */
@Tag(bodyContent = BodyContent.EMPTY, example = "&lt;cqex:getProperty var=\"prop\" key=\"myProp\" properties=\"${properties}\" defaultValue=\"Default\"/>")
public class GetPropertyTag extends TagSupport {
	private static final Logger log = LoggerFactory
			.getLogger(GetPropertyTag.class);
	private static final long serialVersionUID = 2906794811653608479L;

	/**
	 * The default value. If no value is retrieved this value will be returned.
	 */
	@Attribute
	private Object defaultValue;

	/**
	 * The key of the attribute to retrieve
	 */
	@Attribute(required = true)
	private String key;

	/**
	 * The ValueMap from which to retrieve the values
	 */
	@Attribute(required = true)
	private ValueMap properties;

	/**
	 * The page context variable in which to save the resource
	 */
	@Attribute(required = true)
	private String var;

	/**
	 * @InheretDoc
	 */
	@Override
	public int doEndTag() throws JspException {
		log.trace("doEndTag");

		Object value = null;
		if (this.defaultValue != null) {
			value = this.properties.get(this.key, this.defaultValue);
		} else {
			value = this.properties.get(this.key);
		}
		this.pageContext.setAttribute(this.var, value);
		return javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
	}

	public Object getDefaultValue() {
		return this.defaultValue;
	}

	public String getKey() {
		return this.key;
	}

	public ValueMap getProperties() {
		return this.properties;
	}

	public String getVar() {
		return this.var;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setProperties(ValueMap properties) {
		this.properties = properties;
	}

	public void setVar(String var) throws JspTagException {
		this.var = var;
	}
}
