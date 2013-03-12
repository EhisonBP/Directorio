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
@WebFault(name="ListarInstitucionesEliminadasErrorAplicacion")
public class ListarInstitucionesEliminadasErrorAplicacion extends Exception{
    
    private TipoError error;

    public ListarInstitucionesEliminadasErrorAplicacion(String message, TipoError error) {
        super(message);
        this.error = error;
    }

    public ListarInstitucionesEliminadasErrorAplicacion(String message, TipoError error, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public TipoError getError() {
        return error;
    }
}
