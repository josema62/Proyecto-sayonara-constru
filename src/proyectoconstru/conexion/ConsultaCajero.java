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

/**
 *
 * @author Gabriel
 */
public class ConsultaCajero {

    private final Connection conexion;

    /**
     * Crea la entidad con las funciones para la consulta del cajero
     *
     * @param conexion La conexion a la base de datos.
     */
    public ConsultaCajero(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Devuelve un lista con los cajeros
     */
    public List<Cajero> listarCajeros() {
        String consulta = "SELECT usuario.rut, nombre, contrasena, "
                          + "telefono, direccion , estado FROM usuario, cajero "
                          + "WHERE usuario.rut = cajero.rut;";
        ArrayList<Cajero> cajerosNuevos = new ArrayList<Cajero>();

        try (Statement consultaST = this.conexion.createStatement()) {
            ResultSet resultado = consultaST.executeQuery(consulta);
            while (resultado.next()) {
                String rut = resultado.getString("rut");
                String nombre = resultado.getString("nombre");
                String contrasena = resultado.getString("contrasena");
                String telefono = resultado.getString("telefono");
                String direccion = resultado.getString("direccion");
                String estado = resultado.getString("estado");
                Cajero cajero = new Cajero(rut, nombre, contrasena, telefono,
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

        String consulta = "SELECT usuario.rut, nombre, contrasena, "
                                  + "telefono, direccion , estado FROM usuario, cajero "
                          + "WHERE usuario.rut = cajero.rut and usuario.rut = ?;";
        Cajero cajero = null;
        try (PreparedStatement consultaST = this.conexion.prepareStatement(
                consulta)) {
            consultaST.setString(0, rutCajero);
            ResultSet resultado = consultaST.executeQuery(consulta);
            if (resultado.next()) {
                String rut = resultado.getString("rut");
                String nombre = resultado.getString("nombre");
                String contrasena = resultado.getString("contrasena");
                String telefono = resultado.getString("telefono");
                String direccion = resultado.getString("direccion");
                String estado = resultado.getString("estado");
                /*Aqui deberia llenarse una lista con los cajeros y crear los
                    *objetos cajeros
                 */
                cajero = new Cajero(rut, nombre, contrasena, telefono, direccion,
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
     * @param contrasena Cadena que contiene la contrasena del cajero
     * @param telefono Cadena con el numero de telefono del cajero
     * @param direccion Cadena con la direccion del cajero
     */
    public void insertarCajero(String rut, String nombre, String contrasena,
                               String telefono, String direccion) {

        String consulta = "INSERT INTO usuario (rut, nombre, contrasena, "
                                  + "telefono, direccion) "
                                  + "values (?, ?, ?, ?, ?)";
        try (PreparedStatement declaracion = this.conexion
                .prepareStatement(consulta)) {
            declaracion.setString(1, rut);
            declaracion.setString(2, nombre);
            declaracion.setString(3, contrasena);
            declaracion.setString(4, telefono);
            declaracion.setString(5, direccion);
            declaracion.execute();
        }

        catch (SQLException ex) {
            Logger.getLogger(ConsultaCajero.class.getName()).log(Level.SEVERE,
                                                                 null, ex);
        }
    }

    /**
     * Modifica la informacion del cajero
     *
     * @param rut Cadena con el rut del cajero
     * @param nombre Cadena con el nombre del cajero
     * @param contrasena Cadena con el contrasena del cajero
     * @param telefono Cadena con el telefono del cajero
     * @param direccion Cadena con el direccion del cajero
     * @param estado Cadena con el estado del cajero
     */
    public void modificarCajero(String rut, String nombre, String contrasena,
                                String telefono, String direccion,
                                boolean estado) {

        String consultaUsuario = "UPDATE usuario set "
                                         + "nombre = ?,"
                                         + "contrasena = ?,"
                                         + "telefono = ?,"
                                         + "direccion = ? "
                                         + "WHERE rut = ?";
        String consultaCajero = "UPDATE cajero set "
                                        + "estado = ? "
                                        + "WHERE rut = ?";
        try (PreparedStatement declaracionConsultaUsuario = this.conexion
                .prepareStatement(consultaUsuario)) {
            declaracionConsultaUsuario.setString(0, nombre);
            declaracionConsultaUsuario.setString(1, contrasena);
            declaracionConsultaUsuario.setString(2, telefono);
            declaracionConsultaUsuario.setString(3, direccion);
            declaracionConsultaUsuario.setString(4, rut);
            declaracionConsultaUsuario.execute();
            modificarEstadoCajero(rut, estado);
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaCajero.class.getName()).log(Level.SEVERE,
                                                                 null, ex);
        }

    }

    /**
     * Modifica el estado del cajero en la base de datos
     *
     * @param rut El identificador del cajero a modificar
     * @param estado Si es false el cajero estara desactivado, en caso contrario
     * esta activado
     *
     * @throws SQLException
     */
    private void modificarEstadoCajero(String rut, boolean estado) throws SQLException {

        String consulta = "UPDATE cajero set "
                                  + "estado = ? "
                                  + "WHERE rut = ?";
        try (PreparedStatement declaracionConsultaCajero = this.conexion
                .prepareStatement(consulta)) {
            declaracionConsultaCajero.setBoolean(0, estado);
            declaracionConsultaCajero.setString(1, rut);
        }
    }
}
