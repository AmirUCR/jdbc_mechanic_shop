package JavaFX;

import Connection.ConnectionClass;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListCustomersWithMoreThan20Cars implements Initializable {
    private int row = 1;

    public TableView<MoreThan20CarsTable> table;
    public TableColumn<MoreThan20CarsTable, String> colRow;
    public TableColumn<MoreThan20CarsTable, String> colFName;
    public TableColumn<MoreThan20CarsTable, String> colLName;
    public TableColumn<MoreThan20CarsTable, String> colID;
    public TableColumn<MoreThan20CarsTable, String> colCars;

    private ObservableList<MoreThan20CarsTable> oblist = FXCollections.observableArrayList();

    public Button btnReturn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> btnReturn.requestFocus());

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT customer.fname, customer.lname, customer.id, COUNT(owns.car_vin) AS cars FROM owns, customer WHERE owns.customer_id = customer.id GROUP BY customer.fname, customer.lname, customer.id HAVING COUNT(owns.car_vin) > 20 ORDER BY COUNT(owns.car_vin) DESC");

            while (rs.next()) {
                oblist.add(new MoreThan20CarsTable(Integer.toString(row), rs.getString("fname"), rs.getString("lname"), rs.getString("id"), rs.getString("cars")));
                row++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        colRow.setCellValueFactory(new PropertyValueFactory<>("row"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCars.setCellValueFactory(new PropertyValueFactory<>("cars"));

        table.setItems(oblist);
    }

    public void handleCancelAction() {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }

}
