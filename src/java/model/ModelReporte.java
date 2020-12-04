
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
     public boolean ProcesarEncuesta()
     
     {
    boolean salida =false;
    try
        {
            Connection conn;
            PreparedStatement pstm;
            conn =  (Connection) MySqlConexion.getConexion();
            String sql = "CALL sp_generar_resultado()";
            pstm = conn.prepareCall(sql);
            salida = pstm.execute();
            conn.close();
        }
        catch (SQLException ex)
                {
                    Logger.getLogger(ModelEncuesta.class.getName()).log(Level.SEVERE,null,ex);
                }
    return salida;
  }
     
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
                
                String sql = " SELECT rep.codvarfin,codresultado escala,var.tipovar,sum(Femenino) Femenino," + 
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
                        "	INNER  JOIN tbvarfinal var ON (rep.codvarfin = var.codvarfin) " +
                        "	WHERE  sexo = 'Femenino' AND var.tipovar = 'MEDIA' " +
                        "	GROUP BY rep.codvarfin " +
                        "   UNION ALL " +
                        "	SELECT rep.codvarfin,'MEDIA',0 AS Femenino,AVG(media)  AS Masculino,2 AS ORDEN " +
                        "	FROM   tbreporte rep "  + 
                        "	INNER  JOIN tbvarfinal var ON (rep.codvarfin = var.codvarfin) " +
                        "	WHERE  sexo = 'Masculino' AND var.tipovar = 'MEDIA' " +
                        "	GROUP BY rep.codvarfin " +
                        "	UNION ALL " +
                        "	SELECT rep.codvarfin,'DESVIACION',AVG(desviacion) AS Femenino,0 AS Masculino,1 AS ORDEN " +
                        "	FROM   tbreporte rep " +
                        "	INNER  JOIN tbvarfinal var ON (rep.codvarfin = var.codvarfin) " +
                        "	WHERE  sexo = 'Femenino' AND var.tipovar = 'MEDIA' " +
                        "	GROUP BY rep.codvarfin " +
                        "   UNION ALL " +
                        "	SELECT rep.codvarfin,'DESVIACION',0 AS Femenino,AVG(desviacion)  AS Masculino,2 AS ORDEN " +
                        "	FROM   tbreporte rep " +
                        "	INNER  JOIN tbvarfinal var ON (rep.codvarfin = var.codvarfin) " +
                        "	WHERE  sexo = 'Masculino' AND var.tipovar = 'MEDIA' " +
                        "	GROUP BY rep.codvarfin" +
                        "  ) rep " +
                        " INNER JOIN tbvarfinal var ON (rep.CodVarfin = var.codvarfin) " + 
                        " WHERE rep.codvarfin = ? " +
                        " GROUP BY rep.codvarfin,codresultado,var.tipovar " +
                        "ORDER BY rep.codvarfin,ORDEN,codresultado ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1,codvarfin);            
                resultado = pstm.executeQuery();
                
                while (resultado.next()){
                    if(resultado.getString("escala").equalsIgnoreCase("OUT_RANGE"))
                    {
                        //
                    }                        
                    else{
                    reporte = new Reporte();
                    reporte.setCodvarfin(resultado.getString("codvarfin"));
                    reporte.setCodresultado(resultado.getString("escala"));
                    reporte.setTipovar(resultado.getString("tipovar"));
                    reporte.setCta_masculino(resultado.getDouble("Masculino"));
                    reporte.setCta_femenino(resultado.getDouble("Femenino"));
                    reporte.setCta_total(resultado.getDouble("Total"));
                    reportes.add(reporte);
                            }
                }                
                conn.close();
            
        }
        catch (SQLException ex) {
                    Logger.getLogger(ModelEncuesta.class.getName()).log(Level.SEVERE,null,ex);
        } 
    return reportes;
  }
    
   public List<Reporte> ListarReporte2(String codvarfin) {
        Connection conn;
        Reporte reporte;
        PreparedStatement pstm;
        ResultSet resultado=null;
        List<Reporte> reportes = new ArrayList<Reporte>();
        try {
                conn =  (Connection) MySqlConexion.getConexion();                
                String sql = "CALL sp_consultar_repregion()";
                pstm = conn.prepareCall(sql);
                resultado = pstm.executeQuery();
                
                while (resultado.next()){
                    if(resultado.getString("codvarfin").equalsIgnoreCase(codvarfin))
                    {
                    reporte = new Reporte();
                    reporte.setCodvarfin(resultado.getString("codvarfin"));
                    reporte.setDepartamento(resultado.getString("departamento"));
                    reporte.setTipovar(resultado.getString("tipovar"));
                    reporte.setCta_ninguno(resultado.getDouble("Ninguno"));
                    reporte.setCta_bajo(resultado.getDouble("Bajo"));
                    reporte.setCta_normal(resultado.getDouble("Normal"));
                    reporte.setCta_medio(resultado.getDouble("Medio"));
                    reporte.setCta_moderado(resultado.getDouble("Moderado"));
                    reporte.setCta_alta(resultado.getDouble("Alta"));
                    reporte.setCta_severo(resultado.getDouble("Severo"));
                    reporte.setCta_extremo(resultado.getDouble("Extremo"));
                    reporte.setMedia(resultado.getDouble("media"));
                    reporte.setDesviacion(resultado.getDouble("Desviacion"));
                    reporte.setCta_total(resultado.getDouble("Total"));
                    reportes.add(reporte);
                            }
                }                
                conn.close();
            
        }
        catch (SQLException ex) {
                    Logger.getLogger(ModelEncuesta.class.getName()).log(Level.SEVERE,null,ex);
        } 
    return reportes;
  } 
    
}

