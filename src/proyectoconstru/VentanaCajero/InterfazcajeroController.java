
package proyectoconstru.VentanaCajero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Unknown
 */
public class InterfazcajeroController implements Initializable {

    @FXML
    private Label etiquetaNombreProducto;
    @FXML
    private Label etiquetaValorProducto;
    @FXML
    private Label borrar;
    @FXML
    private TextField campoDeTextoCodigo;
    @FXML
    private Button botonEditarProducto;
    @FXML
    private TableView<?> tablaBoleta;
    @FXML
    private Label etiquetaNombreCajero;
    @FXML
    private Button botonIngresar;
    @FXML
    private Button botonCancelar;
    @FXML
    private TableColumn<?, ?> columnaCodigo;
    @FXML
    private TableColumn<?, ?> columnaProducto;
    @FXML
    private TableColumn<?, ?> columnaCantidad;
    @FXML
    private TableColumn<?, ?> columnaSubtotal;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.etiquetaNombreProducto.setWrapText(true);
        this.tablaBoleta.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        columnaCodigo.setResizable(true);
        columnaCantidad.setResizable(true);
        columnaProducto.setResizable(true);
        columnaSubtotal.setResizable(true);
        columnaCodigo.setMaxWidth(15000);
        columnaCantidad.setMaxWidth(10000);
        columnaProducto.setMaxWidth(40000);
        columnaSubtotal.setMaxWidth(15000);

        
        // TODO
    }    

    /*
    Debe abrir una nueva pestaña con los datos del articulo seleccionado
    */
    @FXML
    private void accionBotonEditarProducto(ActionEvent event) {
        
    }

    /*
    Cuando se termime la compra, se debe ingresar la boleta con los datos de la tabla
    Posteriormente setear en blanco la tabla y las etiquetas que puedan quedar
    */
    @FXML
    private void accionIngresar(ActionEvent event) {
      
    }

    @FXML
    private void accionCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }

    /*
    Se obtiene el codigo del producto, se debe recepcionar los datos del mismo
    (nombre, valor) y calcular segun la cantidad. Se debe actualizar las etiquetas (nombre y valor)
    y la tabla con la info.
    */
    @FXML
    private void accionIngresoCodigo(KeyEvent event) {
        
        if(event.getCode().ENTER==event.getCode()){
            //HACER TODO
            
            //despues de hacer todo hay que setear el texto y dejarlo en blanco
            this.campoDeTextoCodigo.setText("");
        }
    }

  

   
    
    
}
