<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">

<div id="blogmenu">
	<ul>
		<h1><a style="color: white; text-decoration: none;" href="${pageContext.request.contextPath}/${currentId}">${blogVo.title}</a></h1>
		<c:choose>

			<c:when test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			</c:when>

			<c:otherwise>
				<c:choose>
					<c:when test="${currentId == authUser.id}">
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin">블로그 관리</a></li>
					</c:when>

					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</ul>
</div>