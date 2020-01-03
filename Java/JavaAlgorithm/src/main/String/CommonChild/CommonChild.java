package main.String.CommonChild;

import java.util.Scanner;

/*
    SHINCHAN
    NOHARAAA

    3

    두 문자 사이의 가장 긴 문자를 찾는 문제
    처음에 O(n^3)으로 풀었더니 터져서 디스커션을 봤음
    DP처럼 문제를 해결할 수 있더라
    배열을 선언하고 해당 배열의 이전 값을 현재 값으로 두고, 배열의 x-1, j-1중 더 큰 값을 현재 값으로 결정하는 알고리즘 이다.
 */

class CommonChild {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();

        int[][] c = new int[s1.length()+1][s2.length()+1];

        for(int i=1; i<=s1.length(); i++){
            for(int j=1; j<=s2.length(); j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)) c[i][j] = c[i-1][j-1]+1;
                else c[i][j] = Math.max(c[i][j-1],c[i-1][j]);
            }
        }
        System.out.println(c[s1.length()][s2.length()]);
    }
}
