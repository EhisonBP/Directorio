package ve.gob.cnti.falla;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Define la estructura del tipo de dato #TipoError
 * para el archivo XSD en la definicion del esquema del WSDL.
 * 
 * @author Danielle Mariani
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoError", 
        propOrder = {"codigo","descripcion","detallesTecnicos"},
        namespace = "http://www.cnti.gob.ve/schema/error/TipoError")
public class TipoError {
    
    private String codigo;
    private String descripcion;
    private String detallesTecnicos;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetallesTecnicos() {
        return detallesTecnicos;
    }

    public void setDetallesTecnicos(String detallesTecnicos) {
        this.detallesTecnicos = detallesTecnicos;
    }
   
}