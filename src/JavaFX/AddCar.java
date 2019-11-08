package JavaFX;

import Connection.ConnectionClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class AddCar {
    public TextField txtAddCarMake;
    public TextField txtAddCarModel;
    public TextField txtAddCarYear;
    public TextField txtAddCarVIN;
    public TextField txtCustomerID;

    public Button btnAddCar;
    public Button btnAddCarReturn;

    public Label lblVINError;
    public Label lblYearError;
    public Label lblIDError;
    public Label lblMakeError;
    public Label lblModelError;
    public Label lblVINCounter;

    @FXML
    protected void initialize() {
        Platform.runLater(() -> btnAddCarReturn.requestFocus());
    }

    public void handleAddAction(ActionEvent actionEvent) {
        lblVINError.setOpacity(0);
        lblYearError.setOpacity(0);
        lblIDError.setOpacity(0);
        lblMakeError.setOpacity(0);
        lblModelError.setOpacity(0);
        PreparedStatement stmt = null;

        try {
            if (txtCustomerID.getText().length() < 1) {
                lblIDError.setOpacity(1);
                return;
            }

            if (txtAddCarVIN.getText().length() != 16) {
                lblVINError.setOpacity(1);
                return;
            }

            if (txtAddCarMake.getText().length() < 1) {
                lblMakeError.setOpacity(1);
                return;
            }

            if (txtAddCarModel.getText().length() < 1) {
                lblModelError.setOpacity(1);
                return;
            }

            int year_int;
            try {
                year_int = Integer.parseInt(txtAddCarYear.getText());
            } catch (NumberFormatException e) {
                lblYearError.setOpacity(1);
                return;
            }

            int id;
            try {
                id = Integer.parseInt(txtCustomerID.getText());
            } catch (NumberFormatException e) {
                lblIDError.setOpacity(1);
                return;
            }


            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            List<List<String>> data = connectionClass.executeQueryAndReturnResult("SELECT MAX(ownership_id) FROM owns");
            int ownership_id = Integer.parseInt(data.get(0).get(0)) + 1;

            stmt = connection.prepareStatement("INSERT INTO car (vin, make, model, year) VALUES (?, ?, ?, ?)");
            stmt.setString(1, txtAddCarVIN.getText());
            stmt.setString(2, txtAddCarMake.getText());
            stmt.setString(3, txtAddCarModel.getText());
            stmt.setInt(4, year_int);
            stmt.executeUpdate();

            stmt = connection.prepareStatement("INSERT INTO owns (ownership_id, customer_id, car_vin) VALUES (?, ?, ?)");
            stmt.setInt(1, ownership_id);
            stmt.setInt(2, id);
            stmt.setString(3, txtAddCarVIN.getText());
            stmt.executeUpdate();

            Platform.runLater(() -> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Summary.fxml"));
                    Parent summary = fxmlLoader.load();

                    Summary summaryController = fxmlLoader.<Summary>getController();
                    summaryController.SetLabel("Car " + txtAddCarMake.getText() + " " + txtAddCarModel.getText() + " VIN " + txtAddCarVIN.getText() + " added successfully for customer ID " + txtCustomerID.getText() + "!");

                    Stage stage = new Stage();
                    stage.setTitle("Success!!");
                    stage.setScene(new Scene(summary));
                    stage.showAndWait();
                    Stage thisStage = (Stage) btnAddCarReturn.getScene().getWindow();
                    thisStage.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCancelAction() {
        Stage stage = (Stage) btnAddCarReturn.getScene().getWindow();
        stage.close();
    }

    public void handleClickedVIN() {
        lblVINCounter.setOpacity(1);
    }

    public void handleKeyPressedVIN() {
        lblVINCounter.setOpacity(1);
        int l = txtAddCarVIN.getText().length();

        if (l < 17) {
            lblVINCounter.setText(Integer.toString(l));
        } else {
            lblVINCounter.setText(Integer.toString(l) + "/16");
        }

        if (txtAddCarVIN.getText().length() == 0) {
            lblVINCounter.setOpacity(0);
        }
    }
}
