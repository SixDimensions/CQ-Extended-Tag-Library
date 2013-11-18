/*
 * Copyright 2013 - Six Dimensions
 * All Rights Reserved
 */
package com.sixdimensions.wcm.cq.taglib.ext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tldgen.BodyContentType;
import tldgen.Tag;
import tldgen.TagAttribute;

/**
 * The Defer Tag defers the writing of HTML until a WriteDeferred Tag is found.
 * Usually this is before the closing of the body tag. It allows the
 * specification of an ID for the content, which means you can ensure only one
 * copy of the html is written to the page by setting the same ID for all of the
 * instances of a component.
 * 
 * @author dklco
 */
@Tag(bodyContentType = BodyContentType.JSP)
public class DeferTag extends BodyTagSupport {

	/**
	 * The SLF4J Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(DeferTag.class);

	/**
	 * The Serialization UID
	 */
	private static final long serialVersionUID = -1920884113021274033L;

	/**
	 * The ID for the content
	 */
	private String contentId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doAfterBody()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int doAfterBody() {
		log.trace("doAfterBody");

		Map<String, byte[]> defers = Map.class.cast(this.pageContext
				.getAttribute(DeferTag.class.getCanonicalName() + ".defers",
						PageContext.REQUEST_SCOPE));
		if (defers == null) {
			log.debug("Creating new deferred map");
			defers = new HashMap<String, byte[]>();
			this.pageContext.setAttribute(DeferTag.class.getCanonicalName()
					+ ".defers", defers, PageContext.REQUEST_SCOPE);
		}

		if (!defers.containsKey(this.contentId)) {
			log.debug("Adding deferred content for id {}", this.contentId);
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				IOUtils.copy(this.getBodyContent().getReader(), baos);
				defers.put(this.contentId, baos.toByteArray());
			} catch (final IOException e) {
				log.warn("Encountered IO Exception copying body content", e);
			}
		} else {
			log.debug("Deferred content for id {} already added", this.contentId);
		}
		return SKIP_BODY;
	}

	/**
	 * @return the id
	 */
	public String geContentId() {
		return this.contentId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@TagAttribute(required = true)
	public void setContentId(final String id) {
		this.contentId = id;
	}
}