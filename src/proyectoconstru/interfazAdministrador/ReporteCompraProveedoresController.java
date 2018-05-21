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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelodedatos.DetalleReporteDeComprasProveedores;
import modelodedatos.ReporteDeComprasProveedores;
import proyectoconstru.conexion.ConsultaReporte;


/**
 * FXML Controller class
 *
 * @author Unknown
 */
public class ReporteCompraProveedoresController implements Initializable {

    @FXML
    private Label etiquetaFechaInicio;
    @FXML
    private Label etiquetaFechaTermino;
    @FXML
    private TableView<DetalleReporteDeComprasProveedores> tablaCompraProveedores;
    @FXML
    private TableColumn<DetalleReporteDeComprasProveedores, String> columnaProveedor;
    @FXML
    private TableColumn<DetalleReporteDeComprasProveedores, Integer> columnaNFactura;
    @FXML
    private TableColumn<DetalleReporteDeComprasProveedores, String> columnaFechaDeEmision;
    @FXML
    private TableColumn<DetalleReporteDeComprasProveedores, ?> columnaProductos;
    @FXML
    private TableColumn<DetalleReporteDeComprasProveedores, String> columnaCodigoProducto;
    @FXML
    private TableColumn<DetalleReporteDeComprasProveedores, String> columnaNombreProducto;
    @FXML
    private TableColumn<DetalleReporteDeComprasProveedores, Integer> columnaPrecioProducto;
    @FXML
    private TableColumn<DetalleReporteDeComprasProveedores, Integer> columnaCantidadProducto;
    @FXML
    private TableColumn<DetalleReporteDeComprasProveedores, Integer> columnaSubtotalProducto;
    
    private ObservableList<DetalleReporteDeComprasProveedores> datos = FXCollections.observableArrayList();
    
    private LocalDate fechaInicio;
    private LocalDate fechaTermino;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.tablaCompraProveedores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.columnaProveedor.setMaxWidth(20000);
        this.columnaNFactura.setMaxWidth(10000);
        this.columnaFechaDeEmision.setMaxWidth(12000);
        this.columnaCodigoProducto.setMaxWidth(10000);
        this.columnaNombreProducto.setMaxWidth(22000);
        this.columnaPrecioProducto.setMaxWidth(10000);
        this.columnaCantidadProducto.setMaxWidth(9000);
        this.columnaSubtotalProducto.setMaxWidth(10000);
        
        columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));
        columnaNFactura.setCellValueFactory(new PropertyValueFactory<>("numeroFactura"));
        columnaFechaDeEmision.setCellValueFactory(new PropertyValueFactory<>("fechaEmisionFactura"));
        columnaCodigoProducto.setCellValueFactory(new PropertyValueFactory<>("codigoProducto"));
        columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        columnaPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        columnaCantidadProducto.setCellValueFactory(new PropertyValueFactory<>("cantidadDeProductosComprados"));
        columnaSubtotalProducto.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }    
    
    public void llenarTablaCompra(){
        
        ConsultaReporte consulta = new ConsultaReporte();
        ReporteDeComprasProveedores reporte = consulta.obtenerReporteDeComprasAProveedores(
                this.fechaInicio, this.fechaTermino);
        ArrayList<DetalleReporteDeComprasProveedores> detallesReporte = reporte.obtenerDetallesDeReporte();
        for (DetalleReporteDeComprasProveedores detalle : detallesReporte) {
            datos.add(detalle);
        }
        this.tablaCompraProveedores.setItems(datos);
    }
    
    
    public void modificarFechas(LocalDate fechaInicio, LocalDate fechaTermino){
        
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        String fechaI = fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        String fechaT = fechaTermino.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        this.etiquetaFechaInicio.setText(fechaI);
        this.etiquetaFechaTermino.setText(fechaT);
    }
    
    private void warning(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(text1);
        alert.setContentText(texto2);
        alert.showAndWait();
    }
}
