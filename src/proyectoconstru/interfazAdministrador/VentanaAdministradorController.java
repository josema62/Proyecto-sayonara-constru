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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Roberto Ureta
 */
public class VentanaAdministradorController implements Initializable {

    
    
    
    @FXML
    private TreeView<String> raiz;
    @FXML
    private BorderPane paneDinamico;

    
    private TreeItem gestionProductos;
    private TreeItem gestionProveedores;
    private TreeItem gestionCajeros;
    private TreeItem anularBoleta;
    private TreeItem agregarFactura;
    private TreeItem generarReportes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paneDinamico = new BorderPane();
        TreeItem<String> root = new TreeItem<>("MENU");
        root.setExpanded(true);
        creadorGestionProductos();
        creadorGestionProveedores();
        creadorGestionCajeros();
        creadorVarios();
        creadorGeneradorReportes();
        
        root.getChildren().addAll(gestionProductos, gestionProveedores, agregarFactura, generarReportes, gestionCajeros, anularBoleta);
        raiz.setRoot(root);
        
        //raiz.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> determinarPanel(newValue.getValue()));
        
    }   
    
    private void creadorGestionProductos(){
        gestionProductos = new TreeItem<>("Gestion de Productos");
        TreeItem<String> agregarProducto = new TreeItem<>("Agregar Producto");
        TreeItem<String> listarProducto = new TreeItem<>("Listar Productos");
        gestionProductos.getChildren().addAll(agregarProducto,listarProducto);
    }
    
    private void creadorGestionProveedores(){
        gestionProveedores = new TreeItem<>("Gestion de Proveedores");
        TreeItem<String> agregarProveedor = new TreeItem<>("Agregar Proveedor");
        TreeItem<String> listarProveedor = new TreeItem<>("Listar Proveedor");
        gestionProveedores.getChildren().addAll(agregarProveedor,listarProveedor);
    }
    
    private void creadorGestionCajeros() {
        gestionCajeros = new TreeItem<>("Gestion de Cajeros");
        TreeItem<String> agregarCajero = new TreeItem<>("Agregar Cajero");
        TreeItem<String> listarCajeros = new TreeItem<>("Listar Cajeros");
        gestionCajeros.getChildren().addAll(agregarCajero,listarCajeros);
    }
    
    private void creadorVarios(){
        anularBoleta = new TreeItem<>("AnularBoleta");
        agregarFactura = new TreeItem<>("Agregar Factura");
        
    }
    
    private void creadorGeneradorReportes(){
        generarReportes = new TreeItem<>("Generar Reporte");
        TreeItem<String> compras = new TreeItem<>("Compras a proveedores");
        TreeItem<String> ventas = new TreeItem<>("Venta diaria");
        generarReportes.getChildren().addAll(compras,ventas);
    }

   /* private void determinarPanel(String value) {
        if(value.equals("Agregar Producto")){
            System.out.println("hola");
            try{
                URL paneProductoURL = getClass().getResource("AgregarProducto.fxml");
                AnchorPane paneProducto = FXMLLoader.load(paneProductoURL);
                paneDinamico.setCenter(paneProducto);
                
            }catch(IOException e){
                //
            }
        }
    }

    */
    
    
    
    
}
