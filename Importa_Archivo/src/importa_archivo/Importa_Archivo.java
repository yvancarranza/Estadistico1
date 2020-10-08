/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importa_archivo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yvans
 */
public class Importa_Archivo {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, CsvValidationException {
        // TODO code application logic here
        
        String sql = "";
        int salida = 0;
        Connection cnn = null;        
        try {       
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sanamente","root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Importa_Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PreparedStatement pstmgen;
        sql = "DELETE FROM tbpregunta ";            
        try
            {
                pstmgen = cnn.prepareStatement(sql);
                salida = pstmgen.executeUpdate();
            }
        catch (SQLException ex)
                {
                    System.out.println(ex.toString());
                }      
       
        pstmgen = null;
        sql = "DELETE FROM tbrspta ";            
        try
            {
                pstmgen = cnn.prepareStatement(sql);
                salida = pstmgen.executeUpdate();
            }
        catch (SQLException ex)
                {
                    System.out.println(ex.toString());
                }      
         pstmgen = null;
        sql = "DELETE FROM tbencuesta ";            
        try
            {
                pstmgen = cnn.prepareStatement(sql);
                salida = pstmgen.executeUpdate();
            }
        catch (SQLException ex)
                {
                    System.out.println(ex.toString());
                }           
        
        
    String archCSV = "D:\\DiccionarioVariables.csv";
    CSVReader csvReader = new CSVReader(new FileReader(archCSV));
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
                PreparedStatement pstm;
                sql = "insert into tbpregunta(idpregunta,codpregunta,nompregunta,tipocampo,formulario)" + 
                             " values(null,?,?,?,?)";            
                try
                    {
                    pstm = cnn.prepareStatement(sql);
                    pstm.setString(1,idVariable);
                    pstm.setString(2,nomVariable);
                    pstm.setString(3,tipocampo);
                    pstm.setString(4,formulario);
                    salida = pstm.executeUpdate();
                    }
                catch (SQLException ex)
                    {
                        System.out.println(nomVariable);
                        System.out.println(ex.toString());
                    }          
                numreg++;
            // ------------------------------
            // Grabar en Tabla de Respuesta
            // Identificar si se tiene definido separado por palotes
            boolean ispalote;
            ispalote = respuesta.contains("|");
            if(ispalote)
                {
                    String[] values = respuesta.split("\\|");
                    for(int ireg = 0;ireg<values.length;ireg++){
                        String[] valores = values[ireg].split(",");
                        String codRspta = valores[0].trim();
                        String nomRspta = valores[1].trim();

                        PreparedStatement pstm1;
                        String sql1 = "insert into tbrspta(idrspta,codpregunta,codrspta,nomrspta)" + 
                             " values(null,?,?,?)";            
                        try
                           {
                                pstm = cnn.prepareStatement(sql1);
                                pstm.setString(1,idVariable);
                                pstm.setString(2,codRspta);
                                pstm.setString(3,nomRspta);
                                salida = pstm.executeUpdate();
                           }
                        catch (SQLException ex)
                            {

                               System.out.println(ex.toString());
                               System.out.println(nomRspta);
                            }  
                        }
                }
             }                
          }
        }
    csvReader.close();
      
     
     // Encuestas
     
    archCSV = "D:\\DataVariables.csv";
    csvReader = new CSVReader(new FileReader(archCSV));
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
                    PreparedStatement pstm1;
                    String valrspta;
                    String codpregunta;
                    valrspta = fila[ifila].trim();
                    codpregunta = Preguntas[ifila].trim();
                    if(!valrspta.isEmpty())  {
                        String sql1 = "insert into tbencuesta(idregistro,idencuesta,codpregunta,valrspta)" + 
                             " values(null,?,?,?)";            
                        try
                           {
                                pstm1 = cnn.prepareStatement(sql1);
                                pstm1.setInt(1, idencuesta);
                                pstm1.setString(2,codpregunta);
                                pstm1.setString(3,valrspta);
                                salida = pstm1.executeUpdate();
                           }
                        catch (SQLException ex)
                            {

                               System.out.println(ex.toString());
                               System.out.println(valrspta);
                            }  
                    }
                }                   
                 numreg++;
                }            
           }
           csvReader.close();
           try {
                cnn.close();
               }
           catch (SQLException ex) {
            Logger.getLogger(Importa_Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
}
