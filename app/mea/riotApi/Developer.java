package mea.riotApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

import mea.app.MeaLogger;

public final class Developer
{
    private static String developerKey;

    private Developer()
    {
        // Read file
        Path file = Paths.get(DevResource.getDevKey());
        InputStream inputStream;
        try
        {
            inputStream = Files.newInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                Developer.developerKey = line;
            }
        }
        catch (IOException e)
        {
            MeaLogger.log(Level.SEVERE, "Cannot set API access settings", e);
            System.exit(1);
        }
    }

    public static String getDeveloperKey()
    {
        return Developer.developerKey;
    }
}
