package entidad;

/**
 *
 * @author Grupo 4
 * Yvan Carranza
 * Guillermo Gonzales
 * Jhosep Garcia
 */
public class Usuario {
    String idusuario;
    String nomusuario;

    public String getNomusuario() {
        return nomusuario;
    }

    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }
    String password;
    String tipodeusuario;
    String estado;
    String tienecaducidad;
    String fechacaduca;
    String fecregistro;
    String fecmodifica;
    String fecacceso;
    Persona persona;
    int   idpersona;
    public Persona getPersona() {
        return persona;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public String getPassword() {
        return password;
    }

    public String getTipodeusuario() {
        return tipodeusuario;
    }

    public String getEstado() {
        return estado;
    }

    public String getTienecaducidad() {
        return tienecaducidad;
    }

    public String getFechacaduca() {
        return fechacaduca;
    }

    public String getFecregistro() {
        return fecregistro;
    }

    public String getFecmodifica() {
        return fecmodifica;
    }

    public String getFecacceso() {
        return fecacceso;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTipodeusuario(String tipodeusuario) {
        this.tipodeusuario = tipodeusuario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTienecaducidad(String tienecaducidad) {
        this.tienecaducidad = tienecaducidad;
    }

    public void setFechacaduca(String fechacaduca) {
        this.fechacaduca = fechacaduca;
    }

    public void setFecregistro(String fecregistro) {
        this.fecregistro = fecregistro;
    }

    public void setFecmodifica(String fecmodifica) {
        this.fecmodifica = fecmodifica;
    }

    public void setFecacceso(String fecacceso) {
        this.fecacceso = fecacceso;
    }
}
