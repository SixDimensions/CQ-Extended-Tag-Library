---
title: getAsset
layout: default
summary: Retrieves an asset from a resource.
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Tags]({{ site.baseurl }}/api/tags.html) / {{ page.title }}

## getAsset Tag

Retrieves an asset from a resource. Checks whether the given resource is an asset, and 
if not, travels upwards the resource hierarchy until a resource is an asset.

* **name** - getAsset
* **class** - com.sixdimensions.wcm.cq.taglib.ext.GetAssetTag
* **attributes**:

  * **resource** - *required* - The resource from which to retrieve the asset
  * **var** -  *required* - The name of the page context variable in which to save the asset

Example:

	<ext:getAsset resource="${assetResource}" var="asset" />