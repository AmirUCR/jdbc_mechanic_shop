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

public class CarsBefore1995With50KMiles implements Initializable {
    private int row = 1;

    public TableView<CarsBeforeTable> table;
    public TableColumn<CarsBeforeTable, String> colRow;
    public TableColumn<CarsBeforeTable, String> colMake;
    public TableColumn<CarsBeforeTable, String> colModel;
    public TableColumn<CarsBeforeTable, String> colYear;
    public TableColumn<CarsBeforeTable, String> colOdometer;

    private ObservableList<CarsBeforeTable> oblist = FXCollections.observableArrayList();

    public Button btnReturn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> btnReturn.requestFocus());

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            table.setPlaceholder(new Label("No rows to display"));

            ResultSet rs = connection.createStatement().executeQuery("SELECT car.make, car.model, car.year, service_request.odometer FROM car, service_request WHERE car.vin = service_request.car_vin AND car.year < 1995 AND service_request.odometer >= 50000 ORDER BY service_request.odometer DESC");

            while(rs.next()) {
                oblist.add(new CarsBeforeTable(Integer.toString(row), rs.getString("make"), rs.getString("model"), rs.getString("year"), rs.getString("odometer")));
                row++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        colRow.setCellValueFactory(new PropertyValueFactory<>("row"));
        colMake.setCellValueFactory(new PropertyValueFactory<>("make"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colOdometer.setCellValueFactory(new PropertyValueFactory<>("odometer"));

        table.setItems(oblist);
    }

    public void handleCancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }

}
