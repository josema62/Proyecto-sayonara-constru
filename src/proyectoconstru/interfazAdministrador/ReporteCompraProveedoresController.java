
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
 * Controlador de la ventana CompraProveedores
 *
 * @author Jose Nunnez
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
     * Se inicializan las columnas de la tabla, tanto en tamanno como en los valores que
     * van a tomar
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
    
    /**
     * Se encarga de llenar la tabla con los datos provenientes de la consulta
     * a la base de datos
     */
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
    
    /**
     * Se modifican las etiquetas de las fechas de inicio y termino 
     * @param fechaInicio
     * @param fechaTermino 
     */
    public void modificarFechas(LocalDate fechaInicio, LocalDate fechaTermino){
        
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        String fechaI = fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        String fechaT = fechaTermino.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        this.etiquetaFechaInicio.setText(fechaI);
        this.etiquetaFechaTermino.setText(fechaT);
    }
    
}
