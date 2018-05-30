
package proyectoconstru.VentanaCajero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelodedatos.DetalleProducto;
import modelodedatos.Producto;
import modelodedatos.ValidacionCampo;
import proyectoconstru.conexion.ConsultaProducto;

/**
 * Controlador de la ventana Edicion
 *
 * @author Jose Nunnez
 */
public class VentanaEdicionController implements Initializable {

    @FXML
    private Button botonCancelar;
    @FXML
    private Label etiquetaNombreProducto;
    @FXML
    private TextField campoDeTextoCantidad;
    @FXML
    private Button botonEditar;
    
    private DetalleProducto producto;
    private InterfazcajeroController padre;
    private ValidacionCampo validacion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.etiquetaNombreProducto.setWrapText(true);
        this.validacion = new ValidacionCampo();
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
     * Se encarga de ejecutar, segun el dato engresado en el campo, la cantidad
     * del producto.
     * @param event 
     */
    @FXML
    private void accionBotonEditar(ActionEvent event) {
        ConsultaProducto consulta = new ConsultaProducto();
        Producto productoReal = consulta.obtenerDatosProducto(producto.getCodigoProducto());
        String cantidadIngresada = this.campoDeTextoCantidad.getText();
        if(!validacion.isNumeros(cantidadIngresada)){
            this.mostrarMensajeAlerta("Error de ingreso", "Por favor, ingrese un valor correcto");
            return;
        }
        int cantidadNueva = Integer.parseInt(cantidadIngresada);
        if(cantidadNueva>0 && productoReal.getStockActual()>=cantidadNueva){
            int valorSubtotal = cantidadNueva * producto.getPrecioUnitario();
            this.padre.modificarFila(producto.getCodigoProducto(), cantidadNueva, valorSubtotal,true);
            Stage stage = (Stage) this.botonEditar.getScene().getWindow();
            stage.close();
        }
        else{
            if(cantidadNueva<=0){
                this.mostrarMensajeAlerta("Error en Ingreso", "La cantidad debe ser mayor a 0");
            }
            else{
                this.mostrarMensajeAlerta("Error en Ingreso", "No hay stock suficiente, solo hay "
                                          + productoReal.getStockActual()+" productos");
            }
        }
    }
    /**
     * Se encarga de mostrar un mensaje de alerta en pantalla
     * @param text1 Es el titulo del mensaje
     * @param texto2 Es el cuerpo del mensaje
     */
    private void mostrarMensajeAlerta(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setTitle(text1);
        alert.setHeaderText(null);
        alert.setContentText(texto2);
        alert.showAndWait();
    }
    
}
