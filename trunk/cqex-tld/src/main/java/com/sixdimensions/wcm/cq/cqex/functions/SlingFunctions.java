/*
 * Copyright 2012 - Six Dimensions
 */
package com.sixdimensions.wcm.cq.cqex.functions;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tldgen.annotations.Function;

/**
 * Class for Sling EL functions.
 * 
 * @author dklco
 */
public class SlingFunctions {

	private static final Logger log = LoggerFactory
			.getLogger(SlingFunctions.class);

	/**
	 * Retrieves a property from the ValueMap allowing for default values.
	 * 
	 * @param properties
	 *            the ValueMap from which to retrieve the value
	 * @param key
	 *            the key for the value to retrieve
	 * @param defaultValue
	 *            the default value, if no value is retrieved, this value is
	 *            returned
	 * @return
	 */
	@Function(example = "${cqex:getProperty(properties, key, defaultValue)}")
	public static Object getProperty(ValueMap properties, String key,
			Object defaultValue) {
		log.trace("getProperty");
		Object value = null;
		if (defaultValue != null) {
			value = properties.get(key, defaultValue);
		} else {
			value = properties.get(key);
		}
		return value;
	}

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
