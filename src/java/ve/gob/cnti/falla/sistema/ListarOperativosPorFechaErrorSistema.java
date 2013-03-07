package ve.gob.cnti.falla.sistema;

import javax.xml.ws.WebFault;
import ve.gob.cnti.falla.TipoError;

/**
 *
 * @author Danielle Mariani
 */
@WebFault(name="ListarOperativosPorFechaErrorSistema")
public class ListarOperativosPorFechaErrorSistema extends Exception {
    
    private TipoError error;

    public ListarOperativosPorFechaErrorSistema(String message, TipoError error) {
        super(message);
        this.error = error;
    }

    public ListarOperativosPorFechaErrorSistema(String message, TipoError error, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public TipoError getError() {
        return error;
    }
    
}
