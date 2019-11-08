package JavaFX;

public class InsertRequestCarsTable {
    private String row, make, model, vin;

    public InsertRequestCarsTable(String row, String make, String model, String vin) {
        this.row = row;
        this.make = make;
        this.model = model;
        this.vin = vin;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
