/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import modelodedatos.Producto;
import org.junit.Test;
import static org.junit.Assert.*;
import proyectoconstru.conexion.ConsultaProducto;
/**
 *
 * @author Ariel
 */
public class ProductoTest {
    
    private ConsultaProducto consulta;
    
    public ProductoTest()
    {
        this.consulta = new ConsultaProducto();
    }
    
    @Test
    public void consultaRegistrarProducto()
    {
        boolean resultado = this.consulta.registrarProducto("12456789012", "Leche Colun Sin Lactosa",
                                                            "Lacteos", true, 990,
                                                            50, 10);
        assertTrue(resultado);
    }
    
    @Test
    public void consultaListarProductos()
    {
        List<Producto> resultado = this.consulta.listarProductos();
        
        assertNotNull(resultado);
        System.out.println("Size" + resultado.size());
    }
    
    @Test
    public void consultaDarDeBajaProducto()
    {
        boolean resultado = this.consulta.darDeBajaProducto("123456789012");
        assertTrue(resultado);
    }
    
    @Test
    public void consultaModificarProducto()
    {
        boolean resultado=this.consulta.modificarDatosDeProducto("123456789012", 
                                                      "Leche Colun Sin Lactosa 1 L",
                                                           5,"Lacteos", true, 62,
                                                            100);
        assertTrue(resultado);
    }
    
    @Test
    public void consultaExisteProductoTrue()
    {
        boolean resultado1 = this.consulta.existeProducto("123456789012");
        assertTrue(resultado1);
        
       
    }
    
    @Test
    public void consultaExisteProductoFalse()
    {
        boolean resultado2 = this.consulta.existeProducto("-1");
        assertFalse(resultado2);
    }
    
}
