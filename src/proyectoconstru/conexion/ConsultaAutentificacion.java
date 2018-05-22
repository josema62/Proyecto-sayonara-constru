/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelodedatos.BD;

/**
 *
 * @author Gabriel
 */
public class ConsultaAutentificacion {
    private final BD bd;
    private final Connection conexion;

    /**
     * Crea la entidad con las funciones para la consulta de autentificacion de
     * usuario
     *
     * @param conexion La conexion a la base de datos.
     */
    public ConsultaAutentificacion() {
        this.bd = BD.obtenerBD();
        this.conexion = this.bd.obtenerConexion();
    }
    
    /**
     * Devuelve la contrasenia del administrador
     * @param rut Rut del usuario del que se quiere obtener la contrasenia
     * @return Cadena con la contrasenia, null en caso de que no exista el 
     * administrador
     */
    public String obtenerContaseniaAdministrador(String rut){
        String consulta = "SELECT contrasenia FROM administrador WHERE rut = ?";
        try (PreparedStatement consultaST = this.conexion.prepareStatement(consulta)){
            consultaST.setString(1, rut);
            ResultSet resultado = consultaST.executeQuery();
            while(resultado.next()){
                return resultado.getString("contrasenia");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaAutentificacion.class.getName()).log(Level.SEVERE,
                                                                          null,
                                                                          ex);
        }
        return null;
    }
    
    /**
     * Devuelve la contrasenia del cajero
     * @param rut Rut del usuario del que se quiere obtener la contrasenia
     * @return Cadena con la contrasenia, null en caso de que no exista el 
     * cajero
     */
    public String obtenerContraseniaCajero(String rut){
        String consulta = "SELECT contrasenia FROM cajero WHERE rut = ?";
        try (PreparedStatement consultaST = this.conexion.prepareStatement(consulta)){
            consultaST.setString(1, rut);
            ResultSet resultado = consultaST.executeQuery();
            while(resultado.next()){
                return resultado.getString("contrasenia");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaAutentificacion.class.getName()).log(Level.SEVERE,
                                                                          null,
                                                                          ex);
        }
        return null;
    }
    
    
    
}
