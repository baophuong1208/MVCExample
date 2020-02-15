
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDB {
    public static Connection openconnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thang2", "root", "kien12051998");
           return connection;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void closed(Connection connect, PreparedStatement ps, ResultSet rs){
          
           try {
                if(ps !=null&&ps.isClosed()){
               ps.close();
                }
                if(rs !=null&&rs.isClosed()){
                    rs.close();
                }
                if (connect !=null&&connect.isClosed()){
                    connect.close();
                }
                
           } catch (SQLException ex) {
               Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
           }
       
    }
    
}
