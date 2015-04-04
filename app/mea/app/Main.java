package mea.app;

import java.text.ParseException;
import java.util.logging.Level;

import mea.database.DatabaseDriver;
import mea.riotApi.Developer;
import mea.riotApi.dataRetriever.UrfMatchRetriever;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;

/**
 * Entry point for server setup.
 * MEA application will be started after setup has finished.
 */
public class Main
{
    public static void main(String[] args)
    {
        // Set API access settings and server region
        MeaLogger.log(Level.INFO, "Setting Riot API access settings");
        RiotAPI.setAPIKey(Developer.getDeveloperKey());
        RiotAPI.setMirror(Region.NA);
        RiotAPI.setRegion(Region.NA);

        // Check connection to the database
        MeaLogger.log(Level.INFO, "Checking database connection settings");
        DatabaseDriver.getConnection();

        // Finally, start the application if all the setup has passed
        MeaLogger.log(Level.INFO, "Starting MEA application!\n");
        new Main();
    }

    // TODO probably move this to a different class
    /** Start the application */
    public Main()
    {
        try
        {
            new UrfMatchRetriever();
        }
        catch (ParseException e)
        {
            MeaLogger.log(Level.SEVERE, "Failed to execute application", e);
            System.exit(1);
        }
    }
}
