<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=euc-kr" %>

	
	<c:choose>
		<c:when test = "${pageType eq 'user' }">
			<c:set var = "fncName" value =  "fncGetUserList"/>
		</c:when>
		
		<c:when test = "${pageType eq 'product' }">
			<c:set var = "fncName" value =  "fncGetProductList"/>
			<c:set var = "menu" value = ",'${param.menu}'"/>
		</c:when>
		
	</c:choose>	
		
		<c:if test="${ resultPage.currentPage <= resultPage.pageUnit }">
				◀ 이전
		</c:if>
		<c:if test="${ resultPage.currentPage > resultPage.pageUnit }">
				<a href="javascript:${fncName }('${ resultPage.currentPage-1}')">◀ 이전</a>
		</c:if>
		
		<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
			<a href="javascript:${fncName }('${ i }');">${ i }</a>
		</c:forEach>
		
		<c:if test="${ resultPage.endUnitPage >= resultPage.maxPage }">
				이후 ▶
		</c:if>
		<c:if test="${ resultPage.endUnitPage < resultPage.maxPage }">
				<a href="javascript:${fncName }('${resultPage.endUnitPage+1}')">이후 ▶</a>
		</c:if>
	