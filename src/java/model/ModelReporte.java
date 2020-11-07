
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
                /*String sql = " SELECT codvarfin,codresultado escala,sum(Femenino) as Femenino,SUM(masculino) as Masculino," + 
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
                */
                
                String sql = " SELECT codvarfin,codresultado escala,sum(Femenino) Femenino," + 
                        " SUM(masculino) Masculino,SUM(Femenino + Masculino) Total, " +
                        "       SUM(ORDEN) AS ORDEN " +
                        "FROM  ( " +
                        "	SELECT rep.codvarfin,codresultado,0 AS Femenino,COUNT(*) Masculino,MAX(ORDEN) AS ORDEN " +
                        "	FROM   tbreporte rep " +
                        "	WHERE  sexo = 'Masculino' " +
                        "	GROUP BY rep.codvarfin,codresultado " +
                        "	UNION ALL " +
                        "	SELECT rep.codvarfin,codresultado,COUNT(*)  AS Femenino,0 AS Masculino,MAX(ORDEN) AS ORDEN " +
                        "	FROM   tbreporte rep " +
                        "	WHERE  sexo = 'Femenino' " +
                        "	GROUP BY rep.codvarfin,codresultado " +
                        "	UNION ALL " +
                        "	SELECT rep.codvarfin,'TOTAL',0 AS Femenino,COUNT(*) Masculino,0 AS ORDEN " +
                        "	FROM   tbreporte rep " +
                        "	WHERE  sexo = 'Masculino' " +
                        "	GROUP BY rep.codvarfin " +
                        "	UNION ALL " +
                        "	SELECT rep.codvarfin,'TOTAL',COUNT(*)  AS Femenino,0 AS Masculino,0 AS ORDEN " +
                        "	FROM   tbreporte rep " +
                        "	WHERE  sexo = 'Femenino' " +
                        "	GROUP BY rep.codvarfin " +
                        "	UNION ALL " +
                        "	SELECT rep.codvarfin,'MEDIA',AVG(media) AS Femenino,0 AS Masculino,1 AS ORDEN " +
                        "	FROM   tbreporte rep " +
                        "	WHERE  sexo = 'Femenino' AND rep.codvarfin = 'RESILIENCIA' " +
                        "   UNION ALL " +
                        "	SELECT rep.codvarfin,'MEDIA',0 AS Femenino,AVG(media)  AS Masculino,2 AS ORDEN " +
                        "	FROM   tbreporte rep " +
                        "	WHERE  sexo = 'Masculino' AND rep.codvarfin = 'RESILIENCIA' " +
                        "	UNION ALL " +
                        "	SELECT rep.codvarfin,'DESVIACION',AVG(desviacion) AS Femenino,0 AS Masculino,1 AS ORDEN " +
                        "	FROM   tbreporte rep " +
                        "	WHERE  sexo = 'Femenino' AND rep.codvarfin = 'RESILIENCIA' " +
                        "   UNION ALL " +
                        "	SELECT rep.codvarfin,'DESVIACION',0 AS Femenino,AVG(desviacion)  AS Masculino,2 AS ORDEN " +
                        "	FROM   tbreporte rep " +
                        "	WHERE  sexo = 'Masculino' AND rep.codvarfin = 'RESILIENCIA' " +                                
                        "  ) ABC " +
                        " WHERE codvarfin = ? " +
                        " GROUP BY codvarfin,codresultado " +
                        "ORDER BY codvarfin,ORDEN,codresultado ";
                
                
                
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

