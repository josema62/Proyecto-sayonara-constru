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
    
    @Test
    public void agregarConsultaCajero()
    {
          boolean resultado = this.consulta.insertarCajero("19.008.993-5", "Matias Acevedo", "6520", "(09)87534512",
                                     "Villa Prat Los Aromos 45", true);
        assertTrue(resultado);
    }
    
   
    
}
