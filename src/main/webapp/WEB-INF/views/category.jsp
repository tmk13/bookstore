<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
	pageEncoding="ISO-8859-2"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="js/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="css/bookstore.css" type="text/css" />
<script src="js/bookstore.js"></script>
<title><spring:message code="bookstore.BookController.home.title"/> </title>

</head>
<body>
	<div id="centered">
		<jsp:include page="header.jsp" flush="true" />
		<br />
		<jsp:include page="leftColumn.jsp" flush="true" />

			<c:if test="${param.category != null}">
				<div>
					<span class="label" style="margin-left: 15px;">
						<spring:message code="bookstore.AddBookController.list.categoryList" var="categoryList"/>
					<c:out value="${categoryList} "></c:out>
					<c:out value="${param.category }: ${fn:length(allBooks) }" />
					</span>
				</div>
			</c:if>

		<table id="grid">
			<thead>
				<tr>
					<th id="th-title"><spring:message code="bookstore.AddBookController.addBook.title"/> </th>
					<th id="th-author"><spring:message code="bookstore.AddBookController.addBook.author"/> </th>
					<th id="th-publisher"><spring:message code="bookstore.AddBookController.addBook.publisher"/> </th>
					<th id="th-price"><spring:message code="bookstore.AddBookController.addBook.price"/> </th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="book" varStatus="loop" items="${allBooks }">
				
					<c:forEach var="bookCategory" items="${book.categories }">
						<c:if test="${bookCategory.id == param.categoryId  }">
							<tr>
								<th scope="row" id="r100"><c:out value="${book.bookTitle }" /></th>
		
								<td>
									<c:forEach var="author" varStatus="loop" items="${book.authors }">
										<c:out value="${author.firstName }" /> 
										<c:out value="${author.lastName }" />${!loop.last ? ',' : '' }
									</c:forEach>
								</td>
								<td><c:out value="${book.publisherName }" /></td>
								<td><c:out value="${book.price }" /></td>
							</tr>
						</c:if>
					
					</c:forEach>

				</c:forEach>

			</tbody>

		</table>
	</div>
</body>
</html>