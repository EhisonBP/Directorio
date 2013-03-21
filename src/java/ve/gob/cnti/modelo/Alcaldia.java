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

    private Integer idAlcaldia;
    private String nombreMunicipio;
    private String nombreAlcaldia;
    private String directorAlcaldia;
    private String direccionAlcaldia;
    private String telefonoAlcaldia;
    private String webAlcaldia;
    private String correoAlcaldia;
    private String fecha;
    private Integer poder;
    private Integer estado;

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

    public Alcaldia(int idAlcaldia, String nombreAlcaldia) {
        this.idAlcaldia = idAlcaldia;
        this.nombreAlcaldia = nombreAlcaldia;
    }

    public Integer getIdAlcaldia() {
        return idAlcaldia;
    }

    public void setIdAlcaldia(int idAlcaldia) {
        this.idAlcaldia = idAlcaldia;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getNombreAlcaldia() {
        return nombreAlcaldia;
    }

    public void setNombreAlcaldia(String nombreAlcaldia) {
        this.nombreAlcaldia = nombreAlcaldia;
    }

    public String getDirectorAlcaldia() {
        return directorAlcaldia;
    }

    public void setDirectorAlcaldia(String directorAlcaldia) {
        this.directorAlcaldia = directorAlcaldia;
    }

    public String getDireccionAlcaldia() {
        return direccionAlcaldia;
    }

    public void setDireccionAlcaldia(String direccionAlcaldia) {
        this.direccionAlcaldia = direccionAlcaldia;
    }

    public String getTelefonoAlcaldia() {
        return telefonoAlcaldia;
    }

    public void setTelefonoAlcaldia(String telefonoAlcaldia) {
        this.telefonoAlcaldia = telefonoAlcaldia;
    }

    public String getWebAlcaldia() {
        return webAlcaldia;
    }

    public void setWebAlcaldia(String webAlcaldia) {
        this.webAlcaldia = webAlcaldia;
    }

    public String getCorreoAlcaldia() {
        return correoAlcaldia;
    }

    public void setCorreoAlcaldia(String correoAlcaldia) {
        this.correoAlcaldia = correoAlcaldia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getPoder() {
        return poder;
    }

    public void setPoder(Integer poder) {
        this.poder = poder;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "alcaldia:" + idAlcaldia + ":" + nombreAlcaldia + ":" + directorAlcaldia;
    }
}
