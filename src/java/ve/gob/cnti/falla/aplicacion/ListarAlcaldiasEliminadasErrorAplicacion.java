/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.gob.cnti.falla.aplicacion;

import javax.xml.ws.WebFault;
import ve.gob.cnti.falla.TipoError;

/**
 *
 * @author ehisonbp
 */
@WebFault(name = "ListarAlcaldiasEliminadasErrorAplicacion")
public class ListarAlcaldiasEliminadasErrorAplicacion extends Exception {

    private TipoError error;

    public ListarAlcaldiasEliminadasErrorAplicacion(String message, TipoError error) {
        super(message);
        this.error = error;
    }

    public ListarAlcaldiasEliminadasErrorAplicacion(String message, TipoError error, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public TipoError getError() {
        return error;
    }
}
