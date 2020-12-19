package model;
import entidad.Usuario;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelUsuarioTest {
    
    public ModelUsuarioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * CASO DE PRUEBA CUANDO SE INTENTA INSERTAR USUARIO QUE NO EXISTE
     */    
    @Test
    public void testInsertaUsuario_NoExiste() {
        String IDRandom = java.util.UUID.randomUUID().toString().substring(1,10);
        String cUsuario = IDRandom + "A@peru.com";
        System.out.println("insertaUsuario");
        Usuario oUsuario = new Usuario();
        oUsuario.setIdusuario(cUsuario);
        oUsuario.setNomusuario(cUsuario);
        oUsuario.setTipodeusuario("Personal");
        oUsuario.setEstado("ACTIVO");
        oUsuario.setPassword("c3t3p5o");
        oUsuario.setTienecaducidad("NO");
        oUsuario.setIdpersona(1);
        Usuario obj = oUsuario;
        ModelUsuario instance = new ModelUsuario();
        int expResult = 1;
        int result = instance.insertaUsuario(obj);
        assertEquals(expResult, result);
       if(expResult != result)
            fail("Falló la prueba de inserción de usuario no existente");
       else
           System.out.println("OK: Prueba inserta usuario no existe satisfactoria!");
    }
    /**
     * CASO DE PRUEBA CUANDO SE INTENTA INSERTAR USUARIO QUE YA EXISTE
     */    
  @Test
    public void testInsertaUsuario_Existe() {
        System.out.println("insertaUsuario");
        Usuario oUsuario = new Usuario();
        oUsuario.setIdusuario("guillermo@peru.com");
        oUsuario.setNomusuario("Guillermo Gonzales");
        oUsuario.setTipodeusuario("Personal");
        oUsuario.setEstado("ACTIVO");
        oUsuario.setPassword("c3t3p5o");
        oUsuario.setTienecaducidad("NO");
        oUsuario.setIdpersona(1);
        Usuario obj = oUsuario;
        ModelUsuario instance = new ModelUsuario();
        int expResult = -1;
        int result = instance.insertaUsuario(obj);
        assertEquals(expResult, result);
       if(expResult != result)
            fail("Falló la prueba de inserción de usuario existente");
       else
           System.out.println("OK: Prueba inserta usuario existente satisfactoria!");
    }

    /**
     * CASO DE PRUEBA CUANDO SE BUSCA USUARIO SI EXISTE
     */
    @Test
    public void testBuscaUsuario() {
        System.out.println("buscaUsuario");
        String idusuario = "yvan@correo.com";
        ModelUsuario instance = new ModelUsuario();
        
        Usuario salida = new Usuario();
        salida.setIdusuario(idusuario);
        //Usuario expResult = salida;
        Usuario expResult = salida;
        Usuario result = instance.buscaUsuario(idusuario);
        //assertEquals(expResult.getPassword(), result.getPassword());
        assertEquals(expResult.getIdusuario(), result.getIdusuario());
        //if (!expResult.getPassword().equalsIgnoreCase(result.getPassword()))
        if (!expResult.getIdusuario().equalsIgnoreCase(result.getIdusuario()))
            fail("Falló la prueba de usuario que si existe");
        else
            System.out.println("OK: Prueba busca usuario existente satisfactoria");
    }
    
    

    // * CASO DE PRUEBA CUANDO SE BUSCA QUE USUARIO NO EXISTE
    @Test
    public void testBuscaUsuario_Noexiste() {
        System.out.println("buscaUsuario");
        String idusuario = "abcoekwoew@correo.com";
        ModelUsuario instance = new ModelUsuario();
        Usuario expResult = null;
        Usuario result = instance.buscaUsuario(idusuario);
        assertEquals(expResult, result);
        if (expResult!=result)
            fail("Falló la prueba de usuario que NO existe");           
        else
            System.out.println("OK: Prueba busca usuario NO existente satisfactoria");
    }
    
}
