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

import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.apache.sling.testing.tools.http.RequestExecutor;
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
		if (!this.slingClient
				.exists("/apps/test/install/org.apache.sling.scripting.jsp.taglib-2.2.0.jar")) {
			Utils.createFolders(this.slingClient, "/apps/test/install");
			this.slingClient
					.upload("/apps/test/install/org.apache.sling.scripting.jsp.taglib-2.2.0.jar",
							ExtTagLibraryIT.class
									.getClassLoader()
									.getResourceAsStream(
											"org.apache.sling.scripting.jsp.taglib-2.2.0.jar"),
							-1, true);
			try {
				Thread.sleep(120000);
			} catch (final InterruptedException ie) {
			}
		}

		log.info("Initialization successful");
	}

	public RequestExecutor initTest(final String name) throws IOException {
		log.info("Creating testing component...");
		Utils.createFolders(this.slingClient, "/apps/test/ext/" + name);
		this.slingClient.upload(
				"/apps/test/ext/" + name + "/" + name + ".jsp",
				ExtTagLibraryIT.class.getClassLoader().getResourceAsStream(
						name + ".jsp"), -1, true);
		log.info(this
				.getRequestExecutor()
				.execute(
						this.getRequestBuilder()
								.buildGetRequest(
										"/apps/test/ext/" + name + ".3.json")
								.withCredentials("admin", "admin"))
				.assertStatus(200).getContent());

		log.info("Creating testing content...");
		this.slingClient.createNode("/content/test/ext/" + name,
				"jcr:primaryType", "nt:unstructured", "sling:resourceType",
				"test/ext/" + name);
		log.info(this
				.getRequestExecutor()
				.execute(
						this.getRequestBuilder()
								.buildGetRequest(
										"/content/test/ext/" + name + ".json")
								.withCredentials("admin", "admin"))
				.assertStatus(200).getContent());

		final RequestExecutor exec = this.getRequestExecutor().execute(
				this.getRequestBuilder()
						.buildGetRequest("/content/test/ext/" + name + ".html")
						.withCredentials("admin", "admin"));
		log.info("Content: " + exec.getContent());
		exec.assertStatus(200);

		return exec;
	}

	/**
	 * Test the asset tags
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAssetTags() throws Exception {

		final RequestExecutor exec = this.initTest("asset");

		log.info("Asserting asset works");
		exec.assertContentContains("ASSET_PATH/content/dam/geometrixx/shapes/cir_circle.pngASSET_PATH");
		exec.assertContentContains("ASSET_METADATAimage/pngASSET_METADATA");

		log.info("All asset tests successful");
	}

	/**
	 * Test the two defer tags
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDefer() throws Exception {

		log.info("Asserting defer works");
		final RequestExecutor exec = this.initTest("defer");

		exec.assertContentContains("twoone");

		log.info("All Defer tests successful");
	}

	/**
	 * Test the two defer tags
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDisplayMode() throws Exception {

		log.info("Asserting displayMode works");
		final RequestExecutor exec = this.initTest("displayMode");
		exec.assertContentContains("Edit");
		assertFalse(exec.getContent().contains("Publish"));

		log.info("All displayMode tests successful");
	}

	/**
	 * Test the externalize tag
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExternalize() throws Exception {

		log.info("Asserting externalize works");
		final RequestExecutor exec = this.initTest("externalize");

		exec.assertContentContains("AUTHORhttp://localhost:4502/content/extAUTHOR");
		exec.assertContentContains("RELATIVE/content/geometrixxRELATIVE");
		exec.assertContentContains("ABSOLUTEhttp://localhost:8765/content/geometrixxABSOLUTE");

		log.info("All externalize tests successful");
	}

	/**
	 * Test the functions
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFunctions() throws Exception {
		log.info("Asserting functions work");
		final RequestExecutor exec = this.initTest("functions");

		exec.assertContentContains("/content/geometrixx/en");
		exec.assertContentContains("English");
		exec.assertContentContains("GeoMetrixx");
		exec.assertContentContains("path_1766808107");
		exec.assertContentContains("Toolbar");
		exec.assertContentContains("awesome-coolness");
		log.info("Content created successfully");
	}

	/**
	 * Test the xss escape tag
	 * 
	 * @throws Exception
	 */
	@Test
	public void testXSSEscape() throws Exception {
		log.info("Asserting xss works");
		final RequestExecutor exec = this.initTest("xss");
		exec.assertContentContains("&lt;script&gt;I am a bad person&lt;&#x2f;script&gt;");
		exec.assertContentContains("&quot;I&#x20;am&#x20;worse");
		exec.assertContentContains("\\x22I\\x20am\\x20also\\x20bad\\x22");
		exec.assertContentContains("&#x3c;xml&#x3e;is stupid&#x3c;&#x2f;xml&#x3e;");
		exec.assertContentContains("&#x22;XML SUX&#x22;");
		log.info("Content: " + exec.getContent());

		log.info("All asset tests successful");
	}
}