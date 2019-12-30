package main.HashMaps.SherlockandAnagrams;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SherlockandAnagramsAgain {
    static boolean isAnagrams(String s1, String s2){
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
        String str = sc.next();
        List<String> list = new ArrayList<>();
        int ans = 0;

        for(int i=0; i<str.length(); i++){
            for(int j=i+1; j<=str.length(); j++){
                list.add(str.substring(i,j));
            }
        }

        for(int i=0; i<list.size(); i++){
            for(int j=i+1; j<list.size(); j++){
                if(isAnagrams(list.get(i),list.get(j)))  ans++;
            }
        }

        System.out.println(ans);

    }
}
