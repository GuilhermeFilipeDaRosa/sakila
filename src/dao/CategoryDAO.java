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
import model.Category;
import util.ConnectionJdbc;

/**
 *
 * @author User
 */
public class CategoryDAO {
    Connection connection;
    public CategoryDAO()throws Exception{
        connection = ConnectionJdbc.getConnection();
    }

 public void save(Category category) throws Exception {
        String SQL = "INSERT INTO CATEGORY(CATEGORY_ID, NAME, LAST_UPDATE) VALUES (?,?,?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, category.getId());
            p.setString(2, category.getNome());
            java.util.Date d = new java.util.Date();
            java.sql.Date dt = new java.sql.Date (d.getTime());
            p.setDate(3, dt);
            p.execute();
            JOptionPane.showMessageDialog(null, "Salvo!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(Category category) throws Exception {
        String SQL = "UPDATE  CATEGORY SET NAME=?, LAST_UPDATE=? WHERE CATEGORY_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, category.getNome());
            java.util.Date d = new java.util.Date();
            java.sql.Date dt = new java.sql.Date (d.getTime());
            p.setDate(2, dt);
            p.setInt(3, category.getId());
            p.execute();
            JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void delete(Category category) throws Exception {
        String SQL = "DELETE FROM CATEGORY WHERE CATEGORY_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, category.getId());
            p.execute();
            JOptionPane.showMessageDialog(null, "Deletado!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Category findById(int d) {
        return new Category();
    }

    public List<Category> findAll() throws Exception {
        // Lista para manter os valores do ResultSet
        List<Category> list = new ArrayList<>();
        Category objeto;
        String SQL = "SELECT * FROM CATEGORY";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            // 
            ResultSet rs = p.executeQuery();
            // Navega a classe e informa o valor do BD
            while (rs.next()) {
                // Instancia a classe e informa os valores do BD
                objeto = new Category();
                //objeto.setCategory_id(rs.getInt("category_id"));
                objeto.setNome(rs.getString("NAME"));
                objeto.setId(rs.getInt("CATEGORY_ID"));
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
