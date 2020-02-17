package Service;

//import DAO.UserDAO;
import DAO.UserDAO;
import java.util.List;
import java.util.Objects;
import model.User;
import thang2.MainClass;

public class PermissionService {

    UserDAO userDao = new UserDAO();

    public boolean login(String name) {
        User u = userDao.getUserByUserName(name);
        if (Objects.isNull(u)) {
            return false;
        } else {
            MainClass.loginUser = u.getRole().toLowerCase();
            MainClass.idUserLogged = u.getId_user();
            return true;
        }
    }

    public String getRolesLoggedInUser() {
        return MainClass.loginUser;
    }

    public String getIDLoggedInUser() {
        return MainClass.idUserLogged;

    }

    public void logout() {
        MainClass.loginUser = "";
    }

    public boolean userHasRole(List<String> rolesAccess) {
        for (String role : rolesAccess) {
            if (getRolesLoggedInUser().equalsIgnoreCase(role)) {
                return true;
            }
        }
        
        System.out.println("User khong co quyen");
        return false;
    }
    
}
