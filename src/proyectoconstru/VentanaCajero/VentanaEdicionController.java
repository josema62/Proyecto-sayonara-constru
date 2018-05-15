/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.VentanaCajero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelodedatos.DetalleProducto;

/**
 * FXML Controller class
 *
 * @author Unknown
 */
public class VentanaEdicionController implements Initializable {

    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Label etiquetaNombreProducto;
    private DetalleProducto producto;
    private InterfazcajeroController padre;
    @FXML
    private TextField campoDeTextoCantidad;
    @FXML
    private Button botonEditar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.etiquetaNombreProducto.setWrapText(true);
    }    

    public void recibirParametros(DetalleProducto producto, InterfazcajeroController padre){
        this.padre = padre;
        this.producto = producto;
        this.etiquetaNombreProducto.setText(producto.getNombre());
        this.campoDeTextoCantidad.setText(producto.getCantidad()+"");
    }
    
    @FXML
    private void accionBotonCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void accionBotonEliminar(ActionEvent event) {
        this.padre.eliminarProductoLista(producto);
        Stage stage = (Stage) this.botonEliminar.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void accionBotonEditar(ActionEvent event) {
        //FALTA VERIFICAR QUE SE INGRESE UN NUMERO CORRECTO
        int cantidadNueva = Integer.parseInt(this.campoDeTextoCantidad.getText());
        int valorSubtotal = cantidadNueva * producto.getPrecioUnitario();
        this.padre.modificarFila(producto.getCodigoProducto(), cantidadNueva, valorSubtotal,true);
        Stage stage = (Stage) this.botonEditar.getScene().getWindow();
        stage.close();
    }
    
}
