package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.City;
import util.ConnectionJdbc;

/**
 *
 * @author User
 */
public class CityDAO {
    Connection connection;
    public CityDAO()throws Exception{
        connection = ConnectionJdbc.getConnection();
    }
    
    public void save(City city) throws Exception {
        String SQL = "INSERT INTO CITY(ACTOR_ID, FIRST_NAME, LAST_NAME, LAST_UPDATE) VALUES (?,?,?,?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, city.getId());
            p.setString(2, city.getNome());
            p.setString(3, city.getUltimonome());
            java.util.Date d = new java.util.Date();
            java.sql.Date dt = new java.sql.Date (d.getTime());
            p.setDate(4, dt);
            p.execute();
            JOptionPane.showMessageDialog(null, "Salvo!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(City city) throws Exception {
        String SQL = "UPDATE  ACTOR SET FIRST_NAME=?, LAST_NAME=?, LAST_UPDATE=? WHERE ACTOR_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, city.getNome());
            p.setString(2, city.getUltimonome());
            java.util.Date d = new java.util.Date();
            java.sql.Date dt = new java.sql.Date (d.getTime());
            p.setDate(3, dt);
            p.setInt(4, city.getId());
            p.execute();
            JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void delete(City city) throws Exception {
        String SQL = "DELETE FROM ACTOR WHERE ACTOR_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, city.getId());
            p.execute();
            JOptionPane.showMessageDialog(null, "Deletado!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public City findById(int d) {
        return new City();
    }

    public List<City> findAll() throws Exception {
        // Lista para manter os valores do ResultSet
        List<City> list = new ArrayList<>();
        City objeto;
        String SQL = "SELECT * FROM ACTOR";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            // 
            ResultSet rs = p.executeQuery();
            // Navega a classe e informa o valor do BD
            while (rs.next()) {
                // Instancia a classe e informa os valores do BD
                objeto = new City();
                //objeto.setCity_id(rs.getInt("city_id"));
                objeto.setNome(rs.getString("FIRST_NAME"));
                objeto.setId(rs.getInt("ACTOR_ID"));
                objeto.setUltimonome(rs.getString("LAST_NAME"));
                objeto.setUltimatualizacao(rs.getString("LAST_UPDATE"));
                // Inclui na lista
                list.add(objeto);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        // Retorna a lista
        return list;
    }
}
