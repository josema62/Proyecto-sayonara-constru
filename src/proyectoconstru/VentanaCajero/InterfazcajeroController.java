
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
    OJO: HAY QUE VALIDAR QUE EL CODIGO SEAN NUMEROS O "6X129038182" QUE CONTIENE SOLO UNA X
    QUE SIMBOLIZA LA CANTIDAD DE ESE PRODUCTO... TODAS LAS DEMAS POSIBILIDADES ESTAN DESCARTADAS
    */
    @FXML
    private void accionIngresoCodigo(KeyEvent event) {
        int cantidadProducto;
        long codigoProducto;
        if(event.getCode().ENTER==event.getCode()){
            //HACER TODO
            String codigoPuro = this.campoDeTextoCodigo.getText();
            cantidadProducto = buscarCantidadProducto(codigoPuro);
            codigoProducto = buscarCodigoProducto(codigoPuro);
            //despues de hacer todo hay que setear el texto y dejarlo en blanco
            this.campoDeTextoCodigo.setText("");
        }
    }
    /*
    Lo que hace es:
        Si se ingresa en el text field un codigo de la siguiente forma:
    "6x0800485" se debe obtener el valor 6 que es la cantidad de productos
    */
    private int buscarCantidadProducto(String texto){
        char[] arregloCodigo = texto.toCharArray();
        for (int i = 0; i < arregloCodigo.length; i++) {
            char c = arregloCodigo[i];
            if(c == 'x'|| c=='X'){
                return Integer.parseInt(texto.substring(0, i));
            }
        }
        return 1;
    }
    
    private long buscarCodigoProducto(String texto){
        char[] arregloCodigo = texto.toCharArray();
        for (int i = 0; i < arregloCodigo.length; i++) {
            char c = arregloCodigo[i];
            if(c == 'x' || c == 'X'){
                return Integer.parseInt(texto.substring(i+1,arregloCodigo.length));
            }
        }
        return Integer.parseInt(texto);
    }
  

   
    
    
}
