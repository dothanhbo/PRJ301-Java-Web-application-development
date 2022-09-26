<%-- 
    Document   : header_loginedUser
    Created on : Jun 13, 2022, 12:22:30 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mycss.css" type="text.css" />
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="mainController?action=changeprofile">Change profile</a></li>
                <li><a href="mainController?action=loadOrder&status=2">Completed orders</a></li>
                <li><a href="mainController?action=loadOrder&status=3">Canceled orders</a></li>
                <li><a href="mainController?action=loadOrder&status=1">Processing orders</a></li>
                <li><a href="mainController?action=logout">Logout</a></li>
                <li><form action="mainController" method="post">from<input type="date" name="from">to<input type="date" name="to">
                        <input type="submit" value="searchByDate" name="action"></form>
                </li>
            </ul>
        </nav>
    </body>
</html>