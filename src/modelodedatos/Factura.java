/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;

import java.util.HashMap;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Benjamin
 */
public class Factura {

    private IntegerProperty numeroFactura;
    private StringProperty fechaEmision;
    private IntegerProperty valorIva;
    private IntegerProperty valorNeto;
    private IntegerProperty total;

    //Llave: codigo de producto;    Valor: Detalle del producto (cantidad, precio unitario y subtotal)
    private HashMap<String, DetalleProducto> detalleProductos;

    public Factura(int numeroFactura, String fechaEmision, int valorIva, int valorNeto, int total, HashMap<String, DetalleProducto> detalleProductos) {
        this.numeroFactura = new SimpleIntegerProperty(numeroFactura);
        this.fechaEmision = new SimpleStringProperty(fechaEmision);
        this.valorIva = new SimpleIntegerProperty(valorIva);
        this.valorNeto = new SimpleIntegerProperty(valorNeto);
        this.detalleProductos = detalleProductos;
        this.total = new SimpleIntegerProperty(total);
    }

    
    
    
    public void agregarFactura() {
    }

    public void seleccionarFactura() {
    }

    public HashMap<String, DetalleProducto> getDetalleProductos() {
        return detalleProductos;
    }

    public void setDetalleProductos(HashMap<String, DetalleProducto> detalleProductos) {
        this.detalleProductos = detalleProductos;
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

    public String getFechaEmision() {
        return this.fechaEmision.getValue();
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision.setValue(fechaEmision);
    }

    public StringProperty getFechaEmisionProperty() {
        return this.fechaEmision;
    }

    public void setFechaEmisionProperty(StringProperty fechaEmisionProperty) {
        this.fechaEmision = fechaEmisionProperty;
    }

    public int getValorIva() {
        return this.valorIva.getValue();
    }

    public void setValorIva(int valorIva) {
        this.valorIva.setValue(valorIva);
    }

    public IntegerProperty getValorIvaProperty() {
        return this.valorIva;
    }

    public void setValorIvaProperty(IntegerProperty valorIvaProperty) {
        this.valorIva = valorIvaProperty;
    }

    public int getValorNeto() {
        return this.valorNeto.getValue();
    }

    public void setValorNeto(int valorNeto) {
        this.valorNeto.setValue(valorNeto);
    }

    public IntegerProperty getValorNetoProperty() {
        return this.valorNeto;
    }

    public void setValorNetoProperty(IntegerProperty valorNetoProperty) {
        this.valorNeto = valorNetoProperty;
    }

    public int getTotal() {
        return this.total.getValue();
    }

    public void setTotal(int total) {
        this.total.setValue(total);
    }

    public IntegerProperty getTotalProperty() {
        return this.total;
    }

    public void setTotalProperty(IntegerProperty totalProperty) {
        this.total = totalProperty;
    }

}
