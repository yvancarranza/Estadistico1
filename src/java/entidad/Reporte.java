package entidad;
public class Reporte {
    String codvarfin;
    String codresultado;
    double  suma;
    double  media;
    double  desviacion;   
    Integer cta_masculino;
    Integer cta_femenino;
    Integer cta_total;
    
    public String getCodvarfin() {
        return codvarfin;
    }

    public void setCodvarfin(String codvarfin) {
        this.codvarfin = codvarfin;
    }

    public String getCodresultado() {
        return codresultado;
    }

    public void setCodresultado(String codresultado) {
        this.codresultado = codresultado;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getDesviacion() {
        return desviacion;
    }

    public void setDesviacion(double desviacion) {
        this.desviacion = desviacion;
    }

    public Integer getCta_masculino() {
        return cta_masculino;
    }

    public void setCta_masculino(Integer cta_masculino) {
        this.cta_masculino = cta_masculino;
    }

    public Integer getCta_femenino() {
        return cta_femenino;
    }

    public void setCta_femenino(Integer cta_femenino) {
        this.cta_femenino = cta_femenino;
    }

    public Integer getCta_total() {
        return cta_total;
    }

    public void setCta_total(Integer cta_total) {
        this.cta_total = cta_total;
    }
  
}
