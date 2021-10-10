package hw8.q4.backend.entities;

public class PlayermatchPerformance {

    private Integer playerId;
    private Integer matchId;
    private Integer scoredGoals;


    public PlayermatchPerformance(Integer playerId, Integer matchId, Integer scoredGoals) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.scoredGoals = scoredGoals;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(Integer scoredGoals) {
        this.scoredGoals = scoredGoals;
    }
}
