
package model;

import entidad.Respuesta;
import conexion.MySqlConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
public class ModelRespuesta {
 
       public int insertaRespuesta(Respuesta obj)
{
    int salida =-1;
    try
        {
            Connection conn;
            PreparedStatement pstm;
            conn =  (Connection) MySqlConexion.getConexion();
            String sql = "insert into tbrespuesta(idrespuesta,codpregunta,codrespuesta,nomrespuesta)" + 
                         " values(null,?,?,?)";            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,obj.getCodPregunta());
            pstm.setString(2,obj.getCodRespuesta());
            pstm.setString(3, obj.getNomRespuesta());
            salida = pstm.executeUpdate();
             conn.close();
        }
        catch (SQLException ex)
                {
                    Logger.getLogger(ModelRespuesta.class.getName()).log(Level.SEVERE,null,ex);
                }
    return salida;
  }

   public int eliminaRespuestaMasivo (){
       int salida = -1;
               
        try {
            Connection conn;
            PreparedStatement pstm;
            conn = MySqlConexion.getConexion();
             String sql = "delete from tbrespuesta ";
            pstm = conn.prepareStatement(sql);
            salida = pstm.executeUpdate();
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }      
         return salida;
  }
    
}
