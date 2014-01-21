---
title: Expression Language Functions
layout: default
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / {{ page.title }}

## Expression Language Functions

The CQ Extended Tag Library provides a set of [custom Expression Language Functions](http://docs.oracle.com/javaee/6/tutorial/doc/gjddd.html)
built to make it easier for developers to create clean, error-free scripts in Adobe CQ/AEM.

The following EL functions are available:

{% for elf in site.pages %}
{% if elf.url contains "/api/functions" %}
{% unless elf.url contains "functions.html" %}
* __[{{ elf.title }}]({{ site.baseurl }}{{ elf.url }})__ - {{ elf.summary }}
{% endunless %}
{% endif %}
{% endfor %}

