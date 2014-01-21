---
title: listPages
layout: default
summary: Lists the child pages of a particular page.
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Expression Language Functions]({{ site.baseurl }}/api/functions.html) / {{ page.title }}

## listPages EL Function

Lists the child pages of a particular page.  If filtering is enabled the default 
[PageFilter](http://dev.day.com/docs/en/cq/current/javadoc/com/day/cq/wcm/api/PageFilter.html)
which skips invalid and hidden pages will be used.

### Parameters

* **path** - `com.day.cq.wcm.api.Page` - the page for which to list the child pages
* **filter** - `java.lang.Boolean` - whether or not to filter the child pages

### Returns

`java.util.Iterator<com.day.cq.wcm.api.Page>` - the child pages
  
Example:

	<ul>
      <c:forEach var="childPage" items="${ext:listPages(currentPage, true)}">
      	<li><a href="${childPage.path}.html">${ext:getTitle(childPage,'page')}</a>
      </c:forEach>
	</ul>