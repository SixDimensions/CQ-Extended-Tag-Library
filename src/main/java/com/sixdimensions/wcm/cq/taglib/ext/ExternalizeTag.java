/*
 * Copyright 2013 - Six Dimensions
 * All Rights Reserved
 */
package com.sixdimensions.wcm.cq.taglib.ext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScriptHelper;

import com.day.cq.commons.Externalizer;

/**
 * Tag for getting an external/mapped link to a path.
 * 
 * @author dklco
 */
public class ExternalizeTag extends TagSupport {

	/**
	 * The serialization UID
	 */
	private static final long serialVersionUID = -8963712253742824777L;

	/**
	 * The path to externalize
	 */
	private String path;

	/**
	 * The title mode, on of 'author', 'absolute' or 'relative', relative is the
	 * default.
	 */
	private String mode;

	/**
	 * The variable in which to save the externalized URL
	 */
	private String var;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {

		final SlingScriptHelper sling = SlingScriptHelper.class
				.cast(this.pageContext.getAttribute(SlingBindings.SLING));
		final Externalizer externalizer = sling.getService(Externalizer.class);
		final Resource resource = Resource.class.cast(this.pageContext
				.getAttribute(SlingBindings.RESOURCE));
		final SlingHttpServletRequest request = SlingHttpServletRequest.class
				.cast(this.pageContext.getAttribute(SlingBindings.REQUEST));

		String externalized = null;
		if ("author".equals(this.mode)) {
			externalized = externalizer.authorLink(
					resource.getResourceResolver(), this.path);
		} else if ("absolute".equals(this.mode)) {
			externalized = externalizer.absoluteLink(request,
					request.getScheme(), this.path);
		} else {
			externalized = externalizer.relativeLink(request, this.path);
		}
		this.pageContext.setAttribute(this.var, externalized);

		return EVAL_PAGE;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return this.mode;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * @return the var
	 */
	public String getVar() {
		return this.var;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(final String mode) {
		this.mode = mode;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(final String path) {
		this.path = path;
	}

	/**
	 * @param var
	 *            the var to set
	 */
	public void setVar(final String var) {
		this.var = var;
	}

}
