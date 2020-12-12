package model;

import conexion.MySqlConexion;
import entidad.Persona;
import entidad.Usuario;
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
public class ModelUsuario {
    public int insertaUsuario(Usuario obj)
{
    int salida =-1;
    try
        {
            Connection conn;
            PreparedStatement pstm;
            conn =  (Connection) MySqlConexion.getConexion();
            String sql = "insert into tbusuario(idusuario,password,tipodeusuario,estado,tienecaducidad,idpersona)" + 
                         "values(?,?,?,?,?,?)";            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,obj.getIdusuario());
            pstm.setString(2, obj.getPassword());
            pstm.setString(3, obj.getTipodeusuario());
            pstm.setString(4, obj.getEstado() );
            pstm.setString(5, obj.getTienecaducidad() );
            pstm.setInt(6, obj.getIdpersona());
            salida = pstm.executeUpdate();
        }
        catch (SQLException ex)
                {
                    if(ex.getSQLState().equalsIgnoreCase("23000"))
                        System.out.println("USUARIO " + obj.getIdusuario() + " YA ESTA REGISTRADO EN BASE DE DATOS");
                    else
                    Logger.getLogger(ModelUsuario.class.getName()).log(Level.SEVERE,null,ex);
                }
    return salida;
  }

  public List<Usuario> listaUsuario()
  {
    Persona objpersona;
    List<Usuario> data = new ArrayList<>();
        try {

            Connection conn;
            PreparedStatement pstm;
            ResultSet rs;
            conn = MySqlConexion.getConexion();
            String sql = "Select us.*,per.nombres,per.apellidos from tbusuario us INNER JOIN tbpersonal per on (per.correo = us.idusuario) ";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            Usuario obj;
            
            while (rs.next())
            {
                obj = new Usuario();
                obj.setIdusuario(rs.getString("idusuario"));
                obj.setPassword(rs.getString("password"));
                obj.setFecacceso(rs.getString("fecacceso"));
                objpersona = new Persona();
                objpersona.setNombres(rs.getString("nombres"));
                objpersona.setApellidos(rs.getString("apellidos"));
                obj.setPersona(objpersona);
                data.add(obj);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(ModelUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }      
   return data;
  } 
   
  public Usuario buscaUsuario(String idusuario)
  {
       Usuario obj = null;
       try
        {
            Connection conn;
            PreparedStatement pstm;
            ResultSet rs;
            
            conn = MySqlConexion.getConexion();
            if (conn == null){
                System.out.print("No se pudo conectar a la Base de Datos");
                obj = new Usuario();
                obj.setIdusuario("ERROR");
                obj.setNomusuario("No se pudo conectar a la Base de Datos - " + MySqlConexion.mensajeconexion);
            }
            else{
            //String sql = "Select * from tbusuario ";
            String sql = "Select us.*,per.nombres,per.apellidos from tbusuario us INNER JOIN tbpersonal per on (per.correo = us.idusuario) where us.idusuario = ?";
            pstm =  conn.prepareStatement(sql);
            pstm.setString(1,idusuario);
            rs = pstm.executeQuery();
            if(rs.next())
            {
                obj = new Usuario();
                obj.setIdusuario(rs.getString("idusuario"));
                obj.setPassword(rs.getString("password"));
                obj.setNomusuario(rs.getString("nomusuario"));
                obj.setTipodeusuario(rs.getString("tipodeusuario"));
                obj.setFecacceso(rs.getString("fecacceso"));
                obj.setFechacaduca(rs.getString("fechacaduca"));
                obj.setFecregistro(rs.getString("fecregistro"));
                obj.setFecregistro(rs.getString("fecmodifica"));
                obj.setNomusuario(rs.getString("nombres").trim() + " " + rs.getString("apellidos").trim());
                obj.setIdpersona(rs.getInt("idpersona"));
            }
        }
        }
       catch (SQLException ex)
       {
           
           Logger.getLogger(ModelUsuario.class.getName()).log(Level.SEVERE, null, ex);
       }       
       
       return obj;
  }
  
  public int actualizaUsuario(Usuario obj)
{
    int salida =-1;
    try
        {
            Connection conn;
            PreparedStatement pstm;
            conn =  (Connection) MySqlConexion.getConexion();
            String sql = "update tbusuario set password = ? where idusuario = ? "; 
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,obj.getPassword());
            pstm.setString(2,obj.getIdusuario() );
            salida = pstm.executeUpdate();
        }
        catch (SQLException ex)
                {
                    if(ex.getSQLState().equalsIgnoreCase("23000"))
                        System.out.println("USUARIO " + obj.getIdusuario() + " YA ESTA REGISTRADO EN BASE DE DATOS");
                    else
                    Logger.getLogger(ModelUsuario.class.getName()).log(Level.SEVERE,null,ex);
                }
    return salida;
  }

     public int eliminaUsuario(String p_idusuario)
{
    int salida =-1;
    try
        {
            Connection conn;
            PreparedStatement pstm;
            conn =  (Connection) MySqlConexion.getConexion();
            String sql = "delete from tbusuario where idusuario = ? ";           
            pstm = conn.prepareStatement(sql);
            pstm.setString(1,p_idusuario);
            salida = pstm.executeUpdate();
        }
        catch (SQLException ex)
                {
                    if(ex.getSQLState().equalsIgnoreCase("23000"))
                        System.out.println("USUARIO " + p_idusuario + " YA ESTA REGISTRADO EN BASE DE DATOS");
                    else
                    Logger.getLogger(ModelUsuario.class.getName()).log(Level.SEVERE,null,ex);
                }
    return salida;
  }
  
}
