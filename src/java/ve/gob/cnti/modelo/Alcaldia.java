package ve.gob.cnti.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * Define la estructura del tipo de dato #TipoPoder del esquema del WSDL bajo el
 * namespace http://www.cnti.gob.ve/schema/directorio/TipoPoder
 *
 * @author Danielle Mariani
 */
@XmlRootElement(name = "Alcaldia",
namespace = "http://www.cnti.gob.ve/schema/directorio/TipoAlcaldia")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoAlcaldia", propOrder = {
    "idAlcaldia",
    "nombreMunicipio",
    "nombreAlcaldia",
    "directorAlcaldia",
    "direccionAlcaldia",
    "telefonoAlcaldia",
    "webAlcaldia",
    "correoAlcaldia",
    "fecha",
    "poder",
    "estado"},
namespace = "http://www.cnti.gob.ve/schema/directorio/TipoAlcaldia")
public class Alcaldia {

    private int idAlcaldia;
    private String nombreMunicipio;
    private String nombreAlcaldia;
    private String directorAlcaldia;
    private String direccionAlcaldia;
    private String telefonoAlcaldia;
    private String webAlcaldia;
    private String correoAlcaldia;
    private String fecha;
    private int poder;
    private int estado;

    public Alcaldia() {
    }

    public Alcaldia(int idAlcaldia, String nombreAlcaldia, String nombreMunicipio, String directorAlcaldia, String direccionAlcaldia, String telefonoAlcaldia, String webAlcaldia, String correoAlcaldia, String fecha, int poder, int estado) {
        this.idAlcaldia = idAlcaldia;
        this.nombreAlcaldia = nombreAlcaldia;
        this.nombreMunicipio = nombreMunicipio;
        this.directorAlcaldia = directorAlcaldia;
        this.direccionAlcaldia = direccionAlcaldia;
        this.telefonoAlcaldia = telefonoAlcaldia;
        this.webAlcaldia = webAlcaldia;
        this.correoAlcaldia = correoAlcaldia;
        this.fecha = fecha;
        this.poder = poder;
        this.estado = estado;
    }

    public Alcaldia(int idAlcaldia, String nombreAlcaldia, int poder, int estado) {
        this.idAlcaldia = idAlcaldia;
        this.nombreAlcaldia = nombreAlcaldia;
        this.poder = poder;
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public long getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public String getCorreoAlcaldia() {
        return correoAlcaldia;
    }

    public void setCorreoAlcaldia(String correoAlcaldia) {
        this.correoAlcaldia = correoAlcaldia;
    }

    public String geWebAlcaldia() {
        return webAlcaldia;
    }

    public void setWebAlcaldia(String web) {
        this.webAlcaldia = web;
    }

    public String getTelefonoAlcaldiaLcaldia() {
        return telefonoAlcaldia;
    }

    public void setTelefonoAlcaldia(String telefonoAlcaldia) {
        this.telefonoAlcaldia = telefonoAlcaldia;
    }

    public String getDireccionALcaldia() {
        return direccionAlcaldia;
    }

    public void setDireccionAlcaldia(String direccionAlcaldia) {
        this.direccionAlcaldia = direccionAlcaldia;
    }

    public String getDirectorALcaldia() {
        return directorAlcaldia;
    }

    public void setDirectorAlcaldia(String director) {
        this.directorAlcaldia = director;
    }

    public long getIdAlcaldia() {
        return idAlcaldia;
    }

    public String getNombreAlcaldia() {
        return nombreAlcaldia;
    }

    public void setIdAlcaldia(int idPoder) {
        this.idAlcaldia = idPoder;
    }

    public void setNombreAlcaldia(String nombreAlcaldia) {
        this.nombreAlcaldia = nombreAlcaldia;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    @Override
    public String toString() {
        return "alcaldia:" + idAlcaldia + ":" + nombreAlcaldia + ":" + directorAlcaldia;
    }
}
