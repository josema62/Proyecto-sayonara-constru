/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Benjamin
 */
public class DetalleReporteDeVentas {

    private StringProperty codigoProducto;
    private StringProperty nombreProducto;
    private IntegerProperty cantidadDeProductosVendidos;
    private IntegerProperty subtotal;

    public DetalleReporteDeVentas(String codigoProducto, String nombreProducto, int cantidadDeProductosVendidos, int subtotal) {
        this.codigoProducto = new SimpleStringProperty(codigoProducto);
        this.nombreProducto = new SimpleStringProperty(nombreProducto);
        this.cantidadDeProductosVendidos = new SimpleIntegerProperty(cantidadDeProductosVendidos);
        this.subtotal = new SimpleIntegerProperty(subtotal);
    }

    public String getCodigoProducto() {
        return this.codigoProducto.getValue();
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto.setValue(codigoProducto);
    }

    public StringProperty getCodigoProductoProperty() {
        return this.codigoProducto;
    }

    public void setCodigoProductoProperty(StringProperty codigoProductoProperty) {
        this.codigoProducto = codigoProductoProperty;
    }

    public String getNombreProducto() {
        return this.nombreProducto.getValue();
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto.setValue(nombreProducto);
    }

    public StringProperty getNombreProductoProperty() {
        return this.nombreProducto;
    }

    public void setNombreProductoProperty(StringProperty nombreProductoProperty) {
        this.nombreProducto = nombreProductoProperty;
    }

    public int getCantidadDeProductosVendidos() {
        return this.cantidadDeProductosVendidos.getValue();
    }

    public void setCantidadDeProductosVendidos(int cantidadDeProductosVendidos) {
        this.cantidadDeProductosVendidos.setValue(cantidadDeProductosVendidos);
    }

    public IntegerProperty getCantidadDeProductosVendidosProperty() {
        return this.cantidadDeProductosVendidos;
    }

    public void setCantidadDeProductosVendidosProperty(IntegerProperty cantidadDeProductosVendidosProperty) {
        this.cantidadDeProductosVendidos = cantidadDeProductosVendidosProperty;
    }

    public int getSubtotal() {
        return this.subtotal.getValue();
    }

    public void setSubtotal(int subtotal) {
        this.subtotal.setValue(subtotal);
    }

    public IntegerProperty getSubtotalProperty() {
        return this.subtotal;
    }

    public void setSubtotalProperty(IntegerProperty subtotalProperty) {
        this.subtotal = subtotalProperty;
    }

}
