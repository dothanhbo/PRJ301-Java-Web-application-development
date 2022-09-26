<%-- 
    Document   : OrderDetail
    Created on : Jun 14, 2022, 10:07:49 PM
    Author     : ACER
--%>

<%@page import="dbacess.PlantDao"%>
<%@page import="Packages.Plant"%>
<%@page import="Packages.Plant"%>
<%@page import="Packages.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbacess.OrderDao"%>
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
            <%@include file="header.jsp" %>
        </header>
        <section>
            <a href="viewCart.jsp">go back</a>
            <%
                String pid = request.getParameter("plantid");
                Plant plant=PlantDao.getPlant(pid);

            %>
            <table class='plant'>
                <tr>
                    <td><img src='<%=plant.getImgPath()%>' class='planting' id="image"/></td>
                    <td>Product ID:<%=plant.getPid()%></td>
                    <td>Product Name:<%=plant.getName()%></td>
                    <td>Price:<%=plant.getPrice()%></td>
                    <td>Status:<%=plant.getStatus()%></td>
                    <td>Category:<%=plant.getCateName()%></td>
                   
                </tr>
            </table>
            
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
</html>