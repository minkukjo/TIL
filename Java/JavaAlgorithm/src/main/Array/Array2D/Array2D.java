package main.Array.Array2D;

import java.util.Scanner;

/*
    -9 -9 -9  1 1 1
     0 -9  0  4 3 2
    -9 -9 -9  1 2 3
     0  0  8  6 6 0
     0  0  0 -2 0 0
     0  0  1  2 4 0

     28

     크기가 정해진 6x6 배열에서 모래시계 모양에 해당하는 수를 모두 더했을때 가장 큰 값을 구하는 문제

     삼성에선 이거보다 더 어려운 문제가 나왔었지 (테트리미노)
 */
public class Array2D {
    public static void main(String[] args) {
        int[][] arr = new int[6][6];
        int ans = Integer.MIN_VALUE;
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                if(j<=3 && i<=3){
                    int temp = arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i+1][j+1] + arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2];
                    ans = Math.max(temp,ans);
                }
            }
        }
        System.out.println(ans);
    }
}
