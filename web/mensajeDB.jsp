<%-- 
    Document   : mensajeDB
    Created on : 10 dic. 2020, 12:27:30
    Author     : yvans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Manejo de Errores de Base de Datos</h1>
        <% String message = (String)request.getAttribute("alertMsg");%>        
        <h4> ${alertMsg}</h4>
    </body>
    <script type="text/javascript">
        var msg = "<%=message%>";
        alert(msg);
    </script>
</html>
