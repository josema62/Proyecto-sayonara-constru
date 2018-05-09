/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.VentanaLogin;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import proyectoconstru.VentanaCajero.InterfazcajeroController;

/**
 * FXML Controller class
 *
 * @author Unknown
 */
public class VentanaLoginController implements Initializable {
    private String contraseniaCorrecta;
    @FXML
    private Button botonAcceder;
    @FXML
    private Button botonCancelar;
    @FXML
    private RadioButton selectorAdmin;
    @FXML
    private RadioButton selectorCajero;
    @FXML
    private Font x1;
    @FXML
    private TextField campoDeTextoRUT;
    @FXML
    private TextField campoDeTextoContrasenia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accionBotonAcceder(ActionEvent event) {
        String rut = this.campoDeTextoRUT.getText();
        //AQUI FALTARIA CORROBORAR QUE ES UN INGRESO CORRECTO
        String contraseniaVentana = this.campoDeTextoContrasenia.getText();
        
        //Es solo para probar
        this.contraseniaCorrecta ="123";
        
        if(rut.isEmpty())
            this.warning("El campo RUN está vacío");
        
        else if (contraseniaVentana.isEmpty())
            this.warning("El campo contraseña está vacío");
        else{
            if(this.selectorAdmin.isSelected()){
                //OBTENER CONTRASEÑA DEL ADMIN VIA CONSULTAAUTENTICACION
                
                //this.contraseniaCorrecta = obtenerContraseniaAdmin(rut);
                
                if(this.contraseniaCorrecta ==null) this.warning("El RUN ingresado NO esta registrado");
                
                else if(this.contraseniaCorrecta.equals(contraseniaVentana)){
                    //ABRIR VENTANA ADMIN
                    /*
                    Stage stageVentanaAdmin = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/proyectoconstru/VentanaAdministrador/VentanaAdministrador.fxml"));
                        Scene scene = new Scene(root);
                        stageVentanaAdmin.setScene(scene);
                        
                        Stage stageActual = (Stage) this.botonAcceder.getScene().getWindow();
                        stageActual.close();
                        
                        stageVentanaAdmin.show();
                        */
                }
                else{
                    this.warning("La contraseña ingresada es incorrecta");
                }
            }
            else if (this.selectorCajero.isSelected()){
                //this.contraseniaCorrecta = obtenerContraseniaCajero(rut);
                
                if(this.contraseniaCorrecta ==null) this.warning("El RUN ingresado NO esta registrado");
                
                else if(this.contraseniaCorrecta.equals(contraseniaVentana)){
                   try {
                        //ABRIR VENTANA CAJERO
                        
                        Stage stageVentanaCajero = new Stage();
                
                        Parent root = FXMLLoader.load(getClass().getResource("/proyectoconstru/VentanaCajero/interfazcajero.fxml"));
                        Scene scene = new Scene(root);
                        stageVentanaCajero.setScene(scene);
                        
                        Stage stageActual = (Stage) this.botonAcceder.getScene().getWindow();
                        stageActual.close();
                        
                        stageVentanaCajero.show();  
                   }
                   catch (IOException ex) {
                       
                   }
                }
                else{
                    this.warning("La contraseña ingresada es incorrecta");
                }
            }
            else{
                this.warning("Debe seleccionar el tipo de usuario!");
            }
        }
    }

    @FXML
    private void accionBotonCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void accionSelectorAdmin(ActionEvent event) {
        this.selectorCajero.setSelected(false);
    }

    @FXML
    private void accionSelectorCajero(ActionEvent event) {
        this.selectorAdmin.setSelected(false);
    }
    
     private void warning(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error en Ingreso");
        alert.setContentText(text);
        alert.showAndWait();
    }
     
     
}
