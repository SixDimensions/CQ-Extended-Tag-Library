---
title: xssEncode
layout: default
summary: Tag for XSS safe encoding arbitrary text for HTML, XML and JS
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Tags]({{ site.baseurl }}/api/tags.html) / {{ page.title }}

## xssEncode Tag

Tag for XSS safe encoding arbitrary text for HTML, XML and JS.  Uses's Adobe Granite's XSS
API for the encoding.

* **name** - xssEncode
* **class** - com.sixdimensions.wcm.cq.taglib.ext.XSSEncodeTag
* **attributes**:

  * **encodingType** - The encoding type to use for the value, must be one of:
  
      * html (default)
      * html\_attr
      * js
      * xml
      * xml\_attr
      
  * **value** -  *required* - The text to encode
  * **var** -  The variable in which to save the encoded text, if not specified the 
       encoded text will be written to the page
  
Example:

	<ext:xssEncode value="<script>alert('Exploit!')</script>" />
