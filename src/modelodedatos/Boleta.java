/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;

import java.util.HashMap;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Benjamin
 */
public class Boleta {

    private IntegerProperty total;
    private HashMap<String, DetalleProducto> detalleProductos;
    private BooleanProperty estado;
    private StringProperty fechaEmision;
    private StringProperty horaEmision;
    private IntegerProperty codigo;
    private StringProperty nombreCajero;
    private StringProperty rutCajero;

    public Boleta(int total, boolean estado, String fechaEmision, String horaEmision, int codigo, String nombreCajero, String rutCajero) {
        this.total = new SimpleIntegerProperty(total);
        this.detalleProductos = new HashMap<>();
        this.estado = new SimpleBooleanProperty(estado);
        this.fechaEmision = new SimpleStringProperty(fechaEmision);
        this.horaEmision = new SimpleStringProperty(horaEmision);
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nombreCajero = new SimpleStringProperty(nombreCajero);
        this.rutCajero = new SimpleStringProperty(rutCajero);
    }
    
    public Boleta(int total, boolean estado, String fechaEmision, String horaEmision,String nombreCajero, String rutCajero) {
        this.total = new SimpleIntegerProperty(total);
        this.detalleProductos = new HashMap<>();
        this.estado = new SimpleBooleanProperty(estado);
        this.fechaEmision = new SimpleStringProperty(fechaEmision);
        this.horaEmision = new SimpleStringProperty(horaEmision);
        this.nombreCajero = new SimpleStringProperty(nombreCajero);
        this.rutCajero = new SimpleStringProperty(rutCajero);
    }

    public void agregarBoleta(){}
    public void deshabilitarBoleta() {}
    public void seleccionarBoleta(){}
    
    
    public HashMap<String, DetalleProducto> getDetalleProductos() {
        return detalleProductos;
    }

    public void agregardetalles(String clave, DetalleProducto value){
        this.detalleProductos.put(clave,value);
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

    public String getHoraEmision() {
        return this.horaEmision.getValue();
    }

    public void setHoraEmision(String horaEmision) {
        this.horaEmision.setValue(horaEmision);
    }

    public StringProperty getHoraEmisionProperty() {
        return this.horaEmision;
    }

    public void setHoraEmisionProperty(StringProperty horaEmisionProperty) {
        this.horaEmision = horaEmisionProperty;
    }

    public int getCodigo() {
        return this.codigo.getValue();
    }

    public void setCodigo(int codigo) {
        this.codigo.setValue(codigo);
    }

    public IntegerProperty getCodigoProperty() {
        return this.codigo;
    }

    public void setCodigoProperty(IntegerProperty codigoProperty) {
        this.codigo = codigoProperty;
    }

    public String getNombreCajero() {
        return this.nombreCajero.getValue();
    }

    public void setNombreCajero(String nombreCajero) {
        this.nombreCajero.setValue(nombreCajero);
    }

    public StringProperty getNombreCajeroProperty() {
        return this.nombreCajero;
    }

    public void setNombreCajeroProperty(StringProperty nombreCajeroProperty) {
        this.nombreCajero = nombreCajeroProperty;
    }

    public String getRutCajero() {
        return this.rutCajero.getValue();
    }

    public void setRutCajero(String rutCajero) {
        this.rutCajero.setValue(rutCajero);
    }

    public StringProperty getRutCajeroProperty() {
        return this.rutCajero;
    }

    public void setRutCajeroProperty(StringProperty rutCajeroProperty) {
        this.rutCajero = rutCajeroProperty;
    }

}
