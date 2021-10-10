package hw8.q4.backend.entities;

import java.sql.Date;
import java.sql.Time;

public class Match {

    private int id;
    private Date date;
    private int homeTeamId;
    private int awayTeamId;
    private int stadiumId;


    public Match(int id, Date date, int homeTeamId, int awayTeamId, int stadiumId) {
        this.id = id;
        this.date = date;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.stadiumId = stadiumId;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public int getStadiumId() {
        return stadiumId;
    }

}





