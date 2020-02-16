/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang2;

import DAO.UserDAO;
import java.util.List;

/**
 *
 * @author Kien.NTK
 */
public class a {
    public static void main(String[] args) {
        UserDAO user = new UserDAO();
        List<String> listID = user.getlistUserName();
        System.out.println(listID.size());
        
    }
    
}
