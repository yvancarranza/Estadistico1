package model;

import conexion.MySqlConexion;
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
                String sql = " SELECT codvarfin,nomvarfin " +
                        " FROM tbvarfinal ";
                        
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
}
