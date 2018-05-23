/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class VentaDiariaController implements Initializable {

    @FXML
    private DatePicker datePickerFecha;
    @FXML
    private Button botonGenerarReporte;
    @FXML
    private Button botonCancelar;
    
    private VentanaAdministradorController controlador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Aqui se editan las celdas futuras, para evitar problemas
        Callback<DatePicker,DateCell> celdaDia = new Callback<DatePicker, DateCell>()
        {
            public DateCell call(final DatePicker datePicker){
            return new DateCell(){
                @Override
                public void updateItem(LocalDate item, boolean empty){
                    super.updateItem(item, empty);
                    if(item.isAfter(LocalDate.now())){
                        this.setDisable(true);
                    }
                    }
                };
            }
        };
        //se actualiza el pickerDate con las celdas deshabilitadas
        datePickerFecha.setDayCellFactory(celdaDia);
    }    
    
     @FXML
    private void accionBotonGenerarReporte(ActionEvent event) {
        LocalDate date = this.datePickerFecha.getValue();
        if(date==null){
            this.MostrarMensajeAlerta("ERROR DE INGRESO", "Por favor, ingrese una fecha");
        }
        else{
            
            Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
            stage.close();
            controlador.modificarPaneDinamicoVentas("ReporteDiario.fxml",date);
        }
    }

    @FXML
    private void accionBotonCancelar(ActionEvent event) {
         Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }
    
    public void recibirControlador(VentanaAdministradorController controlador){
        this.controlador = controlador;
    }
    private void MostrarMensajeAlerta(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(text1);
        alert.setContentText(texto2);
        alert.showAndWait();
    }
}
