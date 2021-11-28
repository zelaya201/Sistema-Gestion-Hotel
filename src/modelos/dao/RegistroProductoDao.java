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
import modelos.entidades.Registro;
import modelos.entidades.RegistroProducto;
import utilidades.ListaSimple;

/**
 *
 * @author Luis Vaquerano
 */
public class RegistroProductoDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public RegistroProductoDao() {
    }
    
    public ListaSimple<RegistroProducto> selectAll() throws SQLException{
        String sql = "SELECT * FROM registro_producto";
        return select(sql);
    }
    
    public ListaSimple<RegistroProducto> selectAllTo(String atributo, String condicion) throws SQLException{
        String sql = "select * from registro_producto where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public ListaSimple<RegistroProducto> buscar(String dato) throws SQLException {
        String sql = "SELECT * FROM registro_producto WHERE fk_id_registro like '" + dato + "%' OR fk_cod_producto like '" + dato +"%'";
        return select(sql);
    }
    
    public ListaSimple<RegistroProducto> selectIdRegistro(int id) throws SQLException {
        String sql = "SELECT * FROM registro_producto WHERE id_registro_producto = ";
        return select(sql);
    }
    
    public boolean insertar(RegistroProducto obj) throws SQLException{
        String sql = "INSERT INTO registro_producto(subtotal_registro_producto, cant_registro_producto, fk_id_registro, fk_cod_producto) VALUES (?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
//    public boolean delete(RegistroProducto obj) throws SQLException{
//        String sql = "DELETE FROM registro_producto WHERE ";
//    }
    private ListaSimple<RegistroProducto> select(String sql) throws SQLException{
        ListaSimple<RegistroProducto> lista = new ListaSimple();
        RegistroProducto obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new RegistroProducto();
                
                obj.setId_registro_producto(rs.getInt("id_registro_producto"));
                obj.setSubtotal(rs.getDouble("subtotal_registro_producto"));
                obj.setCantidad(rs.getInt("cant_registro_producto"));
                obj.setRegistro(new Registro(rs.getInt("fk_id_registro")));
                obj.setProducto(new Producto(rs.getString("fk_cod_producto")));
                
                lista.insertar(obj);
            }
        } catch(Exception e) {
             Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        return lista;
    }
    
    private boolean alterarRegistro (String sql, RegistroProducto obj) throws SQLException{
        try{
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setDouble(1, obj.getSubtotal());
            ps.setInt(2, obj.getCantidad());
            ps.setInt(3, obj.getRegistro().getIdRegistro());
            ps.setString(4, obj.getProducto().getCodigo());
            
            ps.execute();
            return true;
        } catch(Exception e) {      
            System.out.println(e + "ERROOOOOR");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        return false; 
    }
}
