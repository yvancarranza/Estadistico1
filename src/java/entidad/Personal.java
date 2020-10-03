
package entidad;

/**
 * @author Grupo 4
 * Yvan Carranza
 * Guillermo Gonzales
 */
public class Personal extends Persona  {
    int idpersonal;
    String genero;
    String fecregistro;
    String fecmodifica;

  
    public int getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(int idpersonal) {
        this.idpersonal = idpersonal;
    }
    
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFecregistro() {
        return fecregistro;
    }

    public void setFecregistro(String fecregistro) {
        this.fecregistro = fecregistro;
    }

      public String getFecmodifica() {
        return fecmodifica;
    }

    public void setFecmodifica(String fecmodifica) {
        this.fecmodifica = fecmodifica;
    }
    
}
