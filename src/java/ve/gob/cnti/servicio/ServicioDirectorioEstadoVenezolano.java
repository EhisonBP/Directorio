package ve.gob.cnti.servicio;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import ve.gob.cnti.falla.aplicacion.ListarAlcaldiasPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesPorPoderesErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarOperativosPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarPoderesErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarTramitesPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarTramitesPorInstitucionErrorAplicacion;
import ve.gob.cnti.falla.sistema.ListarAlcaldiasPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarInstitucionesPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarInstitucionesPorPoderErrorSistema;
import ve.gob.cnti.falla.sistema.ListarOperativosPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarPoderesErrorSistema;
import ve.gob.cnti.falla.sistema.ListarTramitesPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarTramitesPorInstitucionErrorSistema;
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
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface ServicioDirectorioEstadoVenezolano {

    /**
     * Métodos creados por el Centro Nacional de Tecnologia de Informacion (CNTI)
     */
    
    @WebMethod(operationName = "listarPoderes")
    @WebResult(name = "tipoPoder")
    @Action(input = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarPoderesEntrada", output = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarPoderesSalida",
    fault = {
        @FaultAction(className = ListarPoderesErrorSistema.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarPoderes/error/ListarPoderesErrorSistema"),
        @FaultAction(className = ListarPoderesErrorAplicacion.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarPoderes/error/ListarPoderesErrorAplicacion")
    })
    public List<Poder> listadoPoderes()
            throws ListarPoderesErrorSistema,
            ListarPoderesErrorAplicacion;

    @WebMethod(operationName = "listarInstitucionesPorPoderes")
    @WebResult(name = "tipoInstitucion")
    @Action(input = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorPoderesEntrada",
    output = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorPoderesSalida",
    fault = {
        @FaultAction(className = ListarInstitucionesPorPoderErrorSistema.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorPoderes/error/ListarInstitucionesPorPoderesErrorSistema"),
        @FaultAction(className = ListarInstitucionesPorPoderesErrorAplicacion.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorPoderes/error/ListarInstitucionesPorPoderesErrorAplicacion")
    })
    public List<Institucion> listadoInstitucionesPorPoderes(@WebParam(name = "idPoder")
            final int idPoder)
            throws ListarInstitucionesPorPoderErrorSistema,
            ListarInstitucionesPorPoderesErrorAplicacion;

    @WebMethod(operationName = "listarTramitesPorInstitucion")
    @WebResult(name = "tipoTramite")
    @Action(input = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucionEntrada",
    output = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucionSalida",
    fault = {
        @FaultAction(className = ListarTramitesPorInstitucionErrorSistema.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucion/error/ListarTramitesPorInstitucionErrorSistema"),
        @FaultAction(className = ListarTramitesPorInstitucionErrorAplicacion.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucion/error/ListarTramitesPorInstitucionErrorAplicacion")
    })
    public List<Tramite> listadoTramitesPorInstitucion(@WebParam(name = "idInstitucion")
            final int idInstitucion)
            throws ListarTramitesPorInstitucionErrorSistema,
            ListarTramitesPorInstitucionErrorAplicacion;

    /***
     * Métodos creados por Ehison para Android
     */
    @WebMethod(operationName = "listarAlcaldiaPorFecha")
    @WebResult(name = "tipoAlcaldia")
    @Action(input = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarAlcaldiaPorFechaEntrada", 
            output = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarAlcaldiaPorFechaSalida",
    fault = {
        @FaultAction(className = ListarAlcaldiasPorFechaErrorSistema.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarPoderes/error/ListarAlcaldiasPorFechaErrorSistema"),
        @FaultAction(className = ListarAlcaldiasPorFechaErrorAplicacion.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarPoderes/error/ListarAlcaldiasPorFechaErrorAplicacion")
    })
    public List<Alcaldia> listadoAlcaldiasPorFechas(@WebParam(name = "fecha")
            final String fecha)
            throws ListarAlcaldiasPorFechaErrorSistema,
            ListarAlcaldiasPorFechaErrorAplicacion;
    
    
    /**
     * Metodo para listar Instituciones que sean superior a la fecha ingresada
     * 
     * @param fecha
     * @return
     * @throws ListarInstitucionesPorFechaErrorSistema
     * @throws ListarInstitucionesPorFechaErrorAplicacion 
     */
    @WebMethod(operationName = "listarInstitucionesPorFecha")
    @WebResult(name = "tipoInstitucion")
    @Action(input = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorFechaEntrada",
    output = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorFechaSalida",
    fault = {
        @FaultAction(className = ListarInstitucionesPorFechaErrorSistema.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorPoder/error/ListarInstitucionesPorFechaErrorSistema"),
        @FaultAction(className = ListarInstitucionesPorFechaErrorAplicacion.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarInstitucionesPorPoder/error/ListarInstitucionesPorFechaErrorAplicacion")
    })
    public List<Institucion> listadoInstitucionesPorFecha(@WebParam(name = "fecha")
            final String fecha)
            throws ListarInstitucionesPorFechaErrorSistema,
            ListarInstitucionesPorFechaErrorAplicacion;

    @WebMethod(operationName = "listarTramitesPorFecha")
    @WebResult(name = "tipoTramite")
    @Action(input = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorFechaEntrada",
    output = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorFechaSalida",
    fault = {
        @FaultAction(className = ListarTramitesPorFechaErrorSistema.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucion/error/ListarTramitesPorFechaErrorSistema"),
        @FaultAction(className = ListarTramitesPorFechaErrorAplicacion.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucion/error/ListarTramitesPorFechaErrorAplicacion")
    })
    public List<Tramite> listadoTramitesPorFecha(@WebParam(name = "fecha")
            final String fecha)
            throws ListarTramitesPorFechaErrorSistema,
            ListarTramitesPorFechaErrorAplicacion;
    /**
    @WebMethod(operationName= "listarOperativosPorFecha")
    @WebResult(name="tipoOperativo")
    @Action(input = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarOperativosPorFechaEntrada",
    output = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarOperativosPorFechaSalida",
    fault = {
        @FaultAction(className = ListarOperativosPorFechaErrorSistema.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucion/error/ListarOperativosPorFechaErrorSistema"),
        @FaultAction(className = ListarOperativosPorFechaErrorAplicacion.class,
        value = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano/ServicioDirectorioEstadoVenezolanoDefinicion/listarTramitesPorInstitucion/error/ListarOperativosPorFechaErrorAplicacion")
    })
    public List<Operativo> listadoOperativosPorFecha(@WebParam(name = "fecha")
            final String fecha)
            throws ListarOperativosPorFechaErrorSistema,
            ListarOperativosPorFechaErrorAplicacion;
    */
}
