import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MyConnection {
    Connection connection = null;
    Statement stm = null;

    public MyConnection() {

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

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        stm.close();
        connection.close();
    }
}
