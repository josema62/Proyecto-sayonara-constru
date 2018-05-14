/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.HashMap;
import modelodedatos.Boleta;
import modelodedatos.DetalleProducto;
import org.junit.Test;
import static org.junit.Assert.*;
import proyectoconstru.conexion.ConsultaBoleta;

/**
 *
 * @author Diego Matus
 */
public class BoletaTest {
    ConsultaBoleta consulta;
    public BoletaTest() {
       this.consulta = new ConsultaBoleta();
    }
    
    
    public void pruebaAgregarBoleta()
    {  
        HashMap<String,DetalleProducto> detallesProducto = new HashMap<>();
        detallesProducto.put("3", new DetalleProducto("3","Cereal Estrellitas 500g", 2, 2190, 4380));
        detallesProducto.put("121375", new DetalleProducto("121375","Atun San Jose al agua", 1, 690, 690));
        
        Boleta boleta = new Boleta(5070,detallesProducto,true,"02-04-2018","12:30",15,"marcos","1234");
        boolean resultado = this.consulta.registrarBoleta(boleta);
    }
    
    
    @Test
    public void pruebasObtenerCodigoUltimoBoleta()
    {
        int resultado = this.consulta.obtenerCodigoUltimaBoleta();
        
        assertEquals(resultado, 15);
    }

    @Test
    public void consultaAgregarBoleta()
    {
       DetalleProducto detalle1 = new DetalleProducto(3,"Cereal Estrellitas 500g",2,2190,4380);
       DetalleProducto detalle2 = new DetalleProducto(349844,"Salchichas PF",3,610,1230);
       HashMap<String,DetalleProducto> muestras = new HashMap<>();
       muestras.put("349844", detalle2);
       muestras.put("3",detalle1);
       Boleta prueba = new Boleta(5610,muestras,true,"13-05-2018","23:51",123,"marcos","1234");
       
       boolean resultado = this.consulta.registrarBoleta(prueba);
       assertTrue(resultado);
    }
    
    @Test
    public void existeBoletaTrue()
    {
        boolean resultado = this.consulta.existeBoleta(1);
        assertTrue(resultado);
    }
    
    @Test
    public void existeBoletaFalse()
    {
        boolean resultado = this.consulta.existeBoleta(-1);
        assertFalse(resultado);
    }
    
    @Test
    public void consultaVerificarPrecioExiste()
    {
       long resultado = this.consulta.verificarPrecio("3");
       assertEquals(resultado,2190);
    } 
    
    @Test
    public void consultVerificarPrecioNoExiste()
    {
        long resultado = this.consulta.verificarPrecio("-2");
        assertEquals(-1,resultado);
    }
    
    @Test 
    public void consultaDarDeBajaBoleta()
    {
        boolean resultado = this.consulta.darDeBajaBoleta(2);
        assertTrue(resultado);
    }
    
}
