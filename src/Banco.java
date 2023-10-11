import java.sql.*;

public class Banco {
    private static final String urlBanco = "jdbc:mysql://localhost:3306/aulajavabancodados";
    private static final String username = "root";
    private static final String password = "root";


    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(urlBanco, username, password);
    }
}
