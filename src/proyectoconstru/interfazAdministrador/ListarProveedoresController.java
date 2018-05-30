/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import modelodedatos.Cajero;
import modelodedatos.Producto;
import modelodedatos.Proveedor;
import proyectoconstru.conexion.ConsultaProveedor;


/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class ListarProveedoresController implements Initializable {

    @FXML
    private TableView<Proveedor> tablaProveedor;
    @FXML
    private TableColumn<Proveedor,String> columnaRut;
    @FXML
    private TableColumn<Proveedor,String> columnaRazonSocial;
    @FXML
    private TableColumn<Proveedor,String> columnaNombre;
    @FXML
    private TableColumn<Proveedor,String> columnaDireccion;
    @FXML
    private TableColumn<Proveedor,String> columnaTelefono;
    @FXML
    private TableColumn<Proveedor,String> columnaTelefonoOpcional;
    @FXML
    private TableColumn<Proveedor,String> columnaCorreo;
    @FXML
    private TableColumn<Proveedor,Boolean> columnaEstado;//revisar
    @FXML
    private Button editarProveedor;
    
    private Hyperlink ProductosAsociados;

    private final ObservableList<Proveedor> listaProveedores = FXCollections.observableArrayList();
    
    private ConsultaProveedor consulta = new ConsultaProveedor();
    
    private Stage stagePrincipal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tablaProveedor.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.columnaRut.setMaxWidth(10000);
        this.columnaRazonSocial.setMaxWidth(20000);
        this.columnaNombre.setMaxWidth(20000);
        this.columnaDireccion.setMaxWidth(15000);
        this.columnaTelefono.setMaxWidth(10000);
        this.columnaTelefonoOpcional.setMaxWidth(10000);
        this.columnaCorreo.setMaxWidth(10000);
        this.columnaEstado.setMaxWidth(10000);
        
        setearValorCeldas();
        agregarProveedoresEnLista();
        tablaProveedor.setItems(listaProveedores);  
    }    
    
    
    /**
     * obtiene los productos registrados desde la bd para agregarlos
     * a la ObservableList listaProductos.
     */
    private void agregarProveedoresEnLista(){
        Task<List<Proveedor>> tarea = new Task<List<Proveedor>>() {
            @Override
            protected List<Proveedor> call() throws Exception {
                return consulta.listarProveedores();
            }
        };
        tarea.setOnSucceeded(event -> {
            List <Proveedor> lista = tarea.getValue();
            for (int i = 0; i < lista.size(); i++) {
                listaProveedores.add(lista.get(i));
            }
            tablaProveedor.setPlaceholder(new Label("La tabla esta vacia"));
        });
        Thread thread = new Thread(tarea);
        tablaProveedor.setPlaceholder(new Label("Cargando ..."));
        thread.setDaemon(true);
        thread.start();   
    }
    
    /**
     * setea las columnas con el objeto y el valor que ira en cada una de ellas.
     */
    private void setearValorCeldas(){
        columnaRut.setCellValueFactory(
                new PropertyValueFactory<Proveedor, String>("rut"));
        columnaRazonSocial.setCellValueFactory(
                new PropertyValueFactory<Proveedor, String>("razonSocial"));
        columnaNombre.setCellValueFactory(
                new PropertyValueFactory<Proveedor, String>("nombre"));
        columnaDireccion.setCellValueFactory(
                new PropertyValueFactory<Proveedor, String>("direccion"));
        columnaTelefono.setCellValueFactory(
                new PropertyValueFactory<Proveedor, String>("telefonoObligatorio"));
        columnaTelefonoOpcional.setCellValueFactory(
                new PropertyValueFactory<Proveedor, String>("telefonoOpcional"));
        columnaCorreo.setCellValueFactory(
                new PropertyValueFactory<Proveedor, String>("correo"));
        columnaEstado.setCellValueFactory(
                new PropertyValueFactory<Proveedor, Boolean>("estado"));
        columnaEstado.setCellValueFactory(cellData -> cellData.getValue().getEstadoProperty());
        // or cellData -> new SimpleBooleanProperty(cellData.getValue().getGender())
        // if your model class doesn't use JavaFX properties

        columnaEstado.setCellFactory(col -> new TableCell<Proveedor, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty) ;
                setText(empty ? null : item ? "Habilitado" : "Deshabilitado" );
            }
        });
        
    }
    
    /**
     * Le da funcionalidad al boton Editar Proveedor.
     * @param event 
     */
    @FXML
    private void botonEditarProveedor(ActionEvent event) throws IOException {
        if(tablaProveedor.getSelectionModel().getSelectedItem()!=null){
            mostrarStageSecundario("EditarProveedor.fxml");
            listaProveedores.clear();         
            agregarProveedoresEnLista();         
            tablaProveedor.setItems(listaProveedores);
        }
        else
            warning("No ha seleccionado ningun proveedor", "Por favor, selecciones un proveedor de la lista");
    }
    
    /**
     * genera un stage para mostrar una ventana secundaria
     * @param ruta string con el nombre del archivo fxml que se va abrir.
     */
    private void mostrarStageSecundario(String ruta) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = obtenerFXML(ruta);
        Parent root = loader.load();  
        EditarProveedorController controlador = loader.getController();
        controlador.cargarProveedor(obtenerProveedorDesdeLista());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Editar Proveedor");
        stage.initStyle(StageStyle.UTILITY);         
        stage.initOwner(stagePrincipal);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
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
    
    private Proveedor obtenerProveedorDesdeLista(){
        Proveedor proveedor = tablaProveedor.getSelectionModel().getSelectedItem();
        return proveedor;
    }
    
    
    /**
     * Le da funcionalidad al Hyperlink detalles de Productos.
     * @param event 
     */
    @FXML
    private void detallesDeProductos(ActionEvent event) throws IOException {
        if(tablaProveedor.getSelectionModel().getSelectedItem()!=null){
            Proveedor proveedor = obtenerProveedorDesdeLista();
            Stage stage = new Stage();
            FXMLLoader loader = obtenerFXML("ListarProductosProveedor.fxml");
            Parent root = loader.load();  
            ListarProductosProveedorController controlador = loader.getController();
            controlador.cargarLista(consulta.obtenerProductosProveedor(proveedor.getRut()));
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.setTitle("Productos Asociados de "+ proveedor.getNombre());
            stage.initStyle(StageStyle.UTILITY);         
            stage.initOwner(stagePrincipal);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            controlador.obtenerStage(stage);
            stage.showAndWait();
            stage.close();
        }  
    }
    
    private void warning(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(text1);
        alert.setTitle("ERROR");
        alert.setContentText(texto2);
        alert.showAndWait();
    }

    public void obtenerStage(Stage stage) {
        stagePrincipal = stage;
    }
    
}
