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
                pr.setIdProduct(rs.getInt(1));
                pr.setNameProduct(rs.getString(2));
                pr.setUser(new UserDAO().getUserByID(rs.getInt(3)));
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
        public List<Integer> getListIDProduct() {
        try {

            List<Integer> listidproduct = new ArrayList<>();
            String query = "select idProduct from product";
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                int a =rs.getInt("idProduct");
                listidproduct.add(a);
            }
            return listidproduct;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
          public List<String> getListNameProduct() {
        try {

            List<String> listnameproduct = new ArrayList<>();
            String query = "select nameProduct from product";
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                String a =rs.getString("nameProduct");
                listnameproduct.add(a);
            }
            return listnameproduct;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
            

    public Product getProductByID(int id) {
        String query = "select * from product where idProduct = ?";
        try {
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet rs = prepareStatement.executeQuery();
            Product pr = new Product();
            while (rs.next()) {
                pr.setIdProduct(rs.getInt(1));
                pr.setNameProduct(rs.getString(2));
                pr.setUser(new UserDAO().getUserByID(rs.getInt(3)));
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
        String query = "insert  into product(nameProduct, idUser,quantity,type,price) values(?,?,?,?,?)";

        try {
            PreparedStatement prepareStatement = connect.prepareStatement(query);
//            prepareStatement.setString(1, pr.getIdProduct());
            prepareStatement.setString(1, pr.getNameProduct());
            prepareStatement.setInt(2, pr.getUser().getIdUser());
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
        String query = "update product SET idProduct =?, nameProduct =?, idUser =?,quantity=?,type=?,price=? WHERE idProduct=? ";
        try {
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            prepareStatement.setInt(1, pr.getIdProduct());
            prepareStatement.setString(2, pr.getNameProduct());
            prepareStatement.setInt(3, pr.getUser().getIdUser());
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

    public boolean deleteProductByID(int id) {
        String query = "delete from product where idProduct = ?";
        try {
            PreparedStatement prepareStatement = connect.prepareStatement(query);
            prepareStatement.setInt(1, id);
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
