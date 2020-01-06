# Red Black Tree

Red Black Tree는 이진 탐색 트리의 변형 형태이다.

이진 탐색 트리의 특징은 자기보다 작은 값은 왼쪽에 큰 값은 오른쪽에 오는 특성을 갖고 있다.

이진 탐색트리의 탐색시간은 일반적으로 log n 이다.

<img width="410" alt="tree1" src="https://user-images.githubusercontent.com/43809168/71545245-4a030300-29cc-11ea-86c1-f87f1d88138f.png">

그러나 위와 같은 최악의 경우 (한쪽으로 트리가 이어진 경우) log h (트리의 높이 만큼)의 탐색시간이 걸리게 된다.

Red Black Tree는 이러한 이진 탐색 트리의 최악의 경우를 피하기 위한 Balanced Tree의 형태이다.

# 특징

Red Black Tree가 되기위한 4가지 조건

1. Root Property : 루트 노드는 검정색이다.
2. External Property : 모든 external node들은 검정색이다.
3. Internal Property : 빨강 노드의 자식은 검정이다.
(빨간색 노드가 연속으로 나올 수 없다)
4. Depth Property : 모든 리프노드에서 Black Depth는 같다.
(리프노드에서 루트노드 까지에서 만나는 블랙노드의 개수는 어떤 리프노드에서 출발해도 동일하다.)

이러한 4개의 조건이 항상 높이를 log n에 바운드되도록 유지시켜 준다.

**삽입되는 노드의 색은 항상 Red이다.**

## Double Red가 발생하는 경우

<img width="717" alt="스크린샷 2019-12-28 오후 11 49 14" src="https://user-images.githubusercontent.com/43809168/71545271-a9f9a980-29cc-11ea-8808-46c3d8834908.png">

항상 루트노드는 Black이기 때문에

4->2->8->3을 순차대로 넣으면 위와 같은 형태가 된다.

2와 3이 Double Red가 발생하게 되고 이를 해결하기 위한 두가지 방법이 존재한다.

1. Restructuring
2. Recoloring

<img width="808" alt="스크린샷 2019-12-28 오후 11 49 34" src="https://user-images.githubusercontent.com/43809168/71545311-01981500-29cd-11ea-8d71-dd80d4912cca.png">


위 두가지 경우가 발생하는 경우는 삽입된 노드의 부모의 형제 즉, unclde node의 색이 Black이냐 Red에 따라 다른 방법을 취한다.

<img width="717" alt="스크린샷 2019-12-28 오후 11 49 14" src="https://user-images.githubusercontent.com/43809168/71545271-a9f9a980-29cc-11ea-8808-46c3d8834908.png">

위의 그림을 다시 보자.

현재 3이 삽입되고 3의 부모는 2이며 2의 형제는 8이다.

8의 색상은 Red이다. 이 경우 Recoloring을 진행한다.

## Recoloring

Recoloring은 아주 간단하다.

부모 노드와 형제 노드 모두 Black 색상으로 바꿔주고 내 부모의 부모(Grand Parent)의 노드를 Red로 바꿔준다. (만약 부모의 부모가 Root라면 노드 색을 검은색으로 둔다.)

그러나 만약 부모의 부모 노드가 Root Node가 Root가 아니라면 Double Red가 또 다시 발생할 수 있고, 이는 Propagation 현상이 발생할 수 있다.

Recoloring은 평균 O(1)이며 최악의 경우 O(log n)만큼의 시간이 걸릴 수 있다. (Double Red가 발생하여 Recoloring이 반복되어 Root까지 올라가는 경우)

## Restructuring

Double Red 현상이 발생하고, 내 부모의 형제가 Black인 경우라면 Restructuring을 진행한다.

Restructuring은 다음과 같이 이루어진다.

1. 나와 부모, 그리고 부모의 부모를 오름차순으로 정렬
2. 가운데 있는 값을 부모로 만들고 나머지 둘을 자식으로 만듦.
3. 올라간 가운데 값을 Black으로 하고 두 자식을 빨강으로 만든다.

<img width="745" alt="스크린샷 2019-12-29 오전 12 01 37" src="https://user-images.githubusercontent.com/43809168/71545444-699b2b00-29ce-11ea-8481-884be23a0a67.png">

위의 예제를 보자.

4 - 7 - 6 를 정렬하면

4 - 6 -7이 된다.

이때 6을 Black으로 바꾸고 4,7을 6의 자식으로 둔다.

<img width="859" alt="스크린샷 2019-12-29 오전 12 02 53" src="https://user-images.githubusercontent.com/43809168/71545461-92232500-29ce-11ea-8baa-6e325ccaaae3.png">

위와 같은 형태가 된다.

그 후 기존 4의 자식인 2를 추가해주면

<img width="868" alt="스크린샷 2019-12-29 오전 12 03 56" src="https://user-images.githubusercontent.com/43809168/71545477-b8e15b80-29ce-11ea-8fee-a0d928b622ff.png">

<Restructuring을 거친 Red Black Tree>

Restructuring 자체 시간은 O(1)이지만 Restructuring은 특정 노드를 Insertion한 후 일어나므로 총 수행시간은 O(log n)이다. 지금 현재 노드가 들어갈 위치를 찾아야하기 때문이다.

Restructuring은 다른 서브 트리에 영향을 미치지 않기 때문에 1번이면 끝난다.

## 결론

Red Black Tree에 새로운 노드를 삽입하는 경우 Restructuring, Recoloring 모두 O(log n)가 걸리기 때문에 Red Black Tree는 항상 O(log n)을 보장한다는 것을 알 수 있다.

Java8버전 부터 HashMap에서는 하나의 해시 버킷에 8개 이상의 리스트가 있는 경우 리스트를 트리로 변경한다.

버킷의 개수가 삭제되는 경우 6개가 되면 다시 트리를 리스트로 변경한다.

이때 사용되는 트리가 Red Black Tree이다.

Red Black Tree를 사용함으로써 Separate Chaining에서의 get Method의 성능을 끌어올릴 수 있었다.

(Java 7의 HashMap) 시간 복잡도 O(N/M)

(Java 8 Hash Map) 시간 복잡도 O(log N/M)




## Reference

https://zeddios.tistory.com/237

https://d2.naver.com/helloworld/831311
