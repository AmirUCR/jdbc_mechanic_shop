package JavaFX;

import Connection.ConnectionClass;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class KCarsMostServices {

    public TableView<KCarsMostServicesTable> table;
    public TableColumn<KCarsMostServicesTable, String> colRow;
    public TableColumn<KCarsMostServicesTable, String> colMake;
    public TableColumn<KCarsMostServicesTable, String> colModel;
    public TableColumn<KCarsMostServicesTable, String> colVIN;
    public TableColumn<KCarsMostServicesTable, String> colCount;

    private ObservableList<KCarsMostServicesTable> oblist = FXCollections.observableArrayList();

    public Button btnReturn;
    public Button btnGo;

    public TextField txtHowMany;

    private void ListCars(String s) {
        Platform.runLater(() -> btnReturn.requestFocus());

        int row = 1;
        table.getItems().clear();

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT car.make, car.model, car.vin, COUNT(service_request.rid) AS count FROM car, service_request WHERE car.vin = service_request.car_vin GROUP BY (car.make, car.model, car.vin) ORDER BY COUNT(service_request.rid) DESC LIMIT " + s);

            while(rs.next()) {
                oblist.add(new KCarsMostServicesTable(Integer.toString(row), rs.getString("make"), rs.getString("model"), rs.getString("vin"), rs.getString("count")));
                row++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        colRow.setCellValueFactory(new PropertyValueFactory<>("row"));
        colMake.setCellValueFactory(new PropertyValueFactory<>("make"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colVIN.setCellValueFactory(new PropertyValueFactory<>("vin"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("count"));

        table.setItems(oblist);
    }

    public void handleCancelAction() {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }

    public void handleGoAction() {
        ListCars(txtHowMany.getText());
    }

}
