/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import modelodedatos.Cajero;
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
    
    /*@Test
    public void agregarConsultaCajero()
    {
          boolean resultado = this.consulta.insertarCajero("19.008.993-5", "Matias Acevedo", "6520", "(09)87534512",
                                     "Villa", true);
        assertTrue(resultado);
    }*/
    
    /*@Test 
    public void listarCajeros()
    {
       List<Cajero> lista = this.consulta.listarCajeros();
       //assertEquals(lista.size(),1);
       Cajero cajero = lista.get(0);
      
        
    }*/
    
   /* @Test
    public void buscarCajeros()
    {
        Cajero cajero =this.consulta.buscarCajero("11.496.730-0");
        assertNotNull(cajero);
    }*/
   
    @Test
    public void modificarCajero()
    {
        boolean modificado = this.consulta.modificarCajero("11.496.730-0", "banquito",
                                                           "123", "98",
                                                           "xuxa", false);
        assertTrue(modificado);
        
    }
    
}
