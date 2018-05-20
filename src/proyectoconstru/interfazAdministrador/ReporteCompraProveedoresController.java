/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelodedatos.DetalleReporteDeComprasProveedores;


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
    private TableColumn<?, ?> columnaProveedor;
    @FXML
    private TableColumn<?, ?> columnaNFactura;
    @FXML
    private TableColumn<?, ?> columnaFechaDeEmision;
    @FXML
    private TableColumn<?, ?> columnaProductos;
    @FXML
    private TableView<?> tablaCompraProveedores;
    
    private ObservableList<DetalleReporteDeComprasProveedores> datos = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.tablaCompraProveedores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        columnaProveedor.setMaxWidth(40000);
        columnaNFactura.setMaxWidth(20000);
        columnaFechaDeEmision.setMaxWidth(20000);
        columnaProductos.setMaxWidth(15000);
        
    }    
    
    public void modificarFechas(String fInicio, String fTermino){
      this.etiquetaFechaInicio.setText(fInicio);
      this.etiquetaFechaTermino.setText(fTermino);
    }
    
}
