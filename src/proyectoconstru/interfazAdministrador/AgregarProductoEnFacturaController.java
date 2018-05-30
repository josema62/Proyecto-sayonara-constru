/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelodedatos.DetalleProducto;
import modelodedatos.Producto;
import proyectoconstru.conexion.ConsultaProducto;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class AgregarProductoEnFacturaController implements Initializable {

    @FXML
    private TextField campoTextoCodigo;
    @FXML
    private Spinner<Integer> spinnerCantidad;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonCancelar;
    
    private ConsultaProducto consulta = new ConsultaProducto();
    
    FormularioAgregarFacturaController formularioFactura;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
 
        spinnerCantidad.setValueFactory(valueFactory);
        campoTextoCodigo.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoCodigo.getText().length() == 12){
                event.consume();
            }

            }});
    }    

    void obtenerControlador(FormularioAgregarFacturaController aThis) {
        formularioFactura = aThis;
    }

    @FXML
    private void agregarProducto(ActionEvent event) { 
        try{
           
            Producto producto = consulta.obtenerDatosProducto(campoTextoCodigo.getText());
            if(producto.getEstado()){
                System.out.println("form:"+formularioFactura.getClass());
                System.out.println("spinner:"+spinnerCantidad.getValue());
                formularioFactura.agregarEnListaProductos(
                        new DetalleProducto(producto.getCodigo(), 
                                producto.getNombre(), 
                                spinnerCantidad.getValue(), 
                                producto.getPrecio(), 
                                producto.getPrecio() * spinnerCantidad.getValue()));
                Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
                stage.close();
            }else
                warning("Error de ingreso!", "Producto deshabilitado!");
            
        }catch (Exception ex) {
            warning("Error de ingreso!", "Codigo no valido");
        }
        
    }
    @FXML
    private void cancelar(ActionEvent event) {
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
