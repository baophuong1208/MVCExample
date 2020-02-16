
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
        String query = "SELECT  order_product.order_id,order_product.product_id,orders.user_id,orders.times FROM ((order_product INNER JOIN orders ON orders.id = order_product.order_id) INNER JOIN product ON  product.id=order_product.product_id );";
        try {
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Orders order = new Orders();
                order.setId_order(rs.getString(1));
                order.setTime(rs.getTime(2));
                order.setUser(new UserDAO().getUserByID(rs.getString(3)));
                order.setList_product(new ProductDAO().getListProduct());
                
             listorder.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
}
