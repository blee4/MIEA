package mea.riotApi.dataRetriever;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import mea.app.util.MeaLogger;

import com.robrua.orianna.api.core.AsyncRiotAPI;
import com.robrua.orianna.type.api.Action;
import com.robrua.orianna.type.core.match.Match;
import com.robrua.orianna.type.core.match.Participant;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.exception.APIException;

/**
 * Retrieves a randomized set of URF matches.
 */
public class UrfMatchRetriever
{
    public UrfMatchRetriever() throws ParseException
    {
        // TODO change this to dynamically retrieve the current time in 5 minute intervals
        String baseDate = "Apr 01 2015 19:10:00.000 AEDT";

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
        Date date = dateFormat.parse(baseDate);
        MeaLogger.log(Level.INFO, "Requesting URF matches at: " + date.toString());

        AsyncRiotAPI.getURFMatches(new Action<List<Match>>()
        {
            @Override
            public void handle(APIException e)
            {
                MeaLogger.log(Level.WARNING, "Could not retrieve URF matches from API", e);
            }

            @Override
            public void perform(List<Match> matches)
            {
                retrieveData(matches);
            }
        }, date);
    }

    /**
     * Retrieves all match data from the list of matches.
     * 
     * @param matches A list of matches.
     */
    private void retrieveData(final List<Match> matches)
    {
        // TODO store everything in a db or something
        for (Match match : matches)
        {
            System.out.println("Match ID: " + match.getID());
            System.out.println("   Queue type: " + match.getQueueType().toString());
            System.out.println("   Duration: " + convertDuration(match.getDuration()).toString());

            for (Champion champion : getChampions(match))
            {
                System.out.println("      Champion: " + champion.getName());
            }

            System.out.println();
        }
    }

    /**
     * Converts a large number of seconds into hours:minutes:seconds.
     * 
     * @param durationInSeconds Total number of seconds.
     * @return LocalTime object containing the converted duration.
     */
    private LocalTime convertDuration(long durationInSeconds)
    {
        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        int seconds = (int) (durationInSeconds % SECONDS_IN_A_MINUTE);
        int totalMinutes = (int) (durationInSeconds / SECONDS_IN_A_MINUTE);
        int minutes = totalMinutes % MINUTES_IN_AN_HOUR;
        int hours = totalMinutes / MINUTES_IN_AN_HOUR;

        return LocalTime.of(hours, minutes, seconds);
    }

    /**
     * Gets the list of champions played in a particular match.
     * 
     * @param match The match.
     * @return ArrayList of champions used in the match.
     */
    private ArrayList<Champion> getChampions(final Match match)
    {
        ArrayList<Champion> champions = new ArrayList<Champion>();
        for (Participant participant : match.getParticipants())
        {
            champions.add(participant.getChampion());
        }
        return champions;
    }
}
