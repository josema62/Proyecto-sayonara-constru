/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Benjamin
 */
public class Producto {

    private StringProperty nombre;
    private IntegerProperty stockActual;
    private LongProperty codigo;
    private StringProperty categoria;
    private BooleanProperty estado;
    private IntegerProperty stockMinimo;
    private IntegerProperty precio;

    public Producto(String nombre, int stockActual, long codigo, String categoria, boolean estado, int stockMinimo, int precio) {
        this.nombre = new SimpleStringProperty(nombre);
        this.stockActual = new SimpleIntegerProperty(stockActual);
        this.codigo = new SimpleLongProperty(codigo);
        this.categoria = new SimpleStringProperty(categoria);
        this.estado = new SimpleBooleanProperty(estado);
        this.stockMinimo = new SimpleIntegerProperty(stockMinimo);
        this.precio = new SimpleIntegerProperty(precio);
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

    public int getStockActual() {
        return this.stockActual.getValue();
    }

    public void setStockActual(int stockActual) {
        this.stockActual.setValue(stockActual);
    }

    public IntegerProperty getStockActualProperty() {
        return this.stockActual;
    }

    public void setStockActualProperty(IntegerProperty stockActualProperty) {
        this.stockActual = stockActualProperty;
    }

    public long getCodigo() {
        return this.codigo.getValue();
    }

    public void setCodigo(long codigo) {
        this.codigo.setValue(codigo);
    }

    public LongProperty getCodigoProperty() {
        return this.codigo;
    }

    public void setCodigoProperty(LongProperty codigoProperty) {
        this.codigo = codigoProperty;
    }

    public String getCategoria() {
        return this.categoria.getValue();
    }

    public void setCategoria(String categoria) {
        this.categoria.setValue(categoria);
    }

    public StringProperty getCategoriaProperty() {
        return this.categoria;
    }

    public void setCategoriaProperty(StringProperty categoriaProperty) {
        this.categoria = categoriaProperty;
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

    public int getStockMinimo() {
        return this.stockMinimo.getValue();
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo.setValue(stockMinimo);
    }

    public IntegerProperty getStockMinimoProperty() {
        return this.stockMinimo;
    }

    public void setStockMinimoProperty(IntegerProperty stockMinimoProperty) {
        this.stockMinimo = stockMinimoProperty;
    }

    public int getPrecio() {
        return this.precio.getValue();
    }

    public void setPrecio(int precio) {
        this.precio.setValue(precio);
    }

    public IntegerProperty getPrecioProperty() {
        return this.precio;
    }

    public void setPrecioProperty(IntegerProperty precioProperty) {
        this.precio = precioProperty;
    }

}
