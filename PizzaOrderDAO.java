// File: PizzaOrderDAO.java
import java.sql.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PizzaOrderDAO {
    private final String URL = "jdbc:mysql://localhost:3306/pizzadb";
    private final String USER = "root";
    private final String PASS = "";

    public void insert(PizzaOrder order) {
        String sql = "INSERT INTO pizza_orders (name, mobile, size, toppings, total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order.getName());
            stmt.setString(2, order.getMobile());
            stmt.setString(3, order.getSize());
            stmt.setInt(4, order.getToppings());
            stmt.setDouble(5, order.getTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<PizzaOrder> getAll() {
        ObservableList<PizzaOrder> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM pizza_orders";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new PizzaOrder(
                    rs.getString("name"),
                    rs.getString("mobile"),
                    rs.getString("size"),
                    rs.getInt("toppings"),
                    rs.getDouble("total")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(PizzaOrder order) {
        String sql = "UPDATE pizza_orders SET name=?, mobile=?, size=?, toppings=?, total=? WHERE mobile=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order.getName());
            stmt.setString(2, order.getMobile());
            stmt.setString(3, order.getSize());
            stmt.setInt(4, order.getToppings());
            stmt.setDouble(5, order.getTotal());
            stmt.setString(6, order.getMobile());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(PizzaOrder order) {
        String sql = "DELETE FROM pizza_orders WHERE mobile=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order.getMobile());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
