/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.conexion.Conexion;
import modelos.entidades.ClaveAcceso;
import modelos.entidades.Cliente;
import utilidades.ListaSimple;

/**
 *
 * @author Mario Zelaya
 */
public class ClaveAccesoDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public ListaSimple<ClaveAcceso> selectAll() throws SQLException{
        String sql = "SELECT * FROM clave_acceso";
        return select(sql);
    }
    
    public ListaSimple<ClaveAcceso> select(String sql) throws SQLException{
        ListaSimple<ClaveAcceso> lista = new ListaSimple();
        ClaveAcceso objeto = null;
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                objeto = new ClaveAcceso();
                objeto.setClave(rs.getString("clave"));
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
