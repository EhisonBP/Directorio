package ve.gob.cnti.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ve.gob.cnti.falla.FallasAplicacion;
import ve.gob.cnti.falla.FallasSistema;
import ve.gob.cnti.falla.TipoError;
import ve.gob.cnti.falla.aplicacion.ListarAlcaldiasEliminadasErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarAlcaldiasPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesEliminadasErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesPorPoderesErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarOperativosPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarPoderesErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarTramitesEliminadosErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarTramitesPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarTramitesPorInstitucionErrorAplicacion;
import ve.gob.cnti.falla.sistema.ListarAlcaldiasEliminadasErrorSistema;
import ve.gob.cnti.falla.sistema.ListarAlcaldiasPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarInstitucionesEliminadasErrorSistema;
import ve.gob.cnti.falla.sistema.ListarInstitucionesPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarInstitucionesPorPoderErrorSistema;
import ve.gob.cnti.falla.sistema.ListarOperativosPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarPoderesErrorSistema;
import ve.gob.cnti.falla.sistema.ListarTramitesEliminadosErrorSistema;
import ve.gob.cnti.falla.sistema.ListarTramitesPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarTramitesPorInstitucionErrorSistema;
import ve.gob.cnti.modelo.Alcaldia;
import ve.gob.cnti.modelo.Institucion;
import ve.gob.cnti.modelo.Operativo;
import ve.gob.cnti.modelo.Poder;
import ve.gob.cnti.modelo.Tramite;
import ve.gob.cnti.servicio.ServicioDirectorioEstadoVenezolano;

/**
 * Operaciones asociadas a las que posee el servicio web definido en
 * {@link ServicioDirectorioEstadoVenezolano}
 *
 * Las operaciones empleadas se definen a continuación: <li>Obtener Poderes
 * {@link #getPoderes()}</li> <li>Obtener Instituciones {@link #getInstituciones(int)
 * }</li> <li>Obtener Tramites {@link #getTramites(int) }</li>
 *
 *
 * Si el usuario desea obtener el listado de Poderes debe invocar a {@link #getPoderes()
 * }
 *
 * Si el usuario desea obtener el listado de Institucones asociadas a un Poder
 * en especifico debe invocar a {@link #getInstituciones(int) }
 *
 * Si el usuario desea obtener el listado de Tramites asociados a una
 * Institucion debe invocar a {@link #getTramites(int) }
 *
 * @author Ehison Perez
 *
 */
public class DAO {

    public static Limpiador limpiar = new Limpiador();

    /**
     *
     * Encargado de listar los objetos tipo {@link Poder}, para ello debe
     * invocar la clase {@link Conexion} para realizar la conexión con la base
     * de datos, ejecutar la consulta a la base de datos y listar la respuesta
     * (de haberla) en un arreglo.
     *
     * @return el arreglo que posee la lista de poderes
     * @throws ListarPoderesErrorSistema
     * @throws ListarPoderesErrorAplicacion
     */
    public static List<Poder> getPoderes() throws ListarPoderesErrorSistema,
            ListarPoderesErrorAplicacion {


        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        String query = "select inode, category_name from poderes";
        System.out.println("Entrando al metodo de poderes");
        try {
            //Iniciando conexion
            conexion = Conexion.iniciarConexion();
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("Erro en la COnexion");
            //Error al iniciar conexion
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_2_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_2_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarPoderesErrorSistema("SQL Exception", tipoError);
        }


        try {
            //Inicializando la sentencia sql
            sentencia = conexion.createStatement();
            System.out.println("Conexion exitosa2");
        } catch (SQLException e) {
            //Error inicializando la sentencia sql
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_3_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_3_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarPoderesErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Ejecutando el query contra la Base de Datos
            resultado = sentencia.executeQuery(query);
            System.out.println("Conexion exitosa3");
        } catch (SQLException e) {
            //Error ejecutando el query contra la Base de Datos
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_4_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_4_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarPoderesErrorSistema("SQL Exception", tipoError);
        }

        if (resultado != null) {

            //Leer respuesta
            ArrayList<Poder> poderes = new ArrayList<Poder>();
            try {
                boolean existe = false;
                while (resultado.next()) {
                    existe = true;
                    System.out.println(" DEV :: Poder :: " + resultado.getInt("inode")
                            + " " + resultado.getString("category_name"));
                    Poder poder = new Poder(resultado.getInt("inode"), resultado.getString("category_name"));
                    poderes.add(poder);
                }

                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_2);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_2);
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarPoderesErrorSistema("Exception", tipoError);
                }

            } catch (SQLException e) {
                //Error al leer respuesta
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_5_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_5_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarPoderesErrorSistema("SQL Exception", tipoError);
            }

            try {
                conexion.close();
            } catch (SQLException e) {
                //Error al cerrar conexion con la Base de Datos
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_6_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_6_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarPoderesErrorSistema("SQL Exception", tipoError);
            }

            return poderes;

        } else {
            //Respuesta vacia
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_2);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_2);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarPoderesErrorSistema("Exception", tipoError);
        }


    }

    /**
     *
     * Encargado de listar los objetos tipo {@link Institucion}, para ello debe
     * invocar la clase {@link Conexion} para realizar la conexión con la base
     * de datos, ejecutar la consulta a la base de datos y listar la respuesta
     * (de haberla) en un arreglo.
     *
     *
     * @param idPoder identificador unico del poder del cual se desea obtener
     * sus instituciones.
     * @return el arreglo que posee la lista de instituciones
     * @throws ListarInstitucionesPorPoderErrorSistema
     * @throws ListarInstitucionesPorPoderesErrorAplicacion
     */
    public static List<Institucion> getInstitucionesPorPoderes(int idPoder)
            throws ListarInstitucionesPorPoderErrorSistema,
            ListarInstitucionesPorPoderesErrorAplicacion {

        System.out.println(" DEV :: Listar Instituciones");

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        String query;

        if (idPoder <= 0) {
            //Parametro invalido
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_1);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_1);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarInstitucionesPorPoderesErrorAplicacion("Exception", tipoError);
        }

        if (idPoder != 128988) {
            query = "select identifier, title, text_area2, text6, text5, text8, text3 "
                    + " from instituciones "
                    + " where parent = " + idPoder + " and live = true";
        } else {
            query = "select identifier, title, text_area2, text6, text5, text8 ,text3 "
                    + " from alcaldias where live = true order by parent ";
        }

        try {
            //Iniciando conexion
            conexion = Conexion.iniciarConexion();
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            //Error al iniciar conexion
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_2_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_2_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarInstitucionesPorPoderErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Inicializando la sentencia sql
            sentencia = conexion.createStatement();
            System.out.println("Conexion exitosa2");
        } catch (SQLException e) {
            //Error inicializando la sentencia sql
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_3_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_3_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarInstitucionesPorPoderErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Ejecutando el query contra la Base de Datos
            resultado = sentencia.executeQuery(query);
            System.out.println("Conexion exitosa3");
        } catch (SQLException e) {
            //Error ejecutando el query contra la Base de Datos
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_4_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_4_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarInstitucionesPorPoderErrorSistema("SQL Exception", tipoError);
        }


        if (resultado != null) {
            //Leer respuesta
            ArrayList<Institucion> instituciones = new ArrayList<Institucion>();
            try {
                boolean existe = false;
                while (resultado.next()) {
                    existe = true;
                    System.out.println(" DEV :: Institución :: " + resultado.getInt("identifier")
                            + " " + resultado.getString("title"));

                    String descripcion = resultado.getString("text_area2");
                    if (!descripcion.equals("")) {
                        descripcion = limpiar.limpiadorEtiquetas(descripcion);
                    }

                    Institucion institucion = new Institucion(resultado.getInt("identifier"),
                            resultado.getString("title"),
                            resultado.getString("text3"),
                            descripcion,
                            resultado.getString("text6"),
                            resultado.getString("text5"),
                            resultado.getString("text8"));
                    instituciones.add(institucion);
                }

                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_3);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_3 + " (" + idPoder + ")");
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarInstitucionesPorPoderesErrorAplicacion("Exception", tipoError);
                }
            } catch (SQLException e) {
                //Error al leer respuesta
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_5_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_5_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarInstitucionesPorPoderErrorSistema("SQL Exception", tipoError);
            }

            try {
                conexion.close();
            } catch (SQLException e) {
                //Error al cerrar conexion con la Base de Datos
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_6_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_6_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarInstitucionesPorPoderErrorSistema("SQL Exception", tipoError);
            }

            return instituciones;

        } else {
            //Respuesta vacia
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_3);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_3 + " (" + idPoder + ")");
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarInstitucionesPorPoderesErrorAplicacion("Exception", tipoError);
        }


    }

    /**
     * Encargado de listar los objetos tipo {@link Tramite}, para ello debe
     * invocar la clase {@link Conexion} para realizar la conexión con la base
     * de datos, ejecutar la consulta a la base de datos y listar la respuesta
     * (de haberla) en un arreglo.
     *
     *
     * @param idInstitucion identificador unico de la institucion del cual se
     * desea obtener sus instituciones.
     * @return el arreglo que posee la lista de tramites
     * @throws ListarTramitesPorInstitucionErrorSistema
     * @throws ListarTramitesPorInstitucionErrorAplicacion
     */
    public static List<Tramite> getTramitesPorInstitucion(int idInstitucion)
            throws ListarTramitesPorInstitucionErrorSistema,
            ListarTramitesPorInstitucionErrorAplicacion {


        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;

        if (idInstitucion <= 0) {
            //Parametro invalido
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_1);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_1);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarTramitesPorInstitucionErrorAplicacion("Exception", tipoError);
        }

//        String query = "select c.inode, c.title, c.text4, c.text_area1, c.text_area5, c.text_area7, c.text_area8, c.text_area9, c.bool2 from \"public\".inode i, \"public\".tree t, \"public\".inode i2, \"public\".contentlet c "
//                + " where i.inode = " + idInstitucion
//                + " and t.child = i.identifier"
//                + " and t.relation_type = 'Tramite-Directorio'"
//                + " and i2.identifier = t.parent"
//                + " and c.inode = i2.inode"
//                + " and c.live is true";
        String query = "select i.identifier, c.title, c.text4, c.text5, c.text_area5, c.text_area8, "
                + " (select c.title from contentlet c, inode ino, tree t where c.structure_inode = 123910 and ino.identifier = " + idInstitucion + " and c.live = true and c.inode = t.child and ino.inode = c.inode and ino.identifier = t.parent) as nombre_institucion"
                + " from  contentlet c inner join inode i on i.inode = c.inode"
                + " where c.structure_inode = 107379"
                + " and i.identifier in "
                + " (select tree.child from tree where tree.parent = " + idInstitucion + " and (tree.relation_type = 'Directorio-Tramite')) "
                + " and c.live = true;";

        if (idInstitucion <= 0) {
            //Parametro invalido
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_1);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_1);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarTramitesPorInstitucionErrorAplicacion("Exception", tipoError);
        }

        try {
            //Iniciando conexion
            conexion = Conexion.iniciarConexion();
        } catch (SQLException e) {
            //Error al iniciar conexion
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_2_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_2_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarTramitesPorInstitucionErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Inicializando la sentencia sql
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            //Error inicializando la sentencia sql
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_3_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_3_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarTramitesPorInstitucionErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Ejecutando el query contra la Base de Datos
            resultado = sentencia.executeQuery(query);
        } catch (SQLException e) {
            //Error ejecutando el query contra la Base de Datos
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_4_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_4_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarTramitesPorInstitucionErrorSistema("SQL Exception", tipoError);
        }

        if (resultado != null) {

            //Leer respuesta
            ArrayList<Tramite> tramites = new ArrayList<Tramite>();
            try {
                boolean existe = false;
                while (resultado.next()) {
                    existe = true;
                    System.out.println(" DEV :: Tramite :: " + resultado.getInt("identifier")
                            + " " + resultado.getString("title"));

                    String direccion = resultado.getString("text_area5");
                    if (!direccion.equals("")) {
                        direccion = limpiar.limpiadorEtiquetas(direccion);
                    }

                    String requisitos = resultado.getString("text_area8");
                    if (!requisitos.equals("")) {
                        requisitos = limpiar.limpiadorEtiquetas(requisitos);
                    }

                    Tramite tramite = new Tramite(resultado.getInt("identifier"),
                            resultado.getString("title"),
                            resultado.getString("text4"),
                            resultado.getString("text5"),
                            direccion,
                            requisitos,
                            resultado.getString("nombre_institucion"));
                    tramites.add(tramite);
                }

                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_4);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_4 + " (" + idInstitucion + ")");
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarTramitesPorInstitucionErrorAplicacion("Exception", tipoError);
                }
            } catch (SQLException e) {
                //Error al leer respuesta
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_5_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_5_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarTramitesPorInstitucionErrorSistema("SQL Exception", tipoError);
            }

            try {
                conexion.close();
            } catch (SQLException e) {
                //Error al cerrar conexion con la Base de Datos
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_6_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_6_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarTramitesPorInstitucionErrorSistema("SQL Exception", tipoError);
            }

            return tramites;

        } else {
            //Respuesta vacia
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_4);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_4 + " (" + idInstitucion + ")");
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarTramitesPorInstitucionErrorAplicacion("Exception", tipoError);
        }

    }

    /**
     * Metodos Implementados para Android
     *
     *
     */
    /**
     *
     * Encargado de listar los objetos tipo {@link Poder}, para ello debe
     * invocar la clase {@link Conexion} para realizar la conexión con la base
     * de datos, ejecutar la consulta a la base de datos y listar la respuesta
     * (de haberla) en un arreglo.
     *
     * @return el arreglo que posee la lista de poderes
     * @throws ListarPoderesErrorSistema
     * @throws ListarPoderesErrorAplicacion
     */
    public static List<Alcaldia> getAlcaldiasPorFecha(String fecha) throws ListarAlcaldiasPorFechaErrorSistema,
            ListarAlcaldiasPorFechaErrorAplicacion {


        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;

        fecha = fecha.trim();
        if (fecha.equals("")) {
            //Parametro invalido el campo se encuentra vacio
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarAlcaldiasPorFechaErrorAplicacion("Exception", tipoError);
        }


        if (fecha.length() != 10 || fecha.charAt(4) != '-' || fecha.charAt(7) != '-') {
            //Parametro invalido el campo se encuentra vacio
            System.out.println("Entrando a la validacion de cantidad de caracteres " + fecha.length() + ": " + fecha.charAt(4) + " :" + fecha.charAt(7));
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarAlcaldiasPorFechaErrorAplicacion("Exception", tipoError);

        }

        String query = "select identifier, title, text3, text6, text5, text8, parent, mod_date "
                + " from alcaldias "
                + " where mod_date > '" + fecha + "' "
                + " and live = true "
                + " order by mod_date ";
        System.out.println("El valor Ingresado es: " + fecha);

        try {
            //Iniciando conexion
            System.out.println("Conexion se esta abriendo");
            conexion = Conexion.iniciarConexion();
            System.out.println("Conexion abierta");

        } catch (SQLException e) {
            //Error al iniciar conexion
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_2_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_2_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarAlcaldiasPorFechaErrorSistema("SQL Exception", tipoError);
        }


        try {
            //Inicializando la sentencia sql
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            //Error inicializando la sentencia sql
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_3_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_3_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarAlcaldiasPorFechaErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Ejecutando el query contra la Base de Datos
            resultado = sentencia.executeQuery(query);
        } catch (SQLException e) {
            //Error ejecutando el query contra la Base de Datos
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_4_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_4_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarAlcaldiasPorFechaErrorSistema("SQL Exception", tipoError);
        }

        if (resultado != null) {

            //Leer respuesta
            ArrayList<Alcaldia> alcaldias = new ArrayList<Alcaldia>();
            try {
                boolean existe = false;

                while (resultado.next()) {
                    try {
                        int estado = resultado.getInt("parent");
                        switch (estado) {
                            case 131910:
                                estado = 1;
                                break;
                            case 131912:
                                estado = 2;
                                break;
                            case 131914:
                                estado = 3;
                                break;
                            case 131915:
                                estado = 4;
                                break;
                            case 131916:
                                estado = 5;
                                break;
                            case 131917:
                                estado = 6;
                                break;
                            case 131918:
                                estado = 7;
                                break;
                            case 131919:
                                estado = 8;
                                break;
                            case 131920:
                                estado = 9;
                                break;
                            case 131921:
                                estado = 10;
                                break;
                            case 131922:
                                estado = 11;
                                break;
                            case 131923:
                                estado = 12;
                                break;
                            case 131924:
                                estado = 13;
                                break;
                            case 131925:
                                estado = 14;
                                break;
                            case 131926:
                                estado = 15;
                                break;
                            case 131927:
                                estado = 16;
                                break;
                            case 131928:
                                estado = 17;
                                break;
                            case 131929:
                                estado = 18;
                                break;
                            case 131930:
                                estado = 19;
                                break;
                            case 131931:
                                estado = 20;
                                break;
                            case 131932:
                                estado = 21;
                                break;
                            case 131933:
                                estado = 22;
                                break;
                            case 131934:
                                estado = 23;
                                break;
                            case 131935:
                                estado = 24;
                                break;
                        }

                        String municipio = resultado.getString("title");
                        if (!municipio.equals("")) {
                            municipio = limpiar.nombreMunicipio(municipio);
                        }

                        String nAlcaldia = resultado.getString("title");
                        if (!nAlcaldia.equals("")) {
                            nAlcaldia = limpiar.nombreAlcaldia(nAlcaldia);
                        }

                        existe = true;
                        System.out.println(" DEV :: Poder :: " + resultado.getInt("identifier")
                                + " " + resultado.getString("title"));

                        Alcaldia alcaldia = new Alcaldia(resultado.getInt("identifier"),
                                nAlcaldia,
                                municipio,
                                resultado.getString("text3"),
                                resultado.getString("text6"),
                                resultado.getString("text5"),
                                resultado.getString("text8"),
                                resultado.getString("mod_date"),
                                7,
                                estado);
                        alcaldias.add(alcaldia);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }

                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_6);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_6);
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarAlcaldiasPorFechaErrorAplicacion("Exception", tipoError);
                }

            } catch (SQLException e) {
                //Error al leer respuesta
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_5_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_5_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarAlcaldiasPorFechaErrorSistema("SQL Exception", tipoError);
            }

            try {
                conexion.close();
            } catch (SQLException e) {
                //Error al cerrar conexion con la Base de Datos
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_6_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_6_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarAlcaldiasPorFechaErrorSistema("SQL Exception", tipoError);
            }

            return alcaldias;

        } else {
            //Respuesta vacia
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_6);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_6);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarAlcaldiasPorFechaErrorAplicacion("Exception", tipoError);
        }


    }

    /**
     *
     * Encargado de listar los objetos tipo {@link Institucion}, para ello debe
     * invocar la clase {@link Conexion} para realizar la conexión con la base
     * de datos, ejecutar la consulta a la base de datos y listar la respuesta
     * (de haberla) en un arreglo.
     *
     *
     * @param idPoder identificador unico del poder del cual se desea obtener
     * sus instituciones.
     * @return el arreglo que posee la lista de instituciones
     * @throws ListarInstitucionesPorPoderErrorSistema
     * @throws ListarInstitucionesPorPoderesErrorAplicacion
     */
    public static List<Institucion> getInstitucionesPorFecha(String fecha)
            throws ListarInstitucionesPorFechaErrorSistema,
            ListarInstitucionesPorFechaErrorAplicacion {

        if (fecha == null) {
            System.out.println("No se ingreso el parametro");
        } else {
            System.out.println("El valor Ingresado es: " + fecha);
        }

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;

        fecha = fecha.trim();
        if (fecha.equals("")) {
            //Parametro invalido el campo se encuentra vacio
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarInstitucionesPorFechaErrorAplicacion("Exception", tipoError);
        }


        if (fecha.length() != 10 || fecha.charAt(4) != '-' || fecha.charAt(7) != '-') {
            //Parametro invalido el campo se encuentra vacio
            System.out.println("Entrando a la validacion de cantidad de caracteres " + fecha.length() + ": " + fecha.charAt(4) + " :" + fecha.charAt(7));
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarInstitucionesPorFechaErrorAplicacion("Exception", tipoError);

        }

        String query = "select identifier, nombre_resumen, title, text3, text6, text5, text8, parent, mod_date "
                + " from instituciones"
                + " where mod_date > '" + fecha + "'"
                + " and live = true "
                + " order by mod_date;";

        try {
            //Iniciando conexion
            conexion = Conexion.iniciarConexion();
        } catch (SQLException e) {
            //Error al iniciar conexion
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_2_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_2_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarInstitucionesPorFechaErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Inicializando la sentencia sql
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            //Error inicializando la sentencia sql
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_3_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_3_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarInstitucionesPorFechaErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Ejecutando el query contra la Base de Datos
            resultado = sentencia.executeQuery(query);
        } catch (SQLException e) {
            //Error ejecutando el query contra la Base de Datos
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_4_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_4_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarInstitucionesPorFechaErrorSistema("SQL Exception", tipoError);
        }


        if (resultado != null) {
            //Leer respuesta
            ArrayList<Institucion> instituciones = new ArrayList<Institucion>();
            try {
                boolean existe = false;
                while (resultado.next()) {
                    int poder = resultado.getInt("parent");

                    switch (poder) {
                        case 131345:
                            poder = 1;
                            break;
                        case 131347:
                            poder = 2;
                            break;
                        case 131346:
                            poder = 3;
                            break;
                        case 131348:
                            poder = 4;
                            break;
                        case 131349:
                            poder = 5;
                            break;
                        case 128987:
                            poder = 6;
                            break;
                        case 143210:
                            poder = 8;
                            break;
                    }

                    existe = true;
                    System.out.println(" DEV :: Institución :: " + resultado.getInt("identifier")
                            + " " + resultado.getString("nombre_resumen"));
                    Institucion institucion = new Institucion(resultado.getInt("identifier"),
                            resultado.getString("title"),
                            resultado.getString("nombre_resumen"),
                            resultado.getString("text3"),
                            resultado.getString("text6"),
                            resultado.getString("text5"),
                            resultado.getString("text8"),
                            poder,
                            resultado.getString("mod_date"));
                    instituciones.add(institucion);
                }

                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_5);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_5);
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarInstitucionesPorFechaErrorAplicacion("Exception", tipoError);
                }
            } catch (SQLException e) {
                //Error al leer respuesta
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_5_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_5_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarInstitucionesPorFechaErrorSistema("SQL Exception", tipoError);
            }

            try {
                conexion.close();
            } catch (SQLException e) {
                //Error al cerrar conexion con la Base de Datos
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_6_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_6_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarInstitucionesPorFechaErrorSistema("SQL Exception", tipoError);
            }

            return instituciones;

        } else {
            //Respuesta vacia
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_5);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_5);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarInstitucionesPorFechaErrorAplicacion("Exception", tipoError);
        }


    }

    /**
     * Encargado de listar los objetos tipo {@link Tramite}, para ello debe
     * invocar la clase {@link Conexion} para realizar la conexión con la base
     * de datos, ejecutar la consulta a la base de datos y listar la respuesta
     * (de haberla) en un arreglo.
     *
     *
     * @param idInstitucion identificador unico de la institucion del cual se
     * desea obtener sus instituciones.
     * @return el arreglo que posee la lista de tramites
     * @throws ListarTramitesPorInstitucionErrorSistema
     * @throws ListarTramitesPorInstitucionErrorAplicacion
     */
    public static List<Tramite> getTramitesPorFecha(String fecha)
            throws ListarTramitesPorFechaErrorSistema,
            ListarTramitesPorFechaErrorAplicacion {

        if (fecha == null) {
            System.out.println("No se ingreso el parametro");
        } else {
            System.out.println("El valor Ingresado es: " + fecha);
        }

        fecha = fecha.trim();
        if (fecha.equals("")) {
            //Parametro invalido el campo se encuentra vacio
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarTramitesPorFechaErrorAplicacion("Exception", tipoError);
        }


        if (fecha.length() != 10 || fecha.charAt(4) != '-' || fecha.charAt(7) != '-') {
            //Parametro invalido el campo se encuentra vacio
            System.out.println("Entrando a la validacion de cantidad de caracteres " + fecha.length() + ": " + fecha.charAt(4) + " :" + fecha.charAt(7));
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarTramitesPorFechaErrorAplicacion("Exception", tipoError);

        }

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;

        String query = " select identifier, text1, text_area5, text_area8, text5, text4, text_area10, mod_date "
                + " from tramites "
                + " where live = true "
                + " and mod_date > '" + fecha + "' "
                + " order by mod_date ";

        System.out.println("El valor del query es: " + query);

        try {
            //Iniciando conexion
            conexion = Conexion.iniciarConexion();
        } catch (SQLException e) {
            //Error al iniciar conexion
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_2_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_2_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarTramitesPorFechaErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Inicializando la sentencia sql
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            //Error inicializando la sentencia sql
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_3_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_3_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarTramitesPorFechaErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Ejecutando el query contra la Base de Datos
            resultado = sentencia.executeQuery(query);
        } catch (SQLException e) {
            //Error ejecutando el query contra la Base de Datos
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_4_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_4_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarTramitesPorFechaErrorSistema("SQL Exception", tipoError);
        }

        if (resultado != null) {

            //Leer respuesta
            ArrayList<Tramite> tramites = new ArrayList<Tramite>();
            try {
                boolean existe = false;
                while (resultado.next()) {
                    existe = true;

                    String direccion = resultado.getString("text_area5");
                    String requisitos = resultado.getString("text_area8");
                    // String descripcion = resultado.getString("text_area10");

                    if (!direccion.equals("")) {
                        direccion = limpiar.limpiadorEtiquetas(direccion);
                    }

                    if (!requisitos.equals("")) {
                        requisitos = limpiar.limpiadorEtiquetas(requisitos);
                    }

                    System.out.println(" DEV :: Tramite :: " + resultado.getInt("identifier")
                            + " " + resultado.getString("text1") + " " + requisitos);

                    Tramite tramite = new Tramite(resultado.getInt("identifier"),
                            resultado.getString("text1"),
                            resultado.getString("text4"),
                            resultado.getString("text5"),
                            direccion,
                            resultado.getString("text_area10"),
                            requisitos,
                            resultado.getString("mod_date"));
                    tramites.add(tramite);
                }

                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_7);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_7);
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarTramitesPorFechaErrorAplicacion("Exception", tipoError);
                }
            } catch (SQLException e) {
                //Error al leer respuesta
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_5_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_5_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarTramitesPorFechaErrorSistema("SQL Exception", tipoError);
            }

            try {
                conexion.close();
            } catch (SQLException e) {
                //Error al cerrar conexion con la Base de Datos
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_6_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_6_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarTramitesPorFechaErrorSistema("SQL Exception", tipoError);
            }
            System.out.println("Lista enviada con exito");
            return tramites;

        } else {
            //Respuesta vacia
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_7);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_7);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarTramitesPorFechaErrorAplicacion("Exception", tipoError);
        }

    }

    public static List<Operativo> getOperativosPorFecha(String fecha)
            throws ListarOperativosPorFechaErrorSistema,
            ListarOperativosPorFechaErrorAplicacion {

        if (fecha == null) {
            System.out.println("No se ingreso el parametro");
        } else {
            System.out.println("El valor Ingresado es: " + fecha);
        }

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
//      Considerar eliminar el relation.type y considerar usarlo cuando se quiere saber a cuál institución pertenece

        //String query = " select i.identifier, c.text1, c.text_area5,c.text_area8, c.text5, c.text4, c.text_area4, c.text_area1, c.mod_date "
        //      + " from contentlet c inner join inode i on i.inode = c.inode "
        //    + " where (c.structure_inode = 107379) "
        //  + " and i.identifier in "
        //+ " (select tree.child from tree where "
        //+ " tree.relation_type = 'Directorio-Tramite') "
        // + " and (c.live is true) and c.mod_date >'" +fecha+ "' order by c.mod_date";

        /**
         * Falata Ingresar el query que se va a ejecutar contra la base de datos
         * del DOTCMS
         */
        fecha = fecha.trim();
        if (fecha.equals("")) {
            //Parametro invalido el campo se encuentra vacio
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarOperativosPorFechaErrorAplicacion("Exception", tipoError);
        }


        if (fecha.length() != 10 || fecha.charAt(4) != '-' || fecha.charAt(7) != '-') {
            //Parametro invalido el campo se encuentra vacio
            System.out.println("Entrando a la validacion de cantidad de caracteres " + fecha.length() + ": " + fecha.charAt(4) + " :" + fecha.charAt(7));
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarOperativosPorFechaErrorAplicacion("Exception", tipoError);

        }

        String query = "select identifier, text1, text2, text3, date1, date2, mod_date "
                + " from operativos "
                + " where mod_date >'" + fecha + "' "
                + " order by mod_date; ";

        try {
            //Iniciando conexion
            conexion = Conexion.iniciarConexion();
        } catch (SQLException e) {
            //Error al iniciar conexion
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_2_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_2_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarOperativosPorFechaErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Inicializando la sentencia sql
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            //Error inicializando la sentencia sql
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_3_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_3_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarOperativosPorFechaErrorSistema("SQL Exception", tipoError);
        }

        try {
            resultado = sentencia.executeQuery(query);
        } catch (SQLException e) {
            //Error ejecutando el query contra la Base de Datos
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_4_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_4_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarOperativosPorFechaErrorSistema("SQL Exception", tipoError);
        }

        if (resultado != null) {

            //Leer respuesta
            ArrayList<Operativo> operativos = new ArrayList<Operativo>();
            try {
                boolean existe = false;
                while (resultado.next()) {
                    existe = true;


                    System.out.println(" DEV :: Operativos :: " + resultado.getInt("identifier")
                            + " " + resultado.getString("text1") + " " + resultado.getString("text2"));

                    Operativo operativo = new Operativo(resultado.getInt("identifier"),
                            resultado.getString("text1"),
                            resultado.getString("text2"),
                            resultado.getString("text3"),
                            resultado.getString("date1"),
                            resultado.getString("date2"),
                            resultado.getString("mod_date"));

                    operativos.add(operativo);
                }

                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_8);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_8);
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarOperativosPorFechaErrorAplicacion("Exception", tipoError);
                }
            } catch (SQLException e) {
                //Error al leer respuesta
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_5_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_5_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarOperativosPorFechaErrorSistema("SQL Exception", tipoError);
            }

            try {
                conexion.close();
            } catch (SQLException e) {
                //Error al cerrar conexion con la Base de Datos
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_6_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_6_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarOperativosPorFechaErrorSistema("SQL Exception", tipoError);
            }
            System.out.println("Lista enviada con exito");
            return operativos;

        } else {
            //Respuesta vacia
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_8);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_8);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarOperativosPorFechaErrorAplicacion("Exception", tipoError);
        }
    }

    public static List<Alcaldia> getAlcaldiasEliminadas(String fecha) throws ListarAlcaldiasEliminadasErrorSistema,
            ListarAlcaldiasEliminadasErrorAplicacion {

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;

        fecha = fecha.trim();
        if (fecha.equals("")) {
            //Parametro invalido el campo se encuentra vacio
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarAlcaldiasEliminadasErrorAplicacion("Exception", tipoError);
        }


        if (fecha.length() != 10 || fecha.charAt(4) != '-' || fecha.charAt(7) != '-') {
            //Parametro invalido el campo se encuentra vacio
            System.out.println("Entrando a la validacion de cantidad de caracteres " + fecha.length() + ": " + fecha.charAt(4) + " :" + fecha.charAt(7));
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarAlcaldiasEliminadasErrorAplicacion("Exception", tipoError);

        }

        String query = "select identifier, title "
                + " from alcaldias "
                + " where mod_date > '" + fecha + "' "
                + " and deleted = true "
                + " order by mod_date ";


        try {
            //Iniciando conexion
            conexion = Conexion.iniciarConexion();
        } catch (SQLException e) {
            //Error al iniciar conexion
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_2_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_2_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarAlcaldiasEliminadasErrorSistema("SQL Exception", tipoError);
        }


        try {
            //Inicializando la sentencia sql
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            //Error inicializando la sentencia sql
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_3_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_3_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarAlcaldiasEliminadasErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Ejecutando el query contra la Base de Datos
            resultado = sentencia.executeQuery(query);
        } catch (SQLException e) {
            //Error ejecutando el query contra la Base de Datos
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_4_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_4_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarAlcaldiasEliminadasErrorSistema("SQL Exception", tipoError);
        }

        if (resultado != null) {

            //Leer respuesta
            ArrayList<Alcaldia> alcaldias = new ArrayList<Alcaldia>();
            try {
                boolean existe = false;

                while (resultado.next()) {
                    try {

                        existe = true;
                        System.out.println(" DEV :: Poder :: " + resultado.getInt("identifier")
                                + " " + resultado.getString("title"));

                        Alcaldia alcaldia = new Alcaldia(resultado.getInt("identifier"),
                                resultado.getString("title"));
                        alcaldias.add(alcaldia);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }

                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_9);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_9);
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarAlcaldiasEliminadasErrorAplicacion("Exception", tipoError);
                }

            } catch (SQLException e) {
                //Error al leer respuesta
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_5_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_5_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarAlcaldiasEliminadasErrorSistema("SQL Exception", tipoError);
            }

            try {
                conexion.close();
            } catch (SQLException e) {
                //Error al cerrar conexion con la Base de Datos
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_6_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_6_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarAlcaldiasEliminadasErrorSistema("SQL Exception", tipoError);
            }

            return alcaldias;

        } else {
            //Respuesta vacia
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_9);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_9);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarAlcaldiasEliminadasErrorAplicacion("Exception", tipoError);
        }
    }

    /**
     * musica electronica 2013
     *
     * @param fecha
     * @return
     * @throws ListarInstitucionesEliminadasErrorSistema
     * @throws ListarInstitucionesEliminadasErrorAplicacion
     */
    public static List<Institucion> getInstitucionesEliminadas(String fecha)
            throws ListarInstitucionesEliminadasErrorSistema,
            ListarInstitucionesEliminadasErrorAplicacion {

        if (fecha == null) {
            System.out.println("No se ingreso el parametro");
        } else {
            System.out.println("El valor Ingresado es: " + fecha);
        }

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;

        fecha = fecha.trim();
        if (fecha.equals("")) {
            //Parametro invalido el campo se encuentra vacio
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarInstitucionesEliminadasErrorAplicacion("Exception", tipoError);
        }


        if (fecha.length() != 10 || fecha.charAt(4) != '-' || fecha.charAt(7) != '-') {
            //Parametro invalido el campo se encuentra vacio
            System.out.println("Entrando a la validacion de cantidad de caracteres " + fecha.length() + ": " + fecha.charAt(4) + " :" + fecha.charAt(7));
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarInstitucionesEliminadasErrorAplicacion("Exception", tipoError);

        }

        String query = "select identifier, title "
                + " from instituciones "
                + " where mod_date > '" + fecha + "'"
                + " and deleted = true "
                + " order by mod_date ";
        try {
            //Iniciando conexion
            conexion = Conexion.iniciarConexion();
        } catch (SQLException e) {
            //Error al iniciar conexion
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_2_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_2_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarInstitucionesEliminadasErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Inicializando la sentencia sql
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            //Error inicializando la sentencia sql
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_3_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_3_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarInstitucionesEliminadasErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Ejecutando el query contra la Base de Datos
            resultado = sentencia.executeQuery(query);
        } catch (SQLException e) {
            //Error ejecutando el query contra la Base de Datos
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_4_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_4_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarInstitucionesEliminadasErrorSistema("SQL Exception", tipoError);
        }


        if (resultado != null) {
            //Leer respuesta
            ArrayList<Institucion> instituciones = new ArrayList<Institucion>();
            try {
                boolean existe = false;
                while (resultado.next()) {
                    existe = true;
                    Institucion institucion = new Institucion(resultado.getInt("identifier"),
                            resultado.getString("title"));
                    instituciones.add(institucion);
                }
                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_10);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_10);
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarInstitucionesEliminadasErrorAplicacion("Exception", tipoError);
                }
            } catch (SQLException e) {
                //Error al leer respuesta
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_5_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_5_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarInstitucionesEliminadasErrorSistema("SQL Exception", tipoError);
            }

            try {
                conexion.close();
            } catch (SQLException e) {
                //Error al cerrar conexion con la Base de Datos
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_6_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_6_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarInstitucionesEliminadasErrorSistema("SQL Exception", tipoError);
            }

            return instituciones;

        } else {
            //Respuesta vacia
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_10);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_10);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarInstitucionesEliminadasErrorAplicacion("Exception", tipoError);
        }
    }

    public static List<Tramite> getTramitesEliminados(String fecha)
            throws ListarTramitesEliminadosErrorSistema,
            ListarTramitesEliminadosErrorAplicacion {

        if (fecha == null) {
            System.out.println("No se ingreso el parametro");
        } else {
            System.out.println("El valor Ingresado es: " + fecha);
        }

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;

        fecha = fecha.trim();
        if (fecha.equals("")) {
            //Parametro invalido el campo se encuentra vacio
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarTramitesEliminadosErrorAplicacion("Exception", tipoError);
        }


        if (fecha.length() != 10 || fecha.charAt(4) != '-' || fecha.charAt(7) != '-') {
            //Parametro invalido el campo se encuentra vacio
            System.out.println("Entrando a la validacion de cantidad de caracteres " + fecha.length() + ": " + fecha.charAt(4) + " :" + fecha.charAt(7));
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_12);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_12);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarTramitesEliminadosErrorAplicacion("Exception", tipoError);

        }

        String query = " select identifier, text1 "
                + " from tramites "
                + " where deleted = true "
                + " and mod_date > '" + fecha + "' "
                + " order by mod_date ";

        System.out.println("El valor del query es: " + query);

        try {
            //Iniciando conexion
            conexion = Conexion.iniciarConexion();
        } catch (SQLException e) {
            //Error al iniciar conexion
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_2_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_2_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarTramitesEliminadosErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Inicializando la sentencia sql
            sentencia = conexion.createStatement();
        } catch (SQLException e) {
            //Error inicializando la sentencia sql
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_3_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_3_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarTramitesEliminadosErrorSistema("SQL Exception", tipoError);
        }

        try {
            //Ejecutando el query contra la Base de Datos
            resultado = sentencia.executeQuery(query);
        } catch (SQLException e) {
            //Error ejecutando el query contra la Base de Datos
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasSistema.FALLA_4_CODIGO);
            tipoError.setDescripcion(FallasSistema.FALLA_4_DESCRIPCION + " - " + e.getMessage());
            tipoError.setDetallesTecnicos(e.getClass().toString());
            throw new ListarTramitesEliminadosErrorSistema("SQL Exception", tipoError);
        }

        if (resultado != null) {

            //Leer respuesta
            ArrayList<Tramite> tramites = new ArrayList<Tramite>();
            try {
                boolean existe = false;
                while (resultado.next()) {
                    existe = true;

                    Tramite tramite = new Tramite(resultado.getInt("identifier"),
                            resultado.getString("text1"));


                    tramites.add(tramite);
                }

                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_11);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_11);
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarTramitesEliminadosErrorAplicacion("Exception", tipoError);
                }
            } catch (SQLException e) {
                //Error al leer respuesta
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_5_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_5_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarTramitesEliminadosErrorSistema("SQL Exception", tipoError);
            }

            try {
                conexion.close();
            } catch (SQLException e) {
                //Error al cerrar conexion con la Base de Datos
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_6_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_6_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarTramitesEliminadosErrorSistema("SQL Exception", tipoError);
            }
            System.out.println("Lista enviada con exito");
            return tramites;

        } else {
            //Respuesta vacia
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_11);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_11);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarTramitesEliminadosErrorAplicacion("Exception", tipoError);
        }
    }
}