package mea.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import mea.app.util.MeaLogger;

/** Class for creating database tables */
public final class CreateTables
{
    /** Enum for table names */
    public enum TableName
    {
        MEA_TABLE_MATCHES("mea_table_matches"), MEA_TABLE_CHAMPIONS("mea_table_champions");

        private String tableName;

        TableName(final String tableName)
        {
            this.tableName = tableName;
        }

        public String getName()
        {
            return tableName;
        }
    }

    /** List of create statements for all tables */
    private static final ArrayList<String> TABLE_CREATE_STATEMENTS = new ArrayList<String>();

    private static final Connection connection = DatabaseDriver.getConnection();

    private static CreateTables createTables;

    /** Puts all table names inside a list, for convenience */
    private CreateTables()
    {
        TABLE_CREATE_STATEMENTS.add(generateStatementMatches());
        TABLE_CREATE_STATEMENTS.add(generateStatementChampions());
    }

    /**
     * Convenience method to get all table names.
     * @return A list of table name strings.
     */
    public static ArrayList<String> getAllTableNames()
    {
        ArrayList<String> tableNames = new ArrayList<String>();
        tableNames.add(TableName.MEA_TABLE_MATCHES.getName());
        tableNames.add(TableName.MEA_TABLE_CHAMPIONS.getName());

        return tableNames;
    }

    /**
     * Create database tables if it doesn't exist.
     */
    public static void verifyTables()
    {
        if (createTables == null)
        {
            createTables = new CreateTables();
        }

        for (String tableCreateStatement : TABLE_CREATE_STATEMENTS)
        {
            try
            {
                MeaLogger.log(Level.INFO, "Executing database statement:" + "\n   " + tableCreateStatement);
                PreparedStatement statement = connection.prepareStatement(tableCreateStatement);
                int rowsCreated = statement.executeUpdate();
                if (rowsCreated == 1)
                {
                    MeaLogger.log(Level.INFO, "Table was successfully created. Rows created: " + rowsCreated);
                }
                else
                {
                    MeaLogger.log(Level.INFO, "Table already exists. Rows created: " + rowsCreated);
                }
            }
            catch (SQLException e)
            {
                MeaLogger.log(Level.SEVERE, "Failed to create database table", e);
                System.exit(1);
            }
        }
    }

    /**
     * Create a DB table that stores URF match data.
     * @return A string statement to create the table.
     */
    private String generateStatementMatches()
    {
        String createStatement = "CREATE TABLE IF NOT EXISTS " + TableName.MEA_TABLE_MATCHES.getName() + " (";
        createStatement += "matchId BIGINT NOT NULL UNIQUE, ";
        createStatement += "matchDate DATE, ";
        createStatement += "matchTime TIME)";

        return createStatement;
    }

    /**
     * Create a DB table that stores champion data.
     * @return A string statement to create the table.
     */
    private String generateStatementChampions()
    {
        String createStatement = "CREATE TABLE IF NOT EXISTS " + TableName.MEA_TABLE_CHAMPIONS.getName() + " (";
        createStatement += "matchId BIGINT NOT NULL, ";
        createStatement += "championId BIGINT NOT NULL)";

        return createStatement;
    }
}
