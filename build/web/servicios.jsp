<%-- 
    Document   : servicios
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>      
        <title>Servicios Administrador</title>
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
<%-------------------------------------------------------------------------------------------------------------------%>        
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
               <% 
                   String tipousuario;
                   tipousuario = (String)session.getAttribute("tipousuario");
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
			<div>
                            <%
                               String varnombre = (String)request.getAttribute("nomusuario");
                            %>
				<h3>BIENVENIDO ${nomusuario}</h3>
				<p style="text-align: justify;">Aquí podras gestionar y dar mantenimiento a los usuarios registrados
                                    </p>
                                <p>También podrás editar tu perfil.</p>
                                
			</div>
	</aside>
        
        </div>
   
       
        
    </body>
         
</html>
