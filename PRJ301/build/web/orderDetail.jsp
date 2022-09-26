<%-- 
    Document   : OrderDetail
    Created on : Jun 14, 2022, 10:07:49 PM
    Author     : ACER
--%>

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
        <%
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>
        <p><font color="red">you must login to view personal page</font></p>
        <p></p>
        <%  } else {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> come back</h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
            <a href="personalPage.jsp">view all order</a>
        </section>
        <section>
            <%
                int money=0;
                String orderid = request.getParameter("orderid");
                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid);
                    OrderDetail detail= OrderDao.getOrderDetail(orderID);
            %>
            <table class='order'>
                <tr><td>Detail ID</td><td>Plant ID</td><td>Plant Name</td><td>Image</td><td>quantity</td></tr>
                <tr>
                    <td><%= detail.getOrderDetailID()%></td>
                    <td><%= detail.getFID()%></td>
                    <td><%= detail.getPlantName()%></td>
                    <td><img src="<%= detail.getImgPath()%>" class="planting" id="image"/>
                    <td><%= detail.getQuantity()%>
                    </td>
                    <% money = money + detail.getPrice() * detail.getQuantity();%></tr>
            </table>
            <h3> Total money: <%= money%></h3>
            <% } else {%>
            <p>You don't have any order</p>
            <%}
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
</html>