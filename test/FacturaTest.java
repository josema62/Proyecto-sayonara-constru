


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import proyectoconstru.conexion.ConsultaFactura;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Ariel
 */
public class FacturaTest {
    private ConsultaFactura consulta;
    
    public FacturaTest()
    {
        this.consulta = new ConsultaFactura();
    }
    
    @Test
    public void consultaExisteFacturaTrue()
    {
        boolean resultado = this.consulta.existeFactura(2);
        assertTrue(resultado);
    }
    
    @Test
    public void consultaExisteFacturaFalse()
    {
        boolean resultado = this.consulta.existeFactura(-1);
        assertFalse(resultado);
    }
    
   
    
}
