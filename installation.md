---
layout: default
title: Installation
---

## Installing CQ Extended Tag Library
 
The latest version of each bundle can be downloaded from the 
[Project Releases](https://github.com/SixDimensions/CQ-Extended-Tag-Library/releases) 
page and then installed through the [OSGi Console](https://sling.apache.org/documentation/tutorials-how-tos/installing-and-upgrading-bundles.html) 
or via a [CRX Package](http://helpx.adobe.com/experience-manager/kb/SlingHowToInstallBundlesViaJCRInstall.html).

### Maven

Additionally, the Component Bindings Provider API is available through the Maven Central 
Repository.  To add the Component Bindings Provider API into your project, add the 
following dependency into your POM:

	<dependency>
		<groupId>com.sixdimensions.wcm.cq</groupId>
        	<artifactId>com.sixdimensions.wcm.cq.taglib.ext</artifactId>
        	<version>0.1.0</version>
    	</dependency>
