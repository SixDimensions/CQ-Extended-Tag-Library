---
title: getTitle
layout: default
summary: Get the title of the specified page.
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Expression Language Functions]({{ site.baseurl }}/api/functions.html) / {{ page.title }}

## getTitle EL Function

Get the title of the specified page gracefully, using the jcr:title and the page node name
as fallbacks.

### Parameters

* **page** - `com.day.cq.wcm.api.Page` - the page from which to get the title
* **type** - `java.lang.String` - the type of title to get, one of 'nav' or 'page', page 
    is default

### Returns

`java.lang.String` - the page title
  
Example:

    <li>${ext:getTitle(currentPage,'nav')}</li>
