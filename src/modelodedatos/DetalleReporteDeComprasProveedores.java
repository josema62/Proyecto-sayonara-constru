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
public class DetalleReporteDeComprasProveedores {

  
    private StringProperty nombreProveedor;
    private IntegerProperty numeroFactura;
    private StringProperty fechaEmisionFactura;
    private StringProperty codigoProducto;
    private StringProperty nombreProducto;
    private IntegerProperty precioUnitario;
    private IntegerProperty cantidadDeProductosComprados;
    private IntegerProperty subtotal;

    public DetalleReporteDeComprasProveedores(String nombreProveedor, int numeroFactura, String fechaEmisionFactura,
                                              String codigoProducto, String nombreProducto, int precioUnitario,
                                              int cantidadDeProductosComprados, int subtotal) {
        this.nombreProveedor = new SimpleStringProperty(nombreProveedor);
        this.numeroFactura = new SimpleIntegerProperty(numeroFactura);
        this.fechaEmisionFactura = new SimpleStringProperty(fechaEmisionFactura);
        this.codigoProducto = new SimpleStringProperty(codigoProducto);
        this.nombreProducto = new SimpleStringProperty(nombreProducto);
        this.precioUnitario = new SimpleIntegerProperty(precioUnitario);
        this.cantidadDeProductosComprados = new SimpleIntegerProperty(cantidadDeProductosComprados);
        this.subtotal = new SimpleIntegerProperty(subtotal);
    }

    public String getNombreProveedor() {
        return this.nombreProveedor.getValue();
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor.setValue(nombreProveedor);
    }

    public StringProperty getNombreProveedorProperty() {
        return this.nombreProveedor;
    }

    public void setNombreProveedorProperty(StringProperty nombreProveedorProperty) {
        this.nombreProveedor = nombreProveedorProperty;
    }

    public int getNumeroFactura() {
        return this.numeroFactura.getValue();
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura.setValue(numeroFactura);
    }

    public IntegerProperty getNumeroFacturaProperty() {
        return this.numeroFactura;
    }

    public void setNumeroFacturaProperty(IntegerProperty numeroFacturaProperty) {
        this.numeroFactura = numeroFacturaProperty;
    }

    public String getFechaEmisionFactura() {
        return this.fechaEmisionFactura.getValue();
    }

    public void setFechaEmisionFactura(String fechaEmisionFactura) {
        this.fechaEmisionFactura.setValue(fechaEmisionFactura);
    }

    public StringProperty getFechaEmisionFacturaProperty() {
        return this.fechaEmisionFactura;
    }

    public void setFechaEmisionFacturaProperty(StringProperty fechaEmisionFacturaProperty) {
        this.fechaEmisionFactura = fechaEmisionFacturaProperty;
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

    public int getPrecioUnitario() {
        return this.precioUnitario.getValue();
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario.setValue(precioUnitario);
    }

    public IntegerProperty getPrecioUnitarioProperty() {
        return this.precioUnitario;
    }

    public void setPrecioUnitarioProperty(IntegerProperty precioUnitarioProperty) {
        this.precioUnitario = precioUnitarioProperty;
    }

    public int getCantidadDeProductosComprados() {
        return this.cantidadDeProductosComprados.getValue();
    }

    public void setCantidadDeProductosComprados(int cantidadDeProductosComprados) {
        this.cantidadDeProductosComprados.setValue(cantidadDeProductosComprados);
    }

    public IntegerProperty getCantidadDeProductosCompradosProperty() {
        return this.cantidadDeProductosComprados;
    }

    public void setCantidadDeProductosCompradosProperty(IntegerProperty cantidadDeProductosCompradosProperty) {
        this.cantidadDeProductosComprados = cantidadDeProductosCompradosProperty;
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
