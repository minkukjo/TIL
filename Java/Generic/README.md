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
위의 경우 list는 최상위 클래스인 Object 클래스 타입으로 설정이 된다.

당연히 컴파일 시에 오류를 발생시키지 않는다.

그런데 만약 이런 경우가 있다고 해보자

```java
Integer value1 = (Integer) list.get(0);
Integer value1 = (Integer) list.get(1);
```

첫번째 경우에서는 오류가 안나지만, 두번째에서 런타임 오류가 발생한다.

잘못된 타입 캐스팅이 발생했다는 오류가 발생한다.

String을 넣고 Integer로 형변환하려고 했기 때문이다.

즉, 위와 같은 경우에는 여러 타입이 들어가기 때문에 어떤 타입으로 형변환이 가능한지 모호한 경우가 많다.

만약 위의 ArrayList를 개선하려고

List 인터페이스를 상속받는 IntegerArrayList와 StringArrayList 두 클래스를 만든다고 상상해보자.

둘의 기능은 거의 같을 텐데, 타입이 다르다는 이유만으로 코드의 중복현상이 발생하게 된다. 

가장 큰 문제는

#### 컴파일 시점에서 어떤 오류도 잡아내지 않는다는 점

그렇다면 제네릭 타입을 사용해서 문제를 해결해보자

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(1); 
list.add("Hi"); // 여기서 에러가 남
list.add(3.045f);
```

제네릭은 컴파일 단계에서 지정한 타입 외의 타입을 넣으려고 시도하면 오류를 발생시켜준다.

타입을 지정해줌으로써 컴파일 단계에서 개발자의 실수를 막아줄 수 있다.

또한 형변환에도 크게 문제가 없다. 지정한 타입과 다른 타입의 참조변수를 선언하면 컴파일 타임에서 오류를 발생시켜버린다.

## 결론

제네릭을 사용하는 가장 큰 이유는 두가지다.

1. 형변환이 필요없고, 타입 안정성이 보장된다.

2. 코드의 재사용성이 높아진다.

## [부록] 제네릭 네이밍 규칙

```
E - Element
K - Key
N - Number
T - Type
V - Value
```

<T>, <K, V>와 같이 제네릭 타입으로 명시되는 이름은 위와같은 의미로 사용하도록 약속되어 있습니다.

# Reference

https://yaboong.github.io/java/2019/01/19/java-generics-1/