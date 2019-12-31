package main.Sorting.Comparator;

import java.util.Arrays;
import java.util.Scanner;

public class Comparator<P> {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Player[] player = new Player[n];
        Checker checker = new Checker();
        for(int i=0; i<n; i++){
            String name = sc.next();
            int score = sc.nextInt();
            player[i] = new Player(name,score);
        }

        Arrays.sort(player,checker);

        for(int i=0; i<n; i++){
            System.out.println(player[i].getName()+ " " + player[i].getScore());
        }
    }
}
