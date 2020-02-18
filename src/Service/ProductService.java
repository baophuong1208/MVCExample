
package Service;

import DAO.ProductDAO;
import DAO.UserDAO;
import java.util.Scanner;
import model.Product;
import model.User;


public class ProductService {
    PermissionService permissionService = new PermissionService();
    ProductDAO productDAO = new ProductDAO();
    UserDAO userDAO = new UserDAO();
    public void insertProduct(Product product){
        product.setUser(userDAO.getUserByID(permissionService.getIDLoggedInUser()));      
        }
    
        
    }
    

