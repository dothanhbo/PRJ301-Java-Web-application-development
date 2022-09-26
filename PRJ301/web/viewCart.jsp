<%-- 
    Document   : viewCart
    Created on : Jul 2, 2022, 8:31:46 PM
    Author     : NCC
--%>

<%@page import="Packages.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbacess.PlantDao"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <link rel="stylesheet" href="mycss.css" type=text/css">
    </head>
    <body>
        <header>
            <%@include file="header.jsp"%>
        </header>
    <section>
<%String name=(String)session.getAttribute("name");
if (name!=null && !name.isEmpty()){
%>
<h3>Welcome <%= session.getAttribute("name") %> come back </h3>
<h3><a href="mainController?action=logout">Log out</a> </h3>
<h3><a href="personalPage.jsp">personal page</a></h3>
<%}%>
<font style='color:red;'><%= (request.getAttribute("WARNING")==null)?"":(String)request.getAttribute("WARNING")%> </font>
        <table width="100%" class="shopping">
            <tr><td>Product id</td><td>image</td><td>price</td><td>quantity</td><td>action</td></tr>
        <%  int price=0;                                                                                                    
            HashMap<String, Integer> cart=(HashMap)session.getAttribute("cart");
            ArrayList<Plant> list=PlantDao.getPlants("", "");
            if(cart!=null){
                Set<String> pids=cart.keySet();
                for (String pid: pids){
                    int quantity=cart.get(pid);
                    int priceOfPlant=0;
                    String image="";
                    for (Plant plant: list){
                        if(plant.getPid()==(Integer.parseInt(pid))){
                           priceOfPlant=plant.getPrice();
                           price+=quantity*priceOfPlant;
                           image=plant.getImgPath();
                    }
                    }
                    
        %>
        
        <form action="mainController" method="post" >
            <tr><td><input type="hidden" value="<%=pid%>" name="pid"><a href="DetailNoLogin.jsp?plantid=<%=pid%>"><%=pid%></a></td>
                <td><img src='<%=image%>' class='planting' id="image"/></td>
                <td><input type="hidden" value="<%=priceOfPlant%>" name="price"><a href="#"><%=priceOfPlant%></a></td>
                <td><input type="number" value="<%=quantity%>" name="quantity"></td>
                <td><input type="submit" value="updatecart" name="action">
                    <input type="submit" value="delete" name="action"></td>
            </tr>
        </form>
                <%}
            }       
            else{
            %>
            <tr><td>Your cart is empty</td></tr>
            <%}%>
            <tr><td>Total money: <%=price%></td></tr>
            <tr><td>Order date: <%=(new Date()).toString()%></td></tr>
            <tr><td>Ship date: N/A</td></tr>
        </table>
    </section>
            <section>
                <form action="mainController" method="post">
                    <input type="submit" value="saveOrder" name="action" class="submitorder">
                </form>
            </section>
    <footer>
            <%@include file="footer.jsp"%>
        </footer>
</html>
