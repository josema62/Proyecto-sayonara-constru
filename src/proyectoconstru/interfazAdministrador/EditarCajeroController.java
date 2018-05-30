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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelodedatos.Cajero;
import modelodedatos.Proveedor;
import proyectoconstru.conexion.ConsultaCajero;
import proyectoconstru.conexion.ConsultaProveedor;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class EditarCajeroController implements Initializable {

    @FXML
    private Button botonHabilitar;
    @FXML
    private TextField campoTextoRut;
    @FXML
    private TextField campoTextoNombre;
    @FXML
    private PasswordField campoContrasena;
    @FXML
    private Button botonDarDeBaja;
    @FXML
    private Button botonEditar;
    @FXML
    private TextField campoTextoTelefono;
    @FXML
    private TextField campoTextoDireccion;
    @FXML
    private Button botonCancelar;

    private Cajero cajero;
    
     private ConsultaCajero consulta=new ConsultaCajero();
     
     private ValidacionCampo validacion = new ValidacionCampo();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campoTextoNombre.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoNombre.getText().length() == 40){
                event.consume();
            }

            }});
        campoTextoDireccion.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoNombre.getText().length() == 40){
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

    public void cargarCajero(Cajero ca){
        cajero = ca;
        campoTextoRut.setText(cajero.getRut());
        campoTextoDireccion.setText(cajero.getDireccion());
        campoTextoNombre.setText(cajero.getNombre());
        campoTextoTelefono.setText(cajero.getTelefono());
        campoContrasena.setText(cajero.getContrasenia());     
        if (cajero.getEstado())
            botonHabilitar.setDisable(true);
        else
            botonDarDeBaja.setDisable(true);
    }
/**
 * Le da funcionalidad al boton Editar de la ventana Editar Cajero.
 * Cuando se presiona se modifican los datos del cajero.
 * @param event 
 */
    @FXML
    private void botonEditarCajero(ActionEvent event) {
        if (verificaCampos()) {
            try{
                consulta.modificarCajero(campoTextoRut.getText(),
                                         campoTextoNombre.getText(),
                                         campoContrasena.getText(),
                                         campoTextoTelefono.getText(),
                                         campoTextoDireccion.getText(),
                                         cajero.getEstado()
                                          );

                Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
                stage.close();
            }catch (Exception ex) {
                System.out.println("error boton EditarProveedor:"+ex);
            }
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
            mensaje+=" Telefono Invalido(Requerido 12 digitos) -";
            verifica = false;
        }
        if (!verifica) {
            warning("Algunos campos invalidos", mensaje);
        }
        return verifica;
    }
    /**
     * Le da funcionalidad al boton Cancelar
     * cerrando la ventana.
     * @param event 
     */
    @FXML
    private void botonCancelar(ActionEvent event) {
        System.out.println("cancelado");
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }
    
    
    /**
     * Le da funcionalidad al boton dar de baja.
     * cambia el estado de un cajero a deshabilitado.
     * @param event 
     */
    @FXML
    private void botonDarBaja(ActionEvent event) {
       try {
           cajero.setEstado(false);
           botonDarDeBaja.setDisable(true);
           botonHabilitar.setDisable(false);
       }catch (Exception ex) {
           System.out.println("error en dar de baja:"+ex);
        }
    }
    /**
     * Habilita el cajero a editar.
     * @param event 
     */
    @FXML
    private void botonHabilitarCajero(ActionEvent event){
        try{
            cajero.setEstado(true);
            botonHabilitar.setDisable(true);
            botonDarDeBaja.setDisable(false);
        }catch (Exception ex) {
            System.out.println("Error en habilitar proveedor:"+ex);
        }
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
