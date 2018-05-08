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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class FormularioAgregarFacturaController implements Initializable {

    @FXML
    private TextField campoTextoNumeroFactura;
    @FXML
    private DatePicker datePickerFechaEmision;
    @FXML
    private ComboBox<?> comboBoxProveedor;
    @FXML
    private TextField campoTextoMontoNeto;
    @FXML
    private TextField campoTextoIVA;
    @FXML
    private TextField campoTextoTotal;
    @FXML
    private Button botonAgregarProducto;
    @FXML
    private Button botonEditarProducto;
    @FXML
    private TableView<?> tablaProductosFactura;
    @FXML
    private TableColumn<?, ?> columnaCodigoProducto;
    @FXML
    private TableColumn<?, ?> columnaNombreProducto;
    @FXML
    private TableColumn<?, ?> columnaPrecioUnitarioProducto;
    @FXML
    private TableColumn<?, ?> columnaCantidad;
    @FXML
    private TableColumn<?, ?> columnaSubtotal;
    @FXML
    private Button botonAgregarFactura;
    @FXML
    private Button botonCancelarFactura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
