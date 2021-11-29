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

    public ListaSimple<Registro> selectAll() throws SQLException {
        String sql = "select * from registro";
        return select(sql);
    }

    public ListaSimple<Registro> selectAllOrder() throws SQLException {
        String sql = "select * from registro ORDER BY fsalida_registro DESC LIMIT 10";
        return select(sql);
    }

    public ListaSimple<Registro> selectAllTo(String atributo, String condicion) throws SQLException {
        String sql = "select * from registro where " + atributo + "=" + condicion + "";
        return select(sql);
    }
    
    public ListaSimple<Registro> buscar(String dato) throws SQLException{
        String sql = "select * from registro where fentrada_registro like '" + dato + "%' or fsalida_registro like '" + dato + "%'";
        return select(sql);
    }
    
    public ListaSimple<Registro> selectId(int id) throws SQLException{
        String sql = "select * from registro where fk_num_habitacion =" + id;
        return select(sql);
    }

    public boolean insert(Registro obj) throws SQLException {
        String sql = "INSERT INTO registro(fentrada_registro, fsalida_registro, tipo_registro, estado_registro, total_registro, descuento_registro, deposito_registro, mora_registro, fk_dui_cliente, fk_num_habitacion, fk_id_usuario) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        return alterarRegistro(sql, obj);
    }

    private ListaSimple<Registro> select(String sql) throws SQLException {
        ListaSimple<Registro> lista = new ListaSimple();
        Registro obj = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                obj = new Registro();

                obj.setIdRegistro(rs.getInt("id_registro"));
                obj.setFechaEntrada(rs.getString("fentrada_registro"));
                obj.setFechaSalida(rs.getString("fsalida_registro"));
                obj.setTipo(rs.getString("tipo_registro"));
                obj.setEstado(rs.getInt("estado_registro"));
                obj.setTotal(rs.getDouble("total_registro"));
                obj.setDeposito(rs.getDouble("deposito_registro"));
                obj.setMora(rs.getDouble("mora_registro"));
                obj.setCliente(new Cliente(rs.getString("fk_dui_cliente")));
                obj.setHabitacion(new Habitacion(rs.getInt("fk_num_habitacion")));
                obj.setUsuario(new Usuario(rs.getInt("fk_id_usuario")));

                lista.insertar(obj);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {

            }
            conectar.closeConexion(con);
        }
        return lista;
    }

    public boolean delete(Registro obj) throws SQLException {
        String sql = "delete from registro where id_registro=" + obj.getIdRegistro();

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        } catch (SQLException e) {

        } finally {
            try {
                ps.close();
                Conexion.closeConexion(con);
            } catch (SQLException ex) {
            }
        }

        return false;
    }

    private boolean alterarRegistro(String sql, Registro obj) {
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, obj.getFechaEntrada());
            ps.setString(2, obj.getFechaSalida());
            ps.setString(3, obj.getTipo());
            ps.setInt(4, obj.getEstado());
            ps.setDouble(5, obj.getTotal());
            ps.setDouble(6, obj.getDescuento());
            ps.setDouble(7, obj.getDeposito());
            ps.setDouble(8, obj.getMora());
            ps.setString(9, obj.getCliente().getDui());
            ps.setInt(10, obj.getHabitacion().getNumHabitacion());
            ps.setInt(11, obj.getUsuario().getIdUsuario());

            ps.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                ps.close();
            } catch (Exception e) {

            }
            Conexion.closeConexion(con);
        }
        return false;
    }
    
    public Registro selectHuesped(Registro obj) throws SQLException{
     String sql = "SELECT id_registro, dui_cliente, email_cliente, fentrada_registro, fsalida_registro, descuento_registro, deposito_registro FROM registro r INNER JOIN cliente c ON r.fk_dui_cliente = c.dui_cliente WHERE fk_num_habitacion = " + obj.getHabitacion().getNumHabitacion() + " && r.estado_registro = 1";   
     Registro re = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                re = new Registro();
                Cliente h = new Cliente();
                
                h.setDui(rs.getString("dui_cliente"));
                h.setEmail(rs.getString("email_cliente"));
                re.setCliente(h);
                re.setIdRegistro(rs.getInt("id_registro"));
                re.setFechaEntrada(rs.getString("fentrada_registro"));
                re.setFechaSalida(rs.getString("fsalida_registro"));
                re.setDescuento(rs.getDouble("descuento_registro"));
                re.setDeposito(rs.getDouble("deposito_registro"));
                
            }
            Conexion.closeConexion(con);
        } catch (SQLException e) {
            System.out.println("ERROR " + e);
        }
        return re;
    }
        public boolean cargarMora(Registro obj) {
        String sql = "UPDATE registro SET mora_registro = ? WHERE fk_num_habitacion = '" + obj.getHabitacion().getNumHabitacion() + "'";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setDouble(1, obj.getMora());
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(HabitacionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally {
            Conexion.closeConexion(con);
        }
        return false;
    }
    public boolean FinRegistro(Registro obj) {
        String sql = "UPDATE registro SET estado_registro = ? WHERE id_registro = '" + obj.getIdRegistro() + "'";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setDouble(1, 0);
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(HabitacionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally {
            Conexion.closeConexion(con);
        }
        return false;
    }
}
