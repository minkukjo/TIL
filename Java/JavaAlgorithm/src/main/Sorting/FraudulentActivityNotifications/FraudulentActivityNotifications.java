package main.Sorting.FraudulentActivityNotifications;

import java.util.Scanner;

/*
    9 5
    2 3 4 2 3 6 8 4 5

    2

    단순히 n^2으로 푸니까 안풀린다.
    Counting Sort + 중간값 구하기로 문제 해결.
    답지봤는데도 이해가 안되더라, 잘 보니까 문제도 중간값을 구하는 문제였고
    그러기 위해서는 반드시 소팅을 해야하는데 이 소팅을 직접 하는 것이 아닌 카운팅 소트의 배열에 개수를 센다.
    sum에 2를 곱해서 d인 경우라면 2*i+1, d가 작다면 i*2인데 로직을 모르겠네..

 */

public class FraudulentActivityNotifications {
    private static int getMedian(int[] count, int d){
        int sum = 0;
        for(int i=0; i<count.length; i++){
            sum += count[i];
            if( (2*sum) == d){
                return (2*i+1);
            }
            else if((2*sum) > d){
                return i*2;
            }
        }
        return 1;
    }
    public static int activityNotifications(int[] expenditure,int d){
        int[] count= new int[201];
        int ans = 0;
        for(int i=0; i<d; i++){
            count[expenditure[i]]++;
        }
        for(int i=d; i<expenditure.length; i++){
            int median = getMedian(count,d);
            if(median <= expenditure[i]){
                ans++;
            }
            count[expenditure[i-d]]--;
            count[expenditure[i]]++;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        int[] expenditure = new int[n];
        int ans = 0;
        for(int i=0; i<n; i++){
            expenditure[i] = sc.nextInt();
        }
        ans = activityNotifications(expenditure,d);
        System.out.println(ans);


    }
}
