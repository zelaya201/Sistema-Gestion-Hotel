package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.conexion.Conexion;
import modelos.entidades.Hotel;
import utilidades.ListaSimple;

public class HotelDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
   
    public HotelDao(){
        
    }
    
    public ListaSimple<Hotel> selectAll() throws SQLException{
        String sql = "select * from hotel";
        return select(sql);
    }
    
    public boolean update(Hotel obj) throws SQLException {
        String sql = "update hotel set nom_hotel = ?, dir_hotel =?, tel_hotel = ? where id_hotel = " + obj.getIdHotel();
        return alterarRegistro(sql, obj);
    }
    
    private ListaSimple<Hotel> select(String sql) throws SQLException{
        ListaSimple<Hotel> lista = new ListaSimple();
        Hotel obj = null;
        
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Hotel();
                obj.setIdHotel(rs.getInt("id_hotel"));
                obj.setNombre(rs.getString("nom_hotel"));
                obj.setDireccion(rs.getString("dir_hotel"));
                obj.setTelefono(rs.getString("tel_hotel"));
                
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
    
    private boolean alterarRegistro(String sql, Hotel obj) throws SQLException{
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTelefono());

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
}
