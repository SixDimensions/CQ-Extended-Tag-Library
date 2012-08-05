/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.tags;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tldgen.annotations.Attribute;
import org.tldgen.annotations.BodyContent;
import org.tldgen.annotations.Tag;

/**
 * Retrieves the resource at the specified path and makes it available as a
 * Sling Resource.
 * 
 * @author dklco
 */
@Tag(bodyContent = BodyContent.EMPTY, example = "&lt;cqex:getResource var=\"resource\" path=\"/content/mysite\" />")
public class GetResourceTag extends AttributeSettingTag {
	private static final Logger log = LoggerFactory
			.getLogger(GetResourceTag.class);
	private static final long serialVersionUID = 5861756752614447760L;

	/**
	 * The absolute path of the resource to retrieve
	 */
	@Attribute(required = true)
	private String path;

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
		this.setAttribute(this.var, resource);
		return javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
	}

	/**
	 * Get the path of the resource to retrieve.
	 * 
	 * @return the path of the resource to retrieve
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Set the absolute path of the resource to retrieve.
	 * 
	 * @param path
	 *            the path of the resource to retrieve, must be absolute
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
