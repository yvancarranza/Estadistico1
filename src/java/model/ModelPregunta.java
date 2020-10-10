package model;

import conexion.MySqlConexion;
import entidad.Pregunta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelPregunta {
     public int insertaPregunta(Pregunta obj)
{
    int salida =-1;
    try
        {
            Connection conn;
            PreparedStatement pstm;
            conn =  (Connection) MySqlConexion.getConexion();
            String sql = "insert into tbpregunta(idpregunta,codpregunta,nompregunta,tipocampo,formulario)" + 
                         " values(null,?,?,?,?)";            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,obj.getCodpregunta());
            pstm.setString(2,obj.getNompregunta());
            pstm.setString(3, obj.getTipocampo());
            pstm.setString(4, obj.getFormulario());
            salida = pstm.executeUpdate();
            conn.close();
        }
        catch (SQLException ex)
                {
                    Logger.getLogger(ModelPregunta.class.getName()).log(Level.SEVERE,null,ex);
                }
    return salida;
  }

   public int eliminaPreguntaMasivo (){
       int salida = -1;
               
        try {
            Connection conn;
            PreparedStatement pstm;
            conn = MySqlConexion.getConexion();
             String sql = "delete from tbpregunta ";
            pstm = conn.prepareStatement(sql);
            salida = pstm.executeUpdate();
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModelPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }      
         return salida;
  }
}
