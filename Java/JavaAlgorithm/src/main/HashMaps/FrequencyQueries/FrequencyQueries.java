package main.HashMaps.FrequencyQueries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
/*
    8
    1 5
    1 6
    3 2
    1 10
    1 10
    1 6
    2 5
    3 2

    0
    1

    처음에 리니어하게 서치하게 해버렸더니 100만개 데이터에서 타임아웃이 발생했다.
    결국 HashMap을 두개 선언해서 문제를 해결했다.
    좀 어이없게도 기존 예제 코드의 IO가 느려서 특정 테스트케이스가 통과가 안됐었다.
    InputStreamReader와 FileWriter를 이용하여 속도를 높여서 문제를 해결하였다.
 */

public class FrequencyQueries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> queries = new ArrayList<List<Integer>>();
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        HashMap<Integer,Integer> freqMap = new HashMap<>();

        int n = sc.nextInt();
        for(int i=0; i<n; i++){
            int first = sc.nextInt();
            int second = sc.nextInt();
            List<Integer> temp = new ArrayList<>();
            temp.add(first);
            temp.add(second);
            queries.add(temp);
        }

        for(int i=0; i<n; i++){
            int operation = queries.get(i).get(0);
            int data = queries.get(i).get(1);
            if(operation == 1){

            }else if(operation == 2){

            }else{
            }
        }


    }
}
