<%@include file="/libs/foundation/global.jsp"%>
<%@taglib prefix="ext" uri="http://labs.sixdimensions.com/tld/ext" %>
AUTHOR<ext:externalize mode="author" path="/content/ext" var="author"/>${author}AUTHOR
RELATIVE<ext:externalize mode="relative" path="/content/geometrixx" var="relative"/>${relative}RELATIVE
ABSOLUTE<ext:externalize mode="absolute" path="/content/geometrixx" var="absolute"/>${absolute}ABSOLUTE
