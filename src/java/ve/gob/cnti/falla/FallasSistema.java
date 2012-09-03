package ve.gob.cnti.falla;


/**
 * Posee atributos asociados a los distintos tipos de
 * Fallas de Sistema determinados.
 * 
 * @author Danielle Mariani
 * 
 */
public class FallasSistema {
    
    
    public static final String FALLA_1_CODIGO = "SIS0001F";
    public static final String FALLA_1_DESCRIPCION = "FALLA grave";
    
    /**
     * Fallas a nivel de la Base de Datos
     */
    public static final String FALLA_2_CODIGO = "SIS0002E";
    public static final String FALLA_2_DESCRIPCION = "FALLA al iniciar conexion con la Base de Datos";
    public static final String FALLA_3_CODIGO = "SIS0003E";
    public static final String FALLA_3_DESCRIPCION = "FALLA al inicializar la sentencia sql";
    public static final String FALLA_4_CODIGO = "SIS0004E";
    public static final String FALLA_4_DESCRIPCION = "FALLA al ejecutar el query contra la Base de Datos";
    public static final String FALLA_5_CODIGO = "SIS0005E";
    public static final String FALLA_5_DESCRIPCION = "FALLA Error al procesar respuesta de la Base de Datos";
    public static final String FALLA_6_CODIGO = "SIS0006E";
    public static final String FALLA_6_DESCRIPCION = "FALLA al cerrar conexion de la Base de Datos";
   
    
    
}
