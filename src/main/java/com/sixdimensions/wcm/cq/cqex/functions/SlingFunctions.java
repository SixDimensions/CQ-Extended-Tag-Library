/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.functions;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tldgen.annotations.Function;

public class SlingFunctions {

	private static final Logger log = LoggerFactory
			.getLogger(SlingFunctions.class);

	/**
	 * Checks to see if there is a child resource at the specified path.
	 * 
	 * @param resource
	 *            the resource to check the children thereof
	 * @param path
	 *            the path of the child to check
	 * @return true if there is a child at the specified path, false otherwise
	 */
	@Function(example = "${cqex:hasChild(resource,path)}")
	public static boolean hasChild(Resource resource, String path) {
		log.trace("hasChild");
		return resource.getChild(path) != null;
	}
}
