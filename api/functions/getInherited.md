---
title: getInherited
layout: default
summary: Gets an inherited value from an InheritanceValueMap.
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Expression Language Functions]({{ site.baseurl }}/api/functions.html) / {{ page.title }}

## getInherited EL Function

Gets an inherited value from an InheritanceValueMap.

### Parameters

* **properties** - `com.day.cq.commons.inherit.InheritanceValueMap` - the 
    InheritanceValueMap from which to get the property
* **key** - `java.lang.String` - the key of the property to retrieve
* **defaultOrType** - `java.lang.Object` - either the default value or a Class in which to 
    coerce the value

### Returns

`java.lang.Object` - the value, if the default is specified and no value exists for the 
key the default is returned.  If a value exists, it will be cast into the same type as
the default or the type of Class if specified.
  
Example:

    ${ext:getInherited(iprops,'jcr:title','Default Title')}

