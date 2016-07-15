<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-2">
    <title><spring:message code="bookstore.BookController.home.title"/></title>
</head>
<body>

<c:if test="${param.category != null}">
    <div>
        <spring:message code="bookstore.AddBookController.list.categoryList" var="categoryList"/>
        <span class="label" style="margin-left: 15px;"> <c:out
                value="${categoryList} ${param.category }"></c:out>
			</span>
    </div>
</c:if>
<c:if test="${param.category == null}">
    <div>
			<span class="label" style="margin-left: 15px;"><spring:message
                    code="bookstore.AddBookController.allBooks.allBooksList"/> </span>
    </div>
</c:if>

<table id="grid">
    <thead>
    <tr>
        <th id="th-title"><spring:message code="bookstore.AddBookController.addBook.title"/></th>
        <th id="th-author"><spring:message code="bookstore.AddBookController.addBook.author"/></th>
        <th id="th-publisher"><spring:message code="bookstore.AddBookController.addBook.publisher"/></th>
        <th id="th-price"><spring:message code="bookstore.AddBookController.addBook.price"/></th>
    </tr>
    </thead>


    <tbody>
    <c:forEach var="book" items="${allBooks}">

        <tr>
            <th scope="row" id="r100"><a href="book.html?id=${book.id}"><c:out value="${book.bookTitle }"/></a></th>
            <td><c:forEach var="author" varStatus="loop" items="${book.authors }">
                <c:out value="${author.firstName }"/>
                <c:out value="${author.lastName }"/>${!loop.last ? ',' : ''}</c:forEach></td>
            <td><c:out value="${book.publisherName }"/></td>
            <td><c:out value="${book.price }"/></td>
        </tr>

    </c:forEach>

    </tbody>

</table>
</body>
</html>