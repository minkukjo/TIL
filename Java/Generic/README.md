# Generic Type

이번 시간에는 자바의 제네릭 타입에 대해 알아보자.

사전적 의미로 Generic은 일반적인 이라는 뜻이다.

클래스 내부에서 사용될 자료형을 지정하는 것이다.

Java 1.5버전 부터 추가되었으며 주로 Collection에서 사용된다.

백문이 불여일견 직접 보면서 이해하도록 하자.

```java
class Foo<T>{
  prviate T t;

  public void set(T t){
    this.t = t;
  }

  public T get(){
    return t;
  }
}
```
기본적인 클래스를 Java로 구현해보았다.

Foo라는 클래스 내부에 사용될 타입을 외부에서 지정할 수 있다.

대표적으로 ArrayList가 있다.

```java
ArrayList<Integer> list = new ArrayList<>();
```

list안에 들어갈 자료형을 Integer로 하겠다는 의미이다.

이것 또한 Generic이 있기에 가능한 문법이다.

그렇다면 이 Generic은 왜 쓰는 것일까?

Generic이 나오기 이전 Java에서는 다음과 같이 객체를 할당했다.

```java
ArrayList list = new ArrayList();
list.add(1);
list.add("Hi");
list.add(3.045f);
```
위의 경우 list는 최상위 클래스인 Object 클래스 타입으로 설정이 됩니다.

당연히 컴파일 시에 오류를 발생시키지 않습니다.

제네릭 타입을 사용한다면 어떻게 될까요?

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(1); 
list.add("Hi"); // 여기서 에러가 남
list.add(3.045f);
```

제네릭은 컴파일 단계에서 이미 오류를 알아차릴 수 있게 되지요.

제네릭을 사용하지않으면, 오류가 발생해도 컴파일 단계에서 알기가 어렵습니다.

타입을 지정해줌으로써 컴파일 단계에서 개발자의 실수를 막아줄 수 있는 것이지요.

개발자 또한 사람이기 때문에 실수를 할 수 있고, 그 실수를 막아주는 훌륭한 기능이 바로 제네릭입니다.

이것이 제네릭을 사용하는 이유이자 제네릭의 가장 큰 장점입니다.

마지막으로 제네릭에 네이밍 규칙에 대해 알아보고 마무리하도록 합시다.

```
E - Element
K - Key
N - Number
T - Type
V - Value
```

<T>, <K, V>와 같이 제네릭 타입으로 명시되는 이름은 위와같은 의미로 사용하도록 약속되어 있습니다.

# Reference