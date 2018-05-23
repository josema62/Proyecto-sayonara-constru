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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        System.out.println("editado");
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
}
