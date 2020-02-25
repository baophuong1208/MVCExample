
package Service;

import DAO.ProductDAO;
import DAO.UserDAO;
import model.Product;


public class ProductService {
    private PermissionService permissionService = new PermissionService();
    private ProductDAO productDAO = new ProductDAO();
    private UserDAO userDAO = new UserDAO();
    private  ValidationService validationService = new ValidationService();
    public void insertProduct(Product product){
        product.setUser(userDAO.getUserByID(permissionService.getLoggedInUserID()));      
        }
    
    public void updateProduct(Product product, int idProduct){
        if (validationService.checkUserHasPermissionOnProduct(idProduct)){
            productDAO.updateProductByID(idProduct,product);
        }
        return;
    }
    
    public void deleteProduct(int idProduct){
          if (validationService.checkUserHasPermissionOnProduct(idProduct)){
            productDAO.deleteProductByID(idProduct);
        }
      
    }
    
        
    }
    

