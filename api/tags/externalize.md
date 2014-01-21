---
title: externalize
layout: default
summary: Externalizes paths into absolute, relative and author URLs
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Tags]({{ site.baseurl }}/api/tags.html) / {{ page.title }}

## externalize Tag

Uses the [CQ Externalizer](http://dev.day.com/docs/en/cq/current/javadoc/com/day/cq/commons/Externalizer.html)
to externalize paths into absolute, relative and author URLs.  This is useful when paths 
will not be automatically externalized by the CQ URL rewriter such as links inside 
JavaScript blocks, in RSS Feeds or in data-* attributes.

* **name** - externalize
* **class** - com.sixdimensions.wcm.cq.taglib.ext.ExternalizeTag
* **attributes**:

  * **mode** - The externalization mode, should be one of 'author', 'absolute' or 
      'relative', relative is the default
  * **path** -  *required* - The path to externalize
  * **var** -  The variable in which to save the externalized path, if not specified the 
      externalized path will be written to the page
  
Example:

	<ext:externalize path="/content/geometrixx/en.html" />
