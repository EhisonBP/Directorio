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
        "descripcion",
        "fechaOperativo",
        "direccion",
        "fechaCreacion"},
        namespace="http://www.cnti.gob.ve/schema/directorio/TipoOperativo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Operativo {
    
    private int idOperativo;
    private String nombre;
    private String descripcion;
    private String fechaOperativo;
    private String direccion;
    private String fechaCreacion;
    
    public Operativo (){
    }
    
    public Operativo(int idOperativo, String nombre, String descripcion, String fechaOperativo, String direccion, String fechaCreacion){
        this.idOperativo = idOperativo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaOperativo = fechaOperativo;
        this.direccion = direccion;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdOperativo() {
        return idOperativo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaOperativo() {
        return fechaOperativo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setIdOperativo(int idOperativo) {
        this.idOperativo = idOperativo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaOperativo(String fechaOperativo) {
        this.fechaOperativo = fechaOperativo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
}
