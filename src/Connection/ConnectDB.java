/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectDB {    
    private static Connection instance;
    
    private ConnectDB(){};
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            instance = DriverManager.getConnection("jdbc:mysql://localhost:3306/thang2", "root", "kien12051998");
        } catch (Exception e) {

        }
    }
    
    public static Connection openconnect(){
        return instance;
    }
    
}
