<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blogmenu.jsp" />
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
				    <c:choose>
				        <c:when test="${not empty post}">
				            <h2>${post.title}</h2>
				            <p>${post.contents}</p>
				        </c:when>
				        <c:otherwise>
				            <h2>게시물이 존재하지 않습니다.</h2>
				      		<a href="${pageContext.request.contextPath}/${currentId}" class="move">
				                   블로그 메인으로 돌아가기
				            </a>
				        </c:otherwise>
				    </c:choose>
				</div>
				<ul class="blog-list">
				    <c:forEach var="post" items="${postList}">
				        <li>
				            <c:choose>
				                <c:when test="${post.id == currentPostId}">
				                    <a href="${pageContext.request.contextPath}/${currentId}/${post.categoryId}/${post.id}" class="activeBlogList">
				                        ${post.title}
				                    </a>
				                </c:when>
				                <c:otherwise>
				                    <a href="${pageContext.request.contextPath}/${currentId}/${post.categoryId}/${post.id}">
				                        ${post.title}
				                    </a>
				                </c:otherwise>
				            </c:choose>
				            <span>${post.regDate}</span>
				        </li>
				    </c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img
					src="${pageContext.request.contextPath}${blogVo.profile}">
			</div>
		</div>

	<div id="navigation">
    <h2>카테고리</h2>
    <ul>
        <c:forEach items="${categoryList}" var="category">
            <li>
                <c:choose>
                    <c:when test="${category.id == currentCategoryId}">
                        <a href="${pageContext.request.contextPath}/${currentId}/${category.id}" class="activeCategory">
                            ${category.name}
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/${currentId}/${category.id}">
                            ${category.name}
                        </a>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:forEach>
    </ul>
</div>



		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>