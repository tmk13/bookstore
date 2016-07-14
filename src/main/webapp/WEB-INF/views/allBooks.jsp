<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html >

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="js/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="css/bookstore.css" type="text/css" />
<script src="js/bookstore.js" type="text/javascript"></script>
<title><spring:message code="bookstore.BookController.home.title" /> </title>
</head>

<body>
	<div id="centered">

		<jsp:include page="header.jsp" flush="true" />
		<br />
		<jsp:include page="leftColumn.jsp" flush="true" />
		<div>
			<span class="label" style="margin-left: 15px;"><spring:message
                    code="bookstore.AddBookController.allBooks.allBooksList"/> </span>
		</div>


		<jsp:include page="list.jsp" flush="true" />


	</div>


</body>
</html>

