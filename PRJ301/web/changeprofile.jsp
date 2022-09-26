    <%-- 
    Document   : personalPage
    Created on : Jun 13, 2022, 7:30:04 PM
    Author     : NCC
--%>

<%@page import="dbacess.OrderDao"%>
<%@page import="Packages.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <form action="changeprofileServlet" method="post">
            <table>
                <tr>
                    <td>full name: </td><td><input type="text" name="txtname"></td></tr>
                <tr><td>phone: </td><td><input type="phone" name="txtphone"></td></tr>  
                <tr><td colspan="2"><input type="submit" name="action" value="confirm"></td></tr>  
                </tr>
            </table>
            </form>
        </section>
            <footer>
                <%@include file="footer.jsp"%>
            </footer>
           
    </body>
</html>
