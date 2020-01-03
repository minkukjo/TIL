package main.String.ShelockandtheValidString;

import java.util.*;

/*
    주어진 문자열에서 단 하나의 문자를 제거하여 빈도수를 모두 똑같이 할 수 있는지 물어본 문제

    aabbcd
    NO
    답은 안보고 로직을 보고 해결한 문제.
    숫자를 key로, 빈도수를 value로 만든 hashMap2를 만들어놓고,
    key를 set으로 만들어서 key의 숫자가 1이라면 YES(하나밖에 없으니까 valid)
    2보다 크다면 3개이상의 value가 다른 값이 있기 때문에 not valid
    2인 경우라면 key가 더 작은 것의 수가 1이면 무조건 valid하고
    min의 값이 1은 아니지만 key값 둘의 차이가 1이고, 둘 중 더 큰값의 개수가 1이라면 valid로 간주
    그 외 경우라면 NO
 */

public class ShelockandValidString {
    public static <Interger> void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Character,Integer> hashMap = new HashMap<>();
        HashMap<Integer,Integer> hashMap2 = new HashMap<>();
        String s = sc.next();
        for(int i=0; i<s.length(); i++){
            hashMap.compute(s.charAt(i), (k,v) -> v == null ? 1 : ++v);
        }
        for(int i : hashMap.values()){
            hashMap2.compute(i,(k,v)-> v== null ? 1: ++v);
        }
        Set<Integer> values = new HashSet<>(hashMap2.keySet());
        if(values.size() == 1){
            System.out.println("YES");
        }
        else if(values.size() > 2){
            System.out.println("NO");
        }else{
            int[] arr = new int[3];
            int i= 0;
            for(int v : hashMap2.keySet()){
                arr[i++] = v;
            }
            int min = hashMap2.get(Math.min(arr[0],arr[1]));
            if(min == 1){
                System.out.println("YES");
            }else if(Math.abs(arr[0] - arr[1]) == 1 && hashMap2.get(Math.max(arr[0],arr[1])) == 1){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }

        }

    }
}
