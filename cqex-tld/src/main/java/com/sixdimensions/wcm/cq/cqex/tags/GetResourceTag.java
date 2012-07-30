/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.tags;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Retrieves the resource at the specified path and makes it available as a
 * Sling Resource.
 * 
 * @author dklco
 */
public class GetResourceTag extends TagSupport {
	private static final long serialVersionUID = 5861756752614447760L;
	private static final Logger log = LoggerFactory
			.getLogger(GetResourceTag.class);
	private String path;
	private String var = "resource";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() {
		log.trace("doEndTag");

		log.debug("Retrieving resource at path");
		SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) this.pageContext
				.getRequest();
		Resource resource = slingRequest.getResourceResolver().getResource(
				this.path);
		log.debug("Retrieved resource: "
				+ (resource != null ? resource.getPath() : "null"));
		this.pageContext.setAttribute(this.var, resource,
				PageContext.REQUEST_SCOPE);
		return Tag.EVAL_PAGE;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setVar(String var) {
		this.var = var;
	}
}
