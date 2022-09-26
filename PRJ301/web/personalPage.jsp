    <%@page import="Packages.Account"%>
<%@page import="dbacess.AccountDao"%>
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
        <%
            String name= (String)session.getAttribute("name");
            String email=(String)session.getAttribute("email");
            Cookie[] c=request.getCookies();
            boolean login=false;
            if(name==null){
                String token="";
                for(Cookie aCookie:c){
                    if(aCookie.getName().equals("selector")){
                        token= aCookie.getValue();
                        Account acc=AccountDao.getAccount(token);
                        if (acc!=null){
                            name=acc.getFullname();
                            email=acc.getEmail();
                            login=true;
                        }
                    }
                }
            }else{
                
            }
            if(login){
        %>
        <p><font color="red">you must <a href="login.jsp">login</a> to view personal page</font></p>
        <p></p>
        <%}else{
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%=name%> come back </h3>
            <h3><a href="mainController?action=logout"></a>Log out</h3>
            
            <%   
                int statusOfOrder=0;
                
                if(request.getParameter("status")==null){
                    statusOfOrder=0;
                }else
                    statusOfOrder=(Integer.parseInt(request.getParameter("status")));
                ArrayList<Order> list;
                if (statusOfOrder==0)
                list=OrderDao.getOrders(email);
                else{
                    list=OrderDao.getOrders(email,statusOfOrder);
                }
                String[] status={"","processing","completed","canceled"};
                if(list!=null && !list.isEmpty()){
                    for (Order ord: list){
            %>
            <table class="order">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                <tr><td><%=ord.getOrderID()%></td>
                    <td><%=ord.getOrderDate()%></td>
                    <td><%=ord.getShipDate()%></td>
                    <td><%=status[ord.getStatus()]%>
                <br/><%if(ord.getStatus()==1){%>    
                <a href="mainController?action=cancelOrder&orderID=<%=ord.getOrderID()%>">cancel order</a>  
                <%}%>
                <br/><%if(ord.getStatus()==2){%>    
                <a href="mainController?action=orderAgain&orderID=<%=ord.getOrderID()%>">order again</a>  
                <%}%>
                
            </td>
                <td><a href="orderDetail.jsp?orderid=<%=ord.getOrderID()%>">detail</a></td></tr>
            </table>
             <%}
            }
}
            %>
            
        </section>
            <footer>
                <%@include file="footer.jsp"%>
            </footer>
           
    </body>
</html>
