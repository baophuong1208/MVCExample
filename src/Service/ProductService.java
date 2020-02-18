/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.ProductDAO;
import DAO.UserDAO;
import java.util.Scanner;
import model.Product;
import model.User;

/**
 *
 * @author Kien.NTK
 */
public class ProductService {
    public void insertProduct(Product pr){
        UserDAO ud = new UserDAO();
           PermissionService ps = new PermissionService();
           pr.setUser(ud.getUserByID(ps.getIDLoggedInUser()));
        
        }
        
    }
    

