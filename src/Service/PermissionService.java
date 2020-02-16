
package Service;

//import DAO.UserDAO;
import DAO.UserDAO;
import java.util.Objects;
import model.User;
import thang2.MainClass;
public class PermissionService {
    UserDAO userDao = new UserDAO();
    
    public boolean login(String name){
        User u = userDao.getUserByUserName(name);
        if(Objects.isNull(u)){
            return false;
        }else{
            MainClass.loginUser = u.getRole().toLowerCase();
            MainClass.idUserLogged = u.getId_user();
            return true;
        }
    }
    
    public String getRolesLoggedInUser(){
        return MainClass.loginUser;
    }
  public String getIDLoggedInUser(){
       return MainClass.idUserLogged;
      
  }
    
    public void logout(){
        MainClass.loginUser = "";
    }
    
    
    
}
