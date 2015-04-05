package mea.database;

import java.time.LocalDateTime;

import mea.database.query.QueryMatch;

import com.robrua.orianna.type.core.match.Match;

/** Entry point for adding/retrieving items out of the database */
public class QueryEntryPoint
{
    public boolean storeRetrievedMatches(Match match, LocalDateTime dateTime)
    {
        new QueryMatch().createMatch(match.getID(), dateTime);

        // TODO do we even need this since we can grab the champs right off the Match object?
        // // If we modify a row (ie added a match to the table), then also store the champions
        // if (modifiedRows == 1)
        // {
        //
        // new QueryChampion().createChampion(matchId, championId)
        // }

        return false;
    }
}
