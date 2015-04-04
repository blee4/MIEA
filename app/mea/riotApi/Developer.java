package mea.riotApi;

import java.util.logging.Level;

import mea.app.util.FileReader;
import mea.app.util.MeaLogger;

/** A class containing developer-specific details used to access the Riot API */
public final class Developer
{
    private static Developer developer;

    /** Riot API developer key */
    private String developerKey;

    /** Set the developer key from file */
    private Developer()
    {
        // Read file
        developerKey = FileReader.readFile(DevResource.getDevKey());

        if ((developerKey == null) || developerKey.equals(""))
        {
            MeaLogger.log(Level.SEVERE, "Cannot set API settings because the developer key was empty");
            System.exit(1);
        }
    }

    /**
     * @return Gets the developer key.
     */
    public static String getDeveloperKey()
    {
        if (developer == null)
        {
            developer = new Developer();
        }

        return developer.developerKey;
    }
}
