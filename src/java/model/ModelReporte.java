
package model;

import conexion.MySqlConexion;
import entidad.Encuesta;
import entidad.Reporte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelReporte {
      
    public List<Reporte> ListarReporte1(String codvarfin) {
        Connection conn;
        Reporte reporte;
        PreparedStatement pstm;
        ResultSet resultado=null;
        List<Reporte> reportes = new ArrayList<Reporte>();
        try {
                conn =  (Connection) MySqlConexion.getConexion();
                String sql = " SELECT codvarfin,codresultado escala,sum(Femenino) as Femenino,SUM(masculino) as Masculino," + 
                             " SUM(Femenino + Masculino) Total " + 
                "FROM  (" +
                        "	SELECT rep.codvarfin,codresultado,0 AS Femenino,COUNT(*) as Masculino " +
                        "	FROM   tbreporte rep " +
                        "	WHERE  sexo = 'Masculino' " +
                        "	GROUP BY rep.codvarfin,codresultado " +
                        "	UNION ALL " +
                        "	SELECT rep.codvarfin,codresultado,COUNT(*)  AS Femenino,0 AS Masculino " +
                        "	FROM   tbreporte rep " +
                        "	WHERE  sexo = 'Femenino' " +
                        "	GROUP BY rep.codvarfin,codresultado " +
                "      ) ABC " +
                " WHERE codvarfin = ? " +
                "GROUP BY codvarfin,codresultado";            
                
                pstm = conn.prepareStatement(sql);
                pstm.setString(1,codvarfin);            
                resultado = pstm.executeQuery();
                
                while (resultado.next()){
                    reporte = new Reporte();
                    reporte.setCodvarfin(resultado.getString("codvarfin"));
                    reporte.setCodresultado(resultado.getString("escala"));
                    reporte.setCta_masculino(resultado.getInt("Masculino"));
                    reporte.setCta_femenino(resultado.getInt("Femenino"));
                    reporte.setCta_total(resultado.getInt("Total"));
                    reportes.add(reporte);
                }                
                conn.close();
            
        }
        catch (SQLException ex) {
                    Logger.getLogger(ModelEncuesta.class.getName()).log(Level.SEVERE,null,ex);
        } 
    return reportes;
  }
}

