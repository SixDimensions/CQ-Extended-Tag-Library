/*
 * Copyright 2012 - Six Dimensions
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.sixdimensions.wcm.cq.cqex.tags;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tldgen.annotations.Attribute;
import org.tldgen.annotations.BodyContent;
import org.tldgen.annotations.Tag;

/**
 * Retrieves the resource at the specified path and makes it available as a
 * Sling Resource.
 * 
 * @author dklco
 */
@Tag(bodyContent = BodyContent.EMPTY, example = "&lt;cqex:getResource var=\"resource\" path=\"/content/mysite\" />")
public class GetResourceTag extends AttributeSettingTag {
	private static final Logger log = LoggerFactory
			.getLogger(GetResourceTag.class);
	private static final long serialVersionUID = 5861756752614447760L;

	/**
	 * The absolute path of the resource to retrieve
	 */
	@Attribute(required = true)
	private String path;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() {
		log.trace("doEndTag");

		log.debug("Retrieving resource at path");
		final SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) this.pageContext
				.getRequest();
		final Resource resource = slingRequest.getResourceResolver()
				.getResource(this.path);
		log.debug("Retrieved resource: "
				+ (resource != null ? resource.getPath() : "null"));
		this.setAttribute(this.getVar(), resource);
		return javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
	}

	/**
	 * Get the path of the resource to retrieve.
	 * 
	 * @return the path of the resource to retrieve
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Set the absolute path of the resource to retrieve.
	 * 
	 * @param path
	 *            the path of the resource to retrieve, must be absolute
	 */
	public void setPath(final String path) {
		this.path = path;
	}

}
