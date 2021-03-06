# Strategy Pattern

 
<img width="597" alt="스크린샷 2020-02-09 오후 9 32 34" src="https://user-images.githubusercontent.com/43809168/74102093-b3596280-4b83-11ea-8c7f-4a4a98fbb465.png">

위와 같은 객체지향 설계 UML 다이어그램이 있다.

민국은 오리 연못 시뮬레이션 게임을 만드는 회사에 다니고 있다.

민국은 오리가 꽥꽥 거리고 헤엄치며 겉모습을 보여주는 어플리케이션을 만들었다.

각각의 오리들은 모양이 다르기 때문에 display 메소드를 오버라이딩하였고 이는 객체지향의 특징 중 하나인 다형성이다.

척 보기에 위의 객체지향은 나쁘지않아 보인다.

그러던 어느날 회사의 임원들은 오리들이 날아다녔으면 좋겠다고 하였고 민국은 오리를 날아다니게 만들어야 했다.

<img width="603" alt="스크린샷 2020-02-09 오후 9 35 46" src="https://user-images.githubusercontent.com/43809168/74102138-24991580-4b84-11ea-8a62-e84c91865373.png">

그래서 fly라는 메소드를 최상위 클래스인 Duck 클래스에 추가했다.

이로써 회사 임원들의 요구사항을 만족했다고 생각한 민국은 안심하고 있었다.

그러나 얼마 뒤 회사는 발칵 뒤집어졌다.

회사 임원들이 주주총회에서 게임 시뮬레이션을 투자자들 앞에서 보여줬는데, 오리 인형이 화면에 날아다니는 것이다.

<img width="617" alt="스크린샷 2020-02-09 오후 9 37 40" src="https://user-images.githubusercontent.com/43809168/74102175-67f38400-4b84-11ea-8206-8fa82e8bd4d3.png">


민국은 날아서는 안되는 오리 인형마저 날아다니게 만들어버린 것이다.

이를 고민하던 민국은 한가지 묘수를 내게 된다.

그것은 고무 오리 인형은 fly 메소드를 오버라이딩해서 아무것도 하지 않으면 되지 않겠어? 라고 생각한 것이다.

<img width="366" alt="스크린샷 2020-02-09 오후 9 38 44" src="https://user-images.githubusercontent.com/43809168/74102197-91acab00-4b84-11ea-9c58-e244a83f629b.png">


하지만 그것은 임시방편에 불과했다.

<img width="368" alt="스크린샷 2020-02-09 오후 9 39 40" src="https://user-images.githubusercontent.com/43809168/74102212-b30d9700-4b84-11ea-9156-4df1da3e5456.png">


이후 조각으로 된 가짜 오리를 추가할 때 민국은 고민에 빠진다.

가짜 오리는 울어서도 안되며 날아서도 안된다.

민국은 이때부터 뭔가 이건 아니지 않나? 싶은 생각이 들기 시작한다.

왜냐하면 임원들이 이제부터 6개월마다 새로운 오리들을 갱신하기로 결정했기 때문이다.

그 말은 Duck 클래스가 언제든 바뀔 수 있음을 의미하기 때문에 그때마다 fly()와 quack() 메소드를 일일이 살펴봐야 했고 상황에 따라 오버라이딩도 해야하기 때문에 이 일이 앞으로 계속 반복될 것이다.

결국 고민하던 민국은 일부 오리만 날거나 꽥꽥거릴 수 있게 인터페이스를 사용하기로 결정한다.

<img width="628" alt="스크린샷 2020-02-09 오후 9 42 04" src="https://user-images.githubusercontent.com/43809168/74102245-054eb800-4b85-11ea-81af-b837de7447c5.png">

이 구조는 괜찮을까?

만약 fly와 quack이 바뀌게 되면 모든 클래스들의 메소드들을 찾아가면서 일일이 바꿔주어야 한다.

즉, 이 방법도 좋은 방법이 아니였다.

민국은 어떻게해야할까?

소프트웨어 디자인 원칙 중 다음과 같은 원칙이 존재한다.

```
어플리케이션에서 달라지는 부분을 찾아내고,
달라지지 않는 부분으로 부터 분리시킨다.
```

바뀌는 부분을 따로 뽑아서 캡슐화 시킨다.

그렇게 하면 나중에 바뀌지 않는 부분에는 영향을 미치지 않은 채로 그 부분만 고치거나 확장하는 것이 가능하다.

이는 모든 디자인 패턴의 기반을 이루는 원칙이기도 하다.

시스템의 일부분을 다른 부분과 독립적으로 변화시킬 수 있는 방법을 제공하기 위한 것이기 때문이다.

지금 Duck 클래스에서 바뀌는 부분은 어떤 부분인가?

날아다니는 행동인 fly()와 꽥꽥거리는 quack()이다.

좋다 그러면 바뀌는 부분을 알아냈으니 어떻게 이 클래스를 구성해주어야할까?

이때도 역시나 다음과 같은 디자인 원칙이 존재한다.

```
구현이 아닌 인터페이스에 맞춰서 프로그래밍한다.
```

각 행동은 **FlyBehavior**과 **QuackBehavior**라는 인터페이스로 표현하고 실질적인 행동은 이 인터페이들을 구현하도록 하자.

날아다니는 행동과 꽥꽥거리는 행동은 이제 Duck 클래스에서 구현하지 않는다.

<img width="521" alt="스크린샷 2020-02-09 오후 9 49 35" src="https://user-images.githubusercontent.com/43809168/74102338-13510880-4b86-11ea-9ec4-d1e0e35439c9.png">

위와 같이 인터페이스를 구현한 클래스에서 구체적인 행동을 구현한다.

이렇게 하면 날아다니는 행위를 바꿀때도 FlyWithWings 클래스의 fly 메소드를 수정하는 것으로 공통적으로 변경할 수 있으며 추가적인 날아다니는 행동이 필요하면 새로운 클래스를 만들어 FlyBehavior를 구현하면 될 것이다.

만약 오리가 로켓 엔진으로 날아다니는 행위를 표현해야 한다면 어떻게 해야할까?

```java
public class FlyRocketPowered implements FlyBehavior{
  public void fly() {
    System.out.println("로켓 발사!!!");
  }
}
```

FlyBehavior를 구현하는 FlyRocketPowered를 만들면 간단하게 새로운 행위로 확장하는 것이 가능하게 된다.

<img width="606" alt="스크린샷 2020-02-09 오후 9 51 30" src="https://user-images.githubusercontent.com/43809168/74102372-57440d80-4b86-11ea-8afd-6944a69904bf.png">

최종적으로 위와 같이 표현할 수 있을 것이다.

이제 전체 클래스 구조를 보도록 하자.

<img width="600" alt="스크린샷 2020-02-09 오후 9 52 06" src="https://user-images.githubusercontent.com/43809168/74102387-6cb93780-4b86-11ea-97a2-c50927b680cc.png">

Duck 클래스는 행동에 해당하는 인터페이스를 상태로 갖는다.

각각의 인터페이스의 구현체는 setter를 이용하여 Duck을 상속받는 클래스에서 행위를 명시해줄 수 있을 것이다.

여기서 Duck 클래스는 행위에 해당하는 인터페이스를 갖고 있다. 

오리 클래스는 행동을 상속받는 것 대신 행동 객체로 구성됨으로써 행동을 부여받고 있다.

이처럼 클래스 내부에 다른 클래스를 갖고 있는 것을 Composition이라고 한다.

그래서 다음과 같은 디자인 원칙이 존재한다.

```
상속보다는 Composition을 사용한다.
```

상속은 클래스 재사용에 용이하지않다.

이것은 상속을 배운 사람이라면 모두 알고 있을 것이다.

소프트웨어는 개발이 끝난 후 더 많은 시간을 쏟게 된다.

그 이유는 소프트웨어의 요구사항은 계속 변화하기 때문이다.

변화하는 소프트웨어를 유지보수하기 위해서는 관리하기 용이하고 확장하기 쉬운 구조를 만드는 것이 무엇보다 중요하다.

지금까지 알아본 이 방식이 **Strategy Pattern**이다.

**Strategy Pattern**의 정의는 알고리즘을 정의하고 각각을 캡슐화하여 교환해서 사용할 수 있도록 만드는 디자인 패턴이다.

**Strategy Pattern**을 활용하면 알고리즘을 사용하는 클라이언트와는 독립적으로 알고리즘을 변경하는 것이 가능하다.

## Reference

https://itewbm.tistory.com/entry/Strategy-Pattern-Head-First-Design-Patterns