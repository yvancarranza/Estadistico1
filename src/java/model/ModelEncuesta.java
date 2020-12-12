
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
            String sql = "insert into tbencuesta(idregistro,idencuesta,codpregunta,valrespuesta)" + 
                         " values(null,?,?,?)";            
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1,obj.getIdEncuesta());
            pstm.setString(2,obj.getCodPregunta());
            if(obj.getValRespuesta().length() > 49)
                pstm.setString(3, obj.getValRespuesta().substring(0, 48));
            else
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
             String sql = "delete from tbencuesta ";
            pstm = conn.prepareStatement(sql);
            salida = pstm.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelEncuesta.class.getName()).log(Level.SEVERE, null, ex);
        }      
         return salida;
  }
}
