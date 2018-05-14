package modelodedatos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Benjamin
 * Ésta clase se encarga de envolver los datos relacionados con los detalles de los productos que están en una boleta
 * o factura. Para cada producto en una boleta o factura, se deben mostrar ciertos datos como nombre del producto, la
 * cantidad en la que fue vendido o adquirido, precio únitario y el subtotal.
 */
public class DetalleProducto {

    
    private IntegerProperty codigoProducto;
    private StringProperty nombre;
    private IntegerProperty cantidad;
    private IntegerProperty precioUnitario;
    private IntegerProperty subtotal;
    

    public DetalleProducto(int codigoProducto, String nombre, int cantidad, int precioUnitario, int subtotal) {
        this.codigoProducto = new SimpleIntegerProperty(codigoProducto);
        this.nombre = new SimpleStringProperty(nombre);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.precioUnitario = new SimpleIntegerProperty(precioUnitario);
        this.subtotal = new SimpleIntegerProperty(subtotal);
    }

    public int getCodigoProducto() {
        return this.codigoProducto.getValue();
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto.setValue(codigoProducto);
    }

    public IntegerProperty getCodigoProductoProperty() {
        return this.codigoProducto;
    }

    public void setCodigoProductoProperty(IntegerProperty codigoProductoProperty) {
        this.codigoProducto = codigoProductoProperty;
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

    public int getCantidad() {
        return this.cantidad.getValue();
    }

    
    
    public void setCantidad(int cantidad) {
        this.cantidad.setValue(cantidad);
    }

    public IntegerProperty getCantidadProperty() {
        return this.cantidad;
    }

    public void setCantidadProperty(IntegerProperty cantidadProperty) {
        this.cantidad = cantidadProperty;
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
