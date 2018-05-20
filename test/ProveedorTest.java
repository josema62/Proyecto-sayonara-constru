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
    
     @Test
    public void pruebaAgregarProveedor()
    {
        boolean resultado = this.consulta.registrarProveedor("79.608.923-5","Kelogs", "kelogs@gmail.com",
                                                             "Nestle LTDA",
                                                             "Mackena 901",
                                                             "901823456",
                                                             "86734521",
                                                             true);
        assertTrue(resultado);
    }
    
    @Test
     public void pruebaModificarDatosProveedor()
     {
         boolean resultado = this.consulta.modificarDatosProveedor("81.221.989-9","Colun Del Sur",
                                                                   "ventascolun@outlook.com",
                                                                   "Av. Pedro Mackena 345",
                                                                   "89071234",
                                                                   "89876541",
                                                                   true);
         assertTrue(resultado);
     }
     
     @Test
     public void pruebaDarDeBajaProveedor()
     {
         boolean resultado = this.consulta.darDeBajaProveedor("76.432.692-k");
         assertTrue(resultado);
     }
     
     @Test
     public void pruebaObtenerDatosProveedor()
     {
         Proveedor proveedor = this.consulta.obtenerDatosProveedor("81.221.989-9");
         assertNotNull(proveedor);
         
         System.out.println(proveedor.getRut()+","+proveedor.getNombre()+","+
                            proveedor.getRazonSocial()+","+proveedor.getDireccion()+","+proveedor.getCorreo()+
                            ","+proveedor.getTelefonoObligatorio()+","+proveedor.getTelefonoOpcional());
         
     }
}
