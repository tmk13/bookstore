<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
    <link rel="stylesheet" href="css/bookstore.css" type="text/css"/>
    <script src="js/bookstore.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

    <script type="text/javascript">
        var strings = new String();
        strings['bookstore.BookController.home.search'] = "<spring:message code='bookstore.BookController.home.search' javaScriptEscape='true' />";
        <%--strings['settings.authorFirstName'] = "<spring:message code='bookstore.BookController.home.authorFirstName' javaScriptEscape='true' />";--%>
    </script>
    <%--<spring:theme code="jsFile" var="js" />--%>
    <%--<script type="text/javascript" src="${js}" />--%>

</head>

<body>
<div class="leftbar">
    <ul id="menu">
        <li>
            <div>
                <a class="link1" href="${initParam.home}"> <span class="label"
                                                                 style="margin-left: 15px;"><spring:message
                        code="bookstore.BookController.leftColumn.homepage"/></span>
                </a>
            </div>
        </li>
        <li>
            <div>
                <a class="link1" href="allBooks.html?language=en"><span
                        style="margin-left: 15px;" class="label"><spring:message
                        code="bookstore.BookController.leftColumn.allbooks"/></span></a>
            </div>
        </li>
        <li>
            <div>
                <span class="label" style="margin-left: 15px;"><spring:message
                        code="bookstore.BookController.leftColumn.categories"/></span>
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
                <span class="label" style="margin-left: 15px;"><spring:message
                        code="bookstore.BookController.leftColumn.contact"/></span>

            </div>
        </li>
    </ul>
    <form action="searchResult.html">
        <input type="hidden" name="action" value="search"/> <input id="text"
                                                                   type="text" name="keyWord" size="7"/> <span
            class="tooltip_message">?</span>
        <p/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input id="submit" type="submit" value=
        	<spring:message code="bookstore.BookController.leftColumn.search"/>
        />
    </form>

        <br/><br/>
        <sec:authorize access="isAnonymous()">
        <a href="loginPage.html"><spring:message code="bookstore.BookController.leftColumn.login"/></a>
        <br/><br/>
        <form:form action="register.html" method="get">
            <spring:message code="bookstore.BookController.leftColumn.doNotHaveAccount"/>
        <br/>
            <%--<input type="submit" value="Zarejestruj">--%>
        <a href="register.html"><spring:message code="bookstore.BookController.leftColumn.register"/></a>
        </form:form>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_USER')">
        <br/>
        <b><spring:message code="bookstore.BookController.leftColumn.logged"/>:</b>
        <br/><br/>
        <b>
            <sec:authentication property="principal.username"/>
        </b>
        <br/><br/>
            <%--<a href="loginPage.html?logout">Wyloguj</a>--%>
        <form action="logout.do" method="post">
            <spring:message code="bookstore.BookController.leftColumn.logout" var="logout"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="submit" value="${logout}">
                <%--<a href="logout.do">Wyloguj</a>--%>
        </form>
            <%--<a href="<c:out value="j_spring_security_logout" />">Wyloguj</a>--%>
            <%--<form action="/mylogout.html" method="get" >--%>
            <%--&lt;%&ndash;<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />&ndash;%&gt;--%>
            <%--<input type="submit" value="Wyloguj">--%>
            <%--</form>--%>
        </sec:authorize>
        <br/>
        <a href="language.html?language=uk"><img src="resources/uk.png"></a>
        <a href="language.html?language=pl"><img src="resources/pl.png"></a>
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