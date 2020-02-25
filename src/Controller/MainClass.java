package Controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import Service.OrderService;
import Service.PermissionService;
import Service.ProductService;
import Service.UserService;
import Service.ValidationService;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import model.Orders;
import model.Product;
import model.User;

public class MainClass {

    public static int idUserLogged = 0;
    public static String loginUser = "";
    private PermissionService permissionService = new PermissionService();
    private UserService userService = new UserService();
    private ValidationService validationService = new ValidationService();
    private Scanner input = new Scanner(System.in);
    private UserDAO userDao = new UserDAO();
    private ProductService productService = new ProductService();
    private ProductDAO productDAO = new ProductDAO();
    private OrderService orderService = new OrderService();
    private OrderDAO orderDAO = new OrderDAO();
    private Product product = new Product();

    public void Menu(int n) {
        System.out.println("1: them tai khoan");
        System.out.println("2: Sua tai khoan");
        System.out.println("3: Xoa tai khoan");
        System.out.println("4: Them san pham");
        System.out.println("5: sua san pham");
        System.out.println("6: Xoa san pham");
        System.out.println("7: xem tat ca san pham");
        System.out.println("8: Tim kiem san pham theo ten");
        System.out.println("9: Tim kiem san pham theo id san pham");
        System.out.println("10: Mua hang");
        System.out.println("11: xem order ");
        System.out.println("12: Tim kiem order theo id: ");
        System.out.println("13: Sua order");
        System.out.println("15: Dang xuat");

        n = Integer.parseInt(input.nextLine());

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
                insertProduct();
                break;
            }
            case 5: {
                updateProduct();
                break;
            }
            case 6: {
                deleteProduct();
                break;
            }
            case 7: {
                getListProduct();
                break;
            }

            case 8: {
                getProductByNameProduct();
//                getProductByID();
                break;
            }
            case 9: {
                getProductByID();
                break;
            }
            case 10: {
                createOrder();
                break;
            }
            case 11: {
                getListOrder();

                break;
            }
            case 12: {
                getOrderByID();
                break;
            }
            case 13: {
                updateOrder();
                break;
            }
            case 15: {
                permissionService.logout();
                break;
            }
        }
    }

    private void insertUser() {
        if (permissionService.loggedInUserHasRole(Arrays.asList("admin", "superadmin"))) {
            User u = inputUser();

            if (!validationService.validateUser(u)) {

                return;
            }
            userDao.insertUser(u);

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
        if (permissionService.loggedInUserHasRole(Arrays.asList("admin", "superadmin"))) {
            System.out.println("nhap idUser can sua");
            int id = Integer.parseInt(input.nextLine());
            System.out.println("nhap thong tin can sua");
            User u = inputUser();
            userService.updateUser(u, id);

        }

    }

    private void deleteUser() {
        if (permissionService.loggedInUserHasRole(Arrays.asList("admin", "superadmin"))) {
            System.out.println("nhap idUser can xoa");
            int id = Integer.parseInt(input.nextLine());
            userService.deleteUser(id);
        }

    }

    private void insertProduct() {
        if (permissionService.loggedInUserHasRole(Arrays.asList("admin", "superadmin"))) {
            Product product = inputProduct();
            if (!validationService.validateProduct(product)) {
                return;
            }
            productDAO.insertProduct(product);
        }
    }

    private void updateProduct() {
        if (permissionService.loggedInUserHasRole(Arrays.asList("admin", "superadmin"))) {
            System.out.println("nhap id san pham can sua: ");
            int id = Integer.parseInt(input.nextLine());
            System.out.println("nhap thong tin san pham sua");
            Product product = inputProduct();
            productService.updateProduct(product, id);
        }
    }

    private void deleteProduct() {
        if (permissionService.loggedInUserHasRole(Arrays.asList("admin", "superadmin"))) {
            System.out.println("nhap idProduct can xoa");
            int idproduct = Integer.parseInt(input.nextLine());
            productService.deleteProduct(idproduct);
        }
    }

    private Product inputProduct() {
        Product product = new Product();
        System.out.println("nhap thong tin san pham: ");
        System.out.println("nhap ten san pham ");
        product.setNameProduct(input.nextLine());
        productService.insertProduct(product);
        System.out.println("so luong");
        product.setQuantity(Integer.parseInt(input.nextLine()));
        System.out.println("loai");
        product.setType(input.nextLine());
        System.out.println("gia");
        product.setPrice(Float.parseFloat(input.nextLine()));
        return product;
    }

    private void getListProduct() {
        List<Product> listProduct = productDAO.getListProduct();
        for (int i = 0; i < listProduct.size(); i++) {
            listProduct.get(i).output();

        }

    }

    private void getProductByNameProduct() {
        System.out.println("nhap ten san pham");
        String name = input.nextLine();
        Product product = productDAO.getProductByName(name);
        product.output();

    }

    private void getProductByID() {
        System.out.println("nhap id san pham can tim");
        int p = Integer.parseInt(input.nextLine());
        product = productDAO.getProductByID(p);
        product.output();
    }

    private void getListOrder() {
        List<Orders> list = orderDAO.getListOrder();
        double thanhtien = 0;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).output();
            System.out.println("ID Product --- so luong:  ");
            List<Product> listproduct = list.get(i).getListProduct();
            for (int j = 0; j < listproduct.size(); j++) {
                System.out.println(listproduct.get(j).getNameProduct() + "----" + listproduct.get(j).getQuantity());
                thanhtien = thanhtien + (listproduct.get(j).getIdProduct() * listproduct.get(j).getQuantity());
            }
            System.out.println("thanh tien: " + thanhtien);
            System.out.println("--------");

        }
    }

    private void createOrder() {

        int choose = 0;

        do {

            System.out.println("Hay chon");
            System.out.println("1: Xem tat ca san pham");
            System.out.println("2: tim va mua");
            System.out.println("3: thoat");
            choose = Integer.parseInt(input.nextLine());
            switch (choose) {
                case 1: {
                    System.out.println("Danh dach tat ca san pham");
                    getListProduct();
                    break;
                }
                case 2: {
                    List<Product> list = new ArrayList<>();
                    addProduct(list);
                    Orders ord = new Orders();
                    ord.setUser(userDao.getUserByID(MainClass.idUserLogged));
                    ord.setListProduct(list);
                    orderDAO.insertOrder(ord);

                    orderDAO.insertOrderProduct(ord);

                    ord.output();
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).output();
                    }
                    break;

                }

            }
        } while (choose != 3);

    }

    private void updateOrder() {
        System.out.println("nhap idOrder can sua");
        int id = Integer.parseInt(new Scanner(System.in).nextLine());
        Orders order = orderDAO.getOrderById(id);
            List<Product> list = order.getListProduct();
            if (orderService.updateOrder(id)) {
                int x = 0;
                System.out.println("nhap thong tin can update: ");
                System.out.println("thoi gian update = thoi gian hien tai");
                System.out.println("update san pham trong hoa don");
                do {
                    System.out.println("1: them san pham vao hoa don: ");
                    System.out.println("2: sua thong tin san pham trong hoa don: ");
                    System.out.println("3: xoa san pham trong hoa don ");
                    System.out.println("4: thoat");
                    System.out.println("chon");
                    x = Integer.parseInt(new Scanner(System.in).nextLine());
                    switch (x) {
                        case 1: {
                            List<Product> listnew = new ArrayList<>();
                            addProduct(listnew);
                            order.setListProduct(listnew);
                            orderDAO.updateProductInOrder(id, order);
                            orderDAO.insertProductInOrderProduct(id, order);

                            break;
                        }
                        case 2: {
                            updateListProductByNameProduct(list);
                            order.setListProduct(list);
                            orderDAO.updateProductInOrder(id, order);
                            orderDAO.updateProductInOrderProduct(id, order);
                            break;
                        }
                        case 3: {

                            System.out.println("nhap ten san pham can xoa: ");
                            String name = input.nextLine();
                            orderDAO.deleteProductInOrder(id, name);
                            break;
                        }

                    }
                } while (x != 4);
            
        }
    }

    private void updateListProductByNameProduct(List<Product> list) {
        int n = 0;
        do {
            System.out.println("nhap ten san pham can sua");
            String name = input.nextLine();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getNameProduct().equals(name)) {
                    System.out.println("nhap so luong: ");
                    list.get(i).setQuantity(Integer.parseInt(input.nextLine()));
                }
            }
            System.out.println("chon 2 de update hoa don, chon so khac de tiep tuc sua: ");
            n = Integer.parseInt(new Scanner(System.in).nextLine());
        } while (n != 2);

    }

    private void addProduct(List<Product> listnew) {
        int x = 0;
        do {
            System.out.println("nhap ten san pham can tim");
            String name = new Scanner(System.in).nextLine();
            Product p = productDAO.getProductByName(name);

            if (Objects.isNull(p)) {
                return;
            }
            p.output();
            System.out.println("nhap so luong san pham: ");
            int quantity = Integer.parseInt(input.nextLine());

            product = new Product();
            product.setIdProduct(p.getIdProduct());
            product.setNameProduct(p.getNameProduct());
            product.setUser(p.getUser());
            product.setQuantity(quantity);
            product.setType(p.getType());
            product.setPrice(p.getPrice());
            listnew.add(product);
            System.out.println("chon 2 de tao hoa don, chon so khac de tiep tuc them san pham ");
            x = Integer.parseInt(input.nextLine());
        } while (x != 2);
    }

    private void getOrderByID() {
        System.out.println("nhap id can tim: ");
        int id = Integer.parseInt(new Scanner(System.in).nextLine());
        Orders order = orderDAO.getOrderById(id);
        order.output();
        double thanhtien = 0;
        System.out.print("ID product va so luong:  ");
        for (int i = 0; i < order.getListProduct().size(); i++) {
            int idProduct = order.getListProduct().get(i).getIdProduct();
            int quantity = order.getListProduct().get(i).getQuantity();
            product = productDAO.getProductByID(idProduct);
            thanhtien = thanhtien + (product.getPrice() * quantity);
            System.out.print(idProduct);
            System.out.println("   " + quantity + "\n");

        }
        System.out.println("thanh tien: " + thanhtien);

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

        } while (n != 15);

    }
}
