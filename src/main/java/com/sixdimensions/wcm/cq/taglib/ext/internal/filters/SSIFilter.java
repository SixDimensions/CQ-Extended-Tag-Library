/*
 * Copyright 2013 - Six Dimensions
 * All Rights Reserved
 */
package com.sixdimensions.wcm.cq.taglib.ext.internal.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.sling.SlingFilter;
import org.apache.felix.scr.annotations.sling.SlingFilterScope;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestDispatcherOptions;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.SyntheticResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sixdimensions.wcm.cq.taglib.ext.internal.SSIConfig;

/**
 * A filter for handing SSI includes. Handles synthetic resources and the
 * caching headers.
 * 
 * @author dklco
 */
@SlingFilter(scope = SlingFilterScope.REQUEST, order = Integer.MIN_VALUE)
public class SSIFilter implements Filter {

	/**
	 * The SLF4J Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(SSIFilter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// nothing to do
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		boolean continueChain = true;
		if (request instanceof SlingHttpServletRequest) {
			final SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;
			final String[] requestSelectors = ((SlingHttpServletRequest) request)
					.getRequestPathInfo().getSelectors();

			// SSI should probably be the first selector
			if (ArrayUtils.contains(requestSelectors,
					SSIConfig.SSI_SELECTOR)) {
				log.trace("Request {} is an SSI request",
						slingRequest.getRequestURL());

				// disable dispatcher caching
				if (!ArrayUtils.contains(requestSelectors,
						SSIConfig.CACHE_SELECTOR)) {
					((HttpServletResponse) response).setHeader("Dispatcher",
							"no-cache");
				}

				// handle non-existing resources
				if (slingRequest.getResource().isResourceType(
						Resource.RESOURCE_TYPE_NON_EXISTING)) {
					log.debug(
							"Resource {} does not exist, using information from selectors",
							slingRequest.getResource());

					String resourceType = null;
					for (final String selector : requestSelectors) {
						if (selector
								.startsWith(SSIConfig.RESOURCE_TYPE_PREFIX)) {
							resourceType = new String(
									Base64.decodeBase64(selector
											.substring(SSIConfig.RESOURCE_TYPE_PREFIX
													.length())), "UTF-8");
						}
					}
					log.debug("Loaded resource type {}", resourceType);

					if (!StringUtils.isEmpty(resourceType)) {
						log.debug("Loading synthetic resource");
						final String path = slingRequest
								.getResource()
								.getPath()
								.substring(
										0,
										slingRequest.getResource().getPath()
												.indexOf("."));
						final SyntheticResource syntheticResource = new SyntheticResource(
								slingRequest.getResourceResolver(), path,
								resourceType);
						slingRequest.getRequestDispatcher(syntheticResource,
								new RequestDispatcherOptions()).forward(
								slingRequest, response);
						continueChain = false;
					} else {
						log.warn(
								"Non-existant resource {} requested through SSI without resource type",
								slingRequest.getResource());
					}
				}
			}
		}

		// Only continue on the chain if not forwarding to a synthetic resource
		if (continueChain) {
			chain.doFilter(request, response);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		// nothing to do
	}
}