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

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;

/**
 * Retrieves a metadata attribute from the specified asset.
 * 
 * @author dklco
 */
public class GetAssetMetadataTag extends TagSupport {
	private static final Logger log = LoggerFactory
			.getLogger(GetAssetMetadataTag.class);
	private static final long serialVersionUID = -4882565776256570621L;

	/**
	 * The asset from which to retrieve the metadata.
	 */
	private Asset asset;

	/**
	 * The property to retrieve from the asset metadata.
	 */
	private String key;

	/**
	 * The variable name in which to save the asset metadata value
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

		if (this.asset != null) {
			log.debug("Retrieving metadata from asset: " + this.asset.getPath());
			final String value = this.asset.getMetadataValue(this.key);

			if (!StringUtils.isEmpty(this.getVar())) {
				this.pageContext.setAttribute(this.getVar(), value);
			} else {
				try {
					this.pageContext.getOut().print(value);
				} catch (final IOException e) {
					log.error("Exception writing asset metadata to output stream");
				}
			}
		} else {
			log.warn("Unable to retrieve Metadata Asset is null");
		}

		return javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
	}

	/**
	 * Get the asset from which the metadata is to be retrieved.
	 * 
	 * @return the asset
	 */
	public Asset getAsset() {
		return this.asset;
	}

	/**
	 * Get the key of the metadata value to retrieve.
	 * 
	 * @return the key of the metadata value
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * @return the var
	 */
	public String getVar() {
		return this.var;
	}

	/**
	 * Set the asset from which to retrieve the metadata.
	 * 
	 * @param asset
	 *            the asset from which to retrieve the metadata
	 */
	public void setAsset(final Asset asset) {
		this.asset = asset;
	}

	/**
	 * Set the key for the metadata value to retrieve.
	 * 
	 * @param key
	 *            the key of the metadata value to retrieve
	 */
	public void setKey(final String key) {
		this.key = key;
	}

	/**
	 * @param var
	 *            the var to set
	 */
	public void setVar(final String var) {
		this.var = var;
	}

}
