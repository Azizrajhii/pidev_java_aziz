package org.example.services;

import org.example.entities.EquipeUser;
import org.example.utils.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceEquipeUser {
    private Connection conn;

    public ServiceEquipeUser() {
        conn = MyDatabase.getInstance().getConnection();
    }

    public void ajouter(EquipeUser equipeUser) throws SQLException {
        String sql = "INSERT INTO equipe_user(equipe_id, user_id) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, equipeUser.getEquipeId());
            ps.setInt(2, equipeUser.getUserId());
            ps.executeUpdate();
        }
    }

    public void modifier(EquipeUser ancien, EquipeUser nouveau) throws SQLException {
        String sql = "UPDATE equipe_user SET equipe_id=?, user_id=? WHERE equipe_id=? AND user_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, nouveau.getEquipeId());
            ps.setInt(2, nouveau.getUserId());
            ps.setInt(3, ancien.getEquipeId());
            ps.setInt(4, ancien.getUserId());
            ps.executeUpdate();
        }
    }

    public void supprimer(int equipeId, int userId) throws SQLException {
        String sql = "DELETE FROM equipe_user WHERE equipe_id=? AND user_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, equipeId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }

    public List<EquipeUser> getAll() throws SQLException {
        String sql = "SELECT * FROM equipe_user";
        List<EquipeUser> relations = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                EquipeUser relation = new EquipeUser(
                        rs.getInt("equipe_id"),
                        rs.getInt("user_id")
                );
                relations.add(relation);
            }
        }

        return relations;
    }
}
