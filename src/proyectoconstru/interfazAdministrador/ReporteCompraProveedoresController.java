/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void modificarFechas(String fInicio, String fTermino){
      this.etiquetaFechaInicio.setText(fInicio);
      this.etiquetaFechaTermino.setText(fTermino);
    }
    
}
