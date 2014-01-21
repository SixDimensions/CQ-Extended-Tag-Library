---
title: displayMode
layout: default
summary: Displays or hides the contents of the tag based on the current WCMMode
---

[6D Labs](http://labs.sixdimensions.com) / [{{ site.name }}]({{ site.baseurl }}/) / [API]({{ site.baseurl }}/api.html) / [Tags]({{ site.baseurl }}/api/tags.html) | {{ page.title }}

## displayMode Tag

Displays the contents of the tag only if the current WCMMode matches a 
list of display modes or is not in a list of hide modes.  One of either 
the display or hide modes must be specified.

Valid modes are:

* analytics - The WCM is in analytics mode.
* design - The WCM is in design mode.
* disabled - The WCM is disabled.
* edit - The WCM is in edit mode.
* preview - The WCM is in preview mode.
* read_only  - The WCM is in read only mode.

* **name** - displayMode
* **class** - com.sixdimensions.wcm.cq.taglib.ext.DisplayModeTag
* **attributes**:
  * **displayModes** - A comma separate list of valid modes in which to display the contents
  * **hideModes** - A comma separate list of valid modes in which to hide the contents
  
Example:

	<ext:displayMode displayMode="edit">
	  <c:if test="${properties.address == ''}">
	  	Please set the address!
	  </c:if>
	</ext:displayMode>

