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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campoTextoCodigo.setText(Long.toString(producto.getCodigo()));
        campoTextoNombre.setText(producto.getNombre());
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
        comboBoxCategoria.setValue(producto.getCategoria());
        campoTextoStockInicial.setText(Integer.toString(producto.getStockActual()));
        campoTextoStockMinimo.setText(Integer.toString(producto.getStockMinimo()));
        campoTextoPrecio.setText(Integer.toString(producto.getPrecio()));
        
        if (producto.getEstado())
            botonHabilitar.setDisable(true);
        else
            botonDarDeBaja.setDisable(true);
        
    }  
    
    public void cargarProducto(Producto producto){
        this.producto = producto;
    }
/**
 * Le da funcionalidad al boton Editar de la ventana Editar Producto.
 * Cuando se presiona se modifican los datos del producto.
 * @param event 
 */
    @FXML
    private void botonEditarProducto(ActionEvent event) {
        System.out.println("editado");
        try{
            consulta.modificarDatosDeProducto(campoTextoCodigo.getText(),
                                          campoTextoNombre.getText(),
                                          Integer.parseInt(campoTextoStockMinimo.getText()),
                                          comboBoxCategoria.getValue(),
                                          producto.getEstado(),
                                          Integer.parseInt(campoTextoStockInicial.getText()),
                                          Integer.parseInt(campoTextoPrecio.getText())
                                          );
        }catch (Exception ex) {
            System.out.println("error boton EditarProducto:"+ex);
        }
        
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
           consulta.darDeBajaProducto(producto.getCodigo());
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
            consulta.modificarProducto(producto.getCodigo(),
                                       producto.getNombre(),
                                       producto.getStockMinimo(),
                                       producto.getCategoria(),
                                       true,
                                       producto.getStockActual(),
                                       producto.getPrecio());
            botonHabilitar.setDisable(true);
            botonDarDeBaja.setDisable(false);
        }catch (Exception ex) {
            System.out.println("Error en habilitar Producto:"+ex);
        }
    }
}
