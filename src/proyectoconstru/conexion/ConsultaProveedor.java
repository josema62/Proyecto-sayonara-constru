package proyectoconstru.conexion;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelodedatos.Producto;
import modelodedatos.Proveedor;

/**
 *
 * @author Benjamin
 */
public class ConsultaProveedor extends Consulta {

    public ConsultaProveedor() {
        super();
    }

    /**
     * Este método se encarga de llamar al procedimiento almacenado en la base de datos que se encarga de registrar un proveedor.
     *
     * @param rut El rut del nuevo proveedor. DEBERÍA SER UN RUT VÁLIDO.
     * @param nombre El nombre del nuevo proveedor. NO DEBERÍA ser un nombre "en blanco".
     * @param correo El correo del nuevo proveedor. PUEDE SER un correo en blanco, pues es opcional.
     * @param razonSocial La razón social del nuevo proveedor. NO DEBERÍA estar "en blanco".
     * @param direccion La dirección del nuevo proveedor. NO DEBERÍA estar "en blanco".
     * @param telefonoObligatorio El número de teléfono de contacto del nuevo proveedor. NO DEBERÍA estar "en blanco".
     * @param telefonoOpcional Un número de teléfono de contacto opcional del nuevo proveedor. Puede estar "en blanco".
     * @param estado El estado del nuevo proveedor. DEBERÍA SER true, pues ésto indica la vigencia del servicio que le presta sl minimarket.
     *
     * @return true si el nuevo proveedor se logró registrar con éxito en la base de datos. false en caso contrario.
     */
    public boolean registrarProveedor(String rut, String nombre, String correo, String razonSocial, String direccion,
                                      String telefonoObligatorio, String telefonoOpcional, Boolean estado) {

        String consulta = "{CALL registrar_proveedor(?,?,?,?,?,?,?,?)}";

        boolean exitoConsulta = false;
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)) {
            declaracion.setString(1, rut);
            declaracion.setString(2, nombre);
            declaracion.setString(3, correo);
            declaracion.setString(4, razonSocial);
            declaracion.setString(5, direccion);
            declaracion.setString(6, telefonoObligatorio);
            declaracion.setString(7, telefonoOpcional);
            declaracion.setBoolean(8, estado);

            if(declaracion.execute()) exitoConsulta = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProveedor.class.getName()).log(Level.SEVERE,
                                                                    null, ex);
            return false;
        }
        
        return exitoConsulta;

    }

    /**
     * Este método llama al procedimiento almacenado en la base de datos que se encarga de modificar los datos
     * de un proveedor registrado.
     *
     * @param rut El rut el proveedor cuyos datos se van a modificar. El rut NO se modifica.
     * @param nombre El nuevo nombre del proveedor.
     * @param correo El nuevo correo del proveedor.
     * @param direccion La nueva direccion del proveedor.
     * @param telefonoObligatorio El nuevo número de teléfono del proveedor.
     * @param telefonoOpcional El nuevo número de teléfono opcional del proveedor.
     * @param estado El nuevo estado del proveedor.
     *
     * @return true si se logró con éxito modificar los datos del proveedor. false en caso contrario.
     */
    public boolean modificarDatosProveedor(String rut, String nombre, String correo, String direccion,
                                           String telefonoObligatorio, String telefonoOpcional, Boolean estado) {

        String consulta = "{CALL modificar_datos_de_proveedor(?,?,?,?,?,?,?)}";

        boolean exitoConsulta = false;
        
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)) {

            declaracion.setString(1, rut);
            declaracion.setString(2, nombre);
            declaracion.setString(3, correo);
            declaracion.setString(4, direccion);
            declaracion.setString(5, telefonoObligatorio);
            declaracion.setString(6, telefonoOpcional);
            declaracion.setBoolean(7, estado);

            exitoConsulta = declaracion.execute();

        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProveedor.class.getName()).log(Level.SEVERE,
                                                                    null, ex);

            return false;
        }
        
        return exitoConsulta;
    }

    /**
     * Este método llama al procedimiento almacenado en la base de datos que se encarga de dar de baja un proveedor.
     * Ésto es, cambiarle su estado para que indique que proveedor ya no presta servicios al minimarket.
     *
     * @param rut El rut del proveedor que se quiere dar de baja.
     *
     * @return true en caso de que logre dar de baja con éxito al proveedor. false en caso contrario.
     */
    public boolean darDeBajaProveedor(String rut) {
        String consulta = "{CALL dar_de_baja_proveedor(?)}";

        boolean exitoConsulta = false;
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)) {

            declaracion.setString(1, rut);

            exitoConsulta = declaracion.execute();
                

        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProveedor.class.getName()).log(Level.SEVERE,
                                                                    null, ex);
            return false;
        }
        
        return exitoConsulta;
       
    }

    /**
     * Este método se llama a un procedimiento almacenado en la base de datos para obtener los datos de un proveedor en particular.
     * Además, hace llamado a una función para obtener los productos que el proveedor tiene asociados.
     *
     * @param rut El rut del proveedor del cual se quiere obtener sus datos.
     *
     * @return Los datos del proveedor envueltos un objeto de tipo Proveedor. null si es que no se encontró el proveedor
     * o bien si hubo algun error.
     */
    public Proveedor obtenerDatosProveedor(String rut) {
        String consulta = "{CALL obtener_datos_proveedor(?)}";
        Proveedor proveedor = null;

        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)) {
            declaracion.setString(1, rut);

            ResultSet resultado = declaracion.executeQuery();

            //Atributos de cada proveedor listado.
            String rutProveedor = rut;
            String nombre;
            String correo;
            String razonSocial;
            String direccion;
            String telefonoObligatorio;
            String telefonoOpcional;
            Boolean estado;

            while (resultado.next()) {

                rutProveedor = resultado.getString("rut");
                nombre = resultado.getString("nombre");
                correo = resultado.getString("correo");
                razonSocial = resultado.getString("razonsocial");
                direccion = resultado.getString("direccion");
                telefonoObligatorio = resultado.getString("telefonoobligatorio");
                telefonoOpcional = resultado.getString("telefonoOpcional");
                estado = resultado.getBoolean("estado");

                ArrayList<Producto> productosProveedor = (ArrayList<Producto>) obtenerProductosProveedor(
                        rut);

                proveedor = new Proveedor(rutProveedor, nombre, correo, razonSocial,
                                          telefonoObligatorio, estado,
                                          productosProveedor, telefonoOpcional,
                                          direccion);

            }

            resultado.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return proveedor;
    }

    /**
     * Este método construye una lista con los proveedores que se hayan registrados en la base de datos.
     * Para ello obtiene los datos de todos los proveedores (y además los de los productos asociados a éstos)
     * haciendo llamadas a procedimientos almacenados en la base de datos.
     *
     * @return Una lista con los proveedores. null en caso de que no haya ningún proveedor registrado.
     */
    public List<Proveedor> listarProveedores() {

        String consulta = "{CALL obtener_ruts_proveedores()}";
        ArrayList<Proveedor> proveedores = null;
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)) {

            ResultSet rutsDeProveedores = declaracion.executeQuery();

            proveedores = new ArrayList<Proveedor>();
            Proveedor proveedor;

            //Mientras hayan proveedores en el listado
            while (rutsDeProveedores.next()) {

                proveedor = obtenerDatosProveedor(rutsDeProveedores.getString("rut"));
                proveedores.add(proveedor);

            }

            rutsDeProveedores.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return proveedores;
    }
    
    

    /**
     * Este método retorna una lista con objetos de tipo Producto, que contiene los productos asociados
     * al un determinado proveedor. Dicho proveedor está dado por su rut.
     *
     * @param rut El rut del proveedor cuyos productos se desea obtener.
     *
     * @return Una lista con los produtos que tiene asociado el proveedor. Null si es que el proveedor no tiene productos asociados en la BD.
     */
    public List<Producto> obtenerProductosProveedor(String rut) {

        String consulta = "{CALL obtener_productos_proveedor(?)}";
        ArrayList<Producto> productos = null;

        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)) {
            declaracion.setString(1, rut);
            ResultSet listado = declaracion.executeQuery();

            String codigoProducto;
            Producto producto;

            ConsultaProducto consultasProducto = new ConsultaProducto();

            productos = new ArrayList<>();

            while (listado.next()) {
                codigoProducto = listado.getString("fk_productofactura");
                producto = consultasProducto.obtenerDatosProducto(codigoProducto);
                productos.add(producto);

            }

            listado.close();

        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProducto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return productos;
    }
}
