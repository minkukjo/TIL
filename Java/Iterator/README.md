# Iterator

Iterator는 Collection Framework를 읽어오는 방법을 표준화한 것이다.

Iterator가 가지고 있는 메서드는 다음과 같다.

```java
boolean hasNext() // 다음 값이 있는지 없는지 확인
E next() // 다음 값을 리턴
remove() // 값을 삭제
```

Iterator 인터페이스의 remove 메소드는 컬렉션을 순회하면서 원소를 삭제할 수 있는 유일하게 안정한 방법이라고 한다.

## Reference

https://www.daleseo.com/how-to-remove-from-list-in-java/