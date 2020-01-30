# Deque

Deque는 Queue의 자료구조와 매우 비슷한 형태를 갖고 있다.

![anod](https://user-images.githubusercontent.com/43809168/73451066-14c24a00-43aa-11ea-92d9-e93b862403d2.png)

Queue와 비슷한 성질을 갖고 있음에도 Deque와 Queue의 차이점은 Queue는 선입 선출 FIFO 자료구조인데 비해서 Deque은 자료구조의 앞쪽과 뒤쪽에 삽입/삭제가 가능하다는 특징이 있다.

그래서 Java의 Deque는 Queue 인터페이스를 상속받아 구현되어 있다.

<img width="600" alt="스크린샷 2020-01-30 오후 9 48 52" src="https://user-images.githubusercontent.com/43809168/73451168-53f09b00-43aa-11ea-86f7-cee06e5972d4.png">

< 실제 Deque의 구현체 >

## Deque의 사용법

Dequq은 노드의 앞쪽과 뒤쪽에 삽입,삭제 또는 검사를 할 수 있는 메소드를 갖고 있다.

### Head, Tail 삽입 / 삭제 / 검사

Head는 자료구조의 머리쪽에 해당하는 부분을 의미한다.

Tail은 자료구조 꼬리에 해당하는 부분을 의미한다.

#### 삽입

1. addfirst(e) / addLast(e)

2. offerFirst(e) / offerLast(e)

#### 삭제

1. removeFirst() / removeLast()

2. pollFirst() / pollLast()

#### 검사

1. getFirst() / getLast()

2. peekFirst() / peekLast()

그러면 여기서 의문이 생긴다.

왜 같은 기능을 수행하는 메소드가 두개씩 선언이 되어있는 걸까?

그 이유는 **1**의 경우 Collection 인터페이스의 Method이고

**2**는 Queue의 Method이기 때문이다.

물론 pollFirst와 peekFirst()의 경우 Deque의 메소드이다.

사실 이것 보다 더욱 중요한 점은 **에러를 발생시키는 유무**이다.

**1**의 경우 수행할 수 없는 예외 상항에서 Exception을 발생시킨다.

**2**의 경우 수행할 수 없는 예외가 발생할 경우 False의 값을 반환한다.

이것이 두 메소드의 가장 큰 차이이다. 그래서 만약 예외처리가 필요하다면 **2** 메소드를 사용하는 것이 좋겠다.


## Reference

https://sup2is.github.io/java-data-structure-8/

https://stackoverflow.com/questions/15591431/difference-between-offer-and-add-in-priority-queue-in-java