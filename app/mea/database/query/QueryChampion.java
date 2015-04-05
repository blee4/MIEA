package mea.database.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import mea.app.util.MeaLogger;
import mea.database.CreateTables.TableName;
import mea.database.DatabaseDriver;

/**
 * @deprecated may not need this as we can just grab champion info off the Match object
 */
@Deprecated
public final class QueryChampion
{
    private Connection connection = DatabaseDriver.getConnection();

    private final String CHAMPION_TABLE_NAME = TableName.MEA_TABLE_CHAMPIONS.getName();

    /**
     * Inserts a champion into the database.
     * @param matchId
     * @param championId
     * @return True if the champion was successfully inserted into the database.
     */
    public int createChampion(final long matchId, final long championId)
    {
        String insertMatch = "INSERT INTO " + CHAMPION_TABLE_NAME + " (matchId, championId) VALUES (?, ?)";

        try
        {
            MeaLogger.log(Level.INFO, "Executing statement: \n" + "   " + insertMatch);
            PreparedStatement statement = connection.prepareStatement(insertMatch);
            statement.setLong(1, matchId);
            statement.setLong(2, championId);
            int modifiedRows = statement.executeUpdate();
            MeaLogger.log(Level.INFO, "Modified rows: " + modifiedRows);

            return modifiedRows;
        }
        catch (SQLException e)
        {
            MeaLogger.log(Level.WARNING, "Failed to insert champion", e);
        }

        return -1;
    }
}
