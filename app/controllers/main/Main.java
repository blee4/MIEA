package controllers.main;

import java.io.IOException;
import java.util.logging.Level;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.summoner.Summoner;

public class Main
{
    private static Developer developer;

    public static void main(String[] args)
    {
        try
        {
            developer = new Developer();
            RiotAPI.setAPIKey(developer.getDeveloperKey());
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();

            System.exit(1);
        }

        // TODO remove
        RiotAPI.setMirror(Region.NA);
        RiotAPI.setRegion(Region.NA);

        new Main();
    }

    /** Do stuff */
    public Main()
    {
        MeaLogger.log(Level.INFO, "Doing stuff");

        Summoner summoner = RiotAPI.getSummonerByName("Emitted");
        System.out.println(summoner.getName() + " is a level " + summoner.getLevel() + " summoner on the NA server.");
    }
}
