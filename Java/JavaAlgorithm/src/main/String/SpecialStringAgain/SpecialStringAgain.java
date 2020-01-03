package main.String.SpecialStringAgain;

import java.util.ArrayList;
import java.util.Scanner;
/*
    실패 긴 문자열에서 TimeOver
 */

public class SpecialStringAgain {
    private static boolean isSpecial(String substr){
        int size = substr.length();
        char target = substr.charAt(0);
        for(int i=0; i<size/2; i++){
            if(substr.charAt(i) != target || substr.charAt(size-i-1) != target){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        ArrayList<String> ans = new ArrayList<>();
        for(int i=0; i<n; i++){
            ans.add(""+s.charAt(i));
        }

        for(int i=0; i<n; i++){
            for(int j=i+1; j<=n; j++){
                String substr = s.substring(i,j);
                if(substr.length() == 1) continue;
                if(isSpecial(substr)){
                    ans.add(substr);
                }
            }
        }

        System.out.println(ans.size());

    }
}
