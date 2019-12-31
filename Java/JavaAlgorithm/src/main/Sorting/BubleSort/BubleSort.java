package main.Sorting.BubleSort;

import java.util.Scanner;

/*
    단순히 버블소트의 스왑 회수를 계산하는 문제 매우 쉬웠다.
 */

public class BubleSort {

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];
        int swap_count = 0;

        for(int i=0;i<n; i++){
            a[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    swap_count++;
                }
            }
        }
        for(int i=0;i<n; i++){
            System.out.print(a[i] + " ");
        }

        System.out.println(swap_count);

    }
}
