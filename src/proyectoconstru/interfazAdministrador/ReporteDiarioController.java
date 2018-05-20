/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelodedatos.DetalleReporteDeVentas;
import modelodedatos.ReporteDeVentas;
import proyectoconstru.conexion.ConsultaReporte;

/**
 * FXML Controller class
 *
 * @author Unknown
 */
public class ReporteDiarioController implements Initializable {

    @FXML
    private Label fechaVenta;
    @FXML
    private TableColumn<DetalleReporteDeVentas, String> columnaCodigo;
    @FXML
    private TableColumn<DetalleReporteDeVentas, String> columnaNombre;
    @FXML
    private TableColumn<DetalleReporteDeVentas, Integer> columnaCantidad;
    @FXML
    private TableColumn<DetalleReporteDeVentas, Integer> columnaSubtotal;
    @FXML
    private TableView<DetalleReporteDeVentas> tablaProductosVendidos;
    
    private LocalDate fechaConsulta;
    private ObservableList<DetalleReporteDeVentas> datos = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tablaProductosVendidos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        columnaCodigo.setMaxWidth(15000);
        columnaCantidad.setMaxWidth(10000);
        columnaNombre.setMaxWidth(40000);
        columnaSubtotal.setMaxWidth(20000);
        
        columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoProducto"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadDeProductosVendidos"));
        columnaSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

    }    
    
    public void llenarTabla(){
        ConsultaReporte consulta = new ConsultaReporte();
        ReporteDeVentas reporte = consulta.obtenerReporteDeVentas(this.fechaConsulta);
        ArrayList<DetalleReporteDeVentas> detallesReporte = reporte.obtenerDetallesDeReporte();
        for (DetalleReporteDeVentas detalleReporte : detallesReporte) {
            datos.add(detalleReporte);
        }
        this.tablaProductosVendidos.setItems(datos);
    }
    
    
    public void modificarFecha(LocalDate fechaPicker){
        this.fechaConsulta = fechaPicker;
        String fecha = fechaPicker.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
        this.fechaVenta.setText(fecha);
    }
    
}
