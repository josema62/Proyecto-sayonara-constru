/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benjamin
 */
public class BD {

    private static BD bd;

    /**
     * La base de datos esta en localhost y se llama proyectosayonaracontru
     * "jdbc:mysql://localhost/proyectosayonaracontru"
     * Si se necesita que sea remota entonces
     * "jdbc:mysql://ip:port/proyectosayonaracontru"
     */
    private String url = "jdbc:mysql://localhost/proyectosayonaracontru";
    private String usuario;
    private String contrasenia;
    private String puerto;
    private Connection conexion;

    private BD(String usuario, String contrasenia, String puerto) {

        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.puerto = puerto;

    }

    public static BD obtenerBD(String usuario, String contrasenia, String puerto) {
        if (BD.bd == null) {
            BD.bd = new BD(usuario, contrasenia, puerto);
        }

        return BD.bd;
    }

    /**
     * Inicia la coneccion a la base de datos
     *
     * @return Devuelve true si la coneccion fue exitosa, false en caso
     * contario
     */
    public boolean iniciarConexion() {
        if (this.conexion == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                this.conexion = DriverManager.getConnection(url, usuario,
                                                            contrasenia);
                System.out.println("base de datos conectada");
            }
            catch (Exception ex) {
                System.out.println("Error al conectar a la base de datos");
                return false;
            }
        }
        return true;
    }

    /**
     * Devuelve la conexion a la base de datos
     *
     * @return El objeto que representa la conexion a la base de datos
     */
    public Connection obtenerConexion() {
        if (this.conexion != null) {
            return this.conexion;
        }
        return null;
    }

    /**
     * Cierra la coneccion con la base de datos
     *
     * @return Devuelve false si hubo algun problema y true en caso contrario
     */
    public boolean cerrarConexion() {
        if (this.conexion != null) {
            try {
                this.conexion.close();
                this.conexion = null;
            }
            catch (SQLException ex) {
                Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

}
