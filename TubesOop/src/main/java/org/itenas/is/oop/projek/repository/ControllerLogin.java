package org.itenas.is.oop.projek.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.itenas.is.oop.projek.utils.ConnectionManager;
/**
 *
 * @author asus
 */
public class ControllerLogin {
    private ConnectionManager conMan;
    private Connection conn;

    public int login(String user, String pwd) {
        int stat = 0;
        conMan = new ConnectionManager();
        conn = conMan.logOn();

        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user);
            stmt.setString(2, pwd);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (user.equals(rs.getString("username")) && pwd.equals(rs.getString("password"))) {
                    stat = 1;
                } else {
                    stat = 0;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error login: " + ex.getMessage());
        }
        return stat;
    }

    public int register(String user, String pwd) {
        int stat = 0;
        String query = "INSERT INTO admin VALUES (?, ?)";
        conMan = new ConnectionManager();
        conn = conMan.logOn();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user);
            stmt.setString(2, pwd);
            stmt.executeUpdate();
            stat = 1;
        } catch (SQLException ex) {
            System.out.println("Error register: " + ex.getMessage());
        }
        return stat;
    }
}