/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.conexion.Conexion;
import modelos.entidades.Producto;
import utilidades.ListaSimple;

/**
 *
 * @author Luis Vaquerano
 */
public class ProductoDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ProductoDao() {
    }
    
    public ListaSimple<Producto> select(String sql) throws SQLException{
        ListaSimple<Producto> lista = new ListaSimple();
        Producto objeto = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                objeto = new Producto();

                objeto.setCodigo(rs.getString("cod_producto"));
                objeto.setDescripcion(rs.getString("descripcion_producto"));
                objeto.setPrecio(rs.getDouble("precio_producto"));

                lista.insertar(objeto);
            }
        } catch(Exception e) {
             Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                ps.close();
            } catch (Exception ex){
                
            }
            conectar.closeConexion(con);
        }
        return lista;
    }
    
    public ListaSimple<Producto> selectAll() throws SQLException{
        String sql = "SELECT * FROM producto";
        return select(sql);
    }
    
    public ListaSimple<Producto> selectAllTo(String atrib, String condicion) throws SQLException{
        String sql = "SELECT * FROM producto WHERE " + atrib + "='" + condicion + "'";
        return select(sql);
    }
    
    public ListaSimple<Producto> buscar(String dato) throws SQLException{
        String sql = "SELECT * FROM producto WHERE cod_producto like '" + dato + "%' or precio_producto like '" + dato + "%'";
        return select(sql);
    }
    
    public ListaSimple<Producto> selectCod(String cod) throws SQLException{
        String sql = "SELECT * FROM producto WHERE cod_producto ='" + cod + "'";
        return select(sql);
    }
    
    public boolean insertar(Producto product) throws SQLException {
        String sql = "INSERT INTO producto(cod_producto, descripcion_producto, precio_producto) VALUES (?,?,?)";
        return alterarRegistro(sql, product);
    }
    
    public boolean update(Producto obj) throws SQLException {
        String sql = "update producto set descripcion_producto = ?, precio_producto = ?  where cod_producto = '" + obj.getCodigo() + "'";
        return alterarRegistroUp(sql, obj);
    }
    
    private boolean alterarRegistroUp (String sql, Producto product) throws SQLException {
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, product.getDescripcion());
            ps.setDouble(2, product.getPrecio());
            
            ps.execute();
            return true;
        } catch(Exception e) {      
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                ps.close();
            } catch (Exception e){
                
            }
            conectar.closeConexion(con);
        }
        return false;
    }
    
    private boolean alterarRegistro (String sql, Producto product) throws SQLException {
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, product.getCodigo());
            ps.setString(2, product.getDescripcion());
            ps.setDouble(3, product.getPrecio());  

            ps.execute();
            return true;
        } catch(Exception e) {      
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                ps.close();
            } catch (Exception e){
                
            }
            conectar.closeConexion(con);
        }
        return false;
    }
    
    private boolean eliminar(Producto product) throws SQLException {
        String sql = "DELETE FROM producto WHERE cod_producto ='" + product.getCodigo() + "";
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        } catch (Exception e){
            
        } finally {
            try {
                ps.close();
                conectar.closeConexion(con);
            } catch (Exception e){
                
            }
            
        }
        return false;
    }
    
     public String getCodigo() throws SQLException {
        String sql = "SELECT MAX(cod_producto) AS mayor FROM producto";
        String cod = "";
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            cod = rs.getString("mayor");
            
        } catch (SQLException e){
            System.out.println(e);
        } finally {
            try {
                ps.close();
                conectar.closeConexion(con);
            } catch (Exception e){
                
            }
            
        }
        return cod;
    }
}