# B+ Tree가 등장한 배경

![skewLeft](https://user-images.githubusercontent.com/43809168/72682085-f12b1400-3b0c-11ea-8a19-d367231cd49f.png)

기존의 2진 탐색 트리는 검색에 평균적으로 O(logn)의 성능을 보여준다.

그러나 위와 같은 skewed 형태의 이진 탐색 트리의 경우 O(n)의 최악의 성능을 보인다.

하지만, 만약 위의 트리가 완전 이진 탐색트리의 형태를 유지할 수 있다면 O(logn)의 검색성능을 낼 수 있을 것이다.

B+ 트리는 이러한 이진 탐색 트리의 약점을 보완하기 위한 BBST(Balanced Binary Search Tree)라고 부른다.

이름에도 알 수 있듯이 B 트리의 변형 형태이다.

# B Tree

![B_Tree](https://user-images.githubusercontent.com/43809168/72682423-43216900-3b10-11ea-9da0-6cc8c06e0480.png)

B트리는 항상 트리의 균형을 일정하게 유지해주며, 하나의 노드에 여러 값을 가질 수 있다는 특성이 있다.

하나의 노드에 여러 값을 가질 수 있다는 의미는, 트리의 전체 높이가 낮아지기 때문에 검색의 성능이 좋아질 수 있겠다.

B 트리는 데이터베이스, 파일시스템에서 주로 사용된다.

# B+ Tree

![B+_Tree](https://user-images.githubusercontent.com/43809168/72682424-43216900-3b10-11ea-8c27-a40809fa88d6.png)

B+ 트리는 B 트리를 개량한 트리이다.

B 트리 처럼 모든 리프 노드는 동일한 Depth를 갖는다.

B 트리와는 달리 Inner Node에는 키만 저장하고 Leaf Node에는 키와 데이터를 둘 다 저장한다.

또한 Leaf Node에만 데이터가 저장되기 때문에 Leaf Node들 간의 포인터를 연결하여 B 트리에 비해 비교적 쉬운 순회가 가능하다는 특성이 있다.

B+ 트리의 Inner Node에는 데이터가 없으므로 B 트리보다 용량이 작기 때문에 B+ 트리는 Key 탐색시에 B 트리 보다 조금 더 나은 성능을 보여준다.


# Reference

https://www.geeksforgeeks.org/complexity-different-operations-binary-tree-binary-search-tree-avl-tree/

https://ssup2.github.io/theory_analysis/B_Tree_B+_Tree/