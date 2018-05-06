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
    private Button botonAgregarProductos;
    @FXML
    private Button botonAgregar;
    @FXML
    private TextField campoTextoMontoNeto;
    @FXML
    private TextField campoTextoIVA;
    @FXML
    private TextField campoTextoTotal;
    @FXML
    private Button botonCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
