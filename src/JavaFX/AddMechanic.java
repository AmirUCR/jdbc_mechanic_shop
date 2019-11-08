package JavaFX;

import Connection.ConnectionClass;
import javafx.application.Platform;
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

public class AddMechanic {

    public TextField txtMechanicFName;
    public TextField txtMechanicLName;
    public TextField txtMechanicYear;

    public Button btnMechanicCreate;
    public Button btnMechanicReturn;

    public Label lblYearError;
    public Label lblError;

    @FXML
    protected void initialize() {
        Platform.runLater(() -> btnMechanicReturn.requestFocus());
    }

    public void handleAddAction() {
        PreparedStatement stmt;

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            List<List<String>> data = connectionClass.executeQueryAndReturnResult("SELECT MAX(id) FROM mechanic");
            int id = Integer.parseInt(data.get(0).get(0)) + 1;

            if (txtMechanicLName.getText().length() < 1 || txtMechanicFName.getText().length() < 1) {
                lblError.setText("All fields are required.");
                lblError.setOpacity(1);
                return;
            }

            int year_int;
            try {
                year_int = Integer.parseInt(txtMechanicYear.getText());
                lblYearError.setOpacity(0);
            } catch (NumberFormatException e) {
                lblYearError.setOpacity(1);
                return;
            }

            stmt = connection.prepareStatement("INSERT INTO mechanic (id, fname, lname, experience) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, id);
            stmt.setString(2, txtMechanicFName.getText());
            stmt.setString(3, txtMechanicLName.getText());
            stmt.setInt(4, year_int);
            stmt.executeUpdate();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Summary.fxml"));
                Parent summary = fxmlLoader.load();

                Summary summaryController = fxmlLoader.<Summary>getController();
                summaryController.SetLabel("Mechanic " + txtMechanicFName.getText() + " " + txtMechanicLName.getText() + " ID " + id + " added successfully!");

                Stage stage = new Stage();
                stage.setTitle("Success!!");
                stage.setScene(new Scene(summary));
                stage.showAndWait();
                Stage thisStage = (Stage) btnMechanicCreate.getScene().getWindow();
                thisStage.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCancelAction() {
        Stage stage = (Stage) btnMechanicReturn.getScene().getWindow();
        stage.close();
    }

    public void handleKeyPressed() {
        lblError.setOpacity(0);
    }
}
