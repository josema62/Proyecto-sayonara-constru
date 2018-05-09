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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class FormularioAgregarProductoController implements Initializable {

    @FXML
    private TextField campoTextoCodigo;
    @FXML
    private TextField campoTextoNombre;
    @FXML
    private ComboBox<?> comboBoxCategoria;
    @FXML
    private TextField campoTextoStockInicial;
    @FXML
    private TextField campoTextoStockMinimo;
    @FXML
    private TextField campoTextoPrecio;
    @FXML
    private Button botonAgregar;
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
