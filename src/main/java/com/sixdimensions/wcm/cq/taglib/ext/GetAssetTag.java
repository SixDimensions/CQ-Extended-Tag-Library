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
package com.sixdimensions.wcm.cq.taglib.ext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.commons.util.DamUtil;

/**
 * Retrieves an asset from the resource. Checks whether the given resource is an
 * asset, and if not, travels upwards the resource hierarchy until a resource is
 * an asset.
 * 
 * @author dklco
 */
public class GetAssetTag extends TagSupport {
	private static final long serialVersionUID = -4882565776256570621L;
	private static final Logger log = LoggerFactory
			.getLogger(GetAssetTag.class);

	/**
	 * The resource to use to retrieve the asset.
	 */
	private Resource resource;

	/**
	 * The variable name in which to save the value
	 */
	private String var;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		log.trace("doEndTag");

		if (this.resource != null) {
			log.debug("Retrieving asset from resource: "
					+ this.resource.getPath());
			final Asset asset = DamUtil.resolveToAsset(this.resource);
			if (asset != null) {
				log.debug("Retrieved asset: " + asset.getPath());
			} else {
				log.warn("No asset retrieved from resource: "
						+ this.resource.getPath());
			}

			this.pageContext.setAttribute(this.getVar(), asset);
		} else {
			log.warn("Unable to retrieve asset, resource is null");
		}

		return javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
	}

	/**
	 * Get the resource from which the asset is to be retrieve.
	 * 
	 * @return the resource
	 */
	public Resource getResource() {
		return this.resource;
	}

	/**
	 * @return the var name
	 */
	public String getVar() {
		return this.var;
	}

	/**
	 * Set the resource from which to retrieve the asset.
	 * 
	 * @param resource
	 *            the resource from which to retrieve the asset
	 */
	public void setResource(final Resource resource) {
		this.resource = resource;
	}

	/**
	 * @param var
	 *            the var name in which to set the asset
	 */
	public void setVar(final String var) {
		this.var = var;
	}

}
