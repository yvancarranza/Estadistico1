<%-- 
    Document   : listadoUsuarios
    Author     : Guillermo, Yvan
--%>

<%@page import="java.util.List"%>
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
    </head>
   
    <body>
        <header>
		<div class="contenedor">
                    <div id="marca">
                        <img src="estilos/imagenes/logo1.png" width="120px" height="120px" >     
                    </div>
                        
                    <div id="marca">
                            <h1><span style="color: #830C32;">Estadistico</span>1.0</h1>
                            <small style="color: green">Servicio de Reportería</small>  
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
                  <li>
                    <a href="servicios.jsp">
                        <i class="fa fa-home fa-2x"></i>
                        <span class="nav-text">
                            Bienvenido
                        </span>
                    </a>    
                </li>
               
                <li>
                    <a href="personal?metodo=lista">
                        <i class="fa fa-font fa-2x"></i>
                        <span class="nav-text">
                           Lista de Usuarios
                        </span>
                    </a>
                </li>
             
                
                <li>
                    <a href="#">
                       <i class="fa fa-info fa-2x"></i>
                        <span class="nav-text">
                            Vacio        
               
                <li>
                    <a href="importarEncuesta.jsp">
                        <i class="fa fa-font fa-2x"></i>
                        <span class="nav-text">
                           Importar Encuesta
                        </span>
                    </a>
                </li>
              
    
            </ul>

            <ul class="logout">
                <li>
                   <a href="index.jsp">
                         <i class="fa fa-power-off fa-2x"></i>
                        <span class="nav-text">
                                Salir
                        </span>
                    </a>
                </li>  
            </ul>
        </nav>
       
         <aside class="servicios">
			<div>
				<h3>Listado de Usuarios...</h3>
				<p style="text-align: justify;">Aquí podras gestionar los usuarios registrados.</p>
                                
              
                                <br>
                                
                                <table class="table">
                                    <tr>
                                        <th>Id</th> <th>Nombre</th> <th>Apellido</th> 
                                        <th>DNI</th> <th>Correo</th> <th>Teléfono</th> <th>Género</th>
                                    </tr>
                            <%        
                               List<Personal> data = (List<Personal>)request.getAttribute("data");
                               
                                if(data!=null){
                                    for(Personal x:data){
                            %>
                            
                            <tr>
                                <td> <%= x.getIdpersonal() %> </td>
                                <td> <%= x.getNombres() %> </td>
                                <td> <%= x.getApellidos() %> </td>
                                <td> <%= x.getDni() %> </td>
                                <td> <%= x.getCorreo() %> </td>
                                <td> <%= x.getTelefono() %> </td>
                                <td> <%= x.getGenero() %> </td>
                                
                                <td>
                                    <a href="personal?metodo=busca&id=<%= x.getIdpersonal() %>">EDITAR </a>
                                </td>  
                                <td>
                                      <a href="personal?metodo=elimina&id=<%= x.getIdpersonal() %>">ELIMINAR </a>
                                </td>
                                
                            </tr>
                            <%    
                            }             
                                }
                            %>

                                </table>
			</div>
	<a href="registroUsuario.jsp"><input type="button" name="registroUsuario" value="Agregar Usuario"></a>
         </aside>
        
        </div>
       
       
    </body>
</html>
