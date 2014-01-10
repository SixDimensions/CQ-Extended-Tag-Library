<%@include file="/libs/foundation/global.jsp"%>
<%@taglib prefix="ext" uri="http://labs.sixdimensions.com/tld/ext" %>
${ext:escapeSelector('Awesome Coolness!')}
<!-- Need to do inheritance -->
<c:set var="page" value="${ext:getPage(pageManager,'/content/geometrixx/en')}" />
${page.path}
${ext:getTitle(page,"nav")}
${ext:getTitle(page,"page")}
${ext:getUniqueId('/content/page/jcr:content/path')}
<c:forEach var="child" items="${ext:listPages(page,false)}">
	${ext:getTitle(child,"page")}
</c:forEach>