<%-- 
    Document   : perfilpa
    Created on : 11/11/2019, 10:26:40 PM
    Author     : Guillermo Gonzales , Yvan Carranza
--%>

<%@page import="entidad.Personal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>       
        <title>Servicios</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
	<meta name="description" content="pagina venta server">
	<meta name="keywords" content="server">
	<meta name="autor" content="Yvan , Guillermo"> 
	<link rel="stylesheet" type="text/css" href="estilos/css/estilos.css">
        <link rel="stylesheet" type="text/css" href="estilos/css/estiloMenu.css">
        <link rel="stylesheet" type="text/css" href="estilos/css/estiloServicios.css">
        <link rel="stylesheet" type="text/css" href="estilos/css/perfilcss.css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans|Montez|Pathway+Gothic+One" rel="stylesheet">
    </head>
    
    <body>
          <% 
                   String tipousuario;
                   tipousuario = (String)session.getAttribute("tipousuario");
                    if(tipousuario == null || tipousuario.isEmpty() ){
                          response.sendRedirect("index.jsp");
                   }
       %>
         <header>
		<div class="contenedor">
                    <div id="marca">
                        <img src="estilos/imagenes/logo1.png" width="120px" height="120px" >     
                    </div>
                        
                    <div id="marca">
                            <h1><span style="color: #830C32;">Sana</span>Mente</h1>
                            <small style="color: green">Servicio de reportería estadística</small>  
                    </div>                  
                        <nav>
				<ul>
					<li> <a href="index.jsp">Inicio</a></li>
					<li class="actual"> <a href="servicios.jsp">Servicios</a></li>
                                        <li> <a href="noticias.jsp">Noticias</a></li>
				</ul>
			</nav>           
		</div>	
	</header>
        
        <div class="contenedor">
        <div class="area"></div>
        
        <nav class="main-menu">
            <ul>
                
                <ul>
                <li>
                    <a href="javascript:history.back()">
                        <i class="fa fa-home fa-2x"></i>
                        <span class="nav-text">
                            Retroceder
                        </span>
                    </a>    
                </li>
                </ul>
            </ul>   
                
        </nav>
      
         <aside class="fondo" >
                        <div>
                            <h3>Modificar Usuario</h3>
                            <a href="javascript:history.back()"> <img src="estilos/imagenes/atras40px.png"></a>
                            <br>
                            <% Personal a = (Personal) request.getAttribute("data"); %>
                            <form action="personal" method = "post">
                                <input type ="hidden" name ="metodo" value ="actualiza">
                                <input type ="hidden" name ="idpersonal" value ="<%=a.getIdpersonal()%>">
                                <center><img src="estilos/imagenes/perfil.jpg" width="30%" height="30%"></center>
                                    <input type="file" name="uploadfoto" value="subir foto">
                                <br>
                                <center><h2>Datos de Perfil</h2></center>
                                <br>
                                    <span class="label"> Nombres</span>
                                    <input class="control" type="text" name="nombres"  placeholder="Nombres" value = "<%=a.getNombres()%>">
                                    <span> Apellidos</span>
                                    <input class="control" type="text" name="apellidos" placeholder="Apellidos" value = "<%=a.getApellidos()%>">
                                    <label> Número de teléfono</label>
                                    <input class="control" type="text" name="telefono" placeholder="Número de Teléfono" value = "<%=a.getTelefono()%>">
                                    <label> Sexo</label>
                                <select name="genero"  >
                                        <option selected="selected"> <%=a.getGenero()%></option>
                                        <option value="Hombre">Hombre</option>
                                        <option value="Mujer">Mujer</option>
                                <label> DNI</label>
                                <input class="control" type="text" name="dni" placeholder="DNI" value = "<%=a.getDni()%>">
                                <label> Dirección</label>
                                <input class="control" type="text" name="direccion" placeholder="DIRECCION" value = "<%=a.getDireccion()%>">
                                <label> Distrito</label>
                                <select name="ubigeo" >
                                    <option value="<%=a.getUbigeo()%>" selected="selected"><%=a.getUbigeo()%></option>
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
                                 <label> Dirección de Correo</label>
                                <input class="control" type="text" name="correo" placeholder="Correo Electrónico" readonly="true" value = "<%=a.getCorreo()%>">
                                <label> Contraseña</label>
                                <input class="control" type="password" name="password" placeholder="Password">
                                    
                                    <center><input type="submit" value="Actualizar Datos"></center>
                            </form>
			</div>
	</aside>
        
        </div>
        
    </body>
    
</html>
