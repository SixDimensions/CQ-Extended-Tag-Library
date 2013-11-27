/*
 * Copyright 2013 - Six Dimensions
 * All Rights Reserved
 */
package com.sixdimensions.wcm.cq.taglib.ext;

import java.util.Iterator;
import java.util.zip.CRC32;

import org.apache.commons.lang.StringUtils;

import com.day.cq.commons.inherit.InheritanceValueMap;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.PageManager;
import com.day.text.Text;

/**
 * The Expression Languages for the Ext Taglib.
 * 
 * @author dklco
 */
public class Functions {

	/**
	 * Escapes the provided string into a valid CSS selector. The string will be
	 * converted to lowercase, and all non-ASCII characters replaced with '-'
	 * 
	 * @param string
	 *            the string to escape
	 * @return the escaped string
	 */
	public static final String escapeSelector(final String string) {
		return Text.escape(string.toLowerCase(), '-');
	}

	/**
	 * Gets an inherited value from an inheritance value map.
	 * 
	 * @param properties
	 *            the inheritance value map from which to get the property
	 * @param key
	 *            the key of the property to retrieve
	 * @param defaultOrType
	 *            either the default value or a class in which to coerse the
	 *            value
	 * @return the value
	 */
	public static final Object getInherited(
			final InheritanceValueMap properties, final String key,
			final Object defaultOrType) {
		Object toReturn = null;
		if (defaultOrType instanceof Class<?>) {
			if (properties != null) {
				toReturn = properties.getInherited(key,
						(Class<?>) defaultOrType);
			}
		} else {
			if (properties != null) {
				toReturn = properties.getInherited(key, defaultOrType);
			} else {
				toReturn = defaultOrType;
			}
		}
		return toReturn;
	}

	/**
	 * Gets the page at the path or the page containing the resource at the
	 * path.
	 * 
	 * @param pm
	 *            the page manager for with to fetch the page
	 * @param path
	 *            the path of the page or a child resource of the page
	 * @return the page at the path
	 */
	public static final Page getPage(final PageManager pm, final String path) {
		return pm.getContainingPage(path);
	}

	/**
	 * Get the title for the specified page.
	 * 
	 * @param page
	 *            the page from which to get the title
	 * @param type
	 *            the type of title to get, one of 'nav' or 'page', page is
	 *            default
	 * @return
	 */
	public static final String getTitle(final Page page, final String type) {
		String title = null;
		if ("nav".equals(type)) {
			title = page.getNavigationTitle();
		} else {
			title = page.getPageTitle();
		}
		if (StringUtils.isEmpty(title)) {
			title = page.getTitle();
			if (StringUtils.isEmpty(title)) {
				title = page.getName();
			}
		}
		return title;
	}

	/**
	 * Generates a Unique ID for the component at the specified path.
	 * 
	 * @param path
	 *            the path for which to generate the id
	 * @return the unique ID
	 */
	public static final String getUniqueId(final String path) {
		final String name = path.substring(path.lastIndexOf("/") + 1);
		final CRC32 hash = new CRC32();
		hash.update(path.getBytes());
		return name + "_" + hash.getValue();
	}

	/**
	 * Lists the child pages of a particular page.
	 * 
	 * @param page
	 *            the page for which to list the child pages
	 * @param filter
	 *            whether or not to filter the child pages
	 * @return the child pages
	 */
	public static final Iterator<Page> listChildPages(final Page page,
			final boolean filter) {
		return filter ? page.listChildren(new PageFilter()) : page
				.listChildren();
	}
}
