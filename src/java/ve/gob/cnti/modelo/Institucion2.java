package ve.gob.cnti.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * Define la estructura del tipo de dato #TipoInstitucion
 * para su propio archivo XSD en la definicion del esquema del WSDL
 * bajo el namespace http://www.cnti.gob.ve/schema/directorio/TipoInstitucion2.
 *
 * @author Danielle Mariani
 */
@XmlRootElement(name="Institucion2",
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoInstitucion2")
@XmlType(name = "TipoInstitucion2", propOrder = {
        "idInstitucion",
        "nombreInstitucion",
        "descripcion",
        "direccion",
        "telefono",
        "paginaWeb"},
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoInstitucion2")
@XmlAccessorType(XmlAccessType.FIELD)
public class Institucion2 {

    private int idInstitucion;
    private String nombreInstitucion;
    private String descripcion;
    private String direccion;
    private int telefono;
    private String paginaWeb;

    public Institucion2() {
    }

    public Institucion2(int idInstituto, String nombreInstituto,
            String descripcion, String direccion, int telefono, String web) {
        this.idInstitucion = idInstituto;
        this.nombreInstitucion = nombreInstituto;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.paginaWeb = web;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstituto) {
        this.idInstitucion = idInstituto;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstituto) {
        this.nombreInstitucion = nombreInstituto;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String gePaginatWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String web) {
        this.paginaWeb = web;
    }

    @Override
    public String toString() {
        return "instituto:" + idInstitucion + ":" + nombreInstitucion + ":" + descripcion;
    }
}

