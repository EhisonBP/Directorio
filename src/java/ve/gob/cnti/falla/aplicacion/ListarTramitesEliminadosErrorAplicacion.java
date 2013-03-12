/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.gob.cnti.falla.aplicacion;

import ve.gob.cnti.falla.TipoError;

/**
 *
 * @author ehisonbp
 */
public class ListarTramitesEliminadosErrorAplicacion extends Exception{
    
        private TipoError error;

    public ListarTramitesEliminadosErrorAplicacion(String message, TipoError error) {
        super(message);
        this.error = error;
    }

    public ListarTramitesEliminadosErrorAplicacion(String message, TipoError error, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public TipoError getError() {
        return error;
    }
    
}
