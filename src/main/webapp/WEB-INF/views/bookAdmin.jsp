<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html >

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <script src="js/jquery-1.9.1.js"></script>
    <link rel="stylesheet" href="css/bookstore.css" type="text/css"/>
    <script src="js/bookstore.js" type="text/javascript"></script>
    <title><spring:message code="bookstore.BookController.home.title"/></title>
</head>

<body>
<div id="centered">

    <jsp:include page="header.jsp" flush="true"/>
    <br/>
    <jsp:include page="leftColumn.jsp" flush="true"/>
    <div>
			<span class="label" style="margin-left: 15px;"><spring:message
                    code="bookstore.BookController.book.details"/> </span>
    </div>
    <br/>
    <c:if test="${bookFormDTO.image != null}">
        <img src="data:image/png;base64,${bookFormDTO.image}"/>
        <br/>
        <a href="downloadImage.do?id=${bookFormDTO.id}"><spring:message code="bookstore.BookController.book.saveImage"/> </a>
    </c:if>
    <c:if test="${bookFormDTO.image == null}">
        <%--<img src="data:image/png;base64,${book.image}"/><br/><br/> maybe some default image --%>
    </c:if>
    <br/><br/>
    <form:form action="saveBook.html" enctype="multipart/form-data" method="post" modelAttribute="bookFormDTO">
        <input type="hidden" name="id" value="${bookFormDTO.id}">
        <spring:message code="bookstore.AddBookController.addBook.title"/>:
        <input type="text" name="bookTitle" value="${bookFormDTO.bookTitle }"><br/>
        <spring:message code="bookstore.AddBookController.addBook.author"/>:
        <c:forEach var="author" varStatus="loop" items="${bookFormDTO.authors }">
            <c:out value="${author.firstName }"/>
            <c:out value="${author.lastName }"/>${!loop.last ? ',' : ''}</c:forEach><br/>
        <spring:message code="bookstore.AddBookController.addBook.publisher"/>:
        <input type="text" name="publisherName" value="${bookFormDTO.publisherName }"><br/>
        <spring:message code="bookstore.AddBookController.addBook.category"/>:
        <c:forEach var="category" varStatus="loop" items="${bookFormDTO.categories }">
            <c:out value="${category.categoryDescription }"/>${!loop.last ? ',' : ''}</c:forEach><br/>
        <spring:message code="bookstore.AddBookController.addBook.price"/>:
        <input type="text" name="price" value="${bookFormDTO.price }"><br/><br/>
        <input type="file"
               accept=".jpg,.jpeg,.JPG,.JPEG,.png,.PNG, image/vnd.sealedmedia.softseal-jpg, image/jpeg, image/png"
               name="file"/><br/><br/>
        <spring:message code="bookstore.RegisterController.register.save" var="save"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="${save}"/>
    </form:form>

</div>


</body>
</html>

