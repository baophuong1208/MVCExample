
package model;

import java.util.Date;
import java.util.List;

public class Orders {
    private String id_order;
    private Date time;
    private User user;
    private List<Product> list_product;

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getList_product() {
        return list_product;
    }

    public void setList_product(List<Product> list_product) {
        this.list_product = list_product;
    }
    
    
    
    
    
    
}
