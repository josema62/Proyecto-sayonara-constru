/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import modelodedatos.ValidacionCampo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego Matus
 */
public class ValidacionCampoTest {
    ValidacionCampo validacion;
    public ValidacionCampoTest() {
        this.validacion = new ValidacionCampo();
    }
    
    @Test
    public void pruebaCampoVacioTrue()
    {
        boolean resultado = this.validacion.campoVacio("");
        assertTrue(resultado);
    }
    
    @Test
    public void pruebaCampoVacioFalse()
    {
        boolean resultado = this.validacion.campoVacio("1");
        assertFalse(resultado);
    }
    
    @Test
    public void pruebaIsEmailTrue()
    {
        boolean resultado = this.validacion.isEmail("diego@gmail.com");
        assertTrue(resultado);
    }
    
    @Test
    public void pruebaIsEmailFalse()
    {
        boolean resultado = this.validacion.isEmail("diejjkjiu.@gmail.cl9");
        assertFalse(resultado);
    }
    
    @Test
    public void pruebaIsNumeroTrue()
    {
        boolean resultado = this.validacion.isNumeros("1234560");
        assertTrue(resultado);
    }
    
    @Test
     public void pruebaIsNumeroFalse()
    {
        boolean resultado = this.validacion.isNumeros("12jk[34 56-0");
        assertFalse(resultado);
    }
    
}


