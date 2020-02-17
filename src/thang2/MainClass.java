package thang2;

import DAO.UserDAO;
import Service.PermissionService;
import Service.UserService;
import Service.ValidationService;
import java.util.Arrays;
import java.util.Scanner;
import model.User;

public class MainClass {

    public static String loginUser = "";
    public static String idUserLogged = "";
    private PermissionService permissionService = new PermissionService();
    private UserService userService = new UserService();
    private ValidationService validationService = new ValidationService();
    private Scanner input = new Scanner(System.in);

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

                break;
            }
            case 3: {

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
            userService.insertUser(u);
        }

    }

    private User inputUser() {
        User u = new User();
        System.out.println("Nhap thong tin User");
        System.out.println("Nhap ten User");
        u.setName_user(input.nextLine());
        System.out.println("Nhap Role");
        u.setRole(input.nextLine());
        return u;
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
            }
            running.Menu(n);

        } while (n != 4);

    }
}
