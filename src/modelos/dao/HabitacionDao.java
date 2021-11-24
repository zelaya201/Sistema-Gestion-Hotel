package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.conexion.Conexion;
import modelos.entidades.Habitacion;
import modelos.entidades.Hotel;
import modelos.entidades.TipoHabitacion;
import utilidades.ListaSimple;

public class HabitacionDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
   
    public HabitacionDao(){
        
    }
    
    public ListaSimple<Habitacion> selectAll() throws SQLException{
        String sql = "select * from habitacion";
        return select(sql);
    }
    
    public ListaSimple<Habitacion> selectAllTo(String atributo, String condicion) throws SQLException{
        String sql = "select * from habitacion where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public ListaSimple<Habitacion> buscar(String dato) throws SQLException{
        String sql = "select * from habitacion where id_habitacion like '" + dato + "%' or  num_habitacion like '" + dato + "%'";
        return select(sql);
    }
    
    public ListaSimple<Habitacion> selectId(int id) throws SQLException{
        String sql = "select * from habitacion where num_habitacion=" + id;
        return select(sql);
    } 
    
    public boolean insert(Habitacion obj) throws SQLException{
        String sql = "insert into habitacion(id_habitacion, num_habitacion, descripcion_habitacion, precio_habitacion, estado_habitacion, disposicion_habitacion, fk_id_tipo, fk_id_hotel)values(?,?,?,?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }

    private ListaSimple<Habitacion> select(String sql) throws SQLException{
        ListaSimple<Habitacion> lista = new ListaSimple();
        Habitacion obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Habitacion();

                obj.setNumHabitacion(rs.getInt("num_habitacion"));
                obj.setDescripcion(rs.getString("descripcion_habitacion"));
                obj.setPrecio(rs.getDouble("precio_habitacion"));
                obj.setEstado(rs.getInt("estado_habitacion"));
                obj.setDisposicion(rs.getString("disposicion_habitacion"));
                obj.setTipoHabitacion(new TipoHabitacion(rs.getInt("fk_id_tipo")));
                obj.setHotel(new Hotel(rs.getInt("fk_id_hotel")));
                
                lista.insertar(obj);
            }
             
            
        }catch(Exception e) {
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
    
    private boolean alterarRegistro(String sql, Habitacion obj) throws SQLException{
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, obj.getNumHabitacion());
            ps.setString(2, obj.getDescripcion());
            ps.setDouble(3, obj.getPrecio());
            ps.setInt(4, obj.getEstado());
            ps.setString(5, obj.getDisposicion());
            ps.setInt(6, obj.getTipoHabitacion().getIdTipo());
            ps.setInt(7, obj.getHotel().getIdHotel());
            
            ps.execute();
            
            return true;
        }catch(Exception e) {      
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                ps.close();
            } catch (Exception ex) {
                
            }
            conectar.closeConexion(con);
        }
        return false; 
    }
    
    public boolean delete(Habitacion obj) throws SQLException{
        String sql = "delete from habitacion where id_habitacion='" + obj.getNumHabitacion() + "'";
        
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
