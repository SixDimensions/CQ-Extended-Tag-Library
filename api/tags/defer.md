---
title: defer
layout: default
summary: Defers the writing of HTML until a WriteDeferred tag is evaluated.
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Tags]({{ site.baseurl }}/api/tags.html) | {{ page.title }}

## Defer Tag

Defers the writing of HTML until a WriteDeferred tag is evaluated.  This is useful especially
for JavaScript libraries which may be in components on the page, but should be evaluated
at the end of the page for performance reasons.

* **name** - defer
* **class** - com.sixdimensions.wcm.cq.taglib.ext.DeferTag
* **attributes**:
  * **id** - The id of the content block, ensures content is only written once

Example:

	<ext:defer id="a-script-block">
	  <script>
	    alert("Hello World");
	  </script>
	</ext:defer>
