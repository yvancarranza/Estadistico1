
package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entidad.Reporte;
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
import org.owasp.esapi.ESAPI;

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
             listaporsexopdf(request,response);
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
          
                
                
          
                Image imagenes = Image.getInstance("D:\\UTP\\CICLO VII - AGOSTO 2020\\Integrador 2\\GRUPO_01\\Estadistico\\web\\estilos\\imagenes\\logo1.png");
                imagenes.setAlignment(Element.ALIGN_CENTER);
                imagenes.scaleToFit(100,100);
                documento.add(imagenes);
                
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
