package main.CountingValleys;

/*

    8
    UDDDUDUU
    mountain은 올라갔다 내려온 경우 1개의 마운틴을 등산했다고 한다.
    valley는 계곡, 아래쪽을 내려갔다 올라온 경우 1개의 계곡을 지나갔다고 한다.
    이때 주인공이 지나온 valley는 몇개인지를 찾는 문제.
    올라왔을때 위치가 0인 경우라면 계곡을 지나온 경우이므로 valley를 +1 해주었다.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class CountingValleys {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int position = 0;
        int valley = 0;
        int n = s.nextInt();
        String str = s.next();

        for(int i=0; i<n; i++){
            char temp = str.charAt(i);
            if(temp == 'U'){
                ++position;
                if(position == 0){
                  valley += 1;
                }
            }
            else{
                --position;
            }
        }

        System.out.println(valley);

    }
}
