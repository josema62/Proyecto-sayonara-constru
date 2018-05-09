/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.logicaCajero;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Roberto Ureta
 */
public class Cajero {
    private final SimpleStringProperty rut;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty contrasena;
    private final SimpleStringProperty telefono;
    private final SimpleStringProperty direccion;
    private final SimpleBooleanProperty estado;

    public Cajero(String rut, String nombre, String contrasena, String telefono, String direccion, boolean estado) {
        this.rut = new SimpleStringProperty(rut);
        this.nombre = new SimpleStringProperty(nombre);
        this.contrasena = new SimpleStringProperty(contrasena);
        this.telefono = new SimpleStringProperty(telefono);
        this.direccion = new SimpleStringProperty(direccion);
        this.estado = new SimpleBooleanProperty(estado);
    }

    public String getRut() {
        return rut.get();
    }

    public String getNombre() {
        return nombre.get();
    }
    
    public SimpleStringProperty getContrasena() {
        return contrasena;
    }

    public String getTelefono() {
        return telefono.get();
    }

    public String getDireccion() {
        return direccion.get();
    }

    public SimpleBooleanProperty getEstado() {
        return estado;
    }
    
    
    
    
    
}
