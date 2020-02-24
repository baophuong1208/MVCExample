/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import java.util.Arrays;
import java.util.Objects;
import model.Orders;
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
    private OrderDAO orderDAO = new OrderDAO();

    public boolean validateUser(User u) {
        User getUserByName = userDao.getUserByUserName(u.getNameUser());
        if (Objects.nonNull(getUserByName)) {
            System.out.println("Name da ton tai");
            return false;
        }

        if (permissionService.getLoggedInUserRoles().equals("admin")) {
            if (!u.getRole().equals("user")) {
                System.out.println("Admin khong duoc tao user co role super admin hoac admin");
                return false;
            }
        }
        return true;
    }

    public boolean LoggedInUserHasPermissionOnUser(int id) {
        User user = userDao.getUserByID(id);
        if (Objects.nonNull(user)) {
            if (permissionService.loggedInUserHasRole(Arrays.asList("superadmin", "admin"))) {
                if (permissionService.isCreatedByLoggedInUser(id)) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean validateProduct(Product p) {
        Product product = productDAO.getProductByName(p.getNameProduct());
        if (Objects.nonNull(product)) {
            System.out.println("san pham da ton tai");
            return false;
        }
        return true;
    }

    public boolean checkUserHasPermissionOnProduct(int id) {
        Product product = productDAO.getProductByID(id);
        if (Objects.nonNull(product)) {
            if (permissionService.loggedInUserHasRole(Arrays.asList("superadmin", "admin"))) {
                if (permissionService.productIsCreatedByLoggedInUser(product.getUser().getIdUser())) {
                    return true;
                }

            }

        }
        return false;
    }

    public boolean checkUserHasPermissionOnOrder(int id) {
        Orders order = orderDAO.getOrderById(id);
        if (Objects.nonNull(order)) {
            if (permissionService.loggedInUserHasRole(Arrays.asList("superadmin", "admin","user"))) {
                if (permissionService.orderIsCreatedByLoggedInUser(id)) {
                    return true;
                }

            }
        }
        return false;
    }
}
