package ve.gob.cnti.falla.aplicacion;

import javax.xml.ws.WebFault;
import ve.gob.cnti.falla.TipoError;

/**
 *
 * @author Danielle Mariani
 */
@WebFault(name="ListarPoderesErrorAplicacion")
public class ListarPoderesErrorAplicacion extends Exception {
    
    private TipoError error;

    public ListarPoderesErrorAplicacion(String message, TipoError error) {
        super(message);
        this.error = error;
    }

    public ListarPoderesErrorAplicacion(String message, TipoError error, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public TipoError getError() {
        return error;
    }
    
}
