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
                <h3> <center>REPORTE GENERADO</center> </h3>
                
                <h4>${variable_analisis}</h4>
                <table class="table">
                    <tr>
                        <th>Escala/Sexo</th> 
                        <th>Masculino</th> 
                        <th>Femenino</th> 
                        <th>Total</th> 
                    </tr>
                <%        
                    double total_masculino = 0.00;
                    double total_femenino = 0.00;
                    double total_grupo    = 0.00;
                    
                    double porcen_masculino = 0.00;
                    double porcen_femenino = 0.00;
                    double porcen_grupo = 0.00;                    

                    List<Reporte> data = (List<Reporte>)request.getAttribute("data_reporte_sexo");
                    if(data!=null){
                       for(Reporte x:data){
                           if(x.getCodresultado().equalsIgnoreCase("TOTAL") )
                           {
                               total_masculino = x.getCta_masculino();
                               total_femenino = x.getCta_femenino();
                               total_grupo    = x.getCta_total();
                               
                            %>
                                <tr>
                                    <td> TOTAL UNIDADES </td>
                                    <td> <%= x.getCta_masculino() %> </td>
                                    <td> <%= x.getCta_femenino() %> </td>
                                    <td> <%= x.getCta_total() %> </td>
                                </tr>        
                            <%
                           }
                           else
                           {
                                
                                porcen_masculino = (x.getCta_masculino().doubleValue()/total_grupo)*100;
                                porcen_femenino  = (x.getCta_femenino().doubleValue()/total_grupo)*100;
                                porcen_grupo     = (x.getCta_total().doubleValue()/total_grupo)*100;

                               if(x.getCodvarfin().equalsIgnoreCase("RESILIENCIA")) {
                %>
                            
                    <tr>
                        <td> <%= x.getCodresultado() %> </td>
                        <td> <%= String.format("%.2f", x.getCta_masculino().doubleValue()) %>  </td>
                        <td> <%= String.format("%.2f",x.getCta_femenino().doubleValue()) %>  </td>
                        <td> <%= String.format("%.2f",x.getCta_total().doubleValue()) %>  </td>
                    </tr>
                <%    
                    }
                    else {
                            
                 %>
                  <tr>
                        <td> <%= x.getCodresultado() %> </td>
                        <td> <%= String.format("%.2f",porcen_masculino) %> % </td>
                        <td> <%= String.format("%.2f",porcen_femenino) %> % </td>
                        <td> <%= String.format("%.2f",porcen_grupo) %> % </td>
                    </tr>
                 
                 <%
                            
                            }
                            }
      
                    }

                        porcen_masculino = (Double.valueOf(total_masculino)/total_grupo)*100;
                        porcen_femenino  = (Double.valueOf(total_femenino)/total_grupo)*100;                        
                %>
                    <tr>
                        <th> TOTAL PORCENTAJE </th>
                        <th> <%= String.format("%.2f",porcen_masculino) %> % </th>
                        <th> <%= String.format("%.2f",porcen_femenino) %> % </th>                        
                        <th> 100 % </th>
                    </tr>
                <%
                    }
                %>
                </table>
           </div>
         </aside>           
        </div>          
    </body>
</html>
