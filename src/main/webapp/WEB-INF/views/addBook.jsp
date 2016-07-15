<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="css/bookstore.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script src="js/bookstore.js" charset="UTF-8"></script>
    <title><spring:message code="bookstore.BookController.home.title"/> </title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>

<div id="centered">

    <jsp:include page="header.jsp" flush="true"/>
    <br/>
    <jsp:include page="leftColumn.jsp" flush="true"/>

    <h1><spring:message code="bookstore.AddBookController.addBook.addBook"/> </h1>
    <form:form enctype="multipart/form-data" method="post" modelAttribute="bookFormDTO">
        <spring:message code="bookstore.AddBookController.addBook.author"/>
        <form:errors path="authors" cssClass="error"/> <br/>
        <form:select multiple="true" path="authors">
            <form:options items="${authorList}" itemValue="id" itemLabel="fullName"/>
        </form:select>
        <br/><br/>
        <spring:message code="bookstore.AddBookController.addBook.category"/>
        <form:errors path="categories" cssClass="error"/> <br/>
        <form:select multiple="true" path="categories">
            <form:options items="${catList}" itemValue="id" itemLabel="categoryDescription"/>
        </form:select>
        <br/><br/>
        <spring:message code="bookstore.AddBookController.addBook.title"/>
        <form:errors path="bookTitle" cssClass="error"/><br/>
        <form:input path="bookTitle"/><br/><br/>
        <spring:message code="bookstore.AddBookController.addBook.publisher"/>
        <form:errors path="publisherName" cssClass="error"/> <br/>
        <form:input path="publisherName"/><br/><br/>
        <spring:message code="bookstore.AddBookController.addBook.price"/>
        <form:errors path="price" cssClass="error"/> <br/>
        <form:input path="price"/><br/>
        <br/>
        <spring:message code="bookstore.AddBookController.addBook.coverImage"/>
        <br/>
        <input type="file" accept=".jpg,.jpeg,.JPG,.JPEG,.png,.PNG, image/vnd.sealedmedia.softseal-jpg, image/jpeg, image/png" name="file"/>
        <br/><br/>
        <spring:message code="bookstore.AddBookController.addBook.imageDescription"/>
        <br/>
        <input type="text" name="description"/>
        <br/><br/>
        <spring:message code="bookstore.AddBookController.addBook.send" var="send"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="submit" value="${send}">
    </form:form>

</div>

</body>
</html>
