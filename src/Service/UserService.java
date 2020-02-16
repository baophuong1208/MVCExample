package Service;

import Connection.ConnectDB;
import DAO.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserService {

    UserDAO user = new UserDAO();
    User u = new User();
    PermissionService role = new PermissionService();
    Connection connect = ConnectDB.openconnect();
       
   
            
        

    public void insertUser(User u) { 
//        if (role.getRolesLoggedInUser().equalsIgnoreCase("user")) {
//            System.out.println("khong co quyen");
//        } 
        if(role.getRolesLoggedInUser().equalsIgnoreCase("superadmin")){
        System.out.println("nhap role");
        u.setRole(new Scanner(System.in).nextLine());
        u.setId_parent(role.getIDLoggedInUser());
        user.insertUser(u);
        }

        if (role.getRolesLoggedInUser().equalsIgnoreCase("admin")) {
            System.out.println("role = user");
            u.setRole("user");
            u.setId_parent(role.getIDLoggedInUser());
            user.insertUser(u);
        }
        
    }
    
        public boolean kiemTraTrung(String t, List a){
        for (int i=0; i<a.size(); i++){
            if(a.get(i).equals(t)){
                return true;
            }
            }
        return false;
        }
    }

