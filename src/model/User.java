
package model;

import DAO.UserDAO;
import Service.UserService;
import java.util.List;
import java.util.Scanner;
import thang2.MainClass;

public class User {
    private String id_user;
    private String name_user;
    private String role;
    private String id_parent;

    public User() {
    }

    public User(String id_user, String name_user, String role, String id_parent) {
        this.id_user = id_user;
        this.name_user = name_user;
        this.role = role;
        this.id_parent = id_parent;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId_parent() {
        return id_parent;
    }

    public void setId_parent(String id_parent) {
        this.id_parent = id_parent;
    }
    
 public void nhapUser(){
        UserDAO ud = new UserDAO();
        UserService us = new UserService();
        List<String> listid = ud.getlistID(); 
        List<String> listname = ud.getlistUserName();

           
           
            int flag =0;
            int flag2 = 0;
        
        do{
        System.out.println("Nhap id: ");
         String id = new Scanner(System.in).nextLine();
         if(us.kiemTraTrung(id, listid)==false){
            id_user=id;
             setId_user(id_user);
            flag =1;
             do{
            System.out.println("nhap user name: ");
            String name = new Scanner(System.in).nextLine();
            us.kiemTraTrung(name, listname);
            if(us.kiemTraTrung(name, listname)==true){
                     flag2=1;
              
            }
            else{  
                name_user = name;
                setName_user(name_user);
              
            }
             }while(flag2==1);
             }else {
             
           
         }
        }while(flag!=1);
            

    }
}
     
    

