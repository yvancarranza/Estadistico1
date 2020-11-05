package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;

public final class reporteporsexo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("     <head>      \n");
      out.write("        <title>Análisis de variables por Sexo</title>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width\">\n");
      out.write("\t<meta name=\"description\" content=\"pagina venta server\">\n");
      out.write("\t<meta name=\"keywords\" content=\"server\">\n");
      out.write("\t<meta name=\"autor\" content=\"Yvan , Guillermo\"> \n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos/css/estilos.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"estilos/css/estiloMenu.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"estilos/css/estiloServicios.css\">\n");
      out.write("    </head>\n");
      out.write("   \n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("\t\t<div class=\"contenedor\">\n");
      out.write("                    <div id=\"marca\">\n");
      out.write("                        <img src=\"estilos/imagenes/logo1.png\" width=\"120px\" height=\"120px\" >     \n");
      out.write("                    </div>\n");
      out.write("                        \n");
      out.write("                    <div id=\"marca\">\n");
      out.write("                            <h1><span style=\"color: #830C32;\">Estadistico</span>1.0</h1>\n");
      out.write("                            <small style=\"color: green\">Servicio de Reportería</small>  \n");
      out.write("                    </div>                  \n");
      out.write("                        <nav>\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("\t\t\t\t\t<li> <a href=\"index.jsp\">Inicio</a></li>\n");
      out.write("\t\t\t\t\t<li class=\"actual\"> <a href=\"servicios.jsp\">Servicios</a></li>\n");
      out.write("                                        <li> <a href=\"noticias.jsp\">Noticias</a></li>\n");
      out.write("\t\t\t\t</ul>\n");
      out.write("\t\t\t</nav>           \n");
      out.write("\t\t</div>\t\n");
      out.write("\t</header>\n");
      out.write("        \n");
      out.write("        <div class=\"contenedor\">\n");
      out.write("        <div class=\"area\"></div>\n");
      out.write("        <nav class=\"main-menu\">\n");
      out.write("            <ul>\n");
      out.write("                  <li>\n");
      out.write("                    <a href=\"servicios.jsp\">\n");
      out.write("                        <i class=\"fa fa-home fa-2x\"></i>\n");
      out.write("                        <span class=\"nav-text\">\n");
      out.write("                            Bienvenido\n");
      out.write("                        </span>\n");
      out.write("                    </a>    \n");
      out.write("                </li>\n");
      out.write("               \n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\">\n");
      out.write("                       <i class=\"fa fa-info fa-2x\"></i>\n");
      out.write("                        <span class=\"nav-text\">\n");
      out.write("                            Vacio        \n");
      out.write("               \n");
      out.write("               \n");
      out.write("    \n");
      out.write("            </ul>\n");
      out.write("\n");
      out.write("            <ul class=\"logout\">\n");
      out.write("                <li>\n");
      out.write("                   <a href=\"index.jsp\">\n");
      out.write("                         <i class=\"fa fa-power-off fa-2x\"></i>\n");
      out.write("                        <span class=\"nav-text\">\n");
      out.write("                                Salir\n");
      out.write("                        </span>\n");
      out.write("                    </a>\n");
      out.write("                </li>  \n");
      out.write("            </ul>\n");
      out.write("        </nav>\n");
      out.write("               \n");
      out.write("        </div>\n");
      out.write("       \n");
      out.write("        <form name =\"reporte\" action = \"/Estadistico/pdf\">\n");
      out.write("            \n");
      out.write("            <input type =\"submit\" value =\"Visualizar PDF\" name =\"btnver\" />\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("       \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
