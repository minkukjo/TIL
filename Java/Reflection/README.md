# Java Reflection API

리플렉션은 구체적인 클래스 타입을 몰라도 해당 클래스의 메소드, 타입, 변수들에 접근할 수 있게 해주는 API이다.

```java
public class Car(){
  public void drive(){
    // Do Something
  }
}

public class Main(){
  public static void main(Stirng[] args){
    Object car = new Car();
    car.drive(); // 컴파일 에러
  }
}
```

위와 같은 코드가 있다고 할때 car.drive는 컴파일 에러를 발생시킨다.

왜냐하면 Object는 모든 클래스의 최상위 클래스이므로 Car 클래스의 인스턴스를 담을 수 있지만 사용 가능한 메소드에 drive는 없기 떄문이다.

이런 경우 구체적인 타입의 클래스를 모를 때 사용하는 것이 Reflection 이다.

컴파일한 클래스 정보를 활용해 프로그래밍이 가능하도록 지원하는 Java의 API이자 자바에서만 보여주는 언어적 특성이다.



## Reference 

https://brunch.co.kr/@kd4/8