package hw8.q1.entities;
import hw8.q1.Turner;

public class Lights implements Turner {
    private String state;

    public Lights() {
        this.state = "turnOn";
        turn();
    }

    public String getState() {
        return state;
    }

    @Override
    public void turn() {
        this.state = "turnOff";
        System.out.println(" >> Now the lights is " +  getState());

    }
}
