---
title: Defer
layout: default
---

## Defer Tag

Defers the writing of HTML until a WriteDeferred tag is evaluated.

* **name** - defer
* **class** - com.sixdimensions.wcm.cq.taglib.ext.DeferTag
* **attributes**:
  * **id** - The id of the content block, ensures content is only written once

Example:

<ext:defer id="a-script-block">
  <script>
    alert("Hello World");
  </script>
</ext:defer>
