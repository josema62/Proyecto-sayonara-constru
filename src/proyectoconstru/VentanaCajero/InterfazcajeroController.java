
package proyectoconstru.VentanaCajero;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelodedatos.Boleta;
import modelodedatos.DetalleProducto;
import modelodedatos.Producto;
import proyectoconstru.conexion.ConsultaBoleta;
import proyectoconstru.conexion.ConsultaProducto;

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
    private TableView<DetalleProducto> tablaBoleta;
    @FXML
    private Label etiquetaNombreCajero;
    @FXML
    private Button botonIngresar;
    @FXML
    private Button botonCancelar;
    @FXML
    private TableColumn<DetalleProducto, String> columnaCodigo;
    @FXML
    private TableColumn<DetalleProducto, String> columnaProducto;
    @FXML
    private TableColumn<DetalleProducto, Integer> columnaCantidad;
    @FXML
    private TableColumn<DetalleProducto, Integer> columnaSubtotal;
    @FXML
    private TableColumn<DetalleProducto, Integer> columnaPrecioUnitario;
    @FXML
    private Font x1;
    @FXML
    private Label etiquetaTotal;
    private String rutCajero;
    private ObservableList<DetalleProducto> datos = FXCollections.observableArrayList();
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.etiquetaNombreProducto.setWrapText(true);
        this.tablaBoleta.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        columnaCodigo.setResizable(true);
        columnaCantidad.setResizable(true);
        columnaPrecioUnitario.setResizable(true);
        columnaProducto.setResizable(true);
        columnaSubtotal.setResizable(true);
        columnaCodigo.setMaxWidth(15000);
        columnaCantidad.setMaxWidth(10000);
        columnaPrecioUnitario.setMaxWidth(11000);
        columnaProducto.setMaxWidth(40000);
        columnaSubtotal.setMaxWidth(20000);

        columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoProducto"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnaProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
        columnaSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        
        this.botonEditarProducto.setDisable(true);
        // TODO
    }    

    /*
    Debe abrir una nueva pestaña con los datos del articulo seleccionado
    */
    @FXML
    private void accionBotonEditarProducto(ActionEvent event) {
        try{
            Stage stageVentanaEdicion = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                    "/proyectoconstru/VentanaCajero/VentanaEdicion.fxml"));
            Parent root = loader.load();

            VentanaEdicionController in = loader.<VentanaEdicionController>getController();
            DetalleProducto producto = this.tablaBoleta.getSelectionModel().getSelectedItem();
                if(producto!=null){
                    this.botonEditarProducto.setDisable(false);
                     in.recibirParametros(producto, this);
                }


            Scene scene = new Scene(root);
            stageVentanaEdicion.setScene(scene);
            stageVentanaEdicion.setTitle("EdiatProducto");
            stageVentanaEdicion.show();
        }catch(Exception e){
        
        }
        
        
    }

    /*
    Cuando se termime la compra, se debe ingresar la boleta con los datos de la tabla
    Posteriormente setear en blanco la tabla y las etiquetas que puedan quedar
    */
    @FXML
    private void accionIngresar(ActionEvent event) {
        Date date = new Date();
        DateFormat formatoHora = new SimpleDateFormat("HH:mm");
        DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyy");
        
        String hora = formatoHora.format(date).toString();
        String fecha = formatoFecha.format(date).toString();
        
        if(!datos.isEmpty()){

        
        Boleta boleta = new Boleta(Integer.parseInt(this.etiquetaTotal.getText()), true, fecha,hora,
                                 this.etiquetaNombreCajero.getText(), this.rutCajero);
        
        for (DetalleProducto dato : datos) {
            boleta.agregardetalles(dato.getCodigoProducto(), dato);
            }
        
        ConsultaBoleta consultaBoleta = new ConsultaBoleta();
        
        int codigo = consultaBoleta.obtenerCodigoUltimaBoleta()+1;
        boleta.setCodigoProperty(new SimpleIntegerProperty(codigo));
        
        consultaBoleta.registrarBoleta(boleta);
        this.warning("Boleta ingresada Exitosamente","Se ha ingresado la boleta "+boleta.getCodigo());
        
        this.limpiarVentana();

        }
        else{
            this.warning("ERROR","Debe ingresar productos para emitir la boleta");
            
        }
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
        String codigoProducto;
        if(event.getCode().ENTER==event.getCode()){
            //HACER TODO
            String codigoPuro = this.campoDeTextoCodigo.getText();
            cantidadProducto = buscarCantidadProducto(codigoPuro);
            codigoProducto = buscarCodigoProducto(codigoPuro);
            
            ConsultaProducto consultaProducto = new ConsultaProducto();
            Producto producto = consultaProducto.obtenerDatosProducto(codigoProducto);
            if(producto!=null){
                if(!producto.getEstado()){
                    //EMITIR ALERTA DE QUE ESTA DESHABILITADO
                }
                else{
                    this.etiquetaNombreProducto.setText(producto.getNombre());
                    this.etiquetaValorProducto.setText("$"+producto.getPrecio());

                    int calcularSubtotal = cantidadProducto * producto.getPrecio();

                    //SIYA ESTA EN LA "BOLETA" SOLO DEBO AUMENTAR SU CANTIDAD Y SUBTOTAL
                    if(buscarProductoBoleta(codigoProducto)){
                        this.modificarFila(codigoProducto, cantidadProducto,
                                           calcularSubtotal,false);

                    }
                    else{
                        DetalleProducto detalle = new DetalleProducto(codigoProducto, producto.getNombre(),
                                                                      cantidadProducto,
                                                                      producto.getPrecio(),calcularSubtotal
                                                                      );
                        this.datos.add(detalle);
                        this.tablaBoleta.setItems(datos);
                    }
                    //Se actualiza el valor total
                    actualizarTotal();

                     //despues de hacer todo hay que setear el texto y dejarlo en blanco
                    this.campoDeTextoCodigo.setText("");
                }
            }
            else{
                this.warning("El producto no existe!", "El producto con código "+ 
                             this.campoDeTextoCodigo.getText()+" no esta registrado en el sistema");
            }
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
    
    private String buscarCodigoProducto(String texto){
        char[] arregloCodigo = texto.toCharArray();
        for (int i = 0; i < arregloCodigo.length; i++) {
            char c = arregloCodigo[i];
            if(c == 'x' || c == 'X'){
                return texto.substring(i+1,arregloCodigo.length);
            }
        }
        return texto;
    }
  
    public void setearNombreCajero(String nombre){
        this.etiquetaNombreCajero.setText(nombre);
    }
    
    public void setRutCajero(String rut){
        this.rutCajero = rut;
    }

   private void actualizarTotal(){
       int total =0;
       for (DetalleProducto dato : datos) {
           total+=dato.getSubtotal();
       }
       this.etiquetaTotal.setText(total+"");
   }
    
   private boolean buscarProductoBoleta(String codigo){
       for (DetalleProducto dato : datos) {
           if(codigo.equals(dato.getCodigoProducto())){
               return true;
           }
       }
       return false;
   }
   
   /*
   flag true: Edito con los datos ingresados
   flag false: Edito calculando los datos
   */
    public void modificarFila(String codigo, int cantidad, int subtotal, boolean flag){
        for (DetalleProducto dato : datos) {
            if(codigo.equals(dato.getCodigoProducto())){
                if(flag){
                    dato.setCantidad(cantidad);
                    dato.setSubtotal(subtotal);
                }
                else{
                    dato.setCantidad(dato.getCantidad()+cantidad);
                    dato.setSubtotal(dato.getSubtotal()+subtotal);
                }
           }
        }
        this.updateDatos();
    }
    
    private void updateDatos(){
        if(!datos.isEmpty()){
            ArrayList<DetalleProducto> produ = new ArrayList<>();
            for (DetalleProducto pro : datos) 
            {
                produ.add(pro);
            }
            datos.removeAll(datos);
            ObservableList<DetalleProducto> data2 = FXCollections.observableArrayList();
            this.tablaBoleta.getItems().clear();
            for (DetalleProducto deta : produ) {
                data2.add(deta);
            }
            datos = data2;
            this.tablaBoleta.setItems(datos);
            this.botonEditarProducto.setDisable(true);
        }
        actualizarTotal();
        
    }

    @FXML
    private void accionEliminarFila(KeyEvent event) {
        if(event.getCode() == event.getCode().DELETE){
            DetalleProducto producto = this.tablaBoleta.getSelectionModel().getSelectedItem();
            if(producto!=null){
                this.eliminarProductoLista(producto);
            }
            
        }
    }
    
    public void eliminarProductoLista(DetalleProducto pro){
        
        for (DetalleProducto dato : datos) {
            if(dato.getCodigoProducto().equals(pro.getCodigoProducto())){
                datos.remove(dato);
                this.etiquetaNombreProducto.setText("");
                this.etiquetaValorProducto.setText("");
                this.updateDatos();
                return;
            }
        }
    }
    
    private void limpiarVentana(){
        this.etiquetaNombreProducto.setText("");
        this.etiquetaTotal.setText("");
        this.etiquetaValorProducto.setText("");
        datos.removeAll(datos);
    }
    
    private void warning(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(text1);
        alert.setContentText(texto2);
        alert.showAndWait();
    }

    @FXML
    private void accionClickTabla(MouseEvent event) {
        DetalleProducto producto = this.tablaBoleta.getSelectionModel().getSelectedItem();
            if(producto!=null){
                this.botonEditarProducto.setDisable(false);
            }
    }

}
