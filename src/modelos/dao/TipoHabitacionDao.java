package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.conexion.Conexion;
import modelos.entidades.Tipo_Habitacion;
import utilidades.ListaCircularDoble;

public class TipoHabitacionDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
   
    public TipoHabitacionDao(){
        
    }
    
    public ListaCircularDoble<Tipo_Habitacion> selectAll() throws SQLException{
        String sql = "select * from tipo_habitacion";
        return select(sql);
    }
    
    public ListaCircularDoble<Tipo_Habitacion> selectAllTo(String atributo, String condicion) throws SQLException{
        String sql = "select * from tipo_habitacion where " + atributo + "='" + condicion + "'";
        return select(sql);
    }
    
    public ListaCircularDoble<Tipo_Habitacion> buscar(String dato) throws SQLException{
        String sql = "select * from tipo_habitacion where nombre_tipo like '" + dato + "%'";
        return select(sql);
    }
    
    public ListaCircularDoble<Tipo_Habitacion> selectId(int id) throws SQLException{
        String sql = "select * from tipo_habitacion where id_tipo='" + id + "'";
        return select(sql);
    } 

    private ListaCircularDoble<Tipo_Habitacion> select(String sql) throws SQLException{
        ListaCircularDoble<Tipo_Habitacion> lista = new ListaCircularDoble();
        Tipo_Habitacion obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Tipo_Habitacion();
                obj.setId_tipo(rs.getInt("id_tipo"));
                obj.setNombre_tipo(rs.getString("nombre_tipo"));
                obj.setCantidad_tipo(rs.getInt("cantidad_tipo"));
                
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
