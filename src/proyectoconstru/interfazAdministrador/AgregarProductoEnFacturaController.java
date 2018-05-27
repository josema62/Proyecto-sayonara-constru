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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
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
    }    

    void obtenerControlador(FormularioAgregarFacturaController aThis) {
        formularioFactura = aThis;
    }

    @FXML
    private void agregarProducto(ActionEvent event) { 
        try{
            Producto producto = consulta.obtenerDatosProducto(campoTextoCodigo.getText());
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
        }catch (Exception ex) {
            System.out.println("error en agregarProducto factura:"+ex);
        }
        
    }
    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }
    
}
