package hw8.q4.backend.entities;

public class Player extends Person {
    private Integer teamId;
    private Integer age;
    private PlayerPosition Position;
    private Long salary;


    public Player(Integer id, String firstName, String lastName, Long contract, Integer teamId, Integer age, PlayerPosition position, Long salary) {
        super(id, firstName, lastName, contract);
        this.teamId = teamId;
        this.age = age;
        Position = position;
        this.salary = salary;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public Integer getAge() {
        return age;
    }

    public PlayerPosition getPosition() {
        return Position;
    }

    public Long getSalary() {
        return salary;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPosition(PlayerPosition position) {
        Position = position;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}


