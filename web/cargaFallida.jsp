<%-- 
    Document   : login
    Author     : Guillermo, Yvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	
	<title>Resultado de Carga</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width-device-width, user-scaleble=no, initial-scale=1, maximum-scale=1, minimun-scale=1">
        <link rel="stylesheet" type="text/css" href="estilos/css/estilos.css">
        <link rel="stylesheet" type="text/css" href="estilos/css/estiloMenu.css">
        <link rel="stylesheet" type="text/css" href="estilos/css/estiloServicios.css">
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

        <h3>
            ! ! ! Se produjo un error en la carga, revise el archivo ! !
            
             <h4> Archivo Seleccionado:  ${fileupload} </h4> 
             
            <a href="importarEncuesta.jsp"><input type="button" name="importarEncuesta" value="Aceptar"></a>
        </h3>
    <footer>
	Derechos Reservados - <font face="Comic Sans MS"> Estadistico 1.0</font>, Copyright &copy;2020
	<br>
	Lima-Perú <img src="estilos/imagenes/peru.jpg" width="10" height="10">
    </footer>
</body>

</html>
