package ve.gob.cnti.dao;

import ve.gob.cnti.falla.sistema.ListarInstitucionesPorPoderErrorSistema;
import ve.gob.cnti.falla.sistema.ListarPoderesErrorSistema;
import ve.gob.cnti.modelo.Institucion;
import ve.gob.cnti.modelo.Alcaldia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ve.gob.cnti.falla.*;
import ve.gob.cnti.falla.aplicacion.ListarInstitucionesPorPoderErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarPoderesErrorAplicacion;
import ve.gob.cnti.falla.aplicacion.ListarTramitesPorPerfilesErrorAplicacion;
import ve.gob.cnti.falla.sistema.ListarTramitesPorPerfilesErrorSistema;
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
 * @author Danielle Mariani
 * @author Ehison Perez
 *
 */
public class DAO {

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
    public static List<Alcaldia> getAlcaldias(String fecha) throws ListarPoderesErrorSistema,
            ListarPoderesErrorAplicacion {


        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        String query = " select ino.identifier, c.title, c.text3, c.text6, c.text5, c.text8, c.text7, t.parent, c.mod_date "
                + " from contentlet c, tree t, inode ino "
                + " where (t.parent = 131910 "
                + " or t.parent = 131912 "
                + " or t.parent = 131914 "
                + " or t.parent = 131915 "
                + " or t.parent = 131916 "
                + " or t.parent = 131917 "
                + " or t.parent = 131918 "
                + " or t.parent = 131919 "
                + " or t.parent = 131920 "
                + " or t.parent = 131921 "
                + " or t.parent = 131922 "
                + " or t.parent = 131923 "
                + " or t.parent = 131924 "
                + " or t.parent = 131925 "
                + " or t.parent = 131926 "
                + " or t.parent = 131927 "
                + " or t.parent = 131928 "
                + " or t.parent = 131929 "
                + " or t.parent = 131930 "
                + " or t.parent = 131931 "
                + " or t.parent = 131932 "
                + " or t.parent = 131933 "
                + " or t.parent = 131934 "
                + " or t.parent = 131935) and c.inode = t.child and c.bool1 = true and c.live = true and language_id = 2 and ino.inode = c.inode and c.mod_date > ' " + fecha + " ' "
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
            ArrayList<Alcaldia> poderes = new ArrayList<Alcaldia>();
            try {
                boolean existe = false;

                while (resultado.next()) {
                    try {
                        String director = resultado.getString("text3");
                        String nombre = resultado.getString("title");
                        String direccion = resultado.getString("text6");
                        String telefono = resultado.getString("text5");
                        String correo = resultado.getString("text7");
                        String web = resultado.getString("text8");
                        if (director.equals("")) {
                            director = "No disponible";
                        }
                        if (nombre.equals("")) {
                            nombre = "No disponible";
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
                                nombre,
                                director,
                                direccion,
                                telefono,
                                web,
                                correo,
                                resultado.getString("mod_date").substring(0, 10),
                                7,
                                resultado.getInt("parent"));
                        poderes.add(poder);
                    } catch (Exception e) {
                        e.getMessage();
                    }
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
     * @throws ListarInstitucionesPorPoderErrorAplicacion
     */
    public static List<Institucion> getInstituciones(String fecha)
            throws ListarInstitucionesPorPoderErrorSistema,
            ListarInstitucionesPorPoderErrorAplicacion {

        String h = null;
        if (fecha == null) {
            System.out.println("No se ingreso el parametro");
        } else {
            System.out.println("El valor Ingresado es: " + fecha);
        }

        Connection conexion = null;
        Statement sentencia = null;
        ResultSet resultado = null;
//        String query = "select c.inode, c.title, c.text_area2 "
//                + "from \"public\".contentlet c, \"public\".tree t "
//                + "where t.parent = " + idPoder + " and c.inode = t.child";

        String query = " select ino.identifier, c.title, c.text3, c.text6, c.text5, c.text8, c.text7, t.parent, c.mod_date "
                + " from contentlet c, tree t, inode ino "
                + " where (t.parent = 128987 "
                + " or t.parent = 131345 "
                + " or t.parent = 131346 "
                + " or t.parent = 131347 "
                + " or t.parent = 131348 "
                + " or t.parent = 131349 "
                + " or t.parent = 143210) and c.inode = t.child and c.live = true and language_id = 2 and ino.inode = c.inode and c.mod_date > ' " + fecha + " ' "
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
                    String director = resultado.getString("text3");
                    String nombre = resultado.getString("title");
                    String direccion = resultado.getString("text6");
                    String telefono = resultado.getString("text5");
                    String correo = resultado.getString("text7");
                    String web = resultado.getString("text8");
                    int poder = resultado.getInt("parent");
                    if (poder == 131345) {
                        poder = 1;
                    }

                    if (poder == 131347) {
                        poder = 2;
                    }

                    if (poder == 131346) {
                        poder = 3;
                    }
                    if (poder == 131348) {
                        poder = 4;
                    }
                    if (poder == 131349) {
                        poder = 5;
                    }
                    if (poder == 128987) {
                        poder = 6;
                    }
                    if (poder == 143210) {
                        poder = 8;
                    }
                    if (director.equals("")) {
                        director = "No disponible";
                    }
                    if (nombre.equals("")) {
                        nombre = "No disponible";
                    } else {
                        nombre = nombre.toString().replace("Ministerio del Poder Popular para la ", "");
                        nombre = nombre.toString().replace("Ministerio del Poder Popular para las ", "");
                        nombre = nombre.toString().replace("Ministerio del Poder Popular para los ", "");
                        nombre = nombre.toString().replace("Ministerio del Poder Popular para el ", "");
                        nombre = nombre.toString().replace("Ministerio del Poder Popular del ", "");
                        nombre = nombre.toString().replace("Ministerio del Poder Popular de ", "");
                        nombre = nombre.toString().replace("Ministerio del Poder Popular para ", "");
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
                    System.out.println(" DEV :: Institución :: " + resultado.getInt("identifier")
                            + " " + nombre);
                    Institucion institucion = new Institucion(resultado.getInt("identifier"),
                            nombre,
                            director,
                            direccion,
                            telefono,
                            web,
                            correo,
                            poder,
                            resultado.getString("mod_date").substring(0, 10));
                    instituciones.add(institucion);
                }

                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_3);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_3);
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarInstitucionesPorPoderErrorAplicacion("Exception", tipoError);
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
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_3);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarInstitucionesPorPoderErrorAplicacion("Exception", tipoError);
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
    public static List<Tramite> getTramites(String fecha)
            throws ListarTramitesPorPerfilesErrorSistema,
            ListarTramitesPorPerfilesErrorAplicacion {

        String h = null;
        if (fecha == null) {
            System.out.println("No se ingreso el parametro");
        } else {
            System.out.println("El valor Ingresado es: " + fecha);
        }

        Limpiador limpiar = new Limpiador();
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

        String query = "select i.identifier, c.text1, c.text_area5,c.text_area8, c.text5, c.text4, c.text_area4, c.text_area1, c.mod_date"
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
            throw new ListarTramitesPorPerfilesErrorSistema("SQL Exception", tipoError);
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
            throw new ListarTramitesPorPerfilesErrorSistema("SQL Exception", tipoError);
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
            throw new ListarTramitesPorPerfilesErrorSistema("SQL Exception", tipoError);
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
                    String requisitos = resultado.getString("text_area8");
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
                            resultado.getString("mod_date").substring(0, 10),
                            1);


                    tramites.add(tramite);
                }

                if (!existe) {
                    //Respuesta vacia
                    TipoError tipoError = new TipoError();
                    tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_4);
                    tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_4);
                    tipoError.setDetallesTecnicos("Detalles Tecnicos");
                    throw new ListarTramitesPorPerfilesErrorAplicacion("Exception", tipoError);
                }
            } catch (SQLException e) {
                //Error al leer respuesta
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_5_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_5_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarTramitesPorPerfilesErrorSistema("SQL Exception", tipoError);
            }

            try {
                conexion.close();
            } catch (SQLException e) {
                //Error al cerrar conexion con la Base de Datos
                TipoError tipoError = new TipoError();
                tipoError.setCodigo(FallasSistema.FALLA_6_CODIGO);
                tipoError.setDescripcion(FallasSistema.FALLA_6_DESCRIPCION + " - " + e.getMessage());
                tipoError.setDetallesTecnicos(e.getClass().toString());
                throw new ListarTramitesPorPerfilesErrorSistema("SQL Exception", tipoError);
            }
            System.out.println("Lista enviada con exito");
            return tramites;

        } else {
            //Respuesta vacia
            TipoError tipoError = new TipoError();
            tipoError.setCodigo(FallasAplicacion.CODIGO_FALLA_4);
            tipoError.setDescripcion(FallasAplicacion.DESCRIPCION_FALLA_4);
            tipoError.setDetallesTecnicos("Detalles Tecnicos");
            throw new ListarTramitesPorPerfilesErrorAplicacion("Exception", tipoError);
        }

    }
}