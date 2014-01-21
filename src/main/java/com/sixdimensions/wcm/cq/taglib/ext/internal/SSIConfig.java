/*
 * Copyright 2013 - Six Dimensions
 * All Rights Reserved
 */
package com.sixdimensions.wcm.cq.taglib.ext.internal;

import java.util.Dictionary;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.sling.commons.osgi.OsgiUtil;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The SSI Include configuration
 * 
 * @author dklco
 */
@Component(label = "SSIIncludeConfig", metatype = true, immediate = true)
public class SSIConfig {

	/**
	 * The constant for the selector added into all SSI requests which should be
	 * cached
	 */
	public static final String CACHE_SELECTOR = "cache";

	/**
	 * The prefix for the resource type selector
	 */
	public static final String RESOURCE_TYPE_PREFIX = "rt-";

	/**
	 * The constant for the selector added into all SSI requests
	 */
	public static final String SSI_SELECTOR = "ssi";

	/**
	 * The SLF4J Logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(SSIConfig.class);

	/**
	 * The constant for the enabled configuration key
	 */
	@Property(label = "Enabled", boolValue = true)
	private static final String ENABLED = "enabled";

	/**
	 * The constant for the format configuration key
	 */
	@Property(label = "Format", value = "%s")
	private static final String FORMAT = "format";

	/**
	 * The enabled flag
	 */
	private boolean enabled;

	/**
	 * The format to format the SSI include URL
	 */
	private String format;

	/**
	 * Called by the OSGi Container when the component is activated. Gets the
	 * properties and registers the scheduled job.
	 * 
	 * @param context
	 *            the component context
	 * @throws Exception
	 */
	@Activate
	protected void activate(final ComponentContext context) throws Exception {
		log.info("activate");
		final Dictionary<?, ?> properties = context.getProperties();
		this.enabled = OsgiUtil.toBoolean(properties.get(ENABLED), true);
		this.format = OsgiUtil.toString(properties.get(FORMAT), "%s");
	}

	/**
	 * Gets the format string
	 * 
	 * @return the format string
	 */
	public String getFormat() {
		return this.format;
	}

	/**
	 * Gets the enabled setting.
	 * 
	 * @return
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
}