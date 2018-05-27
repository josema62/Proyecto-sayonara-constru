/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta & Jose Nunnez
 */
public class VentanaAdministradorController implements Initializable {

    @FXML
    private TreeView<String> raiz;
    @FXML
    private StackPane paneDinamico;

    private TreeItem gestionProductos;
    private TreeItem gestionProveedores;
    private TreeItem gestionCajeros;
    private TreeItem anularBoleta;
    private TreeItem agregarFactura;
    private TreeItem generarReportes;
    private TreeItem varios;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cambiarFabricaCeldas();
        TreeItem<String> root = new TreeItem<>("MENU");
        root.setExpanded(true);
        creadorGestionProductos();
        creadorGestionProveedores();
        creadorGestionCajeros();
        creadorVarios();
        creadorGeneradorReportes();
        root.getChildren().addAll(gestionProductos, gestionProveedores,
                                  generarReportes, gestionCajeros, varios);
        raiz.setRoot(root);
    }
    
    private void cambiarFabricaCeldas(){
        raiz.setCellFactory((treeview) ->
        {
            TreeCell<String> cell = new TreeCell<String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            cell.setOnMouseClicked((event) -> {
                determinarPanel(cell.getText());
            });
            return cell;
        });
    }
    
    /**
     * crea el treeItem Gestion de Productos junto con sus hijos.
     */
    private void creadorGestionProductos() {
        gestionProductos = new TreeItem<>("Gestion de Productos");
        TreeItem<String> agregarProducto = new TreeItem<>("Agregar Producto");
        TreeItem<String> listarProducto = new TreeItem<>("Listar Productos");
        gestionProductos.getChildren().addAll(agregarProducto, listarProducto);
    }
    /**
     * crea el treeItem Gestion de Proveedores junto con sus hijos.
     */
    private void creadorGestionProveedores() {
        gestionProveedores = new TreeItem<>("Gestion de Proveedores");
        TreeItem<String> agregarProveedor = new TreeItem<>("Agregar Proveedor");
        TreeItem<String> listarProveedor = new TreeItem<>("Listar Proveedor");
        gestionProveedores.getChildren().addAll(agregarProveedor,
                                                listarProveedor);
    }
    /**
     * crea el treeItem Gestion de Cajeros junto con sus hijos.
     */
    private void creadorGestionCajeros() {
        gestionCajeros = new TreeItem<>("Gestion de Cajeros");
        TreeItem<String> agregarCajero = new TreeItem<>("Agregar Cajero");
        TreeItem<String> listarCajeros = new TreeItem<>("Listar Cajeros");
        gestionCajeros.getChildren().addAll(agregarCajero, listarCajeros);
    }
    /**
     * crea el treeItem Varios junto con sus hijos.
     */
    private void creadorVarios() {
        varios = new TreeItem<>("Varios");
        anularBoleta = new TreeItem<>("Anular Boleta");
        agregarFactura = new TreeItem<>("Agregar Factura");
        TreeItem<String> notificacionProducto = new TreeItem<>("Productos bajos");
        varios.getChildren().addAll(agregarFactura, anularBoleta,
                                                    notificacionProducto);

    }
    /**
     * crea el treeItem Generar Reporte junto con sus hijos.
     */
    private void creadorGeneradorReportes() {
        generarReportes = new TreeItem<>("Generar Reporte");
        TreeItem<String> compras = new TreeItem<>("Compras a proveedores");
        TreeItem<String> ventas = new TreeItem<>("Venta Diaria");
        generarReportes.getChildren().addAll(compras, ventas);
    }
    /**
     * Aqui se modifica el paneDinamico segun lo que presione el usuario en el TreeView
     * @param value string que contiene el valor del treeItem que presiono el usuario.
     */
    private void determinarPanel(String value) {
        if (value.equals("Agregar Producto")) {
            modificarPaneDinamico("FormularioAgregarProducto.fxml");
        }
        if (value.equals("Listar Productos")) {
            modificarPaneDinamicoListarProductos("ListarProductos.fxml");
        }
        if (value.equals("Agregar Proveedor")) {
            modificarPaneDinamico("FormularioAgregarProveedor.fxml");
        }
        if (value.equals("Listar Proveedor")) {
            modificarPaneDinamicoListarProveedores("ListarProveedores.fxml");
        }
        if (value.equals("Agregar Factura")) {
            modificarPaneDinamicoAgregarFactura("FormularioAgregarFactura.fxml");
        }
        if (value.equals("Agregar Cajero")) {
            modificarPaneDinamico("FormularioAgregarCajero.fxml");
        }
        if (value.equals("Listar Cajeros")) {
            modificarPaneDinamicoListarCajeros("ListarCajeros.fxml");
        }
        if (value.equals("Compras a proveedores")) {
            mostrarStageSecundarioCompra();
        }
        if (value.equals("Venta Diaria")) {
            mostrarStageSecundarioVentas();
        }
        if (value.equals("Anular Boleta")) {
            mostrarStageSecundario("AnularBoleta.fxml");
        }

    }
    /**
     * genera un stage para mostrar una ventana secundaria
     * @param ruta string con el nombre del archivo fxml que se va abrir.
     */
    private void mostrarStageSecundario(String ruta) {
        
        Stage stageVentanaAnularBoleta = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                    ruta));
            Parent root = loader.load();

            //AnularBoletaController controlador = loader.<AnularBoletaController>getController();

            Scene scene = new Scene(root);
            stageVentanaAnularBoleta.setScene(scene);
            stageVentanaAnularBoleta.setTitle("Anular Boleta");
            stageVentanaAnularBoleta.initStyle(StageStyle.UTILITY);
            
            Stage stageActual = (Stage) this.paneDinamico.getScene().getWindow();
            stageVentanaAnularBoleta.initOwner(stageActual);
            stageVentanaAnularBoleta.initModality(Modality.WINDOW_MODAL);
            stageVentanaAnularBoleta.setResizable(false);
            stageVentanaAnularBoleta.showAndWait();

        }catch(Exception e){
            System.out.println("ERROR: "+e);
        }
    }
    
    
    
      /**
     * Genera un stage para mostrar una ventana secundaria de ventas
     * @param ruta string con el nombre del archivo fxml que se va abrir.
     */
    private void mostrarStageSecundarioVentas() {
        Stage stageVentanaVentas = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                    "VentaDiaria.fxml"));
            Parent root = loader.load();

            VentaDiariaController controlador = loader.<VentaDiariaController>getController();
            controlador.recibirControlador(this);

            Scene scene = new Scene(root);
            stageVentanaVentas.setScene(scene);
            stageVentanaVentas.setTitle("Fecha Venta");
            stageVentanaVentas.initStyle(StageStyle.UTILITY);
            
            Stage stageActual = (Stage) this.paneDinamico.getScene().getWindow();
            stageVentanaVentas.initOwner(stageActual);
            stageVentanaVentas.initModality(Modality.WINDOW_MODAL);
            
            stageVentanaVentas.showAndWait();

        }catch(Exception e){
            System.out.println("ERROR: "+e);
        }
    }
    /**
     * genera un stage para mostrar una ventana secundaria de compras a proveedores
     * @param ruta string con el nombre del archivo fxml que se va abrir.
     */
    private void mostrarStageSecundarioCompra() {
        Stage stageVentanaCompraProveedores = new Stage();
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                    "CompraProveedores.fxml"));
            Parent root = loader.load();

            CompraProveedoresController controlador = loader.<CompraProveedoresController>getController();
            controlador.recibirControlador(this);

            Scene scene = new Scene(root);
            stageVentanaCompraProveedores.setScene(scene);

            stageVentanaCompraProveedores.initStyle(StageStyle.UTILITY);
            stageVentanaCompraProveedores.setTitle("Periodo de compra");
            
            Stage stageActual = (Stage) this.paneDinamico.getScene().getWindow();
            stageVentanaCompraProveedores.initOwner(stageActual);
            stageVentanaCompraProveedores.initModality(Modality.WINDOW_MODAL);

            stageVentanaCompraProveedores.showAndWait();


            
        }catch(Exception e){
            System.out.println("ERROR: "+e);
        }
    }
    
    /**
     * Limpia los hijos que almacena paneDinamico y agrega el nuevo AnchorPane
     * que se obtiene de obtenerFXML(ruta).
     * @param ruta contiene un string con el nombre del archivo fxml.
     */
    private void modificarPaneDinamico(String ruta) {
        paneDinamico.getChildren().clear();
        paneDinamico.getChildren().add(obtenerFXML(ruta));
    }
    
    private void modificarPaneDinamicoListarCajeros(String ruta) {
        paneDinamico.getChildren().clear();
        AnchorPane pane =null;
        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource(ruta));
            pane = load.load();
            ListarCajerosController r =load.<ListarCajerosController>getController();
            r.obtenerStage((Stage)this.paneDinamico.getScene().getWindow());
        }
        catch (IOException e) {
            System.out.println("fallo:" + e);
        }
        
        paneDinamico.getChildren().add(pane);
    }
    
    private void modificarPaneDinamicoListarProveedores(String ruta) {
        paneDinamico.getChildren().clear();
        AnchorPane pane =null;
        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource(ruta));
            pane = load.load();
            ListarProveedoresController r =load.<ListarProveedoresController>getController();
            r.obtenerStage((Stage)this.paneDinamico.getScene().getWindow());
        }
        catch (IOException e) {
            System.out.println("fallo:" + e);
        }
        
        paneDinamico.getChildren().add(pane);
    }
    
    private void modificarPaneDinamicoListarProductos(String ruta) {
        paneDinamico.getChildren().clear();
        AnchorPane pane =null;
        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource(ruta));
            pane = load.load();
            ListarProductosController r =load.<ListarProductosController>getController();
            r.obtenerStage((Stage)this.paneDinamico.getScene().getWindow());
        }
        catch (IOException e) {
            System.out.println("fallo:" + e);
        }
        
        paneDinamico.getChildren().add(pane);
    }
    
    private void modificarPaneDinamicoAgregarFactura(String ruta) {
        paneDinamico.getChildren().clear();
        AnchorPane pane =null;
        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource(ruta));
            pane = load.load();
            FormularioAgregarFacturaController r =load.<FormularioAgregarFacturaController>getController();
            r.obtenerStage((Stage)this.paneDinamico.getScene().getWindow());
        }
        catch (IOException e) {
            System.out.println("fallo:" + e);
        }
        
        paneDinamico.getChildren().add(pane);
    }
    protected void modificarPaneDinamicoVentas(String ruta, LocalDate fecha) {
        paneDinamico.getChildren().clear();
        AnchorPane pane =null;
        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource(ruta));
            pane = load.load();
            ReporteDiarioController r =load.<ReporteDiarioController>getController();
            r.modificarFecha(fecha);
            r.llenarTabla();
        }
        catch (IOException e) {
            System.out.println("fallo:" + e);
        }
        
        paneDinamico.getChildren().add(pane);
    }

    protected void modificarPaneDinamicoCompra(String ruta, LocalDate fechaI, LocalDate fechaT) {
        paneDinamico.getChildren().clear();
        AnchorPane pane =null;
        try {
            FXMLLoader load = new FXMLLoader(getClass().getResource(ruta));
           pane = load.load();
           ReporteCompraProveedoresController repo =load.<ReporteCompraProveedoresController>getController();
            repo.modificarFechas(fechaI, fechaT);
            repo.llenarTablaCompra();
        }
        catch (IOException e) {
            System.out.println("fallo:" + e);
        }
        
        paneDinamico.getChildren().add(pane);
    }
    /**
     * abre un archivo fxml dado en la ruta
     * @param ruta contiene un string con el nombre del archivo fxml.
     * @return AnchorPane usado para ser agregado a otro Pane.
     */
    private AnchorPane obtenerFXML(String ruta) {
        try {
            URL paneProductoURL = getClass().getResource(ruta);
            AnchorPane pane = FXMLLoader.load(paneProductoURL);
            return pane;
        }
        catch (IOException e) {
            System.out.println("fallo:" + e);
            return null;
        }
    }

}
