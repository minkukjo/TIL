package main.HashMaps.CountTriplets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
    4 2
    1 2 2 4

    O(n^3)으로 풀었으나 몇몇 테스트케이스를 시간제한으로 통과못한다.
    즉 더 나은 성능으로 풀어야한다는 뜻

    HashMap 한개로 풀어보려고 했는데 안풀려서 답지를 보니 HashMap을 두개를 쓰네 하.. ㅠㅠ

    중간수를 저장하는 Hash와 마지막 수를 저장하는 Hash 두개를 둔다.

    loop는 array를 돌며 해당 숫자가 마지막수를 저장하는 hash에 있다면 value를 추가한다.
    없다면 중간수에 해당 값이 있는지를 보고 존재한다면 마지막 해쉬에 존재하는 값 + 중간 해쉬에 존재하는 값을 더한다.
    그 후 마지막에는 해당 값의 *r 한 값을 중간 해쉬값에 저장해주고 존재한다면 +1 해준다.
    이렇게 풀면 O(n)만에 끝낼 수 있네 신기하다.. 아직 많이 부족하다 더 열심히 하자
 */

public class CountTriplets {
    static void timeOver(int n, int r, int[] arr, int ans){
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                for(int k=j+1; k<n; k++){
                    if(arr[j]/r == arr[i] && arr[i]*r == arr[j] && arr[k] == arr[j]*r){
                        ans++;
                    }
                }
            }
        }

        System.out.println(ans);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n,r;
        long ans = 0;
        long result = 0;
        n = sc.nextInt();
        r = sc.nextInt();
        ArrayList<Long> arr = new ArrayList<>();
        HashMap<Long,Long> hashMap2 = new HashMap<>();
        HashMap<Long,Long> hashMap3 = new HashMap<>();
        for(int i=0; i<n; i++){
            arr.add(sc.nextLong());
        }

        for(int i=0; i<n; i++){
            result += hashMap3.getOrDefault(arr.get(i),0L);

            if(hashMap2.containsKey(arr.get(i))){
                hashMap3.put(arr.get(i)*r,hashMap3.getOrDefault(arr.get(i)*r, 0L)+hashMap2.get(arr.get(i)));
            }
            hashMap2.put(arr.get(i)*r,hashMap2.getOrDefault(arr.get(i)*r, 0L)+1);
        }

        System.out.println(ans);





    }
}
