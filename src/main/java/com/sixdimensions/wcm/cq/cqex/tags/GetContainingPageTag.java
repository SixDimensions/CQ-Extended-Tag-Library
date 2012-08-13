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

import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tldgen.annotations.Attribute;
import org.tldgen.annotations.BodyContent;
import org.tldgen.annotations.Tag;

import com.day.cq.wcm.api.PageManager;

/**
 * Tag that retrieves the page at or containing the specified path.
 * 
 * @author dklco
 */
@Tag(bodyContent = BodyContent.EMPTY, example = "&lt;cqex:getContainingPage var=\"page\" path=\"/content/geometrixxx/en/jcr:content\" />")
public class GetContainingPageTag extends AttributeSettingTag {
	private static final long serialVersionUID = 2906794811653608479L;
	private static final Logger log = LoggerFactory
			.getLogger(GetContainingPageTag.class);

	/**
	 * The absolute path of the page to retrieve or to a resource under the
	 * page.
	 */
	@Attribute
	private String path;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		log.trace("doEndTag");

		final PageManager pageMgr = (PageManager) this.pageContext
				.getAttribute("pageManager");

		if (pageMgr != null) {
			this.setAttribute(this.var, pageMgr.getContainingPage(this.path));
		} else {
			log.error("Page manager not found");
		}

		return javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
	}

	/**
	 * The absolute path of the page to retrieve or to a resource under the
	 * page.
	 * 
	 * @return the path of the page to retrieve
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * The absolute path of the page to retrieve or to a resource under the
	 * page.
	 * 
	 * @param path
	 *            the path of the page to retrieve
	 */
	public void setPath(final String path) {
		this.path = path;
	}
}
