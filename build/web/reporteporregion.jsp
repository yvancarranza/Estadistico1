<%-- 
    Document   : listadoUsuarios
    Author     : Guillermo, Yvan
--%>
<%@page import="org.owasp.esapi.ESAPI"%>
<%@page import="entidad.Reporte"%>
<%@page import="entidad.Variable"%>
<%@page import="model.ModelVariable"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
     <head>      
        <title>Análisis de variables por Región</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
	<meta name="description" content="Estadistico">
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
                <input type ="hidden" name ="metodo" value ="region">
              <%-- Selector lo coloqué en un div ggl--%>
              <h4><center> GENERADOR DE REPORTE </center></h4>
              <br>
              <div class="form-row align-items-center ml-5">
                    <div class="col-auto">
                      <%-- <label class="mr-sm-2" for="Variable">Síndrome</label>--%>
                      <select class="custom-select mr-sm-2" name="Variable" id="Variable" required>
                          <%-- agregue valor "" primera option/js ggl--%>
                          <option value="">Seleccione Sindrome</option>
                         <% for (Variable variable : variables) { %>
                         <option c:out value="<%= variable.getCodvarfin() %>"><%=variable.getCodvarfin() %></option>
                         <% } %>
                      </select>
                    </div>
                     <div class="col-auto">
                         <input type ="hidden" name ="metodo" value ="lista1">
                        <%-- Cambié a boton ggl--%>
                        <button class="btn btn-primary mr-5 ml-5 btn-lg" type ="submit" value ="Visualizar Reporte" name ="btnver">
                            Generar
                        </button>
                        <button class="btn btn-danger btn-lg" type ="submit" value ="Exportar PDF" name ="btnpdf">
                            Exportar PDF
                        </button>
                     </div>
               </div>
              <br>
              <%-- Es una barra q da la impresión que carga (si crees q no va, lo sacas) ggl--%>
              <div class="progress">
                <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
              </div>
               
                
            </form>        
        </aside>
               
        <aside class="servicios">
            <div>
                <h4> <center>RESULTADO</center> </h4>
                
                <h4 > <c:out value="${variable_analisis}"></c:out></h4>
                <table class="table">
                    <tr>
                        <th>Región/Escala</th> 
                         <%
                            String var_codvarfin = (String)request.getAttribute("mvarfinal");
                            List<Variable> indicadores = mvariable.ListarIndicador(var_codvarfin);
                         %>
                         <% for (Variable variable : indicadores) { %>
                        <th> <c:out value="<%= variable.getCodresultado()%>" > </c:out></th>
                         <% } %>
                        <th>Total</th> 
                    </tr>
                <%        
                    double total_ninguno = 0.00;
                    double total_bajo = 0.00;
                    double total_normal = 0.00;
                    double total_medio = 0.00;
                    double total_moderado = 0.00;
                    double total_alta = 0.00;
                    double total_severo = 0.00;
                    double total_extremo = 0.00;
                    double total_grupo    = 0.00;
                    
                    double porcen_ninguno = 0.00;
                    double porcen_bajo = 0.00;
                    double porcen_normal = 0.00;
                    double porcen_medio = 0.00;
                    double porcen_moderado = 0.00;
                    double porcen_alta = 0.00;
                    double porcen_severo = 0.00;
                    double porcen_extremo = 0.00;
                    double porcen_grupo = 0.00;                    

                    List<Reporte> data = (List<Reporte>)request.getAttribute("data_reporte_region");
                    
                    if(data!=null){
                       for(Reporte x:data){
                           if(x.getDepartamento().equalsIgnoreCase("TOTAL") )  {
                               total_ninguno = x.getCta_ninguno();
                               total_bajo = x.getCta_bajo();
                               total_normal = x.getCta_normal();
                               total_medio = x.getCta_medio();
                               total_moderado = x.getCta_moderado();
                               total_alta = x.getCta_alta();
                               total_severo = x.getCta_severo();
                               total_extremo =  x.getCta_extremo();
                               
                               total_grupo    = x.getCta_total();
                               
                            %>
                                <tr>
                                    <td> TOTAL UNIDADES </td>
                                    <% for (Variable variable : indicadores) { %>
                                    <% if(variable.getCodresultado().equalsIgnoreCase("Ninguno")){ %>
                                        <td> <%= String.format("%.0f",x.getCta_ninguno()) %> </td>
                                    <%}%>
                                   <% if(variable.getCodresultado().equalsIgnoreCase("Bajo")) {%>
                                        <td> <%= String.format("%.0f",x.getCta_bajo()) %> </td>
                                   <%}%>
                                   <% if(variable.getCodresultado().equalsIgnoreCase("Normal")) { %>
                                        <td> <%= String.format("%.0f",x.getCta_normal()) %> </td>
                                   <%}%>
                                   <% if(variable.getCodresultado().equalsIgnoreCase("Medio")) {%>
                                        <td> <%= String.format("%.0f",x.getCta_medio()) %> </td>                                        
                                   <%}%>
                                   <% if(variable.getCodresultado().equalsIgnoreCase("Moderado")) {%>
                                        <td> <%= String.format("%.0f",x.getCta_moderado()) %> </td>
                                   <%}%>
                                   <% if(variable.getCodresultado().equalsIgnoreCase("Alta")) {%>
                                        <td> <%= String.format("%.0f",x.getCta_alta()) %> </td>
                                   <%}%>                                        
                                   <% if(variable.getCodresultado().equalsIgnoreCase("Severo")) {%>
                                        <td> <%= String.format("%.0f",x.getCta_severo()) %> </td>
                                   <%}%>
                                   <% if(variable.getCodresultado().equalsIgnoreCase("Extremadamente")) {%>
                                        <td> <%= String.format("%.0f",x.getCta_extremo()) %> </td>
                                   <%}%>
                                  v<% } %>                                    
                                    <td> <%= String.format("%.0f",x.getCta_total()) %> </td>
                                </tr>        
                            <%
                           }
                           else
                           {
                                
                                porcen_ninguno = (x.getCta_ninguno()/total_grupo)*100;
                                porcen_bajo  = (x.getCta_bajo()/total_grupo)*100;
                                porcen_normal  = (x.getCta_normal()/total_grupo)*100;
                                porcen_medio  = (x.getCta_medio()/total_grupo)*100;
                                porcen_moderado = (x.getCta_moderado()/total_grupo)*100;
                                porcen_alta = (x.getCta_alta()/total_grupo)*100;
                                porcen_severo  = (x.getCta_severo()/total_grupo)*100;
                                porcen_extremo  = (x.getCta_extremo()/total_grupo)*100;

                                porcen_grupo     = (x.getCta_total()/total_grupo)*100;

                               if(x.getTipovar().equalsIgnoreCase("MEDIA") ) {
                %>
                            
                    <tr>
                        <td> <%= x.getDepartamento() %> </td>
                            <% for (Variable variable : indicadores) { %>
                            <% if(variable.getCodresultado().equalsIgnoreCase("Ninguno")) {%>
                                <td> <%= String.format("%.2f",x.getCta_ninguno()) %> </td>
                            <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Bajo")) {%>
                                <td> <%= String.format("%.2f",x.getCta_bajo()) %> </td>
                            <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Normal")) {%>
                                <td> <%= String.format("%.2f",x.getCta_normal()) %> </td>
                            <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Medio")) {%>
                                <td> <%= String.format("%.2f",x.getCta_medio()) %> </td>
                            <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Moderado")) {%>
                                <td> <%= String.format("%.2f",x.getCta_moderado()) %> </td>
                            <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Alta")) {%>
                                <td> <%= String.format("%.2f",x.getCta_alta()) %> </td>
                            <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Severo")) {%>
                                <td> <%= String.format("%.2f",x.getCta_severo()) %> </td>
                            <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Extremadamente")) {%>
                                <td> <%= String.format("%.2f",x.getCta_extremo()) %> </td>
                            <%}%>

                                <% } %>
                        <td> <%= String.format("%.2f",x.getCta_total()) %>  </td>
                    </tr>
                <%    
                    }
                    else {
                            
                 %>
                  <tr>
                        <td> <%= x.getDepartamento() %> </td>
                            <% for (Variable variable : indicadores) { %>
                            <% if(variable.getCodresultado().equalsIgnoreCase("Ninguno")) {%>
                                <td> <%= String.format("%.2f",porcen_ninguno) %> % </td>
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Bajo")) {%>
                                <td> <%= String.format("%.2f",porcen_bajo) %> % </td>
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Normal")) {%>
                                <td> <%= String.format("%.2f",porcen_normal) %> % </td>
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Medio")) {%>
                                <td> <%= String.format("%.2f",porcen_medio) %> % </td>  
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Moderado")) {%>
                                <td> <%= String.format("%.2f",porcen_moderado) %> % </td>
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Alta")) {%>
                                <td> <%= String.format("%.2f",porcen_alta) %> % </td>  
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Severo")) {%>
                                <td> <%= String.format("%.2f",porcen_severo) %> % </td>
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Extremadamente")) {%>
                                <td> <%= String.format("%.2f",porcen_extremo) %> % </td>
                              <%}%>

                                <% } %>
                        
                        <td> <%= String.format("%.2f",porcen_grupo) %> % </td>
                    </tr>
                 
                 <%
                            
                            }
                            }
      
                    }

                        porcen_ninguno = (Double.valueOf(total_ninguno)/total_grupo)*100;
                        porcen_bajo  = (Double.valueOf(total_bajo)/total_grupo)*100;                        
                        porcen_normal  = (Double.valueOf(total_normal)/total_grupo)*100;                        
                        porcen_medio  = (Double.valueOf(total_medio)/total_grupo)*100;                        
                        porcen_moderado  = (Double.valueOf(total_moderado)/total_grupo)*100;                        
                        porcen_alta  = (Double.valueOf(total_alta)/total_grupo)*100;                        
                        porcen_severo  = (Double.valueOf(total_severo)/total_grupo)*100;                        
                        porcen_extremo  = (Double.valueOf(total_extremo)/total_grupo)*100;                        


                %>
                    <tr>
                        <th> TOTAL PORCENTAJE </th>
                         <% for (Variable variable : indicadores) { %>
                            <% if(variable.getCodresultado().equalsIgnoreCase("Ninguno")) {%>
                                <th> <%= String.format("%.2f",porcen_ninguno) %> % </th>
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Bajo")) {%>
                                <th> <%= String.format("%.2f",porcen_bajo) %> % </th>
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Normal")) {%>
                                <th> <%= String.format("%.2f",porcen_normal) %> % </th>
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Medio")) {%>
                                <th> <%= String.format("%.2f",porcen_medio) %> % </th>  
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Moderado")) {%>
                                <th> <%= String.format("%.2f",porcen_moderado) %> % </th>
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Alta")) {%>
                                <th> <%= String.format("%.2f",porcen_alta) %> % </th>  
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Severo")) {%>
                                <th> <%= String.format("%.2f",porcen_severo) %> % </th>
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Extremadamente")) {%>
                                <th> <%= String.format("%.2f",porcen_extremo) %> % </th>
                              <%}%>

                                <% } %>

                        <th> 100 % </th>
                    </tr>
                <%
                    }
                %>
                </table>
           </div>
         </aside>           
        </div>          
   
                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    
    </body>
</html>
