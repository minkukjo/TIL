package main.Array.MinimumSwaps2;

import java.util.Scanner;

/*

    4
    4 3 1 2

    3

    의외로 쉬운 문제였다. 정렬에 필요한 최소한의 정렬횟수를 구하는 문제다.

    연속적인 정수이며 중복이 없다고 했기 때문에
    min값을 증가시켜 나가면서 최소값을 찾은 경우 swap을 하는 방식으로 문제를 해결하였다.

 */

public class MinimumSwaps2 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        int min = 1;
        int ans = 0;
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(arr[j] == min){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    min++;
                    ans++;
                    break;
                }
                else if(arr[i] == min){
                    min++;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}
