---
layout: default
title: API
releases: ["0.1.2","0.1.1","0.1.0"]
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / {{ page.title }}

## CQ Extended Tag Library API

The following API documentation is available:

* [JSP Tags]({{ site.baseurl }}/api/tags.html)
* [Expression Language Functions]({{ site.baseurl }}/api/functions.html)

### JavaDoc

Additionally, auto-generated JavaDocs are available for each release:

**[Current Release]({{ site.baseurl }}/api/javadoc/current)**

#### Past Releases

{% for release in page.releases %}
* [{{ release }}]({{ site.baseurl }}/api/javadoc/com.sixdimensions.wcm.cq.taglib.ext-{{ release }}-javadoc.jar)
{% endfor %}