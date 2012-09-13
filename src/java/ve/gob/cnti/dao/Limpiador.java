/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.gob.cnti.dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pehison
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
                Pattern p = Pattern.compile(borrar);
                Matcher m = p.matcher(dato);
                if (m.find()){
                    String limpio = m.replaceAll("");
                    dato = limpio;
                    dato.trim();
                }
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
        return dato;
    }
    String limpiarInstituciones (String dato){
        dato = dato.toString().replace("Ministerio del Poder Popular para la ", "");
        dato = dato.toString().replace("Ministerio del Poder Popular para las ", "");
        dato = dato.toString().replace("Ministerio del Poder Popular para los ", "");
        dato = dato.toString().replace("Ministerio del Poder Popular para el ", "");
        dato = dato.toString().replace("Ministerio del Poder Popular del ", "");
        dato = dato.toString().replace("Ministerio del Poder Popular de ", "");
        dato = dato.toString().replace("Ministerio del Poder Popular para ", "");
        dato = dato.toString().replace("Gobernación del Estado ", "");
        dato = dato.toString().replace("República Bolivariana de Venezuela", "");
        return dato;
    }

}
