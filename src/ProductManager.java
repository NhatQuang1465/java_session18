import java.sql.*;

public class ProductManager {

    public void getAllProducts() {

        String sql =
                "SELECT * FROM Product ORDER BY Product_Id";

        try (
                Connection conn =
                        DBConnection.getConnection();

                Statement st =
                        conn.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt("Product_Id")
                                + " | "
                                + rs.getString("Product_Name")
                                + " | "
                                + rs.getDouble("Product_Price")
                                + " | "
                                + rs.getString("Product_Catalog")
                );
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public void addProduct(Product p) {

        String sql =
                "{call add_product(?,?,?,?,?,?)}";

        Connection conn = null;

        try {

            conn =
                    DBConnection.getConnection();

            conn.setAutoCommit(false);

            CallableStatement cs =
                    conn.prepareCall(sql);

            cs.setString(1, p.getProductName());
            cs.setDouble(2, p.getProductPrice());
            cs.setString(3, p.getProductTitle());
            cs.setDate(4, p.getProductCreated());
            cs.setString(5, p.getProductCatalog());
            cs.setBoolean(6, p.isProductStatus());

            cs.execute();

            conn.commit();

            System.out.println(
                    "Thêm thành công");

        } catch (Exception e) {

            try {

                if (conn != null) {
                    conn.rollback();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println(e.getMessage());

        } finally {

            try {

                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateProduct(
            int id,
            Product p) {

        String sql =
                "{call update_product(?,?,?,?,?,?)}";

        try (
                Connection conn =
                        DBConnection.getConnection();

                CallableStatement cs =
                        conn.prepareCall(sql)
        ) {

            cs.setInt(1, id);
            cs.setString(2, p.getProductName());
            cs.setDouble(3, p.getProductPrice());
            cs.setString(4, p.getProductTitle());
            cs.setString(5, p.getProductCatalog());
            cs.setBoolean(6, p.isProductStatus());

            cs.execute();

            System.out.println(
                    "Cập nhật thành công");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public void deleteProduct(int id) {

        String sql =
                "{call delete_product(?)}";

        try (
                Connection conn =
                        DBConnection.getConnection();

                CallableStatement cs =
                        conn.prepareCall(sql)
        ) {

            cs.setInt(1, id);

            cs.execute();

            System.out.println(
                    "Xóa thành công");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public void searchProduct(
            String keyword) {

        String sql =
                """
                SELECT *
                FROM Product
                WHERE Product_Name
                ILIKE ?
                """;

        try (
                Connection conn =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    "%" + keyword + "%");

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("Product_Id")
                                + " | "
                                + rs.getString("Product_Name")
                                + " | "
                                + rs.getDouble("Product_Price")
                );
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public void sortByPriceAsc() {

        String sql =
                """
                SELECT *
                FROM Product
                ORDER BY Product_Price ASC
                """;

        try (
                Connection conn =
                        DBConnection.getConnection();

                Statement st =
                        conn.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getString("Product_Name")
                                + " | "
                                + rs.getDouble("Product_Price")
                );
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public void statisticCatalog() {

        String sql =
                """
                SELECT Product_Catalog,
                       COUNT(*)
                FROM Product
                GROUP BY Product_Catalog
                """;

        try (
                Connection conn =
                        DBConnection.getConnection();

                Statement st =
                        conn.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getString(1)
                                + " : "
                                + rs.getInt(2)
                );
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }
}