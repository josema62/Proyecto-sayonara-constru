/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Benjamin
 */
public class Cajero extends Usuario {

    private BooleanProperty estado;

    public Cajero(String rut, String nombre, String contrasenia, String direccion, String telefono, boolean estado) {
        super(rut, nombre, contrasenia, direccion, telefono);
        this.estado = new SimpleBooleanProperty(estado);

    }

    public void desactivarUsuario() {

    }

    public boolean getEstado() {
        return this.estado.getValue();
    }

    public void setEstado(boolean estado) {
        this.estado.setValue(estado);
    }

    public BooleanProperty getEstadoProperty() {
        return this.estado;
    }

    public void setEstadoProperty(BooleanProperty estadoProperty) {
        this.estado = estadoProperty;
    }

}
