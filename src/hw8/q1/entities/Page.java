package hw8.q1.entities;


import hw8.q1.Turner;

import java.util.Scanner;

public class Page implements Turner {
    Scanner scanner = new Scanner(System.in);
    private int pageNumber;

    public Page() {
        this.pageNumber = 1;
        turn();
    }

    private int getPageNumber(){
        return pageNumber;
    }

    @Override
    public void turn() {
        System.out.println(" >> Enter the page that you wish to study :");
        int page = scanner.nextInt();
        this.pageNumber += page;
        System.out.println(" >> Now you can study the page " + getPageNumber());
    }
}
