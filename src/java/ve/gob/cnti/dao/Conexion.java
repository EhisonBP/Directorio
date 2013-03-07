package ve.gob.cnti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Define la operación encargada de realizar la conexión a
 * la base de datos.
 * 
 * Los atributos generales de la base de datos son:
 *   <li>Host: 127.0.0.1</li>
 *   <li>Puerto: 5432</li>
 *   <li>Driver: jdbc.postgresql</li>
 *   <li>Nombre: cnti</li>
 *   <li>Usuario: postgres</li>
 *   <li>Contraseña: postgres</li>
 * 
 *
 * Si el usuario desea ejecutar alguna sentencia en la base de datos
 * debe primero ejecutar {@link #iniciarConexion()}
 *
 * @author Ehison Perez
 * 
 */
public class Conexion {
    
    // Dirección ip donde esta ubicada la Base de Datos
    private static String HOST = "localhost";

    // Puerto por el cual se accede a la Base de Datos
    private static int PUERTO = 5432;

    // Driver empleado para establecer la conexion y procesar las solicitudes a la Base de Datos
    private static String DRIVER = "jdbc:postgresql";

    // Nombre de la Base de Datos
    private static String NOMBRE_BD = "dotcms";

    // Usuario de la Base de Datos
    private static String USUARIO = "postgres";

    // Contraseña del usuario de la Base de Datos
    private static String CONTRASEÑA = "22ks2009";
    
    
    public static Connection iniciarConexion() throws SQLException {
        String uri = DRIVER + "://"+ HOST +":" + PUERTO +"/" + NOMBRE_BD;
        Connection conexion = DriverManager.getConnection(uri, USUARIO, CONTRASEÑA);
        return conexion;
    }
    
    
}
