package model;

import conexion.MySqlConexion;
import entidad.Personal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Grupo 4
 * Yvan Carranza
 * Guillermo Gonzales
 * Jhosep Garcia
 */
public class ModelPersonal {
    public int insertaPersonal(Personal obj)
{
    int salida =-1;
    try
        {
            Connection conn;
            PreparedStatement pstm;
            conn =  (Connection) MySqlConexion.getConexion();
            String sql = "insert into tbpersonal(idpersonal,dni,nombres,apellidos,correo,telefono,direccion,ubigeo,genero)" + 
                         " values(null,?,?,?,?,?,?,?,?)";            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,obj.getDni());
            pstm.setString(2,obj.getNombres());
            pstm.setString(3, obj.getApellidos());
            pstm.setString(4, obj.getCorreo());
            pstm.setString(5, obj.getTelefono());
            pstm.setString(6, obj.getDireccion());
            pstm.setString(7, obj.getUbigeo());
            pstm.setString(8, obj.getGenero());
            salida = pstm.executeUpdate();
        }
        catch (SQLException ex)
                {
                    Logger.getLogger(ModelUsuario.class.getName()).log(Level.SEVERE,null,ex);
                }
    return salida;
  }

  public List<Personal> listaPersonal()
  {
    List<Personal> data = new ArrayList<>();
        try {

            Connection conn;
            PreparedStatement pstm;
            ResultSet rs;
            conn = MySqlConexion.getConexion();
            String sql = "select * from tbpersonal";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            Personal obj;
            
            while (rs.next())
            {
                obj = new Personal();
                obj.setIdpersonal(rs.getInt("idpersonal"));
                obj.setNombres(rs.getString("nombres"));
                obj.setApellidos(rs.getString("apellidos"));
                obj.setCorreo(rs.getString("correo"));
                obj.setDireccion(rs.getString("direccion"));
                obj.setDni(rs.getString("dni"));
                obj.setGenero(rs.getString("genero"));
                obj.setTelefono(rs.getString("telefono"));
                obj.setUbigeo(rs.getString("ubigeo"));
                data.add(obj);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(ModelPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }      
   return data;
  } 
   
   public int actualizaPersonal(Personal obj)
  {
    int salida  = -1;
    try {
        
            Connection conn;
            PreparedStatement pstm;
            conn = MySqlConexion.getConexion();
            String sql = "update tbpersonal set nombres = ?,apellidos = ?,dni = ?," + 
                         "telefono = ?,direccion = ?,ubigeo = ?,genero = ?" + 
                         " where idpersonal = ?";
                       
            //String escapedSQL = StringEscapeUtils.es .escapeSql(sql);
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,obj.getNombres());
            pstm.setString(2,obj.getApellidos());
            pstm.setString(3,obj.getDni());
            pstm.setString(4,obj.getTelefono());
            pstm.setString(5,obj.getDireccion());
            pstm.setString(6,obj.getUbigeo());
            pstm.setString(7,obj.getGenero());
            pstm.setInt(8,obj.getIdpersonal());
            
            salida = pstm.executeUpdate();
        } 
    catch (SQLException ex)
        {
            Logger.getLogger(ModelPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
    
   return salida;
  }   
   
   
   public int eliminaPersonal (int idPersonal){
       int salida = -1;
               
        try {
            Connection conn;
            PreparedStatement pstm;
            conn = MySqlConexion.getConexion();
             String sql = "delete from tbpersonal where idpersonal=?";
            pstm = conn.prepareStatement(sql);
             pstm.setInt(1, idPersonal);
            salida = pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ModelPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }      
         return salida;
  }
   
  public Personal buscaPersonal(int idPersonal)
  {
       Personal obj = null;
       try
        {
            Connection conn;
            PreparedStatement pstm;
            ResultSet rs;
            
            conn = MySqlConexion.getConexion();
            String sql = "Select * from tbpersonal where idpersonal = ?";
            pstm =  conn.prepareStatement(sql);
            pstm.setInt(1,idPersonal);
            rs = pstm.executeQuery();
            if(rs.next())
            {
                obj = new Personal();
                obj.setIdpersonal(rs.getInt("idpersonal"));
                obj.setNombres(rs.getString("nombres"));
                obj.setApellidos(rs.getString("apellidos"));
                obj.setCorreo(rs.getString("correo"));
                obj.setDni(rs.getString("dni"));
                obj.setDireccion(rs.getString("direccion"));
                obj.setGenero(rs.getString("genero"));
                obj.setTelefono(rs.getString("telefono"));
                obj.setUbigeo(rs.getString("ubigeo"));
            }
        }
       catch (SQLException ex)
       {
           Logger.getLogger(ModelUsuario.class.getName()).log(Level.SEVERE, null, ex);
       }       
       return obj;
  }
  
  
  public Personal buscaPersonal(String dni)
  {
       Personal obj = null;
       try
        {
            Connection conn;
            PreparedStatement pstm;
            ResultSet rs;
            
            conn = MySqlConexion.getConexion();
            String sql = "Select * from tbpersonal where dni = ?";
            pstm =  conn.prepareStatement(sql);
            pstm.setString(1,dni);
            rs = pstm.executeQuery();
            if(rs.next())
            {
                obj = new Personal();
                obj.setIdpersonal(rs.getInt("idpersonal"));
                obj.setNombres(rs.getString("nombres"));
                obj.setApellidos(rs.getString("apellidos"));
                obj.setCorreo(rs.getString("correo"));
                obj.setDni(rs.getString("dni"));
                obj.setDireccion(rs.getString("direccion"));
                obj.setGenero(rs.getString("genero"));
                obj.setTelefono(rs.getString("telefono"));
                obj.setUbigeo(rs.getString("ubigeo"));
            }
        }
       catch (SQLException ex)
       {
           Logger.getLogger(ModelPersonal.class.getName()).log(Level.SEVERE, null, ex);
       }       
       return obj;
  }
}
