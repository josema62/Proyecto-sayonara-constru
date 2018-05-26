/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconstru.conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelodedatos.BD;
import modelodedatos.Producto;

/**
 *
 * @author Benjamin
 */
public class ConsultaProducto extends Consulta{
    

    /**
     * Crea la entidad con las funciones para la consulta del cajero
     *
     * @param conexion La conexion a la base de datos.
     */
    public ConsultaProducto() {
        super();
    }
    
    /**
     * Este método recibe los parámetros del producto a registrar en la base de datos.
     * @param codigo EL codigo del producto a registrar. NO DEBERÍA ser vacío.
     * @param nombre El nombre del producto a registrar. NO DEBERÍA ser vacío.
     * @param categoria La categoria del producto a registrar.
     * @param estado El estado del producto a registrar. DEBERÍA ser true
     * @param precio El precio del producto a registrar. DEBERÍA ser mayor a 0.
     * @param stockInicial El stock inicial del producto a registrar. NO DEBERÍA ser menor a 0.
     * @param stockMinimo El stock minimo del producto a registrar. NO DEBERÍA ser menor a 0.
     * @return true si se registró con éxito. false si no se registró debido a un error.
     */
    public boolean registrarProducto(String codigo, String nombre, String categoria, boolean estado, 
                                  int precio, int stockInicial, int stockMinimo) {
        String consulta = "{CALL registrar_producto(?,?,?,?,?,?,?)}";
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)){
            
         
            //Se le pasan los parámetros de entrada al procedimiento.
            declaracion.setString(1, codigo);
            declaracion.setString(2, nombre);
            declaracion.setString(3, categoria);
            declaracion.setBoolean(4, estado);
            declaracion.setInt(5, precio);
            declaracion.setInt(6, stockInicial);
            declaracion.setInt(7, stockMinimo);
            
            declaracion.executeQuery();
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProducto.class.getName()).log(Level.SEVERE,
                                                                   null, ex);
            return false;
        }
        
        return true;
    }
    
    /**
     * El sirve para obtener un listado de los productos registrados en la base de datos.
     * 
     * @return null si es que no hay productos que listar. Retorna el un objeto List con los productos que hay
     * en la registrados en caso de que hayan.
     */
    public List<Producto> listarProductos() {
        String consulta = "{CALL listar_productos()}";
        ArrayList<Producto> productos = null;
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)){
            
            ResultSet listado = declaracion.executeQuery();
            
            
            productos = new ArrayList<Producto>();
            Producto producto;
            String codigo;
            String nombre;
            String categoria;
            Boolean estado;
            int precio;
            int stockActual;
            int stockMinimo;
            
            //Mientras hayan productos en el listado
            while (listado.next()) {
                //Se crean los productos con los respectivos datos.
                codigo = listado.getString("codigo");
                nombre = listado.getString("nombre");
                categoria = listado.getString("categoria");
                estado = listado.getBoolean("estado");
                precio = listado.getInt("precio");
                stockActual = listado.getInt("stock");
                stockMinimo = listado.getInt("stockminimo");
                
                producto = new Producto(nombre, stockActual, codigo, categoria,
                                        estado, stockMinimo, precio);
                
                productos.add(producto);
            }
            
            listado.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProducto.class.getName()).log(Level.SEVERE,
                                                                   null, ex);
            return null;
        }
        return productos;
    }
    
    /**
     * Este método se encarga de dar de baja un producto registrado en la base de datos.
     * @param codigo El código del producto a dar de baja en la base de datos. DEBERÍA ser un código de algun producto registrado en la base de datos.
     * @return true si logró dar de baja al producto con éxito. false si no lo logró.
     */
    public boolean darDeBajaProducto(String codigo) {
        String consulta = "{CALL dar_de_baja_producto(?)}";
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)) {
            
            declaracion.setString(1, codigo);
            declaracion.executeQuery();
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProducto.class.getName()).log(Level.SEVERE,
                                                                   null, ex);
            
            return false;
        }
        return true;
    }
    
    /**
     * Este método recibe como parámetros los nuevos datos del producto. Es necesario aclarar que el codigo
     * que se le pasa como parámetro de entrada NO ES UN NUEVO CODIGO, sino que es el codigo del producto cuyos datos hay
     * que modificar.
     * @param codigo El codigo del producto a modificar
     * @param nombre El nuevo nombre del producto.
     * @param stockMinimo El nuevo stock mínimo del producto. NO DEBERÍA ser menor a 0.
     * @param categoria La nueva categoría del producto.
     * @param estado El nuevo estado del producto.
     * @param precio El nuevo precio del producto. DEBERÍA ser mayor a 0.
     * @return true si la modificación de los datos se realizó correctamente. false si es que hubo algún error y no se pudo concretar la consulta.
     */
    public boolean modificarDatosDeProducto(String codigo, String nombre, int stockMinimo, String categoria, boolean estado, int precio) {
        String consulta = "{CALL modificar_datos_de_producto(?,?,?,?,?,?)}";
        //nombre, stock mínimo, categoría, estado, stock, y precio
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)){        
            
            //Se pasan los parámetros al procedimiento.
            declaracion.setString(1, codigo);
            declaracion.setString(2, nombre);
            declaracion.setInt(3, stockMinimo);
            declaracion.setString(4, categoria);
            declaracion.setBoolean(5, estado);
            declaracion.setInt(6, precio);
            
            declaracion.executeQuery();

        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProducto.class.getName()).log(Level.SEVERE,
                                                                   null, ex);
            return false;
        }
            
        //Se retorna true en caso de que la consulta se haya realizado con éxito.
        return true;
        
    }
    
    /**
     * Este método verifica si existe algún producto ya registrado con el nombre o con el código que fue pasado
     * como parámetro de entrada a éste metodo. Tanto código como nombre de un producto deben ser únicos para
     * poder ser registrados en la base de datos.
     * @param codigo El codigo a comparar con los que hay registrados.
     * @param nombre El nombre a comparar con los que hay registrados.
     * @return true si hay algún producto registrado en la base de datos con el codigo o nombre que fueron pasados como párametros. false si no lo hay.
     */
    public boolean existeProducto(String codigo, String nombre) {
        
        boolean existe = false;
        //La rutina almacenada en la base de datos retornará verdadero o falso dependiendo si 
        //existe o no un producto ya registrado con ese código.
        String consulta = "{? = call existe_producto(?,?)}";
        try (CallableStatement declaracion = this.conexion.prepareCall(consulta)){
            
            declaracion.registerOutParameter(1, java.sql.Types.BOOLEAN);
            declaracion.setString(2, codigo);
            declaracion.setString(3, nombre);
            
            declaracion.execute();
            existe = declaracion.getBoolean(1);
            
            
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProducto.class.getName()).log(Level.SEVERE,
                                                                   null, ex);
        }
        
        return existe;
    }
    
    
    /**
     * A partir del código de un producto, obtiene los datos de un producto registrdo en la base de datos.
     * @param codigo El codigo del producto cuya información se debe extraer.
     * @return Un objeto de clase Producto que incluye la información correspondiente al producto. Retorna null en caso de que no encuentre el producto.
     */
    public Producto obtenerDatosProducto(String codigo) { 
        String consulta = "{CALL obtener_datos_producto(?)}";
        
        //El producto que se va a retornar.
        Producto producto = null;
        
        //Los atributos del producto que se va a retornar.
        String codigoProducto;
        String nombre;
        String categoria;
        Boolean estado;
        int precio;
        int stockActual;
        int stockMinimo;
            
        
	 
        try(CallableStatement declaracion = this.conexion.prepareCall(consulta)) {
            declaracion.setString(1, codigo);
            ResultSet listado = declaracion.executeQuery();
            
            //Se extraen los datos del resultado devuelto por la base de datos.
            if(listado.next()) {
                codigoProducto = listado.getString("codigo");
                nombre = listado.getString("nombre");
                categoria = listado.getString("categoria");
                estado = listado.getBoolean("estado");
                precio = listado.getInt("precio");
                stockActual = listado.getInt("stock");
                stockMinimo = listado.getInt("stockminimo");
                producto = new Producto(nombre, stockActual, codigoProducto, categoria, estado,
                                        stockMinimo, precio);
            }
            
            
            listado.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(ConsultaProducto.class.getName()).log(Level.SEVERE,
                                                                   null, ex);
        }
        
        return producto;
        
    }
}
