# LIS 최장 증가 수열

최장 증가 수열은 가장 긴 증가하는 부분 수열을 의미한다.

[ 7, 2, 3, 8, 4, 5 ] → 해당 배열에서는 [2,3,4,5]가 LIS로 답은 4

구현 방법 (시간복잡도)
DP : O(N^2)
Lower Bound : O(NlogN)

```java

int dp[] = new int[arr.length];

for(int i=1; i<dp.length; i++){
  for(int j= i-1; j>=0; j--){
      if(arr[i] > arr[j]){
        dp[i] = Math.max(dp[i], dp[j] + 1);
      }
  }
}

int max = Arrays.stream(dp).max().getAsInt();
System.out.println(max+1);
```

DP로 풀어본 LIS

간단하게 O(N^2) 성능이 나온다.

이 최장 증가 수열은 Binary Search를 사용해서도 해결할 수 있다.

```java
    public static int lowerBound(int[] dp, int end, int n) {
        int start = 0;
        int ret = -1;
        while (start <= end) {
            int m = (start + end) / 2;
            if (dp[m] >= n) {
                end = m - 1;
                ret = m;
            } else {
                start = m + 1;
            }
        }
        return ret;
    }

    public static int binarySearchLSI(int[] arr,int n) {
        int[] dp = new int[n+1];
        int ans = 1;
        dp[1] = arr[1];
        int size = 1;
        for (int i = 2; i < n+1; i++) {
            if (dp[size] < arr[i]) {
                dp[++size] = arr[i];
            }else{
                int target = lowerBound(dp, size, arr[i]);
                dp[target] = arr[i];

            }
            ans = Math.max(ans, size);
        }
        return ans;
    }
  ```

  이진탐색을 사용할 경우 O(nlogn) 만에 해결할 수 있다.

  