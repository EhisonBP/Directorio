/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.gob.cnti.dao;

/**
 *
 * @author Ehison Perez
 */
public class Limpiador {

    String nombreMunicipio(String dato) {
        int indiceF;
        indiceF = dato.indexOf('-');
        if (indiceF > 0) {
            String s1 = dato.substring(0, (indiceF + 2));
            dato = dato.replaceAll(s1, "");
            dato = dato.trim();
        }
        return dato;
    }

    String nombreAlcaldia(String dato) {
        int indiceF;
        indiceF = dato.indexOf('-');
        if (indiceF > 0) {
            dato = dato.substring(0, (indiceF - 1));
            dato = dato.trim();
        }
        return dato;
    }
}
