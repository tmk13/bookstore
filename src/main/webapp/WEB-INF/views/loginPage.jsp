<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="secutity" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="css/bookstore.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script src="js/bookstore.js" charset="UTF-8"></script>
    <title><spring:message code="bookstore.BookController.home.title"/></title>
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

    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
        <%--<font color="red">--%>
        <%--Your login attempt was not successful due to <br/><br/>--%>
        <%--<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.--%>
        <%--</font>--%>
        <%--<span class="error">--%>
        <%--Your login attempt was not successful due to <br/><br/>--%>
        <%--<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.--%>
        <%--</span>--%>
        <span class="error">
			Your login attempt was not successful, try again. <br/><br/>
			<c:out value="Reason: ${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
		</span>
    </c:if>
    <br/><br/>
    <b><spring:message code="bookstore.BookController.leftColumn.loggedout"/></b>
    <br/>
    <%--<form action="login" method="post" >--%>
    <%--Użytkownik<br />--%>
    <%--<input type="text" name="USER_NAME" size="10" />--%>
    <%--<br />--%>
    <%--Hasło--%>
    <%--<br />--%>
    <%--<input type="password" name="USER_PASSWORD" size="10" />--%>
    <%--<br /> <br />--%>
    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
    <%--<input type="submit" value="Zaloguj">--%>
    <%--</form>--%>
    <%--<br />--%>
    <security:form action="loginPage.html" method="post">
        <br/>
        <spring:message code="bookstore.LoginController.loginPage.user"/> <br/>
        <input type="text" name="USER_NAME" size="10"/>
        <br/>
        <spring:message code="bookstore.LoginController.loginPage.password"/>
        <br/>
        <input type="password" name="USER_PASSWORD" size="10"/>
        <br/> <br/>
        <spring:message code="bookstore.BookController.leftColumn.login" var="login"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="${login}"/>

        <br/><br/>
        <form:form action="register.html" method="get">
            <spring:message code="bookstore.BookController.leftColumn.doNotHaveAccount"/>
            <br/>
            <%--<input type="submit" value="Zarejestruj">--%>
            <a href="register.html"><spring:message code="bookstore.BookController.leftColumn.register"/></a>
        </form:form>
        <br/>

    </security:form>


</div>
</body>
</html>


