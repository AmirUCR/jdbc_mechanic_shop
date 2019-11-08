package JavaFX;

public class MoreThan20CarsTable {
    private String row, fname, lname, id, cars;

    public MoreThan20CarsTable(String row, String fname, String lname, String id, String cars) {
        this.row = row;
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.cars = cars;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCars() {
        return cars;
    }

    public void setCars(String numCars) {
        this.cars = cars;
    }
}
