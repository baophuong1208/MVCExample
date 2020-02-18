package Service;

//import DAO.UserDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import model.Product;
import model.User;
import thang2.MainClass;

public class PermissionService {

    UserDAO userDao = new UserDAO();

    public boolean login(String name) {

        User u = userDao.getUserByUserName(name);
        if (Objects.isNull(u)) {
            return false;
        } else {
            MainClass.loginUser = u.getRole();
            MainClass.idUserLogged = u.getIdUser();
            return true;
        }
    }

    public String getRolesLoggedInUser() {
        return MainClass.loginUser;
    }

    public int getIDLoggedInUser() {
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

    public boolean isCreateUser(int id) {
        User u = userDao.getUserByID(id);
        if (u != null) {
            if (MainClass.idUserLogged == u.getIdParent()) {
                return true;
            }
        }
        return false;
    }

    public boolean isCreatProduct(int id) {
        if (userHasRole(Arrays.asList("admin"))) {
            ProductDAO pd = new ProductDAO();
            Product p = pd.getProductByID(id);
            if (p != null) {
                if (MainClass.idUserLogged == p.getUser().getIdUser()) {
                    return true;
                }
            }
        }
        return false;

    }

}
