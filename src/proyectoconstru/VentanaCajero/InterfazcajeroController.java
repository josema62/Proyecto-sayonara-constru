
package proyectoconstru.VentanaCajero;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelodedatos.Boleta;
import modelodedatos.DetalleProducto;
import modelodedatos.Producto;
import proyectoconstru.conexion.ConsultaBoleta;
import proyectoconstru.conexion.ConsultaProducto;

/**
 * Controlador de la interfaz de VentanaCajero
 *
 * @author Jose Nunnez
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
    private TableView<DetalleProducto> tablaBoleta;
    @FXML
    private Label etiquetaNombreCajero;
    @FXML
    private Button botonIngresar;
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
    @FXML
    private Button botonCancelarBoleta;
    @FXML
    private Spinner<Integer> spinnerCantidad;
    @FXML
    private Button botonIngresarProducto;
    @FXML
    private Button botonModificarCantidad;
    @FXML
    private Button botonEliminarProducto;
    
   
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
        
        this.botonModificarCantidad.setDisable(true);
        this.botonEliminarProducto.setDisable(true);
        this.botonCancelarBoleta.setDisable(true);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        spinnerCantidad.setValueFactory(valueFactory);
    }    

/**
 * Corresponde a la accion del boton Editar Producto. Aqui se abre una nueva
 * ventana (VentanaEdicion.fxml)
 * @param event 
 */
    @FXML
    private void accionBotonModificarProducto(ActionEvent event) {
        try{
            Stage stageVentanaEdicion = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                    "/proyectoconstru/VentanaCajero/VentanaEdicion.fxml"));
            Parent root = loader.load();

            VentanaEdicionController in = loader.<VentanaEdicionController>getController();
            DetalleProducto producto = this.tablaBoleta.getSelectionModel().getSelectedItem();
                if(producto!=null){
                    this.botonModificarCantidad.setDisable(false);
                    this.botonEliminarProducto.setDisable(false);
                     in.recibirParametros(producto, this);
                }


            Scene scene = new Scene(root);
            stageVentanaEdicion.setScene(scene);
            stageVentanaEdicion.setTitle("Editar Producto");
            stageVentanaEdicion.show();
        }catch(Exception e){
            System.out.println("paasodaps");
        }
        
        
    }

/**
 * Es la accion del boton Ingresar. Aqui se crea el objeto Boleta, el cual recibe
 * todos los datos que estan en pantalla, posteriormente, se llama a la consulta
 * que la inserta en la base de datos, mostrando un mensaje si fue exitoso o no.
 * @param event 
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
        this.mostrarMensajeAlerta("Boleta ingresada Exitosamente","Se ha ingresado la boleta "+boleta.getCodigo());
        
        this.limpiarVentana();

        }
        else{
            this.mostrarMensajeAlerta("ERROR","Debe ingresar productos para emitir la boleta");
            
        }
    }

    /**
     * Accion correspondiente al boton cancelar.
     * Solo se encarga de cerrar la actual ventana.
     * @param event 
     */
    private void accionCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }


    /**
     * Corresponde a la accion de ingreso de un codigo de un producto. 
     * Aqui se procede a obtener dicho codigo y corroborar la existencia del mismo
     * en la base de datos o si se encuentra deshabilitado. Si ninguno de estos casos
     * ocurre, se procede a obtener los datos del mismo para mostrarlos en la tabla
     * y actualizar el total de la venta
     * @param event 
     */
    @FXML
    private void accionBotonIngreso(ActionEvent event) {
        if(this.campoDeTextoCodigo.getText().isEmpty()){
            this.mostrarMensajeAlerta("Error de Ingreso", "El campo del código está vacío");
            return;
        }
        
        String codigoProducto = this.campoDeTextoCodigo.getText();
        int cantidadProducto = this.spinnerCantidad.getValue();
        ConsultaProducto consultaProducto = new ConsultaProducto();
        Producto producto = consultaProducto.obtenerDatosProducto(codigoProducto);
        if(producto!=null){
            if(producto.getStockActual()>cantidadProducto){
                if(!producto.getEstado()){
                    //Emite alerta que está deshabilitado
                    this.mostrarMensajeAlerta("Error de Ingreso", "El producto ingresado se encuentra deshabilitado");
                }
                else{
                    this.etiquetaNombreProducto.setText(producto.getNombre());
                    this.etiquetaValorProducto.setText("$"+producto.getPrecio());

                    int calcularSubtotal = cantidadProducto * producto.getPrecio();

                    //SI YA ESTA EN LA "BOLETA" SOLO DEBO AUMENTAR SU CANTIDAD Y SUBTOTAL
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
                    this.botonCancelarBoleta.setDisable(false);
                    this.campoDeTextoCodigo.setText("");
                    this.spinnerCantidad.getValueFactory().setValue(1);
                }
            }
            else{
                this.mostrarMensajeAlerta("Cantidad Erronea", "No hay stock suficiente, existen "
                + producto.getStockActual()+" productos");
            }
        }
        else{
            this.mostrarMensajeAlerta("El producto no existe!", "El producto con código "+ 
                         this.campoDeTextoCodigo.getText()+" no esta registrado en el sistema");
        }
        
    }

   
    /**
     *                  CODIGO NO NECESARIO PARA ESTA IMPLEMENTACION
     * Metodo que se encarga de obtener la cantidad ingresada de un determinado producto.
     * Lo que hace es verificar si se ingresó la cantidad en el campo codigo, es decir,
     * si hay un codigo del tipo "2x12312" donde 2 sería la cantidad del producto
     * con codigo 12312. Sino, es un codigo simple, es decir, 12312.
     * @param texto el codigo 'puro'
     * @return La cantidad de un determinado producto
     */
    /*
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
    */
    /**         CODIGO NO NECESARIO PARA ESTA VERSION
     * Metodo que se encarga de obtener el codigo de un producto.
     * Lo que hace es verificar si el codigo es simple o compuesto, es decir,
     * si hay un codigo del tipo "2x12312" donde 2 sería la cantidad del producto
     * con codigo 12312. Sino, es un codigo simple, es decir, 12312.
     * @param texto el codigo 'puro'
     * @return codigo del producto
     */
    /*
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
    
    /**
     * Renombra la etiqueta de NombreCajero con el nombre correspondiente
     * al cajero que se encuentra utilizando el sistema
     * @param nombre 
     */
    public void setearNombreCajero(String nombre){
        this.etiquetaNombreCajero.setText(nombre);
    }
    /**
     * Recibe y modifica el parametro rutCajero
     * @param rut 
     */
    public void setRutCajero(String rut){
        this.rutCajero = rut;
    }

    /**
     * Se encarga de Actualizar el total de una venta, se recorre todos los datos
     * de la tabla y se calcula su total, que es modificado en su correspondiente
     * etiqueta.
     */
    private void actualizarTotal(){
       int total =0;
       for (DetalleProducto dato : datos) {
           total+=dato.getSubtotal();
       }
       this.etiquetaTotal.setText(total+"");
    }
    
    /**
     * Verifica si unh producto ya se encuentra ingresado o no en una venta(Boleta)
     * @param codigo
     * @return true si está, false si NO está
     */
    private boolean buscarProductoBoleta(String codigo){
       for (DetalleProducto dato : datos) {
           if(codigo.equals(dato.getCodigoProducto())){
               return true;
           }
       }
       return false;
   }
   
    /**
     * Se encarga de modificar una fila de la tabla, tanto en su cantidad como subtotal.
     * Si la modificacion proviene de la ventana Editar, se debe editar completamente
     * los campos (sin realizar calculos). En cambio, si se agrega un producto que 
     * ya existe en la tabla se debe modificar calculando la nueva cantidad y subtotal
     * @param codigo codigo del producto a modificar
     * @param cantidad cantidad nueva del producto a sumar/modificaar
     * @param subtotal subtotal nuevo del producto a sumar/modificar
     * @param flag  determina si es proveniente de la ventana Edicion (true) 
     *              o de una agregacion (false) 
     */
    public void modificarFila(String codigo, int cantidad, int subtotal, boolean flag){
        for (DetalleProducto dato : datos) {
            if(codigo.equals(dato.getCodigoProducto())){
                //Si proviene de la ventana de edicion
                if(flag){
                    dato.setCantidad(cantidad);
                    dato.setSubtotal(subtotal);
                }
                //Si proviene de una agregación.
                else{
                    ConsultaProducto consulta = new ConsultaProducto();
                    Producto producto = consulta.obtenerDatosProducto(codigo);
                    if((dato.getCantidad()+cantidad)>producto.getStockActual()){
                        this.mostrarMensajeAlerta("Error en ingreso", "No hay stock suficiente, existen"
                + producto.getStockActual()+" productos");
                    }
                    else{
                        dato.setCantidad(dato.getCantidad()+cantidad);
                        dato.setSubtotal(dato.getSubtotal()+subtotal);
                    }
                }
           }
        }
        this.updateDatos();
    }
    
    /**
     * Se encarga de "refrescar" los datos ante posibles cambios.
     */
    private void updateDatos(){
        if(!datos.isEmpty()){
            //Se copian los datos en un ArrayList
            ArrayList<DetalleProducto> produ = new ArrayList<>();
            for (DetalleProducto pro : datos) 
            {
                produ.add(pro);
            }
            //Se eliminan los datos "oficiales"
            datos.removeAll(datos);
            ObservableList<DetalleProducto> data2 = FXCollections.observableArrayList();
            
            //Se copian los datos del Arraylist en los datos "oficiales"
            this.tablaBoleta.getItems().clear();
            for (DetalleProducto deta : produ) {
                data2.add(deta);
            }
            datos = data2;
            this.tablaBoleta.setItems(datos);
            this.botonModificarCantidad.setDisable(true);
            this.botonEliminarProducto.setDisable(true);
        }
        actualizarTotal();
        
    }

    /**
     * Se encarga de eliminar una fila de la tabla con productos. Esto se realiza
     * al seleccionar un producto y presionar la tecla DELETE
     * @param event 
     */
    @FXML
    private void accionEliminarFila(KeyEvent event) {
        if(event.getCode() == event.getCode().DELETE){
            DetalleProducto producto = this.tablaBoleta.getSelectionModel().getSelectedItem();
            if(producto!=null){
                this.eliminarProductoLista(producto);
                this.mostrarMensajeAlerta("Producto Eliminado", "El producto seleccionado ha sido eliminado");

            }
            
        }
    }
    
    /**
     * Se encarga de eliminar un producto
     * @param pro 
     */
    public void eliminarProductoLista(DetalleProducto pro){
        
        for (DetalleProducto dato : datos) {
            if(dato.getCodigoProducto().equals(pro.getCodigoProducto())){
                datos.remove(dato);
                this.etiquetaNombreProducto.setText("");
                this.etiquetaValorProducto.setText("");
                this.updateDatos();
                this.botonModificarCantidad.setDisable(true);
                this.botonEliminarProducto.setDisable(true);
                this.spinnerCantidad.getValueFactory().setValue(1);
                if(datos.isEmpty()){
                    this.botonCancelarBoleta.setDisable(true);
                }
                return;
            }
        }
    }
    
    /**
     * Se encarga de reiniciar la ventana y los datos al terminar un ingreso de 
     * una boleta
     */
    private void limpiarVentana(){
        this.etiquetaNombreProducto.setText("");
        this.etiquetaTotal.setText("");
        this.etiquetaValorProducto.setText("");
        this.spinnerCantidad.getValueFactory().setValue(1);
        datos.removeAll(datos);
    }
    
    /**
     * Se encarga de mostrar un mensaje de alerta en pantalla
     * @param text1 Es el titulo del mensaje
     * @param texto2 Es el cuerpo del mensaje
     */
    private void mostrarMensajeAlerta(String text1, String texto2) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setTitle(text1);
        alert.setHeaderText(null);
        alert.setContentText(texto2);
        alert.showAndWait();
    }

    /**
     * Se encarga de habilitar el boton de edicion cuando se selecciona un producto
     * de la tabla
     * @param event 
     */
    @FXML
    private void accionClickTabla(MouseEvent event) {
        DetalleProducto producto = this.tablaBoleta.getSelectionModel().getSelectedItem();
        if(producto!=null){
            this.botonModificarCantidad.setDisable(false);
            this.botonEliminarProducto.setDisable(false);
        }
    }

    @FXML
    private void accionBotonCancelarBoleta(ActionEvent event) {
        if(datos.isEmpty()){
            this.mostrarMensajeAlerta("Error", "No existen productos ingresados");
        }
        else{
            Alert dialogoConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoConfirmacion.setTitle("Confirmacion Cancelar Boleta");
            dialogoConfirmacion.setHeaderText(null);
            dialogoConfirmacion.initStyle(StageStyle.UTILITY);
            dialogoConfirmacion.setContentText("¿Desea cancelar la boleta?");

            Optional<ButtonType> resultado = dialogoConfirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK){
                this.limpiarVentana();
                this.mostrarMensajeAlerta("Boleta Cancelada","La compra ha sido cancelada");
            }
        }
    }

    @FXML
    private void accionIngresoCodigo(KeyEvent event) {
        
    }

    @FXML
    private void accionBotonEliminarProducto(ActionEvent event) {
        DetalleProducto producto = this.tablaBoleta.getSelectionModel().getSelectedItem();
            if(producto!=null){
                this.eliminarProductoLista(producto);
                this.mostrarMensajeAlerta("Producto Eliminado", "El producto seleccionado ha sido eliminado");
            }
        
        
    }

}
