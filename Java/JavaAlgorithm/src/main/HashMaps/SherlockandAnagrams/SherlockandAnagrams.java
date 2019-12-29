package main.HashMaps.SherlockandAnagrams;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    2
    abba
    abcd

    너무 어려워서 답지 봄. 문제도 잘 이해가 안될 지경.
    O(n^4)으로 풀리는 문제이다. 결국 모든 조합을 다 구해놓고
    해당 조합들을 하나씩 비교하는 문제
    이 문제는 내일 다시 풀어볼 것.

 */
public class SherlockandAnagrams {
    private static boolean isAnagrams(String s1, String s2){
        if(s1.length() != s2.length()) return false;

        int cnt = 0;
        boolean[] visit = new boolean[s2.length()];
        for(int i=0; i<s1.length(); i++){
            for(int j=0; j<s2.length(); j++){
                if(s1.charAt(i) == s2.charAt(j) && !visit[j]){
                    cnt++;
                    visit[j] = true;
                    break;
                }
            }
        }
        return cnt == s2.length();

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        List<String> list = new ArrayList<>();
        for(int i=0; i<s.length(); i++){
            for(int j=i+1; j<=s.length(); j++){
                list.add(s.substring(i,j));
            }
        }

        int ans = 0;
        for(int i=0;i<list.size(); i++){
            for(int j=i+1; j<list.size(); j++){
                if(isAnagrams(list.get(i),list.get(j))) ans++;
            }
        }
        System.out.println(ans);



    }
}
