package Service;

import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import model.Orders;
import model.Product;
import thang2.MainClass;

public class OrderService {

    private ProductDAO productDAO = new ProductDAO();
    private UserDAO userDAO = new UserDAO();
    private Product product = new Product();
    private OrderDAO orderDAO = new OrderDAO();
    private Scanner input = new Scanner(System.in);
    private ValidationService validationService = new ValidationService();

    public boolean updateOrder(int idOrder) {
        if (validationService.checkUserHasPermissionOnOrder(idOrder)) {
            return true;
        }
        return false;

    }
    
    
    
}
