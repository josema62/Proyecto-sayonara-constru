/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelodedatos.Producto;
import proyectoconstru.conexion.ConsultaProducto;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class ListarProductosProveedorController implements Initializable {

    @FXML
    private TableView<Producto> tablaProducto;
    @FXML
    private TableColumn<Producto, String> columnaCodigo;
    @FXML
    private TableColumn<Producto, String> columnaNombre;
    @FXML
    private TableColumn<Producto, String> columnaStockDisponible;
    @FXML
    private TableColumn<Producto, String> columnaStockMinimo;
    @FXML
    private TableColumn<Producto, String> columnaPrecio;
    @FXML
    private TableColumn<Producto, String> columnaCategoria;
    @FXML
    private TableColumn<Producto, String> columnaEstado;
    
     private final ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    
     private List<Producto> lista;
     private ConsultaProducto consulta = new ConsultaProducto();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setearValorCeldas();
    }    
    
    
    public void cargarLista(List<Producto> list){
        lista = list;
        listaProductos.clear();
        for (int i = 0; i < lista.size(); i++) {
            listaProductos.add(lista.get(i));
        }
        tablaProducto.setItems(listaProductos);
    }
    
    /**
     * setea las columnas con el objeto y el valor que ira en cada una de ellas.
     */
    private void setearValorCeldas(){
        columnaCodigo.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("codigo"));
        columnaNombre.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("nombre"));
        columnaStockDisponible.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("stockActual"));
        columnaStockMinimo.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("stockMinimo"));
        columnaPrecio.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("precio"));
        columnaCategoria.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("categoria"));
        columnaEstado.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("estado"));
    }
    /**
     * Le da funcionalidad al boton Editar Producto.
     * @param event 
     */
    @FXML
    private void botonEditarProducto(ActionEvent event) throws IOException {
        if(tablaProducto.getSelectionModel().getSelectedItem()!=null){
            mostrarStageSecundario("EditarProducto.fxml");
            Producto producto = obtenerProductoDesdeLista();
            String codigo = producto.getCodigo();
            Producto nuevo = consulta.obtenerDatosProducto(codigo);
            for (int i = 0; i < lista.size(); i++) {
                if(lista.get(i).equals(codigo)){
                    lista.remove(i);
                    lista.add(nuevo);
                }
            }
            listaProductos.clear();
            for (int i = 0; i < lista.size(); i++) {
                listaProductos.add(lista.get(i));
            }
            tablaProducto.setItems(listaProductos);
        }
        else
            warning("No ha seleccionado ningun producto", "Por favor seleccione un producto de la lista");
    }
    
    /**
     * genera un stage para mostrar una ventana secundaria
     * @param ruta string con el nombre del archivo fxml que se va abrir.
     */
    private void mostrarStageSecundario(String ruta) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = obtenerFXML(ruta);
        Parent root = loader.load();  
        EditarProductoController controlador = loader.getController();
        controlador.cargarProducto(obtenerProductoDesdeLista());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        stage.close();
    }
    
    /**
     * abre un archivo fxml dado en la ruta
     * @param ruta contiene un string con el nombre del archivo fxml.
     * @return AnchorPane usado para ser agregado a otro Pane.
     */
    private FXMLLoader obtenerFXML(String ruta) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ruta));
        return loader;
    }
    
    private Producto obtenerProductoDesdeLista(){
        Producto producto = tablaProducto.getSelectionModel().getSelectedItem();
        return producto;
    }
    private void warning(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(text1);
        alert.setTitle("ERROR");
        alert.setContentText(texto2);
        alert.showAndWait();
    }
}
