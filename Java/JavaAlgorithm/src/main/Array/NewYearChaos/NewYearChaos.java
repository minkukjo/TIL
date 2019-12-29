package main.Array.NewYearChaos;

import java.util.Scanner;

/*
    2
    5
    2 1 5 3 4
    5
    2 5 1 3 4

    3
    Too chaotic

    못풀어서 답지본 문제.

    문제는 정렬된 인원들이 최대 2명까지 새치기가 가능함.
    주어진 배열 위치처럼 되기 위한 최소 새치기 가능 횟수를 구하는 문제.
    미디움은 정말 난이도가 높다. 계속 도전해봐야할 듯.


 */
public class NewYearChaos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test_case = sc.nextInt();

        for(int i=0; i<test_case; i++){
            int n = sc.nextInt();
            int[] q = new int[n];
            int ans = 0;
            for(int j=0; j<n; j++){
                q[j] = sc.nextInt();
            }

            for( int j=0; j<n; j++){
                if(q[j] - (j+1) > 2){
                    System.out.println("Chaos");
                    break;
                }

                for(int k= Math.max(0,q[j] -2); k<j; k++){
                    if(q[k] > q[j]){
                        ans++;
                    }
                }
            }
            System.out.println(ans);

        }
    }
}
