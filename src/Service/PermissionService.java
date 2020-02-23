package Service;

//import DAO.UserDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import model.Orders;
import model.Product;
import model.User;
import thang2.MainClass;

public class PermissionService {

    private UserDAO userDao = new UserDAO();
    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();

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

    public String getLoggedInUserRoles() {
        return MainClass.loginUser;
    }

    public int getLoggedInUserID() {
        return MainClass.idUserLogged;

    }

    public void logout() {
        MainClass.loginUser = "";
    }

    public boolean loggedInUserHasRole(List<String> rolesAccess) {

        for (String role : rolesAccess) {
            if (getLoggedInUserRoles().equalsIgnoreCase(role)) {
                return true;
            }
        }

        System.out.println("User khong co quyen");
        return false;
    }

    public boolean isCreatedByLoggedInUser(int id) {
        User u = userDao.getUserByID(id);
        if (MainClass.loginUser.equalsIgnoreCase("superadmin")) {
        } else {
            return true;
        }
        if (u != null) {
            if (MainClass.idUserLogged == u.getIdParent()) {
                return true;
            }
        }
        return false;
    }

    public boolean productIsCreatedByLoggedInUser(int id) {
        Product product = productDAO.getProductByID(id);
        if (MainClass.loginUser.equalsIgnoreCase("superadmin")) {
            return true;
        }
        if (loggedInUserHasRole(Arrays.asList("admin"))) {
            if (product != null) {
                if (MainClass.idUserLogged == product.getUser().getIdUser()) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean orderIsCreatedByLoggedInUser(int id) {
        Orders order = orderDAO.getOrderById(id);
        if (Objects.nonNull(order)) {
            if (MainClass.loginUser.equalsIgnoreCase("superadmin")) {
                return true;
            }
            if (loggedInUserHasRole(Arrays.asList("admin"))) {
                if ((MainClass.idUserLogged == order.getUser().getIdUser()) || (MainClass.idUserLogged == order.getUser().getIdParent())) {
                    return true;
                }
            }
        }
        return false;
    }
}
