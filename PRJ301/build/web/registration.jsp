<%-- 
    Document   : registration
    Created on : Jun 4, 2022, 10:33:54 PM
    Author     : NCC
--%>

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
            <form action="mainController" method="post" class="form">
                <h1>Register</h1>
                <table>
                    <tr><td>email</td><td><input type="text" name="txtemail" required="" value="<%=(request.getAttribute("txtemail")==null)?"":request.getAttribute("txtemail")%>"></td></tr>
                    <tr><td>full name</td><td><input type="text" name="txtfullname" required="" value="<%=(request.getAttribute("txtfullname")==null)?"":request.getAttribute("txtfullname")%>"></td></tr>
                    <tr><td>password</td><td><input type="password" name="txtpassword" required=""></td></tr>  
                    <tr><td>phone</td><td><input type="text" name="txtphone" required="" value="<%=(request.getAttribute("txtphone")==null)?"":request.getAttribute("txtphone")%>">
                            <%=(request.getAttribute("ERROR")==null)?"":request.getAttribute("ERROR")%></td></tr>
                    <tr><td colspan="2"><input type="submit" name="action" value="register"></td></tr>  
            </table>
            </form>
        </section>
         <footer>
            <%@include file="footer.jsp"%>
        </footer>
    </body>
</html>
