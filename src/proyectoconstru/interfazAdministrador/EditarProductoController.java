/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import modelodedatos.ValidacionCampo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelodedatos.Producto;
import proyectoconstru.conexion.ConsultaProducto;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class EditarProductoController implements Initializable {

    @FXML
    private Button botonDarDeBaja;
    @FXML
    private TextField campoTextoCodigo;
    @FXML
    private TextField campoTextoNombre;
    @FXML
    private ComboBox<String> comboBoxCategoria;
    @FXML
    private Button botonAgregar;
    @FXML
    private TextField campoTextoStockInicial;
    @FXML
    private TextField campoTextoStockMinimo;
    @FXML
    private TextField campoTextoPrecio;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonHabilitar;
    
    private Producto producto;
    
    private ConsultaProducto consulta=new ConsultaProducto();
    
    private ValidacionCampo validacion = new ValidacionCampo();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxCategoria.getItems().addAll(
                "Abarrotes",
                "Lacteos",
                "Cecinas",
                "Carnes",
                "Bebidas",
                "Frutas",
                "Verduras",
                "Confites",
                "Utiles de Aseo",
                "Articulos de escritorio",
                "Otros"
        );
        
        campoTextoNombre.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoNombre.getText().length() == 40){
                event.consume();
            }

            }});
        campoTextoStockMinimo.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoStockMinimo.getText().length() == 5){
                event.consume();
            }

            }});
        campoTextoPrecio.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            if(  campoTextoPrecio.getText().length() == 10){
                event.consume();
            }

            }});
    }  
    
    public void cargarProducto(Producto pr){
        producto = pr;
        campoTextoCodigo.setText(producto.getCodigo());
        campoTextoNombre.setText(producto.getNombre());
        comboBoxCategoria.setValue(producto.getCategoria());
        campoTextoStockInicial.setText(Integer.toString(producto.getStockActual()));
        campoTextoStockMinimo.setText(Integer.toString(producto.getStockMinimo()));
        campoTextoPrecio.setText(Integer.toString(producto.getPrecio()));
        if (producto.getEstado())
            botonHabilitar.setDisable(true);
        else
            botonDarDeBaja.setDisable(true);
    }
/**
 * Le da funcionalidad al boton Editar de la ventana Editar Producto.
 * Cuando se presiona se modifican los datos del producto.
 * @param event 
 */
    @FXML
    private void botonEditarProducto(ActionEvent event) {
        if (verificaCampos()) {
            try{
                consulta.modificarDatosDeProducto(campoTextoCodigo.getText(),
                                              campoTextoNombre.getText(),
                                              Integer.parseInt(campoTextoStockMinimo.getText()),
                                              comboBoxCategoria.getValue(),
                                              producto.getEstado(),
                                              Integer.parseInt(campoTextoPrecio.getText())
                                              );
                Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
                stage.close();
            }catch (Exception ex) {
                System.out.println("error boton EditarProducto:"+ex);
            }
        }
       
        
    }
    
    private boolean verificaCampos(){
        boolean verifica = true;
        String mensaje = "";
        if(validacion.campoVacio(campoTextoNombre.getText())){
            campoTextoNombre.setPromptText("Inserte un nombre valido");
            verifica = false; 
        }
        if(validacion.campoVacio(campoTextoStockMinimo.getText())){
            campoTextoStockMinimo.setPromptText("Inserte un numero valido");
            verifica = false; 
        }
        else if(!validacion.isNumeros(campoTextoStockMinimo.getText())){
            mensaje+=" Stock Minimo Invalido -";
            verifica = false;
        }
        if(validacion.campoVacio(campoTextoPrecio.getText())){
            campoTextoPrecio.setPromptText("Inserte un numero valido");
            verifica = false; 
        }
        else if(!validacion.isNumeros(campoTextoPrecio.getText())){
            mensaje+=" Precio Invalido -";
            verifica = false;
        }
        if (!verifica) {
            warning("Algunos campos invalidos", mensaje);
        }
        return verifica;
    }
    
    private void warning(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(text1);
        alert.setTitle("ERROR");
        alert.setContentText(texto2);
        alert.showAndWait();
    }

    /**
     * Le da funcionalidad al boton Cancelar
     * cerrando la ventana.
     * @param event 
     */
    @FXML
    private void botonCancelar(ActionEvent event) {
        System.out.println("cancelado");
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }
    
    
    /**
     * Le da funcionalidad al boton dar de baja.
     * cambia el estado de un producto a deshabilitado.
     * @param event 
     */
    @FXML
    private void botonDarBaja(ActionEvent event) {
       try {
           producto.setEstado(false);
           botonDarDeBaja.setDisable(true);
           botonHabilitar.setDisable(false);
       }catch (Exception ex) {
           System.out.println("error en dar de baja:"+ex);
        }
    }
    /**
     * Habilita el producto a editar.
     * @param event 
     */
    @FXML
    private void botonHabilitarProducto(ActionEvent event){
        try{
            producto.setEstado(true);
            botonHabilitar.setDisable(true);
            botonDarDeBaja.setDisable(false);
        }catch (Exception ex) {
            System.out.println("Error en habilitar Producto:"+ex);
        }
    }
}
