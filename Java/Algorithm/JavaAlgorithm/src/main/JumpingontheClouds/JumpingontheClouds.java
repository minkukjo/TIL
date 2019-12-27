package main.JumpingontheClouds;

import java.util.ArrayList;
import java.util.Scanner;

/*
    7
    0 0 1 0 0 1 0

    answer : 4

    DP인가..? 했지만 갈 수 있는 경우가 1이랑 2밖에 없어서 브루투포스로 해결함.
    뇌운을 건너지 말고 최소한 적게 점프를 해서 목표점에 도달해야하는 문제.

 */

public class JumpingontheClouds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        int position = 0;
        int jump = 0;
        int n = sc.nextInt();
        for(int i=0; i<n; i++){
            arr.add(sc.nextInt());
        }

        while (position != n -1) {
            if (position + 2 <= n-1 && arr.get(position + 2) != 1 ) {
                position += 2;
                jump += 1;
            } else {
                position += 1;
                jump += 1;
            }
        }
        System.out.println(jump);

    }
}
