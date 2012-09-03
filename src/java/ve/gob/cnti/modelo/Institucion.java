package ve.gob.cnti.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Define la estructura del tipo de dato #TipoInstitucion
 * para su propio archivo XSD en la definicion del esquema del WSDL
 * bajo el namespace http://www.cnti.gob.ve/schema/directorio/TipoInstitucion.
 * 
 * @author Danielle Mariani
 */
@XmlRootElement(name="Institucion",
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoInstitucion")
@XmlType(name = "TipoInstitucion", propOrder = {
        "idInstitucion",
        "director",
        "nombreInstitucion",
        "direccion",
        "telefono",
        "paginaWeb",
        "correoElect",
        "poder",
        "fecha"},
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoInstitucion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Institucion {

    private int idInstitucion;
    private String nombreInstitucion;
    private String director;
    private String direccion;
    private String telefono;
    private String paginaWeb;
    private String correoElect;
    private int poder;
    private String fecha;

    public Institucion() {
    }

    public Institucion(int idInstituto, String nombreInstituto, String director
            , String direccion, String telefono, String web, String correoElect, int poder,String fecha) {
        this.idInstitucion = idInstituto;
        this.nombreInstitucion = nombreInstituto;
        this.director = director;
        this.direccion = direccion;
        this.telefono = telefono;
        this.paginaWeb = web;
        this.correoElect = correoElect;
        this.poder = poder;
        this.fecha = fecha;
    }


    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCorreoElect() {
        return correoElect;
    }

    public void setCorreoElect(String correoElect) {
        this.correoElect = correoElect;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
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
        return "instituto:" + idInstitucion + ":" + nombreInstitucion + ":" + director;
    }
}
