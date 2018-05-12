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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class FormularioAgregarProveedorController implements Initializable {

    @FXML
    private TextField campoTextoRut;
    @FXML
    private TextField campoTextoRazonSocial;
    @FXML
    private TextField campoTextoNombre;
    @FXML
    private TextField campoTextoDireccion;
    @FXML
    private Button botonAgregar;
    @FXML
    private TextField campoTextoTelefono;
    @FXML
    private TextField campoTextoTelefonoOpcional;
    @FXML
    private TextField campoTextoCorreoOpcional;
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
