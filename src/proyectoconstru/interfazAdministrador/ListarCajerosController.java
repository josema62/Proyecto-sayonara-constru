/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.interfazAdministrador;

import proyectoconstru.logicaCajero.Lista;
import proyectoconstru.logicaCajero.Cajero;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    
    private final ObservableList<Cajero> listaCajeros = FXCollections.observableArrayList();
    
    //private ConsultaCajero conexionbd = new ConsultaCajero();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setearValorCeldas();
        agregarCajeroEnLista();
        tablaCajero.setItems(listaCajeros);
    }    
    
    /**
     * obtiene los cajeros registrados desde la bd para agregarlos
     * a la ObservableList listaCajeros.
     */
    private void agregarCajeroEnLista(){
        /*List<Cajero> lista = conexionbd.listarCajeros();
        for (int i = 0; i < lista.size(); i++) {
            listaCajeros.add(lista.get(i));
        }*/
        for (int i = 0; i < Lista.size(); i++) {
            listaCajeros.add(Lista.get(i));
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
    
    }
}
