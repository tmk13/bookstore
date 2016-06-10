<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="loggedbar">

			<br />
			<b>Zalogowany:</b>
			<br /><br />
			<b>${sessionScope.loggedUser.userName}</b>
			<br /><br />
			<form:form action="logout.html" method="post" modelAttribute="user">
		            <input type="submit" value="Wyloguj">
			</form:form>

</div>