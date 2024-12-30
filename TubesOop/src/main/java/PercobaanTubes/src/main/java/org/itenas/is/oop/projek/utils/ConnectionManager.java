package org.itenas.is.oop.projek.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author asus
 */
public class ConnectionManager {
     private Connection con;
    private String url = "jdbc:mysql://localhost:3306/percobaan_tubes";
    private String Driver = "com.mysql.cj.jdbc.Driver";
    private String Username = "root";
    private String Password = "basdat2024";
    
    public Connection logOn(){
        if(con == null){
            try{
                Class.forName(Driver).newInstance();
                con = DriverManager.getConnection(url,
                        Username,Password);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return con;
    }
    
    public Connection logOff(){
        try{
            con.close();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
}
