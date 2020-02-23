package DAO;

import Connection.ConnectDB;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.LocalAttribute;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orders;
import model.Product;

public class OrderDAO {

    Connection connect = ConnectDB.openconnect();
    ProductDAO productDAO = new ProductDAO();
    UserDAO userDAO = new UserDAO();

    public List<Orders> getListOrder() {
        List<Orders> listorder = new ArrayList<>();
        String query = "SELECT  orderproduct.idOrder,orderproduct.idProduct,orderproduct.quantity, orders.idUser,orders.time FROM ((orderproduct INNER JOIN orders ON orders.idOrder = orderproduct.idOrder) INNER JOIN product ON  product.idProduct=orderproduct.idProduct );";
        try {
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setIdOrder(rs.getInt(1));
                order.setTime(rs.getTimestamp(2));
                order.setUser(new UserDAO().getUserByID(rs.getInt(3)));
                order.setListProduct(new ProductDAO().getListProduct());

                listorder.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public boolean updateProductInOrder(int idOrder, Orders order) {
        String query = "update orders SET time=?, idUser =? where idOrder =?";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setInt(2, order.getUser().getIdUser());
            ps.setInt(3, idOrder);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                return true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteProductInOrder(int idOrder, String nameProduct) {
        String query = "delete from  orderproduct where idOrder =? and idProduct=?;";
        Product product = productDAO.getProductByName(nameProduct);
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, idOrder);
            ps.setInt(2, product.getIdProduct());
            int rs = ps.executeUpdate();
            if (rs != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean insertProductInOrderProduct(int idOrder, Orders order) {
        String query2 = "insert into orderproduct(idProduct,idOrder,quantity) values(?,?,?)";

        int rs2 = 0;
        try {
            PreparedStatement ps2 = connect.prepareStatement(query2);
            for (int i = 0; i < order.getListProduct().size(); i++) {
                ps2.setInt(1, order.getListProduct().get(i).getIdProduct());
                ps2.setInt(2, idOrder);
                ps2.setInt(3, order.getListProduct().get(i).getQuantity());
                rs2 = ps2.executeUpdate();
            }
            if (rs2 != 0) {
                return true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

//    public boolean insertProductInOrder(int idOrder, Orders orders) {
//        String query2 = "insert into orderproduct(idProduct,quantity) values(?,?)  where idOrder =?";
//        Orders order = getOrderById(idOrder);
//        int rs2 = 0;
//        PreparedStatement ps2;
//        try {
//            ps2 = connect.prepareStatement(query2);
//               for (int j = 0; j < orders.getListProduct().size(); j++) {
//            if (!orders.getListProduct().get(j).getNameProduct(). {
//                ps2.setInt(1, orders.getListProduct().get(j).getIdProduct());
//                ps2.setInt(2, orders.getListProduct().get(j).getQuantity());
//                ps2.setInt(3, idOrder);
//                rs2 = ps2.executeUpdate();
//            }
//            if (rs2 != 0) {
//                return true;
//            }
//               }
//        }catch (SQLException ex) {
//            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//       
//               }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
    public boolean updateProductInOrderProduct(int idOrder, Orders order) {
        Orders order1 = getOrderById(idOrder);
        String query2 = "update orderproduct SET quantity=? where idOrder=? and idProduct=? ";

        try {
            PreparedStatement ps2 = connect.prepareStatement(query2);
            int rs2 = 0;
            for (int i = 0; i < order1.getListProduct().size(); i++) {
                ps2.setInt(1, order.getListProduct().get(i).getQuantity());
                ps2.setInt(2, idOrder);
                ps2.setInt(3, order.getListProduct().get(i).getIdProduct());
                rs2 = ps2.executeUpdate();
            }

            if (rs2 != 0) {
                return true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Orders getOrderById(int orderID) {
        String sql = "select *from orders where idOrder = ?";

        List<Product> listproduct = new ArrayList<>();
        Product pr = new Product();
        String sql2 = "select idProduct, quantity from orderproduct where idOrder=?";
        try {
            PreparedStatement ps2 = connect.prepareStatement(sql2);
            ps2.setInt(1, orderID);
            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                pr = productDAO.getProductByID(rs2.getInt(1));
                pr.setQuantity(rs2.getInt(2));
                listproduct.add(pr);

            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            Orders order = new Orders();
            while (rs.next()) {
                order.setIdOrder(rs.getInt(1));
                order.setTime(rs.getTimestamp(2));
                order.setUser(userDAO.getUserByID(rs.getInt(3)));
                order.setListProduct(listproduct);
            }
            return order;

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public boolean insertOrder(Orders order) {
        String query = "insert into orders (time,idUser) values (?,?)";
        try {

            PreparedStatement ps = connect.prepareStatement(query);
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setInt(2, order.getUser().getIdUser());
            int rs = ps.executeUpdate();
            if (rs != 0) {

                return true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean insertOrderProduct(Orders order) {

        String query2 = "insert into orderproduct(idProduct,idOrder,quantity) values(?,?,?)";
        String query = "select max(idOrder) from orders";
        int id = 0;
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);

            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        int rs2 = 0;
        try {
            PreparedStatement ps2 = connect.prepareStatement(query2);
            for (int i = 0; i < order.getListProduct().size(); i++) {
                ps2.setInt(1, order.getListProduct().get(i).getIdProduct());
                ps2.setInt(3, order.getListProduct().get(i).getQuantity());
                ps2.setInt(2, id);
                rs2 = ps2.executeUpdate();

            }
            if (rs2 != 0) {
                return true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

}
