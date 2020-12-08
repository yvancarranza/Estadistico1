<%-- 
    Document   : registroUsuario
    Author     : Guillermo Gonzales , Yvan Carranza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta charset="utf-8">
	<title>Registro Cliente</title>
	<meta name="viewport" content="width-device-width, user-scaleble=no, initial-scale=1, maximum-scale=1, minimun-scale=1">
	<link rel="stylesheet" type="text/css" href="estilos/css/clientecss.css">
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
      <% 
                   String tipousuario;
                   tipousuario = (String)session.getAttribute("tipousuario");
                    if(tipousuario == null || tipousuario.isEmpty() ){
                          response.sendRedirect("index.jsp");
                   }
       %>
                
        <form action="personal" method="post" id ="personal">
        <input type ="hidden" name ="metodo" value ="registra">
    
        <section>
            <a href="index.jsp"> <img id="img2" src="estilos/imagenes/home48px.png"></a>
            <center>
                <img src="estilos/imagenes/logo1.png" width="150" height="150">
            </center>
            <h4><em>Complete el Registro</em></h4>
                <input class="control" type="text" name="nombres" placeholder="Nombres">
                <input class="control" type="text" name="apellidos" placeholder="Apellidos">               
                <input class="control" type="text" name="dni" placeholder="Número de DNI">
            <h3>Género:</h3>
            <select name="genero">
                    <option value="Hombre">Hombre</option>
                    <option value="Mujer">Mujer</option>
            </select>            
            <h3>Tipo de Usuario:</h3>
            <select name="tipousuario">
                    <option value="Personal">Personal</option>
                    <option value="Admin">Admin</option>
            </select>            
            <input class="control" type="text" name="telefono" placeholder="Número de Teléfono">
           
            <h3>Selecione Distrito: </h3> 
            <select name="ubigeo" >
               <option value="Ancón">Ancón</option> 
                    <option value="Ate Vitarte">Ate Vitarte</option> 
                    <option value="Barranco">Barranco</option>
                    <option value="Breña">Breña</option> 
                    <option value="Carabayllo">Carabayllo</option> 
                    <option value="Chorrillos">Chorrillos</option> 
                    <option value="Comas">Comas</option> 
                    <option value="Independencia">Independencia</option> 
                    <option value="Jesus Maria">Jesus Maria</option> 
                    <option value="Los Olivos">Los Olivos</option> 
                    <option value="San Miguel">San Miguel</option> 
                    <option value="Lince">Lince</option> 
                    <option value="La Molina">La Molina</option> 
                    <option value="Surco">Surco</option> 
                    <option value="Lima">Lima</option> 
                    <option value="Magdalena del Mar">Magdalena del Mar</option>
                    <option value="San Isidro">San Isidro</option> 
                    <option value="San Borja">San Borja</option> 
                    <option value="Surquillo">Surquillo</option>   
            </select>
            <h3>Datos de tu cuenta:</h3>
            <input class="control" type="text" name="correo" placeholder="*Correo Electrónico">   
            <input class="control" type="password" name="password" placeholder="*Ingrese una clave">
            <small>*Ten en cuenta que estos datos serán usados para tu inicio de sesión</small>
            <br>${message}
            <input type="submit" name="regist" value="Registrar">

            </section>
        </form>
        <footer>
            Derechos Reservados - <font face="Comic Sans MS"> Estadistico 1.0</font>, Copyright &copy;2020
            <br>
            Lima-Perú <img src="estilos/imagenes/peru.jpg" width="10" height="10">
        </footer>
    </body>
    
</html>
