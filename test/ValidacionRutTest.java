/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import modelodedatos.ValidacionRut;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Ariel
 */
public class ValidacionRutTest {
    private ValidacionRut validador;
    
    public ValidacionRutTest()
    {
        this.validador = new ValidacionRut();
    }
    
    @Test
    public void pruebaValidacionRutTrue()
    {
        
        assertTrue(this.validador.validaRut("15890747-k"));
    }
    
    @Test
    public void pruebaVlidacionRutFalse()
    {
        
        assertFalse(this.validador.validaRut("19.299.833-6"));
    }
    
}
