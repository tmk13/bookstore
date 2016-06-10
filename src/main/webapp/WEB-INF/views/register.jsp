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

	       	<form:form action="register.html" method="post" modelAttribute="userRegister">
		            Nazwa użytkownika<br />
		            <form:input type="text" path="userName" size="10" />
		            <br />
		            Hasło
		            <br />
		            <form:input type="password" path="userPassword" size="10" />
		           <br /> <br />
		            <input type="submit" value="Zapisz">
		            <br />
		            <form:errors path="*" cssClass="error"/>
			</form:form>

		</div>
    
    </body>
</html>
