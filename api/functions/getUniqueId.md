---
title: getUniqueId
layout: default
summary: Generates a Unique ID for the resource at the specified path.
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Expression Language Functions]({{ site.baseurl }}/api/functions.html) / {{ page.title }}

## getUniqueId EL Function

Generates a Unique ID for the resource at the specified path.  Does this by appending 
the name of the resource with a CRC32 checksum of the path to the resource.

### Parameters

* **path** - `java.lang.String` - the path for which to generate the id

### Returns

`java.lang.String` - the unique ID
  
Example:

    <div data-id="${ext:getUniqueId(resource.path)}">
    </div>