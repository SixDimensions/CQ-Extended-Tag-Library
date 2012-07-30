/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.tags;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

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
	private Logger log = LoggerFactory.getLogger(GetPropertiesTag.class);
	private String path;
	private String var;
	private Resource resource;

	/**
	 * @InheretDoc
	 */
	public int doEndTag() throws JspException {
		log.trace("doEndTag");

		Map<?, ?> properties = null;
		SlingHttpServletRequest request = (SlingHttpServletRequest) pageContext
				.getRequest();

		Resource rsrc = null;
		if (resource != null) {
			log.trace("Using resource: " + resource.getPath());
			rsrc = resource;
		} else if (path.startsWith("/")) {
			log.trace("Finding resource at absolute path: " + path);
			rsrc = request.getResourceResolver().getResource(path);
		} else {
			log.trace("Finding resource at relative path: " + path);
			rsrc = request.getResourceResolver().getResource(
					request.getResource(), path);
		}
		if (rsrc != null
				&& !rsrc.getResourceType().equals(
						Resource.RESOURCE_TYPE_NON_EXISTING)) {
			properties = rsrc.adaptTo(ValueMap.class);
			pageContext.setAttribute(var, properties);
		} else {
			log.debug("Resource not found at path: " + path);
		}

		return TagSupport.EVAL_PAGE;
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
