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
import model.Actor;
import util.ConnectionJdbc;

/**
 *
 * @author User
 */
public class ActorDAO {
    Connection connection;
    public ActorDAO()throws Exception{
        connection = ConnectionJdbc.getConnection();
    }
    
    public void save(Actor actor) throws Exception {
        String SQL = "INSERT INTO ACTOR(ACTOR_ID, FIRST_NAME, LAST_NAME, LAST_UPDATE) VALUES (?,?,?,?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, actor.getId());
            p.setString(2, actor.getNome());
            p.setString(3, actor.getUltimonome());
            java.util.Date d = new java.util.Date();
            java.sql.Date dt = new java.sql.Date (d.getTime());
            p.setDate(4, dt);
            p.execute();
            JOptionPane.showMessageDialog(null, "Salvo!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(Actor actor) throws Exception {
        String SQL = "UPDATE  ACTOR SET FIRST_NAME=?, LAST_NAME=?, LAST_UPDATE=? WHERE ACTOR_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, actor.getNome());
            p.setString(2, actor.getUltimonome());
            java.util.Date d = new java.util.Date();
            java.sql.Date dt = new java.sql.Date (d.getTime());
            p.setDate(3, dt);
            p.setInt(4, actor.getId());
            p.execute();
            JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void delete(Actor actor) throws Exception {
        String SQL = "DELETE FROM ACTOR WHERE ACTOR_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, actor.getId());
            p.execute();
            JOptionPane.showMessageDialog(null, "Deletado!");
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Actor findById(int d) {
        return new Actor();
    }

    public List<Actor> findAll() throws Exception {
        // Lista para manter os valores do ResultSet
        List<Actor> list = new ArrayList<>();
        Actor objeto;
        String SQL = "SELECT * FROM ACTOR";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            // 
            ResultSet rs = p.executeQuery();
            // Navega a classe e informa o valor do BD
            while (rs.next()) {
                // Instancia a classe e informa os valores do BD
                objeto = new Actor();
                //objeto.setActor_id(rs.getInt("actor_id"));
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
