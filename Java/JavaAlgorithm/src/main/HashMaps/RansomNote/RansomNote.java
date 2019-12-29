package main.HashMaps.RansomNote;

import java.util.HashMap;
import java.util.Scanner;

/*
    6 4
    give me one grand today night
    give one grand today

    기본적인 HashMap을 사용할 줄 아는지 묻는 문제였다.
    위에 주어진 단어들을 HashMap에 저장하고, 아래쪽에 있는 단어가 존재하는지 찾는 문제
    HashMap 연습문제로 나쁘지않았다.

 */

public class RansomNote {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int magazineSize = sc.nextInt();
        int noteSize = sc.nextInt();
        boolean no = false;
        HashMap<String,Integer> hashMap = new HashMap<>();
        for(int i=0; i<magazineSize; i++){
            String temp = sc.next();
            int x = 1;
            if(hashMap.containsKey(temp)){
                x = hashMap.get(temp) +1;
            }
            hashMap.put(temp,x);
        }

        for(int i=0; i<noteSize; i++){
            String input = sc.next();
            if(hashMap.containsKey(input) && hashMap.get(input) > 0){
                hashMap.put(input,hashMap.get(input)-1);
            }else{
                no = true;
                break;
            }
        }
        if(no){
            System.out.println("No");
        }else{
            System.out.println("Yes");
        }

    }
}
