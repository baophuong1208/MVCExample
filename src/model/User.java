
package model;

import DAO.UserDAO;
import Service.PermissionService;
import Service.UserService;
import java.util.List;
import java.util.Scanner;
import thang2.MainClass;

public class User {
    private int idUser;
    private String nameUser;
    private String role;
    private int idParent;

    public User() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

  

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

 

   
// public void nhapUser(){
//        UserDAO ud = new UserDAO();
//        PermissionService ps = new PermissionService();
//        List<String> listname = ud.getlistUserName();
//
//            int flag2 = 0;
// 
////             do{
////            System.out.println("nhap user name: ");
////            String name = new Scanner(System.in).nextLine();
////            ps.kiemTraTrung(name, listname);
////            if(ps.kiemTraTrung(name, listname)==true){
////                     flag2=1;
////              
////            }
////            else{  
////                name_user = name;
////                setName_user(name_user);
////              
////            }
////             }while(flag2==1);
//
//}

 
}
     
    

