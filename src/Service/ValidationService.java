/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ProductDAO;
import DAO.UserDAO;
import java.util.Arrays;
import java.util.Objects;
import model.Product;
import model.User;

/**
 *
 * @author nam-dep-trai
 */
public class ValidationService {

    private UserDAO userDao = new UserDAO();
    private PermissionService permissionService = new PermissionService();
    private ProductDAO productDAO = new ProductDAO();

    public boolean validateUser(User u) {
        User getUserByName = userDao.getUserByUserName(u.getNameUser());
        if (Objects.nonNull(getUserByName)) {
            System.out.println("Name da ton tai");
            return false;
        }

        if (permissionService.getRolesLoggedInUser().equals("admin")) {
            if (!u.getRole().equals("user")) {
                System.out.println("Admin khong duoc tao user co role super admin hoac admin");
                return false;
            }
        }
        return true;
    }

    public boolean validateUpdateUser(int id) {
        User user = userDao.getUserByID(id);
        if (Objects.nonNull(user)) {
            if (permissionService.userHasRole(Arrays.asList("superadmin"))) {
                return true;
            }
            if (permissionService.userHasRole(Arrays.asList("admin"))) {
                if (permissionService.isCreateUser(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validateDelete(int id) {
        User user = userDao.getUserByID(id);
        if (Objects.nonNull(user)) {
            if (permissionService.userHasRole(Arrays.asList("superadmin"))) {
                return true;

            }
            if (permissionService.userHasRole(Arrays.asList("admin"))) {
                if (permissionService.isCreateUser(id)) {
                    return true;
                }
            }
        }
        return false;
    }
    


}
