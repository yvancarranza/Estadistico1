
package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entidad.Reporte;
import entidad.Variable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelReporte;
import model.ModelVariable;

@WebServlet(name = "reporte", urlPatterns = {"/reporte"})
public class reporte extends HttpServlet {

   protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
     response.setContentType("text/html;charset=UTF-8");
        
     String metodo = request.getParameter("metodo");
     //String metodo = ESAPI.encoder().encodeForHTML(request.getParameter("metodo"));
     if(metodo.equals("sexo"))
        {
            listaporsexo(request,response);
        }else if(metodo.equals("region"))
        {
            listaporregion(request,response);
        }
    }     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet reporte</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet reporte at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
      protected void listaporsexo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        String variable = request.getParameter("Variable");  
        String bver = request.getParameter("btnver");  
        String bpdf = request.getParameter("btnpdf");  
        request.setAttribute("variable_analisis", "Variable Analizada: " + variable);
        if(bver != null) {
            ModelReporte mreporte = new ModelReporte();
            List<Reporte> reportes =  mreporte.ListarReporte1(variable);
            request.setAttribute("data_reporte_sexo", reportes);         
            request.getRequestDispatcher("/reporteporsexo.jsp").forward(request, response);
        }
        else{
             listaporsexopdf(request,response);
        }
     }
      
       protected void listaporregion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        String variable = request.getParameter("Variable");  
        String bver = request.getParameter("btnver");  
        String bpdf = request.getParameter("btnpdf");  
        request.setAttribute("variable_analisis", "Variable Analizada: " + variable);
        request.setAttribute("mvarfinal",variable);
        if(bver != null) {
            ModelReporte mreporte = new ModelReporte();
            List<Reporte> reportes =  mreporte.ListarReporte2(variable);
            request.setAttribute("data_reporte_region", reportes);         
            request.getRequestDispatcher("/reporteporregion.jsp").forward(request, response);
        }
        else{
             listaporregionpdf(request,response);
        }
     }
      
     protected void listaporsexopdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
       response.setContentType("application/pdf");
        OutputStream salida = response.getOutputStream();
        ModelReporte mreporte = new ModelReporte();
       String variable = request.getParameter("Variable");  
        List<Reporte> reportes = mreporte.ListarReporte1(variable);
 
        try {
                
            try {
                Document documento = new Document();
                PdfWriter.getInstance(documento, salida);
                documento.open();
                
                Paragraph par1 = new Paragraph();
                Font fonttitulo = new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.BLACK);
                par1.add(new Phrase("Reporte Estadístico - Síndromes",fonttitulo));
                par1.setAlignment(Element.ALIGN_CENTER);
                par1.add(new Phrase(Chunk.NEWLINE));
                par1.add(new Phrase(Chunk.NEWLINE));
                documento.add(par1);
          
                
                
          
                /*Image imagenes = Image.getInstance("\\estilos\\imagenes\\logo1.png");
                imagenes.setAlignment(Element.ALIGN_CENTER);
                imagenes.scaleToFit(100,100);
                documento.add(imagenes);
                */
                Paragraph par2 = new Paragraph();
                Font fontdescripcion = new Font(Font.FontFamily.TIMES_ROMAN,14,Font.NORMAL,BaseColor.BLACK);
                par2.add(new Phrase("Variables de Análisis : " + variable,fontdescripcion));
                par2.setAlignment(Element.ALIGN_JUSTIFIED);
                par2.add(new Phrase(Chunk.NEWLINE));
                par2.add(new Phrase(Chunk.NEWLINE));
                documento.add(par2);
            
                
                PdfPTable tabla = new PdfPTable(4);
                tabla.setWidthPercentage(100);
                tabla.setWidths(new float[] { 3, 1,1,1 });
                
                PdfPCell  celda1 = new PdfPCell(new Paragraph("            Escala / Sexo          ",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                PdfPCell  celda2 = new PdfPCell(new Paragraph("Masculino",FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                PdfPCell  celda3 = new PdfPCell(new Paragraph("Femenino",FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                PdfPCell  celda4 = new PdfPCell(new Paragraph("Total",FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                celda1.setFixedHeight(30f);
                celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                celda1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                int posicion = 0;
                double total_masculino = 0;
                double total_femenino = 0;
                double total_grupo  = 0;
                
                double porcen_masculino = 0.00;
                double porcen_femenino = 0.00;
                double porcen_grupo = 0.00;
                String tipovar="";
                for (Reporte reporte : reportes) {
                     if(reporte.getCodresultado().equalsIgnoreCase("TOTAL")) {
                         total_masculino = reporte.getCta_masculino();
                         total_femenino = reporte.getCta_femenino();
                         total_grupo    = reporte.getCta_total();
                         tipovar = reporte.getTipovar();
                     }
                     else{
                         porcen_masculino = (reporte.getCta_masculino()/total_grupo)*100;
                         porcen_femenino  = (reporte.getCta_femenino()/total_grupo)*100;
                         porcen_grupo     = (reporte.getCta_total()/total_grupo)*100;
                    } 
                    if(reporte.getTipovar().equalsIgnoreCase("MEDIA")){
                        if(reporte.getCodresultado().equalsIgnoreCase("TOTAL")) {

                             PdfPCell  celda11 = new PdfPCell(new Paragraph("TOTAL UNIDADES",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda12 = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_masculino()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda13 = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_femenino()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda14 = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_total()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                            celda11.setHorizontalAlignment(Element.ALIGN_LEFT);
                            celda12.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            celda13.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            celda14.setHorizontalAlignment(Element.ALIGN_RIGHT);
                             celda11.setFixedHeight(25f);                                   
                            tabla.addCell(celda11);
                            tabla.addCell(celda12 );
                            tabla.addCell(celda13 );
                            tabla.addCell(celda14 );                        }
                        else {
                            
                             PdfPCell  celda11 = new PdfPCell(new Paragraph(reporte.getCodresultado(),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda12 = new PdfPCell(new Paragraph(String.format("%.2f", reporte.getCta_masculino()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda13 = new PdfPCell(new Paragraph(String.format("%.2f", reporte.getCta_femenino()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda14 = new PdfPCell(new Paragraph(String.format("%.2f", reporte.getCta_total()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                            celda11.setFixedHeight(25f);                                        
                            celda11.setHorizontalAlignment(Element.ALIGN_LEFT);
                            celda12.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            celda13.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            celda14.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            tabla.addCell(celda11);
                            tabla.addCell(celda12 );
                            tabla.addCell(celda13 );
                            tabla.addCell(celda14 );
                        }
                    }
                    else{
                        if(reporte.getCodresultado().equalsIgnoreCase("TOTAL")) {
                             PdfPCell  celda11 = new PdfPCell(new Paragraph("TOTAL UNIDADES",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda12 = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_masculino()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda13 = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_femenino()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda14 = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_total()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             
                            celda11.setFixedHeight(25f);                                   
                            celda11.setHorizontalAlignment(Element.ALIGN_LEFT);
                            celda12.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            celda13.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            celda14.setHorizontalAlignment(Element.ALIGN_RIGHT);

                            tabla.addCell(celda11);
                            tabla.addCell(celda12 );
                            tabla.addCell(celda13 );
                            tabla.addCell(celda14 );                        
                        }
                        else {
                             PdfPCell  celda11 = new PdfPCell(new Paragraph(reporte.getCodresultado(),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda12 = new PdfPCell(new Paragraph(String.format("%.2f", porcen_masculino) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda13 = new PdfPCell(new Paragraph(String.format("%.2f", porcen_femenino) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda14 = new PdfPCell(new Paragraph(String.format("%.2f", porcen_grupo) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             celda11.setFixedHeight(25f);                                   
                             celda11.setHorizontalAlignment(Element.ALIGN_LEFT);
                             celda12.setHorizontalAlignment(Element.ALIGN_RIGHT);
                             celda13.setHorizontalAlignment(Element.ALIGN_RIGHT);
                             celda14.setHorizontalAlignment(Element.ALIGN_RIGHT);
                
                             tabla.addCell(celda11);
                             tabla.addCell(celda12 );
                             tabla.addCell(celda13 );
                             tabla.addCell(celda14 );                                                    
                        }
                    }
                } 
                
                porcen_masculino = (Double.valueOf(total_masculino)/total_grupo)*100;
                porcen_femenino = (Double.valueOf(total_femenino)/total_grupo)*100;
                porcen_grupo = 100.00;
                
                
                PdfPCell  celda11 = new PdfPCell(new Paragraph("TOTAL PORCENTAJE ",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                PdfPCell  celda12 = new PdfPCell(new Paragraph(String.format("%.2f",porcen_masculino) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                PdfPCell  celda13 = new PdfPCell(new Paragraph(String.format("%.2f",porcen_femenino) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                PdfPCell  celda14 = new PdfPCell(new Paragraph(String.format("%.2f",porcen_grupo) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                
                celda11.setFixedHeight(30f); 
                celda11.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda12.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda13.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda14.setBackgroundColor(BaseColor.LIGHT_GRAY);
                celda11.setHorizontalAlignment(Element.ALIGN_LEFT);
                celda12.setHorizontalAlignment(Element.ALIGN_RIGHT);
                celda13.setHorizontalAlignment(Element.ALIGN_RIGHT);
                celda14.setHorizontalAlignment(Element.ALIGN_RIGHT);
                
                tabla.addCell(celda11);
                tabla.addCell(celda12);
                tabla.addCell(celda13);
                tabla.addCell(celda14);
                
                documento.add(tabla);
                    documento.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
        finally{
               salida.close();
            }
            // Try creación de Documento              
            
            
        } 

      protected void listaporregionpdf(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
       response.setContentType("application/pdf");
       //response.sendRedirect("reporteporregion.jsp");
        OutputStream salida = response.getOutputStream();
        ModelReporte mreporte = new ModelReporte();
        String variable = request.getParameter("Variable");  
        List<Reporte> reportes = mreporte.ListarReporte2(variable);
 
        try {
                
            try {
                Document documento = new Document(PageSize.A4.rotate(),10f,10f,10f,10f);
                PdfWriter.getInstance(documento, salida);
                
                documento.open();
                Paragraph par1 = new Paragraph();
                Font fonttitulo = new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.BLACK);
                par1.add(new Phrase("Reporte Estadístico - Síndromes",fonttitulo));
                par1.setAlignment(Element.ALIGN_CENTER);
                par1.add(new Phrase(Chunk.NEWLINE));
                par1.add(new Phrase(Chunk.NEWLINE));
                documento.add(par1);
          
                
                
          
                /*Image imagenes = Image.getInstance("\\estilos\\imagenes\\logo1.png");
                imagenes.setAlignment(Element.ALIGN_CENTER);
                imagenes.scaleToFit(100,100);
                documento.add(imagenes);
                */
                Paragraph par2 = new Paragraph();
                Font fontdescripcion = new Font(Font.FontFamily.TIMES_ROMAN,14,Font.NORMAL,BaseColor.BLACK);
                par2.add(new Phrase("Variables de Análisis : " + variable,fontdescripcion));
                par2.setAlignment(Element.ALIGN_JUSTIFIED);
                par2.add(new Phrase(Chunk.NEWLINE));
                par2.add(new Phrase(Chunk.NEWLINE));
                documento.add(par2);
                ModelVariable mvariable = new ModelVariable();
                String var_codvarfin = (String)request.getAttribute("mvarfinal");
                List<Variable> indicadores = mvariable.ListarIndicador(var_codvarfin);
                if(var_codvarfin == null)
                   {
                      var_codvarfin = "";
                   }
                
                int cuentacol = 0;
                if(var_codvarfin.equalsIgnoreCase("RESILIENCIA")){
                    cuentacol = 4;
                }
                else{
                    cuentacol = indicadores.size()+2;
                }                
                
                PdfPTable tabla = new PdfPTable(cuentacol);
                tabla.setWidthPercentage(100);
              //  tabla.setWidths(new float[] { 3, 1,1,1 });
                
                 
             
                PdfPCell  celdaNinguno;
                PdfPCell  celdaBajo;
                PdfPCell  celdaNormal;
                PdfPCell  celdaMedio;
                PdfPCell  celdaModerado;
                PdfPCell  celdaAlta;
                PdfPCell  celdaMuyAlta;
                PdfPCell  celdaSevero;
                PdfPCell  celdaExtremo;
                PdfPCell  celdaTotal;
                PdfPCell  celdaMedia;
                PdfPCell  celdaDesviacion;
                
                PdfPCell  celda1 = new PdfPCell(new Paragraph("            Escala / Sexo          ",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                celda1.setFixedHeight(30f);
                celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                tabla.addCell(celda1);
                
                for (Variable varnombre : indicadores) {
                    if(varnombre.getCodresultado().equalsIgnoreCase("Ninguno")){
                        celdaNinguno = new PdfPCell(new Paragraph(varnombre.getCodresultado(),FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                        celdaNinguno.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdaNinguno.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                        tabla.addCell(celdaNinguno);
                    }
                    if(varnombre.getCodresultado().equalsIgnoreCase("Bajo")){
                        celdaBajo = new PdfPCell(new Paragraph(varnombre.getCodresultado(),FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                        celdaBajo.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdaBajo.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                        tabla.addCell(celdaBajo);
                    }
 
                    if(varnombre.getCodresultado().equalsIgnoreCase("Normal")){
                        celdaNormal = new PdfPCell(new Paragraph(varnombre.getCodresultado(),FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                        celdaNormal.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdaNormal.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                        tabla.addCell(celdaNormal);
                    }

                    if(varnombre.getCodresultado().equalsIgnoreCase("Medio")){
                        celdaMedio = new PdfPCell(new Paragraph(varnombre.getCodresultado(),FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                        celdaMedio.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdaMedio.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                        tabla.addCell(celdaMedio);
                    }
                    if(varnombre.getCodresultado().equalsIgnoreCase("Moderado")){
                        celdaModerado = new PdfPCell(new Paragraph(varnombre.getCodresultado(),FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                        celdaModerado.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdaModerado.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                        tabla.addCell(celdaModerado);
                    }
                    if(varnombre.getCodresultado().equalsIgnoreCase("Alta")){
                        celdaAlta = new PdfPCell(new Paragraph(varnombre.getCodresultado(),FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                        celdaAlta.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdaAlta.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                        tabla.addCell(celdaAlta);
                    }
                    if(varnombre.getCodresultado().equalsIgnoreCase("Muy Alta")){
                        celdaMuyAlta = new PdfPCell(new Paragraph(varnombre.getCodresultado(),FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                        celdaMuyAlta.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdaMuyAlta.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                        tabla.addCell(celdaMuyAlta);
                    }
                    if(varnombre.getCodresultado().equalsIgnoreCase("Severo")){
                        celdaSevero = new PdfPCell(new Paragraph(varnombre.getCodresultado(),FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                        celdaSevero.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdaSevero.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                        tabla.addCell(celdaSevero);
                    }
                    if(varnombre.getCodresultado().equalsIgnoreCase("Extremadamente")){
                        celdaExtremo = new PdfPCell(new Paragraph(varnombre.getCodresultado(),FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                        celdaExtremo.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdaExtremo.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                        tabla.addCell(celdaExtremo);
                    }                  
                 }
                if(var_codvarfin.equalsIgnoreCase("RESILIENCIA")){
                        celdaMedia = new PdfPCell(new Paragraph("MEDIA",FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                        celdaMedia.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdaMedia.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                        tabla.addCell(celdaMedia);
                        
                        celdaDesviacion = new PdfPCell(new Paragraph("DESVIACION",FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                        celdaDesviacion.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celdaDesviacion.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                        tabla.addCell(celdaDesviacion);
                        
                        
                }
                celdaTotal = new PdfPCell(new Paragraph("Total",FontFactory.getFont("Helvetica", 13,Font.BOLD,BaseColor.BLACK)));
                celdaTotal.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                tabla.addCell(celdaTotal);
                
                int posicion = 0;
                double total_ninguno = 0;
                double total_bajo = 0;
                double total_normal = 0;
                double total_medio = 0;
                double total_moderado = 0;
                double total_alta = 0;
                double total_muyalta = 0;
                double total_severo = 0;
                double total_extremo = 0;
                
                double total_grupo  = 0;
                
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
                String tipovar="";
                for (Reporte reporte : reportes) {
                     if(reporte.getDepartamento().equalsIgnoreCase("TOTAL")) {
                         total_ninguno = reporte.getCta_ninguno();
                         total_bajo = reporte.getCta_bajo();
                         total_normal = reporte.getCta_normal();
                         total_medio = reporte.getCta_medio();
                         total_moderado = reporte.getCta_moderado();
                         total_alta = reporte.getCta_alta();
                         total_muyalta = reporte.getCta_muyalta();
                         total_severo = reporte.getCta_severo();
                         total_extremo = reporte.getCta_extremo();
                         total_grupo    = reporte.getCta_total();
                         tipovar = reporte.getTipovar();
                     }
                     else{
                         porcen_ninguno = (reporte.getCta_ninguno()/total_grupo)*100;
                         porcen_bajo  = (reporte.getCta_bajo()/total_grupo)*100;
                         porcen_normal  = (reporte.getCta_normal()/total_grupo)*100;
                         porcen_medio  = (reporte.getCta_medio()/total_grupo)*100;
                         porcen_moderado  = (reporte.getCta_moderado()/total_grupo)*100;
                         porcen_alta  = (reporte.getCta_alta()/total_grupo)*100;
                         porcen_muyalta  = (reporte.getCta_muyalta()/total_grupo)*100;
                         porcen_severo  = (reporte.getCta_severo()/total_grupo)*100;
                         porcen_extremo  = (reporte.getCta_extremo()/total_grupo)*100;
                         porcen_grupo     = (reporte.getCta_total()/total_grupo)*100;
                    } 
                    if(reporte.getTipovar().equalsIgnoreCase("MEDIA")){
                        if(reporte.getDepartamento().equalsIgnoreCase("TOTAL")) {

                             PdfPCell  celda11 = new PdfPCell(new Paragraph("TOTAL UNIDADES",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda12 = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getMedia()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda13 = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getDesviacion()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda14 = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_total()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                            celda11.setHorizontalAlignment(Element.ALIGN_LEFT);
                            celda12.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            celda13.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            celda14.setHorizontalAlignment(Element.ALIGN_RIGHT);
                             celda11.setFixedHeight(25f);                                   
                            tabla.addCell(celda11);
                            tabla.addCell(celda12 );
                            tabla.addCell(celda13 );
                            tabla.addCell(celda14 );                        }
                        else {
                            
                             PdfPCell  celda11 = new PdfPCell(new Paragraph(reporte.getDepartamento(),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda12 = new PdfPCell(new Paragraph(String.format("%.2f", reporte.getMedia()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda13 = new PdfPCell(new Paragraph(String.format("%.2f", reporte.getDesviacion()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             PdfPCell  celda14 = new PdfPCell(new Paragraph(String.format("%.2f", reporte.getCta_total()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                            celda11.setFixedHeight(25f);                                        
                            celda11.setHorizontalAlignment(Element.ALIGN_LEFT);
                            celda12.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            celda13.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            celda14.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            tabla.addCell(celda11);
                            tabla.addCell(celda12 );
                            tabla.addCell(celda13 );
                            tabla.addCell(celda14 );
                        }
                    }
                    else{
                        if(reporte.getDepartamento().equalsIgnoreCase("TOTAL")) {
                             PdfPCell  celda11 = new PdfPCell(new Paragraph("TOTAL UNIDADES",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             celda11.setFixedHeight(25f);                                   
                             celda11.setHorizontalAlignment(Element.ALIGN_LEFT);
                             tabla.addCell(celda11);

                             for (Variable varnombre : indicadores) {
                                if(varnombre.getCodresultado().equalsIgnoreCase("Ninguno")){
                                    celdaNinguno = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_ninguno()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaNinguno.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaNinguno);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Bajo")){
                                    celdaBajo = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_bajo()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaBajo.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaBajo);
                                }

                                if(varnombre.getCodresultado().equalsIgnoreCase("Normal")){
                                    celdaNormal = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_normal()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaNormal.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaNormal);
                                }

                                if(varnombre.getCodresultado().equalsIgnoreCase("Medio")){
                                    celdaMedio = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_medio()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaMedio.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaMedio);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Moderado")){
                                    celdaModerado = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_moderado()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaModerado.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaModerado);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Alta")){
                                    celdaAlta = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_alta()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaAlta.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaAlta);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Muy Alta")){
                                    celdaMuyAlta = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_muyalta()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaMuyAlta.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaMuyAlta);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Severo")){
                                    celdaSevero = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_severo()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaSevero.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaSevero);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Extremadamente")){
                                    celdaExtremo = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_extremo()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaExtremo.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaExtremo);
                                }
                             }
                             PdfPCell  celda14 = new PdfPCell(new Paragraph(String.format("%.0f", reporte.getCta_total()),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));                             
                             celda14.setHorizontalAlignment(Element.ALIGN_RIGHT);
                             tabla.addCell(celda14 );                        
                        }
                        else {
                             PdfPCell  celda11 = new PdfPCell(new Paragraph(reporte.getDepartamento(),FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             celda11.setFixedHeight(25f);                                   
                             celda11.setHorizontalAlignment(Element.ALIGN_LEFT);
                             tabla.addCell(celda11);                             
                           for (Variable varnombre : indicadores) {
                                if(varnombre.getCodresultado().equalsIgnoreCase("Ninguno")){
                                    celdaNinguno = new PdfPCell(new Paragraph(String.format("%.2f", porcen_ninguno) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaNinguno.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaNinguno);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Bajo")){
                                    celdaBajo = new PdfPCell(new Paragraph(String.format("%.2f", porcen_bajo) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaBajo.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaBajo);
                                }

                                if(varnombre.getCodresultado().equalsIgnoreCase("Normal")){
                                    celdaNormal = new PdfPCell(new Paragraph(String.format("%.2f", porcen_normal) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaNormal.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaNormal);
                                }

                                if(varnombre.getCodresultado().equalsIgnoreCase("Medio")){
                                    celdaMedio = new PdfPCell(new Paragraph(String.format("%.2f", porcen_medio) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaMedio.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaMedio);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Moderado")){
                                    celdaModerado = new PdfPCell(new Paragraph(String.format("%.2f", porcen_moderado) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaModerado.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaModerado);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Alta")){
                                    celdaAlta = new PdfPCell(new Paragraph(String.format("%.2f", porcen_alta) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaAlta.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaAlta);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Muy Alta")){
                                    celdaMuyAlta = new PdfPCell(new Paragraph(String.format("%.2f", porcen_muyalta) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaMuyAlta.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaMuyAlta);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Severo")){
                                    celdaSevero = new PdfPCell(new Paragraph(String.format("%.2f", porcen_severo) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaSevero.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaSevero);
                                }
                                if(varnombre.getCodresultado().equalsIgnoreCase("Extremadamente")){
                                    celdaExtremo = new PdfPCell(new Paragraph(String.format("%.2f", porcen_extremo) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                                    celdaExtremo.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celdaExtremo);
                                }
                             }                             
                             
                             PdfPCell  celda14 = new PdfPCell(new Paragraph(String.format("%.2f", porcen_grupo) + " %",FontFactory.getFont("garamond bold", 11,Font.NORMAL,BaseColor.BLACK)));
                             celda14.setHorizontalAlignment(Element.ALIGN_RIGHT);
                             tabla.addCell(celda14 );                                                    
                        }
                    }
                } 
                
                porcen_ninguno = (total_ninguno/total_grupo)*100;
                porcen_bajo = (total_bajo/total_grupo)*100;
                porcen_normal = (total_normal/total_grupo)*100;
                porcen_medio = (total_medio/total_grupo)*100;
                porcen_moderado = (total_moderado/total_grupo)*100;
                porcen_alta = (total_alta/total_grupo)*100;
                porcen_muyalta = (total_muyalta/total_grupo)*100;
                porcen_severo = (total_severo/total_grupo)*100;
                porcen_extremo = (total_extremo/total_grupo)*100;
                porcen_grupo = 100.00;

                PdfPCell  celda11 = new PdfPCell(new Paragraph("TOTAL PORCENTAJE ",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                celda11.setFixedHeight(30f); 
                celda11.setBackgroundColor(BaseColor.LIGHT_GRAY);
                tabla.addCell(celda11);

                for (Variable varnombre : indicadores) {
                     if(varnombre.getCodresultado().equalsIgnoreCase("Ninguno")){
                         celdaNinguno = new PdfPCell(new Paragraph(String.format("%.2f", porcen_ninguno) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                         celdaNinguno.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         celdaNinguno.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                         tabla.addCell(celdaNinguno);
                     }
                     if(varnombre.getCodresultado().equalsIgnoreCase("Bajo")){
                         celdaBajo = new PdfPCell(new Paragraph(String.format("%.2f", porcen_bajo)+ " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                         celdaBajo.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         celdaBajo.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                         tabla.addCell(celdaBajo);
                     }

                     if(varnombre.getCodresultado().equalsIgnoreCase("Normal")){
                         celdaNormal = new PdfPCell(new Paragraph(String.format("%.2f", porcen_normal) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                         celdaNormal.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         celdaNormal.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                         tabla.addCell(celdaNormal);
                     }

                     if(varnombre.getCodresultado().equalsIgnoreCase("Medio")){
                         celdaMedio = new PdfPCell(new Paragraph(String.format("%.2f", porcen_medio) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                         celdaMedio.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         celdaMedio.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                         tabla.addCell(celdaMedio);
                     }
                     if(varnombre.getCodresultado().equalsIgnoreCase("Moderado")){
                         celdaModerado = new PdfPCell(new Paragraph(String.format("%.2f", porcen_moderado) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                         celdaModerado.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         celdaModerado.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                         tabla.addCell(celdaModerado);
                     }
                     if(varnombre.getCodresultado().equalsIgnoreCase("Alta")){
                         celdaAlta = new PdfPCell(new Paragraph(String.format("%.2f", porcen_alta) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                         celdaAlta.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         celdaAlta.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                         tabla.addCell(celdaAlta);
                     }
                     if(varnombre.getCodresultado().equalsIgnoreCase("Muy Alta")){
                         celdaMuyAlta = new PdfPCell(new Paragraph(String.format("%.2f", porcen_muyalta) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                         celdaMuyAlta.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         celdaMuyAlta.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                         tabla.addCell(celdaMuyAlta);
                     }
                     if(varnombre.getCodresultado().equalsIgnoreCase("Severo")){
                         celdaSevero = new PdfPCell(new Paragraph(String.format("%.2f", porcen_severo) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                         celdaSevero.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         celdaSevero.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                         tabla.addCell(celdaSevero);
                     }
                     if(varnombre.getCodresultado().equalsIgnoreCase("Extremadamente")){
                         celdaExtremo = new PdfPCell(new Paragraph(String.format("%.2f", porcen_extremo) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                         celdaExtremo.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         celdaExtremo.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                         tabla.addCell(celdaExtremo);
                     }
                  }                             
                PdfPCell  celda14 = new PdfPCell(new Paragraph(String.format("%.2f",porcen_grupo) + " %",FontFactory.getFont("garamond bold", 13,Font.BOLD,BaseColor.BLACK)));
                celda14.setHorizontalAlignment(Element.ALIGN_RIGHT);
                celda14.setBackgroundColor(BaseColor.LIGHT_GRAY);                             
                tabla.addCell(celda14);                
                documento.add(tabla);
                    documento.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
        finally{
               salida.close();
            }
            // Try creación de Documento              
            
            
        } 

     
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
