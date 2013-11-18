/*
 * Copyright 2013 - Six Dimensions
 * All Rights Reserved
 */
package com.sixdimensions.wcm.cq.taglib.ext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tldgen.Tag;

/**
 * The WriteDeferred Tag writes all of the HTML deferred by the Defer Tag.
 * 
 * @author dklco
 * @see com.sixdimensions.wcm.cq.taglib.ext.DeferTag
 */
@Tag
public class WriteDeferredTag extends TagSupport {

	/**
	 * The Serialization UID
	 */
	private static final long serialVersionUID = 3252945435842424818L;

	/**
	 * The SLF4J Logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(WriteDeferredTag.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() {
		log.trace("doEndTag");

		@SuppressWarnings("unchecked")
		final Map<String, byte[]> defers = Map.class.cast(this.pageContext
				.getAttribute(DeferTag.class.getCanonicalName() + ".defers",
						PageContext.REQUEST_SCOPE));

		if (defers != null) {
			for (final Map.Entry<String, byte[]> entry : defers.entrySet()) {
				log.debug("Writing content for {}", entry.getKey());
				try {
					IOUtils.copy(new ByteArrayInputStream(entry.getValue()),
							this.pageContext.getOut());
				} catch (final IOException e) {
					log.warn(
							"Exception writing deferred content "
									+ entry.getKey(), e);
				}
			}
		}

		return EVAL_PAGE;
	}
}