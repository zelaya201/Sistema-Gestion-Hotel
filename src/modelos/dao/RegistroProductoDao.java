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
import modelos.entidades.Habitacion;
import modelos.entidades.Producto;
import modelos.entidades.Registro;
import modelos.entidades.RegistroProducto;
import modelos.entidades.Usuario;
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
        String sql = "select * from registro_producto where " + atributo + "=" + condicion + "";
        return select(sql);
    }
    
    public ListaSimple<RegistroProducto> buscar(String dato) throws SQLException {
        String sql = "SELECT * FROM registro_producto WHERE fk_id_registro like '" + dato + "%' OR fk_cod_producto like '" + dato +"%'";
        return select(sql);
    }
    
    public ListaSimple<RegistroProducto> selectIdRegistro(int id) throws SQLException {
        String sql = "SELECT * FROM registro_producto WHERE fk_id_registro = ";
        return select(sql);
    }
    
    public boolean insertar(RegistroProducto obj) throws SQLException{
        String sql = "insert into registro_producto(subtotal_registro_producto, cant_registro_producto, fk_id_registro, fk_cod_producto) values(?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    public boolean update(RegistroProducto obj) throws SQLException{
        String sql = "update registro_producto set subtotal_registro_producto=?, cant_registro_producto=?, fk_id_registro=?, fk_cod_producto=? where id_registro_producto=" + obj.getId_registro_producto();
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
    public double subTotalCompleto(Registro obj){
        String sql = "SELECT SUM( rp.subtotal_registro_producto ) AS subtotalCompleto FROM registro r INNER JOIN registro_producto rp ON r.id_registro = rp.fk_id_registro WHERE r.id_registro = '"+ obj.getIdRegistro() +"' GROUP BY fk_dui_cliente";
        double subtotal = 0;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
               subtotal = rs.getDouble("subtotalCompleto");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
            }
            Conexion.closeConexion(con);
        }
        return subtotal;
    }
    
    public ListaSimple<RegistroProducto> selectAllFinal(Habitacion obj){
        String sql = "SELECT p.cod_producto, r.id_registro, p.descripcion_producto, p.precio_producto, rp.cant_registro_producto, rp.subtotal_registro_producto FROM registro_producto rp INNER JOIN producto p ON rp.fk_cod_producto = p.cod_producto INNER JOIN registro r ON rp.fk_id_registro = r.id_registro WHERE r.fk_num_habitacion = '" + obj.getNumHabitacion() + "' && r.estado_registro = 1";
        ListaSimple<RegistroProducto> lista = new ListaSimple<>();
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                RegistroProducto re = new RegistroProducto();
                Registro registro = new Registro();
                Producto prod = new Producto();
                
                registro.setIdRegistro(rs.getInt("id_registro"));
                
                prod.setCodigo(rs.getString("cod_producto"));
                prod.setDescripcion(rs.getString("descripcion_producto"));
                prod.setPrecio(rs.getDouble("precio_producto"));
                
                re.setCantidad(rs.getInt("cant_registro_producto"));
                re.setSubtotal(rs.getDouble("subtotal_registro_producto"));
                
                re.setProducto(prod);
                re.setRegistro(registro);
                
                lista.insertar(re);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
            }
            Conexion.closeConexion(con);
        }
        return lista;
    }
}
