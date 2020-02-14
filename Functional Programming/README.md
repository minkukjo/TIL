# 함수형 프로그래밍

함수형 프로그래밍이란 무엇일까?

함수형 프로그래밍은 절차지향 프로그래밍, 객체지향 프로그래밍과 같은 프로그래밍 패러다임의 한 종류이다.

현대의 프로그래밍 기법이자 람다 계산법에 기인한 프로그래밍 기법이다.

최근 현대 프로그래밍 언어들은 이 함수형 프로그래밍을 도입하고 있다. (e.q. JavaScript, Kotlin, Python, Java8 이상)

특히 Java 8버전부터 람다와 스트림 같은 함수형 프로그래밍을 도입 하였다.

지금부터 왜 현대의 프로그래밍 언어들이 함수형 프로그래밍을 지원하는지 알아보도록 하자.

함수형 프로그래밍을 알아보기에 앞서, 기존 개발 패러다임에 대해 알 필요가 있다.

기존의 개발 패러다임인 절차지향, 객체지향 프로그래밍과 같은 프로그래밍 기법을 **명령형 프로그래밍**이라고 부른다.

**명령형 프로그래밍**은 How to에 집중한다.

즉, 프로그램의 상태를 계속해서 변화시켜 나가면서 연산을 진행하는 것을 **명령형 프로그래밍**이라고 한다.

그에 비해 함수형 프로그래밍은 **선언형 프로그래밍**이라고 한다.

**선언형 프로그래밍**에서의 프로그램은 함수의 계산으로 이루어지며 How to 보다는 What에 집중한다.

여기까지 읽으면 크게 와닿지 않으므로, 예시를 보면서 함수형 프로그래밍이 어떤 가치를 추구하는지 알아보도록 하자.

## 함수형 프로그래밍의 컨셉

1. 변경 가능한 상태는 존재할 수 없다. 모두 불변(Immutable)로 만들어 Side Effect를 제거하자!
2. 모든 것은 객체다. (심지어 함수도)
3. 코드는 최대한 간결하게, 가독성을 높여 구현할 로직에 집중시키자.
4. 동시성 작업을 보다 안전하게 해보자.

## 불변 상태를 만들어 Side Effect를 제거한다?

```kotlin

// 항상 같은 값을 반환하는 순수 함수
fun sum(x:Int, y:Int):Int{
  return x+y
} 

// 외부 값 z에 의해 함수의 반환 값이 다를 수 있다.
// 이러한 경우 순수 함수가 아니라고 부른다.
var z = 10
fun sum2(x:Int, y:Int):Int{
  return x+y+z
}
```

함수형 프로그래밍에선 **순수 함수**라는 개념이 존재한다.

**순수 함수**란 f(x)라는 함수를 실행 했을 때 반환 값은 항상 y가 나와야됨을 의미한다.

즉 함수형 프로그래밍에선 상태가 존재하지 않기 때문에 Side Effect를 사전에 차단할 수 있다.

## 모든 것은 객체다?

함수형 언어에서 모든 것은 객체로 인식한다.

클래스 뿐만 아닌, 함수 조차도 객체이기 때문에 함수를 값으로 할당 할 수 있으며 파라미터로 전달하거나 결과 값으로 반환 받는 것 또한 가능하다.

1. 함수를 변수나 데이터로 할당할 수 있다.
2. 함수를 파라미터로 전달할 수 있다.
3. 함수를 리턴 값으로 리턴할 수 있다.

함수형 언어에서 이러한 3가지 조건을 충족하면 **1급 객체**라고 부른다.

지금부터 Java의 메소드는 왜 1급 객체가 아니며, Kotlin의 함수는 왜 1급객체라고 부르는지 알아보자.

```
변수나 데이터에 할당할 수 있어야 한다
```

```kotlin
object Main{
  @JvmStatic
  fun main(args: Array<String>){
    val a = test
  }

  val test: () -> Unit = { println("Hi") }
}
```

```java
public class java{
  public static void test(){
    System.out.println("java");
  }

  public static void main(String[] args){
    System.out.println("java");
  }
}
```

코틀린에서는 java와 다르게 a라는 변수에 ()->Unit인 test 함수 할당이 가능하다.

하지만 java에서는 변수에 함수를 할당시키는 것은 불가능하다.

## 함수를 파라미터로 전달할 수 있다?

```kotlin
object Main{
  @JvmStatic
  fun main(args: Array<String>){
    function(test)
  }

  fun function(f: ()-> Unit){
    f.invoke()
  }

  val test: () -> Unit = { println("Hi!") }
}
```

위와 같이 함수형 프로그래밍에서는 함수의 인자로 함수 타입을 전달 할 수 있다.

하지만 이는 Java에서는 불가능하다.

## 함수를 리턴 값으로 리턴할 수 있다?


```kotlin
object Main{
  @JvmStatic
  fun main(args: Array<String>){
    function()
  }

  fun function(f: ()-> Unit){
    return { println("Hi") }
  }
}
```
  
Kotlin과 같은 함수형 패러다임 언어에서는 **변수에 함수를 할당할 수 있으며**, **함수의 인자로 전달이 가능하며**, **함수의 리턴값으로 함수를 반환할 수 있다.**

반면 Java에서는 위의 조건들을 만족할 수 없기 때문에 1급 객체라고 부르지 않는다.

## Lazy Evaluation

직역하자면 **게으른 연산**이라는 뜻의 이 기능은 함수형 프로그래밍에서 보여주는 특징 중 하나이다.

직접 이 Lazy Evaluation이 어떻게 동작하는지 봐보자.

```java

final List<Integer> list = Array.asList(1,2,3,4,5,6,7,8,9,10);

System.out.println(
  list.stream()
    .filter(i -> i<6)
    .filter(i -> i%2 == 0)
    .map(i -> i*10)
    .collect(Collection.toList())
);
```

위와 같이 stream API를 이용한 연산을 할 때 결과로는

20,40이 출력이 된다.

만약 함수형 프로그래밍을 모른다면 위 코드의 동작 방식을 다음과 같이 이해할 것이다.

1. 1~10까지 구한 요소들 중 6보다 작은 값들을 다 찾고
2. 그 값(1번에서 구한 결과값)중에서 짝수인 값들을 구해서
3. 2번에서 구한 결과값에 10씩 곱해준다.

라고 생각할 수 있지만 실제 함수형 프로그래밍의 연산 방법은 위와 다르다.

위와 같은 연산 방법을 Eager Evaluation( 조급한 연산 )이라고 부른다.

실제 동작 과정을 알아보기 위해 아래와 같은 코드를 작성하였다.

```java
final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
 
System.out.println(
    list.stream()
        .filter(i -> {
            System.out.println("i < 6");
            return i<6;
        })
        .filter(i -> {
            System.out.println("i%2 == 0");
            return i%2==0;
        })
        .map(i -> {
            System.out.println("i = i*10");
            return i*10;
        })
        .collect(Collectors.toList())
);
```

위의 코드 결과는 다음과 같은 결과를 출력한다.

```
i < 6
i%2 == 0
i < 6
i%2 == 0
i = i*10
i < 6
i%2 == 0
i < 6
i%2 == 0
i = i*10
i < 6
i%2 == 0
i < 6
i < 6
i < 6
i < 6
i < 6
[20, 40]
```

결과를 보면 생각보다 짧은 것을 알 수 있다.

위 코드는 다음과 같이 동작한다.

1. 6보다 작은지 검사한다. 만약 false라면 2,3번을 건너뛰고 다음 요소로 진행한다.

2. 짝수인지 검사한다. 만약 false라면 3번을 건너뛰고 다음 요소를 진행한다.

3. 요소에 10을 곱해준다.

위와 같은 방식의 연산 방법을 Lazy Evaluation이라고 부른다.

Lazy Evaluation은 **불필요한 연산을 피한다**라는 철학을 갖고 있다.

해결해야할 문제들 (filter 2개)가 주어지더라도 마지막 문제를 제공받을 때 까지 게으르게 기다리다가 마지막 문제를 알게 되면 그때 가서야 연산을 시작한다는 의미이다.

아래의 예시를 보자.

```java
Stream<Integer> stream = Stream.iterate(1,n -> n+1)
            .limit(6)
            .peek(System.out::println) // 스트림을 순회하며 하나씩 요소를 꺼내 출력하는 구문
            .filter(number -> number%2 == 0);
```

Java의 Stream은 기본적으로 게으른 연산을 지원한다.

Stream에서는 위의 limit이나 peek, filter는 중간 연산 메소드라고 부른다.

중간 연산 메소드는 계속해서 Stream을 반환하며 Stream이 이어지면서 연산하는 방식을 갖고 있다.

중간 연산의 마지막은 Stream을 종료하는 메소드를 호출하는데 이 메소드의 이름을 **최종 연산 메소드**라고 부른다.

위의 Stream에서는 최종 연산 메소드가 없기 때문에 peek이라는 Stream 메소드가 있음에도 불구하고 실행시 아무런 값도 찍히지 않는다.

```java
Stream<Integer> stream = Stream.iterate(1,n -> n+1)
            .limit(6)
            .peek(System.out::println)
            .filter(number -> number%2 == 0);

stream.collect(toList());
```

하지만 마지막에 최종 연산 메소드인 collect()를 호출하면 비로소 중간 연산들이 수행되게 된다.

이러한 연산 방식을 사용하기 때문에 기존의 조급한 연산보다 함수형 프로그래밍에서의 게으른 연산의 성능이 더 뛰어난 것이다.

조급한 연산과 게으른 연산을 한눈에 볼 수 있는 움짤이 아래에 있다.

![9905FD465C4A993D20](https://user-images.githubusercontent.com/43809168/74427167-e65c6880-4e99-11ea-8b86-9d2b60a1249b.gif)

<center>< Eager Evaluation ></center>

![99CC3B505C4A994A1C](https://user-images.githubusercontent.com/43809168/74427168-e6f4ff00-4e99-11ea-96b6-ab4ebe3024d2.gif)

<center>< Lazy Evaluation ></center><br>

우리가 간단히 생각해보더라도 Lazy Evaluation이 연산에 더 뛰어난 성능을 보인다는 것을 한눈에 쉽게 알 수 있다.

## Kotlin에서는 왜 함수형 프로그래밍을?

Kotlin에서 함수형 프로그래밍과 객체지향 프로그래밍을 더한 멀티패러다임형 프로그래밍을 지원하는 이유는, 함수형 프로그래밍의 장점과 객체지향 프로그래밍의 장점을 모두 더하고 싶었기 때문이다.

Kotlin은 함수형 프로그래밍이 주는 장점과 객체지향 프로그래밍을 합친 **멀티 패러다임 언어**이다.

Kotlin은 기본적인 Null-Safety로 개발 단계에서 올 수 있는 여러가지 문제들을 미리 막아서 개발 단계에서 일어날 수 있는 여러 문제들을 미리 막아준다는 장점을 갖고 있는 언어이기도 하다.

또한, 코틀린 개발자의 말에 따르면, 코틀린을 개발한 가장 큰 이유는 다음과 같다.

```
JVM 기반 엔터프라이즈급 어플리케이션 개발에서 Java를 대체할 수 있는 언어는 많지 않다.
Java는 오랫동안 사랑받아온 언어고, 진화하고 있는 언어지만 조금 더 간결해질 필요가 있었다.
Java의 대안으로 Scala가 있지만 우리는 Scala는 철저히 학문을 위한 언어라고 생각하고 러닝 커브가 너무 어렵다고 생각한다.
그래서 우리는 JVM 기반의 엔터프라이즈급 어플리케이션 개발 생산성을 높이기 위해 Kotlin을 개발했다.
```

## 필자 여담

함수형 언어는 아주 매력적인 프로그래밍 패러다임이다.

기본적으로 함수형 프로그래밍에서의 모든 값들은 불변성을 갖고 있다.

이를 통해 예상치 못한 곳의 변화로 인한 오류를 최대한 제거하였다.

순수 함수는 함수 내부의 불변성을 보장하여 함수가 단일 책임을 갖게 하였다.

이를 통해 순수함수로 함수 내부의 오류를 줄이고 안정성을 높였다.

순수 함수를 조합하여 만든 고차함수는 생산성을 크게 높일 수 있었다.

Lazy Evaluation을 통해 연산의 성능을 높였다.

Kotlin 또한 적극적으로 함수형 프로그래밍 패러다임을 받아들였으며 NPE를 발생하지 않게 하기 위해 Null-Safe한 데이터 타입을 제공하고 있다.

이는 자바 개발자들이 NPE로 인한 골머리(Optional 클래스의 사용)를 앓는 것을 컴파일 단계에서 막아주었다.

또한 간결한 문법과 Getter,Setter,생성자를 제공해주는 Data Class 등은 코틀린이 확실히 자바를 대체할 수 있을 것이라고 나는 확신한다.


# Reference

https://medium.com/@lazysoul/%ED%95%A8%EC%88%98%ED%98%95-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D%EC%9D%B4%EB%9E%80-d881230f2a5e

https://medium.com/@lazysoul/functional-programming-%EC%97%90%EC%84%9C-1%EA%B8%89-%EA%B0%9D%EC%B2%B4%EB%9E%80-ba1aeb048059

https://jeong-pro.tistory.com/23

https://dororongju.tistory.com/137

