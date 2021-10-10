package hw8.q4.backend.entities;

public class PlayerTransfer {

    private  Integer playerId;
    private Integer firstTeamId;
    private Integer secondTeamId;
    private Long contract;
    private Long salary;

    public PlayerTransfer(Integer playerId,Integer firstTeamId, Integer secondTeamId, Long contract, Long salary) {

        this.playerId = playerId;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.contract = contract;
        this.salary = salary;
    }



    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getFirstTeamId() {
        return firstTeamId;
    }

    public void setFirstTeamId(Integer firstTeamId) {
        this.firstTeamId = firstTeamId;
    }

    public Integer getSecondTeamId() {
        return secondTeamId;
    }

    public void setSecondTeamId(Integer secondTeamId) {
        this.secondTeamId = secondTeamId;
    }

    public Long getContract() {
        return contract;
    }

    public void setContract(Long contract) {
        this.contract = contract;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

}
