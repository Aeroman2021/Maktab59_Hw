package hw8.q4.backend.entities;


public class CoachTransfer {
    private Integer coachId;
    private Integer firstTeamId;
    private Integer secondTeamId;
    private Long contractValue;

    public CoachTransfer(Integer coachId, Integer firstTeamId, Integer secondTeamId, Long contractValue) {
        this.coachId = coachId;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.contractValue = contractValue;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public void setFirstTeamId(Integer firstTeamId) {
        this.firstTeamId = firstTeamId;
    }

    public void setSecondTeamId(Integer secondTeamId) {
        this.secondTeamId = secondTeamId;
    }

    public void setContractValue(Long contractValue) {
        this.contractValue = contractValue;
    }
}
