package hw8.q1.entities;



import hw8.q1.Turner;

import java.util.Scanner;

public class Angle implements Turner {
    Scanner scanner = new Scanner(System.in);
    private double angle;

    public Angle() {
        this.angle = 0;
        turn();
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public void turn() {
        System.out.println(" >> Enter the angle in degree :");
        double degree = scanner.nextDouble();
        this.angle += degree;
        System.out.println(" >> The new angle in  degree :" + getAngle());
    }
}
