package hw8.q4.backend.entities;

public class TeamMatchPerformance {
    private Integer teamid;
    private Integer matchId;
    private Integer goalsScored;
    private Integer recievedGoals;
    private Integer points;

    public TeamMatchPerformance(Integer teamid, Integer matchId, Integer goalsScored, Integer recievedGoals, Integer points) {
        this.teamid = teamid;
        this.matchId = matchId;
        this.goalsScored = goalsScored;
        this.recievedGoals = recievedGoals;
        this.points = points;
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(Integer goalsScored) {
        this.goalsScored = goalsScored;
    }

    public Integer getRecievedGoals() {
        return recievedGoals;
    }

    public void setRecievedGoals(Integer recievedGoals) {
        this.recievedGoals = recievedGoals;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
