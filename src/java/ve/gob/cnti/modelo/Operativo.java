/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.gob.cnti.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Ehison Perez
 */

@XmlRootElement(name="Operativo",
       namespace="http://www.cnti.gob.ve/schema/directorio/TipoOperativo")
@XmlType(name="TipoOperativo", propOrder={
        "idOperativo",
        "nombre",
        "ciudad",
        "direccion",
        "fechaInicioOperativo",
        "fechaFinOperativo",
        "fecha"},
        namespace="http://www.cnti.gob.ve/schema/directorio/TipoOperativo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Operativo {
    
    private int idOperativo;
    private String nombre;
    private String ciudad;
    private String fechaInicioOperativo;
    private String fechaFinOperativo;
    private String direccion;
    private String fecha;
    
    public Operativo (){
    }
    
    public Operativo(int idOperativo, String nombre, String ciudad, String direccion, String fechaInicioOperativo, String fechaFinOperativo, String fecha){
        this.idOperativo = idOperativo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.fechaInicioOperativo = fechaInicioOperativo;
        this.fechaFinOperativo = fechaFinOperativo;
        this.fecha = fecha;
    }

    public int getIdOperativo() {
        return idOperativo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setIdOperativo(int idOperativo) {
        this.idOperativo = idOperativo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFechaInicioOperativo() {
        return fechaInicioOperativo;
    }

    public void setFechaInicioOperativo(String fechaInicioOperativo) {
        this.fechaInicioOperativo = fechaInicioOperativo;
    }

    public String getFechaFinOperativo() {
        return fechaFinOperativo;
    }

    public void setFechaFinOperativo(String fechaFinOperativo) {
        this.fechaFinOperativo = fechaFinOperativo;
    }
    
}
