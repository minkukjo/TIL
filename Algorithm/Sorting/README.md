# Sorting Algorithm

 정렬 알고리즘은 n개의 숫자가 주어졌을 대 사용자가 지정한 기준에 맞게 정렬하려 출력하는 것을 의미한다.

 기본적으로 정렬은 O(N^2), O(nlogn)의 Time Complexity를 갖는다.

 그러나 비교 연산을 수행하지 않는 **Radix Sort**의 경우 O(kn)의 Time Complexity를 갖는 정렬도 존재한다.

 또한 위에서 언급한 내부정렬 외에도 디스크에서 추가적인 데이터를 가져와 정렬하는 **외부 정렬**(External Sort)에 대한 내용도 다뤄보려고 한다.

# N^2 Sorting Algorithm

일반적인 정렬은 전체 비교를 통해 O(n^2)의 Time Complexity를 갖는다.

# Selection Sort

```java
void selectionSort(int[] arr){
  for(int i=0; i<arr.length; i++){
    int temp = i;
    for(int j=i+1; j<arr.length; j++){
      if(arr[temp] >= arr[j]) temp = j;
    }
    swap(arr[i],arr[temp]);
  }
}
```

선택 정렬이라 부리는 이 정렬 방법은 이름에 걸맞게, 현재 위치에 들어갈 값을 찾아 정렬하는 방식이다.

정렬되지 않은 인덱스 맨 앞부터 그 이후 값 중 가장 작은 값을 찾는다.

찾으면 현재 인덱스 값과 최소 값 인덱스 값을 바꿔준다.

위 과정을 배열의 전체까지 순회하면 된다.

전체 비교를 진행하기 때문에 시간 복잡도는 O(n^2)이다.

# Insertion Sort

삽입 정렬은 현재 위치에서 그 이하 배열들을 비교하여 위치를 찾아 삽입하는 알고리즘 이다.

```java
void insertionSort(int[] arr){
  for(int i=1; i<arr.length; i++){
    int key = arr[i];
    int j = i-1;
    while(j>=0 && key < arr[j]){
      arr[j+1] = arr[j];
      j--;
    }
    v[j+1] = key;
  }
}
```

두번째 인덱스 부터 시작하며 비교 인덱스를 현재 인덱스 -1로 잡는다.

별도로 저장해 둔 삽입변수 (key)와 비교 인덱스(arr[j])를 비교한다.

삽입 변수의 값이 더 작다면 현재 인덱스로 비교 인덱스의 값을 저장하고, 비교 인덱스를 감소시켜 비교를 반복한다.
만약 삽입 변수가 비교 변수보다 더 크다면 비교 인덱스 +1에 삽입 변수를 저장한다.

삽입 정렬의 경우 전체 비교를 해야하며 교환 또한 해야하므로 최악의 경우 O(n^2)이 걸린다.

Best Case는 이미 정렬된 경우 이동이 없기 때문에 O(n)이다.

평균적으로 삽입정렬은 O(n^2)의 성능을 보인다.

# Bubble Sort

연속된 두 인덱스를 비교하여 오른쪽 값이 더 작은 경우 스왑을 하는 방식의 정렬 방법이다.

구현이 매우 간단하다는 장점이 있다.

```java
void bubbleSort(int[] arr){
  for(int i=0; i<arr.length-1; i++){
    for(int j=1; j<arr.length-i; j++){
      if(arr[j-1] > arr[j]){
        swap(arr[j-1],arr[j]);
      }
    }
  }
}
```

이 알고리즘은 전체를 비교해야하는 알고리즘이므로 역시나 시간 복잡도는 O(n^2)이다.

# NlogN Sorting Algorithm

지금부터는 정렬계의 혁명인 O(nlogn) 정렬 알고리즘을 배워보도록 하겠다.

nlogn은 비교 정렬 중 가장 빠른 성능을 내는 정렬 알고리즘이다. 

n^2과 nlogn은 데이터 크기가 커질수록 그 성능 차이가 엄청나기 때문에 nlogn 정렬 알고리즘은 반드시 알고 있어야 한다.

# Merge Sort

합병 정렬이라고도 하는 이 알고리즘은 분할 정복(Divide and Conquer) 방식으로 설계된 알고리즘이다.

분할 - 합병 두가지 과정을 거친다.

분할의 과정의 경우 배열의 크기를 0,1이 될때 까지 쪼갠다.

그 후 합병의 과정을 거친다.

합병 과정은 다음과 같은 로직을 갖는다.

두 배열 A,B의 크기를 비교한다.
A[i]와 B[j]를 비교한다.
오름차순 이라면 둘 중 작은 값을 C[k]에 저장하고 k 값을 1 증가시킨다.
만약 A[i]가 더 작았다면 i 값을 1 증가시킨다.
이 과정을 i나 j가 마지막에 도달할때 까지 반복한다.
끝까지 저장이 안된 값들이 존재한다면 C에 저장한다.
C를 원래 배열에 저장한다.

```java
void merge(int[] arr, int s,int e, int m){
  List<Integer> ret = new ArrayList<>();
  int i=s, j= m+1, copy =0;

  while(i<=m && j<= e){
    if(arr[i] < arr[j]) ret.add(arr[i++]);
    else if(arr[i] > arr[j]) ret.add(arr[j++]);
  }

  while(i<= m) ret.add(arr[i++]);
  while(j<=e) ret.add(arr[j++]);

  for(int k=s; k<=e; k++){
    arr[k] = ret.set(copy,copy++);
  }
}

void mergeSort(int[] arr, int s, int e){
  if(s<e){
    int m = (s+e)/2;

    mergeSort(arr,s,m);
    mergeSort(arr,m+1,e);
    merge(arr,s,e,m);
  }
}
```

# Quick Sort

퀵 정렬 또한 분할 정복을 이용하여 정렬을 수행하는 방식이다.

pivot point라는 기준이 되는 값을 설정한다.

이 값을 기준으로 작은 값은 왼쪽으로 큰 값은 오른쪽으로 옮기는 방식으로 정렬을 진행한다.

이를 반복하여 분할된 배열의 크기가 1이 되면 정렬이 완료된다.

Quick Sort는 다음과 같은 순서로 진행한다.

가장 먼저 pivot point를 지정한다. 보통 배열의 가장 앞이나 가장 뒤 혹은 배열의 값 중 중간값이나 랜덤하게 정한다.

분할을 하기에 앞서 가장 왼쪽 배열의 인덱스를 지정하는 left와 가장 오른쪽 배열 인덱스를 지정한 right 변수를 생성한다.

**반복**

right부터 비교를 진행한다. 비교는 right가 left보다 클때만 반복하며 비교한 배열 값이 pivot point보다 크면 right를 하나 감소시키고 비교를 반복한다. pivot point보다 작은 배열 값을 찾으면 반복을 중지한다.
그 다음 부터는 left부터 비교를 진행한다. 비교는 right가 left보다 클때만 진행하며 비교한 배열 값이 pivot point보다 작으면 left를 하나 증가시키고 비교를 반복한다.
만약 pivot point보다 큰 배열 값이 있는 경우 반복을 중지한다.

left와 right의 값을 서로 바꿔준다.

**반복**

위의 반복 범위 만큼 반복하며 left < right를 만족할 때 까지 반복한다.

위 과정이 끝나면 left의 값과 pivot point를 다시 바꿔준다.

맨 왼쪽부터 left -1까지, left+1부터 맨 오른쪽 까지 나눠 다시 **반복**을 수행한다.

일반적으로 퀵 소트는 그 이름에 걸맞게 비교 정렬 알고리즘 중에서 가장 빠른 성능을 보인다고 알려져있다.

퀵정렬의 worst case는 O(n^2)인데 이는 이미 배열이 정렬되어 있는 경우이다. 이 경우 분할이 N만큼 일어나 O(n^2)이 되는데

이 경우를 제외한 일반적인 상황에서 퀵소트는 다른 nlogn 정렬보다 뛰어난 성능을 보인다.

왜 머지 소트보다 퀵 소트가 더 빠른 성능을 보일까?

이는 수학과 컴퓨터 공학 사이의 갭 때문이다.

수학적으로 표현했을때 모든 경우에서 O(nlogn)인 머지 소트가 퀵 소트보다 더 빠른 성능을 보인다고 생각하기 쉽다.

하지만 머지 소트의 경우 배열을 계속 새롭게 생성하기 때문에 데이터들이 page를 계속 이동하게 되고, 캐쉬에 없는 페이지로 이동하게 되면 page cache hit 비율이 떨어지게 된다. 이 경우 physical memory에 접근하여 데이터를 가져와야하기 때문에 시간이 오래걸리게 된다. 

즉 수학적인 관점에서는 머지소트가 더 빠르지만 컴퓨터 공학적으로 접근하여 하드웨어의 성능에서 데이터들이 계속해서 새롭게 페이지 이동을 하는 머지소트 쪽이 하나의 페이지에서 계속 자리를 잡고 있는 퀵소트 보다 더 느린 것이다.

```java
void quickSort(int[] arr, int l, int r){
  int left = l;
  int right = r;
  int pivot = arr[l];

  do{
    while(arr[right] > pivot) right--;
    while(arr[left] < pivot) left++;
    if(left <= right){
      swap(arr[left],arr[right]);
      left++;
      right--;
    }
  }while(left<=right);
  if(l<right) quickSort(arr,l,right);
  if(r>left) sort(arr,left,r);
}
```

# Dual Pivot Quick Sort

흥미롭게도, Java 7버전 이후부터는 듀얼 Pivot 퀵소트를 사용하고 있다.

간단한 알고리즘이기 때문에 한번 알아보자.

[4 2 5 3 6 10 9 8 7] 이라는 배열이 있다고 가정하자.

Dual Pivot Quick Sort는 배열을 총 3 개로 쪼개는 것을 반복한다.

현재 위의 배열에서 가장 왼쪽의 4가 **Left Pivot**이 되며, 가장 오른쪽의 7이 **Right Pivot**이 된다.

이 Pivot은 Left Pivot > Right Pivot인 경우에 Swap을 수행한다는 규칙이 존재한다.

또한 Left Pivot의 왼쪽에는 Left Pivot 보다 작은 값이 와야 하며, Left Pivot의 오른쪽과 Right Pivot의 중간에는 그 둘의 사이 값들이 와야하며, Right Pivot의 오른쪽에는 Right Pivot보다 큰 값이 와야 한다.

다음과 같은 형태가 된다.

[2 3 "4" 5 6 "7" 10 9 8]

그 후

1번째 하위 배열 : items < LP ==> [ 2 3 ]

2번째 하위 배열 : LP <= items <= RP  ==> [ 5 6 ]

3번째 하위 배열 : items >= RP ==> [ 10 9 8 ]

이 된다.

여기서 1번째 하위 배열과 2번째 하위 배열은 각각 왼쪽 피벗이 오른쪽 피벗보다 작기 때문에 아무 일도 일어나지 않는다.

단 3번째 하위배열의 경우 왼쪽 피벗보다 오른쪽 피벗이 크기 때문에 스왑을 진행한다.

그러면 전체 정렬이 완료된다. 이것이 Dual Pivot Quicksort의 원리이다.

# External Sort

작성 중

 ## Reference

 https://hsp1116.tistory.com/33

 https://medium.com/pocs/locality%EC%9D%98-%EA%B4%80%EC%A0%90%EC%97%90%EC%84%9C-quick-sort%EA%B0%80-merge-sort%EB%B3%B4%EB%8B%A4-%EB%B9%A0%EB%A5%B8-%EC%9D%B4%EC%9C%A0-824798181693

 https://dev.to/s_awdesh/double-pivot-quick-sort--javas-default-sorting-algorithm-1m4

 https://gwpark.tistory.com/1743