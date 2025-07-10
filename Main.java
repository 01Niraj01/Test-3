// File: Main.java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Main extends Application {
    TableView<PizzaOrder> table = new TableView<>();
    ObservableList<PizzaOrder> data = FXCollections.observableArrayList();
    PizzaOrderDAO dao = new PizzaOrderDAO();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label title = new Label("Niraj's Pizza Ordering System");
        TextField nameField = new TextField();
        nameField.setPromptText("Customer Name");
        TextField mobileField = new TextField();
        mobileField.setPromptText("Mobile Number");

        CheckBox xl = new CheckBox("XL");
        CheckBox l = new CheckBox("L");
        CheckBox m = new CheckBox("M");
        CheckBox s = new CheckBox("S");

        TextField toppingsField = new TextField();
        toppingsField.setPromptText("Number of Toppings");

        Button create = new Button("Create");
        Button read = new Button("Read");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        Button clear = new Button("Clear");

        TableColumn<PizzaOrder, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());

        TableColumn<PizzaOrder, String> mobileCol = new TableColumn<>("Mobile");
        mobileCol.setCellValueFactory(cell -> cell.getValue().mobileProperty());

        TableColumn<PizzaOrder, String> sizeCol = new TableColumn<>("Size");
        sizeCol.setCellValueFactory(cell -> cell.getValue().sizeProperty());

        TableColumn<PizzaOrder, Integer> toppingsCol = new TableColumn<>("Toppings");
        toppingsCol.setCellValueFactory(cell -> cell.getValue().toppingsProperty().asObject());

        TableColumn<PizzaOrder, Double> billCol = new TableColumn<>("Total Bill");
        billCol.setCellValueFactory(cell -> cell.getValue().totalProperty().asObject());

        table.getColumns().addAll(nameCol, mobileCol, sizeCol, toppingsCol, billCol);
        table.setItems(data);

        create.setOnAction(e -> {
            String size = xl.isSelected() ? "XL" : l.isSelected() ? "L" : m.isSelected() ? "M" : s.isSelected() ? "S" : "";
            int toppings = Integer.parseInt(toppingsField.getText());
            double total = PizzaOrder.calculateTotal(size, toppings);
            PizzaOrder order = new PizzaOrder(nameField.getText(), mobileField.getText(), size, toppings, total);
            dao.insert(order);
            data.add(order);
        });

        read.setOnAction(e -> {
            data.setAll(dao.getAll());
        });

        update.setOnAction(e -> {
            PizzaOrder selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setName(nameField.getText());
                selected.setMobile(mobileField.getText());
                selected.setSize(xl.isSelected() ? "XL" : l.isSelected() ? "L" : m.isSelected() ? "M" : s.isSelected() ? "S" : "");
                selected.setToppings(Integer.parseInt(toppingsField.getText()));
                selected.setTotal(PizzaOrder.calculateTotal(selected.getSize(), selected.getToppings()));
                dao.update(selected);
                table.refresh();
            }
        });

        delete.setOnAction(e -> {
            PizzaOrder selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                dao.delete(selected);
                data.remove(selected);
            }
        });

        clear.setOnAction(e -> {
            nameField.clear();
            mobileField.clear();
            toppingsField.clear();
            xl.setSelected(false); l.setSelected(false); m.setSelected(false); s.setSelected(false);
        });

        VBox root = new VBox(10, title, nameField, mobileField, new HBox(10, xl, l, m, s), toppingsField, new HBox(10, create, read, update, delete, clear), table);
        stage.setScene(new Scene(root, 700, 600));
        stage.setTitle("Pizza Order Page");
        stage.show();
    }
}