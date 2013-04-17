package ve.gob.cnti.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * Define la estructura del tipo de dato #TipoInstitucion para su propio archivo
 * XSD en la definicion del esquema del WSDL bajo el namespace
 * http://www.cnti.gob.ve/schema/directorio/TipoInstitucion.
 *
 * @author Danielle Mariani
 */
@XmlRootElement(name = "Institucion",
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoInstitucion")
@XmlType(name = "TipoInstitucion", propOrder = {
    "idInstitucion",
    "director",
    "nombreSector",
    "nombreInstitucion",
    "descripcion",
    "direccion",
    "telefono",
    "paginaWeb",
    "poder",
    "fecha"},
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoInstitucion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Institucion {

    private Integer idInstitucion;
    private String nombreInstitucion;
    private String nombreSector;
    private String director;
    private String direccion;
    private String telefono;
    private String paginaWeb;
    private String descripcion;
    private Integer poder;
    private String fecha;

    public Institucion() {
    }

    public Institucion(int idInstituto, String nombreInstituto, String nombreSector, String director, String direccion, String telefono, String web, int poder, String fecha) {
        this.idInstitucion = idInstituto;
        this.director = director;
        this.nombreSector = nombreSector;
        this.nombreInstitucion = nombreInstituto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.paginaWeb = web;
        this.poder = poder;
        this.fecha = fecha;
    }

    public Institucion(int idInstituto, String nombreInstituto, String director,
            String descripcion, String direccion, String telefono, String web) {
        this.idInstitucion = idInstituto;
        this.director = director;
        this.nombreInstitucion = nombreInstituto;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.paginaWeb = web;
    }

    public Institucion(int idInstitucion, String nombre) {
        this.idInstitucion = idInstitucion;
        this.nombreInstitucion = nombre;
    }

    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPoder() {
        return poder;
    }

    public void setPoder(Integer poder) {
        this.poder = poder;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "instituto:" + idInstitucion + ":" + nombreInstitucion + ":" + director;
    }
}