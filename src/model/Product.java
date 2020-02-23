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

    public void output(){
        System.out.println("san pham: ");
        System.out.println("ID Product: "+idProduct);
        System.out.println("Name Product: "+nameProduct);
        System.out.println("ID User: " +user.getIdUser());
        System.out.println("Quantity: "+quantity);
        System.out.println("Type: "+type);
        System.out.println("Price: "+price);
        System.out.println("---------------");
    }


    }


