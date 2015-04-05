package mea.database.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;

import mea.app.util.MeaLogger;
import mea.database.CreateTables.TableName;
import mea.database.DatabaseDriver;

public final class QueryMatch
{
    private Connection connection = DatabaseDriver.getConnection();

    private final String MATCH_TABLE_NAME = TableName.MEA_TABLE_MATCHES.getName();

    /**
     * Inserts a match into the database.
     * @param matchId
     * @param localDateTime
     * @return True if the match was successfully inserted into the database.
     */
    public int createMatch(final long matchId, final LocalDateTime localDateTime)
    {
        String insertMatch = "INSERT INTO " + MATCH_TABLE_NAME + " (matchId, matchDate, matchTime) VALUES (?, ?, ?)";

        try
        {
            // TODO check the match ID hasn't been recorded yet, else this will log exceptions everywhere
            MeaLogger.log(Level.INFO, "Executing statement: \n" + "   " + insertMatch);
            PreparedStatement statement = connection.prepareStatement(insertMatch);
            statement.setLong(1, matchId);
            statement.setDate(2, java.sql.Date.valueOf(localDateTime.toLocalDate()));
            statement.setTime(3, java.sql.Time.valueOf(localDateTime.toLocalTime()));

            int modifiedRows = statement.executeUpdate();
            MeaLogger.log(Level.INFO, "Modified rows: " + modifiedRows);
            return modifiedRows;
        }
        catch (SQLException e)
        {
            MeaLogger.log(Level.WARNING, "Failed to insert match", e);
        }

        return -1;
    }

}
