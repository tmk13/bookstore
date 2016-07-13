<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
    <link rel="stylesheet" href="css/bookstore.css" type="text/css"/>
    <script src="js/bookstore.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
</head>

<body>
<div class="leftbar">
    <ul id="menu">
        <li>
            <div>
                <a class="link1" href="${initParam.home}"> <span class="label"
                                                                 style="margin-left: 15px;">Strona domowa</span>
                </a>
            </div>
        </li>
        <li>
            <div>
                <a class="link1" href="allBooks.html"><span
                        style="margin-left: 15px;" class="label">Wszystkie książki</span></a>
            </div>
        </li>
        <li>
            <div>
                <span class="label" style="margin-left: 15px;">Kategorie</span>
            </div>
            <ul>
                <c:forEach var="category" items="${catList}">
                    <c:url value="/category.html" var="main">
                        <c:param name="action" value="category"/>
                        <c:param name="categoryId" value="${category.id}"/>
                        <c:param name="category" value="${category.categoryDescription}"/>
                    </c:url>
                    <li><a class="label"
                           href='<c:out value="${main}" />'><span
                            class="label" style="margin-left: 30px;">${category.categoryDescription}</span></a>
                    </li>

                </c:forEach>

            </ul>

        </li>
        <li>
            <div>
                <span class="label" style="margin-left: 15px;">Kontakt</span>

            </div>
        </li>
    </ul>
    <form action="searchResult.html">
        <input type="hidden" name="action" value="search"/> <input id="text"
                                                                   type="text" name="keyWord" size="7"/> <span
            class="tooltip_message">?</span>
        <p/>
        <input id="submit" type="submit" value="Szukaj"/>
    </form>

    <br/><br/>
    <sec:authorize access="isAnonymous()">
        <a href="loginPage.html">Zaloguj</a>
        <br/><br/>
        <form:form action="register.html" method="get">
            Nie masz konta?
            <br />
            <%--<input type="submit" value="Zarejestruj">--%>
            <a href="register.html">Zarejestruj</a>
        </form:form>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_USER')">
        <br/>
        <b>Zalogowany:</b>
        <br/><br/>
        <b>
            <sec:authentication property="principal.username"/>
        </b>
        <br/><br/>
        <%--<a href="loginPage.html?logout">Wyloguj</a>--%>
        <form action="logout.do" method="post">
            <input type="submit" value="Wyloguj">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <%--<a href="logout.do">Wyloguj</a>--%>
        </form>
        <%--<a href="<c:out value="j_spring_security_logout" />">Wyloguj</a>--%>
        <%--<form action="/mylogout.html" method="get" >--%>
        <%--&lt;%&ndash;<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />&ndash;%&gt;--%>
        <%--<input type="submit" value="Wyloguj">--%>
        <%--</form>--%>
    </sec:authorize>

</div>
</body>

<%--<sec:authorize access="hasRole('ROLE_ANONYMOUSE')">--%>
<%--<jsp:include page="loginPage.jsp" flush="true" />--%>
<%--</sec:authorize>--%>

<%--<sec:authorize access="hasRole('ROLE_USER)">--%>
<%--<jsp:include page="logoutPage.jsp" flush="true" />--%>
<%--</sec:authorize>--%>

<%--<c:if test="${sessionScope.loggedUser.userName == null }">--%>
<%--<jsp:include page="loginPage.jsp" flush="true" />--%>
<%--</c:if>--%>

<%--<c:if test="${sessionScope.loggedUser.userName != null }">--%>
<%--<jsp:include page="logoutPage.jsp" flush="true" />--%>
<%--</c:if>--%>

