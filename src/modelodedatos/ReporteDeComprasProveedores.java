/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelodedatos;

import java.util.ArrayList;

/**
 *
 * @author Benjamin
 */
public class ReporteDeComprasProveedores {
    
    private ArrayList<DetalleReporteDeComprasProveedores> detallesDeReporte;
    
    public ReporteDeComprasProveedores() {
        this.detallesDeReporte = new ArrayList<>();
    }
    
    /**
     * Agrega un detalle al reporte.
     * @param detalle El detalle que se desea agregar al reporte. 
     * @return true si es que se agregó el detalle con éxito. false en caso contrario.
     */
    public boolean agregarDetalleAReporte (DetalleReporteDeComprasProveedores detalle) {
        return detallesDeReporte.add(detalle);
    }
    
    public boolean estaVacio() {
        return this.detallesDeReporte.isEmpty();
    }
    
    public ArrayList<DetalleReporteDeComprasProveedores> obtenerDetallesDeReporte() {
        return this.detallesDeReporte;
    }
}
