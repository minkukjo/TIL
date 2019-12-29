package main.WarmingUp.SockMerchant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/* Test Case
    9
    10 20 20 10 10 30 50 10 20
    양말의 색상이 주어졌을때 그 양말의 짝이 몇개가 되는지 찾는 문제.
    10이 4개이고 20이 3개 30이 1개 50이 1개.
    10은 2쌍, 20은 1쌍 나오므로 총 3쌍의 짝이 있는 양말이 존재.
    HashMap을 활용하여 문제를 해결하였다.
 */

public class SockMerchant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        int n;
        n = scanner.nextInt();
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(int i=0; i<n; i++){
            int temp = scanner.nextInt();
            arr.add(temp);
            int x = 1;
            if(hashMap.containsKey(temp)){
                x = hashMap.get(temp) +1;
            }
            hashMap.put(temp,x);
        }
        int ans = 0;
        for( int value : hashMap.values()){
            if(value >= 2){
                ans += value/2;
            }
        }

        System.out.println(ans);

    }
}
