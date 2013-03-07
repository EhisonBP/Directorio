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

    String limpiadorEtiquetas(String dato){
        int indiceI, indiceF;

        do{
           indiceI = 0;
           indiceF = 0;
           char ini = '<';
           char fin = '>';

           indiceI = dato.indexOf(ini);
           indiceF = dato.indexOf(fin);
           if (indiceI > 0 || indiceF >0){
               try{
                String borrar = dato.substring(indiceI, (indiceF+1));
                    dato = dato.replaceAll(borrar, "");
               }catch (Exception e){
                   System.out.println("Error de formato en dato");
                   dato = "No disponible";
               }
           }
        } while( indiceI > 0 || indiceF > 0);
        dato = dato.replaceAll("&nbsp;", "");
        dato = dato.replaceAll("&eacute;","é");
        dato = dato.replaceAll("&aacute;","á");
        dato = dato.replaceAll("&iacute;","í");
        dato = dato.replaceAll("&oacute;","ó");
        dato = dato.replaceAll("&uacute;","ú");
        dato = dato.replaceAll("&ntilde;", "ñ");
        dato = dato.replaceAll("&Eacute;","É");
        dato = dato.replaceAll("&Aacute;","Á");
        dato = dato.replaceAll("&Iacute;","I");
        dato = dato.replaceAll("&Oacute;","Ó");
        dato = dato.replaceAll("&Uacute;","Ú");
        dato = dato.replaceAll("&Ntilde;", "Ñ");
        dato = dato.trim();
        return dato;

    }
    
    String nombreMunicipio (String dato){
        int indiceF;
        indiceF = dato.indexOf('-');
        if (indiceF > 0){
        String s1 = dato.substring(0, (indiceF+2));
        dato = dato.replaceAll(s1, "");
        dato = dato.trim();
        }
        return dato;
    }
    String nombreAlcaldia(String dato){
        int indiceF;
        indiceF = dato.indexOf('-');
        if (indiceF > 0){
        dato = dato.substring(0, (indiceF-1));
        dato = dato.trim();
        }
        return dato;
    }

}
