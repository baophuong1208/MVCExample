
package Service;

import DAO.ProductDAO;
import DAO.UserDAO;
import java.util.Scanner;
import model.Product;
import model.User;


public class ProductService {
    private PermissionService permissionService = new PermissionService();
    private ProductDAO productDAO = new ProductDAO();
    private UserDAO userDAO = new UserDAO();
    private  ValidationService validationService = new ValidationService();
    public void insertProduct(Product product){
        product.setUser(userDAO.getUserByID(permissionService.getLoggedInUserID()));      
        }
    
    public void updateProduct(Product product, int id){
        if (validationService.checkUserHasPermissionOnProduct(id)){
            productDAO.updateProductByID(id,product);
        }
        return;
    }
    
    public void deleteProduct(int idproduct){
          if (validationService.checkUserHasPermissionOnProduct(idproduct)){
            productDAO.deleteProductByID(idproduct);
        }
      
    }
    
        
    }
    

