
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

@WebServlet(name = "reporte", urlPatterns = {"/reporte"})
public class reporte extends HttpServlet {

   protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
     response.setContentType("text/html;charset=UTF-8");
        
     String metodo = request.getParameter("metodo");
     if(metodo.equals("lista1"))
        {
            listaporsexo(request,response);
        }else if(metodo.equals("lista2"))
        {
            listaporsexo(request,response);
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
                Font fonttitulo = new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.BLUE);
                par1.add(new Phrase("Reporte Estadístico sintomas",fonttitulo));
                par1.setAlignment(Element.ALIGN_CENTER);
                par1.add(new Phrase(Chunk.NEWLINE));
                par1.add(new Phrase(Chunk.NEWLINE));
                documento.add(par1);
          
                
                Paragraph par2 = new Paragraph();
                Font fontdescripcion = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL,BaseColor.BLACK);
                par2.add(new Phrase("Variables de Análisis : " + variable,fontdescripcion));
                par2.setAlignment(Element.ALIGN_JUSTIFIED);
                par2.add(new Phrase(Chunk.NEWLINE));
                par2.add(new Phrase(Chunk.NEWLINE));
                documento.add(par2);
            
          
                Image imagenes = Image.getInstance("D:\\UTP\\CICLO VII - AGOSTO 2020\\Integrador 2\\GRUPO_01\\Estadistico\\web\\estilos\\imagenes\\logo1.png");
                imagenes.setAlignment(Element.ALIGN_CENTER);
                imagenes.scaleToFit(100,100);
                documento.add(imagenes);
                
                PdfPTable tabla = new PdfPTable(4);
                PdfPCell  celda1 = new PdfPCell(new Paragraph("Escala",FontFactory.getFont("Arial", 12,Font.BOLD,BaseColor.RED)));
                PdfPCell  celda2 = new PdfPCell(new Paragraph("Masculino",FontFactory.getFont("Arial", 12,Font.BOLD,BaseColor.RED)));
                PdfPCell  celda3 = new PdfPCell(new Paragraph("Femenino",FontFactory.getFont("Arial", 12,Font.BOLD,BaseColor.RED)));
                PdfPCell  celda4 = new PdfPCell(new Paragraph("Total",FontFactory.getFont("Arial", 12,Font.BOLD,BaseColor.RED)));
                tabla.addCell(celda1);
                tabla.addCell(celda2);
                tabla.addCell(celda3);
                tabla.addCell(celda4);
                
                for (Reporte reporte : reportes) {
                       
                     tabla.addCell(reporte.getCodresultado());
                     tabla.addCell(reporte.getCta_masculino().toString());
                     tabla.addCell(reporte.getCta_femenino().toString());
                     tabla.addCell(reporte.getCta_total().toString());
                } 
                
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
