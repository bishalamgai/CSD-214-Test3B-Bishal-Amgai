package com.example.csd214test3bbishalamgai;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;


import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController implements Initializable {
    public TableColumn id;
    public TableColumn customer;
    public TableColumn mobilenumber;
    public TableColumn size;
    public TableColumn toppings;
    public TableColumn totalbills;
    public TableView tableView;
    public TextField customerNameField;
    public TextField mobileNumberField;
    public TextField toppingsField;
    public CheckBox xlCheckBox;
    public CheckBox lCheckBox;
    public CheckBox mCheckBox;
    public CheckBox sCheckBox;
    ObservableList<PizzaOrders> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<PizzaOrders, Integer>("id"));
        customer.setCellValueFactory(new
                PropertyValueFactory<PizzaOrders, String>("customer_name"));
        mobilenumber.setCellValueFactory(new
                PropertyValueFactory<PizzaOrders, String>("mobile_number"));
        size.setCellValueFactory(new
                PropertyValueFactory<PizzaOrders, String>("pizza_size"));
        toppings.setCellValueFactory(new
                PropertyValueFactory<PizzaOrders, Integer>("number_of_toppings"));
        totalbills.setCellValueFactory(new
                PropertyValueFactory<PizzaOrders, Integer>("total_bill"));
        tableView.setItems(list);
    }

    public void Refresh(ActionEvent actionEvent) {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214_test3b_bishal";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `pizzaorders`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            tableView.getItems().clear();

// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String customer = resultSet.getString("customer_name");
                String mobilenumber = resultSet.getString("mobile_number");
                String size = resultSet.getString("pizza_size");
                int toppings = resultSet.getInt("number_of_toppings");
                int totalbills = resultSet.getInt("total_bill");
                tableView.getItems().add(new PizzaOrders(id, customer, mobilenumber,
                        size, toppings, totalbills));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Insert(ActionEvent actionEvent) {
        String customerName = customerNameField.getText();
        String mobileNumber = mobileNumberField.getText();
        String size = "";
        if (xlCheckBox.isSelected()) size = "XL";
        else if (lCheckBox.isSelected()) size = "L";
        else if (mCheckBox.isSelected()) size = "M";
        else if (sCheckBox.isSelected()) size = "S";
        int numToppings = Integer.parseInt(toppingsField.getText());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csd214_test3b_bishal");

            String query = "INSERT INTO pizza_orders (customer_name, mobile_number, size, toppings) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, mobileNumber);
            preparedStatement.setString(3, size);
            preparedStatement.setInt(4, numToppings);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Insert Success");
                alert.setHeaderText(null);
                alert.setContentText("Pizza order inserted successfully!");
                alert.showAndWait();
                Refresh(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Update(ActionEvent actionEvent) {
        PizzaOrders selectedOrder = (PizzaOrders) tableView.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a pizza order to update.");
            alert.showAndWait();
            return;
        }

        String customerName = customerNameField.getText();
        String mobileNumber = mobileNumberField.getText();
        String size = "";
        if (xlCheckBox.isSelected()) size = "XL";
        else if (lCheckBox.isSelected()) size = "L";
        else if (mCheckBox.isSelected()) size = "M";
        else if (sCheckBox.isSelected()) size = "S";
        int numToppings = Integer.parseInt(toppingsField.getText());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csd214_test3b_bishal");

            String query = "UPDATE pizza_orders SET customer_name=?, mobile_number=?, size=?, toppings=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, mobileNumber);
            preparedStatement.setString(3, size);
            preparedStatement.setInt(4, numToppings);
            preparedStatement.setInt(5, selectedOrder.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Success");
                alert.setHeaderText(null);
                alert.setContentText("Pizza order updated successfully!");
                alert.showAndWait();
                Refresh(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Delete(ActionEvent actionEvent) {
        // Get selected item from TableView
        PizzaOrders selectedOrder = (PizzaOrders) tableView.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a pizza order to delete.");
            alert.showAndWait();
            return;
        }
    }
}

  

