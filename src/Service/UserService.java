package Service;

import Connection.ConnectDB;
import DAO.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserService {

    UserDAO userDao = new UserDAO();
    PermissionService permissionService = new PermissionService();
    Connection connect = ConnectDB.openconnect();
    ValidationService validationService = new ValidationService();

    public void insertUser(User u) {
        u.setIdParent(permissionService.getIDLoggedInUser());

    }

    public void updateUser(User u, int id) {

        if (validationService.validateUpdateUser(id)) {
            userDao.updateUserByID(u, id);
        } else {
            System.out.println("khong sua duoc");
            return;
        }
    }

    public void deleteUser(int id) {

        if (permissionService.isCreateUser(id)) {
            userDao.deleteUserByID(id);
        } else {
            System.out.println("khong phai la nguoi tao user nen khong co quyen xoa");
        }
    }
    

}
