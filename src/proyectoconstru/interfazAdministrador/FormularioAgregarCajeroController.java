/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import modelodedatos.ValidacionCampo;
import java.awt.TrayIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import modelodedatos.ValidacionRut;
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
    private ValidacionCampo validacion = new ValidacionCampo();
    
    //private ConsultaCajero conexionbd = new ConsultaCajero();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campoTextoNombre.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoNombre.getText().length() == 50){
                event.consume();
            }

            }});
        campoTextoRut.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoRut.getText().length() == 12){
                event.consume();
            }

            }});
        campoTextoDireccion.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoDireccion.getText().length() == 30){
                event.consume();
            }

            }});
        campoContrasena.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoContrasena.getText().length() == 12){
                event.consume();
            }

            }});
        campoTextoTelefono.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoTelefono.getText().length() == 12){
                event.consume();
            }

            }});
    }    
    
    /**
     * metodo que ocupa el boton agregar de la ventana cajero, 
     * luego de verificar los campos agrega a la bd el nuevo cajero.
     * @param event 
     */
    @FXML
    private void agregarCajero(ActionEvent event){
        if(verificaCampos()){
            consulta.insertarCajero(campoTextoRut.getText(), 
                campoTextoNombre.getText(),
                campoContrasena.getText(),
                campoTextoTelefono.getText(), 
                campoTextoDireccion.getText(),
                true);    
            limpiarCamposdeTexto();
        }     
    }
    private boolean verificaCampos(){
        boolean verifica = true;
        String mensaje = "";
        if(validacion.campoVacio(campoTextoNombre.getText())){
            campoTextoNombre.setPromptText("Inserte un nombre valido");
            verifica = false; 
        }
        else if(!validacion.isAlpha(campoTextoNombre.getText())){
            mensaje+=" Nombre Invalido -";
            verifica = false;
        }
        if(validacion.campoVacio(campoTextoRut.getText())){
            campoTextoRut.setPromptText("Inserte un rut valido");
            verifica = false; 
        }
        else if(!campoTextoRut.getText().isEmpty()){
            ValidacionRut validacionRut = new ValidacionRut();
            if(!validacionRut.validaRut(campoTextoRut.getText())){
                mensaje+=" Rut Invalido -";
                verifica = false;
            }
        }
        
        if(validacion.campoVacio(campoContrasena.getText())){
            campoContrasena.setPromptText("Inserte una contraseña valida");
            verifica = false; 
        }
        else if(!validacion.isNumeros(campoContrasena.getText())){
            mensaje+=" Contraseña Invalida -";
            verifica = false;
        }
        else if(!validacion.verificaContrasena(campoContrasena.getText())){
            mensaje+=" Contraseña Invalida(Requerido 4 a 10 digitos) -";
            verifica = false;
        }
        if(validacion.campoVacio(campoTextoDireccion.getText())){
            campoTextoDireccion.setPromptText("Inserte una direccion valida");
            verifica = false; 
        }
        if(validacion.campoVacio(campoTextoTelefono.getText())){
            campoTextoTelefono.setPromptText("Inserte un telefono valido");
            verifica = false; 
        }
        else if(!validacion.isCadenaNumeros(campoTextoTelefono.getText())){
            mensaje+=" Telefono Invalido -";
            verifica = false;
        }
        else if(!validacion.verificaCantidadNumeros(campoTextoTelefono.getText())){
            mensaje+=" Telefono Invalido(Requerido 9 a 12 digitos) -";
            verifica = false;
        }    
        if (!verifica) {
             warning("Algunos campos invalidos", mensaje);
        }
       
        return verifica;
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
        campoTextoRut.setPromptText("");
        campoTextoNombre.setPromptText("");
        campoContrasena.setPromptText("");
        campoTextoTelefono.setPromptText("");
        campoTextoDireccion.setPromptText("");
    }
    
   @FXML
    private void botonCancelar(ActionEvent event) {
        limpiarCamposdeTexto();
    }
    private void warning(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(text1);
        alert.setTitle("ERROR");
        alert.setContentText(texto2);
        alert.showAndWait();
    }
}
