<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:if test="${ctx == '/'}">
    <c:set var="ctx" value=""/>
</c:if>
<c:set var="pagesPath" value="${ctx}/WEB-INF/pages"/>
<c:set var="scriptsPath" value="${ctx}/scripts"/>
<c:set var="themesPath" value="${ctx}/themes/default"/>