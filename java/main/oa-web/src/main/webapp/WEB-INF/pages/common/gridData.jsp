<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<dataset>
    <totalCount>${page.totalCount}</totalCount>
    <c:forEach items="${rows}" var="row">
        <row>
            <c:forEach items="${row.cells}" var="cell">
                <${cell.key}>${cell.value}</${cell.key}>
            </c:forEach>
        </row>
    </c:forEach>
</dataset>
