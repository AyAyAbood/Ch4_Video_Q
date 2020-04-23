/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch4_video_q;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AboOod_shbika99
 */
public class TableViewPaneController implements Initializable {

    @FXML
    private TextField txtfieldID;
    @FXML
    private TextField txtfieldName;
    @FXML
    private TextField txtfieldDepartment;
    @FXML
    private TextField txtfieldSalary;
    @FXML
    private TableColumn<Employee, Integer> tcID;
    @FXML
    private TableColumn<Employee, String> tcName;
    @FXML
    private TableColumn<Employee, String> tcDepartment;
    @FXML
    private TableColumn<Employee, Double> tcSalary;
    @FXML
    private Button buttonShow;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonReset;
    @FXML
    private TableView<Employee> tableView;

    Statement statement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/company?serverTimeZone=UTC", "root", "");
            this.statement = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcDepartment.setCellValueFactory(new PropertyValueFactory("department"));
        tcSalary.setCellValueFactory(new PropertyValueFactory("salary"));
        tableView.getSelectionModel().selectedItemProperty().addListener(event -> showSelectedEmployee());
        try {
            showEmployees();
        } catch (SQLException ex) {
            Logger.getLogger(TableViewPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void buttonShowHandle(ActionEvent event) throws Exception {
        ResultSet rs = this.statement.executeQuery("Select * From employee");
        tableView.getItems().clear();
        while (rs.next()) {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDepartment(rs.getString("department"));
            employee.setSalary(rs.getDouble("salary"));
            tableView.getItems().add(employee);
        }
    }

    @FXML
    private void buttonAddHandle(ActionEvent event) throws SQLException, Exception {
        if (!txtfieldID.getText().equals("") && !txtfieldName.getText().equals("") && !txtfieldDepartment.getText().equals("") && !txtfieldSalary.getText().equals("")) {
            Integer id = Integer.parseInt(txtfieldID.getText());
            String name = txtfieldName.getText();
            String department = txtfieldDepartment.getText();
            Double salary = Double.parseDouble(txtfieldSalary.getText());
            String sql = "insert into employee values (" + id + ",'" + name + "','" + department + "'," + salary + ");";
            resetContorls();
            this.statement.executeUpdate(sql);
            showEmployees();
        }
    }

    @FXML
    private void buttonUpdateHandle(ActionEvent event) throws SQLException {
        if (!txtfieldID.getText().equals("") && !txtfieldName.getText().equals("") && !txtfieldDepartment.getText().equals("") && !txtfieldSalary.getText().equals("")) {
        Integer id = Integer.parseInt(txtfieldID.getText());
        String name = txtfieldName.getText();
        String department = txtfieldDepartment.getText();
        Double salary = Double.parseDouble(txtfieldSalary.getText());
        String sql = "Update employee Set name='" + name + "', department='" + department + "', salary=" + salary + " Where id=" + id;
        resetContorls();
        this.statement.executeUpdate(sql);
        showEmployees();
        }
    }

    @FXML
    private void buttonDeleteHandle(ActionEvent event) throws SQLException {
        Employee employee = tableView.getSelectionModel().getSelectedItem();
        if (employee != null) {
            String sql = "delete from employee where id=" + employee.getId();
            resetContorls();
            this.statement.executeUpdate(sql);
            showEmployees();
        }
    }

    @FXML
    private void buttonResetHandle(ActionEvent event) {
        resetContorls();
    }

    private void resetContorls() {
        txtfieldID.setText("");
        txtfieldName.setText("");
        txtfieldDepartment.setText("");
        txtfieldSalary.setText("");
        tableView.getItems().clear();
    }

    private void showSelectedEmployee() {
        Employee employee = tableView.getSelectionModel().getSelectedItem();
        if (employee != null) {
            txtfieldID.setText(String.valueOf(employee.getId()));
            txtfieldName.setText(employee.getName());
            txtfieldDepartment.setText(employee.getDepartment());
            txtfieldSalary.setText(String.valueOf(employee.getSalary()));
        }
    }

    private void showEmployees() throws SQLException {
        ResultSet rs = this.statement.executeQuery("Select * From employee");
        tableView.getItems().clear();
        while (rs.next()) {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDepartment(rs.getString("department"));
            employee.setSalary(rs.getDouble("salary"));
            tableView.getItems().add(employee);
        }
    }
}
