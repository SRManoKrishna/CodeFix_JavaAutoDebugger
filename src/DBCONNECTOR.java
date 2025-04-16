import java.sql.*;

public class DBCONNECTOR {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";

    private static Connection connection;

    public static Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("✅ Connected to Oracle DB");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void saveCodeLog(String filename, String content, String result) {
        try {
            connect();
            String sql = "INSERT INTO code_logs (filename, content, syntax_result) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, filename);
            stmt.setString(2, content);
            stmt.setString(3, result);
            stmt.executeUpdate();
            System.out.println("📦 Code log saved to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
