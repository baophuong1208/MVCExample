
package model;

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
    
    
    
}
