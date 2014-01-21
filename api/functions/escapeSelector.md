---
title: escapeSelector
layout: default
summary: Escapes the provided string into a valid CSS selector
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Expression Language Functions]({{ site.baseurl }}/api/functions.html) / {{ page.title }}

## escapeSelector EL Function

Escapes the provided string into a valid CSS selector. The string will be converted to 
lowercase, and all non-ASCII characters replaced with '-'

### Parameters

* **string** - `java.lang.String` - the string to escape

### Returns

`java.lang.String` - The escaped string
  
Example:

    ${ext:escapeSelector(resource.name)}