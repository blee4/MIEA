package controllers.main;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.summoner.Summoner;

import controllers.asdf.UrfMatchRetriever;

public class Main
{
    private static Developer developer;

    public static void main(String[] args)
    {
        // Set API access settings
        try
        {
            developer = new Developer();
            RiotAPI.setAPIKey(developer.getDeveloperKey());

            // TODO remove
            RiotAPI.setMirror(Region.NA);
            RiotAPI.setRegion(Region.NA);
        }
        catch (IOException e)
        {
            MeaLogger.log(Level.SEVERE, "Cannot set API access settings", e);
            System.exit(1);
        }

        // Do application
        try
        {
            MeaLogger.log(Level.INFO, "Do application");
            new UrfMatchRetriever();
        }
        catch (ParseException e)
        {
            MeaLogger.log(Level.SEVERE, "Failed to execute application", e);
            System.exit(1);
        }
    }

    public Main()
    {
        // TODO remove
        Summoner summoner = RiotAPI.getSummonerByName("Emitted");
        System.out.println(summoner.getName() + " is a level " + summoner.getLevel() + " summoner on the NA server.");
    }
}
