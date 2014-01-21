---
title: getInheritanceValueMap
layout: default
summary: Gets an InheritanceValueMap from a specified resource.
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Expression Language Functions]({{ site.baseurl }}/api/functions.html) / {{ page.title }}

## getInheritanceValueMap EL Function

Gets an InheritanceValueMap from the specified resource.

### Parameters

* **resource** - `org.apache.sling.api.resource.Resource` - the resource from which to get 
    the InheritanceValueMap

### Returns

`com.day.cq.commons.inherit.InheritanceValueMap` - The InheritanceValueMap for the 
specified Resource.
  
Example:

    <c:set var="iprops" value="${ext:getInheritanceValueMap(resource)}" />
