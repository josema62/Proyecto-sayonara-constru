/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import proyectoconstru.conexion.ConsultaAutentificacion;
import org.junit.Test;
import static org.junit.Assert.*;
import proyectoconstru.conexion.ConsultaCajero;


/**
 *
 * @author Ariel
 */
public class AutentificacionTest {
    
    private ConsultaAutentificacion consulta;

    public AutentificacionTest() {
        this.consulta = new ConsultaAutentificacion();
    }
    
      @Test
    public void consultaObtenerContaseniaAdministrador()
    {
        String resultado = this.consulta.obtenerContaseniaAdministrador("33.844.984-4");
        assertNotNull(resultado);
    }
    
    @Test
    public void consultaObtenerContaseniaCajero()
    {
        String resultado = this.consulta.obtenerContraseniaCajero("11.496.730-0");
        assertNotNull(resultado);
    }
    
    
}
