/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;

import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Benjamin
 */
public class Proveedor {

    private StringProperty rut;
    private StringProperty nombre;
    private StringProperty correo;
    private StringProperty razonSocial;
    private StringProperty telefonoObligatorio;
    private BooleanProperty estado;
    private ObservableList<Producto> listaProductos;
    private StringProperty telefonoOpcional;
    private StringProperty direccion;

    public Proveedor(String rut, String nombre, String correo, String razonSocial, String telefonoObligatorio, boolean estado, ArrayList<Producto> listaProductos, String telefonoOpcional, String direccion) {
        this.rut = new SimpleStringProperty(rut);
        this.nombre = new SimpleStringProperty(nombre);
        this.correo = new SimpleStringProperty(correo);
        this.razonSocial = new SimpleStringProperty(razonSocial);
        this.telefonoObligatorio = new SimpleStringProperty(telefonoObligatorio);
        this.estado = new SimpleBooleanProperty(estado);
        this.listaProductos = FXCollections.observableArrayList(listaProductos);
        this.telefonoOpcional = new SimpleStringProperty(telefonoOpcional);
        this.direccion = new SimpleStringProperty(direccion);
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

    public String getCorreo() {
        return this.correo.getValue();
    }

    public void setCorreo(String correo) {
        this.correo.setValue(correo);
    }

    public StringProperty getCorreoProperty() {
        return this.correo;
    }

    public void setCorreoProperty(StringProperty correoProperty) {
        this.correo = correoProperty;
    }

    public String getRazonSocial() {
        return this.razonSocial.getValue();
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial.setValue(razonSocial);
    }

    public StringProperty getRazonSocialProperty() {
        return this.razonSocial;
    }

    public void setRazonSocialProperty(StringProperty razonSocialProperty) {
        this.razonSocial = razonSocialProperty;
    }

    public String getTelefonoObligatorio() {
        return this.telefonoObligatorio.getValue();
    }

    public void setTelefonoObligatorio(String telefonoObligatorio) {
        this.telefonoObligatorio.setValue(telefonoObligatorio);
    }

    public StringProperty getTelefonoObligatorioProperty() {
        return this.telefonoObligatorio;
    }

    public void setTelefonoObligatorioProperty(StringProperty telefonoObligatorioProperty) {
        this.telefonoObligatorio = telefonoObligatorioProperty;
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

    public String getTelefonoOpcional() {
        return this.telefonoOpcional.getValue();
    }

    public void setTelefonoOpcional(String telefonoOpcional) {
        this.telefonoOpcional.setValue(telefonoOpcional);
    }

    public StringProperty getTelefonoOpcionalProperty() {
        return this.telefonoOpcional;
    }

    public void setTelefonoOpcionalProperty(StringProperty telefonoOpcionalProperty) {
        this.telefonoOpcional = telefonoOpcionalProperty;
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

    public ObservableList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ObservableList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    

}
