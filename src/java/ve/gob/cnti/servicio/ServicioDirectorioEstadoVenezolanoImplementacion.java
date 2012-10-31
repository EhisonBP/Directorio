package ve.gob.cnti.servicio;

import java.util.Date;
import ve.gob.cnti.dao.DAO;
import ve.gob.cnti.modelo.Institucion;
import ve.gob.cnti.modelo.Alcaldia;
import java.util.List;
import ve.gob.cnti.falla.aplicacion.ListarTramitesPorPerfilesErrorAplicacion;
import ve.gob.cnti.falla.sistema.ListarTramitesPorPerfilesErrorSistema;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesPorPoderErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarPoderesErrorAplicacion;
import ve.gob.cnti.falla.sistema.ListarInstitucionesPorPoderErrorSistema;
import ve.gob.cnti.falla.sistema.ListarPoderesErrorSistema;
import ve.gob.cnti.modelo.Tramite;
import ve.gob.cnti.modelo.Poder;
import ve.gob.cnti.modelo.Tramite2;
import ve.gob.cnti.modelo.Institucion2;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
//import javax.xml.ws.handler.MessageContext;

import ve.gob.cnti.falla.aplicacion.ListarTramitesPorInstitucionErrorAplicacion;
import ve.gob.cnti.falla.sistema.ListarTramitesPorInstitucionErrorSistema;


/**
 * Implementacion de la interfaz ServicioDirectorioEstadoVenezolano
 * para la definicion de las operaciones del Servicio Web.
 * 
 * Los atributos generales del Servicio se definen a continuación:
 *   <li>Nombre: ServicioDirectorioEstadoVenezolano</li>
 *   <li>Endpoint: ServicioDirectorioEstadoVenezolanoImplementacion</li>
 *   <li>Endpoint Interface: {@link ServicioDirectorioEstadoVenezolano}</li>
 *   <li>Target NameSpace: http://www.cnti.gob.ve/servicio/ServicioDirectorioEstadoVenezolano</li>
 * 
 * 
 * Si el usuario desea obtener el listado de Poderes debe 
 * invocar a {@link #listadoPoderes()}
 * 
 * Si el usuario desea obtener el listado de Institucones asociadas 
 * a un Poder en especifico debe invocar a 
 * {@link #listadoInstituciones(int) } 
 * 
 * Si el usuario desea obtener el listado de Tramites asociados 
 * a una Institucion en especifico debe invocar a 
 * {@link #listadoInstituciones(int) } 
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
     * Invoca al metodo getPoderes() de la clase {@link DAO}, para dar
     * respuesta al cliente que genero la solicitud respecto al listado de
     * poderes.
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
     * @param idPoder es el identificador unico del poder, empleado para realizar
     * la busqueda de instituciones asociadas al mismo.
     * @return el arreglo que contiene los objetos de tipo Institucion.
     * @throws ListarInstitucionesPorPoderErrorSistema
     * @throws ListarInstitucionesPorPoderErrorAplicacion
     */
    @Override
    public List<Institucion2> listadoInstituciones2(final int idPoder)
            throws ListarInstitucionesPorPoderErrorSistema,
            ListarInstitucionesPorPoderErrorAplicacion {
        return DAO.getInstituciones2(idPoder);
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
    public List<Tramite2> listadoTramites2(final int idInstitucion)
            throws ListarTramitesPorInstitucionErrorSistema,
            ListarTramitesPorInstitucionErrorAplicacion {
        return DAO.getTramites2(idInstitucion);
    }

    /**
     * 
     * Operacion del Servicio Web #listarPoderes
     * 
     * Invoca al metodo getPoderes() de la clase {@link DAO}, para dar 
     * respuesta al cliente que genero la solicitud respecto al listado de 
     * poderes.
     *
     * @return el arreglo que contiene los objetos de tipo Poder.
     * @throws ListarPoderesErrorSistema
     * @throws ListarPoderesErrorAplicacion 
     */
    @Override
    public List<Alcaldia> listadoAlcaldias(final String fecha) throws ListarPoderesErrorSistema,
            ListarPoderesErrorAplicacion {
        return DAO.getAlcaldias(fecha);
    }

    /**
     * 
     * Operacion del Servicio Web #listarInstitucionesPorPoder
     * 
     * Invoca al metodo getInstituciones(int) de la clase {@link DAO}, para dar 
     * respuesta al cliente que genero la solicitud respecto al listado de 
     * instituciones.
     *
     * @param idPoder es el identificador unico del poder, empleado para realizar
     * la busqueda de instituciones asociadas al mismo.
     * @return el arreglo que contiene los objetos de tipo Institucion.
     * @throws ListarInstitucionesPorPoderErrorSistema
     * @throws ListarInstitucionesPorPoderErrorAplicacion 
     */
    @Override
    public List<Institucion> listadoInstituciones(final String fecha)
            throws ListarInstitucionesPorPoderErrorSistema,
            ListarInstitucionesPorPoderErrorAplicacion {
        return DAO.getInstituciones(fecha);
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
    public List<Tramite> listadoTramites(String fecha)
            throws ListarTramitesPorPerfilesErrorSistema,
            ListarTramitesPorPerfilesErrorAplicacion {
        return DAO.getTramites(fecha);
    }



}
