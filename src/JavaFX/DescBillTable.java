package JavaFX;

public class DescBillTable {
    private String row, fname, lname, id, bill;

    public DescBillTable(String row, String fname, String lname, String id, String bill) {
        this.row = row;
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.bill = bill;
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

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}
