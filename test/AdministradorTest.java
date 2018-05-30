/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.Test;
import static org.junit.Assert.*;
import proyectoconstru.conexion.ConsultaAdministrador;
/**
 *
 * @author Ariel
 */
public class AdministradorTest {
    private ConsultaAdministrador consulta;
    
    
    public AdministradorTest()
    {
        this.consulta = new ConsultaAdministrador();
    }
    
    @Test
    public void pruebaConectarAdministrador()
    {
        this.consulta.conectarAdministrador("19084004-2");
        assertTrue(this.consulta.estaAdministradorConectado("19084004-2"));
    }
    
    @Test
    public void pruebaDesconectarAdmnistrador()
    {
        this.consulta.desconectarAdministrador("19084004-2");
        assertFalse(this.consulta.estaAdministradorConectado("19084004-2"));
    }
}
