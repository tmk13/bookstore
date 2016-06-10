
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html >

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/bookstore.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script src="js/bookstore.js" charset="UTF-8"></script>
<title>Twoja księgarnia</title>
</head>
<body>
	<div id="centered">

		<jsp:include page="header.jsp" flush="true" />
		<br />
		<jsp:include page="leftColumn.jsp" flush="true" />

		<h1>Lista książek</h1>
		<table border="1">
			<tr>
				<th align="left">Autorzy</th>
				<th align="left">Tytuł książki</th>
			</tr>
			<c:forEach items="${bookList}" var="book">
				<tr>
					<td><c:forEach var="author" varStatus="loop"
							items="${book.authors }">
							<c:out value="${author.firstName }" />
							<c:out value="${author.lastName }" />${!loop.last ? ',' : ''}</c:forEach></td>

					<td>${book.bookTitle}</td>
				</tr>
			</c:forEach>
		</table>
		<br />


	</div>

	<a href="addBook.html">Dodaj książki.</a>
</body>
</html>