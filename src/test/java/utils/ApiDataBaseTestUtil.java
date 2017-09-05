package utils;

import steps.setup.BaseTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static utils.PropertyGetterUtil.getPropertyValue;

public class ApiDataBaseTestUtil extends BaseTest {

    private static String dbUrl = getPropertyValue("db.server.url");
    private static String login = getPropertyValue("db.user.login");
    private static String password = getPropertyValue("db.user.password");

    protected Connection connectToDB() {

        Connection connection = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("DB driver is failed. First of all check your driver and retry");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(dbUrl, login, password);
        } catch (SQLException e) {
            System.out.println("DB connection is failed. First of all check your url, login, password and retry");
            e.printStackTrace();
        }

        return connection;
    }

    protected void sendQueryToDB(String sqlQuery) {

        Connection connection = connectToDB();

        try {
            try {
                Statement statement = connection.createStatement();
                statement.addBatch(sqlQuery);
                int [] results = statement.executeBatch();
                System.out.println(results[0] + " Rows updated in DataBase");
            } catch (SQLException e) {
                System.out.println("DB request is failed. First of all check your SQL request syntax");
                e.printStackTrace();
            }

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getErrorCode() + e.getMessage());
                }
            }
        }
    }

    protected String deleteAllErrorCommands() {

        return "DELETE FROM amc_commands WHERE command_state = 'EXECUTION_SKIPPED' OR command_state = 'ERROR'";
    }
}
