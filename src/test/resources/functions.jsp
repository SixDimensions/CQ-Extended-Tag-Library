<%@page session="false" import="com.day.cq.commons.inherit.*"
%><%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling" %><%
%><%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %><%
%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%
%><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%
%><%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%
%><cq:defineObjects />
<%@taglib prefix="ext" uri="http://labs.sixdimensions.com/tld/ext" %>

${ext:escapeSelector('Awesome Coolness!')}

<c:set var="page" value="${ext:getPage(pageManager,'/content/geometrixx/en')}" />
${page.path}

<c:set var="ivm" value="${ext:getInheritanceValueMap(page.contentResource)}" />
${ext:getInherited(ivm,'cq:loginPage','default')}

${ext:getTitle(page,"nav")}
${ext:getTitle(page,"page")}

${ext:getUniqueId('/content/page/jcr:content/path')}

<c:forEach var="child" items="${ext:listPages(page,false)}">
	${ext:getTitle(child,"page")}
</c:forEach>
