/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.logicaCajero;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Este objeto es provisorio, lo ocupe para almacenar los cajeros 
 * que iban siendo agregados, cuando se integre todo hay que eliminarlo.
 * @author Roberto Ureta
 */
public class  Lista {
    private static ObservableList<Cajero> data = FXCollections.observableArrayList();

    public static boolean add(Cajero e) {
        return data.add(e);
    }

    public static Cajero get(int index) {
        return data.get(index);
    }

    public static int size() {
        return data.size();
    }
    
    
    
}
