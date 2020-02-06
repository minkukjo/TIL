# Serialize Interface

직렬화 인터페이스에 대해서 알기전에

직렬화와 역직렬화가 무엇인지 알아보자

## Serialize (직렬화)

- 자바 내부 시스템에 사용되는 Object 또는 Data를 외부의 자바 시스템에서도 사용할 수 있도록 byte 형태로 변환하는 기술

- JVM의 메모리에 존재하는 객체 데이터를 byte 형태로 변환하는 기술

## Deserialize (역직렬화)

- byte로 변환된 Data를 원래대로 Object나 Data로 변환하는 기술

- 직렬화된 데이터를 객체로 변환해서 JVM의 힙영역에 상주시킴

## 직렬화의 조건

**java.io.Serializable** 인터페이스를 상속받은 객체는 직렬화 할 수 있는 기본 조건이다.

```java

class Person implements Serializable{
  ...
}
```

# Reference

https://nesoy.github.io/articles/2018-04/Java-Serialize