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

public class DescBill implements Initializable {
    private int row = 1;

    public TableView<DescBillTable> table;
    public TableColumn<DescBillTable, String> colRow;
    public TableColumn<DescBillTable, String> colFName;
    public TableColumn<DescBillTable, String> colLName;
    public TableColumn<DescBillTable, String> colID;
    public TableColumn<DescBillTable, String> colBill;

    private ObservableList<DescBillTable> obList = FXCollections.observableArrayList();

    public Button btnReturn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> btnReturn.requestFocus());

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT C.fname, C.lname, C.id, SUM(CR.bill) AS bill FROM closed_request CR, customer C, service_request SR WHERE CR.rid = SR.rid AND SR.customer_id = C.id GROUP BY (C.fname, C.lname, C.id) ORDER BY SUM(CR.bill) DESC");

            while(rs.next()) {
                obList.add(new DescBillTable(Integer.toString(row), rs.getString("fname"), rs.getString("lname"), rs.getString("id"), rs.getString("bill")));
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

        table.setItems(obList);
    }

    public void handleCancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }

}
