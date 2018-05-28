/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelodedatos.Producto;
import proyectoconstru.conexion.ConsultaProducto;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class ListarProductosBajaStockController implements Initializable {

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
    private TableColumn<Producto, Boolean> columnaEstado;
    
     private final ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    
    private ConsultaProducto consulta = new ConsultaProducto();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tablaProducto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.columnaCodigo.setMaxWidth(10000);
        this.columnaNombre.setMaxWidth(20000);
        this.columnaStockDisponible.setMaxWidth(10000);
        this.columnaStockMinimo.setMaxWidth(5000);
        this.columnaPrecio.setMaxWidth(10000);
        this.columnaCategoria.setMaxWidth(10000);
        this.columnaEstado.setMaxWidth(10000);
        
        setearValorCeldas();
        agregarProductosEnLista();
        tablaProducto.setItems(listaProductos);
    }    
    
      /**
     * obtiene los productos registrados desde la bd para agregarlos
     * a la ObservableList listaProductos.
     */
    private void agregarProductosEnLista(){
        List<Producto> lista = consulta.obtenerProductosConBajoStock();
        for (int i = 0; i < lista.size(); i++) {
            listaProductos.add(lista.get(i));
        }
    
    }
    
    public void cargarLista(List<Producto> lista){
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
                new PropertyValueFactory<Producto, Boolean>("estado"));
        columnaEstado.setCellValueFactory(cellData -> cellData.getValue().getEstadoProperty());
        // or cellData -> new SimpleBooleanProperty(cellData.getValue().getGender())
        // if your model class doesn't use JavaFX properties

        columnaEstado.setCellFactory(col -> new TableCell<Producto, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty) ;
                setText(empty ? null : item ? "Habilitado" : "Deshabilitado" );
            }
        });
    }
    
}
