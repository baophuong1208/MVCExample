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
import Controller.MainClass;

public class PermissionService {

    private UserDAO userDao = new UserDAO();
    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();

    public boolean login(String name) {

        User user = userDao.getUserByUserName(name);
        if (Objects.isNull(user)) {
            return false;
        } else {
            MainClass.loginUser = user.getRole();
            MainClass.idUserLogged = user.getIdUser();
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

    public boolean isCreatedByLoggedInUser(int idUser) {
        User u = userDao.getUserByID(idUser);
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

    public boolean productIsCreatedByLoggedInUser(int idProduct) {
        Product product = productDAO.getProductByID(idProduct);
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

    public boolean orderIsCreatedByLoggedInUser(int idOrder) {
        Orders order = orderDAO.getOrderById(idOrder);
        if (Objects.nonNull(order)) {
            if (MainClass.loginUser.equalsIgnoreCase("superadmin")) {
                return true;
            }
            if (loggedInUserHasRole(Arrays.asList("admin","user"))) {
                if ((MainClass.idUserLogged == order.getUser().getIdUser()) || (MainClass.idUserLogged == order.getUser().getIdParent())) {
                    return true;
                }
                System.out.println("khong co quyen sua");
                
            }

        }
        return false;
    }
}
