package ve.gob.cnti.servicio;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
//import javax.servlet.annotation.WebServlet;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesPorPoderErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarPoderesErrorAplicacion;
import ve.gob.cnti.falla.sistema.ListarInstitucionesPorPoderErrorSistema;
import ve.gob.cnti.falla.sistema.ListarPoderesErrorSistema;
import ve.gob.cnti.falla.aplicacion.ListarTramitesPorPerfilesErrorAplicacion;
import ve.gob.cnti.falla.sistema.ListarTramitesPorPerfilesErrorSistema;
import ve.gob.cnti.modelo.*;

/**
 * Interfaz que determina el conjunto de operaciones y anotaciones JAX-WS para 
 * la definicion de un Servicio Web.
 * 
 * Los atributos generales del Servicio se definen a continuación:
 * <li>Port Type: ServicioDirectorioEstadoVenezolanoDefinicion</li>
 * <li>targetNamespace: http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano</li>
 * 
 * Las operaciones con las que cuenta son:
 * <li>listarPoderes {@link #listadoPoderes() }</li>
 * <li>listarInstitucionesPorPoder {@link #listadoInstituciones(int)   }</li>
 * <li>listadoTramites {@link #listadoTramites(int) }</li>
 * 
 * @author Danielle Mariani
 */
@WebService(name = "ServicioDirectorioEstadoVenezolanoDefinicion",
        targetNamespace = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano")
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface ServicioDirectorioEstadoVenezolano {
    
    
    @WebMethod(operationName = "listarAlcaldia")
    @WebResult(name="tipoAlcaldia")
    @Action(input = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarAlcaldiaEntrada", output = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarAlcaldiaSalida",
            fault = {
        @FaultAction(className = ListarPoderesErrorSistema.class, 
            value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarPoderes/error/ListarPoderesErrorSistema"),
        @FaultAction(className = ListarPoderesErrorAplicacion.class, 
            value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarPoderes/error/ListarPoderesErrorAplicacion")
    })
    public List<Alcaldia> listadoAlcaldias(@WebParam(name = "fecha")
        final String fecha)
            throws ListarPoderesErrorSistema, 
            ListarPoderesErrorAplicacion;
    
    
    @WebMethod(operationName = "listarInstitucionesPorPoder")
    @WebResult(name="tipoInstitucion")
    @Action(input = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorPoderEntrada", 
            output = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorPoderSalida", 
            fault = {
        @FaultAction(className = ListarInstitucionesPorPoderErrorSistema.class, 
            value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorPoder/error/ListarInstitucionesPorPoderErrorSistema"),
        @FaultAction(className = ListarInstitucionesPorPoderErrorAplicacion.class, 
            value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorPoder/error/ListarInstitucionesPorPoderErrorAplicacion")
    })

    public List<Institucion> listadoInstituciones(@WebParam(name = "fecha")
        final String fecha)
            throws ListarInstitucionesPorPoderErrorSistema,
            ListarInstitucionesPorPoderErrorAplicacion;
 
    @WebMethod(operationName = "listarTramitesPorPerfiles")
    @WebResult(name="tipoTramite")
    @Action(input = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucionEntrada", 
            output = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucionSalida", 
            fault = {
        @FaultAction(className = ListarTramitesPorPerfilesErrorSistema.class,
            value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucion/error/ListarTramitesPorInstitucionErrorSistema"),
        @FaultAction(className = ListarTramitesPorPerfilesErrorAplicacion.class,
            value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucion/error/ListarTramitesPorInstitucionErrorAplicacion")
    })
    public List<Tramite> listadoTramites(@WebParam(name = "fecha")
        final String fecha)
            throws ListarTramitesPorPerfilesErrorSistema,
            ListarTramitesPorPerfilesErrorAplicacion;
}