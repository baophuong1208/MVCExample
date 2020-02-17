/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.UserDAO;
import java.util.Objects;
import model.User;

/**
 *
 * @author nam-dep-trai
 */
public class ValidationService {
    
    private UserDAO userDao = new UserDAO();
    private PermissionService permissionService = new PermissionService();
    public boolean validateUser(User u){
        User getUserByName = userDao.getUserByUserName(u.getName_user());
        if(Objects.nonNull(getUserByName)){
            System.out.println("Name da ton tai");
            return false;
        }
        
        if(permissionService.getRolesLoggedInUser().equals("admin")){
            if(!u.getRole().equals("user")){
                System.out.println("Admin khong duoc tao user co role super admin hoac admin");
                return false;
            }
        }
        
        return true;
    }
}
