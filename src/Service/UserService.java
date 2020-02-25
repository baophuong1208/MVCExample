package Service;

import Connection.ConnectDB;
import DAO.UserDAO;
import java.sql.Connection;
import model.User;

public class UserService {

    UserDAO userDao = new UserDAO();
    PermissionService permissionService = new PermissionService();
    Connection connect = ConnectDB.openconnect();
    ValidationService validationService = new ValidationService();

    public void insertUser(User u) {
        u.setIdParent(permissionService.getLoggedInUserID());

    }

    public void updateUser(User u, int idUser) {

        if (validationService.LoggedInUserHasPermissionOnUser(idUser)) {
            userDao.updateUserByID(u, idUser);
        } else {
            System.out.println("khong sua duoc");
            return;
        }
    }

    public void deleteUser(int idUser) {
        if(validationService.LoggedInUserHasPermissionOnUser(idUser)){
            userDao.deleteUserByID(idUser);
        } else {
            System.out.println("khong phai la nguoi tao user nen khong co quyen xoa");
        }
    }
    

}
