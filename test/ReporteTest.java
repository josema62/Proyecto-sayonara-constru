/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.LocalDate;
import java.time.Month;
import java.time.format.FormatStyle;
import java.util.Date;
import javafx.util.converter.LocalDateStringConverter;
import modelodedatos.DetalleReporteDeVentas;
import modelodedatos.ReporteDeVentas;
import org.junit.Test;
import static org.junit.Assert.*;
import proyectoconstru.conexion.ConsultaReporte;

/**
 *
 * @author Diego Matus
 */
public class ReporteTest {
    
    ConsultaReporte consulta;
    public ReporteTest() {
        this.consulta = new ConsultaReporte();
    }
    
    @Test
    public void pruebaObtenerReporteDeVentas()
    {
       
        ReporteDeVentas resultado = this.consulta.obtenerReporteDeVentas(LocalDate.of(2018, Month.MAY, 01));
        assertNotNull(resultado);
        for (DetalleReporteDeVentas detalle : resultado.obtenerDetallesDeReporte()) {
            System.out.println(detalle.getCodigoProducto()+","+detalle.getNombreProducto()+
                               ","+detalle.getCantidadDeProductosVendidos()+","+detalle.getSubtotal());
        }
    }
    
}
