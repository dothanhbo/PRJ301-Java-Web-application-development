<%-- 
    Document   : searchpage
    Created on : Jul 17, 2022, 3:07:31 PM
    Author     : NCC
--%>

<%@page import="Packages.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search page</title>
        <link rel="stylesheet" href="mycss.css" type=text/css">
    </head>
    <header>
            <%@include file="header.jsp"%>
        </header>
    <body>
        <%
            ArrayList<Plant> list=(ArrayList<Plant>)request.getAttribute("plantlist");
            if (list != null && !list.isEmpty()) {
            for (Plant plant : list){
                 String[] tmp = {"out of stock", "availble"};
        %>
        <table class='plant'>
                <tr>
                    <td><img src='<%=plant.getImgPath()%>' class='planting' id="image"/></td>
                    <td>Product ID:<%=plant.getPid()%></td>
                    <td>Product Name:<%=plant.getName()%></td>
                    <td>Price:<%=plant.getPrice()%></td>
                    <td>Status:<%=tmp[plant.getStatus()]%></td>
                    <td>Category:<%=plant.getCateName()%></td>
                </tr>
            </table>
        <%}}
else{%>
<p>nothing found</p>
<%}%>
    </body>
    <footer>
            <%@include file="footer.jsp"%>
        </footer>
</html>
