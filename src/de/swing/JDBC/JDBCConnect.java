package de.swing.JDBC;

import java.sql.*;

public class JDBCConnect {
    PreparedStatement preparedStatement = null;

    public Connection openConnection(Connection connection) {

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/postgres", "gfish",
                    "gfish");
            return connection;

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;

        }

    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public void executeSQL(Connection connection, String query) throws SQLException {
        preparedStatement = connection.prepareStatement(query);

        // execute select SQL stetement
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {

            String userid = rs.getString("emp_name");
            System.out.println("userid : " + userid);

        }
    }

}
