package JavaFX;

public class InsertRequestCustomersTable {
    String row, fname, lname, id;

    public InsertRequestCustomersTable(String row, String fname, String lname, String id) {
        this.row = row;
        this.fname = fname;
        this.lname = lname;
        this.id = id;
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
}
