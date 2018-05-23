
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
 * Controlador de la ventana Edicion
 *
 * @author Jose Nunnez
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.etiquetaNombreProducto.setWrapText(true);
    }    

    /**
     * Se encarga de recibir los parametros que son ingresados desde la ventana 
     * del cajero
     * @param producto producto a editar
     * @param padre Controlador de la ventanaCajero
     */
    public void recibirParametros(DetalleProducto producto, InterfazcajeroController padre){
        this.padre = padre;
        this.producto = producto;
        this.etiquetaNombreProducto.setText(producto.getNombre());
        this.campoDeTextoCantidad.setText(producto.getCantidad()+"");
    }
    
    /**
     * Accion del boton cancelar
     * @param event 
     */
    @FXML
    private void accionBotonCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Se encarga de llamar al metodo del controlador de la ventana cajero
     * que elimina un producto
     * @param event 
     */
    @FXML
    private void accionBotonEliminar(ActionEvent event) {
        this.padre.eliminarProductoLista(producto);
        Stage stage = (Stage) this.botonEliminar.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Se encarga de ejecutar, segun el dato engresado en el campo, la cantidad
     * del producto.
     * @param event 
     */
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
