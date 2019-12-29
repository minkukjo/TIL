package main.HashMaps.TwoStrings;

import java.util.HashMap;
import java.util.Scanner;
/*
    2
    hello
    world
    hi
    world

    YES
    NO
    해당 문자열의 substring이 존재하는지 찾는 문제.
    간단하게 문자열을 문자로 쪼개서 Hash에 저장하여 풀 수 있었다.
 */
public class TwoStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        boolean yes = false;
        HashMap<Character, Character> hashMap = new HashMap<>();
        for(int i=0; i<s1.length(); i++){
            hashMap.put(s1.charAt(i),s1.charAt(i));
        }
        for(int i=0; i<s2.length(); i++){
            if(hashMap.containsKey(s2.charAt(i))){
                yes = true;
            }
        }
        if(yes){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }
}
