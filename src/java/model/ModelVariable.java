package model;

import conexion.MySqlConexion;
import entidad.Reporte;
import entidad.Variable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelVariable {
    public List<Variable> Listar() {
        Connection conn;
        Variable variable;
        PreparedStatement pstm;
        ResultSet resultado=null;
        List<Variable> variables = new ArrayList<Variable>();
        try {
                conn =  (Connection) MySqlConexion.getConexion();
                String sql = " SELECT DISTINCT var.codvarfin,var.nomvarfin " +
                             " FROM tbvarfinal var " + 
                             " INNER JOIN tbreporte rep ON (rep.codvarfin = var.codvarfin) ";
                        
                pstm = conn.prepareStatement(sql);
                resultado = pstm.executeQuery();                
                while (resultado.next()){
                    variable = new Variable();
                    variable.setCodvarfin(resultado.getString("codvarfin"));
                    variable.setNomvarfin(resultado.getString("nomvarfin"));
                    variables.add(variable);
                }                
                conn.close();            
        }
        catch (SQLException ex) {
                    Logger.getLogger(ModelEncuesta.class.getName()).log(Level.SEVERE,null,ex);
        } 
    return variables;
    }   
    
 public List<Variable> ListarIndicador(String codvarfin) {
        Connection conn;
        Variable variable;
        PreparedStatement pstm;
        ResultSet resultado=null;
        List<Variable> variables = new ArrayList<Variable>();
        try {
                conn =  (Connection) MySqlConexion.getConexion();
                String sql = " SELECT DISTINCT codresultado,orden " +
                        " FROM tbreporte " + 
                        " WHERE codresultado <> 'OUT_RANGE' and codvarfin = ? " + 
                        " ORDER BY orden ";

                pstm = conn.prepareStatement(sql);
                pstm.setString(1,codvarfin);
                resultado = pstm.executeQuery();                
                while (resultado.next()){
                    variable = new Variable();
                    variable.setCodresultado(resultado.getString("codresultado"));
                    variables.add(variable);
                }                
                conn.close();            
        }
        catch (SQLException ex) {
                    System.out.print(ex.getErrorCode());
                    Logger.getLogger(ModelEncuesta.class.getName()).log(Level.SEVERE,null,ex);
        } 
    return variables;
    }   
    
 
}
