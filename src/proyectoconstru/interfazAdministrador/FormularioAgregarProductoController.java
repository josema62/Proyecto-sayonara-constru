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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import proyectoconstru.conexion.ConsultaProducto;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class FormularioAgregarProductoController implements Initializable {

    @FXML
    private TextField campoTextoCodigo;
    @FXML
    private TextField campoTextoNombre;
    @FXML
    private ComboBox<String> comboBoxCategoria;
    @FXML
    private TextField campoTextoStockInicial;
    @FXML
    private TextField campoTextoStockMinimo;
    @FXML
    private TextField campoTextoPrecio;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonCancelar;
    private ConsultaProducto consulta = new ConsultaProducto();
    private ValidacionCampo validacion=new ValidacionCampo();
            

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
        comboBoxCategoria.setValue("Abarrotes");
    }  
    /**
     * agrega el producto a la base de datos gracias a la consulta 
     * de registrarProducto.
     * @param event 
     */
    @FXML
    private void agregarProducto(ActionEvent event){
        if (verificaCampos()) {
            if(!consulta.existeProducto(campoTextoCodigo.getText(),campoTextoNombre.getText())){
                consulta.registrarProducto(campoTextoCodigo.getText(), 
                    campoTextoNombre.getText(),
                    comboBoxCategoria.getValue(),
                    true,
                    Integer.parseInt(campoTextoPrecio.getText()),
                    Integer.parseInt(campoTextoStockInicial.getText()), 
                    Integer.parseInt(campoTextoStockMinimo.getText()));            
                limpiarCamposdeTexto();
            }
            else
                warning("Este producto ya existe!", "Por favor, ingrese un producto valido!");
        }
        
            
        
        
    }
    
     private boolean verificaCampos(){
        boolean verifica = true;
        String mensaje = "";
        if(validacion.campoVacio(campoTextoCodigo.getText())){
            campoTextoCodigo.setPromptText("Inserte un codigo valido");
            verifica = false; 
        }
        else if(!validacion.isNumeros(campoTextoCodigo.getText())){
            mensaje+=" Codigo Invalido -";
            verifica = false;
        }
        else if(!validacion.verificaCantidadNumerosCodigoProducto(campoTextoCodigo.getText())){
            mensaje+=" Telefono Invalido(Requerido 7 a 12 digitos) -";
            verifica = false;
        }
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
        if(validacion.campoVacio(campoTextoStockInicial.getText())){
            campoTextoStockInicial.setPromptText("Inserte un numero valido");
            verifica = false; 
        }
        else if(!validacion.isNumeros(campoTextoStockInicial.getText())){
            mensaje+=" Stock Inicial Invalido -";
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
     * limpia los campos de texto de la interfaz agregar Producto
     */
    private void limpiarCamposdeTexto() {
        campoTextoCodigo.clear();
        campoTextoNombre.clear();
        campoTextoPrecio.clear();
        campoTextoStockInicial.clear();
        campoTextoStockMinimo.clear();
        campoTextoCodigo.setPromptText("");
        campoTextoNombre.setPromptText("");
        campoTextoPrecio.setPromptText("");
        campoTextoStockInicial.setPromptText("");
        campoTextoStockMinimo.setPromptText("");
    }
    
    /**
     * elimina todos los datos escritos en la ventana agregarProducto
     * @param event 
     */
    @FXML
    private void botonCancelar(ActionEvent event) {
        limpiarCamposdeTexto();
        comboBoxCategoria.setValue("Abarrotes");
    }
    
}
