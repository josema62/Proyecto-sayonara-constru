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
     
     @Test
     public void pruebaIsCadenaNumerosTrue()
     {
         boolean resultado = this.validacion.isCadenaNumeros("1 12 3 4 6");
         assertTrue(resultado);
     }
    
     @Test
     public void pruebaIsCadenaNumerosFalse()
     {
         boolean resultado = this.validacion.isCadenaNumeros("1 3 a 4");
         assertFalse(resultado);
     }
     
     @Test
     public void pruebaIsAlphaTrue()
     {
         boolean resultado = this.validacion.isAlpha("ariel andres");
         assertTrue(resultado);
     }
     
     @Test
     public void pruebaIsAlphaFalse()
     {
         boolean resultado = this.validacion.isAlpha("4riel 4andres");
         assertFalse(resultado);
     }
     
     @Test
     public void pruebaVerificarCantidadNumerosTrue()
     {
         boolean resultado = this.validacion.verificaCantidadNumeros("569984775979");
         assertTrue(resultado);
     }
     
     @Test
     public void pruebaVerificarCantidadNumerosFalse()
     {
         boolean resultado = this.validacion.verificaCantidadNumeros("5698477597990");
         assertFalse(resultado);
     }
     
     @Test
     public void pruebaVerificarCantidadNumerosProductoTrue()
     {
         boolean resultado = this.validacion.verificaCantidadNumerosCodigoProducto(
                 "1234567");
         assertTrue(resultado);
     }
     
      @Test
     public void pruebaVerificarCantidadNumerosProductoFalse()
     {
         boolean resultado = this.validacion.verificaCantidadNumerosCodigoProducto(
                 "12345600000");
         assertFalse(resultado);
     }
     
     
}


