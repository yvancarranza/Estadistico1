package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Grupo 4
 * Yvan Carranza
 * Guillermo Gonzales
 * Jhosep Garcia
 */
public class MySqlConexion {
    //Creo una clase estatica
    public static Connection getConexion() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException ex){
            Logger.getLogger(MySqlConexion.class.getName()).log(Level.SEVERE, null, ex);
        }               
        Connection cnn;        
        //cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/estadistico","root", "");       
        //cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sanamente","root", "");       
       cnn = DriverManager.getConnection("jdbc:mysql://node59701-env-tomcat.jelastic.saveincloud.net:3306/estadistico","root", "PQGhbm23227");       
        //node59677-env-1093214.jelastic.saveincloud.net
        return cnn;
    }
}
