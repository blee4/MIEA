package mea.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import mea.app.MeaLogger;

/** Driver for creating a connection to the database */
public final class DatabaseDriver
{
    // TODO move to a file
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "01001";
    private final String DB_URL = "jdbc:mysql://localhost:3306/test";

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    /** Connection to the database */
    private static Connection connection;

    /** Establishes a DB connection */
    private DatabaseDriver()
    {
        try
        {
            MeaLogger.log(Level.INFO, "Opening a connection to the databse");
            Class.forName(JDBC_DRIVER);
            DatabaseDriver.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            MeaLogger.log(Level.INFO, "Database connection successfully retrieved");
        }
        catch (ClassNotFoundException e)
        {
            MeaLogger.log(Level.SEVERE, "Tried to access the DB connector API [" + JDBC_DRIVER + "] but failed", e);
            System.exit(1);
        }
        catch (SQLException e)
        {
            String message = "Tried to connect to database using these credentials: ";
            message += "DB URL [" + DB_URL + "], DB Username [" + DB_USERNAME + "], DB Password [" + DB_PASSWORD + "] ";
            message += "but failed";

            MeaLogger.log(Level.SEVERE, message, e);
            System.exit(1);
        }
    }

    // /**
    // * Terminate the DB connection.
    // * @param p_connection The connection to close.
    // */
    // public void closeConnection(Connection p_connection)
    // {
    // try
    // {
    // System.out.println("Closing connection to database ..."); // TODO logger
    // p_connection.close();
    // }
    // catch (SQLException e)
    // {
    // System.out.println("Tried to close the database connection but failed with error:");
    // e.printStackTrace();
    // // Try again?
    // }
    // }

    /**
     * @return Gets the database Connection object.
     */
    public static Connection getConnection()
    {
        return DatabaseDriver.connection;
    }
}
