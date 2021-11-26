package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.conexion.Conexion;
import modelos.entidades.Cliente;
import utilidades.ListaSimple;


public class ClienteDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public ListaSimple<Cliente> selectAll() throws SQLException{
        String sql = "SELECT * FROM cliente";
        return select(sql);
    }
    
    public ListaSimple<Cliente> selectAllTo(String atrib, String condicion) throws SQLException{
        String sql = "SELECT * FROM cliente WHERE " + atrib + "='" + condicion + "'";
        return select(sql);
    }
    
    public ListaSimple<Cliente> buscar(String dato) throws SQLException{
        String sql = "SELECT * FROM cliente WHERE dui_cliente like '" + dato + "%' ";
        return select(sql);
    }
    
    public ListaSimple<Cliente> selectDui(int dui) throws SQLException{
        String sql = "SELECT * FROM cliente WHERE dui_cliente ='" + dui + "'";
        return select(sql);
    }
    
    public boolean insertar(Cliente client) throws SQLException {
        String sql = "INSERT INTO cliente(dui_cliente, nom_cliente, ape_cliente, tel_cliente, email_cliente) VALUES (?,?,?,?,?)";
        return alterarRegistro(sql, client);
    }
    
    private boolean alterarRegistro (String sql, Cliente client) throws SQLException {
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, client.getDui());
            ps.setString(2, client.getNombre());
            ps.setString(3, client.getApellido());  
            ps.setString(4, client.getTelefono());
            ps.setString(5, client.getEmail());
            ps.execute();
            return true;
        } catch(Exception e) {      
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                ps.close();
            } catch (Exception e){
                
            }
            conectar.closeConexion(con);
        }
        return false;
    }
    
    public ListaSimple<Cliente> select(String sql) throws SQLException{
        ListaSimple<Cliente> lista = new ListaSimple();
        Cliente objeto = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                objeto = new Cliente();
                objeto.setDui(rs.getString("dui_cliente"));
                objeto.setNombre(rs.getString("nom_cliente"));
                objeto.setApellido(rs.getString("ape_cliente"));
                objeto.setTelefono(rs.getString("tel_cliente"));
                objeto.setEmail(rs.getString("email_cliente"));
                lista.insertar(objeto);
            }
        } catch(Exception e) {
             Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                ps.close();
            } catch (Exception ex){
                
            }
            conectar.closeConexion(con);
        }
        return lista;
    }
}
