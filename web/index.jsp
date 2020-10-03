<%-- 
    Document   : login
    Author     : Guillermo, Yvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	
	<title>Login|Estadistico 1.0</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width-device-width, user-scaleble=no, initial-scale=1, maximum-scale=1, minimun-scale=1">
	<link rel="stylesheet" type="text/css" href="estilos/css/logincss.css">
        <script
                src="https://code.jquery.com/jquery-3.4.1.min.js"
                integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
                crossorigin="anonymous">
       </script>
        <script type="text/javascript"
                src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js">                  
        </script>
    </head>
    
    <body>

    <form action="usuario" method = "post" id ="loginForm">
        <input type ="hidden" name ="metodo" value ="busca">
        <a href="index.jsp"> <img id="img2" src="estilos/imagenes/home48px.png"></a>
        <center>
 	<img src="estilos/imagenes/logo1.png" width="200" height="150">
 	</center>
 		<h2> Bienvenido a Estadistico 1.0</h2>
 		<input type="text" placeholder="&#128272; Usuario" name="idusuario">
 		<input type="password" placeholder="&#128272; Contraseña" name="password">
                <br>${message}
                <input type="submit" name="acceder" value="Ingresar"> <%---FALTA VALIDAR <a solo para probar ingreso>---%>
    </form>
    <footer>
	Derechos Reservados - <font face="Comic Sans MS"> Estadistico 1.0</font>, Copyright &copy;2020
	<br>
	Lima-Perú <img src="estilos/imagenes/peru.jpg" width="10" height="10">
    </footer>
</body>

<script type="text/javascript">

	$(document).ready(function() {
		$("#loginForm").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
		
				password: "required",
			},
			
			messages: {
				email: {
					required: "Por favor ingrese correo",
					email: "Por favor ingrese un correo válido"
				},
				
				password: "Por favor ingrese contraseña válida"
			}
		});

	});
</script>
</html>
