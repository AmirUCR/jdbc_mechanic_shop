package JavaFX;

import Connection.ConnectionClass;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListCustomersWithBillLessThan100 implements Initializable {
    private int row = 1;

    public TableView<Under100Table> table;
    public TableColumn<Under100Table, String> colRow;
    public TableColumn<Under100Table, String> colFName;
    public TableColumn<Under100Table, String> colLName;
    public TableColumn<Under100Table, String> colID;
    public TableColumn<Under100Table, String> colBill;

    private ObservableList<Under100Table> oblist = FXCollections.observableArrayList();

    public Button btnReturn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> btnReturn.requestFocus());

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT customer.fname, customer.lname, customer.id, closed_request.bill FROM customer, closed_request, service_request WHERE closed_request.bill < 100 AND closed_request.rid = service_request.rid AND service_request.customer_id = customer.id ORDER BY closed_request.bill DESC");

            while(rs.next()) {
                oblist.add(new Under100Table(Integer.toString(row), rs.getString("fname"), rs.getString("lname"), rs.getString("id"), rs.getString("bill")));
                row++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        colRow.setCellValueFactory(new PropertyValueFactory<>("row"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBill.setCellValueFactory(new PropertyValueFactory<>("bill"));

        table.setItems(oblist);
    }

    public void handleCancelAction() {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }

}
