# Optional

Optional API는 자바 8에 추가된 기능이다.

자바 8에서 Optional이 등장한 이유는 너무나 명확하다.

바로 **null** 때문이다.

**null**은 자바 개발자들에게 불편함을 느끼게 했다.

자바에서는 **null** 객체에 접근할 때 **NullPointerException**을 발생시키는데 이로 인해 수 많은 자바 개발자들의 암을 유발했다.

때문에 우리의 불쌍한 자바 개발자들은 이 **null** 체크 로직을 추가로 만들 수 밖에 없었고 이는 코드의 가독성과 유지 보수성을 떨어뜨리는데 일조하게 되었다.

## 함수형 언어에서의 null 처리

자바가 존재하지 않는 값을 위해 **null**을 사용했다면, 함수형 언어들은 **존재할지 안할지 모르는 값**을 표현하기 위한 별개의 타입을 갖고 있었다.

Kotlin으로 예를 들자면

```kotlin
val name:String? = "Minkuk" 
```
?타입을 붙이는 것을 예로 들 수 있겠다.

Java 또한 존재할지 안할지 모르는 값을 제어할 수 있는 **java.util.Optional<T>**라는 새로운 클래스를 도입하게 된다.

## Optional?

**Optional**이란 "존재할 수도 있지만 안할 수도 있는 객체" 즉 "null이 될 수 있는 객체"를 감싸고 있는 일종의 래퍼 클래스이다.

## Optional의 효과

- NPE를 유발하는 null을 직접 다루지 않아도 된다.
- null 체크 일일이 안해도 된다.
- 명시적으로 해당 변수가 null이 될 가능성이 있다는 것을 표현할 수 있다.

## Optional 변수 선언

```java
Optional<Person> maybePerson;
Optional<Animal> optAnimal;
```

## Optional 객체 생성

```java
Optional<Person> maybePerson = Optional.empty();
```

null을 담고 있는, 한 마디로 비어있는 Optional 객체를 얻어온다.

이 비어있는 객체는 Optional 내부적으로 미리 생성해놓은 싱글턴 인스턴스이다.

```java
Optional<Person> maybePerson = Optional.of(aPerson);
```

null이 아닌 객체를 담은 Optional 객체를 생성한다.

null이 넘어올 경우 NPE를 던지므로 주의

```java
Optional<Person> maybePerson = Optional.ofNullable(aPerson);
```

aPerson 객체가 null이라면 NPE를 던지지 않고 빈 Optional 객체를 반환한다.

해당 객체가 null인지 아닌지 모르겠다면 이 메소드를 사용하자

## Optional이 가진 객체에 접근하기

- get()

비어있는 Optional 객체라면 NoSuchElementException을 던진다.

- orElse(T other)

비어있는 Optional 객체에 대해서 넘어온 인자를 반환한다.

- orElseGet(Supplier<? extends T> other)

비어있는 Optional 객체에 대해서 넘어온 함수형 인자를 통해 생성된 객체를 반환한다.



## Reference

https://www.daleseo.com/java8-optional-after/

https://jdm.kr/blog/234