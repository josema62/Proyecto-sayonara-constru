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
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelodedatos.Boleta;
import modelodedatos.DetalleProducto;

/**
 *
 * @author Gabriel
 */
public class ConsultaBoleta extends Consulta {

    /**
     * Registra la boleta en la base de datos.
     *
     * @param boleta La boleta que sera ingresada.
     *
     * @return true si la boleta se ingreso correctamente.
     */
    public boolean registrarBoleta(Boleta boleta) {
        String consulta = "{call registrar_boleta(?,?,?,?,?)}";
        try (CallableStatement consultaST
                               = conexion.prepareCall(consulta)) {
            consultaST.setInt(1, boleta.getCodigo());
            consultaST.setString(2, boleta.getFechaEmision());
            consultaST.setString(3, boleta.getHoraEmision());
            consultaST.setInt(4, boleta.getTotal());
            consultaST.setString(5, boleta.getRutCajero());
            consultaST.execute();
            insertarProductoBoleta(boleta.getCodigo(),
                                   boleta.getDetalleProductos());
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaBoleta.class.getName()).log(Level.SEVERE,
                                                                 null, ex);
            return false;
        }
        return true;
    }

    /**
     * Sirve para ver si existe la boleta en la base de datos.
     *
     * @param codigoBoleta El codigo de la boleta.
     *
     * @return true si existe, false en otro caso.
     */
    public boolean existeBoleta(int codigoBoleta) {
        String consulta = "select existe_boleta(?);";
        try (PreparedStatement consultaST
                               = conexion.prepareStatement(consulta)) {
            consultaST.setInt(1, codigoBoleta);
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
     * Obtiene el precio de un producto de la base de datos.
     *
     * @param codigoProducto Codigo del producto del cual se quiere tener el
     * precio.
     *
     * @return el precio del producto .
     */
    public int verificarPrecio(String codigoProducto) {
        String consulta = "select verificar_precio(?);";
        try (PreparedStatement consultaST
                               = conexion.prepareStatement(consulta)) {
            consultaST.setString(1, codigoProducto);
            ResultSet respuesta = consultaST.executeQuery();
            while (respuesta.next()) {
                int precio = respuesta.getInt(1);
                if(respuesta.wasNull()){
                    break;
                }
                return respuesta.getInt(1);
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
        return -1;
    }

    /**
     * Da de baja la boleta cambiando el estado.
     *
     * @param codigoBoleta El codigo de la boleta.
     *
     * @return true si la operacion se llevo a cabo sin problemas, false en caso
     * contrario.
     */
    public boolean darDeBajaBoleta(int codigoBoleta) {
        String consulta = "{call dar_de_baja_boleta(?)}";
        try (CallableStatement consultaST = conexion.prepareCall(consulta);) {
            consultaST.setInt(1, codigoBoleta);
            consultaST.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaBoleta.class.getName()).log(Level.SEVERE,
                                                                 null, ex);
            return false;
        }
        return true;
    }

    /**
     * Permite insertar los productos de la boleta
     *
     * @param codigoBoleta El codigo de la boleta insertada.
     * @param productos Los productos a insertar de esta boleta.
     *
     * @return true si la operacion se llevo a cabo sin problemas, false en caso
     * contrario.
     */
    private boolean insertarProductoBoleta(int codigoBoleta,
                                           HashMap<String, 
                                                   DetalleProducto> productos) {
        Iterator<Entry<String, DetalleProducto>> it = productos.entrySet()
                .iterator();
        String codigoProducto;
        DetalleProducto detalle;
        String consulta = "{call insertar_producto_boleta(?, ?, ?, ?, ?)}";
        try (CallableStatement consultaST
                               = conexion.prepareCall(consulta)) {
            while (it.hasNext()) {
                Entry<String, DetalleProducto> par = it.next();
                codigoProducto = par.getKey();
                detalle = par.getValue();
                
                consultaST.setInt(1, detalle.getCantidad());
                consultaST.setInt(2, detalle.getSubtotal());
                consultaST.setString(3, codigoProducto);
                consultaST.setInt(4, codigoBoleta);
                consultaST.setInt(5, detalle.getPrecioUnitario());
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

    /**
     * Obtiene el codigo de la ultima boleta agregada
     *
     * @return El codigo de la ultima boleta agregada.
     */
    public int obtenerCodigoUltimaBoleta() {
        String consulta = "select ultimo_id_boleta();";
        try (Statement consultaST
                       = conexion.createStatement()) {
            ResultSet respuesta = consultaST.executeQuery(consulta);
            while (respuesta.next()) {
                return respuesta.getInt(1);
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
        return -1;
    }

}
