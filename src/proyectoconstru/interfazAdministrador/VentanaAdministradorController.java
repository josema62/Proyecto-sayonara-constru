/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
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
        
        //Se agrega un Listener al TreeView raiz para obtener lo que el usuario esta marcando.
        raiz.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                determinarPanel(newValue.getValue()));

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
        varios.getChildren().addAll(agregarFactura, anularBoleta);

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
            modificarPaneDinamico("ListarProductos.fxml");
        }
        if (value.equals("Agregar Proveedor")) {
            modificarPaneDinamico("FormularioAgregarProveedor.fxml");
        }
        if (value.equals("Listar Proveedor")) {
            modificarPaneDinamico("ListarProveedores.fxml");
        }
        if (value.equals("Agregar Factura")) {
            modificarPaneDinamico("FormularioAgregarFactura.fxml");
        }
        if (value.equals("Agregar Cajero")) {
            modificarPaneDinamico("FormularioAgregarCajero.fxml");
        }
        if (value.equals("Listar Cajeros")) {
            modificarPaneDinamico("ListarCajeros.fxml");
        }
        if (value.equals("Compras a proveedores")) {
            mostrarStageSecundario("CompraProveedores.fxml");
        }
        if (value.equals("Venta Diaria")) {
            mostrarStageSecundario("VentaDiaria.fxml");
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
        Stage stage2 = new Stage();
        Parent root = obtenerFXML(ruta);
        Scene scene = new Scene(root);
        stage2.setScene(scene);
        stage2.showAndWait();
        stage2.close();
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
