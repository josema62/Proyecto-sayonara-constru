/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.conexion;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benjamin
 */
public class ConsultaAdministrador extends Consulta {
    
    public ConsultaAdministrador() {
        super();
    }
    
    
    /**
     * Cambia el estado de conexión del administrador dependiente de lo que se ingrese.
     * El estado 'conectado' indica si ahora figurará como "conectado o desconectado al sistema" en la base de datos. 
     * @param conectado El estado nuevo de la conexión del administrador al sistema.
     * @param rutAdministrador Rut del administrador al que se le quiere cambiar el estado de conexión.
     * @return Retorna true si logra cambiarle el estado. Retorna false si hay algún fallo o no logra encontrar al administrador con el rut especificado.
     */
    private boolean cambiarEstadoDeConexionAdministrador(boolean conectado,String rutAdministrador) {
        
        String consulta = "{CALL cambiar_estado_conexion_administrador(?,?)}";
        
        try(CallableStatement declaracion = this.conexion.prepareCall(consulta)) {
            
            declaracion.setString(1, rutAdministrador);
            declaracion.setBoolean(2, conectado);
            declaracion.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaAdministrador.class.getName()).log(Level.SEVERE,
                                                                        null,
                                                                        ex);
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Cambia el estado de conexión del administrador a 'conectado'
     * @param rutAdministrador El rut del administrador que se va a conectar.
     * @return Retorna true si logra cambiarle el estado. Retorna false si hay algún fallo o no logra encontrar al administrador con el rut especificado.

     */
    public boolean conectarAdministrador(String rutAdministrador) {
        boolean conectado = true;
        return this.cambiarEstadoDeConexionAdministrador(conectado, rutAdministrador);
    }
    
    /**
     * Cambia el estado de conexión del administrador a 'desconectado'
     * @param rutAdministrador El rut del administrador al que se va a desconectar.
     * @return Retorna true si logra cambiarle el estado. Retorna false si hay algún fallo o no logra encontrar al administrador con el rut especificado.
     */
    public boolean desconectarAdministrador(String rutAdministrador) {
        boolean conectado = false;
        return this.cambiarEstadoDeConexionAdministrador(conectado, rutAdministrador);
    }
    
    /**
     * Verifica que si el administrador está conectado.
     * @param rutAdministrador Rut del administrador que del que se requiere saber si está conectado o no.
     * @return Retorna true si está conectado. Retorna false si no está conectado o no está registrado dicho un administrador con el rut especificado.
     */
    public boolean estaAdministradorConectado(String rutAdministrador) {
        String consulta = "{?=CALL verificar_si_administrador_esta_conectado(?)}";
        
        boolean estaConectado = false;
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)){
            
            declaracion.registerOutParameter(1, java.sql.Types.BOOLEAN);
            declaracion.setString(2, rutAdministrador);
            
            
            declaracion.execute();
            estaConectado = declaracion.getBoolean(1);
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaAdministrador.class.getName()).log(Level.SEVERE,
                                                                        null,
                                                                        ex);
        }
        
        return estaConectado;
    }
}
