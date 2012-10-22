package ve.gob.cnti.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * Define la estructura del tipo de dato #TipoTramite
 * del esquema del WSDL bajo el
 * namespace http://www.cnti.gob.ve/schema/directorio/TipoTramite
 *
 * @author Danielle Mariani
 */
@XmlRootElement(name="Tramite2",
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoTramite2")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoTramite2", propOrder = {
        "idTramite",
        "nombreTramite",
        "telefono",
        "direccion",
        "requisitos",
        "paginaWeb"},
        namespace = "http://www.cnti.gob.ve/schema/directorio/TipoTramite2")
public class Tramite2 {

    private int idTramite;
    private String nombreTramite;
    private int telefono;
    private String direccion;
    private String requisitos;
    private String paginaWeb;

    public Tramite2() {
    }

    public Tramite2(int idTramite, String nombreTramite, int telefono, String direccion, String requisitos, String paginaWeb) {
        this.idTramite = idTramite;
        this.nombreTramite = nombreTramite;
        this.telefono = telefono;
        this.direccion = direccion;
        this.requisitos = requisitos;
        this.paginaWeb = paginaWeb;
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

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

}

