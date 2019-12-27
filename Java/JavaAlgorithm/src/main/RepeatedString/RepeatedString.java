package main.RepeatedString;

import java.util.Scanner;

/*
    aba
    10
    ans : 7

    a
    1000000000000
    ans : 1000000000000

    조금은 생각하게 만든 문제.
    단순히 문자열을 더해가려고 했으나 1조 짜리의 input이 있어서 수학을 써야하는구나 라고 생각함.
    반복되는 문자열의 크기를 n만큼 나누고, 반복 문자열 안의 a 개수와 곱하여 a 개수를 센 다음,
    반복 문자열 크기를 n으로 나머지 연산하여 남은 값만큼 loop를 돌며 남은 a 개수를 세어서 더해주면 끝.
 */

public class RepeatedString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        long n = sc.nextLong();
        long aCount = 0L;
        long sizeLeft = 0L;
        int size = str.length();
        for(int i=0; i<size; i++){
            if(str.charAt(i) == 'a'){
                aCount++;
            }
        }
        aCount = aCount * (n/size);
        sizeLeft = n % size;
        for(int i=0; i<sizeLeft; i++){
            if(str.charAt(i) == 'a'){
                aCount++;
            }
        }
        System.out.println(aCount);
    }
}
