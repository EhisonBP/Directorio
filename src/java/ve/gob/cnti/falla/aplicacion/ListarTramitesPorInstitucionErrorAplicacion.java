package ve.gob.cnti.falla.aplicacion;

import javax.xml.ws.WebFault;
import ve.gob.cnti.falla.TipoError;

/**
 *
 * @author Ehison Perez
 */
@WebFault(name="ListarTramitesPorInstitucionErrorAplicacion")
public class ListarTramitesPorInstitucionErrorAplicacion extends Exception {
    
    private TipoError error;

    public ListarTramitesPorInstitucionErrorAplicacion(String message, TipoError error) {
        super(message);
        this.error = error;
    }

    public ListarTramitesPorInstitucionErrorAplicacion(String message, TipoError error, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public TipoError getError() {
        return error;
    }
    
}
