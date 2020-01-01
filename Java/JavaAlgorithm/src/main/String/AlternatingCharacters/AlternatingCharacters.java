package main.String.AlternatingCharacters;

import java.util.Scanner;

/*
    5
    AAAA
    BBBBB
    ABABABAB
    BABABA
    AAABBB

    3
    4
    0
    0
    4

    연속 문자가 발생할 시 Delete 횟수를 세는 문제
    간단하게 prev 변수를 선언해서 이전 값과 현재 값이 같은 경우라면 Delete 횟수를 증가시키게 하였다.

 */

public class AlternatingCharacters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int ans = 0;
        char prev = s.charAt(0);
        for(int i=1; i<s.length(); i++){
            if(prev == s.charAt(i)){
                ans++;
            }
            prev = s.charAt(i);
        }

        System.out.println(ans);
    }
}
