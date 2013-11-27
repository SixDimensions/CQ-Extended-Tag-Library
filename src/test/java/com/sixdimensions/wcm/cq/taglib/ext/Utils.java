/*
 * Copyright 2013 - Six Dimensions
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
 */
package com.sixdimensions.wcm.cq.taglib.ext;

import java.io.IOException;

import org.apache.sling.testing.tools.sling.SlingClient;

/**
 * Utilities for testing.
 * 
 * @author dklco
 * 
 */
public class Utils {

	/**
	 * Creates the parent folders of the specified path.
	 * 
	 * @param slingClient
	 *            the sling client to use
	 * @param path
	 *            the path to create
	 * @throws IOException
	 */
	public static final void createFolders(SlingClient slingClient, String path)
			throws IOException {
		String currentPath = "";
		for (String part : path.split("/")) {
			if (part == null || part.trim().length() == 0) {
				continue;
			}
			currentPath += "/" + part;
			if (!slingClient.exists(currentPath)) {
				slingClient.mkdir(currentPath);
			}
		}
	}
}
