    <%-- 
    Document   : index
    Created on : Jun 2, 2022, 4:01:54 PM
    Author     : ACER
--%>

<%@page import="dbacess.PlantDao"%>
<%@page import="Packages.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
        <link rel="stylesheet" href="mycss.css" type=text/css">
    </head>
    <body>
        <header>
            <%@include file="header.jsp"%>
        </header>
        <section>
            <%
                String keyword = request.getParameter("txtsearch");
                String searchby = request.getParameter("searchby");
                ArrayList<Plant> list;
                String[] tmp = {"out of stock", "availble"};
                if (keyword == null && searchby == null) {
                    list = PlantDao.getPlants("", "");
                } else {
                    list = PlantDao.getPlants(keyword, searchby);
                }
                if (list != null && !list.isEmpty()) {
                    for (Plant plant : list) {
            %>
            <table class='plant'>
                <tr>
                    <td><img src='<%=plant.getImgPath()%>' class='planting' id="image"/></td>
                    <td>Product ID:<%=plant.getPid()%></td>
                    <td>Product Name:<%=plant.getName()%></td>
                    <td>Price:<%=plant.getPrice()%></td>
                    <td>Status:<%=tmp[plant.getStatus()]%></td>
                    <td>Category:<%=plant.getCateName()%></td>
                    <td><a href='mainController?action=addtocart&pid=<%=plant.getPid()%>'>add to cart</a></td>
                </tr>
            </table>
            <%
                    }
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp"%>
        </footer>
    </body>
</html>