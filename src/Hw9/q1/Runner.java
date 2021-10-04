package Hw9.q1;

import java.util.Random;
import java.util.TreeSet;

public class Runner {

    static TreeSet<Character> inputTreest1 = new TreeSet<Character>();
    static TreeSet<Character> inputTreest2 = new TreeSet<Character>();
    static TreeSet<Character> treeSet3 = new TreeSet<Character>();
    static TreeSet<Character> treeSet4 = new TreeSet<Character>();


    public static void main(String[] args) {

        inputTreest1 = treeSetRandomCharacterFiller();
        inputTreest2 = treeSetRandomCharacterFiller();
        MeetOfTreeSets(inputTreest1, inputTreest2);
        UnionOfTreeSets(inputTreest1, inputTreest2);

    }

    private static TreeSet<Character> treeSetRandomCharacterFiller() {
        TreeSet<Character> treeSet = new TreeSet<Character>();
        Random r = new Random();
        while (treeSet.size() < 10) {
            char c = (char) (r.nextInt(26) + 'a');
            treeSet.add(c);
        }
        System.out.println("This Char array contains: " + treeSet);
        return treeSet;
    }

    private static void MeetOfTreeSets(TreeSet<Character> treeSet1, TreeSet<Character> treeSet2) {
        treeSet3.addAll(treeSet1);
        treeSet3.addAll(treeSet2);
        System.out.println("The Meet of the treeSets is : " + treeSet3);
    }

    private static void UnionOfTreeSets(TreeSet<Character> treeSet1, TreeSet<Character> treeSet2) {
        for (Character currentCharacter : treeSet1) {
            if (treeSet2.contains(currentCharacter))
                treeSet4.add(currentCharacter);
        }
        System.out.println("The union of the treeSets is : " + treeSet4);
    }


}
