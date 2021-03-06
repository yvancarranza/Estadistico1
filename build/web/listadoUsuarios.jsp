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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
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
                    <a href="personal?metodo=busca">
                        <i class="fa fa-archive fa-2x"></i>
                        <span class="nav-text">
                           Modificar Perfil
                        </span>
                    </a>
                </li>
                
               <% 
                   String tipousuario;
                   tipousuario = (String)session.getAttribute("tipousuario");
                   if(tipousuario == null){
                       tipousuario = "";
                   }                   
                   if(tipousuario.equalsIgnoreCase("Admin")) {
                %>
                <li>
                    <a href="personal?metodo=lista">
                        <i class="fa fa-user fa-2x"></i>
                        <span class="nav-text">
                           Lista Usuarios
                        </span>
                    </a>
                </li>
                
                <li>
                    <a href="importarEncuesta.jsp">
                        <i class="fa fa-files-o fa-2x"></i>
                        <span class="nav-text">
                           Importar Encuesta
                        </span>
                    </a>
                </li>
                <%
                    }else{
                       if(tipousuario == null || tipousuario.isEmpty() ){
                          response.sendRedirect("index.jsp");
                       }
                   }
                 %>
                
                <li>
                    <a href="reporteporsexo.jsp">
                        <i class="fa fa-bar-chart-o fa-2x"></i>
                        <span class="nav-text">
                           Reporte x Sexo
                        </span>
                    </a>
                </li>
                
                <li>
                    <a href="reporteporregion.jsp">
                        <i class="fa fa-table fa-2x"></i>
                        <span class="nav-text">
                           Reporte x Region
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
			  <div class="table-responsive">
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
                                    <a class="btn btn-info" role="button"  href="personal?metodo=busca&id=<%= x.getIdpersonal() %>">EDITAR </a>
                                </td>  
                                <td>
                                      <a a class="btn btn-danger" role="button" href="personal?metodo=elimina&id=<%= x.getIdpersonal() %>">ELIMINAR </a>
                                </td>
                                
                            </tr>
                            <%    
                            }             
                                }
                            %>

                                </table>
			</div>
	<a href="registroUsuario.jsp"><input class="btn btn-success" type="button" name="registroUsuario" value="Agregar Usuario"></a>
         </aside>
        
        </div>
       
       
    </body>
</html>
