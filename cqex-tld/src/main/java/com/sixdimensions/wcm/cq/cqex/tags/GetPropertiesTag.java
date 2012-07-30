/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.tags;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tag that retrieves the properties from the resource at a path and saves them
 * into a page context variable as a ValueMap.
 * 
 * @author dklco
 */
public class GetPropertiesTag extends TagSupport {
	private static final long serialVersionUID = 2906794811653608479L;
	private static final Logger log = LoggerFactory
			.getLogger(GetPropertiesTag.class);
	private String path;
	private String var;
	private Resource resource;

	/**
	 * @InheretDoc
	 */
	@Override
	public int doEndTag() throws JspException {
		GetPropertiesTag.log.trace("doEndTag");

		Map<?, ?> properties = null;
		SlingHttpServletRequest request = (SlingHttpServletRequest) this.pageContext
				.getRequest();

		Resource rsrc = null;
		if ((this.resource != null) && !StringUtils.isEmpty(this.path)) {
			GetPropertiesTag.log.trace("Finding resource at relative path: "
					+ this.path);
			rsrc = request.getResourceResolver().getResource(
					request.getResource(), this.path);
		} else if ((this.resource != null) && StringUtils.isEmpty(this.path)) {
			GetPropertiesTag.log.trace("Using resource: "
					+ this.resource.getPath());
			rsrc = this.resource;
		} else if (this.path.startsWith("/")) {
			GetPropertiesTag.log.trace("Finding resource at absolute path: "
					+ this.path);
			rsrc = request.getResourceResolver().getResource(this.path);
		} else {
			GetPropertiesTag.log
					.warn("Unable to retrieve resource, neither path nor resource specified.");
		}

		if ((rsrc != null)
				&& !rsrc.getResourceType().equals(
						Resource.RESOURCE_TYPE_NON_EXISTING)) {
			properties = rsrc.adaptTo(ValueMap.class);
			this.pageContext.setAttribute(this.var, properties);
		} else {
			GetPropertiesTag.log.debug("Resource not found at path: "
					+ this.path);
		}

		return Tag.EVAL_PAGE;
	}

	/**
	 * Set the path of the resource to retrieve.
	 * 
	 * @param path
	 *            the path of the resource to retrieve
	 * @throws JspTagException
	 */
	public void setPath(String path) throws JspTagException {
		this.path = path;
	}

	/**
	 * Set the resource to retrieve the properties from.
	 * 
	 * @param resource
	 *            the resource from which to retrieve the properties
	 * @throws JspTagException
	 */
	public void setResource(Resource resource) throws JspTagException {
		this.resource = resource;
	}

	/**
	 * Set the page context variable to which to save the valuemap of
	 * properties.
	 * 
	 * @param var
	 *            the name of the page context variable
	 * @throws JspTagException
	 */
	public void setVar(String var) throws JspTagException {
		this.var = var;
	}
}
