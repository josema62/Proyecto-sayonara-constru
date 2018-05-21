
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
 * Controlador de la ventana reporte Diario
 *
 * @author Jose Nunnez
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
     * Se inicializan las variables correspondientes a las columnas de la tabla
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
    /**
     * Se encarga de llenar la tabla con los datos que se reciben de la consulta
     * a la base de datos
     */
    public void llenarTabla(){
        ConsultaReporte consulta = new ConsultaReporte();
        ReporteDeVentas reporte = consulta.obtenerReporteDeVentas(this.fechaConsulta);
        ArrayList<DetalleReporteDeVentas> detallesReporte = reporte.obtenerDetallesDeReporte();
        
        for (DetalleReporteDeVentas detalleReporte : detallesReporte) {
            datos.add(detalleReporte);
        }
        this.tablaProductosVendidos.setItems(datos);
    }
    
    /**
     * Se modifica la etiqueta correspondiente a la fecha del reporte
     * @param fechaPicker 
     */
    public void modificarFecha(LocalDate fechaPicker){
        this.fechaConsulta = fechaPicker;
        String fecha = fechaPicker.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
        this.fechaVenta.setText(fecha);
    }
    
}
