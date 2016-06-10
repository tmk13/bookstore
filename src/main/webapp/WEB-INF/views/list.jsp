<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-2">
<title>Twoja księgarnia</title>
</head>
<body>

	<c:if test="${param.category != null}">
		<div>
			<span class="label" style="margin-left: 15px;"> <c:out
					value="Lista książek
				kategorii ${param.category }"></c:out>
			</span>
		</div>
	</c:if>

	<table id="grid">
		<thead>
			<tr>
				<th id="th-title">Tytuł książki</th>
				<th id="th-author">Autor</th>
				<th id="th-author">Wydawca</th>
				<th id="th-price">Cena</th>
			</tr>
		</thead>


		<tbody>
			<c:forEach var="book" items="${allBooks}">

				<tr>
					<th scope="row" id="r100"><c:out value="${book.bookTitle }" /></th>
					<td><c:forEach var="author" varStatus="loop" items="${book.authors }">
							<c:out value="${author.firstName }" />
							<c:out value="${author.lastName }" />${!loop.last ? ',' : ''}</c:forEach></td>
					<td><c:out value="${book.publisherName }" /></td>
					<td><c:out value="${book.price }" /></td>
				</tr>

			</c:forEach>

		</tbody>

	</table>
</body>
</html>