package ve.gob.cnti.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * Define la estructura del tipo de dato #TipoTramite del esquema del WSDL bajo
 * el namespace http://www.cnti.gob.ve/schema/directorio/TipoTramite
 *
 * @author Danielle Mariani
 */
@XmlRootElement(name = "Tramite",
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoTramite")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoTramite", propOrder = {
    "idTramite",
    "nombreTramite",
    "descripcion",
    "telefono",
    "horarios",
    "direccion",
    "requisitos",
    "nombreInstitucion",
    "fecha"},
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoTramite")
public class Tramite {

    private int idTramite;
    private String nombreTramite;
    private String telefono;
    private String horarios;
    private String direccion;
    private String descripcion;
    private String requisitos;
    private String fecha;
    private String nombreInstitucion;

    public Tramite() {
    }

    public Tramite(int idTramite, String nombre) {
        this.idTramite = idTramite;
        this.nombreTramite = nombre;
    }

    public Tramite(int idTramite, String nombreTramite, String telefono, String horarios, String direccion,
            String descripcion, String requisitos, String fecha) {
        this.idTramite = idTramite;
        this.nombreTramite = nombreTramite;
        this.telefono = telefono;
        this.horarios = horarios;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.requisitos = requisitos;
        this.fecha = fecha;
    }

    public Tramite(int idTramite, String nombreTramite, String telefono, String horarios, String direccion,
            String requisitos, String nombreInstitucion) {
        this.idTramite = idTramite;
        this.nombreTramite = nombreTramite;
        this.telefono = telefono;
        this.horarios = horarios;
        this.direccion = direccion;
        this.requisitos = requisitos;
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(int idTramite) {
        this.idTramite = idTramite;
    }

    public String getNombreTramite() {
        return nombreTramite;
    }

    public void setNombreTramite(String nombreTramite) {
        this.nombreTramite = nombreTramite;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }
}
