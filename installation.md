---
layout: default
title: Installation
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / {{ page.title }}

## Installing CQ Extended Tag Library
 
The latest version of each bundle can be downloaded from the 
[Project Releases](https://github.com/SixDimensions/CQ-Extended-Tag-Library/releases) 
page and then installed through the [OSGi Console](https://sling.apache.org/documentation/tutorials-how-tos/installing-and-upgrading-bundles.html) 
or via a [CRX Package](http://helpx.adobe.com/experience-manager/kb/SlingHowToInstallBundlesViaJCRInstall.html).

### Supported AEM Versions

The CQ Extended Tag Library is supported in AEM (Adobe CQ) versions 5.5 - 5.6.1. It 
has not been validated against newer versions and due to it’s inclusion of the Granite 
XSS API, it is not compatible with CQ 5.4 or older. If you need a version compatible with 
older versions of CQ, please create an issue and I will create a specific release.

### Maven

Additionally, the CQ Extended Tab Library is available through the Maven Central 
Repository.  To add the CQ Extended Tab Library into your project, add the 
following dependency into your POM:

	<dependency>
		<groupId>com.sixdimensions.wcm.cq</groupId>
        	<artifactId>com.sixdimensions.wcm.cq.taglib.ext</artifactId>
        	<version>0.1.0</version>
    	</dependency>

This way, you can easily 
[embed the bundle into a content package](http://dev.day.com/docs/en/cq/current/core/how_to/how_to_use_the_vlttool/vlt-mavenplugin.html#Embedding%20OSGi%20Bundles%20in%20a%20Content%20Package).
