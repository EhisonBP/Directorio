package ve.gob.cnti.falla.sistema;

import javax.xml.ws.WebFault;
import ve.gob.cnti.falla.TipoError;

/**
 *
 * @author Danielle Mariani
 */
@WebFault(name="ListarAlcaldiasPorFechaErrorSistema")
public class ListarAlcaldiasPorFechaErrorSistema extends Exception {
    
    private TipoError error;

    public ListarAlcaldiasPorFechaErrorSistema(String message, TipoError error) {
        super(message);
        this.error = error;
    }

    public ListarAlcaldiasPorFechaErrorSistema(String message, TipoError error, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public TipoError getError() {
        return error;
    }
    
}
