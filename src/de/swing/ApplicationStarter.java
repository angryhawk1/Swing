package de.swing;

import de.swing.JDBC.JDBCConnect;

import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationStarter {
    public static void main(String args[]) throws SQLException {
        JDBCConnect jdbcConnect = new JDBCConnect();
        Connection connection = null;
        String sql = "SELECT * FROM EMPLOYEE";
        connection = jdbcConnect.openConnection(connection);
        jdbcConnect.executeSQL(connection, sql);
        jdbcConnect.closeConnection(connection);
    }
}
