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

import static org.junit.Assert.assertTrue;

import org.apache.sling.testing.tools.sling.SlingClient;
import org.apache.sling.testing.tools.sling.SlingTestBase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests for the Ext Tag Library
 * 
 * @author dklco
 */
public class ExtTagLibraryIT extends SlingTestBase {

	/**
	 * The SlingClient can be used to interact with the repository when it is
	 * started. By retrieving the information for the Server URL, username and
	 * password, the Sling instance will be automatically started.
	 */
	private final SlingClient slingClient = new SlingClient(
			this.getServerBaseUrl(), this.getServerUsername(),
			this.getServerPassword()) {

	};

	/**
	 * The SLF4J Logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(ExtTagLibraryIT.class);

	/**
	 * Execute before the actual test, this will be used to setup the test data
	 * 
	 * @throws Exception
	 */
	@Before
	public void init() throws Exception {
		log.info("init");

		if (this.slingClient.exists("/apps/test/ext")) {
			this.slingClient.delete("/apps/test/ext");
		}

		log.info("Initialization successful");
	}

	/**
	 * Test the two defer tags
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDefer() throws Exception {

		log.info("Creating testing component...");
		Utils.createFolders(this.slingClient, "/apps/test/ext/defer");
		this.slingClient.upload(
				"/apps/test/ext/defer/defer.jsp",
				ExtTagLibraryIT.class.getClassLoader().getResourceAsStream(
						"defer.jsp"), -1, true);
		log.info(this
				.getRequestExecutor()
				.execute(
						this.getRequestBuilder()
								.buildGetRequest("/apps/test/ext/defer.3.json")
								.withCredentials("admin", "admin"))
				.assertStatus(200).getContent());

		log.info("Creating testing content...");
		Utils.createFolders(this.slingClient, "/content/test/ext");
		this.slingClient.createNode("/content/test/ext/defer",
				"jcr:primaryType", "nt:unstructured", "sling:resourceType",
				"test/ext/defer");
		log.info(this
				.getRequestExecutor()
				.execute(
						this.getRequestBuilder()
								.buildGetRequest("/content/test/ext/defer.json")
								.withCredentials("admin", "admin"))
				.assertStatus(200).getContent());

		log.info("Asserting defer");
		this.getRequestExecutor()
				.execute(
						this.getRequestBuilder()
								.buildGetRequest("/content/test/ext/defer.html")
								.withCredentials("admin", "admin"))
				.assertStatus(200).assertContentContains("twoone");

		log.info("All Defer tests successful");
	}

	/**
	 * Test the two defer tags
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDisplayMode() throws Exception {

		log.info("Creating testing component...");
		Utils.createFolders(this.slingClient, "/apps/test/ext/displayMode");
		this.slingClient.upload(
				"/apps/test/ext/displayMode/displayMode.jsp",
				ExtTagLibraryIT.class.getClassLoader().getResourceAsStream(
						"displayMode.jsp"), -1, true);
		log.info(this
				.getRequestExecutor()
				.execute(
						this.getRequestBuilder()
								.buildGetRequest(
										"/apps/test/ext/displayMode.3.json")
								.withCredentials("admin", "admin"))
				.assertStatus(200).getContent());

		log.info("Creating testing content...");
		Utils.createFolders(this.slingClient, "/content/test/ext");
		this.slingClient.createNode("/content/test/ext/displayMode",
				"jcr:primaryType", "nt:unstructured", "sling:resourceType",
				"test/ext/displayMode");
		log.info(this
				.getRequestExecutor()
				.execute(
						this.getRequestBuilder()
								.buildGetRequest(
										"/content/test/ext/displayMode.json")
								.withCredentials("admin", "admin"))
				.assertStatus(200).getContent());

		log.info("Asserting displayMode works");
		assertTrue(this
				.getRequestExecutor()
				.execute(
						this.getRequestBuilder()
								.buildGetRequest(
										"/content/test/ext/displayMode.html")
								.withCredentials("admin", "admin"))
				.assertStatus(200).getContent().trim().length() == 0);

		log.info("All displayMode tests successful");
	}
}