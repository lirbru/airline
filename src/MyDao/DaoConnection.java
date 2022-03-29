package MyDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/***********/

public class DaoConnection {
    Connection connection = null;
    Statement stm = null;

    public DaoConnection() {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Airline",
                    "postgres",
                    "1234"
            );
            System.out.println("connection OK");
            stm = connection.createStatement();
//
//            }

        }catch (Exception e) {
            System.out.println("errorrrr" + e.getMessage());
        }
    }

    public Statement getStm() {
        return stm;
    }

    protected void finalize() throws SQLException {
        stm.close();
        connection.close();
        System.out.println("in finalize");
    }


}
