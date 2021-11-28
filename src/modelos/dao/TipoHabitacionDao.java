package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.conexion.Conexion;
import modelos.entidades.TipoHabitacion;
import utilidades.ListaSimple;

public class TipoHabitacionDao {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public TipoHabitacionDao() {

    }

    public ListaSimple<TipoHabitacion> selectAll() throws SQLException{
        String sql = "select * from tipo_habitacion";
        return select(sql);
    }

//    public ListaCircularDoble<Tipo_Habitacion> selectAllTo(String atributo, String condicion) throws SQLException {
//        String sql = "select * from tipo_habitacion where " + atributo + "='" + condicion + "'";
//        return select(sql);
//    }
    
    public ArrayList<TipoHabitacion> selectAllTo(String atributo, String condicion) throws SQLException{
        String sql = "select * from tipo_habitacion where " + atributo + "='" + condicion + "'";
        return selectT(sql);
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

    public boolean insert(TipoHabitacion obj) throws SQLException {
        String sql = "INSERT INTO tipo_habitacion(nombre_tipo, cantidad_tipo) VALUES (?,?)";
        return alterarRegistro(sql, obj);
    }
    public boolean update(TipoHabitacion obj) throws SQLException{
        String sql = "UPDATE tipo_habitacion SET nombre_tipo = ?, cantidad_tipo = ? WHERE id_tipo = " + obj.getIdTipo();
        return alterarRegistro(sql, obj);
    }
    
    public boolean validarTipos(TipoHabitacion obj) throws SQLException{
        boolean existe = false;
        try {
            con = Conexion.getConexion();
            String sql = "SELECT nombre_tipo FROM tipo_habitacion WHERE nombre_tipo = '" + obj.getNombre() + "'";
            ps = con.prepareStatement(sql);
            rs = this.ps.executeQuery();
            while (rs.next()) {
                existe = true;
            }
            return existe;
        } catch (SQLException e) {
            System.out.println("ERROR EN VALIDAR TIPOS : " + e);
            return false;
        }finally {
            Conexion.closeConexion(con);
        }
    }
    
    private ArrayList<TipoHabitacion> selectT(String sql) throws SQLException {
        ArrayList<TipoHabitacion> lista = new ArrayList();
        TipoHabitacion obj = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                obj = new TipoHabitacion();
                obj.setIdTipo(rs.getInt("id_tipo"));
                obj.setNombre(rs.getString("nombre_tipo"));
                obj.setCantidad(rs.getInt("cantidad_tipo"));

                lista.add(obj);
            }

        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {

            }
            Conexion.closeConexion(con);
        }

        return lista;
    }

    private boolean alterarRegistro(String sql, TipoHabitacion obj) throws SQLException {
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

//            ps.setInt(1, obj.getId_tipo());
            ps.setString(1, obj.getNombre());
            ps.setInt(2, obj.getCantidad());

            ps.execute();

            return true;
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {

            }
            Conexion.closeConexion(con);
        }
        return false;
    }
    public boolean eliminar(TipoHabitacion obj){
        String sql = "DELETE FROM tipo_habitacion WHERE id_tipo = " + obj.getIdTipo();
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error en eliminar tipo de habitacion" + e);
            return false;
        }finally {
            try {
                ps.close();
                Conexion.closeConexion(con);
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
    }
}
