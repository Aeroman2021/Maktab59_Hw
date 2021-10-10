package hw8.q4.backend.entities;

public class Team {

    private Integer id;
    private TeamName name;
    private String captainName;
    private Integer coachId;
    private Integer stadiumId;
    private Integer cityId;

    public Team(Integer id, TeamName name, String captainName, Integer coachId, Integer stadiumId, Integer cityId) {
        this.id = id;
        this.name = name;
        this.captainName = captainName;
        this.coachId = coachId;
        this.stadiumId = stadiumId;
        this.cityId = cityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TeamName getName() {
        return name;
    }

    public void setName(TeamName name) {
        this.name = name;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public Integer getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Integer stadiumId) {
        this.stadiumId = stadiumId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }


}
