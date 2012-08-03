/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tldgen.annotations.Attribute;
import org.tldgen.annotations.BodyContent;
import org.tldgen.annotations.Tag;

import com.day.cq.wcm.api.PageManager;

/**
 * Tag that retrieves the page at or containing the specified path.
 * 
 * @author dklco
 */
@Tag(bodyContent = BodyContent.EMPTY, example = "&lt;cqex:getContainingPage var=\"page\" path=\"/content/geometrixxx/en/jcr:content\" />")
public class GetContainingPageTag extends AttributeSettingTag {
	private static final long serialVersionUID = 2906794811653608479L;
	private static final Logger log = LoggerFactory
			.getLogger(GetContainingPageTag.class);

	/**
	 * The absolute path of the page to retrieve or to a resource under the
	 * page.
	 */
	@Attribute
	private String path;

	/**
	 * @InheretDoc
	 */
	@Override
	public int doEndTag() throws JspException {
		log.trace("doEndTag");

		PageManager pageMgr = (PageManager) this.pageContext
				.getAttribute("pageManager");

		if (pageMgr != null) {
			this.setAttribute(this.var, pageMgr.getContainingPage(this.path));
		} else {
			log.error("Page manager not found");
		}

		return javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
	}

	/**
	 * The absolute path of the page to retrieve or to a resource under the
	 * page.
	 * 
	 * @param path
	 *            the path of the resource to retrieve
	 * @throws JspTagException
	 */
	public void setPath(String path) throws JspTagException {
		this.path = path;
	}
}
