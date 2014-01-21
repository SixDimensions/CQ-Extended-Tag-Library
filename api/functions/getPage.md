---
title: getPage
layout: default
summary: Gets the page at the path or the page containing the resource at the path.
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Expression Language Functions]({{ site.baseurl }}/api/functions.html) / {{ page.title }}

## getPage EL Function

Gets the page at the path or the page containing the resource at the path.

### Parameters

* **properties** - `com.day.cq.wcm.api.PageManager` - the page manager with with to fetch 
    the page
* **path** - `java.lang.String` - the path of the page or a child resource of the page

### Returns

`com.day.cq.wcm.api.Page` - the page at or containing the path
  
Example:

    <c:set var="geometrixxEnHome" value="${ext:getPage(pageManager,'/content/geometrixx/en/jcr:content')}" />