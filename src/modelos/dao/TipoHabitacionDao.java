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

public class TipoHabitacionDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
   
    public TipoHabitacionDao(){
        
    }
    
    public ListaSimple<TipoHabitacion> selectAll() throws SQLException{
        String sql = "select * from tipo_habitacion";
        return select(sql);
    }
    
    public ListaSimple<TipoHabitacion> selectAllTo(String atributo, String condicion) throws SQLException{
        String sql = "select * from tipo_habitacion where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public ListaSimple<TipoHabitacion> buscar(String dato) throws SQLException{
        String sql = "select * from tipo_habitacion where nombre_tipo like '" + dato + "%'";
        return select(sql);
    }
    
    public ListaSimple<TipoHabitacion> selectId(int id) throws SQLException{
        String sql = "select * from tipo_habitacion where id_tipo='" + id + "'";
        return select(sql);
    } 

    private ListaSimple<TipoHabitacion> select(String sql) throws SQLException{
        ListaSimple<TipoHabitacion> lista = new ListaSimple();
        TipoHabitacion obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new TipoHabitacion();
                obj.setIdTipo(rs.getInt("id_tipo"));
                obj.setNombre(rs.getString("nombre_tipo"));
                obj.setCantidad(rs.getInt("cantidad_tipo"));
                
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
}
