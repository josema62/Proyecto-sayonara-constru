/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelodedatos.BD;
import modelodedatos.Cajero;

/**
 *
 * @author Gabriel
 */
public class ConsultaCajero {
    private final BD bd;
    private final Connection conexion;

    /**
     * Crea la entidad con las funciones para la consulta del cajero
     *
     * @param conexion La conexion a la base de datos.
     */
    public ConsultaCajero() {
        this.bd = BD.obtenerBD();
        this.conexion = this.bd.obtenerConexion();
    }

    /**
     * Devuelve un lista con los cajeros
     */
    public List<Cajero> listarCajeros() {
        String consulta = "SELECT rut, nombre, telefono, direccion, "
                                  + "contrasenia, estado FROM cajero;";
        ArrayList<Cajero> cajerosNuevos = new ArrayList<Cajero>();

        try (Statement consultaST = this.conexion.createStatement()) {
            ResultSet resultado = consultaST.executeQuery(consulta);
            while (resultado.next()) {
                String rut = resultado.getString("rut");
                String nombre = resultado.getString("nombre");
                String telefono = resultado.getString("telefono");
                String direccion = resultado.getString("direccion");
                String contrasenia = resultado.getString("contrasenia");
                boolean estado = resultado.getBoolean("estado");
                Cajero cajero = new Cajero(rut, nombre, contrasenia, telefono,
                                           direccion, estado);
                cajerosNuevos.add(cajero);
            }
        }
        catch (SQLException ex) {
            /*Tratar la excepcion o mejor enviarla para que en la principal
             *se abra una ventana?
             */
            Logger.getLogger(ConsultaCajero.class.getName()).log(Level.SEVERE,
                                                                 null, ex);
        }
        //Si no se pudo obtener cajeros de la db devuelveremos null
        return cajerosNuevos.isEmpty() ? null : cajerosNuevos;
    }

    /**
     * Busca un cajero especifico a traves del rut
     *
     * @param rutCajero Cadena que contiene el rut del cajero
     *
     * @return La entidad que representa al cajero, null en caso de que no
     * exista.
     */
    public Cajero buscarCajero(String rutCajero) {

        String consulta = "SELECT rut, nombre, telefono, direccion, "
                                  + "contrasenia, estado FROM cajero "
                                  + "WHERE rut = ?;";
        Cajero cajero = null;
        try (PreparedStatement consultaST = this.conexion.prepareStatement(
                consulta)) {
            consultaST.setString(1, rutCajero);
            ResultSet resultado = consultaST.executeQuery();
            if (resultado.next()) {
                String rut = resultado.getString("rut");
                String nombre = resultado.getString("nombre");
                String telefono = resultado.getString("telefono");
                String direccion = resultado.getString("direccion");
                String contrasenia = resultado.getString("contrasenia");
                boolean estado = resultado.getBoolean("estado");
                /*Aqui deberia llenarse una lista con los cajeros y crear los
                    *objetos cajeros
                 */
                cajero = new Cajero(rut, nombre, contrasenia, telefono,
                                    direccion,
                                    estado);
            }
        }
        catch (SQLException ex) {
            /*Tratar la excepcion o mejor enviarla para que en la principal
             *se abra una ventana?
             */
            Logger.getLogger(ConsultaCajero.class.getName()).log(Level.SEVERE,
                                                                 null, ex);
        }
        return cajero;
    }

    /**
     * Inserta un cajero a la base de datos
     *
     * @param rut Cadena con el rut del cajero
     * @param nombre Cadena con el nombre del cajero
     * @param contrasenia Cadena que contiene la contrasenia del cajero
     * @param telefono Cadena con el numero de telefono del cajero
     * @param direccion Cadena con la direccion del cajero
     * @return regresa false si no se pudo realizar la operacion de insercion
     * true en caso contrario.
     */
    public boolean insertarCajero(String rut, String nombre, String contrasenia,
                               String telefono, String direccion, boolean estado) {

        String consulta = "INSERT INTO cajero (rut, nombre, telefono, "
                                  + "direccion, contrasenia, estado) "
                                  + "values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement declaracion = this.conexion
                .prepareStatement(consulta)) {
            declaracion.setString(1, rut);
            declaracion.setString(2, nombre);
            declaracion.setString(3, telefono);
            declaracion.setString(4, direccion);
            declaracion.setString(5, contrasenia);
            declaracion.setBoolean(6, estado);

            declaracion.execute();
        }

        catch (SQLException ex) {
            Logger.getLogger(ConsultaCajero.class.getName()).log(Level.SEVERE,
                                                                 null, ex);
            return false;
        }
        return true;
    }

    /**
     * Modifica la informacion del cajero
     *
     * @param rut Cadena con el rut del cajero
     * @param nombre Cadena con el nombre del cajero
     * @param contrasenia Cadena con el contrasenia del cajero
     * @param telefono Cadena con el telefono del cajero
     * @param direccion Cadena con el direccion del cajero
     * @param estado Cadena con el estado del cajero
     * @return Devuelve false si no se pudo hacer la modificacion,
     * true en caso contrario
     * 
     */
    public boolean modificarCajero(String rut, String nombre, String contrasenia,
                                String telefono, String direccion,
                                boolean estado) {

        String consultaCajero = "UPDATE cajero set "
                                        + "nombre = ?,"
                                        + "telefono = ?,"
                                        + "direccion = ?, "
                                        + "contrasenia = ?,"
                                        + "estado = ? "
                                        + "WHERE rut = ?";

        try (PreparedStatement declaracionConsultaCajero = this.conexion
                .prepareStatement(consultaCajero)) {
            declaracionConsultaCajero.setString(1, nombre);
            declaracionConsultaCajero.setString(2, telefono);
            declaracionConsultaCajero.setString(3, direccion);
            declaracionConsultaCajero.setString(4, contrasenia);
            declaracionConsultaCajero.setBoolean(5, estado);
            declaracionConsultaCajero.setString(6, rut);
            
            declaracionConsultaCajero.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaCajero.class.getName()).log(Level.SEVERE,
                                                                 null, ex);
            return false;
        }
        return true;
    }
    
    
    /**
     * Cambia el estado de conexión del cajero dependiente de lo que se ingrese.
     * El estado 'conectado' indica si ahora figurará como "conectado o desconectado al sistema" en la base de datos. 
     * @param conectado El estado nuevo de la conexión del cajero al sistema.
     * @param rutCajero Rut del cajero al que se le quiere cambiar el estado de conexión.
     * @return Retorna true si logra cambiarle el estado. Retorna false si hay algún fallo o no logra encontrar al cajero con el rut especificado.
     */
    private boolean cambiarEstadoDeConexionCajero(boolean conectado,String rutCajero) {
        
        String consulta = "{CALL cambiar_estado_conexion_cajero(?,?)}";
        
        try(CallableStatement declaracion = this.conexion.prepareCall(consulta)) {
            
            declaracion.setString(1, rutCajero);
            declaracion.setBoolean(2, conectado);
            declaracion.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaCajero.class.getName()).log(Level.SEVERE,
                                                                        null,
                                                                        ex);
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Cambia el estado de conexión del cajero a 'conectado'
     * @param rutCajero El rut del cajero que se va a conectar.
     * @return Retorna true si logra cambiarle el estado. Retorna false si hay algún fallo o no logra encontrar al cajero con el rut especificado.
     */
    public boolean conectarCajero(String rutCajero) {
        boolean conectado = true;
        return this.cambiarEstadoDeConexionCajero(conectado, rutCajero);
    }
    
    /**
     * Cambia el estado de conexión del cajero a 'desconectado'
     * @param rutCajero El rut del cajero al que se va a desconectar.
     * @return Retorna true si logra cambiarle el estado. Retorna false si hay algún fallo o no logra encontrar al cajero con el rut especificado.
     */
    public boolean desconectarCajero(String rutCajero) {
        boolean conectado = false;
        return this.cambiarEstadoDeConexionCajero(conectado, rutCajero);
    }
    
    /**
     * Verifica que si el cajero está conectado.
     * @param rutCajero Rut del cajero que del que se requiere saber si está conectado o no.
     * @return Retorna true si está conectado. Retorna false si no está conectado o no está registrado dicho cajero con el rut especificado.
     */
    public boolean estaCajeroConectado(String rutCajero) {
        String consulta = "{?=CALL verificar_si_cajero_esta_conectado(?)}";
        
        boolean estaConectado = false;
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)){
            
            declaracion.registerOutParameter(1, java.sql.Types.BOOLEAN);
            declaracion.setString(2, rutCajero);
            
            
            declaracion.execute();
            estaConectado = declaracion.getBoolean(1);
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaCajero.class.getName()).log(Level.SEVERE,
                                                                        null,
                                                                        ex);
        }
        
        return estaConectado;
    }
}
