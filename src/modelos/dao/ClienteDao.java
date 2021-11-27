
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

    public ListaSimple<Cliente> selectAll() throws SQLException {
        String sql = "SELECT * FROM cliente";
        return select(sql);
    }

    public ListaSimple<Cliente> selectAllTo(String atrib, String condicion) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE " + atrib + "='" + condicion + "'";
        return select(sql);
    }

    public ListaSimple<Cliente> buscar(String dato) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE dui_cliente like '" + dato + "%' ";
        return select(sql);
    }

    public ListaSimple<Cliente> selectDui(int dui) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE dui_cliente ='" + dui + "'";
        return select(sql);
    }

    public boolean insertar(Cliente client) throws SQLException {
        String sql = "INSERT INTO cliente(dui_cliente, nom_cliente, ape_cliente, tel_cliente, email_cliente) VALUES (?,?,?,?,?)";
        return alterarRegistro(sql, client);
    }

    public boolean insert(Cliente obj) throws SQLException {
        String sql = "INSERT INTO cliente(dui_cliente, nom_cliente, ape_cliente, tel_cliente, email_cliente) VALUES (?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }

    public boolean update(Cliente obj) throws SQLException {
        String sql = "UPDATE cliente SET nom_cliente=?, ape_cliente=?, tel_cliente=?, email_cliente= ? WHERE dui_cliente = '" + obj.getDui() + "'";
        return alterarRegistro(sql, obj);
    }

    private ListaSimple<Cliente> select(String sql) throws SQLException {
        
        ListaSimple<Cliente> lista = new ListaSimple<>();
        Cliente obj = null;
        
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                obj = new Cliente();

                obj.setDui(rs.getString("dui_cliente"));
                obj.setNombre(rs.getString("nombre_completo"));
                obj.setApellido(rs.getString("ape_cliente"));
                obj.setTelefono(rs.getString("tel_cliente"));
                obj.setEmail(rs.getString("email_cliente"));

                lista.insertar(obj);
            }

        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }
        
        return lista;
    }

    private boolean alterarRegistro(String sql, Cliente obj) throws SQLException {
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, obj.getDui());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getApellido());
            ps.setString(4, obj.getTelefono());
            ps.setString(5, obj.getEmail());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("ERROR EN alterarRegistro" + e);
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
            }
            Conexion.closeConexion(con);
        }
        return false;
    }

    public boolean delete(Cliente obj) throws SQLException {
        String sql = "DELETE FROM cliente WHERE dui_cliente = '" + obj.getDui() + "'";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException e) {
        } finally {
            try {
                ps.close();
                Conexion.closeConexion(con);
            } catch (Exception e) {
            }
        }
        return false;
    }

}
