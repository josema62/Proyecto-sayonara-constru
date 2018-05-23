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

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class EditarProductoEnFacturaController implements Initializable {

    @FXML
    private TextField campoTextoCodigo;
    @FXML
    private TextField botonNombre;
    @FXML
    private Spinner<Integer> spinnerCantidad;
    @FXML
    private Button botonAceptar;
    @FXML
    private Button botonCancelar;

    private FormularioAgregarFacturaController formulario;
    
    private DetalleProducto producto;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    void obtenerControlador(FormularioAgregarFacturaController aThis) {
        formulario = aThis;
    }

    void cargarProducto(DetalleProducto pr) {
        producto = pr;
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, producto.getCantidad());
 
        spinnerCantidad.setValueFactory(valueFactory);
        campoTextoCodigo.setText(producto.getCodigoProducto());
        botonNombre.setText(producto.getNombre());
        
    }

    @FXML
    private void aceptarEditar(ActionEvent event) {
        System.out.println("editado");
        try{
            producto.setCantidad(spinnerCantidad.getValue());
            formulario.agregarCambioEnListaProducto(producto);
            Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
            stage.close();
        }catch (Exception ex) {
            System.out.println("error boton aceptar edit en factura:"+ex);
        }
    }

    @FXML
    private void botonCancelar(ActionEvent event) {
    }
    
}
