package com.sixdimensions.wcm.cq.taglib.ext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.scripting.core.ScriptHelper;
import org.apache.sling.scripting.jsp.taglib.IncludeTagHandler;
import org.apache.sling.scripting.jsp.util.TagUtil;
import org.apache.sling.settings.SlingSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.Externalizer;
import com.day.cq.commons.jcr.JcrConstants;
import com.sixdimensions.wcm.cq.taglib.ext.internal.SSIConfig;

/**
 * Include tag which will in specific environments render a SSI include rather
 * than doing a standard include.
 */
public class SSITag extends IncludeTagHandler {

	/**
	 * The Serialization UID
	 */
	private static final long serialVersionUID = -7042750132960514977L;

	/**
	 * The SLF4J logger
	 */
	private static final Logger log = LoggerFactory.getLogger(SSITag.class);

	/** additional selectors argument */
	private String addSelectors;

	/**
	 * Whether or not the SSI request should be cached.
	 */
	private boolean cache;

	/**
	 * The SSI Include Config service, injected when the TagLib starts
	 */
	private SSIConfig config = null;

	/** path argument */
	private String path;

	/** replace selectors argument */
	private String replaceSelectors;

	/** replace suffix argument */
	private String replaceSuffix;

	/** resource argument */
	private Resource resource;

	/** The resourceType to be forced **/
	private String resourceType;

	private boolean isPublish;

	/**
	 * Builds the SSI Path. The path will include all of the selectors and
	 * suffix according to the page request and the configuration in the include
	 * tag. Additionally the selector 'ssi' will always be added and a selector
	 * in the format 'rt-{URL_ENCODED_RESOURCE_TYPE}' will be added if a
	 * resource type was specified if a resource type is specified in the tag.
	 * 
	 * @return
	 */
	private String buildSSIPath() {
		log.trace("buildSSIPath");
		String ssiPath = (this.resource != null) ? this.resource.getPath()
				: this.path;

		if (ssiPath.charAt(0) != '/') {
			final Resource resource = (Resource) this.pageContext
					.getAttribute(SlingBindings.RESOURCE);
			ssiPath = resource.getPath() + "/" + ssiPath;
		}
		ssiPath = ssiPath.replace(JcrConstants.JCR_CONTENT, "_jcr_content");

		final StringBuilder ssiPathBuilder = new StringBuilder(ssiPath);

		log.debug("Building SSI Path for {}", ssiPathBuilder);
		final SlingHttpServletRequest slingRequest = TagUtil
				.getRequest(this.pageContext);

		ssiPathBuilder.append("." + SSIConfig.SSI_SELECTOR);

		if (this.cache) {
			ssiPathBuilder.append("." + SSIConfig.CACHE_SELECTOR);
		}

		if (this.replaceSelectors != null) {
			ssiPathBuilder.append("." + this.replaceSelectors);
		} else {
			if (this.addSelectors != null) {
				ssiPathBuilder.append("." + this.addSelectors);
			}
			if (slingRequest.getRequestPathInfo().getSelectorString() != null) {
				ssiPathBuilder.append(slingRequest.getRequestPathInfo()
						.getSelectorString());
			}
		}

		if (this.resourceType != null) {
			try {
				ssiPathBuilder.append("."
						+ SSIConfig.RESOURCE_TYPE_PREFIX
						+ Base64.encodeBase64URLSafeString(this.resourceType
								.getBytes("UTF-8")));
			} catch (final UnsupportedEncodingException e) {
				log.error("Encoding UTF-8 not supported", e);
			}
		}

		ssiPathBuilder.append(".html");
		if (this.replaceSuffix != null) {
			ssiPathBuilder.append(this.replaceSuffix);
		} else if (slingRequest.getRequestPathInfo().getSuffix() != null) {
			ssiPathBuilder
					.append(slingRequest.getRequestPathInfo().getSuffix());
		}
		log.trace("Path before replacement:" + ssiPathBuilder);

		ssiPath = String.format(this.config.getFormat(),
				ssiPathBuilder.toString());
		log.debug("Generated SSI Path {}", ssiPath);

		return ssiPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.sling.scripting.jsp.taglib.IncludeTagHandler#dispatch(javax
	 * .servlet.RequestDispatcher, javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse)
	 */
	@Override
	protected void dispatch(final RequestDispatcher dispatcher,
			final ServletRequest request, final ServletResponse response)
			throws IOException, ServletException {
		log.trace("dispatch");

		final ScriptHelper sling = (ScriptHelper) this.pageContext
				.getAttribute(SlingBindings.SLING);

		// Set the publish flag if it's not already set
		final SlingSettingsService settingsService = sling
				.getService(SlingSettingsService.class);
		this.isPublish = settingsService.getRunModes().contains(
				Externalizer.PUBLISH);

		// Get the configuration
		this.config = sling.getService(SSIConfig.class);
		log.debug("Loaded config: {}", this.config);

		if (this.isPublish
				&& this.config.isEnabled()
				&& !"false".equals(this.pageContext.getRequest().getParameter(
						SSIConfig.SSI_SELECTOR))) {
			log.trace("Currently on publish server");
			final String ssiPath = this.buildSSIPath();
			response.getWriter().write(
					"<!--#include virtual=\"" + ssiPath + "\" -->");
		} else {
			log.trace("On author server");
			super.dispatch(dispatcher, request, response);
		}
	}

	@Override
	public void setAddSelectors(final String selectors) {
		this.addSelectors = selectors;
		super.setAddSelectors(selectors);
	}

	public void setCache(final boolean cache) {
		this.cache = cache;
	}

	@Override
	public void setPath(final String path) {
		this.path = path;
		super.setPath(path);
	}

	@Override
	public void setReplaceSelectors(final String selectors) {
		this.replaceSelectors = selectors;
		super.setReplaceSelectors(selectors);
	}

	@Override
	public void setReplaceSuffix(final String suffix) {
		this.replaceSuffix = suffix;
		super.setReplaceSuffix(suffix);
	}

	@Override
	public void setResource(final Resource rsrc) {
		this.resource = rsrc;
		super.setResource(rsrc);
	}

	@Override
	public void setResourceType(final String rsrcType) {
		this.resourceType = rsrcType;
		super.setResourceType(rsrcType);
	}
}