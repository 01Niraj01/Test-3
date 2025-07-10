import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import java.util.List;

public class PizzaOrderApp extends Application {

    TextField nameField, mobileField, toppingsField;
    CheckBox xl, l, m, s;
    TableView<PizzaOrder> table;

    @Override
    public void start(Stage stage) {
        Label title = new Label("Jarin's Pizza Ordering System");

        nameField = new TextField();
        nameField.setPromptText("Customer Name");

        mobileField = new TextField();
        mobileField.setPromptText("Mobile Number");

        xl = new CheckBox("XL");
        l = new CheckBox("L");
        m = new CheckBox("M");
        s = new CheckBox("S");

        toppingsField = new TextField();
        toppingsField.setPromptText("Number of Toppings");

        Button addBtn = new Button("Add");
        Button readBtn = new Button("Display");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");
        Button clearBtn = new Button("Clear");

        table = new TableView<>();
        TableColumn<PizzaOrder, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().name));
        TableColumn<PizzaOrder, String> mobileCol = new TableColumn<>("Mobile");
        mobileCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().mobile));
        TableColumn<PizzaOrder, String> sizeCol = new TableColumn<>("Size");
        sizeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().size));
        TableColumn<PizzaOrder, Integer> toppingsCol = new TableColumn<>("Toppings");
        toppingsCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().toppings).asObject());
        TableColumn<PizzaOrder, Double> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().total).asObject());

        table.getColumns().addAll(nameCol, mobileCol, sizeCol, toppingsCol, totalCol);

        VBox layout = new VBox(10, title, nameField, mobileField, xl, l, m, s,
                toppingsField, new HBox(10, addBtn, readBtn, updateBtn, deleteBtn, clearBtn), table);
        Scene scene = new Scene(layout, 600, 600);

        addBtn.setOnAction(e -> handleAdd());
        readBtn.setOnAction(e -> handleRead());
        updateBtn.setOnAction(e -> handleUpdate());
        deleteBtn.setOnAction(e -> handleDelete());
        clearBtn.setOnAction(e -> clearFields());
        table.setOnMouseClicked(e -> loadSelected());

        stage.setScene(scene);
        stage.setTitle("Pizza Ordering App");
        stage.show();
    }

    private void handleAdd() {
        try {
            String size = getSelectedSize();
            int toppings = Integer.parseInt(toppingsField.getText());

            PizzaOrder order = new PizzaOrder(nameField.getText(), mobileField.getText(), size, toppings);
            new PizzaOrderDAO().addOrder(order);
            System.out.println("Order Added");
            handleRead(); // refresh table
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleRead() {
        try {
            List<PizzaOrder> orders = new PizzaOrderDAO().getAllOrders();
            table.setItems(FXCollections.observableArrayList(orders));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleUpdate() {
        try {
            PizzaOrder selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                String size = getSelectedSize();
                int toppings = Integer.parseInt(toppingsField.getText());

                PizzaOrder updated = new PizzaOrder(nameField.getText(), mobileField.getText(), size, toppings);
                new PizzaOrderDAO().updateOrder(selected.id, updated);
                System.out.println("Order Updated");
                handleRead(); // refresh
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleDelete() {
        try {
            PizzaOrder selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                new PizzaOrderDAO().deleteOrder(selected.id);
                System.out.println("Order Deleted");
                handleRead(); // refresh
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadSelected() {
        PizzaOrder selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nameField.setText(selected.name);
            mobileField.setText(selected.mobile);
            toppingsField.setText(String.valueOf(selected.toppings));
            xl.setSelected("XL".equals(selected.size));
            l.setSelected("L".equals(selected.size));
            m.setSelected("M".equals(selected.size));
            s.setSelected("S".equals(selected.size));
        }
    }

    private void clearFields() {
        nameField.clear();
        mobileField.clear();
        toppingsField.clear();
        xl.setSelected(false);
        l.setSelected(false);
        m.setSelected(false);
        s.setSelected(false);
        table.getSelectionModel().clearSelection();
    }

    private String getSelectedSize() {
        if (xl.isSelected()) return "XL";
        if (l.isSelected()) return "L";
        if (m.isSelected()) return "M";
        if (s.isSelected()) return "S";
        return "";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
