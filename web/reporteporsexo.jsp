<%-- 
    Document   : listadoUsuarios
    Author     : Guillermo, Yvan
--%>
<%@page import="entidad.Reporte"%>
<%@page import="entidad.Variable"%>
<%@page import="model.ModelVariable"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>      
        <title>Análisis de variables por Sexo</title>
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
                    <a href="#">
                       <i class="fa fa-info fa-2x"></i>
                        <span class="nav-text">
                            Vacio        
               
               
    
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

            <jsp:useBean id="mvariable" class="model.ModelVariable" scope = "page"></jsp:useBean>
                <%
                    List<Variable> variables = mvariable.Listar();
                %>

               
               <br>
               <br>
                <br>
                 
            <form name ="reporte2" action = "reporte">
                 <label> SINDROMES </label>
                <br>
                
                <select name="Variable" id="Variable">
                <br>
                <br>
                <option>Seleccione Variable</option>
                   <% for (Variable variable : variables) { %>
                   <option value="<%=variable.getCodvarfin() %>"><%=variable.getCodvarfin() %></option>
                   <% } %>
               </select>
                  <input type ="hidden" name ="metodo" value ="lista1">
                <input type ="submit" value ="Visualizar Reporte" name ="btnver" />
                <input type ="submit" value ="Exportar PDF" name ="btnpdf" />
            </form>        
        </aside>
               
        <aside class="servicios">
            <div>
                <h3>REPORTE GENERADO </h3>
                <br>                                
                <table class="table">
                    <tr>
                        <th>Escala/Sexo</th> 
                        <th>Masculino</th> 
                        <th>Femenino</th> 
                        <th>Total</th> 
                    </tr>
                <%        
                    List<Reporte> data = (List<Reporte>)request.getAttribute("data_reporte_sexo");
                    if(data!=null){
                       for(Reporte x:data){
                %>
                            
                    <tr>
                        <td> <%= x.getCodresultado() %> </td>
                        <td> <%= x.getCta_masculino() %> </td>
                        <td> <%= x.getCta_femenino() %> </td>
                        <td> <%= x.getCta_total() %> </td>
                    </tr>
                <%    
                            }             
                    }
                %>
                </table>
           </div>
         </aside>           
        </div>          
    </body>
</html>
