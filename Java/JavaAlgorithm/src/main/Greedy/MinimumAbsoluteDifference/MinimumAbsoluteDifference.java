package main.Greedy.MinimumAbsoluteDifference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
    3
    3 -7 0

    3
    서로 다른 두 수의 차이 중 가장 작은 수를 찾는 문제였다.
    인접한 수의 차이가 가장 작을 것이라 생각하고
    배열을 정렬한 후 n-1만큼 돌며 인접한 인덱스 두개의 값을 구하여 문제를 해결하였다.
 */

public class MinimumAbsoluteDifference {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int ans = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        for(int i=0; i<n-1; i++){
            ans = Math.min(Math.abs( arr[i] - arr[i+1]),ans);
        }
        System.out.println(ans);

    }
}
