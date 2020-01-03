package main.String.SpecialStringAgain;

import java.util.Scanner;

/*
    4
    aaaa
    10
    답지를 봐도 모르겠는 문제는 처음이네.
    진짜 어렵다. 머릿속엔 팰린드롬으로 계산한다는 생각이 강하게 박혀서 다른 로직을 떠올리기 힘들었다.
    솔직히 지금도 잘 이해안되는 문제.
    다시 풀라해도 못풀겠다.
 */
public class SpecialStringAgainRetry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        int counter = s.length();
        int consec=  1;
        int midIndex = -1;

        for(int i=1; i<n; i++){
            if(s.charAt(i) == s.charAt(i-1)){
                counter += consec;
                consec++;
                if(midIndex> 0){
                    // midIndex값이 0보다 크다는 것은 aba, cbc같은 것이 이미 검증되었기 때문에 midIndex-consec을 한 값과 i가 같은지만 보면 추가 가능
                    if((midIndex-consec) >0 && s.charAt(midIndex-consec) == s.charAt(i)){
                        counter++;
                    }else{
                        midIndex = -1;
                    }
                }
            }else{
                consec = 1;
                if( (i-2) >= 0 && s.charAt(i-2) == s.charAt(i)){
                    counter++;
                    midIndex = i-1;
                }else{
                    midIndex = 1;
                }
            }
        }

        System.out.println(counter);
    }
}
