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
        <title>Análisis de variables por Sexo</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
	<meta name="description" content="pagina venta server">
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
                 <input type ="hidden" name ="metodo" value ="sexo">
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
                
                <h4>${variable_analisis}</h4>
                <table class="table">
                    <tr>
                        <th>Escala/Sexo</th> 
                        <th>Masculino</th> 
                        <th>Femenino</th> 
                        <th>Total</th> 
                    </tr>
                <%        
                    double pie1data [];
                    String pie1label[];
                    
                    
                   
                    int contador = 0;
                    
                    /////////////////////////////////////
                    double total_masculino = 0.00;
                    double total_femenino = 0.00;
                    double total_grupo    = 0.00;
                    
                    double porcen_masculino = 0.00;
                    double porcen_femenino = 0.00;
                    double porcen_grupo = 0.00;                  
                    int cuentaescala = 0;
                    
                    List<Reporte> datap = (List<Reporte>)request.getAttribute("data_reporte_sexo");
                    if(datap!=null){
                       for(Reporte x:datap){
                           if(!x.getCodresultado().equalsIgnoreCase("TOTAL") )
                               cuentaescala = cuentaescala  +1;
                       }
                    }
                    pie1label = new String[cuentaescala];
                    pie1data = new double[cuentaescala];
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
                                    <td> <%= String.format("%.0f",x.getCta_masculino()) %> </td>
                                    <td> <%= String.format("%.0f",x.getCta_femenino()) %> </td>
                                    <td> <%= String.format("%.0f",x.getCta_total()) %> </td>
                                </tr>        
                            <%
                           }
                           else
                           {
                                
                                porcen_masculino = (x.getCta_masculino()/total_grupo)*100;
                                porcen_femenino  = (x.getCta_femenino()/total_grupo)*100;
                                porcen_grupo     = (x.getCta_total()/total_grupo)*100;
                                
                                //llenar el array
                                 
                                pie1data[contador] = porcen_masculino;
                                pie1label[contador] = "'" + x.getCodresultado() + "'";
                                contador = contador + 1;

                               if(x.getTipovar().equalsIgnoreCase("MEDIA") ) {
                %>
                            
                    <tr>
                        <td> <%= x.getCodresultado() %> </td>
                        <td> <%= String.format("%.2f", x.getCta_masculino()) %>  </td>
                        <td> <%= String.format("%.2f",x.getCta_femenino()) %>  </td>
                        <td> <%= String.format("%.2f",x.getCta_total()) %>  </td>
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
                        <th> 100 %  </th>
                    </tr>
                <%
                    
                    } 
                %>
                </table>
           </div>
         </aside> 
                
          <br><br> 
                   
          
          
                <%
                    //variable prueba, para llenar el script pero no me funca
                    double[] guille = {total_masculino};
                %>  
                <%---Aquí agrego un aside en el mismo contenedor para graficar los pie---%> 
         <aside class="servicios">
                <div class="row">
                    <div class="col-xs-10 col-md-6"><canvas id="myChart" width="100" height="100"></canvas></div>
                    <div class="col-xs-10 col-md-6"><canvas id="myChart2" width="100" height="100"></canvas></div>
                </div>      
          </aside>     
                
                
        </div>          
   
                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
              
              
    </body>
    
    <script>
               
    var  guille1=<%=(Arrays.toString(pie1data))%>;
  
        var guille2=[];
        
        var ctx= document.getElementById("myChart").getContext("2d");
        var myChart= new Chart(ctx,{
            type:"pie",
            data:{
                labels: <%=(Arrays.toString(pie1label))%>,
                datasets:[{
                        label:'Num datos',
                        data: guille1,
                        backgroundColor:[
                            'rgb(66, 134, 244,0.5)',
                            'rgb(74, 135, 72,0.5)',
                            'rgb(229, 176, 50,0.5)',
                            'rgb(100, 145, 50,0.5)',
                            'rgb(123, 89, 50,0.5)',
                            'rgb(32, 154, 50,0.5)'
                        ]
                }]
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
        
        var ctx= document.getElementById("myChart2").getContext("2d");
        var myChart2= new Chart(ctx,{
            type:"pie",
            data:{
                labels:['col1','col2','col3'],
                datasets:[{
                        label:'Num datos',
                        data:[12,38,2],
                        backgroundColor:[
                            'rgb(66, 134, 244,0.5)',
                            'rgb(74, 135, 72,0.5)',
                            'rgb(229, 89, 50,0.5)'
                        ]
                }]
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
        
       
        
    </script>
</html>
