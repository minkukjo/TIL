# Concurrent 라이브러리

멀티 쓰레딩 환경에서는 쓰레드가 프로세스의 자원을 공유하기 때문에 Race Condition이 발생하게 된다.

자바에서는 멀티쓰레딩 환경에서 동시성 제어를 위한 라이브러리를 별도로 제공해주고 있는데 이것이 **Concurrent** 라이브러리다. 

지금부터 **Concurrent** 라이브러리 내부를 들여다보자.

## CopyOnWriteArrayList

CopyOnWrite라는 이름처럼 변경이 일어날 경우 객체를 clone하여 다루자는 전략이다.

CopyOnWrite는 ArrayList,LinkecdList와 마찬가지로 컬렉션 프레임워크를 상속받는 List 인터페이스의 구현체이다.

ArrayList와 다른 점은 딱 한가지인데, 바로 Thread-safe 유무이다.

CopyOnWriteArrayList Thread-safe하므로 동시성 제어가 필요한 경우에 사용한다.

때문에 일반적으로 성능은 ArrayList가 CopyOnWriteArrayList 보다 빠르다.

## BlockingQueue

보통 생산자-소비자 패턴에서 많이 활용하는 큐로 사용된다.

소비자가 꺼내어 사용할 동안 생산자는 멈춰있으며 생산자가 넣을 동안 소비자는 멈춰있다.

동시성 제어를 위한 큐이다.

## ConcurrentHashMap

ConcurrentHashMap은 map의 일부분에만 Lock을 걸기 때문에 HashTable과 sychronized map보다 효율적이다.

이를 위해 대표적인 동시성 제어 자료구조인 HashTable과 ConcurrentHashMap 그리고 동시성 제어를 제공하지 않는 HashMap을 비교해보자

1. HashTable

```java
public synchronized V More ...get(Object key)
public synchronized V More ...put(K key, V value)
```

해쉬 테이블에선 메소드에 synchronized를 걸어버린다.

2. HashMap

HashMap은 동시성 제어를 제공하지 않으므로 당연히 synchronized가 없다. 속도는 빠르지만 멀티스레드 환경에서 별도의 동시성 제어를 처리하지않으면 여러 Thread가 동시 접근하면서 Exception이 일어난다.

3. ConcurrentHashMap

ConcurrentHashMap은 HashTable과는 큰 차이점을 보인다.

그것은 HashTable이 모든 메소드에 Locking을 하는 것과 달리 ConcurrentHashMap은 읽기 작업에는 Locking을 하지 않고 쓰기 작업을 하는 메소드에만 Locking을 하기 때문에 성능적으로 더 우수하다.

ConcurrentHashMap가 Map 자체를 Currency 레벨을 영역별로 쪼개서 영역별로 Lock을 걸게 된다. 

이 Lock의 이름은 ReetrantLock이라고 한다.

다른 여타 동기화를 위한 자료구조처럼 읽기가 쓰기보다 더 많을 때 사용한다.

javadoc에서도 동기화가 필요하다면 HashTable보다는 ConcurrentHashMap을 사용할 것을 권장하고 있다.


## Reference

https://hamait.tistory.com/381

http://blog.leekyoungil.com/?p=159


https://highlyscalable.blogspot.com/2014/06/java-concurrenthashmap.html
