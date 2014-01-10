<%@include file="/libs/foundation/global.jsp"%>
<%@taglib prefix="ext" uri="http://labs.sixdimensions.com/tld/ext" %>
<ext:xssEncode encodingType="html" value="<script>I am a bad person</script>" />
<ext:xssEncode encodingType="html_attr" value="\"I am worse" />
<ext:xssEncode encodingType="js" value="\"I am also bad\"" />
<ext:xssEncode encodingType="xml" value="<xml>is stupid</xml>" />
<ext:xssEncode encodingType="xml" value="\"XML SUX\"" />