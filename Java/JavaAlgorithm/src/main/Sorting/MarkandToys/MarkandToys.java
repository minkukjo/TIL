package main.Sorting.MarkandToys;

import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

/*
    7 50
    1 12 5 111 200 1000 10

    4

    주어진 값에 최대한 많은 선물을 고르는 문제
    소팅해놓고 처음부터 골라가면 끝
 */
public class MarkandToys {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int k = sc.nextInt();
        int ans = 0;
        int[] prices = new int[n];
        for(int i=0; i<n; i++){
            prices[i] = sc.nextInt();
        }

        Arrays.sort(prices);

        for(int i=0; i<n; i++){
            if(prices[i] <= k){
                k -= prices[i];
                ans++;
            }else{
                break;
            }
        }

        System.out.println(ans);


    }
}
