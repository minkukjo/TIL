package main.String.ShelockandtheValidString;

import java.util.*;

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
            if(min == 1 && Math.min(hashMap2.get(arr[0]),hashMap2.get(arr[1])) == 1){
                System.out.println("YES");
            }else if(Math.abs(arr[0] - arr[1]) == 1 && hashMap2.get(Math.max(arr[0],arr[1])) == 1){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }

        }

    }
}
