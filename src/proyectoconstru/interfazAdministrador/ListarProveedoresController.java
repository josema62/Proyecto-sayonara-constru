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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class ListarProveedoresController implements Initializable {

    @FXML
    private TableView<?> tablaProveedor;
    @FXML
    private TableColumn<?, ?> columnaRut;
    @FXML
    private TableColumn<?, ?> columnaRazonSocial;
    @FXML
    private TableColumn<?, ?> columnaNombre;
    @FXML
    private TableColumn<?, ?> columnaDireccion;
    @FXML
    private TableColumn<?, ?> columnaTelefono;
    @FXML
    private TableColumn<?, ?> columnaTelefonoOpcional;
    @FXML
    private TableColumn<?, ?> columnaCorreo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}