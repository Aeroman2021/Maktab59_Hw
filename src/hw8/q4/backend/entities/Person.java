package hw8.q4.backend.entities;

public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
    private Long contract;

    public Person(Integer id,String firstName, String lastName,Long contract) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contract = contract;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getContract() {
        return contract;
    }

    public void setContract(Long contract) {
        this.contract = contract;
    }
}
