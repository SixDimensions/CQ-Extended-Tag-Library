/*
 * Copyright 2013 - Six Dimensions
 * All Rights Reserved
 */
package com.sixdimensions.wcm.cq.taglib.ext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.WCMMode;

/**
 * Allows content to be displayed or hidden depending on the WCMMode for the
 * current page.
 * 
 * @author dklco
 * @see com.day.cq.wcm.api.WCMMode
 */
public class DisplayModeTag extends BodyTagSupport {

	/**
	 * The SLF4J Logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(DisplayModeTag.class);

	/**
	 * The Serialization UID
	 */
	private static final long serialVersionUID = 6346480082928570731L;

	/**
	 * A comma separated list of the modes in which to display the body contents
	 * of this tag. Must be valid CQ WCMModes.
	 */
	private String displayModes;

	/**
	 * A comma separated list of the modes in which to hide the body contents of
	 * this tag. Must be valid CQ WCMModes.
	 */
	private String hideModes;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		int evalBody = SKIP_BODY;
		final WCMMode currentMode = WCMMode.fromRequest(this.pageContext
				.getRequest());

		// Check the display modes
		if (!StringUtils.isEmpty(this.displayModes)) {
			evalBody = SKIP_BODY;
			final String[] displayModeStrs = this.displayModes.toUpperCase()
					.split("\\,");
			for (final String displayModeStr : displayModeStrs) {
				final WCMMode displayMode = WCMMode.valueOf(displayModeStr);
				if (displayMode == null) {
					log.warn("Invalid display mode {} specified",
							displayModeStr);
					continue;
				}
				if (currentMode == displayMode) {
					evalBody = EVAL_BODY_INCLUDE;
					break;
				}
			}
		} else if (!StringUtils.isEmpty(this.hideModes)) {

			// check the hide mode
			evalBody = EVAL_BODY_INCLUDE;
			final String[] hideModeStrs = this.hideModes.toUpperCase().split(
					"\\,");
			for (final String hideModeStr : hideModeStrs) {
				final WCMMode hideMode = WCMMode.valueOf(hideModeStr);
				if (hideMode == null) {
					log.warn("Invalid hide mode {} specified", hideModeStr);
					continue;
				}
				if (currentMode == hideMode) {
					evalBody = SKIP_BODY;
					break;
				}
			}
		}
		return evalBody;
	}

	/**
	 * @return the mode
	 */
	public String getDisplayModes() {
		return this.displayModes;
	}

	/**
	 * @return the hideModes
	 */
	public String getHideModes() {
		return this.hideModes;
	}

	/**
	 * @param modes
	 *            the modes to set
	 */
	public void setDisplayModes(final String modes) {
		this.displayModes = modes;
	}

	/**
	 * @param hideModes
	 *            the hideModes to set
	 */
	public void setHideModes(final String hideModes) {
		this.hideModes = hideModes;
	}

}
