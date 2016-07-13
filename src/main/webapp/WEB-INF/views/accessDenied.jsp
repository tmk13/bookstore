<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Twoja księgarnia</title>
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

    <h2>HTTP Status 403 - Access is denied</h2>
    <br/>
    <h3>You do not have permission to access this page!</h3>

</div>
</body>
</html>


