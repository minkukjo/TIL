# Java HashMap

Java HashMap은 Java Collection의 한 종류이다.

일반적으로 Collection 인터페이스를 상속받고 있는 List,Set,Queue 인터페이스를 Collection이라고 부른다.

그러나 Java HashMap은 Map 인터페이스를 상속받고 있다. 하지만 Collection으로 분류된다.

## HashMap과 HashTable의 차이

일반적으로 HashMap과 HashTable의 차이는 동기화 유무로 구분한다.

또한 HashTable의 경우 버전이 상승하면서 변화가 거의 없는 반면, HashMap은 지속적으로 개선되고 있다.

HashTable은 HashMap에서 사용하고 있는 보조해시함수를 사용하지 않기 때문에 해시충돌이 더 빈번하게 발생한다.

즉 HashTable보다 HashMap이 더 성능상 이점이 있다.

## 해시 충돌

일반적으로 HashMap은 O(1)의 성능을 보인다.

HashMap이 O(1)의 성능을 보이는 것은 랜덤 접근이 가능한 배열을 사용하기 때문이다.

이를 **associative array**라고 부르며 메모리를 절약하기 위해 실제 해시 함수의 표현 정수 범위 N 보다 작은 M개의 원소가 있는 배열을 사용한다.

```java
int index = hashMap.hashCode() % M;
```

이 방식을 이용하면 서로 다른 해시코드를 가진 서로다른 객체가 1/M의 확률로 같은 해시 버킷을 사용하게 된다.

이러한 충돌을 대응할수 있는 두가지 방법이 존재하는데,

Open Addressing과 Separate Chaning이다.

## Open Addressing

Open Addressing 방식은 데이터를 삽입하려고 할 때 이미 사용중인 버킷의 경우 다른 해시 버킷에 데이터를 삽입하는 방식이다.

데이터를 저장하거나 조회할때는 Linear Probing, Quadratic Probing 방식을 사용한다.

## Separate Chaning

Separate Chaning 방식은 이미 사용중인 버킷에 삽입하려는 경우 해당 인덱스의 Linked List를 연결한다.

## 두 방식의 차이

둘 모두 Worst Case O(M)이다.

하지만 Open Addressing의 경우 연속 메모리 공간에 데이터를 저장하기 때문에 Separate Chaning에 비해 Cache Hit 비율이 높다.

그러나 데이터의 크기가 커지면 배열의 크기가 커지게 되고 이 경우 Open Addressing의 Cache Hit 적중률이 낮아지게 된다.

## HashMap은 Separate Chaning

그래서 Java HashMap은 Separate Chaning을 사용하고 있다.

Java8 버전부터는 데이터 개수가 많아지면 충돌이 발생할 때 Index와 이어지는 자료구조를 링크드리스트가 아닌 트리를 사용한다.

때문에 이는 기존의 링크드리스트보다 더 좋은 성능을 보이며 최대 logN/M의 성능을 보여준다. 

물론 이때의 트리는 RedBlack 트리를 사용한다.

Java Collection Framework에서 TreeMap과 구현이 거의 비슷하다.

트리 순회시 사용하는 대소 판단 기준은 해시값을 기준으로 한다.

그러나 이때 해시값을 기준으로 하는 경우 두 해시값이 같아도 두 값이 동등하지 않을 수 있다.

따라서 어떤 두 개의 키에 대한 해시 함수 값이 같을 경우,

임의로 대소 관계를 지정해주어야 하고 이를 tieBreakOrder 메소드를 사용해 해결하였다.

## 보조해시 함수

Java8에서는 상위 16비트 값을 XOR하여 해시 코드 값을 구한다.

```java
static final int hash(Object key) { int h; return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16); } 
```

이러한 단순한 형태의 보조 해시 함수를 사용하는 이유는

1. 자료구조를 트리로 바꾸면서 충돌 시 발생할 수 있는 성능 문제 완화
2. 최근 해시 함수는 균등 분포가 잘 되게 만들어지는 경우가 많아 Java7에서 사용한 보조 해시 함수의 효과가 크지 않음.

두번째 이유가 좀 더 결정적인 이유가 되어 Java 8에서 보조해시 함수의 구현을 바꾸었다.


## String의 HashCode

String의 경우 31 소수를 곱하여 해시값을 사용한다.

그 이유는 31N=32N-N인데 32는 2^5이니 어떤 수에 32를 곱한 값은 shift 연산으로 빠르게 구할 수 있다. 따라서 N에 31을 곱한다는 것은 (N<<5)-N와 같다. 즉 머신 코드에서 빠른 연산을 위해 사용하며 String 클래스에서는 각각의 문자마다 31을 곱하여 해시 코드값을 계산한다.



# Reference

https://d2.naver.com/helloworld/831311

