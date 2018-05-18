/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import modelodedatos.Producto;
import org.junit.Test;
import modelodedatos.Proveedor;
import static org.junit.Assert.*;
import proyectoconstru.conexion.ConsultaProveedor;
/**
 *
 * @author Ariel
 */
public class ProveedorTest {
    
    private ConsultaProveedor consulta;
    
    public ProveedorTest()
    {
        this.consulta = new ConsultaProveedor();
    }
    
    @Test
    public void consultaListarProveedores()
    {
        List<Proveedor> resultado = this.consulta.listarProveedores();
        assertNotNull(resultado);
    }
    
    @Test
    public void consultaObtenerProductosProveedor()
    {
       List<Producto> resultado = this.consulta.obtenerProductosProveedor
                                                        ("76.432.692-k");
      
       assertNotNull(resultado);
    }
    
    @Test
    public void consultaDarDeBajaProveedor()
    {
        boolean resultado = this.consulta.darDeBajaProveedor("1");
        assertTrue(resultado);
    }
}
