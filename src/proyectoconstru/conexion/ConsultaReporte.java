/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.conexion;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelodedatos.DetalleReporteDeComprasProveedores;
import modelodedatos.DetalleReporteDeVentas;
import modelodedatos.ReporteDeComprasProveedores;
import modelodedatos.ReporteDeVentas;

/**
 *
 * @author Benjamin
 */
public class ConsultaReporte  extends Consulta {
    
    public ConsultaReporte() {
        super();
    }
    
    /**
     * Este método se encarga de generar el reporte de ventas a clientes a partir de los datos obtenidos a través
     * de un procedimiento almacenado en la base de datos.
     * Entrega una lista de los productos que fueron vendidos en una fecha específica.
     * Específicamente, para cada producto del listado contiene el codigo del producto, nombre, la cantidad de unidades que se vendieron esa fecha y el subtotal asociado a dicha cantidad de unidades.
     * @param fechaDeVentas La fecha respecto a la cual se quiere generar al reporte. Ésta se compara con la fecha de emisión de las boletas.
     * @return Un objeto de tipo ReporteDeVentas que contiene una lista de detalles. En caso de que la fecha entregada no hubiera habido ventas, entrega un reporte vacío. En caso de que haya habido algún error, retorna null.
     */
    public ReporteDeVentas obtenerReporteDeVentas(LocalDate fechaDeVentas) {
        
        String consulta = "{CALL obtener_ventas_a_clientes(?)}";
        ReporteDeVentas reporteDeVentas = null;
        
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)) {
            declaracion.setString(1, fechaDeVentas.toString());
            
            
            ResultSet detallesDeReporte = declaracion.executeQuery();
            reporteDeVentas = new ReporteDeVentas();
            
            String codigoProducto;
            String nombreProducto;
            int cantidad;
            int subtotal;
            
            DetalleReporteDeVentas detalle;
            
            while(detallesDeReporte.next()) {
                codigoProducto = detallesDeReporte.getString("codigo_producto");
                nombreProducto = detallesDeReporte.getString("nombre_producto");
                cantidad = detallesDeReporte.getInt("cantidad");
                subtotal = detallesDeReporte.getInt("subtotal");
                
                
                detalle = new DetalleReporteDeVentas(codigoProducto, nombreProducto, cantidad, subtotal);
                reporteDeVentas.agregarDetalleAReporte(detalle);
                
            }
            
            detallesDeReporte.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaReporte.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return reporteDeVentas;
    }
    
   /**
    * Este método se encarga de generar el reporte de compras a proveedores a partir de los datos obtenidos a través de 
    * un procedimiento almacenado en la base de datos.
    * Entrega una lista de compras a proveedores que se hicieron dentro de un rango de fechas determinado.
    * Para cada detalle del reporte, se indica el nombre del proveedor al que se compraron los productos, el número de factura a la que está asociada
    * la compra, la fecha emisión de la factura, el código del producto que fue comprado, el nombre del producto, el precio unitario, cantidad
    * y el subtotal.
    * @param fechaLimiteInferior Fecha inclusive desde la cual se empieza el rango de fechas.
    * @param fechaLimiteSuperior Fecha inclusive hasta donde termina el rango de fechas.
    * @return Un objeto de tipo ReporteDeComprasProveedores que contiene una lista de detalles. En caso de que dentro del rango de fechas no hubieran habido compras a proveedores, entrega un reporte vacío. En caso de que haya habido algún error, retorna null.
    */
    public ReporteDeComprasProveedores obtenerReporteDeComprasAProveedores(LocalDate fechaLimiteInferior, LocalDate fechaLimiteSuperior) {
        String consulta = "{CALL obtener_compras_a_proveedores(?,?)}";
        ReporteDeComprasProveedores reporteDeCompras = null;
        
        String nombreProveedor;
        int numeroFactura;
        String fechaEmisionFactura;
        String codigoProducto;
        String nombreProducto;
        int precioUnitario;
        int cantidadDeProductosComprados;
        int subtotal;
        
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)){
            
            
            declaracion.setString(1, fechaLimiteInferior.toString());
            declaracion.setString(2, fechaLimiteSuperior.toString());
            
            reporteDeCompras = new ReporteDeComprasProveedores();
            DetalleReporteDeComprasProveedores detalle;
            
            ResultSet detallesDeReporte = declaracion.executeQuery();
            
           
            while(detallesDeReporte.next()) {
                nombreProveedor = detallesDeReporte.getString("nombre_proveedor");
                numeroFactura = detallesDeReporte.getInt("nro_factura");
                fechaEmisionFactura = detallesDeReporte.getDate("fecha_emision_factura").toLocalDate().toString();
                codigoProducto = detallesDeReporte.getString("codigo_producto");
                nombreProducto = detallesDeReporte.getString("nombre_producto");
                precioUnitario = detallesDeReporte.getInt("precio_unitario");
                cantidadDeProductosComprados = detallesDeReporte.getInt("cantidad");
                subtotal = detallesDeReporte.getInt("subtotal");
                
                detalle = new DetalleReporteDeComprasProveedores(nombreProveedor, numeroFactura, fechaEmisionFactura, codigoProducto, nombreProducto, precioUnitario, cantidadDeProductosComprados, subtotal);
                reporteDeCompras.agregarDetalleAReporte(detalle);
            }
            
            detallesDeReporte.close();
            
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaReporte.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return reporteDeCompras;
    }
}
