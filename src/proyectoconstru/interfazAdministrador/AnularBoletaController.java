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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectoconstru.conexion.ConsultaBoleta;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class AnularBoletaController implements Initializable {

    @FXML
    private TextField campoTextoCodigoBoleta;
    @FXML
    private Button botonAnularBoleta;
    @FXML
    private Button botonCancelar;

    private ConsultaBoleta consulta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void anularBoleta(ActionEvent event) {
        if(consulta.existeBoleta(Integer.parseInt(campoTextoCodigoBoleta.getText()))){
            consulta.darDeBajaBoleta(Integer.parseInt(campoTextoCodigoBoleta.getText()));
            warning("Boleta anulada", "boleta Anulada exitosamente!");
            Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
            stage.close();
        }
        else
            warning("Codigo de boleta incorrecto!", "Por favor, ingrese un numero de boleta valido!");
       
    }

    @FXML
    private void botonCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
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
