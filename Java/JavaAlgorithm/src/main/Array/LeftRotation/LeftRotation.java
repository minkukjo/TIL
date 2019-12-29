package main.Array.LeftRotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
    5 4
    1 2 3 4 5

    5 1 2 3 4

    배열을 왼쪽으로 움직이는 문제

    Collections의 rotate 메소드를 이용하였으나, 더 깔끔한 방법은 modulation을 하는 것.


 */


public class LeftRotation {
    public void MySolution(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        int n,r;
        n = sc.nextInt();
        r = sc.nextInt();
        for(int i=0; i<n; i++){
            arr.add(sc.nextInt());
        }

        Collections.rotate(arr, -r);

        arr.forEach(System.out::println);
    }
    public static void main(String[] args) {
        LeftRotation leftRotation = new LeftRotation();
        //leftRotation.MySolution();

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        int n,r;
        n = sc.nextInt();
        r = sc.nextInt();
        for(int i=0; i<n; i++){
            arr.add(sc.nextInt());
        }

        int[] ans = new int[n];

        for(int i=0;i <n; i++){
            int move = (i+r)%n;
            ans[i] = arr.get(move);
            System.out.println(ans[i] + " ");
        }


    }
}
