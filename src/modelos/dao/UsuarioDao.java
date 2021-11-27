/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelos.conexion.Conexion;
import modelos.entidades.Usuario;
import utilidades.ListaSimple;

/**
 *
 * @author Mario Zelaya
 */
public class UsuarioDao {
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public UsuarioDao(){
        
    }
    
    public ListaSimple<Usuario> selectAll() {
        String sql = "SELECT u.id_usuario, CONCAT_WS(' ',u.nom_usuario,u.ape_usuario) AS nom_completo, "
                + "u.tel_usuario, TIMESTAMPDIFF(YEAR, u.fnac_usuario, NOW()) AS edad, u.genero_usuario, u.nick_usuario, u.rol_usuario, u.clave_usuario, u.estado_usuario "
                + "FROM usuario u";
        return selectEspecifico(sql);
    }
    
    public ListaSimple<Usuario> selectAllTo(String atributo, String condicion) {
        String sql = "select * from usuario where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public ListaSimple<Usuario> buscar(String dato) {
        String sql = "SELECT u.id_usuario, CONCAT_WS(' ',u.nom_usuario,u.ape_usuario) AS nom_completo, "
                + "u.tel_usuario, TIMESTAMPDIFF(YEAR, u.fnac_usuario, NOW()) AS edad, u.genero_usuario, u.nick_usuario, u.rol_usuario, u.clave_usuario, u.estado_usuario "
                + "FROM usuario u WHERE nick_usuario like '" + dato + "%' OR nom_usuario like '" + dato + "%' OR ape_usuario like '" + dato + "%'";
        return selectEspecifico(sql);
    }
    
    public ListaSimple<Usuario> selectId(int id) {
        String sql = "select * from usuario where id_usuario=" + id;
        return select(sql);
    } 
    
    public boolean insert(Usuario obj){
        String sql = "insert into usuario(nom_usuario,ape_usuario,tel_usuario,fnac_usuario,genero_usuario,nick_usuario,rol_usuario,clave_usuario,estado_usuario)VALUES(?,?,?,?,?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }
    
    public boolean update(Usuario obj) {
        String sql = "update usuario set nom_usuario=?, ape_usuario=?, tel_usuario=?, fnac_usuario=?, genero_usuario=?, nick_usuario=?, rol_usuario=?, clave_usuario=?, estado_usuario=? where id_usuario=" + obj.getIdUsuario();
        return alterarRegistro(sql, obj);
    }

    private ListaSimple<Usuario> selectEspecifico(String sql){
        ListaSimple<Usuario> lista = new ListaSimple();
        Usuario obj = null;
        
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Usuario();
                
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setNombre(rs.getString("nom_completo"));
                obj.setTelefono(rs.getString("tel_usuario"));
                obj.setEdad(Integer.parseInt(rs.getString("edad")));
                obj.setGenero(rs.getString("genero_usuario"));
                obj.setNick(rs.getString("nick_usuario"));
                obj.setRol(rs.getString("rol_usuario"));
                obj.setClave(rs.getString("clave_usuario"));
                obj.setEstado(rs.getInt("estado_usuario"));
                
                lista.insertar(obj);
            }
            
        }catch(Exception e) {
            
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        
        return lista;
    }
    
    private ListaSimple<Usuario> select(String sql){
        ListaSimple<Usuario> lista = new ListaSimple();
        Usuario obj = null;
        
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Usuario();
                
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setNombre(rs.getString("nom_usuario"));
                obj.setApellido(rs.getString("ape_usuario"));
                obj.setTelefono(rs.getString("tel_usuario"));
                obj.setfNacimiento(rs.getString("fnac_usuario"));
                obj.setGenero(rs.getString("genero_usuario"));
                obj.setNick(rs.getString("nick_usuario"));
                obj.setClave(rs.getString("clave_usuario"));
                obj.setRol(rs.getString("rol_usuario"));
                obj.setEstado(rs.getInt("estado_usuario"));
                
                lista.insertar(obj);
            }
            
        }catch(Exception e) {
            
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        
        return lista;
    }
    
    private boolean alterarRegistro(String sql, Usuario obj){
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getApellido());
            ps.setString(3, obj.getTelefono());
            ps.setString(4, obj.getfNacimiento());
            ps.setString(5, obj.getGenero());
            ps.setString(6, obj.getNick());
            ps.setString(7, obj.getRol());
            ps.setString(8, obj.getClave());
            ps.setInt(9, obj.getEstado());
            
            ps.execute();
            
            return true;
        }catch(Exception e) {
            
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        return false; 
    }
}
