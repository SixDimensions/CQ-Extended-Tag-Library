/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.functions;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathFunctions {
	private static final Logger log = LoggerFactory
			.getLogger(PathFunctions.class);

	/**
	 * Converts the specified path into a link based on the following rules:
	 * <ul>
	 * <li>if the path starts with 'http' use the path as is</li>
	 * <li>if the path contains '.' use the path as is</li>
	 * <li>otherwise append '.html'</li>
	 * </ul>
	 */
	public static String toLink(String path) {
		log.trace("toLink");

		if (path == null) {
			log.debug("Path is null");
			return null;
		}

		log.trace("Converting path: " + path + " to link");
		String link = path;
		if (!link.startsWith("http") && !link.startsWith("mailto")
				&& !link.startsWith("#") && !link.contains(".")) {
			link += ".html";
		}
		return StringEscapeUtils.escapeHtml(link);
	}
}
