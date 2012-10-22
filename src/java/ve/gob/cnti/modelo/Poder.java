package ve.gob.cnti.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Define la estructura del tipo de dato #TipoPoder
 * del esquema del WSDL bajo el 
 * namespace http://www.cnti.gob.ve/schema/directorio/TipoPoder
 * 
 * @author Danielle Mariani
 */
@XmlRootElement(name="Poder",
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoPoder")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoPoder", propOrder = {
        "idPoder",
        "nombrePoder",
        "descripcion"},
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoPoder")
public class Poder {
    
    private long idPoder;
    private String nombrePoder;
    private String descripcion;

    public Poder() {
    }

    public Poder(long idPoder, String nombrePoder, String descripcion) {
        this.idPoder = idPoder;
        this.nombrePoder = nombrePoder;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public long getIdPoder() {
        return idPoder;
    }

    public String getNombrePoder() {
        return nombrePoder;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdPoder(long idPoder) {
        this.idPoder = idPoder;
    }

    public void setNombrePoder(String nombrePoder) {
        this.nombrePoder = nombrePoder;
    }

    @Override
    public String toString() {
        return "poder:" + idPoder + ":" + nombrePoder + ":" + descripcion;
    }
    
    
    
    
}
