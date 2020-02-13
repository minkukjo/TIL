# Collection Framework

Java의 Collection Framework란 무엇인가?

자바에서는 다수의 데이터를 쉽고 효과적으로 처리할 수 있는 표준화된 방법을 제공하는 클래스들이 존재한다.

이 클래스들의 집합을 우리는 **Collection Framework**라고 부른다.

데이터를 저장하는 자료구조와 데이터를 처리하는 알고리즘을 캡슐화하여 클래스로 구현해놓았다고 이해하면 된다.

Collection Framework는 기본적으로 Interface를 사용하여 구현한다.

![java-collections-cheat-sheet](https://user-images.githubusercontent.com/43809168/74423637-ef4a3b80-4e93-11ea-8aad-addf7b995672.png)


위의 그림은 Collection Framework를 한눈에 보여주는 구조도 이다.

Map의 경우 Collection 인터페이스를 상속받고 있지 않은 것을 알 수 있는데, 그럼에도 Map을 포함해서 Collection Framework라고 부른다.

Map이 Collection 인터페이스를 구현하지 않는 이유는 간단하다.

Collection 인터페이스의 경우 제네릭 타입을 1개를 갖고 있는데에 비해 Map은 2개를 가져야 한다. (Key,Value)

때문에 Collection 인터페이스를 구현하지 않고 별도로 구현되어 있음을 알 수 있다.

하나씩 살펴보도록 하자.

## List

리스트는 선형 자료구조이다.

List는 크게 ArrayList와 LinkedList, Vector로 나누어진다.

ArrayList는 내부적으로 배열로 구현되어있으며 일반적으로 우리가 잘 아는 배열의 기능인 임의접근에 O(1)이 걸리며, 중간 삽입,삭제의 성능이 좋지 않다.

LinkedList는 ArrayList와 달리 중간 삽입,삭제에 유리하지만 비연속적인 메모리 공간에 자리잡기 때문에 탐색에 O(n)이 걸리게 된다.

Vector의 경우 현재는 잘 쓰이지 않는데, 기본적으로 동기화된 메소드를 갖고 있기 때문에 멀티 쓰레드에 대한 처리가 되어있다.

그러나 잘 사용하지는 않는다.

FILO or LIFO 자료구조인 Stack이 Vector를 상속받아 구현되어져 있다.

## Queue

FIFO 자료구조인 Queue의 경우 일반적으로 LinkedList로 구현되는 경우가 많다.

Queue를 구현한 자료구조로는 Priority Queue와 Deque가 있다.

Deque의 경우 Queue의 진화 형태로, 가장 앞쪽의 삽입,삭제와 가장 뒤쪽의 삽입,삭제가 가능한 자료구조이다.

Deque의 구현은 일반적으로 LinkedList와 ArrayDeque가 있는데, ArrayDeque가 LinkedList보다 Cache Hit Rate가 높아 접근에 성능이 더 뛰어다나고 한다.

또한 LinkedList의 경우 노드가 삭제될때 마다 가비지 콜렉터의 대상이 되기 때문에 더 빈번히 GC가 발생할 수 있어서 일반적으로는 ArrayDeque를 더 많이 사용한다고 한다.

이 내용에 대해서 더 알고 싶다면 아래의 StackoverFlow 글을 참조하자.

https://stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist

Priority Queue의 경우 Queue를 구현하는 클래스인데 내부적으로 오름차순 또는 내림차순으로 정렬된 형태를 갖는다.

기본 정렬 방식은 오름차순이며 정렬 방법을 바꾸고 싶을 때는 Priority Queue에 저장할 클래스에 Comparable 인터페이스를 구현하거나 Priority Queue에서 Comparator 메소드를 오버라이딩 하여 정렬 방법을 지정할 수 있다.

## Set

Set은 기본적으로 중복을 허용하지 않는 자료구조이다.

크게 HashSet,LinkedHashSet, TreeSet이 있는데

이 구조는 Map과 동일하니 잘 봐두도록 하자.

HashSet의 경우 저장의 순서를 보장하진 않지만 탐색에 O(1)이 걸린다.

잘 알고 있는 HashFuction을 이용하여 데이터를 저장한다.

LinkedHashSet의 경우 저장의 순서를 보장하지만 이름 그대로 LinkedList로 구현이 되기 때문에 랜덤 접근에 O(n)이 걸린다.

TreeSet의 경우 값을 정렬하여 저장한다. 그래서 SortedSet이라는 인터페이스를 구현하고 있는 것을 볼 수 있다.

정렬 방법을 정해주는 것은 Priority Queue와 마찬가지로 Set에 저장될 클래스에 Comparable 인터페이스를 구현하여 메소드 오버라이딩으로 compare 함수로 정렬 방법을 지정하거나, Set이 Comparator 인터페이스를 구현하여 정렬방법을 지정하는 방법이 있다.

이 Set은 앞으로 볼 Map과 그 형태가 매우 유사하다.

## Map

Key와 Value로 저장하는 자료구조이다.

Key는 중복을 허용하지 않으며 Key와 Value는 1:1로 매핑된다.

Key는 HashFunction을 사용하여 Hash값으로 바꾼 후 버킷에 저장된다.

HashFuction은 일반적으로 Modulation(%)를 사용하여 구현되기 때문에 필연적으로 Collision이 발생한다.

이 Collision을 극복하기 위해 OpenAddressing (개방주소법)과 Separate Chaning(체이닝) 두가지 방법을 제공하는데 Java의 HashMap은 체이닝 방식을 사용한다.

앞서 언급한 것과 같이 HashMap은 Java Collection Framework의 대표적인 자료구조이다.

가장 많이 쓰이면서 버전이 업데이트될 때 마다 계속해서 진화하는 자료구조이다.

이름 그대로 임의 접근에 O(1)이 걸리며 순서를 보장하지 않는다.

HashTable의 경우 HashMap과 이름이 유사하지만 기능이 조금 다르다.

HashTable은 기본적으로 모든 메소드들에 Synchronize 키워드가 붙어있다.

즉 동기화를 기본적으로 제공해주는데, HashMap에 비해 버전이 올라가도 크게 바뀐게 없다.

또한 메소드 자체에 동기화를 해버려서 성능 또한 좋지 못하다.

심지어 HashMap에서는 보조해시함수를 사용하여 Collision 빈도를 줄였는데 HashTable에는 보조해시함수도 없다.

개인적인 생각이지만 자바에서 HashTable은 레거시 자바코드를 위해 살려놓은게 아닐까 생각해본다.

위의 표에는 없지만 동기화를 해결하기 위해 자바에서는 Concurrent 라이브러리를 추가했다.

여기에 ConcurrenctHashMap이라는 것이 존재한다.

ConcurrenctHashMap은 이름그대로 HashMap에서 Thread-Safe하도록 만들어진 클래스이다.

ConcurrenctHashMap은 내부적으로 Write에만 락을 걸어서 Read를 할때는 Lock을 걸지 않음으로써 성능을 높였다.

또한 내부적으로 Map을 Currency 레벨로 영역을 쪼개서 해당 영역별로 Lock을 거는 방식을 사용하고 있다.

LinkedHashMap의 경우 연결 리스트로 구현되어있기 때문에 순서를 보장하지만, 탐색에 O(n)이 걸린다.

TreeMap의 경우 내부적으로 정렬이 되어있는데, 이때 TreeMap의 구현은 RB Tree로 구현이 되어있다.

그래서 삽입,삭제,탐색에 O(logN)의 시간복잡도를 갖는다.

## Iterable

최상위 인터페이스인 컬렉션 인터페이스는 Iterable 인터페이스를 상속받고 있다.

이로 인해 모든 자바의 자료구조들은 Iterator라는 구현체를 통해 같은 방법으로 내부를 순회할 수 있다.

이는 디자인패턴에서 Iterator 패턴이라고도 불리운다.

Map의 경우 Collection 인터페이스를 상속받고 있지 않기 때문에 이것이 불가능한데,

그래서 Map에는 keySet()이라는 메소드를 통해 현재 Map의 Key들을 Set 자료구조로 변환하여 반환해주는 메소드가 존재한다.

이를 통해 반환된 Set의 Iterator 메소드를 호출하여 키와 값의 순회가 가능하다.

## Reference

https://www.javacodeexamples.com/java-collection-framework-tutorial-with-examples/1641