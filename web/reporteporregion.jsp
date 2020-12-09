<%-- 
    Document   : listadoUsuarios
    Author     : Guillermo, Yvan
--%>
<%@page import="java.util.Arrays"%>
<%@page import="entidad.Reporte"%>
<%@page import="entidad.Variable"%>
<%@page import="model.ModelVariable"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>     
        
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
                         <option value="<%=variable.getCodvarfin() %>"><%=variable.getCodvarfin() %></option>
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
                
                <h4 > ${variable_analisis}</h4>
                <table class="table">
                    <tr>
                        <th>Región/Escala</th> 
                         <%
                            String var_codvarfin = (String)request.getAttribute("mvarfinal");

                            if(var_codvarfin == null)
                            {
                                var_codvarfin = "";
                            }
                            List<Variable> indicadores = mvariable.ListarIndicador(var_codvarfin);
                         %>

                         <% for (Variable variable : indicadores) { %>
                        <th> <%= variable.getCodresultado()%> </th>
                        
                            <% } 

                            if(var_codvarfin.equalsIgnoreCase("RESILIENCIA")) { %>
                              <th>Media</th> 
                              <th>Desviación</th>                            
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
                    double total_muyalta = 0.00;
                    double total_severo = 0.00;
                    double total_extremo = 0.00;
                    double total_grupo    = 0.00;
                    
                    double porcen_ninguno = 0.00;
                    double porcen_bajo = 0.00;
                    double porcen_normal = 0.00;
                    double porcen_medio = 0.00;
                    double porcen_moderado = 0.00;
                    double porcen_alta = 0.00;
                    double porcen_muyalta = 0.00;
                    double porcen_severo = 0.00;
                    double porcen_extremo = 0.00;
                    double porcen_grupo = 0.00;
                    double total_general = 0.00;                                        
                    String Matrizlabel[];
                    double mdataNinguno [];
                    double mdataBajo [];
                    double mdataNormal [];
                    double mdataMedio [];
                    double mdataModerado [];
                    double mdataAlta [];
                    double mdataMuyAlta [];
                    double mdataSevero [];
                    double mdataExtremo [];
                    
                    int cuentaescala;                    
                    int contador = 0;
                    List<Reporte> data = (List<Reporte>)request.getAttribute("data_reporte_region");
                    if(data!=null){
                      cuentaescala = data.size()-1;
                        
                    }else{
                      cuentaescala = 0;
                    }
                    
                    Matrizlabel = new String[cuentaescala];
                    mdataNinguno = new double[cuentaescala];
                    mdataBajo = new double[cuentaescala];
                    mdataNormal = new double[cuentaescala];
                    mdataMedio = new double[cuentaescala];
                    mdataModerado = new double[cuentaescala];
                    mdataAlta = new double[cuentaescala];
                    mdataMuyAlta = new double[cuentaescala];
                    mdataSevero = new double[cuentaescala];
                    mdataExtremo = new double[cuentaescala];
                    
                    if(data!=null){
                       for(Reporte x:data){
                           if(x.getDepartamento().equalsIgnoreCase("TOTAL") )  {
                               total_ninguno = x.getCta_ninguno();
                               total_bajo = x.getCta_bajo();
                               total_normal = x.getCta_normal();
                               total_medio = x.getCta_medio();
                               total_moderado = x.getCta_moderado();
                               total_alta = x.getCta_alta();
                               total_muyalta = x.getCta_muyalta();
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
                                  <% } %>                                    
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
                                porcen_muyalta = (x.getCta_muyalta()/total_grupo)*100;
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
                            <% if(variable.getCodresultado().equalsIgnoreCase("Muy Alta")) {%>
                                <td> <%= String.format("%.2f",x.getCta_muyalta()) %> </td>
                            <%}%>                            
                           <% if(variable.getCodresultado().equalsIgnoreCase("Severo")) {%>
                                <td> <%= String.format("%.2f",x.getCta_severo()) %> </td>
                            <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Extremadamente")) {%>
                                <td> <%= String.format("%.2f",x.getCta_extremo()) %> </td>
                            <%}%>

                                <% } %>
                       <%
                           if(x.getCodvarfin().equalsIgnoreCase("RESILIENCIA")) { %>
                            <td> <%= String.format("%.2f",x.getMedia() ) %> </td>
                            <td> <%= String.format("%.2f",x.getDesviacion() ) %> </td>
                       <%
                           total_general = total_general + x.getCta_total();
                           }
                       %>
                        <td> <%= String.format("%.2f",x.getCta_total()) %>  </td>
                    </tr>
                <%    
                    }
                    else {
                            
                 %>
                  <tr>
                        <td> <%= x.getDepartamento() %> </td>
                        <% 
                            Matrizlabel[contador] = "'" + x.getDepartamento() + "'";
                            
                        %>
                            <% for (Variable variable : indicadores) { %>
                            <% if(variable.getCodresultado().equalsIgnoreCase("Ninguno")) {%>
                                <td> <%= String.format("%.2f",porcen_ninguno) %> % </td>
                                <%
                                    mdataNinguno[contador] = x.getCta_ninguno();
                                %>
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Bajo")) {%>
                                <td> <%= String.format("%.2f",porcen_bajo) %> % </td>
                                <%
                                    mdataBajo[contador] = x.getCta_bajo();
                                %>                                
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Normal")) {%>
                                <td> <%= String.format("%.2f",porcen_normal) %> % </td>
                                <%
                                    mdataNormal[contador] = x.getCta_normal();
                                %>                                                                
                              <%}%>
                              
                           <% if(variable.getCodresultado().equalsIgnoreCase("Medio")) {%>
                                <td> <%= String.format("%.2f",porcen_medio) %> % </td>  
                                <%
                                    mdataMedio[contador] = x.getCta_medio();
                                %>                                                                                                
                              <%}%>
                              
                           <% if(variable.getCodresultado().equalsIgnoreCase("Moderado")) {%>
                                <td> <%= String.format("%.2f",porcen_moderado) %> % </td>
                                <%
                                    mdataModerado[contador] = x.getCta_moderado();
                                %>                                                                                                                                
                              <%}%>
                              
                           <% if(variable.getCodresultado().equalsIgnoreCase("Alta")) {%>
                                <td> <%= String.format("%.2f",porcen_alta) %> % </td>  
                                <%
                                    mdataAlta[contador] = x.getCta_alta();
                                %>                                                                                                                                                                
                              <%}%>

                           <% if(variable.getCodresultado().equalsIgnoreCase("Muy Alta")) {%>
                                <td> <%= String.format("%.2f",porcen_muyalta) %> % </td>  
                                <%
                                    mdataMuyAlta[contador] = x.getCta_muyalta();
                                %>                                                                                                                                                                
                              <%}%>
                              
                           <% if(variable.getCodresultado().equalsIgnoreCase("Severo")) {%>
                                <td> <%= String.format("%.2f",porcen_severo) %> % </td>
                                <%
                                    mdataSevero[contador] = x.getCta_severo();
                                %>                                                                                                                                                                
                                
                              <%}%>
                           <% if(variable.getCodresultado().equalsIgnoreCase("Extremadamente")) {%>
                                <td> <%= String.format("%.2f",porcen_extremo) %> % </td>
                                <%
                                    mdataExtremo[contador] = x.getCta_extremo();
                                %>                                                                                                                                                                                                
                              <%}%>

                                <% } %>
                        
                        <td> <%= String.format("%.2f",porcen_grupo) %> % </td>
                    </tr>
                 
                 <%
                            contador = contador + 1; 
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
                        <%
                            if(var_codvarfin.equalsIgnoreCase("RESILIENCIA")) { %>
                                <th> TOTALES </th>
                            <%} else {%>
                                 <th> TOTAL PORCENTAJE </th>
                            <%} %>
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
                          <% if(var_codvarfin.equalsIgnoreCase("RESILIENCIA")) { %>
                                <th>  </th>
                                <th>  </th>
                                <th> <%= String.format("%.2f",total_general) %>  </th>
                           <%} else {%>
                                <th> 100 % </th>
                           <%}%>
                    </tr>
                <%
                    }
                %>
                </table>
           </div>
         </aside>           
          <br><br> 
                   
                    
         <%---Aquí agrego un aside en el mismo contenedor para graficar los pie---%> 
         <aside class="servicios">
                <div class="row">
                    <div class="col-xs-5 col-md-8"><canvas id="myChart" width="100" height="50"></canvas></div>
                </div>      
          </aside>     
                
        </div>          
   
                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    
    </body>

  <script>
               
    var vardata_ninguno=<%=(Arrays.toString(mdataNinguno))%>;
    var vardata_bajo=<%=(Arrays.toString(mdataBajo))%>;
    var vardata_normal=<%=(Arrays.toString(mdataNormal))%>;
    var vardata_medio=<%=(Arrays.toString(mdataMedio))%>;
    var vardata_moderado=<%=(Arrays.toString(mdataModerado))%>;
    var vardata_alta=<%=(Arrays.toString(mdataAlta))%>;
    var vardata_muyalta=<%=(Arrays.toString(mdataMuyAlta))%>;
    var vardata_severo=<%=(Arrays.toString(mdataSevero))%>;
    var vardata_extremo=<%=(Arrays.toString(mdataExtremo))%>;
    var varlabel_matriz=<%=(Arrays.toString(Matrizlabel))%>;    
    var ctx= document.getElementById("myChart").getContext("2d");

    //if(variable_analisis === 'RESILIENCIA')   {
    
        var myChart= new Chart(ctx,{
        type:"bar",
        data:{
              labels: varlabel_matriz,
              datasets:[{
                label:'Bajo',
                data: vardata_bajo,
                backgroundColor:'rgb(0, 218, 236,0.5)'      
                },{
                label:'Normal',
                data: vardata_normal,
                backgroundColor:'rgb(0, 218, 0,0.5)'  
                },{
                label:'Medio',
                data: vardata_medio,
                backgroundColor:'rgb(229, 176, 50,0.5)'
                },{
                label:'Moderado',
                data: vardata_moderado,
                backgroundColor:'rgb(255, 255, 102,0.5)'
                },{
                label:'Alta',
                data: vardata_alta,
                backgroundColor:'rgb(255, 153, 102,0.5)'
                },{
                label:'Severo',
                data: vardata_severo,
                backgroundColor:'rgb(255, 0, 102,0.5)'
                },{
                label:'Extremo',
                data: vardata_extremo,
                backgroundColor:'rgb(255, 0, 0,0.5)'  
                }
            
            ]
            },
            options:{
                scales:{
                    yAxes:[{
                            ticks:{
                                beginAtZero:true
                            }
                    }]
                }
            }
        });
    //};
    </script>
</html>
