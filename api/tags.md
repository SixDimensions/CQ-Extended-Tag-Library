---
title: Tags
layout: default
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / {{ page.title }}

## Tags

The CQ Extended Tag Library provides a set of [custom JSP Tags](http://docs.oracle.com/javaee/5/tutorial/doc/bnalj.html)
built to make it easier for developers to create clean, error-free scripts in Adobe CQ/AEM.

The following tags are available:

{% for tag in site.pages %}
{% if tag.url contains "/api/tags" %}
{% unless tag.url contains "tags.html" %}
* __[{{ tag.title }}]({{ site.baseurl }}{{ tag.url }})__ - {{ tag.summary }}
{% endunless %}
{% endif %}
{% endfor %}

