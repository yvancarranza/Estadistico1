<%-- 
    Document   : servicios
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>      
        <title>Usuario Estadístico</title>
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
               
                <li>
                    <a href="#">
                        <i class="fa fa-font fa-2x"></i>
                        <span class="nav-text">
                           Editar Perfil
                        </span>
                    </a>
                </li>
                
                <li>
                    <a href="#">
                       <i class="fa fa-info fa-2x"></i>
                        <span class="nav-text">
                            Vacio
                        </span>
                    </a>
                </li>
                <li>
                    <a href="Reporte1.jsp">
                        <i class="fa fa-font fa-2x"></i>
                        <span class="nav-text">
                           Reporte de Variable 1
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
				<h3>BIENVENIDO USUARIO...</h3>
				<p style="text-align: justify;">Aquí podrás visualizar los reportes para análisis de variable</p>
                                <p>También podrás editar tu perfil.</p>
                                
			</div>
	</aside>
        
        </div>
   
       
        
    </body>
         
</html>
