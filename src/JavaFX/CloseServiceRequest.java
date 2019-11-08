package JavaFX;

import Connection.ConnectionClass;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class CloseServiceRequest {

    public Label lblError;

    public TextField txtReqNum;
    public TextField txtMechID;
    public TextArea taComments;
    public TextField txtBill;

    public Button btnSubmit;
    public Button btnCancel;

    @FXML
    protected void initialize() {
        Platform.runLater(() -> btnCancel.requestFocus());
    }

    public void handleKeyboardPressedAction() {
        lblError.setOpacity(0);
    }

    public void handleSubmitAction() {

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            List<List<String>> data = connectionClass.executeQueryAndReturnResult("SELECT MAX(wid) FROM closed_request");
            int wid = Integer.parseInt(data.get(0).get(0)) + 1;

            if (txtReqNum.getText().length() < 1) {
                lblError.setText("Service request number is required!");
                lblError.setOpacity(1);
                return;
            }

            try {
                Integer.parseInt(txtReqNum.getText());
            } catch (NumberFormatException e) {
                lblError.setText("Service request must be a number!");
                lblError.setOpacity(1);
                return;
            }

            if (txtMechID.getText().length() < 1) {
                lblError.setText("Mechanic ID is required!");
                lblError.setOpacity(1);
                return;
            }

            try {
                Integer.parseInt(txtMechID.getText());
            } catch (NumberFormatException e) {
                lblError.setText("Mechanic ID must be a number!");
                lblError.setOpacity(1);
                return;
            }

            if (txtBill.getText().length() < 1) {
                lblError.setText("Free service? Generous. Bill is required!");
                lblError.setOpacity(1);
                return;
            }

            try {
                Integer.parseInt(txtBill.getText());
            } catch (NumberFormatException e) {
                lblError.setText("Bill must be a number!");
                lblError.setOpacity(1);
                return;
            }

            if (taComments.getText().length() < 1) {
                lblError.setText("Comments are required!");
                lblError.setOpacity(1);
                return;
            }

            if (connectionClass.executeQuery("SELECT rid FROM service_request WHERE rid = '" + txtReqNum.getText() + "'") == 0) {
                lblError.setText("Whoops. Service request number does not exist.");
                lblError.setOpacity(1);
                return;
            }

            if (connectionClass.executeQuery("SELECT rid FROM closed_request WHERE closed_request.rid = '" + txtReqNum.getText() + "'") > 0) {
                lblError.setText("Whoops. Service already closed.");
                lblError.setOpacity(1);
                return;
            }

            if (connectionClass.executeQuery("SELECT id FROM mechanic WHERE id = '" + txtMechID.getText() + "'") == 0) {
                lblError.setText("Whoops. Mechanic ID number does not exist.");
                lblError.setOpacity(1);
                return;
            }

            Date sqlDate = new Date(System.currentTimeMillis());

            connectionClass.executeUpdate("INSERT INTO closed_request (wid, rid, mid, date, comment, bill) VALUES (" + wid + ", " + txtReqNum.getText() + ", " + txtMechID.getText() + ", '" + sqlDate + "', '" + taComments.getText() + "', " + txtBill.getText() + ")");

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Summary.fxml"));
                Parent summary = fxmlLoader.load();

                Summary summaryController = fxmlLoader.getController();
                summaryController.SetLabel("Closed request " + txtReqNum.getText() + " with mechanic ID " + txtMechID.getText() + ". Bill total is $" + txtBill.getText() + ". Closed request ID is " + wid);

                Stage stage = new Stage();
                stage.setTitle("Success!!");
                stage.setScene(new Scene(summary));
                stage.showAndWait();
                Stage thisStage = (Stage) btnSubmit.getScene().getWindow();
                thisStage.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleCancelAction() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
