/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Country;
import util.ConnectionJdbc;

/**
 *
 * @author User
 */
public class CountryDAO {
    Connection connection;
    public CountryDAO() throws Exception{
        connection = ConnectionJdbc.getConnection();
    }
    
    public void save(Country country) throws Exception {
        String SQL = "INSERT INTO COUNTRY(COUNTRY_ID, COUNTRY, LAST_UPDATE) VALUES (?,?,?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, country.getId());
            p.setString(2, country.getNome());
            java.util.Date d = new java.util.Date();
            java.sql.Date dt = new java.sql.Date (d.getTime());
            p.setDate(3, dt);
            p.execute();
            JOptionPane.showMessageDialog(null, "Salvo!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(Country country) throws Exception {
        String SQL = "UPDATE  COUNTRY SET COUNTRY=?, LAST_UPDATE=? WHERE COUNTRY_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, country.getNome());
            java.util.Date d = new java.util.Date();
            java.sql.Date dt = new java.sql.Date (d.getTime());
            p.setDate(2, dt);
            p.setInt(3, country.getId());
            p.execute();
            JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void delete(Country country) throws Exception {
        String SQL = "DELETE FROM COUNTRY WHERE COUNTRY_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, country.getId());
            p.execute();
            JOptionPane.showMessageDialog(null, "Deletado!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Country findById(int d) {
        return new Country();
    }

    public List<Country> findAll() throws Exception {
        // Lista para manter os valores do ResultSet
        List<Country> list = new ArrayList<>();
        Country objeto;
        String SQL = "SELECT * FROM COUNTRY";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            // 
            ResultSet rs = p.executeQuery();
            // Navega a classe e informa o valor do BD
            while (rs.next()) {
                // Instancia a classe e informa os valores do BD
                objeto = new Country();
                //objeto.setCountry_id(rs.getInt("country_id"));
                objeto.setNome(rs.getString("COUNTRY"));
                objeto.setId(rs.getInt("COUNTRY_ID"));
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
