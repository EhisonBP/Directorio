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
import ve.gob.cnti.falla.aplicacion.ListarAlcaldiasPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesPorFechaErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesPorPoderesErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarPoderesErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarTramitesPorInstitucionErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarTramitesPorFechaErrorAplicacion;
import ve.gob.cnti.falla.sistema.ListarAlcaldiasPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarInstitucionesPorFechaErrorSistema;
import ve.gob.cnti.falla.sistema.ListarInstitucionesPorPoderErrorSistema;
import ve.gob.cnti.falla.sistema.ListarPoderesErrorSistema;
import ve.gob.cnti.falla.sistema.ListarTramitesPorInstitucionErrorSistema;
import ve.gob.cnti.falla.sistema.ListarTramitesPorFechaErrorSistema;
import ve.gob.cnti.modelo.Alcaldia;
import ve.gob.cnti.modelo.Institucion;
import ve.gob.cnti.modelo.Poder;
import ve.gob.cnti.modelo.Tramite;
import ve.gob.cnti.servicio.ServicioDirectorioEstadoVenezolano;

/**
 * Operaciones asociadas a las que posee el servicio web definido en {@link ServicioDirectorioEstadoVenezolano}
 *
 * Las operaciones empleadas se definen a continuación: <li>Obtener Poderes {@link #getPoderes()}</li>
 * <li>Obtener Instituciones {@link #getInstituciones(int) }</li> <li>Obtener
 * Tramites {@link #getTramites(int) }</li>
 *
 *
 * Si el usuario desea obtener el listado de Poderes debe invocar a {@link #getPoderes()
 * }
 *
 * Si el usuario desea obtener el listado de Institucones asociadas a un Poder
 * en especifico debe invocar a
 * {@link #getInstituciones(int) }
 *
 * Si el usuario desea obtener el listado de Tramites asociados a una
 * Institucion debe invocar a {@link #getTramites(int) }
 *
 * @author Ehison Perez
 *
 */
public class DAO {

    private static final int ID_CATEGORIA_PODER = 123836;
    public static Limpiador limpiar = new Limpiador();
    /**
     *
     * Encargado de listar los objetos tipo {@link Poder}, para ello
     * debe invocar la clase {@link Conexion} para realizar la conexión
     * con la base de datos, ejecutar la consulta a la base de datos
     * y listar la respuesta (de haberla) en un arreglo.
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
        String query = "select c.inode, c.category_name "
                + "from \"public\".category c, \"public\".tree t "
                + "where c.active is true "
                + "and t.parent = " + ID_CATEGORIA_PODER
                + "and c.inode = t.child";

        try {
            //Iniciando conexion
            conexion = Conexion.iniciarConexion();
        } catch (SQLException e) {
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
                    Poder poder = new Poder(resultado.getInt("inode"), resultado.getString("category_name"), "Descripcion");
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
     * Encargado de listar los objetos tipo {@link Institucion}, para ello
     * debe invocar la clase {@link Conexion} para realizar la conexión
     * con la base de datos, ejecutar la consulta a la base de datos
     * y listar la respuesta (de haberla) en un arreglo.
     *
     *
     * @param idPoder identificador unico del poder del cual se desea
     *      obtener sus instituciones.
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
//        String query = "select c.inode, c.title, c.text_area2 "
//                + "from \"public\".contentlet c, \"public\".tree t "
//                + "where t.parent = " + idPoder + " and c.inode = t.child";
        String query = "select c.inode, c.title, c.text_area2 "
                + "from \"public\".contentlet c, \"public\".tree t "
                + "where t.parent = " + idPoder + " and c.inode = t.child and c.live = true";

        if (idPoder <= 0) {
            //Parametro invalido
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_1);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_1);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarInstitucionesPorPoderesErrorAplicacion("Exception", tipoError);
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
            throw new ListarInstitucionesPorPoderErrorSistema("SQL Exception", tipoError);
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
            throw new ListarInstitucionesPorPoderErrorSistema("SQL Exception", tipoError);
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
            throw new ListarInstitucionesPorPoderErrorSistema("SQL Exception", tipoError);
        }


        if (resultado != null) {
            //Leer respuesta
            ArrayList<Institucion> instituciones = new ArrayList<Institucion>();
            try {
                boolean existe = false;
                while (resultado.next()) {
                    existe = true;
                    System.out.println(" DEV :: Institución :: " + resultado.getInt("inode")
                            + " " + resultado.getString("title"));
                    Institucion institucion = new Institucion(resultado.getInt("inode"), 
                                                              resultado.getString("title"), 
                                                              resultado.getString("text_area2"), 
                                                              "Dirección Institución", "5555555", 
                                                              "www.institucion.gob.ve");
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
     * Encargado de listar los objetos tipo {@link Tramite}, para ello
     * debe invocar la clase {@link Conexion} para realizar la conexión
     * con la base de datos, ejecutar la consulta a la base de datos
     * y listar la respuesta (de haberla) en un arreglo.
     *

     * @param idInstitucion identificador unico de la institucion del cual
     *          se desea obtener sus instituciones.
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
//        String query = "select c.inode, c.title, c.text4, c.text_area1, c.text_area5, c.text_area7, c.text_area8, c.text_area9, c.bool2 from \"public\".inode i, \"public\".tree t, \"public\".inode i2, \"public\".contentlet c "
//                + " where i.inode = " + idInstitucion
//                + " and t.child = i.identifier"
//                + " and t.relation_type = 'Tramite-Directorio'"
//                + " and i2.identifier = t.parent"
//                + " and c.inode = i2.inode"
//                + " and c.live is true";
        String query = "select c.inode, c.title, c.text4, c.text_area1, c.text_area5, c.text_area7, c.text_area8, c.text_area9, c.bool2 from \"public\".contentlet c inner join \"public\".inode i on i.inode = c.inode"
                + " where (c.structure_inode = 107379)"
                + " and i.identifier in"
                + " (select tree.child from tree where (tree.parent = (select inode.identifier FROM inode INNER JOIN tree ON inode.inode = tree.child WHERE (tree.parent = " + idInstitucion + "))"
                + " and (tree.relation_type = 'Directorio-Tramite'))) "
                + " and (c.live is true)";

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
                    System.out.println(" DEV :: Tramite :: " + resultado.getInt("inode")
                            + " " + resultado.getString("title"));

                    Tramite tramite = new Tramite(resultado.getInt("inode"),
                            resultado.getString("title"), "123",
                            resultado.getString("text_area5"),
                            resultado.getString("text_area7"),
                            "www.institucion.gob.ve/tramite/id_tramite");
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


    /**Metodos Implementados para Android
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
        String query = " select ino.identifier, c.title, c.text3, c.text6, c.text5, c.text8, c.text7, t.parent, c.mod_date "
                + " from contentlet c, tree t, inode ino "
                + " where (t.parent = 131910 "  // Distrito Capital
                + " or t.parent = 131912 "      // Amazonas
                + " or t.parent = 131914 "      // Anzoategui
                + " or t.parent = 131915 "      // Apure
                + " or t.parent = 131916 "      // Aragua
                + " or t.parent = 131917 "      // Barinas   
                + " or t.parent = 131918 "      // Bolivar
                + " or t.parent = 131919 "      // Carabobo
                + " or t.parent = 131920 "      // Cojedes 
                + " or t.parent = 131921 "      // Delta Amacuro
                + " or t.parent = 131922 "      // Falcon 
                + " or t.parent = 131923 "      // Guarico 
                + " or t.parent = 131924 "      // Lara
                + " or t.parent = 131925 "      // Merida
                + " or t.parent = 131926 "      // Miranda
                + " or t.parent = 131927 "      // Monagas
                + " or t.parent = 131928 "      // Nueva Esparta
                + " or t.parent = 131929 "      // Potuguesa
                + " or t.parent = 131930 "      // Sucre
                + " or t.parent = 131931 "      // Tachira
                + " or t.parent = 131932 "      // Trujillo
                + " or t.parent = 131933 "      // Vargas 
                + " or t.parent = 131934 "      // Yaracuy
                + " or t.parent = 131935)"     // Zulia
                + " and c.inode = t.child and c.bool1 = true and c.live = true and c.working = true and language_id = 2 and ino.inode = c.inode and c.mod_date > ' " + fecha + " ' "
                + " order by c.mod_date";


        try {
            //Iniciando conexion
            conexion = Conexion.iniciarConexion();
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
            ArrayList<Alcaldia> poderes = new ArrayList<Alcaldia>();
            try {
                boolean existe = false;

                while (resultado.next()) {
                    try {
                        int estado = resultado.getInt("parent");
                        switch (estado){
                            case 131910:
                                estado = 1;
                                break;
                            case 131912:
                                estado = 2;
                                break;
                            case 131914:
                                estado= 3;
                                break;
                            case 131915:
                                estado= 4;
                                break;
                            case 131916:
                                estado= 5;
                                break;
                            case 131917:
                                estado= 6;
                                break;
                            case 131918:
                                estado= 7;
                                break;
                            case 131919:
                                estado= 8;
                                break;
                            case 131920:
                                estado= 9;
                                break;
                            case 131921:
                                estado= 10;
                                break;
                            case 131922:
                                estado= 11;
                                break;
                            case 131923:
                                estado= 12;
                                break;
                            case 131924:
                                estado= 13;
                                break;
                            case 131925:
                                estado= 14;
                                break;
                            case 131926:
                                estado= 15;
                                break;
                            case 131927:
                                estado= 16;
                                break;
                            case 131928:
                                estado= 17;
                                break;
                            case 131929:
                                estado= 18;
                                break;
                            case 131930:
                                estado= 19;
                                break;
                            case 131931:
                                estado= 20;
                                break;
                            case 131932:
                                estado= 21;
                                break;
                            case 131933:
                                estado= 22;
                                break;
                            case 131934:
                                estado= 23;
                                break;
                            case 131935:
                                estado= 24;
                                break;
                        }
                        
                        String director = resultado.getString("text3");
                        String municipio=resultado.getString("title");
                        String alcaldia=resultado.getString("title");
                        String direccion = resultado.getString("text6");
                        String telefono = resultado.getString("text5");
                        String correo = resultado.getString("text7");
                        String web = resultado.getString("text8");
                        if (director.equals("")) {
                            director = "No disponible";
                        } 
                        if (municipio.equals("")) {
                            municipio = "No disponible";
                        }else{
                            municipio = limpiar.nombreMunicipio(municipio);
                        }
                        if (alcaldia.equals("")) {
                            alcaldia = "No disponible";
                        }else{
                            alcaldia = limpiar.nombreAlcaldia(alcaldia);
                        }
                        if (direccion.equals("")) {
                            direccion = "No disponible";
                        }
                        if (telefono.equals("")) {
                            telefono = "No disponible";
                        }
                        if (correo.equals("")) {
                            correo = "No disponible";
                        }
                        if (web.equals("")) {
                            web = "No disponible";
                        }

                        existe = true;
                        System.out.println(" DEV :: Poder :: " + resultado.getInt("identifier")
                                + " " + resultado.getString("title"));

                        Alcaldia poder = new Alcaldia(resultado.getInt("identifier"),
                                alcaldia,
                                municipio,
                                director,
                                direccion,
                                telefono,
                                web,
                                correo,
                                resultado.getString("mod_date"),
                                7,
                                estado);
                        poderes.add(poder);
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

            return poderes;

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

        String h = null;
        if (fecha == null) {
            System.out.println("No se ingreso el parametro");
        } else {
            System.out.println("El valor Ingresado es: " + fecha);
        }

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;

        String query =  "select ino.identifier," 
                       +" replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(c.title, 'de la República Bolivariana de Venezuela', ''), 'Gobernación del Estado ', ''), 'Gobernación del ', ''), 'Ministerio del Poder Popular para la ', ''), 'Ministerio del Poder Popular de ', ''), 'Ministerio del Poder Popular del ', ''), 'Ministerio del Poder Popular para el ', ''), 'Ministerio del Poder Popular para los ', ''), 'Ministerio del Poder Popular para las ', ''), 'Ministerio del Poder Popular para ', '')"
                       +" ,c.title , c.text3, c.text6, c.text5, c.text8, c.text7, t.parent, c.mod_date"
                       +" from contentlet c, tree t, inode ino" 
                       +" where (t.parent = 128987" 
                       +" or t.parent = 131345" 
                       +"or t.parent = 131346" 
                       +"or t.parent = 131347" 
                       +"or t.parent = 131348" 
                       +"or t.parent = 131349" 
                       +"or t.parent = 143210) and c.inode = t.child and c.live = true and c.working = true and language_id = 2 and ino.inode = c.inode and c.mod_date > ' " + fecha + " ' "
                       +"order by c.mod_date";
              
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
                    String director = resultado.getString("text3");
                    String nombreSector = resultado.getString("replace");
                    String nombreInstitucion = resultado.getString("title");
                    String direccion = resultado.getString("text6");
                    String telefono = resultado.getString("text5");
                    String correo = resultado.getString("text7");
                    String web = resultado.getString("text8");
                    int poder = resultado.getInt("parent");
                    
                    switch (poder){
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

                    if (director.equals("")) {
                        director = "No disponible";
                    }
                    if (nombreInstitucion.equals("")) {
                        nombreInstitucion = "No disponible";
                    }
               /**     if (nombreSector.equals("")) {
                        nombreSector = "No disponible";
                    }          */              
                    if (direccion.equals("")) {
                        direccion = "No disponible";
                    }
                    if (telefono.equals("")) {
                        telefono = "No disponible";
                    }
                    if (correo.equals("")) {
                        correo = "No disponible";
                    }
                    if (web.equals("")) {
                        web = "No disponible";
                    }
                   
                    existe = true;
                    System.out.println(" DEV :: Institución :: " + resultado.getInt("identifier")
                            + " " +resultado.getString("replace"));
                    Institucion institucion = new Institucion(resultado.getInt("identifier"),
                            nombreInstitucion,
                            nombreSector,
                            director,
                            direccion,
                            telefono,
                            web,
                            correo,
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

        String h = null;
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

        String query = "select i.identifier, c.text1, c.text_area5, replace(c.text_area8,'*',''), c.text5, c.text4, c.text_area4, c.text_area1, c.mod_date"
                + " from contentlet c, tree t, inode i"
                + " where c.structure_inode = 107379"
                + " and t.parent = i.identifier"
                + " and c.working = true"
                + " and c.language_id = 2 "
                + " and i.inode = c.inode"
                + " and c.inode = t.child"
                + " and c.live = true and c.mod_date >'" + fecha + "' order by c.mod_date";
                
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
                    String costo = resultado.getString("text_area4");
                    String nombre = resultado.getString("text1");
                    String direccion = resultado.getString("text_area5");
                    String telefono = resultado.getString("text4");
                    String requisitos = resultado.getString("replace");
                    String descripcion = resultado.getString("text_area1");
                    String horarios = resultado.getString("text5");
                    if (costo.equals("")) {
                        costo = "No disponible";
                    } else {
                        costo = limpiar.limpiadorEtiquetas(costo);
                    }

                    if (nombre.equals("")) {
                        nombre = "No disponible";
                    } else {
                        nombre = limpiar.limpiadorEtiquetas(nombre);
                    }

                    if (direccion.equals("")) {
                        direccion = "No disponible";
                    } else {
                        direccion = limpiar.limpiadorEtiquetas(direccion);
                    }

                    if (telefono.equals("")) {
                        telefono = "No disponible";
                    } else {
                        telefono = limpiar.limpiadorEtiquetas(telefono);
                    }

                    if (requisitos.equals("")) {
                        requisitos = "No disponible";
                    } else {
                        requisitos = limpiar.limpiadorEtiquetas(requisitos);
                    }

                    if (descripcion.equals("")) {
                        descripcion = "No disponible";
                    } else {
                        descripcion = limpiar.limpiadorEtiquetas(descripcion);
                    }

                    if (horarios.equals("")) {
                        horarios = "No disponible";
                    } else {
                        horarios = limpiar.limpiadorEtiquetas(horarios);
                    }

                    System.out.println(" DEV :: Tramite :: " + resultado.getInt("identifier")
                            + " " + resultado.getString("text1")+ " "+ requisitos);

                    Tramite tramite = new Tramite(resultado.getInt("identifier"),
                            nombre,
                            telefono,
                            horarios,
                            direccion,
                            descripcion,
                            costo,
                            requisitos,
                            resultado.getString("mod_date"),
                            1);


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
}