/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.tags;

import javax.servlet.jsp.JspException;

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
public class GetPropertyTag extends AttributeSettingTag {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
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
		this.setAttribute(this.var, value);
		return javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
	}

	/**
	 * Gets the default value.
	 * 
	 * @return the default value
	 */
	public Object getDefaultValue() {
		return this.defaultValue;
	}

	/**
	 * Gets the key to retrieve.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Gets the map of properties.
	 * 
	 * @return the value map of properties
	 */
	public ValueMap getProperties() {
		return this.properties;
	}

	/**
	 * Set the default value
	 * 
	 * @param defaultValue
	 *            the default value
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Sets the key
	 * 
	 * @param key
	 *            the key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Sets the properties.
	 * 
	 * @param properties
	 *            the properties
	 */
	public void setProperties(ValueMap properties) {
		this.properties = properties;
	}
}
