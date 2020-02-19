package model;

import DAO.ProductDAO;
import Service.PermissionService;
import java.util.List;
import java.util.Scanner;
import thang2.MainClass;
import static thang2.MainClass.idUserLogged;

public class Product {

    private int idProduct;
    private String nameProduct;
    private User user;
    private int quantity;
    private String type;
    private double price;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }



    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
//    public void nhapProduct(){
//        ProductDAO prd = new ProductDAO();
//       List<String> listid= prd.getListIDProduct();
//       List<String> listname = prd.getListNameProduct();
//        PermissionService ps = new PermissionService();
//          int flag =0;
//          int flag2=0;
//        do{
//        System.out.println("Nhap id: ");
//         String id = new Scanner(System.in).nextLine();
//         if(ps.kiemTraTrung(id, listid)==false){
//            id_product=id;
//             setId_product(id_product);
//            flag =1;
//             do{
//            System.out.println("nhap user name: ");
//            String name = new Scanner(System.in).nextLine();
//            ps.kiemTraTrung(name, listname);
//            if(ps.kiemTraTrung(name, listname)==true){
//                     flag2=1;
//              
//            }
//            else{  
//                name_product = name;
//                setName_product(name_product);
//              
//            }
//             }while(flag2==1);
//             }else {
//             
//           
//         }
//        }while(flag!=1);
//        
//        
//        System.out.println("nhap price: ");
//           price = new Scanner(System.in).nextDouble();
//           System.out.println("nhap quantity: ");
//           quantity = new Scanner(System.in).nextInt();
//           System.out.println("nhap type: ");
//           type = new Scanner(System.in).nextLine();
//           


    }


