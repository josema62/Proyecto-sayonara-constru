/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;


import java.io.IOException;
import modelodedatos.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import proyectoconstru.conexion.ConsultaCajero;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class ListarCajerosController implements Initializable {

    @FXML
    private TableView<Cajero> tablaCajero;
    @FXML
    private TableColumn<Cajero, String> columnaRut;
    @FXML
    private TableColumn<Cajero, String> columnaNombre;
    @FXML
    private TableColumn<Cajero, String> columnaDireccion;
    @FXML
    private TableColumn<Cajero, String> columnaTelefono;
    @FXML
    private TableColumn<Cajero, String> columnaContrasena;
    @FXML
    private TableColumn<Cajero, Boolean> columnaEstado;
    
    private final ObservableList<Cajero> listaCajeros = FXCollections.observableArrayList();
    
    private ConsultaCajero consulta = new ConsultaCajero();
    
    private Stage stagePrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tablaCajero.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.columnaRut.setMaxWidth(10000);
        this.columnaNombre.setMaxWidth(20000);
        this.columnaDireccion.setMaxWidth(15000);
        this.columnaTelefono.setMaxWidth(10000);
        this.columnaContrasena.setMaxWidth(10000);
        this.columnaEstado.setMaxWidth(10000);
        setearValorCeldas();
        agregarCajeroEnLista();
        tablaCajero.setItems(listaCajeros);
    }    
    
    /**
     * obtiene los cajeros registrados desde la bd para agregarlos
     * a la ObservableList listaCajeros.
     */
    private void agregarCajeroEnLista(){
        List<Cajero> lista = consulta.listarCajeros();
        for (int i = 0; i < lista.size(); i++) {
            listaCajeros.add(lista.get(i));
        }
        
    }
    
    /**
     * setea las columnas con el objeto y el valor que ira en cada una de ellas.
     */
    private void setearValorCeldas(){
        columnaRut.setCellValueFactory(
                new PropertyValueFactory<Cajero, String>("rut"));
        columnaNombre.setCellValueFactory(
                new PropertyValueFactory<Cajero, String>("nombre"));
        columnaDireccion.setCellValueFactory(
                new PropertyValueFactory<Cajero, String>("direccion"));
        columnaTelefono.setCellValueFactory(
                new PropertyValueFactory<Cajero, String>("telefono"));
        columnaContrasena.setCellValueFactory(
                new PropertyValueFactory<Cajero, String>("contrasenia"));
        columnaEstado.setCellValueFactory(
                new PropertyValueFactory<Cajero, Boolean>("estado"));
        columnaEstado.setCellValueFactory(cellData -> cellData.getValue().getEstadoProperty());
        // or cellData -> new SimpleBooleanProperty(cellData.getValue().getGender())
        // if your model class doesn't use JavaFX properties

        columnaEstado.setCellFactory(col -> new TableCell<Cajero, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty) ;
                setText(empty ? null : item ? "Habilitado" : "Deshabilitado" );
            }
        });
    }
    
    @FXML
    private void botonEditarCajero(ActionEvent event) throws IOException{
        if(tablaCajero.getSelectionModel().getSelectedItem()!=null){
            mostrarStageSecundario("EditarCajero.fxml");
            listaCajeros.clear();         
            agregarCajeroEnLista();         
            tablaCajero.setItems(listaCajeros);
        }
        else
            warning("No ha seleccionado ningun cajero", "Por favor, seleccione un cajero");
        
         
    }
    
    
     /**
     * genera un stage para mostrar una ventana secundaria
     * @param ruta string con el nombre del archivo fxml que se va abrir.
     */
    private void mostrarStageSecundario(String ruta) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = obtenerFXML(ruta);
        Parent root = loader.load();  
        EditarCajeroController controlador = loader.getController();
        controlador.cargarCajero(obtenerCajeroDesdeLista());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Editar Cajero");
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
    
    private Cajero obtenerCajeroDesdeLista(){
        Cajero cajero = tablaCajero.getSelectionModel().getSelectedItem();
        return cajero;
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
