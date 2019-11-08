package Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionClass {
    public Connection connection = null;
    public Connection getConnection() {

        String dbName = "mechanic_shop";
        String dbPort = "5432";
        String username = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:" + dbPort + "/" + dbName + "?user=" + username + "&password=" + password;
            // obtain a physical connection
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        return connection;
    }

    public void executeUpdate (String sql) throws SQLException {
        // creates a statement object
        Statement stmt = this.connection.createStatement ();

        // issues the update instruction
        stmt.executeUpdate (sql);

        // close the instruction
        stmt.close ();
    }//end executeUpdate

    public int executeQuery (String query) throws SQLException {
        //creates a statement object
        Statement stmt = this.connection.createStatement();

        //issues the query instruction
        ResultSet rs = stmt.executeQuery(query);
        int rowCount = 0;

        //iterates through the result set and count number of results.
        if (rs.next()) {
            rowCount++;
        }

        stmt.close ();
        return rowCount;
    }

    public List<List<String>> executeQueryAndReturnResult(String query) throws SQLException {
        //creates a statement object
        Statement stmt = this.connection.createStatement();

        //issues the query instruction
        ResultSet rs = stmt.executeQuery(query);

        /*
         * obtains the metadata object for the returned result set.  The metadata
         * contains row and column info.
         */
        ResultSetMetaData rsmd = rs.getMetaData();
        int numCol = rsmd.getColumnCount();
        int rowCount = 0;

        //iterates through the result set and saves the data returned by the query.
        boolean outputHeader = false;

        List<List<String>> result = new ArrayList<List<String>>();

        while (rs.next()) {
            List<String> record = new ArrayList<String>();
            for (int i = 1; i <= numCol; ++i) {
                record.add(rs.getString(i));
            }
            result.add(record);
        }
        stmt.close ();
        return result;
    }//end executeQueryAndReturnResult
}
