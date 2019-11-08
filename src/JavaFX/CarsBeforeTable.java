package JavaFX;

public class CarsBeforeTable {
    private String row, make, model, year, odometer;

    public CarsBeforeTable(String row, String make, String model, String year, String odometer) {
        this.row = row;
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometer = odometer;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }
}
