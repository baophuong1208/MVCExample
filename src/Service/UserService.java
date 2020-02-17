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

    UserDAO userDao = new UserDAO();
    PermissionService permissionService = new PermissionService();
    Connection connect = ConnectDB.openconnect();
       
   
            
        

    public void insertUser(User u) {
        u.setId_parent(permissionService.getIDLoggedInUser());
        userDao.insertUser(u);   
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

