# 자바 버전별 기능

자바의 버전들에 대해서 알아보자,

일반적으로 2020년 1월 기준 가장 최근 버전은 13버전이며, LTS는 11버전이다.

LTS는 Long Term Support라는 의미로 해당 버전에 대해 장기적인 지원을 보장한다는 의미이다.

다시 말해서 자바 11버전의 경우 버그나 수정사항이 생겨도 향후 8년 동안 업데이트를 해주겠다는 의미이다. 

지금 부터 자바의 변천사를 보도록 하자.

## Java 7

### G1 GC의 등장

바둑판의 2MB 크기에 old,young 영역을 할당하여 GC하는 새로운 방식의 GC이다.

9버전부터 Default GC가 되었다.

Latency를 낮추면서 Throughput을 높이기 위해 등장하였다.

Parallel GC의 장점과 CMS GC의 장점을 가져온 GC.

young 영역에는 Parallel GC를, Old 영역에는 CMS GC와 유사한 방법을 사용한다.

### 제네릭 타입을 양쪽에 항상 명시했어야했는데 7버전 부터 다이아몬드 연산자를 지원

```java
List<Integer> list = new ArrayList<>(); // 오른쪽에 제네릭 타입을 명시하지 않는다.
```

- Switch 문에 String 사용 가능

```java
switch(day){
  case "MonDay"
    break;
  case "Tuesday"
    break;
  ...
}
```

### Try-With-Resource

자바 7버전 이전에는 DB Connection, File Stream을 open 했을 때 예기치 못한 오류 발생시 정상 종료를 위해 finally block에서 close 처리를 반드시 해야했다.

그러나 7버전 이후 부터는 try with resource 구문이 추가되어 자동으로 자원을 close 해준다.

이 메커니즘을 사용하기 위해서는 AutoClosable, Closable 인터페이스를 구현해야한다.

자바 7의 Streams,Files,Socket,DB Connection 등은 이러한 인터페이스를 구현하고 있다.

```java
FileOutputStream out = null; 
try { 
  out = new  FileOutputStream("exFile.txt"); //...이후 입출력 로직 처리... 
} catch (FileNotFoundException e) {          
  e.printStackTrace(); 
} finally { 
    if(out != null) { //스트림이 null인지 체크 
      try { 
        out.close(); //close 하다가 예외가 발생할 수 있다. 
      } catch (IOException e) { 
        e.printStackTrace(); 
      } 
    }
}
```
7버전 이전에는 이런식으로 finally 처리를 해야만 했다.

```java
try(FileOutputStream out = new FileOutputStream("exFile.txt")) { 
  //...이후 입출력 로직 처리... 
}catch(IOException e){ 
  e.printStackTrace(); 
}
```
그러나 7버전 이후부터는 입출력 처리시 예외가 발생할 경우 JVM이 자동으로 close()를 호출하여 자원을 반납해준다.

소스 가독성이 상당히 증가한 것을 알 수 있다.

### Fork/Join Framework

Fork/Join Framework는 멀티 프로세서의 성능을 이용할 수 있는 ExecutorService 인터페이스의 구현체다.

반복적으로 작은 조각으로 나누어 작업할 수 있게 설계되어 있다.

Fork/Join Framework는 ThreadPool내 WorkerThread에게 작업들을 분배한다.

Fork/Join Framework는 work-stealing Algorithm라는 알고리즘을 사용한다.

이 알고리즘은 일이 없는 WorkerThread가 다른 Thread의 작업을 훔쳐오는 알고리즘이다.

Fork/Join Framework의 핵심은 ForkJoinPool 인터페이스이다.

### Underscore in Numeric literal

숫자형(정수,실수)에 _(Underscore) 문자열을 사용할 수 있게 되었다.

이로써 큰 숫자를 다룰때 가독성이 높아졌다.

```java
int billion = 1_000_000_000; // 10^9 
long creditCardNumber = 1234_4567_8901_2345L; //16 digit number 
long ssn = 777_99_8888L; 
double pi = 3.1415_9265; 
float pif = 3.14_15_92_65f;
```

숫자 시작이나 끝에 _를 붙이거나, 소수점 뒤에 붙이면 컴파일 에러가 발생하니 주의

## Java 8

오라클이 Sun을 인수 후 첫 버전이다.

사실상 자바 8 버전은 **모던 자바**라고도 불리우며, 많은 부분이 추가되었다.

지금부터 자바 8의 변화에 하나씩 알아보자

### Lambda와 함수형 패러다임

기존의 자바가 객체지향 언어였다면 8버전부터는 함수형 프로그래밍을 도입하였다.

정확히 말하자면, 자바 8부터는 **함수형 인터페이스**를 통해 메소드를 **일급 시민**으로 다룰 수 있게 하였다.

기존의 자바 Method는 일급 시민의 조건을 만족하지 않았었다.

기존 자바의 1급 시민은 **객체**였다.

그러나 함수형 프로그래밍에서는 **함수**가 1급 시민이 되어야한다는 조건이 있다.

**함수가 1급 시민이 되기 위한 조건**

1. 함수를 변수에 담을 수 있어야 한다.

2. 함수의 인자값으로 함수를 받을 수 있어야 한다.

3. 함수를 반환값으로 return 할 수 있다.

위 세가지 조건을 만족해야 함수가 1급 시민이라고 하며 함수형 프로글매이에선 이 **함수**가 1급 시민의 조건을 만족한다.

하지만 자바에는 **함수**가 없다.

그래서 자바 8 버전부터는 **함수형 인터페이스**를 통해 메소드가 일급시민이 될 수 있게 해준다. 

이 **함수형 인터페이스**는 람다식으로 구현하는데, 람다식을 사용하면 자바 컴파일러가 **지금 함수형 인터페이스를 구현하고 있구나?**라고 판단하고 적절한 함수형 인터페이스의 Abstract Method와 바인딩 해준다.

함수형 프로그래밍의 장점은 Side Effect의 제거도 있지만, **고차 함수**의 활용 때문이기도 하다.

```java
(Long val1, String val2) -> val1 + val2.length();
```

위 식은 마치 자바에서 함수 자료형을 지원해서 메소드 자체를 결과값으로 반환하는 것 처럼 보이지만, 사실 반환값은 **객체**이다.

추상 메소드만 존재하는 인터페이스를 익명 클래스가 구현하고 메소드 오버라이딩을 할 때 람다식을 이용할 수 있는 인터페이스를 제공하는 것이다.

**함수형 인터페이스**는 추상 메서드가 1개인 인터페이스로, 이 인터페이스를 구현하는 객체에는 **단 하나의 메소드**만 존재한다고 약속하고 위와같은 람다식을 작성할 수 있는 것이다.

**함수형 인터페이스**를 통해 자바에서는 고차 함수 작업이 가능해지게 되었다.

**고차 함수**란 함수를 매개변수로 받거나 함수를 반환하는 함수를 의미한다.

Array 객체의 forEach, map, filter같은 메소드가 대표적인 **고차 함수**의 예시이다.

**함수형 인터페이스**는 다음과 같이 제공되고 있다.

```java
public interface Function<T,R> {R apply(T t);} // 매개변수,반환값이 있는 경우
public interface Predicte<T> { boolean test(T t);} // 매개변수가 있고 boolean을 리턴한다.

public interface Consumer<T>{ accept(T t); } // T타입의 매개변수가 있고 리턴은 하지 않는다. 즉, 전달받은 인자를 소비하고 끝낸다.

public interface Supplier<T> { T get();} // 매개변수가 없고 리턴 타입만 존재한다. 매개변수를 받지 않기 때문에 get 함수는 항상 일정한 값을 반환한다.

public interface Runnable { run(); } // 매개변수도 없고 리턴값도 없다.

```

### Stream

우리는 앞서 람다식을 이용해서 함수형 인터페이스를 구현하는 익명 클래스를 만들 수 있다고 했다.

자바 Stream은 이 람다식을 활용할 수 있는 기술이기도 하다.

Stream은 **데이터의 흐름**이다. 배열이나 컬렉션 인스턴스에 여러개의 함수를 조합하여 원하는 결과를 필터링하고 가공된 결과를 얻을 수 있다.

즉, 배열과 컬렉션을 함수형으로 처리할 수 있다.

백문이 불여일견, 직접 Stream의 기능을 사용해보면 왜 Stream을 쓰는지 바로 알 수 있다.

```java

// 스트림을 사용하지 않은 경우
List<String> names = Arrays.asList("Jo","Min","Kuk");

long count = 0;

for(String name : names){
  if(names.contains("i")){
    count++;
  }
}
System.out.println(count) // 1

// 스트림을 사용한 경우
count = 0;
count = names.stream().filter(x -> x.contains.("i")).count();
System.out.println(count) // 1
```

불필요한 if,for문을 걷어내고 간결하고 직관적인 코드를 만들 수 있게 되고, 이것이 스트림을 이용하는 가장 큰 **장점이자 목적**이다.

Stream의 구조는 크게 세가지로 나뉜다.

1. 스트림 생성

2. 중개 연산

3. 최종 연산

Collection같은 객체에서는 

**스트림생성()**.**중개연산()**.**최종연산()**과 같은 식으로 진행된다.

위에서는 중개연산이 filter()였고, 최종 연산이 count() 였다.

그럼 지금부터 중개연산에는 어떤 것이 있는지 알아보자.

**Filter**

Filter는 말 그대로 필터링, 조건에 맞는 것만 거른다는 의미이다.

if와 마찬가지로 람다식에서 return 값이 true인 것들만 거른다.

**Map**

map은 각 스트림의 요소를 연산하는데 쓰인다.

만약

```java
List<String> names = Arrays.asList("Jo","Min","Kuk");
names.stream().map((x) -> {return x.concat("s");}).forEach(System.out::println);
```
위를 실행하면 Jo,Min,Kuk의 마지막에 s가 붙게 되는 식이다.

**Sorted**

이름 그대로 스트림의 요소들을 정렬하는데 사용할 수 있다.

지금까지 중개연산에 대해 알아보았으니 최종 연산에 무엇이 있는지도 알아보자.

**count(), min(), max(), sum(), average()**

이름 그대로 최종 연산 결과에 대한 개수,최소,최대,합계,평균 값을 얻을 수 있다.

**forEach**

각 요소를 돌면서 처리할 수 있다.

착각하는 것이 있는데, forEach는 스트림의 각 요소 값을 변경할 수 없다.

**collect**

스트림의 값들을 모아주는 기능을 한다. toMap,toSet,tolist 등 스트림을 다시 컬렉션으로 반환해준다.


**Stream에서 알아두어야할 점**

Stream은 기본적으로 재사용이 불가능하다.

한번 사용한 Stream을 재사용하려고 하면 에러가 난다.

또한 Stream은 병렬 Stream인 **parallelStream()** 기능을 제공하는데, 이 경우 여러 쓰레드가 처리해줄 수 있다.

**중개 연산은 미리 하지 않는다. 지연 연산을 한다.**

```java
Stream<String> a = names.stream().filter(x -> x.contains("i")).map(x -> x.concat("s"));

a.forEach(System.out::println);
```

위의 구문에서 중개연산인 filter와 map은 연산을 수행하지 않고, forEach를 만났을 때 비로소 연산 한다.

이것의 장점은 stream이 미리 계산해서 출력 구문을 만날때 다시 순회하는 멍청한 짓은 하지않는다는 것을 의미한다.

### Default Method

8버전에 추가된 Default Method는 메소드 앞에 Default 키워드를 붙이면 인터페이스에서도 메소드를 구현하는 것이 가능해진다.

이것이 등장한 배경에 대해서 알 필요가 있다.

Interface는 분명 인터페이스에 정의된 기능 외에는 고려하지않아도 되고, 개발자 입장에서는 인터페이스가 원하는 시그니처만 충족시켜주면 모듈을 사용할 수 있다는 장점이 있었다.

Interface는 모듈 개발 시 생각을 단순화 시킬 수 있고, 모듈간의 유연성을 높여줄 수 있다.

라이브러리 설계자들 또한 Interface 위주로 모듈을 설계하여 배포하며 개발자들은 해당 라이브러리를 사용하여 Interface를 구현만 하면 됐었다.

그러나 Interface는 변경에 굉장히 취약하다는 약점이 있다.

만약 Interface를 변경한다면 이를 구현하는 Class들을 모두 변경해줘야한다는 단점이 있다.

Java 8에서는 이를 위해 Default Method를 개발하였다.

Default Method는 인터페이스 내부에 공통적으로 수행해야할 일을 정의할 수 있으며 이를 통해 인터페이스 변경 시 호환성 문제를 해결할 수 있게 되었다.

Java8에서는 인터페이스의 변경에 유연하게 대처하기 위하여 Default Method를 사용했고, 그 대표적인 예시가 **Collection**이다.

```java
List<Integer> numberList = IntStream.range(0, 100).boxed().collect(Collectors.toList());

// Java 8 이전의 정렬 
Collections.sort(numberList,(a,b) -> a.compareTo(b));

// Default Method의 등장 이후 Java 8의 정렬
numberList.sort((a,b) -> a.compareTo(b));
```

하지만 이러한 편리한 Default Method의 등장에 다중 상속 문제가 발생하게 된다.

Interface는 기본적으로 여러개 구현이 가능하지만, 만약 Default Method가 같은 2개의 인터페이스를 구현하려는 클래스는 둘 중 어떤 Default Method를 호출해야하는지 모호함이 발생한다.

이러한 모호함을 막기 위해 Java 8에서는 규율을 정해놓았다.

1. 클래스에서 Default Method를 오버라이딩 해버린다.

인터페이스에 구현된 Default Method를 구현함으로써 모호성을 제거할 수 있다.

2. Sub interface가 Default Method를 재정의 한다면 Sub interface의 Default Method를 사용한다.

interface간 상속구조가 존재하고, 자식 interface가 부모 interface의 Default Method를 재정의한다면 자식 interface를 구현하는 클래스에서 호출하는 Default Method는 자식의 Default Method로 한다.

3. 만약 Default 메소드의 우선순위가 정해져있지 않다면, 명시적으로 선언하여 모호함을 없앤다.

Java 8에서는 인터페이스의 Default Method를 명시하기 위해

`interfaceName.super.method()`

형태의 문법을 제공한다.

Default Method에 의한 충돌 문제는 위 세가지 규칙으로 해결 가능성이 존재한다.

Default Method가 추가되면서 interface의 변경에 대한 호환성이 증대되었으며 새로운 기능을 interface 쉽게 붙일 수 있게 되었다.

### Method Reference

메서드 레퍼런스는 특정 메서드만을 호출하는 람다의 축약 표현이다.

메서드 레퍼런스는 메서드명 앞에 구분자(::)를 붙이는 방식으로 사용된다.

```java
List<String> list = Arrays.asList("Jo","Min","Kuk");
list.forEach( (String s) -> System.out.println(s));

//Method Reference
list.forEach(System.out::println);
```

명시적으로 메서드명을 참조하게 만듦으로써 가독성을 높일 수 있다는 장점이 있다.

### Serial GC Deprecated

### 날짜와 시간을 나타내는 LocalDate 등장

```java
LocalDate localDate = LocalDate.now();
```

## Java 9

### try-with-resource 개선

```java
void tryJava7and8() throws IOException{
  BufferedReader reader1 = new BufferedReader(new FileReader("test.txt"));
  try(BufferedReader reader2 = reader1){
      // Do Something
  }
}

void tryJava9() throws IOException{
  BufferedReader reader1 = new BufferedReader(new FileReader("test.txt"));
  try(reader1){
      // Do Something
  }
}
```

### 익명 클래스에 대한 Diamond Operator 허용

```java
Person<?> person = new Person<>("Minkuk"){
  @Override
  void do(){
    // Do Something
  }
}

### 불변 Collection 생성 메서드 제공

```java
List immutableList = List.of("one","two","three");

Map immutableMap = Map.of(1, "one", 2, "two");
```

### 인터페이스 내부에서 private 사용 가능

인터페이스의 캡슐화를 높임

### HTTP 2 클라이언트

HTTP/1.1 및 HTTP/2 프로토콜 지원

동기/비동기 모드 지원

### G1 GC가 Default GC

자바 7에서 처음 등장했던 G1 GC가 자바 9버전부터 Default GC가 되었다.

## Java 10

Java 10에서 바뀐 부분을 알아보자

### Local-Variable Type Inference

```java
var list = new ArrayList<String>();
var stream = list.stream();
```

### Parallel Full GC for G1

기존 G1 GC에서는 Full GC를 피할 수 있게 설계 되었지만, concurrent GC에서 충분한 young 영역을 확보하지 못하게 될 경우 Full GC가 발생한다.

기존 G1 GC에서 Full GC를 수행할때 mark-sweep-compact 알고리즘은 싱글 쓰레드 방식으로 작동했었다.

Java 10에서는 이를 개선하여 mark-sweep-compact 알고리즘이 병렬로 수행하도록 하여 Full GC가 발생할 때의 Latency를 줄이고자 하였다.

### Thread-Local Handshakes

JVM에서 Full GC가 발생하면 흔히 말하는 'Stop-The-World' 현상이 발생하면서 GC를 수행하는 쓰레드 외에 모든 쓰레드가 멈춘다.

10버전에서는 이전 처럼 모든 쓰레드들이 멈추지 않고 쓰레드를 개별로 멈출 수 있게 되었다.

### Heap Allocation on Alternative Memory Devices

JVM Heap 영역을 NVDIMM(비 휘발성 메모리) 혹은 사용자 지정과 같은 대체 메모리 장치에 할당할 수 있게 되었다.

## 자바 11

현재 LTS 버전이기도 한 자바 11버전의 변경에 대해 알아보자.

### ZGC의 등장

Latency를 극도로 낮춘 새로운 GC가 등장하였다.

ZGC는 Stop-The-World의 시간을 10ms 미만으로 줄이고 15% 이하의 성능 페널티를 목표로 하고 있다.

### 새로운 HTTP 라이브러리

표준 HTTP 클라이언트 API의 등장

HTTP 1.1과 2를 모두 지원하며 HTTP 클라이언트가 요청을 보내고 서버에서 응답을 받는 성능을 향상 시키도록 설계되었다.

### Lambda에서의 var 사용 가능

```java
list.stream()
  .map((var s) -> s.toLowerCase())
  .collect(Collectors.toList());
```

## 자바 12

Switch문에 화살표 사용 가능

```java
switch (day) {
    case MONDAY:
    case FRIDAY:
    case SUNDAY:
        System.out.println(6);
        break;
    case TUESDAY:
        System.out.println(7);
        break;
    case THURSDAY:
    case SATURDAY:
        System.out.println(8);
        break;
    case WEDNESDAY:
        System.out.println(9);
        break;
}
```
기존 Switch 문

```java
switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
    case TUESDAY                -> System.out.println(7);
    case THURSDAY, SATURDAY     -> System.out.println(8);
    case WEDNESDAY              -> System.out.println(9);
}
```
람다식과 비슷한 표현이 가능해진 Switch 문

```java
int numLetters = switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> 6;
    case TUESDAY                -> 7;
    case THURSDAY, SATURDAY     -> 8;
    case WEDNESDAY              -> 9;
};
```

Return 값을 받는 것도 가능해짐

### Java 13

6개월마다 새로운 버전이 출시되다 보니 크게 바뀌는 부분이 많지는 않다.

크게 3가지가 바뀌었는데

1. Text Block
2. Switch Expression
3. Legacy Socket API

지금부터 하나씩 알아보자

### Text Block

Kotlin의 Multiline String Literal 처럼 자바에도 Text Block 기능이 추가 되었다. (코틀린을 의식했나?)

```java
String html = """
              <html>
                  <body>
                      <p>Hello, world</p>
                  </body>
              </html>
              """;
```
위와 같이 2차원 블록으로 표현이 가능하다

```kotlin
val html = """
              <html>
                  <body>
                      <p>Hello, world</p>
                  </body>
              </html>
              """
```
이는 코틀린에서 제공하는 Multiline String Literal과 똑같다.

### Switch Expression

12버전에서 피드백을 받아 수정하였다.

12버전에서 Switch문의 결과값을 반환받을 수 있었는데

이것을 13버전에서는 yield로 반환받게끔 하였다.

```java
int code = 2;
String result = switch(code){
    case 1:
        yield "1번";
    case 2:
        yield "2번";
    case 3:
        yield "3번";
};
```

break 값 반환이 사라지고 13버전에서 Switch문에서 반환값을 받으려면 yield를 사용해야 한다.

### Socket API

java.net.Socket 클래스는 자바 1.0버전에 C와 Java를 혼용해서 만든 클래스다.

디버깅 및 유지관리가 어려워 새롭게 디자인 했다고 한다.

만약 Socket을 사용한다면 13버전의 사용을 고려해볼만 하다.


## 버전 별 요약

### Java 7
- G1 GC 등장
- 다이아몬드 연산자 지원
- Fork/Join Framework 등장 (work-stealing Algorithm )
- try-with-resource AutoClosable 인터페이스를 통해 DB Connection,Socket,FILE 등 close를 명시하지않아도 되는 기능 추가
- 숫자에 언더스코어로 가독성 높일 수 있게 되었다.

### Java 8
- 함수형 프로그래밍 개념 도입, 함수형 인터페이스 제공
- Lambda
- Stream
- Default Method interface를 통한 인터페이스 다중상속 지원, interface의 변경에 호환성이 좋음
- Default Method (e.q. System.out::println)
- 새로운 날짜와 시간 API인 java.time 제공 (LocalDate, ZonedDateTime.. )

### Java 9
- 익명 클래스에 대한 다이아몬드 오퍼레이터 제공
- try-with-resource 개선 ( 기존에는 try 밖에 선언한 변수를 try문 안에서 사용할 수 없어서 새로운 변수에 할당해줬어야 했는데 9버전부터 그렇게 안해도 된다. )
- 불변 타입을 만들어주는 팩토리 메서드 제공
- Default method, static method에 private 기능 제공
- HTTP 2 클라이언트 등장
- G1 GC가 Default GC가 되었다.

### Java 10
- Local Variable Type Inference 제공 (var)
- Parallel Full G1 GC ( 멀티 스레드로 Full GC를 수행하여 성능을 높임 )
- Heap 메모리 영역을 다른 곳에다 저장 가능
- Thread Local HandShake (Full GC시에 전체 스레드가 멈추는게 아닌 개별 스레드 정지 가능)

### Java 11
- 새로운 HTTP 표준 라이브러리의 등장
- ZGC의 등장 (10ms 이하의 Latency, 15% 이하의 성능 저하)
- Lambda 내부에서 var 사용 가능

### Java 12
- Switch 문에 화살표 사용 가능(람다처럼)

### Java 13
- Kotlin의 Text Block ( """ """ ) 추가
- Switch 문 반환시 yield로 반환 가능
- 기존 Socket API가 너무 구식이라서 새롭게 만듬

## Reference

### 자바 7
https://thatisgood.tistory.com/entry/Java-10-%EB%B2%84%EC%A0%84-%ED%8A%B9%EC%A7%95

https://dololak.tistory.com/67

https://okky.kr/article/345720

### 자바 8

https://brunch.co.kr/@kd4/12

https://futurecreator.github.io/2018/08/26/java-8-streams/

https://jeong-pro.tistory.com/165

https://doohyun.tistory.com/47

### 자바 9

https://medium.com/@goinhacker/java-9%EC%9D%98-%EB%B3%80%ED%99%94%EC%99%80-%ED%8A%B9%EC%A7%95-%EB%8C%80%EC%B6%A9-%EC%A0%95%EB%A6%AC-fca77cee88f2

### 자바 10

https://itstory.tk/entry/Java-10-%EC%8B%A0%EA%B7%9C-%EA%B8%B0%EB%8A%A5%ED%8A%B9%EC%A7%95-%EC%A0%95%EB%A6%AC

https://jusungpark.tistory.com/58

### 자바 11

https://futurecreator.github.io/2018/09/29/java-11-released/

https://dzone.com/articles/java-11-standardized-http-client-api

### 자바 12

http://revelope.kr/

### 자바 13

http://revelope.kr/