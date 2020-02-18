package thang2;

import DAO.UserDAO;
import Service.PermissionService;
import Service.ProductService;
import Service.UserService;
import Service.ValidationService;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import model.Product;
import model.User;

public class MainClass {

    public static String loginUser = "";
//    public static String roleUserLogged ="";
    public static int idUserLogged = 0;
    private PermissionService permissionService = new PermissionService();
    private UserService userService = new UserService();
    private ValidationService validationService = new ValidationService();
    private Scanner input = new Scanner(System.in);
    private UserDAO userDao = new UserDAO();
    private ProductService productService =new ProductService();

    public void Menu(int n) {
        System.out.println("1: them tai khoan");
        System.out.println("2: Sua tai khoan");
        System.out.println("3: Xoa tai khoan");
        System.out.println("4: Dang xuat");
        n = new Scanner(System.in).nextInt();
        switch (n) {
            case 1: {
                insertUser();
                break;

            }
            case 2: {
                updateUser();

                break;
            }
            case 3: {
                deleteUser();

                break;
            }
            case 4: {
                permissionService.logout();
                break;
            }
        }
    }

    private void insertUser() {
        if (permissionService.userHasRole(Arrays.asList("admin", "superadmin"))) {
            User u = inputUser();

            if (!validationService.validateUser(u)) {

                return;
            }
            UserDAO userDAO = new UserDAO();
            userDAO.insertUser(u);
        }

    }

    private User inputUser() {
        User u = new User();
        System.out.println("Nhap thong tin User");
        System.out.println("Nhap ten User");
        u.setNameUser(input.nextLine());
        System.out.println("Nhap Role");
        u.setRole(input.nextLine());
        userService.insertUser(u);
        return u;
    }

    private void updateUser() {
        if (permissionService.userHasRole(Arrays.asList("admin", "superadmin"))) {
            System.out.println("nhap idUser can sua");
            int id = Integer.parseInt(input.nextLine());
            System.out.println("nhap thong tin can sua");
            User u = inputUser();
            userService.updateUser(u, id);

        }

    }

    private void deleteUser() {
        if (permissionService.userHasRole(Arrays.asList("admin", "superadmin"))) {
            System.out.println("nhap idUser can xoa");
            int id = Integer.parseInt(input.nextLine());
            userService.deleteUser(id);
            return;
        }

    }
    
//    private void insertProduct(){
//         if (permissionService.userHasRole(Arrays.asList("admin", "superadmin"))) {
//            Product u = inputProduct();
//
//            if (!validationService.validateUser(u)) {
//
//                return;
//            }
//            UserDAO userDAO = new UserDAO();
//            userDAO.insertUser(u);
//        }
//        
//    }
    
    
    private Product inputProduct(){
        Product product = new Product();
        System.out.println("nhap thong tin san pham: ");
        System.out.println("nhap ten san pham ");
        product.setNameProduct(input.nextLine());
        productService.insertProduct(product);
        System.out.println("so luong");
        product.setQuantity(input.nextInt());
        System.out.println("loai");
        product.setType(input.nextLine());
       return product;
    }

    public boolean login() {
        System.out.println("Nhap vao ten: ");
        return permissionService.login(new Scanner(System.in).nextLine());
    }

    public static void main(String[] args) {
        MainClass running = new MainClass();
        int n = 0;
        do {
            if (MainClass.loginUser.equals("")) {
                if (!running.login()) {
                    continue;
                } else {

                }
            } else {
            }
            running.Menu(n);

        } while (n != 4);

    }
}
