package org.itenas.is.oop.projek.repository;

import java.sql.Connection;
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
    Statement stmt;
    ResultSet rs;
    
    public int Login(String user, String pwd){
        int stat = 0;
        conMan = new ConnectionManager();
        conn = conMan.logOn();
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM registrasi_login where username = '" + user +"' and password ='" + pwd + "'");
            while(rs.next()){
                if(user.equals(rs.getString("username"))
                        && pwd.equals(rs.getString("password"))){
                    stat = 1;
                } else {
                    stat = 0;
                }
            }
            return stat;
        } catch (SQLException ex){
            return stat;
        }
    } 
    
    public int register(String user, String pwd){
        int stat = 0;
        String query = "INSERT INTO registrasi_login VALUES ('"+user+"', '"+pwd+"');";
        conMan = new ConnectionManager();
        conn = conMan.logOn();
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conMan.logOff();
        } catch (SQLException ex){
            System.out.println("error: " + ex.getMessage());
        }
        return stat;
    }
}
