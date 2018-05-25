/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.conexion;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelodedatos.DetalleProducto;
import modelodedatos.Factura;

/**
 *
 * @author Gabriel
 */
public class ConsultaFactura extends Consulta{
    
    /**
     * Sirve para ver si existe una factura
     *
     * @param numero El numero de la factura.
     *
     * @return true si existe, false en otro caso.
     */
    public boolean existeFactura(int codigoFactura, String rut_proveedor){
        String consulta = "select existe_factura(?,?);";
        try (PreparedStatement consultaST
                               = conexion.prepareStatement(consulta)) {
            consultaST.setInt(1, codigoFactura);
            consultaST.setString(2, rut_proveedor);
            ResultSet respuesta = consultaST.executeQuery();
            while (respuesta.next()) {
                return respuesta.getBoolean(1);
            }
            respuesta.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaBoleta.class.getName()).log(Level.SEVERE,
                                                                 null, ex);
        }
        /*Para que no de error la funcion pero el respuesta.getBoolean(1) es 
        * el que devuelve true o false si esta o no la boleta con el codigo dado
         */
        return false;
    }
    
     /**
     * Registra la factura en la base de datos.
     *
     * @param factura La factura que sera ingresada.
     *
     * @return true si la factura se ingreso correctamente.
     */
    public boolean registrarFactura(Factura factura) {
        String consulta = "{call registrar_factura(?,?,?,?,?,?)}";
        try (CallableStatement consultaST
                               = conexion.prepareCall(consulta)) {
            consultaST.setInt(1, factura.getNumeroFactura());
            consultaST.setInt(2, factura.getValorNeto());
            consultaST.setString(3, factura.getFechaEmision());
            consultaST.setInt(4, factura.getValorIva());
            consultaST.setInt(5, factura.getTotal());
            consultaST.setString(6, factura.getRutProveedor());
            ResultSet resultado = consultaST.executeQuery();
            
            resultado.next();
            insertarProductoFactura(resultado.getInt(1), 
                                    factura.getDetalleProductos());
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaBoleta.class.getName()).log(Level.SEVERE,
                                                                 null, ex);
            return false;
        }
        return true;
    }

     /**
     * Permite insertar los productos de la factura
     *
     * @param numeroIngresoFactura El numero de la factura insertada.
     * @param productos Los productos a insertar de esta factura.
     *
     * @return true si la operacion se llevo a cabo sin problemas, false en caso
     * contrario.
     */
    private boolean insertarProductoFactura(int numeroIngresoFactura,
                                           HashMap<String, 
                                                   DetalleProducto> productos) {
        Iterator<Map.Entry<String, DetalleProducto>> it = productos.entrySet()
                .iterator();
        
        String codigoProducto;
        DetalleProducto detalle;
        String consulta = "{call insertar_producto_factura(?,?,?,?,?)}";
        try (CallableStatement consultaST
                               = conexion.prepareCall(consulta)) {
            while (it.hasNext()) {
                Map.Entry<String, DetalleProducto> par = it.next();
                codigoProducto = par.getKey();
                detalle = par.getValue();
                
                consultaST.setInt(1, detalle.getCantidad());
                consultaST.setInt(2, detalle.getPrecioUnitario());
                consultaST.setInt(3, detalle.getSubtotal());
                consultaST.setString(4, detalle.getCodigoProducto());
                consultaST.setInt(5, numeroIngresoFactura);
                consultaST.execute();
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaBoleta.class.getName()).log(
                    Level.SEVERE,
                    null, ex);
        }
        return true;
    }
}
