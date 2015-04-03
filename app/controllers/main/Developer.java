package controllers.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Developer
{
    private final String FILE_PATH = "C:\\Users\\Soprano\\Desktop\\";
    private final String DEVELOPER_KEY_FILENAME = "RiotDeveloperApiKey.txt";

    private String developerKey;

    public Developer() throws IOException
    {
        // Read file
        Path file = Paths.get(FILE_PATH + DEVELOPER_KEY_FILENAME);
        InputStream inputStream = Files.newInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line = null;
        while ((line = bufferedReader.readLine()) != null)
        {
            this.developerKey = line;
        }
    }

    public String getDeveloperKey()
    {
        return this.developerKey;
    }
}
