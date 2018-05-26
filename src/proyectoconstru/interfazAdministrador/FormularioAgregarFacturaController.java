/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import modelodedatos.DetalleProducto;
import modelodedatos.Factura;
import modelodedatos.Proveedor;
import proyectoconstru.conexion.ConsultaFactura;
import proyectoconstru.conexion.ConsultaProveedor;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class FormularioAgregarFacturaController implements Initializable {

    @FXML
    private TextField campoTextoNumeroFactura;
    @FXML
    private DatePicker datePickerFechaEmision;
    @FXML
    private ComboBox<String> comboBoxProveedor;
    @FXML
    private TextField campoTextoMontoNeto;
    @FXML
    private TextField campoTextoIVA;
    @FXML
    private TextField campoTextoTotal;
    @FXML
    private Button botonAgregarProducto;
    @FXML
    private Button botonEditarProducto;
    @FXML
    private TableView<DetalleProducto> tablaProductosFactura;
    @FXML
    private TableColumn<DetalleProducto, String> columnaCodigoProducto;
    @FXML
    private TableColumn<DetalleProducto, String> columnaNombreProducto;
    @FXML
    private TableColumn<DetalleProducto, String> columnaPrecioUnitarioProducto;
    @FXML
    private TableColumn<DetalleProducto, String> columnaCantidad;
    @FXML
    private TableColumn<DetalleProducto, String> columnaSubtotal;
    @FXML
    private Button botonAgregarFactura;
    @FXML
    private Button botonCancelarFactura;
        
    private final ObservableList<DetalleProducto> listaProductos = FXCollections.observableArrayList();
    
    private ConsultaFactura consulta = new ConsultaFactura();
    private ConsultaProveedor consultap = new ConsultaProveedor();
    
    private List<Proveedor> lista;
    
    private Stage stagePrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tablaProductosFactura.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.columnaCodigoProducto.setMaxWidth(10000);
        this.columnaNombreProducto.setMaxWidth(20000);
        this.columnaCantidad.setMaxWidth(10000);
        this.columnaSubtotal.setMaxWidth(10000);
        setearValorCeldas();
        lista = consultap.listarProveedores();
        for (int i = 0; i < lista.size(); i++) {
            comboBoxProveedor.getItems().add(lista.get(i).getNombre());
        }
        Callback<DatePicker,DateCell> celdaDia = new Callback<DatePicker, DateCell>()
        {
            public DateCell call(final DatePicker datePicker){
            return new DateCell(){
                @Override
                public void updateItem(LocalDate item, boolean empty){
                    super.updateItem(item, empty);
                    if(item.isAfter(LocalDate.now())){
                        this.setDisable(true);
                    }
                    }
                };
            }
        };
        //se actualiza el pickerDate con las celdas deshabilitadas
        datePickerFechaEmision.setDayCellFactory(celdaDia);
    }    
    
    
    @FXML
    private void agregarFactura(ActionEvent event){
        HashMap<String, DetalleProducto> detalleProductos = new HashMap<>();
        if(listaProductos.size()>0){
            for (int i = 0; i < listaProductos.size(); i++) {
                detalleProductos.put(listaProductos.get(i).getCodigoProducto(),
                                     listaProductos.get(i));
            }
            String proveedor="";
            for (int i = 0; i < lista.size(); i++) {
                if(lista.get(i).getNombre().equals(comboBoxProveedor.getValue())){
                    proveedor = lista.get(i).getRut();
                }
            }
           Factura factura = new Factura(Integer.parseInt(
                    campoTextoNumeroFactura.getText()), 
                datePickerFechaEmision.getValue().format(
                        DateTimeFormatter.ISO_LOCAL_DATE), 
                Integer.parseInt(campoTextoMontoNeto.getText()),
                Integer.parseInt(campoTextoIVA.getText()), 
                Integer.parseInt(campoTextoTotal.getText()), 
                proveedor,
                detalleProductos);
            consulta.registrarFactura(factura);
        }
        limpiarCampos();
    }

    private void limpiarCampos() {
        campoTextoNumeroFactura.clear();
        campoTextoMontoNeto.clear();
        campoTextoIVA.clear();
        campoTextoTotal.clear();
        listaProductos.clear();
    }
    
    /**
     * setea las columnas con el objeto y el valor que ira en cada una de ellas.
     */
    private void setearValorCeldas(){
        columnaCodigoProducto.setCellValueFactory(
                new PropertyValueFactory<DetalleProducto, String>("codigoProducto"));
        columnaNombreProducto.setCellValueFactory(
                new PropertyValueFactory<DetalleProducto, String>("nombre"));
        columnaCantidad.setCellValueFactory(
                new PropertyValueFactory<DetalleProducto, String>("cantidad"));
        columnaPrecioUnitarioProducto.setCellValueFactory(
                new PropertyValueFactory<DetalleProducto, String>("precioUnitario"));
        columnaSubtotal.setCellValueFactory(
                new PropertyValueFactory<DetalleProducto, String>("subtotal"));
    }
    
    @FXML
    private void cancelarFactura(){
        limpiarCampos();
    }
    
    @FXML
    private void agregarProducto() throws IOException{
        mostrarStageSecundario("AgregarProductoEnFactura.fxml");     
    }
    
    @FXML
    private void editarProducto() throws IOException{
        if(tablaProductosFactura.getSelectionModel().getSelectedItem()!=null){
            mostrarStageSecundario1("EditarProductoEnFactura.fxml");
        }else
            warning("No ha seleccionado ningun producto de la lista", "Porfavor,seleccione uno");
    }
    
    /**
     * genera un stage para mostrar una ventana secundaria
     * @param ruta string con el nombre del archivo fxml que se va abrir.
     */
    private void mostrarStageSecundario(String ruta) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = obtenerFXML(ruta);
        Parent root = loader.load();  
        AgregarProductoEnFacturaController controlador = loader.getController();
        controlador.obtenerControlador(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Agregar Producto");
        stage.initStyle(StageStyle.UTILITY);
           
        stage.initOwner(stagePrincipal);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }
    
    /**
     * genera un stage para mostrar una ventana secundaria
     * @param ruta string con el nombre del archivo fxml que se va abrir.
     */
    private void mostrarStageSecundario1(String ruta) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = obtenerFXML(ruta);
        Parent root = loader.load();  
        EditarProductoEnFacturaController controlador = loader.getController();
        controlador.obtenerControlador(this);
        controlador.cargarProducto(obtenerProductoDesdeLista());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Editar Cantidad Producto");
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
    
    private boolean verificaExistenciaProducto(DetalleProducto detalle){
        for (int i = 0; i < listaProductos.size(); i++) {
            if(listaProductos.get(i).getCodigoProducto().equals(detalle.getCodigoProducto())){
                detalle.setCantidad(detalle.getCantidad()+listaProductos.get(i).getCantidad());
                listaProductos.remove(i);
                listaProductos.add(detalle);
                return true;
            }
        }
        return false;
    }
    
    public void agregarEnListaProductos(DetalleProducto detalle){
        if(!verificaExistenciaProducto(detalle))
            listaProductos.add(detalle);
        setearMontos();
        tablaProductosFactura.setItems(listaProductos);
    }
    
    
    private void setearMontos(){
        int suma = 0;
        for (int i = 0; i < listaProductos.size(); i++) {
            suma  = suma + listaProductos.get(i).getSubtotal();
        }
        campoTextoMontoNeto.setText(""+suma);
        int iva = (int) (suma*0.19);
        campoTextoIVA.setText(""+iva);
        int total = suma + iva;
        campoTextoTotal.setText(""+total);
    }
    
    private DetalleProducto obtenerProductoDesdeLista(){
        DetalleProducto producto = tablaProductosFactura.getSelectionModel().getSelectedItem();
        return producto;
    }

    public void agregarCambioEnListaProducto(DetalleProducto producto) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigoProducto().equals(producto.getCodigoProducto())) {
                listaProductos.remove(i);
                if(producto.getCantidad()>0){
                    listaProductos.add(producto);
                }
            }
        }
        setearMontos();
        tablaProductosFactura.setItems(listaProductos);
    }
    
    private void warning(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(text1);
        alert.setTitle("ERROR");
        alert.setContentText(texto2);
        alert.showAndWait();
    }

    void obtenerStage(Stage stage) {
        stagePrincipal = stage;
    }
}
