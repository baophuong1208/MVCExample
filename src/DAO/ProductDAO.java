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
    private UserDAO userDAO = new UserDAO();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getListProduct() {
        try {

            List<Product> listProduct = new ArrayList<>();
            String query = "select * from product";
            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getInt(1));
                product.setNameProduct(rs.getString(2));
                product.setUser(new UserDAO().getUserByID(rs.getInt(3)));
                product.setQuantity(rs.getInt(4));
                product.setType(rs.getString(5));
                product.setPrice(rs.getDouble(6));
                listProduct.add(product);
            }
            return listProduct;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Integer> getListIDProduct() {
        try {

            List<Integer> listIDProduct = new ArrayList<>();
            String query = "select idProduct from product";
            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getInt("idProduct");
                listIDProduct.add(a);
            }
            return listIDProduct;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<String> getListNameProduct() {
        try {

            List<String> listNameProduct = new ArrayList<>();
            String query = "select nameProduct from product";
            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString("nameProduct");
                listNameProduct.add(a);
            }
            return listNameProduct;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Product getProductByID(int idProduct) {
        String query = "select * from product where idProduct = ?";
        try {
            ps = connect.prepareStatement(query);
            ps.setInt(1, idProduct);
            rs = ps.executeQuery();
            Product product = null;
            while (rs.next()) {
                product = new Product();
                product.setIdProduct(rs.getInt(1));
                product.setNameProduct(rs.getString(2));
                product.setUser(new UserDAO().getUserByID(rs.getInt(3)));
                product.setQuantity(rs.getInt(4));
                product.setType(rs.getString(5));
                product.setPrice(rs.getDouble(6));
            }
            return product;

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Product getProductByName(String name) {
        String query = "select * from product where nameProduct =?";
        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            Product product = null;
            while (rs.next()) {
                product = new Product();
                product.setIdProduct(rs.getInt(1));
                product.setNameProduct(rs.getString(2));
                product.setUser(userDAO.getUserByID(rs.getInt(3)));
                product.setQuantity(rs.getInt(4));
                product.setType(rs.getString(5));
                product.setPrice(rs.getDouble(6));
            }
            return product;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertProduct(Product pr) {
        String query = "insert into product(nameProduct,idUser,quantity,type,price) values(?,?,?,?,?);";

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, pr.getNameProduct());
            ps.setInt(2, pr.getUser().getIdUser());
            ps.setInt(3, pr.getQuantity());
            ps.setString(4, pr.getType());
            ps.setDouble(5, pr.getPrice());
            int i = ps.executeUpdate();
            if (i != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean updateProductByID(int idProduct, Product pr) {
        String query = "update product SET nameProduct =?, idUser =?,quantity=?,type=?,price=? WHERE idProduct=? ";
        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, pr.getNameProduct());
            ps.setInt(2, pr.getUser().getIdUser());
            ps.setInt(3, pr.getQuantity());
            ps.setString(4, pr.getType());
            ps.setDouble(5, pr.getPrice());
            ps.setInt(6, idProduct);
            int i = ps.executeUpdate();
            if (i != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteProductByID(int idproduct) {
        String query = "delete from product where idProduct = ?";
        try {
            ps = connect.prepareStatement(query);
            ps.setInt(1, idproduct);
            int i = ps.executeUpdate();
            if (i != 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
