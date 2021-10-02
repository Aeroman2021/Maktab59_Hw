package hw8.q1.entities;


import hw8.q1.Turner;

public class Pancake implements Turner {

    private String side;

    public Pancake() {
        this.side = "firstSide";
        turn();
    }

    public String getSide() {
        return side;
    }

    @Override
    public void turn() {
        this.side = "secondSide";
        System.out.println(" >> The side of the pancake : " + getSide());
    }
}
