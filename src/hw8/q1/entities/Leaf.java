package hw8.q1.entities;


import hw8.q1.Turner;

import java.util.Scanner;

public class Leaf implements Turner {
    Scanner input = new Scanner(System.in);
    private Color color;

    public Leaf() {
        color = Color.YELLOW;
        turn();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void turn() {
        System.out.println("Enter the current season season:Fall,Winter,Spring, or Summer");
        String season = input.next().toLowerCase();
        if (season.equals("fall"))
            this.color = Color.ORANGE;
        else if (season.equals("winter"))
            this.color = Color.RED;
        else
            this.color = Color.GREEN;

        System.out.println("The color if the leaf in this season is: " + getColor());

    }
}


