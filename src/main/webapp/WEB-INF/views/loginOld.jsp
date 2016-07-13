<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

         <style>
            .error {
                color: red;
            }
        </style> 

<div class="loginbar">
			<br />
			<b>Niezalogowany</b>
			<br /><br />
			<form:form action="login.html" method="post" modelAttribute="user">
		            Użytkownik<br />
		            <form:input type="text" path="userName" size="10" />
		            <br />
		            Hasło
		            <br />
		            <form:input type="password" path="userPassword" size="10" />
		           <br /> <br />
		            <form:errors path="*" cssClass="error"/>
		            <input type="submit" value="Zaloguj">
			</form:form>
			<br />
			<form:form action="register.html" method="get">
		            Nie masz konta?
		           <br />
		            <input type="submit" value="Zarejestruj">
			</form:form>

</div>