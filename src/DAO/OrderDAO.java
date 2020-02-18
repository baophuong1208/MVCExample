
package DAO;

import Connection.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orders;

public class OrderDAO {
    Connection connect = ConnectDB.openconnect();
    public List<Orders> getListOrder(){
        List<Orders> listorder = new ArrayList<>();
        String query = "SELECT  orderProduct.idOrder,orderProduct.idProduct, orders.idUser,orders.times FROM ((orderProduct INNER JOIN orders ON orders.idOrder = orderProduct.idOrder) INNER JOIN product ON  product.idProduct=orderProduct.idProduct );";
        try {
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Orders order = new Orders();
                order.setIdOrder(rs.getInt(1));
                order.setTime(rs.getTime(2));
                order.setUser(new UserDAO().getUserByID(rs.getInt(3)));
                order.setListProduct(new ProductDAO().getListProduct());
                
             listorder.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
}
