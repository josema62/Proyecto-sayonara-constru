/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;

/**
 *
 * @author Benjamin
 */
public class BD {
    private BD bd;
    private String usuario;
    private String contrasenia;
    private String puerto;

    private BD(String usuario, String contrasenia, String puerto) {
        
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.puerto = puerto;
        
    }
    
    public BD obtenerBD(String usuario, String contrasenia, String puerto) {
        if(bd == null) this.bd = new BD(usuario, contrasenia, puerto); 
        
        return this.bd;
    }
    
    //public boolean iniciarConexion() {}
    //public boolean cerrarConexion() {}
    
}
