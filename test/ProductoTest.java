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
 * @author Ariel,Diego Matus
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
        boolean resultado=this.consulta.modificarDatosDeProducto("901511", "Yogurt Nestle 50 g",10,"Lacteos", true, 220);
        assertTrue(resultado);
    }
    
    @Test
    public void consultaExisteProductoTrue()
    {
        boolean resultado1 = this.consulta.existeProducto("123456789012","Leche Colun Sin Lactosa");
        assertTrue(resultado1);
        
       
    }
    
    @Test
    public void consultaExisteProductoFalse()
    {
        boolean resultado2 = this.consulta.existeProducto("123456789012","Leche");
        assertFalse(resultado2);
    }
    
}
