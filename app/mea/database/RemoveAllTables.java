package mea.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import mea.app.util.MeaLogger;

import org.junit.Assert;
import org.junit.Test;

/**
 * Quick-n-dirty helper class to remove all tables.
 * 
 * WARNING: IF YOU HAVE ANY EXISTING DATA THAT YOU WANT TO KEEP,
 * YOU SHOULD MODIFY THE TABLE INSTEAD OF DELETING IT !!!
 */
public class RemoveAllTables
{
    /** Iterate through all table names and deletes them if it exists */
    @Test
    public void deleteTables()
    {
        ArrayList<String> allTableNames = CreateTables.getAllTableNames();
        // allTableNames.add("mea_table");

        for (String tableName : allTableNames)
        {
            try
            {
                String dropStatement = "DROP TABLE IF EXISTS " + tableName + " CASCADE";

                MeaLogger.log(Level.INFO, "Executing table drop statement: " + "\n   " + dropStatement);
                PreparedStatement preparedstatement = DatabaseDriver.getConnection().prepareStatement(dropStatement);
                int rowsModified = preparedstatement.executeUpdate();

                MeaLogger.log(Level.WARNING, "Table drop statement was executed. Rows modified: " + rowsModified);
            }
            catch (SQLException e)
            {
                MeaLogger.log(Level.WARNING, "Table was not removed", e);
                Assert.fail();
            }
        }
    }
}
