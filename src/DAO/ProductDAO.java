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
import model.Product;

public class ProductDAO {

    Connection connect = ConnectDB.openconnect();

    public List<Product> getListProduct() {
        try {

            List<Product> listproduct = new ArrayList<>();
            String query = "select * from product";
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                Product pr = new Product();
                pr.setId_product(rs.getString(1));
                pr.setName_product(rs.getString(2));
                pr.setUser(new UserDAO().getUserByID(rs.getString(3)));
                pr.setQuantity(rs.getInt(4));
                pr.setType(rs.getString(5));
                pr.setPrice(rs.getDouble(6));
                listproduct.add(pr);
            }
            return listproduct;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Product getProductByID(String id) {
        String query = "select * from product where id_product = ?";
        try {
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            prepareStatement.setString(1, id);
            ResultSet rs = prepareStatement.executeQuery();
            Product pr = new Product();
            while (rs.next()) {
                pr.setId_product(rs.getString(1));
                pr.setName_product(rs.getString(2));
                pr.setUser(new UserDAO().getUserByID(rs.getString(3)));
                pr.setQuantity(rs.getInt(3));
                pr.setType(rs.getString(4));
                pr.setPrice(rs.getDouble(5));
            }
            return pr;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertProduct(Product pr) {
        String query = "insert into product values(?,?,?,?,?,?)";

        try {
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            prepareStatement.setString(1, pr.getId_product());
            prepareStatement.setString(2, pr.getName_product());
            prepareStatement.setString(3, pr.getUser().getId_user());
            prepareStatement.setInt(4, pr.getQuantity());
            prepareStatement.setString(5, pr.getType());
            prepareStatement.setDouble(6, pr.getPrice());
            int rs = prepareStatement.executeUpdate();
            if (rs != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean updateProductByID(String id, Product pr) {
        String query = "update product SET id =?, name =?, user_id =?,quantity=?,type=?,price=? WHERE id=? ";
        try {
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            prepareStatement.setString(1, pr.getId_product());
            prepareStatement.setString(2, pr.getName_product());
            prepareStatement.setString(3, pr.getUser().getId_user());
            prepareStatement.setInt(4, pr.getQuantity());
            prepareStatement.setString(5, pr.getType());
            prepareStatement.setDouble(6, pr.getPrice());
            int rs = prepareStatement.executeUpdate();
            if (rs != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteProductByID(String id) {
        String query = "delete from product where id = ?";
        try {
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            prepareStatement.setString(1, id);
            int rs = prepareStatement.executeUpdate();
            if (rs != 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
