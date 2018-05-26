/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import modelodedatos.DetalleProducto;
import modelodedatos.Factura;
import org.junit.Test;
import static org.junit.Assert.*;
import proyectoconstru.conexion.ConsultaFactura;

/**
 *
 * @author Diego Matus, Ariel Cornejo
 */
public class FacturaTest {

    ConsultaFactura consulta;

    public FacturaTest() {
        this.consulta = new ConsultaFactura();
    }

    //@Test
    public void pruebaAgregarFacturas() {
        
        HashMap<String, DetalleProducto> detalleProductos =  new HashMap<String, DetalleProducto>();
        detalleProductos.put("1290", new DetalleProducto("192765", "Coca Cola", 10,
                                                      700, 70000));
        Factura factura = new Factura(1290,"2018-05-01", 13300, 70000,83300 ,"76.432.692-k",detalleProductos);
        boolean resultado = this.consulta.registrarFactura(factura);
        assertTrue(resultado);
    }

    @Test
    public void consultaExisteFacturaFalse()
    {
        boolean resultado = this.consulta.existeFactura(1111211,"78.567.834-6");
        assertFalse(resultado);
    }
    @Test
    public void consultaExisteFacturaTrue()
    {
        boolean resultado = this.consulta.existeFactura(1111111,"72.567.834-1");
        assertTrue(resultado);
    }
}