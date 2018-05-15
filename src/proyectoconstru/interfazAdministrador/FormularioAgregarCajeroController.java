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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import proyectoconstru.conexion.ConsultaCajero;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class FormularioAgregarCajeroController implements Initializable {

    @FXML
    private TextField campoTextoRut;
    @FXML
    private TextField campoTextoNombre;
    @FXML
    private PasswordField campoContrasena;
    @FXML
    private Button botonAgregar;
    @FXML
    private TextField campoTextoTelefono;
    @FXML
    private TextField campoTextoDireccion;
    @FXML
    private Button botonCancelar;
    private ConsultaCajero consulta = new ConsultaCajero();
    
    //private ConsultaCajero conexionbd = new ConsultaCajero();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * metodo que ocupa el boton agregar de la ventana cajero, 
     * luego de verificar los campos agrega a la bd el nuevo cajero.
     * @param event 
     */
    @FXML
    private void agregarCajero(ActionEvent event){
        consulta.insertarCajero(campoTextoRut.getText(), 
                campoTextoNombre.getText(),
                campoContrasena.getText(),
                campoTextoTelefono.getText(), 
                campoTextoDireccion.getText(),
                true);
        
        
        limpiarCamposdeTexto();
    }
    /**
     * limpia los campos de texto luego de que hayan sido agregados
     * en la ventana de agregar cajero.
     */
    private void limpiarCamposdeTexto() {
        campoTextoRut.clear();
        campoTextoNombre.clear();
        campoContrasena.clear();
        campoTextoTelefono.clear();
        campoTextoDireccion.clear();
    }
    
   @FXML
    private void botonCancelar(ActionEvent event) {
        limpiarCamposdeTexto();
    }
    
}
