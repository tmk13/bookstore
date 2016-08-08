<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html >

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="css/bookstore.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script src="js/bookstore.js" charset="UTF-8"></script>
    <title><spring:message code="bookstore.BookController.home.title"/></title>
</head>
<body>
<div id="centered">

    <jsp:include page="header.jsp" flush="true"/>
    <br/>
    <jsp:include page="leftColumn.jsp" flush="true"/>
    <span class="label"><spring:message code="bookstore.BookController.home.recommendedBooks"/></span>
    <table>
        <tr>
            <td><span class="tooltip_img1">
                <a href="book.html?id=2">
					<img src=" ${initParam.imageURL}/A9781430248064-small_3.png"/></a>
            </span></td>
            <td><img src="${initParam.imageURL}/A9781430239963-small_1.png"/></td>
            <td><img src="${initParam.imageURL}/A9781430247647-small_3.png"/></td>
            <td><img src="${initParam.imageURL}/A9781430231684-small_8.png"/></td>
            <td><img src="${initParam.imageURL}/A9781430249474-small_1.png"/></td>
        </tr>
        <tr>
            <td><img src="${initParam.imageURL}/A9781430248187-small_1.png"/></td>
            <td><img src="${initParam.imageURL}/A9781430243779-small_2.png"/></td>
            <td><img src="${initParam.imageURL}/A9781430247401-small_1.png"/></td>
            <td><img src="${initParam.imageURL}/A9781430246596-small_1.png"/></td>
            <td><img src="${initParam.imageURL}/A9781430257349-small_1.png"/></td>
        </tr>
    </table>
</div>
<a href="addBook.html"><spring:message code="bookstore.BookController.home.addBooks"/></a></body>
</html>