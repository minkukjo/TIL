# 자바 버전별 기능

자바의 버전들에 대해서 알아보자,

일반적으로 2020년 1월 기준 가장 최근 버전은 13버전이며, LTS는 11버전이다.

LTS는 Long Term Support라는 의미로 해당 버전에 대해 장기적인 지원을 보장한다는 의미이다.

다시 말해서 자바 11버전의 경우 버그나 수정사항이 생겨도 향후 8년 동안 업데이트를 해주겠다는 의미이다. 

지금 부터 자바의 변천사를 보도록 하자.

## Java 7

### 가장 굵직한 변화는 G1 GC가 7버전 부터 등장

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



## Reference

https://thatisgood.tistory.com/entry/Java-10-%EB%B2%84%EC%A0%84-%ED%8A%B9%EC%A7%95

https://dololak.tistory.com/67

https://okky.kr/article/345720