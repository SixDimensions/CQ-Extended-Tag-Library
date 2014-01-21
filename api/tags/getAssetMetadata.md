---
title: getAssetMetadata
layout: default
summary: Retrieves a metadata attribute from the specified asset
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Tags]({{ site.baseurl }}/api/tags.html) / {{ page.title }}

## getAssetMetadata Tag

Retrieves a metadata attribute from the specified asset.

* **name** - getAsset
* **class** - com.sixdimensions.wcm.cq.taglib.ext.GetAssetMetadataTag
* **attributes**:

  * **asset** - *required* - The asset from which to retrieve the metadata
  * **key** -  *required* - The property to retrieve from the asset metadata
  * **var** - The name of the page context variable in which to save the metadata value
      If not specified the value will be written to the page

Example:

	<ext:getAssetMetadata asset="${asset}" key="dam:MimeType" />
