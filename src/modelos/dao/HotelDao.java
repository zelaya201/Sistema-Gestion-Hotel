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
import modelos.entidades.Hotel;
import utilidades.ListaCircularDoble;

public class HotelDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
   
    public HotelDao(){
        
    }
    
    public ListaCircularDoble<Hotel> selectAll() throws SQLException{
        String sql = "select * from hotel";
        return select(sql);
    }
    
    private ListaCircularDoble<Hotel> select(String sql) throws SQLException{
        ListaCircularDoble<Hotel> lista = new ListaCircularDoble();
        Hotel obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                obj = new Hotel();
                obj.setId_hotel(rs.getInt("id_hotel"));
                obj.setNom_hotel(rs.getString("nom_hotel"));
                obj.setDir_hotel(rs.getString("dir_hotel"));
                obj.setTel_hotel(rs.getString("tel_hotel"));
                
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
