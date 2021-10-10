package hw8.q4.backend.entities;

public class Coach extends Person {

    private Integer teamId;

    public Coach(Integer id, String firstName, String lastName, Long contract, Integer teamId) {
        super(id, firstName, lastName, contract);
        this.teamId = teamId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
