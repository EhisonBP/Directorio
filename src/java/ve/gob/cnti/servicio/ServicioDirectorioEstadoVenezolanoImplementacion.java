package ve.gob.cnti.servicio;

import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import ve.gob.cnti.dao.DAO;
import ve.gob.cnti.falla.aplicacion.ListarAlcaldiasEliminadasErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarAlcaldiasPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesEliminadasErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesPorPoderesErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarOperativosPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarPoderesErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarTramitesPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarTramitesPorInstitucionErrorAplicacion;
import ve.gob.cnti.falla.sistema.ListarAlcaldiasEliminadasErrorSistema;
import ve.gob.cnti.falla.sistema.ListarAlcaldiasPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarInstitucionesEliminadasErrorSistema;
import ve.gob.cnti.falla.sistema.ListarInstitucionesPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarInstitucionesPorPoderErrorSistema;
import ve.gob.cnti.falla.sistema.ListarOperativosPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarPoderesErrorSistema;
import ve.gob.cnti.falla.sistema.ListarTramitesPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarTramitesPorInstitucionErrorSistema;
import ve.gob.cnti.modelo.Alcaldia;
import ve.gob.cnti.modelo.Institucion;
import ve.gob.cnti.modelo.Operativo;
import ve.gob.cnti.modelo.Poder;
import ve.gob.cnti.modelo.Tramite;

/**
 * Implementacion de la interfaz ServicioDirectorioEstadoVenezolano para la
 * definicion de las operaciones del Servicio Web.
 *
 * Los atributos generales del Servicio se definen a continuación: <li>Nombre:
 * ServicioDirectorioEstadoVenezolano</li> <li>Endpoint:
 * ServicioDirectorioEstadoVenezolanoImplementacion</li> <li>Endpoint Interface:
 * {@link ServicioDirectorioEstadoVenezolano}</li> <li>Target NameSpace:
 * http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano</li>
 *
 *
 * Si el usuario desea obtener el listado de Poderes debe invocar a
 * {@link #listadoPoderes()}
 *
 * Si el usuario desea obtener el listado de Institucones asociadas a un Poder
 * en especifico debe invocar a {@link #listadoInstituciones(int) }
 *
 * Si el usuario desea obtener el listado de Tramites asociados a una
 * Institucion en especifico debe invocar a {@link #listadoInstituciones(int) }
 *
 * @author Danielle Mariani
 *
 */
@WebService(serviceName = "ServicioDirectorioEstadoVenezolano",
portName = "ServicioDirectorioEstadoVenezolanoUbicacion",
endpointInterface = "ve.gob.cnti.servicio.ServicioDirectorioEstadoVenezolano",
targetNamespace = "http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano")
public class ServicioDirectorioEstadoVenezolanoImplementacion
        implements ServicioDirectorioEstadoVenezolano {

    @Resource
    WebServiceContext wsctx;

    /**
     *
     * Operacion del Servicio Web #listarPoderes
     *
     * Invoca al metodo getPoderes() de la clase {@link DAO}, para dar respuesta
     * al cliente que genero la solicitud respecto al listado de poderes.
     *
     * @return el arreglo que contiene los objetos de tipo Poder.
     * @throws ListarPoderesErrorSistema
     * @throws ListarPoderesErrorAplicacion
     */
    @Override
    public List<Poder> listadoPoderes() throws ListarPoderesErrorSistema,
            ListarPoderesErrorAplicacion {
        return DAO.getPoderes();
    }

    /**
     *
     * Operacion del Servicio Web #listarInstitucionesPorPoder
     *
     * Invoca al metodo getInstituciones(int) de la clase {@link DAO}, para dar
     * respuesta al cliente que genero la solicitud respecto al listado de
     * instituciones.
     *
     * @param idPoder es el identificador unico del poder, empleado para
     * realizar la busqueda de instituciones asociadas al mismo.
     * @return el arreglo que contiene los objetos de tipo Institucion.
     * @throws ListarInstitucionesPorPoderErrorSistema
     * @throws ListarInstitucionesPorPoderesErrorAplicacion
     */
    @Override
    public List<Institucion> listadoInstitucionesPorPoderes(final int idPoder)
            throws ListarInstitucionesPorPoderErrorSistema,
            ListarInstitucionesPorPoderesErrorAplicacion {
        return DAO.getInstitucionesPorPoderes(idPoder);
    }

    /**
     *
     * Operacion del Servicio Web #listarTramitesPorInstitucion
     *
     * Invoca al metodo getTramites(int) de la clase {@link DAO}, para dar
     * respuesta al cliente que genero la solicitud respecto al listado de
     * tramites.
     *
     * @param idInstitucion es el identificador unico de la institucion,
     * empleado para realizar la busqueda de los tramites asociadas a la misma.
     * @return el arreglo que contiene los objetos de tipo Tramite.
     * @throws ListarTramitesPorInstitucionErrorSistema
     * @throws ListarTramitesPorInstitucionErrorAplicacion
     */
    @Override
    public List<Tramite> listadoTramitesPorInstitucion(final int idInstitucion)
            throws ListarTramitesPorInstitucionErrorSistema,
            ListarTramitesPorInstitucionErrorAplicacion {
        return DAO.getTramitesPorInstitucion(idInstitucion);
    }

    /**
     *
     * Operacion del Servicio Web #listarTramitesPorInstitucion
     *
     * Invoca al metodo getTramites(int) de la clase {@link DAO}, para dar
     * respuesta al cliente que genero la solicitud respecto al listado de
     * tramites.
     *
     * @param idInstitucion es el identificador unico de la institucion,
     * empleado para realizar la busqueda de los tramites asociadas a la misma.
     * @return el arreglo que contiene los objetos de tipo Tramite.
     * @throws ListarTramitesPorInstitucionErrorSistema
     * @throws ListarTramitesPorInstitucionErrorAplicacion
     */
    @Override
    public List<Tramite> listadoTramitesPorFecha(String fecha)
            throws ListarTramitesPorFechaErrorSistema,
            ListarTramitesPorFechaErrorAplicacion {
        return DAO.getTramitesPorFecha(fecha);
    }

    /**
     *
     * Operacion del Servicio Web #listarTramitesPorInstitucion
     *
     * Invoca al metodo getInstitucionesPorFecha(fecha) de la clase {@link DAO},
     * para dar respuesta al cliente que genero la solicitud respecto al listado
     * de instituciones.
     *
     * @param fecha esta va ser el parametro para implementar la busqueda dentro
     * de las instituciones que sean mayor a la fecha ingresada
     * @return
     * @throws ListarInstitucionesPorFechaErrorSistema
     * @throws ListarInstitucionesPorFechaErrorAplicacion
     */
    @Override
    public List<Institucion> listadoInstitucionesPorFecha(final String fecha)
            throws ListarInstitucionesPorFechaErrorSistema,
            ListarInstitucionesPorFechaErrorAplicacion {
        return DAO.getInstitucionesPorFecha(fecha);
    }

    /**
     *
     * @param fecha
     * @return
     * @throws ListarAlcaldiasPorFechaErrorSistema
     * @throws ListarAlcaldiasPorFechaErrorAplicacion
     */
    @Override
    public List<Alcaldia> listadoAlcaldiasPorFechas(final String fecha)
            throws ListarAlcaldiasPorFechaErrorSistema,
            ListarAlcaldiasPorFechaErrorAplicacion {
        return DAO.getAlcaldiasPorFecha(fecha);
    }

    @Override
    public List<Operativo> listadoOperativosPorFecha(final String fecha)
            throws ListarOperativosPorFechaErrorSistema,
            ListarOperativosPorFechaErrorAplicacion {
        return DAO.getOperativosPorFecha(fecha);
    }

    @Override
    public List<Alcaldia> listadoAlcaldiasEliminadas(final String fecha)
            throws ListarAlcaldiasEliminadasErrorSistema,
            ListarAlcaldiasEliminadasErrorAplicacion {
        return DAO.getAlcaldiasEliminadas(fecha);
    }

    @Override
    public List<Institucion> listadoInstitucionesEliminadas(final String fecha) 
            throws ListarInstitucionesEliminadasErrorSistema, 
            ListarInstitucionesEliminadasErrorAplicacion {
        return DAO.getInstitucionesEliminadas(fecha);
    }
}
