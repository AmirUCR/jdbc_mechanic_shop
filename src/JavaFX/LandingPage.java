package JavaFX;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LandingPage {

    public void handleAddCustomer() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/AddCustomer.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Customer");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load Add Customer window.");
        }
    }

    public void handleAddCar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/AddCar.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Car");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load Add Car window.");
        }
    }

    public void handleAddMechanic() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/AddMechanic.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Mechanic");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load Add Mechanic window.");
        }
    }

    public void handleListUnder100() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ListCustomersWithBillLessThan100.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("List Under 100");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load List 100 window.");
        }
    }

    public void handleListMoreThan20() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ListCustomersWithMoreThan20Cars.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("List More Than 20");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load List More Than 20 window.");
        }
    }

    public void handleCarsBefore1995With50KMiles() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CarsBefore1995With50KMiles.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Cars Before 1995 With 50K Miles");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load List Before 1955 window.");
        }
    }

    public void handleDescBill() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/DescBill.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Customers With Descending Bills");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load List Desc Bills window.");
        }
    }

    public void handleKMostServices() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/KCarsMostServices.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Cars With Most Services");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load List K Most Services window.");
        }
    }

    public void handleCloseServiceRequest() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/CloseServiceRequest.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Close Service Request");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load Close Service Request window.");
        }
    }

    public void handleInsertServiceRequest() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/InsertServiceRequest.fxml"));
            Parent window = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Insert Service Request");
            stage.setScene(new Scene(window));
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load Insert Service Request window.");
        }
    }
}
