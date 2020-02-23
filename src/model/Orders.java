
package model;

import java.sql.Time;
import java.util.List;
import java.sql.Date;
import java.sql.Timestamp;

public class Orders {
    private int idOrder;
    private Timestamp time; 
    private User user;
    private List<Product> listProduct;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }


    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public void output(){
        System.out.println("Thong tin order: ");
        System.out.println("Time: "+time);
        System.out.println("ID User: "+user.getIdUser());

      
    }

 
    
    
    
    
    
}
