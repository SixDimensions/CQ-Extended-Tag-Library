<%@page session="false" import="javax.jcr.*,
        org.apache.sling.api.resource.Resource,
        org.apache.sling.api.resource.ValueMap,
        com.day.cq.commons.inherit.InheritanceValueMap,
        com.day.cq.wcm.commons.WCMUtils,
        com.day.cq.wcm.api.Page,
        com.day.cq.wcm.api.NameConstants,
        com.day.cq.wcm.api.PageManager,
        com.day.cq.wcm.api.designer.Designer,
        com.day.cq.wcm.api.designer.Design,
        com.day.cq.wcm.api.designer.Style,
        com.day.cq.wcm.api.components.ComponentContext,
        com.day.cq.wcm.api.components.EditContext"
%><%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling" %><%
%><%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %><%
%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%
%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%
%><%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%
%><cq:defineObjects />
<%@taglib prefix="ext" uri="http://labs.sixdimensions.com/tld/ext" %>
<sling:getResource path="/content/dam/geometrixx/shapes/cir_circle.png" var="assetResource" />
<ext:getAsset resource="${assetResource}" var="asset" />
ASSET_PATH${asset.path}ASSET_PATH
ASSET_METADATA<ext:getAssetMetadata asset="${asset}" key="dam:MIMEtype" />ASSET_METADATA