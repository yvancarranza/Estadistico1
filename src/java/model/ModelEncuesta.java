
package model;

import conexion.MySqlConexion;
import entidad.Encuesta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelEncuesta {
      public int insertaEncuesta(Encuesta obj)
{
    int salida =-1;
    try
        {
            Connection conn;
            PreparedStatement pstm;
            conn =  (Connection) MySqlConexion.getConexion();
            String sql = "insert into tbEncuesta(idregistro,idencuesta,codpregunta,valrespuesta)" + 
                         " values(null,?,?,?)";            
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,obj.getIdEncuesta());
            pstm.setString(2,obj.getCodPregunta());
            pstm.setString(3, obj.getValRespuesta());
            salida = pstm.executeUpdate();
            conn.close();
        }
        catch (SQLException ex)
                {
                    Logger.getLogger(ModelEncuesta.class.getName()).log(Level.SEVERE,null,ex);
                }
    return salida;
  }

   public int eliminaEncuestaMasivo (){
       int salida = -1;
               
        try {
            Connection conn;
            PreparedStatement pstm;
            conn = MySqlConexion.getConexion();
             String sql = "delete from tbEncuesta ";
            pstm = conn.prepareStatement(sql);
            salida = pstm.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelEncuesta.class.getName()).log(Level.SEVERE, null, ex);
        }      
         return salida;
  }
}
