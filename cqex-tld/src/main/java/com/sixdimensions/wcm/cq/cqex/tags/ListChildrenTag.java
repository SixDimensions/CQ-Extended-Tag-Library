/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.tags;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sixdimensions.wcm.cq.cqex.util.IterableIterator;

/**
 * Tag for listing the children of a resource. The children are saved into the
 * page context as a variable with the name var and are saved as an
 * IterableIterator, which means they can be iterated through JSTL.
 * 
 * @author dklco
 */
public class ListChildrenTag extends TagSupport {
	private static final long serialVersionUID = 5861756752614447760L;
	private static final Logger log = LoggerFactory
			.getLogger(ListChildrenTag.class);
	private Resource resource;
	private String var = "resources";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() {
		log.trace("doEndTag");
		if (this.resource != null) {
			log.debug("Listing children of: " + this.resource.getPath());
			IterableIterator<Resource> children = new IterableIterator<Resource>(
					this.resource.listChildren());
			this.pageContext.setAttribute(this.var, children,
					PageContext.REQUEST_SCOPE);
		} else {
			log.debug("No/null resource specified");
		}
		return Tag.EVAL_PAGE;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setVar(String var) {
		this.var = var;
	}
}
