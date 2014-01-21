---
title: ssi
layout: default
summary: Renders Server Side Includes
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Tags]({{ site.baseurl }}/api/tags.html) / {{ page.title }}

## ssi Tag

Includes a resource rendering into the current page if in an author instance and uses a SSI 
include on publish servers.  Note, this tag will always include the resource when on an 
author server, even if the WCMMode is set to DISABLED or PREVIEW.

The advantage here is you can include a portion of the page which can be generated 
dynamically while allowing the overall page to be cached.

This class also expects a Filter the SSIFilter to intercept requests and if the requests are 
for SSIs of virtual resources, create an Abstract Resource to render the request based
on the parameters passed in the URL.  This filter is included in the CQ Extended Tag Library.

* **name** - ssi
* **class** - com.sixdimensions.wcm.cq.taglib.ext.SSITag
* **attributes**:

  * **addSelectors** - When dispatching, add the value provided by this option to the 
      selectors.
  * **cache** - Whether or not to add the cache selector to the SSI URL.  If the cache 
  	  selector is not added, the included page will send headers to the dispatcher to not
  	  cache the page.
  * **flush** -  Whether to flush the output before including the target
  * **resource** -  The resource object to include in the current request
      processing. Either resource or path must be specified. If
      both are specified, the resource takes precedences.
  * **path** - The path to the resource object to include in the current request 
      processing. If this path is relative it is appended to the path of the current 
      resource whose script is including the given resource. Either resource or path must 
      be specified. If both are specified, the resource takes precedences.
  * **path** - The path to the resource object to include in the current request 
      processing. If this path is relative it is appended to the path of the current 
      resource whose script is including the given resource. Either resource or path must 
      be specified. If both are specified, the resource takes precedences.
  * **resourceType** - The resource type of a resource to include. If the resource to be 
      included is specified with the path attribute, which cannot be resolved to a 
      resource, the tag may create a synthetic resource object out of the path and this 
      resource type. If the resource type is set the path must be the exact path to a 
      resource object. That is, adding parameters, selectors and extensions to the path is
      not supported if the resource type is set.
  * **replaceSelectors** - When dispatching, replace selectors by the value provided by 
      this option.
  * **replaceSuffix** - When dispatching, replace the suffix by the value provided by this 
      option.
  
  
Example:

	<ext:ssi path="/content/geometrixx/en/par/title" resourceType="app/components/title" />
