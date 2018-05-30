/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import modelodedatos.ValidacionCampo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import modelodedatos.ValidacionRut;
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
    
    private ValidacionCampo validacion = new ValidacionCampo();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campoTextoRut.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoRut.getText().length() == 12){
                event.consume();
            }

            }});
        campoTextoNombre.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoNombre.getText().length() == 30){
                event.consume();
            }

            }});
        campoTextoRazonSocial.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoRazonSocial.getText().length() == 40){
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
        campoTextoTelefono.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoTelefono.getText().length() == 12){
                event.consume();
            }

            }});
        campoTextoCorreoOpcional.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoCorreoOpcional.getText().length() == 40){
                event.consume();
            }

            }});
        campoTextoTelefonoOpcional.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoTelefonoOpcional.getText().length() == 12){
                event.consume();
            }

            }});
    }   
    
    /**
     * agrega el proveedor a la base de datos gracias a la consulta 
     * de registrarProveedor.
     * @param event 
     */
    @FXML
    private void agregarProveedor(ActionEvent event){
        if (verificaCampos()) {
            if(!consulta.existeProveedor(campoTextoRut.getText())){
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
            else
                warning("Este proveedor ya existe!", "Por favor, ingrese un proveedor valido!");
        }
        
        
    }
    
    private boolean verificaCampos(){
        boolean verifica = true;
        String mensaje = "";
        if(validacion.campoVacio(campoTextoRut.getText())){
            campoTextoRut.setPromptText("Inserte un RUT valido");
            verifica = false; 
        }
        else if(!campoTextoRut.getText().isEmpty()){
            ValidacionRut validacionRut = new ValidacionRut();
            if(!validacionRut.validaRut(campoTextoRut.getText())){
                mensaje+=" Rut Invalido -";
                verifica = false;
            }
        }
        if(validacion.campoVacio(campoTextoNombre.getText())){
            campoTextoNombre.setPromptText("Inserte un nombre valido");
            verifica = false; 
        }
        if(validacion.campoVacio(campoTextoRazonSocial.getText())){
            campoTextoRazonSocial.setPromptText("Inserte una razon social valida");
            verifica = false; 
        }
        if(!validacion.campoVacio(campoTextoCorreoOpcional.getText())){
            if(!validacion.isEmail(campoTextoCorreoOpcional.getText())){
                mensaje+=" Correo Invalido -";
                verifica = false;
            }
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
        if(!validacion.campoVacio(campoTextoTelefonoOpcional.getText())){
            if(!validacion.isCadenaNumeros(campoTextoTelefonoOpcional.getText())){
                mensaje+=" Telefono Opcional Invalido -";
                verifica = false;
            }
            else if(!validacion.verificaCantidadNumeros(campoTextoTelefonoOpcional.getText())){
                mensaje+=" Telefono Opcional Invalido(Requerido 9 a 12 digitos) -";
                verifica = false;
            }
        }
        if (!verifica) {
            warning("Algunos campos invalidos", mensaje);
        }
        return verifica;
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
        campoTextoRut.setPromptText("");
        campoTextoNombre.setPromptText("");
        campoTextoCorreoOpcional.setPromptText("");
        campoTextoRazonSocial.setPromptText("");
        campoTextoDireccion.setPromptText("");
        campoTextoTelefono.setPromptText("");
        campoTextoTelefonoOpcional.setPromptText("");
    }
    
    /**
     * elimina todos los datos escritos en la ventana agregarProveedor
     * @param event 
     */
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
