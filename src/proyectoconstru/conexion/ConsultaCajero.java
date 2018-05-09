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
        this.bd = BD.obtenerBD("root","","3306");
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
            ResultSet resultado = consultaST.executeQuery(consulta);
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
}
