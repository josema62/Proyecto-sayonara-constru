/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import proyectoconstru.conexion.ConsultaProveedor;

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
    
    private ConsultaProveedor consulta = new ConsultaProveedor();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    /**
     * agrega el proveedor a la base de datos gracias a la consulta 
     * de registrarProveedor.
     * @param event 
     */
    @FXML
    private void agregarProducto(ActionEvent event){
        consulta.registrarProveedor(campoTextoRut.getText(), 
                campoTextoNombre.getText(),
                campoTextoCorreoOpcional.getText(),
                campoTextoRazonSocial.getText(),
                campoTextoDireccion.getText(),
                campoTextoTelefono.getText(), 
                campoTextoTelefonoOpcional.getText(),
                true);            
        limpiarCamposdeTexto();
    }
    
    /**
     * limpia los campos de texto de la interfaz agregar Proveedor
     */
    private void limpiarCamposdeTexto() {
        campoTextoRut.clear();
        campoTextoNombre.clear();
        campoTextoCorreoOpcional.clear();
        campoTextoRazonSocial.clear();
        campoTextoDireccion.clear();
        campoTextoTelefono.clear();
        campoTextoTelefonoOpcional.clear();
    }
    
    /**
     * elimina todos los datos escritos en la ventana agregarProveedor
     * @param event 
     */
    @FXML
    private void botonCancelar(ActionEvent event) {
        limpiarCamposdeTexto();
    }
    
}
