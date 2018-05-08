/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import proyectoconstru.conexion.ConsultaCajero;

/**
 *
 * @author Diego Matus
 */
public class CajeroTest {
    
    private ConsultaCajero consulta;
    public CajeroTest() {
        this.consulta = new ConsultaCajero();
    }
    
    public void agregarConsultaCajero()
    {
        this.consulta.insertarCajero(rut, nombre, contrasenia, telefono,
                                     direccion, true);
    }
    
   
    
}
