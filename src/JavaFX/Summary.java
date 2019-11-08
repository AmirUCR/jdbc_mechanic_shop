package JavaFX;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class Summary {
    public Button summaryCloseBtn;
    public Label summaryLabel;

    public void SetLabel(String text) {
        summaryLabel.setText(text);
    }

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) summaryCloseBtn.getScene().getWindow();
        stage.close();
    }
}
