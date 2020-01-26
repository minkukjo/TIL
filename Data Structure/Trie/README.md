# Trie

<img width="632" alt="스크린샷 2020-01-26 오후 9 03 14" src="https://user-images.githubusercontent.com/43809168/73134900-5cca2f80-407f-11ea-9a37-fe3c1bbf004e.png">

Trie는 일반 트리 자료구주중 하나로 텍스트 자동 완성 기능과 같이 문자열을 저장하고 탐색하는데 유용한 자료구조이다.

Trie의 이름의 유래는 원래 Tree였는데 자료구조 Tree와 이름이 겹쳐서 Trie로 바꾸었다.

각 노드는 < Key, Value >의 쌍으로 이루어져있다.

위의 그림을 보면 알겠지만 루트노드는 특정 문자를 의미하지 않고 자식 노드만 가지고 있다.

여기서 주의할점은 각 노드들이 나의 부모가 누구인지, 내가 어떤 문자인지를 갖고 있는게 아니다.

Root를 기준으로 하면 Root는 D와 P라는 알파벳을 Key로 하는 자식 노드를 갖고 있을 뿐이다.

D는 E를, P는 I와 O를 갖고 있을 뿐이다.

Trie 자료구조의 특징은 공통 접두어를 가진다는 특징이 있다.

DEV와 DEAR는 **DE**라는 공통 접두어를 가지게 된다.

Trie의 시간 복잡도는 다음과 같다.

**L**을 제일 긴 문자열의 길이

**M**을 Trie에 넣을 총 문자열들의 수

### 삽입

전체 문자열들을 넣는건 가장 긴 L만큼 걸리기 때문에 O(M*L)

만약 하나의 문자열을 넣을때, 즉 순수 삽입 시간은 O(L)이다.

### 검색

O(L)

트리를 타고 들어가봤자 가장 긴 문자열의 길이만큼 탐색하기 때문에 O(L)이다.

이것이 Trie 자료구조 강력한 점이다.

BST를 써도 O(LlogM)인걸 감안했을 때 Trie는 문자열 검색에서 가장 강력한 자료구조이다.

## Refererence

https://the-dev.tistory.com/2

https://twpower.github.io/187-trie-concept-and-basic-problem