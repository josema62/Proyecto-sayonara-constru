
package proyectoconstru.conexion;

import java.sql.Connection;
import modelodedatos.BD;

/**
 *
 * @author Benjamin
 */
public abstract class Consulta {
    
    protected final BD bd;
    protected final Connection conexion;

    /**
     * Crea la entidad con las funciones para la consulta del cajero
     *
     * @param conexion La conexion a la base de datos.
     */
    public Consulta() {
        this.bd = BD.obtenerBD();
        this.conexion = this.bd.obtenerConexion();
        
    }
    
    
}
