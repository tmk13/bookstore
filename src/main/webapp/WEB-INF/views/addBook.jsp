<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/bookstore.css" type="text/css" />
		<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
		<script src="js/bookstore.js" charset="UTF-8"></script>
        <title>Twoja księgarnia</title>
         <style>
            .error {
                color: red;
            }
        </style> 
    </head>
    <body>
    
    	<div id="centered">

			<jsp:include page="header.jsp" flush="true" />
			<br />
			<jsp:include page="leftColumn.jsp" flush="true" />

	        <h1>Dodaj książkę</h1>
			<form:form method="post" modelAttribute="book">
		            Autor<br />
		            <form:select multiple="true" path="authors">
		                <form:options items="${authorList}" itemValue="id" itemLabel="fullName" />
		            </form:select>
		            <br /><br />
		             <form:select multiple="true" path="categories">
		                <form:options items="${catList}" itemValue="id" itemLabel="categoryDescription" />
		            </form:select>
		            <br /><br />
		            Nazwa książki
		            <form:errors path="bookTitle"    cssClass="error" /><br />
		            <form:input path="bookTitle"/><br /><br />
		             Wydawca<br />
		            <form:input path="publisherName"/><br /><br />
		             Cena<br />
		            <form:input path="price"/><br /><br />
		            <br />
		            <input type="submit" value="Wyślij">
			</form:form>

		</div>
    
    </body>
</html>
