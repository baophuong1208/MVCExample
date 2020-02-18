package DAO;

import Connection.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO {

    Connection connect = ConnectDB.openconnect();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<User> getListUser() {

        connect = ConnectDB.openconnect();
        try {
            List<User> listuser = new ArrayList<>();
            String user_query = "select * from user";

            ps = connect.prepareStatement(user_query);
            rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String role = rs.getString(3);
                int idparent = rs.getInt(4);
                u.setIdUser(id);
                u.setNameUser(name);
                u.setRole(role);
                u.setIdParent(idparent);
                listuser.add(u);
            }
            return listuser;

        } catch (SQLException ex) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);

        } 
        return null;

    }

    public List<Integer> getlistID() {
        
        List<Integer> listid = new ArrayList();
        String query = "select idUser from user";
        try {

            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idUser");
                listid.add(id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return listid;

    }

    public List<String> getlistUserName() {
        List<String> listname = new ArrayList();
        String query = "select nameUser from user";
        try {

            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                String string = rs.getString("nameUser");
                listname.add(string);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listname;

    }

    public boolean insertUser(User u) {
        try {

            String themUser = "insert  into user(nameUser,role,idParent) values(?,?,?)";
            PreparedStatement createStatement = connect.prepareStatement(themUser);
            createStatement.setString(1, u.getNameUser());
            createStatement.setString(2, u.getRole());
            createStatement.setInt(3, u.getIdParent());
            int rs = createStatement.executeUpdate();
            if (rs != 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return false;
    }

    public User getUserByID(int id) {
        try {
            User u = new User();
            String query = "select * from user where idUser=?";
            ps = connect.prepareStatement(query);
            ps.setInt(1, id);
             rs = ps.executeQuery();
            while (rs.next()) {
                u.setIdUser(rs.getInt(1));
                u.setNameUser(rs.getString(2));
                u.setRole(rs.getString(3));
                u.setIdParent(rs.getInt(4));
            }
            return u;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    public User getUserByUserName(String name) {
        try {
            User user = null;
            String query = "select * from user where nameUser=?";
            ps = connect.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt(1));
                user.setNameUser(rs.getString(2));
                user.setRole(rs.getString(3));
                user.setIdParent(rs.getInt(4));
            }
            return user;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    public User getUserByRole(String role) {
        try {
            User u = new User();
            String query = "select * from user where role=?";
            ps = connect.prepareStatement(query);
            ps.setString(1, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setIdUser(rs.getInt(1));
                u.setNameUser(rs.getString(2));
                u.setRole(rs.getString(3));
                u.setIdParent(rs.getInt(4));
            }
            return u;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }

    public boolean updateUserByID(User u, int id) {
        try {
            String query = "update user SET nameUser=?, role=?, idParent=? where idUser=?";
            ps = connect.prepareStatement(query);
            ps.setString(1, u.getNameUser());
            ps.setString(2, u.getRole());
            ps.setInt(3, u.getIdParent());
            ps.setInt(4, id);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean deleteUserByID(int id) {
        try {
            String query = "delete from user where idUser = ?";
            ps = connect.prepareStatement(query);
            ps.setInt(1, id);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }

}
