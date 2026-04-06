package org.example.services;

import org.example.entities.test;
import org.example.utils.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceTest implements IService<test>  {
    private Connection conn;
    public ServiceTest(){
        conn= MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(test test) throws SQLException {
        String requete = "INSERT INTO test(name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(requete)) {
            pstmt.setString(1, test.getName());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void modifier(test test) throws SQLException {
        String requete ="UPDATE test SET name=? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(requete)) {
            pstmt.setString(1, test.getName());
            pstmt.setInt(2, test.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String requete = "DELETE FROM test WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(requete)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<test> getAll() throws SQLException {
        String requete = "SELECT * FROM test";
        List<test> tests = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(requete)) {
            while (rs.next()) {
                tests.add(new test(rs.getString("name"), rs.getInt("id")));
            }
        }

        return tests;
    }
}
