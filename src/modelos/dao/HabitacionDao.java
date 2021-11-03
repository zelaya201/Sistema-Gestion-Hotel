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
import modelos.entidades.Tipo_Habitacion;
import utilidades.ListaCircularDoble;

public class HabitacionDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
   
    public HabitacionDao(){
        
    }
    
    public ListaCircularDoble<Habitacion> selectAll() throws SQLException{
        String sql = "select * from habitacion";
        return select(sql);
    }
    
    public ListaCircularDoble<Habitacion> selectAllTo(String atributo, String condicion) throws SQLException{
        String sql = "select * from habitacion where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public ListaCircularDoble<Habitacion> buscar(String dato) throws SQLException{
        String sql = "select * from habitacion where id_habitacion like '" + dato + "%' or  num_habitacion like '" + dato + "%'";
        return select(sql);
    }
    
    public ListaCircularDoble<Habitacion> selectId(int id) throws SQLException{
        String sql = "select * from habitacion where num_habitacion=" + id;
        return select(sql);
    } 
    
    public boolean insert(Habitacion obj) throws SQLException{
        String sql = "insert into habitacion(id_habitacion, num_habitacion, descripcion_habitacion, precio_habitacion, estado_habitacion, disposicion_habitacion, fk_id_tipo, fk_id_hotel)values(?,?,?,?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }

    private ListaCircularDoble<Habitacion> select(String sql) throws SQLException{
        ListaCircularDoble<Habitacion> lista = new ListaCircularDoble();
        Habitacion obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Habitacion();
                
                obj.setId_habitacion(rs.getString("id_habitacion"));
                obj.setNum_habitacion(rs.getInt("num_habitacion"));
                obj.setDescr_habitacion(rs.getString("descripcion_habitacion"));
                obj.setPrecio_habitacion(rs.getDouble("precio_habitacion"));
                obj.setEstado_habitacion(rs.getInt("estado_habitacion"));
                obj.setDispo_habitacion(rs.getString("disposicion_habitacion"));
                obj.setTipoH(new Tipo_Habitacion(rs.getInt("fk_id_tipo")));
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
            
            ps.setString(1, obj.getId_habitacion());
            ps.setInt(2, obj.getNum_habitacion());
            ps.setString(3, obj.getDescr_habitacion());
            ps.setDouble(4, obj.getPrecio_habitacion());
            ps.setInt(5, obj.getEstado_habitacion());
            ps.setString(6, obj.getDispo_habitacion());
            ps.setInt(7, obj.getTipoH().getId_tipo());
            ps.setInt(8, obj.getHotel().getId_hotel());
            
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
        String sql = "delete from habitacion where id_habitacion='" + obj.getId_habitacion() + "'";
        
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
