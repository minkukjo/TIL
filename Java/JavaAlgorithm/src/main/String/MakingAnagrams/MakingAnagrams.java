package main.String.MakingAnagrams;

import java.util.HashMap;
import java.util.Scanner;

/*
    cde
    abc
    4

    HashMap 2개로 풀어보려 했으나 안풀리더라
    답지를 봤는데 alphabet 배열에 해당 알파벳을 저장해놓고
    a에서 더한 후 b에서 빼서 그 절대값만큼이 많거나 적은 개수이므로
    그만큼 sum에 더해주면 빼야할 값 계산 가능
 */

public class MakingAnagrams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        int ans = 0;
        int[] alphabet = new int[26];
        for(char c : a.toCharArray()){
            alphabet[c-'a']++;
        }
        for(char c : b.toCharArray()){
            alphabet[c-'a']--;
        }

        for(int i : alphabet){
            ans += Math.abs(i);
        }

        System.out.println(ans);


    }
}
