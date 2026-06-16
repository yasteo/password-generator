import java.sql.*;

public class DatabaseWorker {

    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:data/database.db");
        String sql = """
                CREATE TABLE IF NOT EXISTS info (
                service_name TEXT PRIMARY KEY,
                number INTEGER NOT NULL
                )
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
    }

    public void insert(String serviceName, int number) throws SQLException {
        String sql = """
                INSERT INTO info(service_name, number) 
                VALUES (?, ?)
                ON CONFLICT (service_name)
                DO UPDATE SET
                    number = excluded.number;
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, serviceName);
            ps.setInt(2, number);
            ps.executeUpdate();
        }
    }

    public String getAll() throws SQLException {
        var builder = new StringBuilder("\n");
        String sql = "SELECT * FROM info";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                builder.append(rs.getString("service_name"))
                        .append(" - ")
                        .append(rs.getInt("number"))
                        .append("\n");
            }
        }
        builder.append("\n");
        return builder.toString();
    }

    public String getNumberByServiceName(String serviceName) throws SQLException {
        var builder = new StringBuilder("\n");
        String sql = "SELECT * FROM info WHERE service_name = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, serviceName);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                builder.append(rs.getString("service_name"))
                        .append(" - ")
                        .append(rs.getInt("number"))
                        .append("\n\n");
            }
        }
        ps.close();
        return (builder.length() == 1) ? "Нет данных\n\n" : builder.toString();
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

}
