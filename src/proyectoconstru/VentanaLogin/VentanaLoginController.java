
package proyectoconstru.VentanaLogin;

import java.awt.Dialog;
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
import proyectoconstru.conexion.ConsultaAutentificacion;
import proyectoconstru.conexion.ConsultaCajero;

/**
 * Controlador de la ventana de Login
 *
 * @author Jose Nunnez
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
    
    private ConsultaAutentificacion consulta = new ConsultaAutentificacion();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    
    /**
     * Accion del botonAccceder.
     * Aqui se realiza la comprobacion de los datos ingresados en los campos.
     * Posterior a la verificación, se abre la ventana correspondiente al usuario
     * que quire ingresar.
     * @param event 
     */
    @FXML
    private void accionBotonAcceder(ActionEvent event) {
        String rut = this.campoDeTextoRUT.getText();
        //AQUI FALTARIA CORROBORAR QUE ES UN INGRESO CORRECTO
        String contraseniaVentana = this.campoDeTextoContrasenia.getText();

        if (rut.isEmpty()) {
            this.mostrarAlerta("El campo RUN está vacío","Por favor, ingrese el RUN");
        }

        else if (contraseniaVentana.isEmpty()) {
            this.mostrarAlerta("El campo contraseña está vacío",
                         "Por favor, ingrese la contraseña");
        }
        else {
            if (this.selectorAdmin.isSelected()) {
                //se obtiene la contrasenia desde la base de datos
               this.contraseniaCorrecta = this.consulta.obtenerContaseniaAdministrador(
                       rut);
               //Se muestra en pantalla si el rut no es valido
                if (this.contraseniaCorrecta == null) {
                    this.mostrarAlerta("El RUN ingresado NO esta registrado",
                                 "Por favor, ingrese un RUN válido");
                }

                else if (this.contraseniaCorrecta.equals(contraseniaVentana)) {
                    //Si la contrasenia es correta, se abre la ventana del administrador
                    try {
                       //ABRIR VENTANA ADMIN    
                       Stage stageVentanaAdmin = new Stage();
                       Parent root = FXMLLoader.load(getClass().getResource("/proyectoconstru/interfazAdministrador/VentanaAdministrador.fxml"));
                       Scene scene = new Scene(root);
                       stageVentanaAdmin.setScene(scene);
                       
                       Stage stageActual = (Stage) this.botonAcceder.getScene().getWindow();
                       stageActual.close();
                       stageVentanaAdmin.setTitle("Ventana Administrador");
                       stageVentanaAdmin.show();
                    }
                   catch (IOException ex) {
                       Logger.getLogger(VentanaLoginController.class.getName()).log(Level.SEVERE,
                                                                                    null,
                                                                                    ex);
                   }
                     
                }
                else {
                    this.mostrarAlerta("La contraseña ingresada es incorrecta",
                                 "Por favor, ingrese la contraseña correcta");
                }
            }
            else if (this.selectorCajero.isSelected()) {
               this.contraseniaCorrecta = this.consulta.obtenerContraseniaCajero(
                       rut);

                if (this.contraseniaCorrecta == null) {
                    this.mostrarAlerta("El RUN ingresado NO esta registrado",
                                 "Por favor, ingrese un RUN válido");
                }

                else if (this.contraseniaCorrecta.equals(contraseniaVentana)) {
                    //Si la contrasenia es correcta, se abre la ventana cajero
                    try {
                        //ABRIR VENTANA CAJERO
                        Stage stageVentanaCajero = new Stage();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                "/proyectoconstru/VentanaCajero/interfazcajero.fxml"));
                        
                        Parent root = loader.load();
                        
                        InterfazcajeroController in = loader.<InterfazcajeroController>getController();
                        ConsultaCajero consultaCajero = new ConsultaCajero();
                        
                        in.setearNombreCajero(consultaCajero.buscarCajero(rut).getNombre());
                        in.setRutCajero(rut);
                        
                        Scene scene = new Scene(root);
                        stageVentanaCajero.setScene(scene);

                        Stage stageActual = (Stage) this.botonAcceder.getScene().getWindow();
                        stageActual.close();

                        stageVentanaCajero.setTitle("Ventana Cajero");
                        stageVentanaCajero.show();
                    }
                    catch (IOException ex) {

                    }
                }
                else {
                    this.mostrarAlerta("La contraseña ingresada es incorrecta",
                                 "Por favor, ingrese la contraseña correcta");
                }
            }
            else {
                this.mostrarAlerta("Debe seleccionar el tipo de usuario!",
                             "Seleccione al usuario correspondiente");
            }
        }
    }

    /**
     * Accion del boton cancelar, que cierra la ventana.
     * @param event 
     */
    @FXML
    private void accionBotonCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Se encarga de deshabilitar la opcion cajero si se selecciona a administrador
     * @param event 
     */
    @FXML
    private void accionSelectorAdmin(ActionEvent event) {
        this.selectorCajero.setSelected(false);
    }

    /**
     * Se encarga de deshabilitar la opcion administrador si se selecciona a cajero
     * @param event 
     */
    @FXML
    private void accionSelectorCajero(ActionEvent event) {
        this.selectorAdmin.setSelected(false);
    }

    /**
     * Se encarga de mostrar un mensaje en pantalla
     * @param text1 titulo del mensaje
     * @param texto2 cuerpo del mensaje
     */
    private void mostrarAlerta(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(text1);
        alert.setTitle("Error en Ingreso");
        alert.setContentText(texto2);
        alert.showAndWait();
    }

}
