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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

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
        // TODO
    }    
    
    public void recibirControlador(VentanaAdministradorController controlador){
        this.controlador = controlador;
    }

    @FXML
    private void accionGenerarReporte(ActionEvent event) {
        LocalDate fechaInicio = this.datePickerInicio.getValue();
        LocalDate fechaTermino = this.datePickerTermino.getValue();
        
        String fechaI = fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        String fechaT = fechaTermino.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        
        controlador.modificarPaneDinamicoCompra("ReporteCompraProveedores.fxml", fechaI, fechaT);
    }

    @FXML
    private void accionBotonCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }
    
}
