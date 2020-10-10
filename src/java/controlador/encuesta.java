
package controlador;

import com.opencsv.CSVReader;
import entidad.Encuesta;
import entidad.Pregunta;
import entidad.Respuesta;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.ModelEncuesta;
import model.ModelPregunta;
import model.ModelRespuesta;

/**
 *
 * @author yvans
 */
@WebServlet(name = "encuesta", urlPatterns = {"/encuesta"})
@MultipartConfig(
     fileSizeThreshold = 10*1024*1024,
     maxFileSize = 5*1024*1024,
     maxRequestSize = 10*1024*1024
)
public class encuesta extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "assets/csv";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet encuesta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet encuesta at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
     response.setContentType("text/html;charset=UTF-8");
        
     String metodo = request.getParameter("metodo");
     if(metodo.equals("importarDiccionario"))
        {
            importarDiccionario(request,response);
        }  
      if(metodo.equals("importarEncuesta"))
        {
            importarEncuesta(request,response);
        }
    }   
    
    /*
     Importa Diccionario de Variables
    */
    protected void importarDiccionario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        try{
            String csv = uploadFile(request,"csvFile");
      //      if(!csv.equalsIgnoreCase("csv"))
        //    {
          //       request.getRequestDispatcher("/importarEncuesta.jsp").forward(request, response);
           // }
           // else
            //{
                String csvPath = request.getServletContext().getRealPath("") 
                                 + File.separator
                                 + UPLOAD_DIR
                                 + File.separator
                                 + csv;            
                
                
                ModelPregunta modelp = new ModelPregunta();
                modelp.eliminaPreguntaMasivo();
                
                ModelRespuesta modelr = new ModelRespuesta();
                modelr.eliminaRespuestaMasivo();
                
                /*ModelEncuesta modele = new ModelEncuesta();
                modele.eliminaEncuestaMasivo();
                */                         
            String archCSV = csvPath;
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(archCSV), "UTF-8"));
            String[] fila = null;
            int numreg = 0;
            while((fila = csvReader.readNext()) != null)
            {
                if(numreg == 0)
                {
                    System.out.println("Columnas");
                    for(int ifila = 0;ifila<fila.length;ifila++ )
                    {
                        //System.out.println(fila[ifila]);
                    }
                    numreg++;
                }
                else
                {
                    //id Variable
                    String idVariable;
                    String nomVariable;
                    String respuesta;
                    String tipocampo;
                    String formulario;

                    idVariable = fila[0];
                    formulario = fila[1];            
                    tipocampo = fila[3];
                    nomVariable = fila[4];           
                    respuesta    = fila[5];

                    //Evaluar si nombre de variable tiene html
                    if(tipocampo.contains("dropdown"))
                    {
                        if(nomVariable.contains("Distrito"))
                            nomVariable = "Distrito";
                        if(nomVariable.contains("Región"))
                            nomVariable = "Region";
                        if(nomVariable.contains("Provincia"))
                            nomVariable = "Provincia";                
                    }

                    if(tipocampo.contains("radio"))
                    {
                        if(nomVariable.contains("<div"))
                        {
                            int posradio = nomVariable.indexOf("<div");
                            nomVariable= nomVariable.substring(0, posradio);
                        }

                    }

                    if(tipocampo.contains("descriptive") || tipocampo.contains("calc"))
                        nomVariable= "";
                    
                    if(tipocampo.contains("text") && nomVariable.contains(">"))
                    {                
                       int posvar1 = nomVariable.indexOf(">");
                       nomVariable= nomVariable.substring(posvar1+1);
                       posvar1 = nomVariable.indexOf("<");
                       nomVariable= nomVariable.substring(0, posvar1 -1);

                    }           
                    idVariable = idVariable.trim();
                    nomVariable = nomVariable.trim();
                                       
                    if(!idVariable.isEmpty() && !nomVariable.isEmpty())
                    {                    
                        ModelPregunta model1 = new ModelPregunta();
                        Pregunta preg1 = new Pregunta();
                        preg1.setCodpregunta(idVariable);
                        preg1.setNompregunta(nomVariable);
                        preg1.setTipocampo(tipocampo);
                        preg1.setFormulario(formulario);
                        
                        model1.insertaPregunta(preg1);
                        numreg++;
                        
                        // ------------------------------
                        // Grabar en Tabla de Respuesta
                        // Identificar si se tiene definido separado por palotes
                        boolean ispalote;
                        ispalote = respuesta.contains("|");
                        String CodPregunta;
                        String NomPregunta;
                        CodPregunta = idVariable;
                        NomPregunta = nomVariable;
                        if(ispalote) {
                            String[] values = respuesta.split("\\|");
                            for(int ireg = 0;ireg<values.length;ireg++){
                                String[] valores = values[ireg].split(",");
                                String codRspta = valores[0].trim();
                                String nomRspta = valores[1].trim();
                                NomPregunta = nomVariable.trim()+ " - " + nomRspta;
                                if(tipocampo.contains("checkbox")){
                                    CodPregunta = idVariable.trim() + "___" + codRspta.trim();
                                    ModelPregunta model2 = new ModelPregunta();
                                    Pregunta preg2 = new Pregunta();
                                    preg2.setCodpregunta(CodPregunta);
                                    preg2.setNompregunta(NomPregunta);
                                    preg2.setTipocampo(tipocampo);
                                    preg2.setFormulario(formulario);
                                    model2.insertaPregunta(preg1);
                
                                }
                                
                                ModelRespuesta model3 = new ModelRespuesta();
                                Respuesta rspta = new Respuesta();
                                rspta.setCodPregunta(CodPregunta);
                                rspta.setCodRespuesta(codRspta);
                                rspta.setNomRespuesta(nomRspta);
                                
                                model3.insertaRespuesta(rspta);                                  
                                }
                        }
                     }                
                  }
                }
            csvReader.close();           
        } catch(Exception e) {
            System.err.println(e.getMessage());      
        }
     }
    
    
    /*
     Importa Encuesta
    */
    protected void importarEncuesta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        try{
            String csv = uploadFile(request,"csvFile");
      //      if(!csv.equalsIgnoreCase("csv"))
        //    {
          //       request.getRequestDispatcher("/importarEncuesta.jsp").forward(request, response);
           // }
           // else
            //{
                String csvPath = request.getServletContext().getRealPath("") 
                                 + File.separator
                                 + UPLOAD_DIR
                                 + File.separator
                                 + csv;            
                
                ModelEncuesta modele = new ModelEncuesta();
                modele.eliminaEncuestaMasivo();
                int numreg;
                String archCSV = csvPath;
                CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(archCSV), "UTF-8"));
                String[] fila = null;
                fila = null;
                numreg = 0;
                String Preguntas[] =null;
                while((fila = csvReader.readNext()) != null){           
                    if(numreg == 0){
                        Preguntas = fila;
                        numreg++;
                    }
                    else {            
                        int idencuesta = Integer.parseInt(fila[0]);
                        for(int ifila = 1;ifila<fila.length;ifila++ ){       
                            String valrspta;
                            String codpregunta;
                            valrspta = fila[ifila].trim();
                            codpregunta = Preguntas[ifila].trim();
                            
                            
                            
                            if(!valrspta.isEmpty())  {
                                ModelEncuesta model4 = new ModelEncuesta();
                                Encuesta enc1 = new Encuesta();

                                enc1.setIdEncuesta(idencuesta);
                                enc1.setCodPregunta(codpregunta);
                                enc1.setValRespuesta(valrspta);

                                model4.insertaEncuesta(enc1);
                            }
                        }                   
                         numreg++;
                        }            
                   }
                   csvReader.close();                   
         } catch(Exception e) {
            System.err.println(e.getMessage());      
        }
     }
    
    
    private String getFileName(Part part)
    {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("************ PartHeader :" + partHeader);
        for(String content: part.getHeader("content-disposition").split(";"))
        {
            if(content.trim().startsWith("filename"))
            {
                return content.substring(content.indexOf('=')+1).trim().replace("\"","");
            }
        }
        return null;
    }
    private String uploadFile(HttpServletRequest request,String name){
        String fileName = "";
        try {
            Part filePart = request.getPart(name);
            fileName = getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            System.out.println("application path :" + applicationPath);
            String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
            System.out.println("basePath : " + basePath);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            try {
                File outputFilePath = new File(basePath+fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes,0,read);
                }    
            } catch (Exception ex){
                ex.printStackTrace();
                fileName = "";
            } finally{
                if(outputStream != null){
                    outputStream.close();
                }
                if(inputStream != null){
                    inputStream.close();
                }                
            }
        } catch (Exception e){
            fileName = "";
        }
        
        return fileName;
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
