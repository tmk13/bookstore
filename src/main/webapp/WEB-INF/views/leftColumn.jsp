<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="css/bookstore.css" type="text/css" />
<script src="js/bookstore.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
</head>

<div class="leftbar">
	<ul id="menu">
		<li><div>
				<a class="link1" href="${initParam.home}"> <span class="label"
					style="margin-left: 15px;">Strona domowa</span>
				</a>
			</div></li>
		<li><div>				
				<a class="link1" href="allBooks.html"><span
					style="margin-left: 15px;" class="label">Wszystkie książki</span></a>
			</div></li>
				<li><div>
				<span class="label" style="margin-left: 15px;">Kategorie</span>
			</div>
			<ul>
				<c:forEach var="category" items="${catList}">
					<c:url value="/category.html" var="main">
						<c:param name="action" value="category" />
						<c:param name="categoryId" value="${category.id}" />
						<c:param name="category" value="${category.categoryDescription}" />
					</c:url>
					<li><a class="label"
						href='<c:out value="${main}" />'><span
							class="label" style="margin-left: 30px;">${category.categoryDescription}</span></a>
					</li>
				
				</c:forEach>
			
			</ul>
		
			</li>
		<li><div>
				<span class="label" style="margin-left: 15px;">Kontakt</span>

			</div></li>
	</ul>
	<form action="searchResult.html">
		<input type="hidden" name="action" value="search" /> <input id="text"
			type="text" name="keyWord" size="7" /> <span
			class="tooltip_message">?</span>
		<p />
		<input id="submit" type="submit" value="Szukaj" />
	</form>


</div>

<c:if test="${sessionScope.loggedUser.userName == null }">
	<jsp:include page="login.jsp" flush="true" />
</c:if>

<c:if test="${sessionScope.loggedUser.userName != null }">
	<jsp:include page="logout.jsp" flush="true" />
</c:if>

