---
title: Usage
layout: default
---

## Using the CQ Extended Tag Library

To enable the CQ Extended Tag Library in your scripts, add the following into your `global.jsp`:

    <%@taglib prefix="ext" uri="http://labs.sixdimensions.com/tld/ext" %>
    
This registers the CQ Extended Tag Library using the prefix `ext`.  Then to use the tags and/or functions, 
you use this prefix, ex:

    <ext:defer id="run-once-javascript">
      <script>
        alert("Hello World");
      </script>
    </ext:defer>
    
or using an Expression Language function:

    ${ext:getPage(pageManager,'/content/geometrixx/en')}

### Sling Tag Library

The newest release of the Sling Tag Library (currently version 2.2.0), complements the CQ Extended Tag Library and 
can be downloaded out of the Maven Central Repository.

For details on using the Sling Tag Library, check the [documentation page](http://sling.apache.org/documentation/bundles/sling-scripting-jsp-taglib.html).
