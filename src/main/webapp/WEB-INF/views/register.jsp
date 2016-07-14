<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/bookstore.css" type="text/css" />
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

			<jsp:include page="header.jsp" flush="true" />
			<br />
			<jsp:include page="leftColumn.jsp" flush="true" />

	       	<form:form action="register.html" method="post" modelAttribute="registrationUserFormDTO">
				<spring:message code="bookstore.RegisterController.register.userName"/>
					<form:errors path="name" cssClass="error"/>
					<br />
		            <form:input type="text" path="name" size="10" />
		            <br />
				<spring:message code="bookstore.LoginController.loginPage.password"/>
					<form:errors path="password" cssClass="error"/>
		            <br />
		            <form:input type="password" path="password" size="10" />
		           <br /> <br />
				<spring:message code="bookstore.RegisterController.register.save" var="save"/>
		            <input type="submit" value="${save}">
		            <br />
			</form:form>

		</div>
    
    </body>
</html>
