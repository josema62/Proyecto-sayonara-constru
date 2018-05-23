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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class CompraProveedoresController implements Initializable {

    @FXML
    private DatePicker datePickerInicio;
    @FXML
    private DatePicker datePickerTermino;
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
        datePickerInicio.setDayCellFactory(celdaDia);
        datePickerTermino.setDisable(true);
    }    
    
    public void recibirControlador(VentanaAdministradorController controlador){
        this.controlador = controlador;
    }

    @FXML
    private void accionGenerarReporte(ActionEvent event) {
        LocalDate fechaInicio = this.datePickerInicio.getValue();
        LocalDate fechaTermino = this.datePickerTermino.getValue();
        if(fechaInicio==null || fechaTermino==null){
           
            this.MostrarMensajeAlerta("ERROR DE INGRESO", "Por favor, ingrese una fecha");
        }
        else{
            Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
            stage.close();
            controlador.modificarPaneDinamicoCompra("ReporteCompraProveedores.fxml", 
                                                    fechaInicio, fechaTermino);
        }
    }

    @FXML
    private void accionBotonCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void accionFecha(ActionEvent event) {
        this.datePickerTermino.setDisable(false);
        LocalDate value = this.datePickerInicio.getValue();
        
        Callback<DatePicker,DateCell> celdaDia = new Callback<DatePicker, DateCell>()
        {
            public DateCell call(final DatePicker datePicker){
            return new DateCell(){
                @Override
                public void updateItem(LocalDate item, boolean empty){
                    super.updateItem(item, empty);
                    if(item.isBefore(value)){
                        this.setDisable(true);
                    }
                    if(item.isAfter(LocalDate.now())){
                        this.setDisable(true);
                    }
                    }
                };
            }
        };
        this.datePickerTermino.setDayCellFactory(celdaDia);
    }

    private void MostrarMensajeAlerta(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(text1);
        alert.setContentText(texto2);
        alert.showAndWait();
        
    }
}
