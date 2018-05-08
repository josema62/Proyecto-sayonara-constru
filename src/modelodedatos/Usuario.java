/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Benjamin
 */
public class Usuario {

    private StringProperty rut;
    private StringProperty nombre;
    private StringProperty contrasenia;
    private StringProperty direccion;
    private StringProperty telefono;

    public Usuario(String rut, String nombre, String contraseña, String direccion, String telefono) {
        this.rut = new SimpleStringProperty(rut);
        this.nombre = new SimpleStringProperty(nombre);
        this.contrasenia = new SimpleStringProperty(contraseña);
        this.direccion = new SimpleStringProperty(direccion);
        this.telefono = new SimpleStringProperty(telefono);
    }
    
    public void agregarUsuario() {
        
    }
    
    public void editarUsuario() {
        
    }
    
    public void seleccionarUsuario() {
        
    }
    public String getRut() {
        return this.rut.getValue();
    }

    public void setRut(String rut) {
        this.rut.setValue(rut);
    }

    public StringProperty getRutProperty() {
        return this.rut;
    }

    public void setRutProperty(StringProperty rutProperty) {
        this.rut = rutProperty;
    }

    public String getNombre() {
        return this.nombre.getValue();
    }

    public void setNombre(String nombre) {
        this.nombre.setValue(nombre);
    }

    public StringProperty getNombreProperty() {
        return this.nombre;
    }

    public void setNombreProperty(StringProperty nombreProperty) {
        this.nombre = nombreProperty;
    }

    public String getContrasenia() {
        return this.contrasenia.getValue();
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia.setValue(contrasenia);
    }

    public StringProperty getContraseniaProperty() {
        return this.contrasenia;
    }

    public void setContraseniaProperty(StringProperty contraseniaProperty) {
        this.contrasenia = contraseniaProperty;
    }

    public String getDireccion() {
        return this.direccion.getValue();
    }

    public void setDireccion(String direccion) {
        this.direccion.setValue(direccion);
    }

    public StringProperty getDireccionProperty() {
        return this.direccion;
    }

    public void setDireccionProperty(StringProperty direccionProperty) {
        this.direccion = direccionProperty;
    }

    public String getTelefono() {
        return this.telefono.getValue();
    }

    public void setTelefono(String telefono) {
        this.telefono.setValue(telefono);
    }

    public StringProperty getTelefonoProperty() {
        return this.telefono;
    }

    public void setTelefonoProperty(StringProperty telefonoProperty) {
        this.telefono = telefonoProperty;
    }

}
