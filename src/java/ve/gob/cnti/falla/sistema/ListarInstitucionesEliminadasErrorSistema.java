/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.gob.cnti.falla.sistema;

import javax.xml.ws.WebFault;
import ve.gob.cnti.falla.TipoError;

/**
 *
 * @author ehisonbp
 */
@WebFault(name="ListarInstitucionesEliminadasErrorSistema")
public class ListarInstitucionesEliminadasErrorSistema extends Exception{
    
    private TipoError error;

    public ListarInstitucionesEliminadasErrorSistema(String message, TipoError error) {
        super(message);
        this.error = error;
    }

    public ListarInstitucionesEliminadasErrorSistema(String message, TipoError error, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public TipoError getError() {
        return error;
    }
    
}
