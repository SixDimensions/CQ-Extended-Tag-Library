---
title: Tags
layout: default
---

The CQ Extended Tag Library provides a set of [custom JSP Tags](http://docs.oracle.com/javaee/5/tutorial/doc/bnalj.html)
built to make it easier for developers to create clean, error-free scripts in Adobe CQ/AEM.

The following tags are available:

<ul>
  {% for tag in site.pages %}
    {% if tag.url contains "/tags" %}
      {% unless tag.url contains "index.html" %}
        <li>
          <a href="{{ tag.url }}">{{ tag.title }}</a>
        </li>
      {% endunless %}
    {% endif %}
  {% endfor %}
</ul>
