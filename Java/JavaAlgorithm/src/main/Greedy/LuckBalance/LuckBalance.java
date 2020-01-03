package main.Greedy.LuckBalance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LuckBalance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int result = 0;

        List<Integer> zeroList = new ArrayList<>();
        List<Integer> oneList = new ArrayList<>();

        for(int i=0; i<n; i++){
            int l = sc.nextInt();
            int t = sc.nextInt();
            if(t == 1){
                oneList.add(l);
            }else{
                zeroList.add(l);
            }
        }

        oneList.sort(Comparator.reverseOrder());

        for(int i=k; i<oneList.size(); i++){
            oneList.set(i, oneList.get(i)*-1);
        }
        for(int e : oneList){
            result += e;
        }

        for(int e : zeroList){
            result += e;
        }


    }
}
