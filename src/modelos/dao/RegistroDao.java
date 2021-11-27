
package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.conexion.Conexion;
import modelos.entidades.Cliente;
import modelos.entidades.Habitacion;
import modelos.entidades.Registro;
import modelos.entidades.Usuario;
import utilidades.ListaSimple;

/**
 *
 * @author Luis Vaquerano
 */
public class RegistroDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public RegistroDao() {
    }
    
    public ListaSimple<Registro> selectAll() throws SQLException{
        String sql = "select * from registro";
        return select(sql);
    }
    
    public ListaSimple<Registro> selectAllOrder() throws SQLException{
        String sql = "select * from registro ORDER BY fsalida_registro DESC LIMIT 10";
        return select(sql);
    }
 
    public ListaSimple<Registro> selectAllTo(String atributo, String condicion) throws SQLException{
        String sql = "select * from registro where " + atributo + "=" + condicion + "";
        return select(sql);
    }
    
    public ListaSimple<Registro> buscar(String dato) throws SQLException{
        String sql = "select * from registro where id_regsitro like '" + dato + "'";
        return select(sql);
    }
    
    public ListaSimple<Registro> selectId(int id) throws SQLException{
        String sql = "select * from regsitro where id_registro" + id;
        return select(sql);
    }
    
    private ListaSimple<Registro> select(String sql) throws SQLException{
        ListaSimple<Registro> lista = new ListaSimple();
        Registro obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Registro();
                
                obj.setIdRegistro(rs.getInt("id_registro"));
                obj.setFechaEntrada(rs.getString("fentrada_registro"));
                obj.setFechaSalida(rs.getString("fsalida_registro"));
                obj.setTipo(rs.getString("tipo_registro"));
                obj.setEstado(rs.getInt("estado_registro"));
                obj.setTotal(rs.getDouble("total_registro"));
                obj.setDeposito(rs.getDouble("deposito_registro"));
                obj.setMora(rs.getDouble("mora_registro"));
                obj.setCliente(new Cliente (rs.getString("fk_dui_cliente")));
                obj.setHabitacion(new Habitacion(rs.getInt("fk_num_habitacion")));
                obj.setUsuario(new Usuario(rs.getInt("fk_id_usuario")));
                
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
    
    public boolean delete(Registro obj) throws SQLException{
        String sql = "delete from registro where id_registro=" + obj.getIdRegistro();
        
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        }catch(Exception e) {
            
        }finally{
            try {
                ps.close();
                conectar.closeConexion(con);
            } catch (Exception ex) {
            }
        }

        return false;
    }
}
