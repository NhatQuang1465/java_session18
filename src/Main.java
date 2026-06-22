import java.sql.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc =
                new Scanner(System.in);

        ProductManager manager =
                new ProductManager();

        while (true) {

            try {

                System.out.println(
                        "\n******** PRODUCT MANAGEMENT ********");

                System.out.println(
                        "1. Danh sách sản phẩm");

                System.out.println(
                        "2. Thêm mới sản phẩm");

                System.out.println(
                        "3. Cập nhật sản phẩm");

                System.out.println(
                        "4. Xóa sản phẩm");

                System.out.println(
                        "5. Tìm kiếm theo tên");

                System.out.println(
                        "6. Sắp xếp giá tăng dần");

                System.out.println(
                        "7. Thống kê theo danh mục");

                System.out.println(
                        "8. Thoát");

                System.out.print(
                        "Lựa chọn: ");

                int choice =
                        Integer.parseInt(
                                sc.nextLine());

                switch (choice) {

                    case 1:

                        manager.getAllProducts();
                        break;

                    case 2:

                        System.out.print("Tên: ");
                        String name = sc.nextLine();

                        System.out.print("Giá: ");
                        double price =
                                Double.parseDouble(
                                        sc.nextLine());

                        System.out.print("Tiêu đề: ");
                        String title =
                                sc.nextLine();

                        System.out.print("Danh mục: ");
                        String catalog =
                                sc.nextLine();

                        Product p =
                                new Product(
                                        name,
                                        price,
                                        title,
                                        new Date(
                                                System.currentTimeMillis()),
                                        catalog,
                                        true
                                );

                        manager.addProduct(p);

                        break;

                    case 3:

                        System.out.print("ID: ");
                        int id =
                                Integer.parseInt(
                                        sc.nextLine());

                        System.out.print("Tên mới: ");
                        name = sc.nextLine();

                        System.out.print("Giá mới: ");
                        price =
                                Double.parseDouble(
                                        sc.nextLine());

                        System.out.print("Tiêu đề mới: ");
                        title =
                                sc.nextLine();

                        System.out.print("Danh mục mới: ");
                        catalog =
                                sc.nextLine();

                        manager.updateProduct(
                                id,
                                new Product(
                                        name,
                                        price,
                                        title,
                                        new Date(
                                                System.currentTimeMillis()),
                                        catalog,
                                        true
                                ));

                        break;

                    case 4:

                        System.out.print("ID: ");

                        manager.deleteProduct(
                                Integer.parseInt(
                                        sc.nextLine()));

                        break;

                    case 5:

                        System.out.print("Tên cần tìm: ");

                        manager.searchProduct(
                                sc.nextLine());

                        break;

                    case 6:

                        manager.sortByPriceAsc();
                        break;

                    case 7:

                        manager.statisticCatalog();
                        break;

                    case 8:

                        System.exit(0);
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Sai kiểu dữ liệu!");

            } catch (Exception e) {

                System.out.println(
                        e.getMessage());
            }
        }
    }
}