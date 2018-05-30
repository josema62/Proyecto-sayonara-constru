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
import javafx.stage.Stage;
import modelodedatos.Proveedor;
import proyectoconstru.conexion.ConsultaProducto;
import proyectoconstru.conexion.ConsultaProveedor;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class EditarProveedorController implements Initializable {

    @FXML
    private Button botonHabilitar;
    @FXML
    private TextField campoTextoRut;
    @FXML
    private TextField campoTextoRazonSocial;
    @FXML
    private TextField campoTextoNombre;
    @FXML
    private TextField campoTextoDireccion;
    @FXML
    private Button botonDarDeBaja;
    @FXML
    private Button botonEditar;
    @FXML
    private TextField campoTextoTelefono;
    @FXML
    private TextField campoTextoTelefonoOpcional;
    @FXML
    private TextField campoTextoCorreoOpcional;
    @FXML
    private Button botonCancelar;
    
    private Proveedor proveedor;
    
     private ConsultaProveedor consulta=new ConsultaProveedor();
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
            if(  campoTextoDireccion.getText().length() == 40){
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
        campoTextoTelefonoOpcional.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoTelefonoOpcional.getText().length() == 12){
                event.consume();
            }

            }});
    }    

    public void cargarProveedor(Proveedor pr){
        proveedor = pr;
        campoTextoRut.setText(proveedor.getRut());
        campoTextoRazonSocial.setText(proveedor.getRazonSocial());
        campoTextoNombre.setText(proveedor.getNombre());
        campoTextoDireccion.setText(proveedor.getDireccion());
        campoTextoTelefono.setText(proveedor.getTelefonoObligatorio());
        campoTextoTelefonoOpcional.setText(proveedor.getTelefonoOpcional());
        campoTextoCorreoOpcional.setText(proveedor.getCorreo());
     
        if (proveedor.getEstado())
            botonHabilitar.setDisable(true);
        else
            botonDarDeBaja.setDisable(true);
    }
/**
 * Le da funcionalidad al boton Editar de la ventana Editar Proveedor.
 * Cuando se presiona se modifican los datos del proveedor.
 * @param event 
 */
    @FXML
    private void botonEditarProveedor(ActionEvent event) {
        if (verificaCampos()) {
             try{
                consulta.modificarDatosProveedor(campoTextoRut.getText(),
                                          campoTextoNombre.getText(),
                                          campoTextoCorreoOpcional.getText(),
                                          campoTextoDireccion.getText(),
                                          campoTextoTelefono.getText(),
                                          campoTextoTelefonoOpcional.getText(),
                                          proveedor.getEstado()
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
    
    private void warning(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(text1);
        alert.setTitle("ERROR");
        alert.setContentText(texto2);
        alert.showAndWait();
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
     * cambia el estado de un proveedor a deshabilitado.
     * @param event 
     */
    @FXML
    private void botonDarBaja(ActionEvent event) {
       try {
           proveedor.setEstado(false);
           botonDarDeBaja.setDisable(true);
           botonHabilitar.setDisable(false);
       }catch (Exception ex) {
           System.out.println("error en dar de baja:"+ex);
        }
    }
    /**
     * Habilita el proveedor a editar.
     * @param event 
     */
    @FXML
    private void botonHabilitarProveedor(ActionEvent event){
        try{
            proveedor.setEstado(true);
            botonHabilitar.setDisable(true);
            botonDarDeBaja.setDisable(false);
        }catch (Exception ex) {
            System.out.println("Error en habilitar proveedor:"+ex);
        }
    }
    
}
