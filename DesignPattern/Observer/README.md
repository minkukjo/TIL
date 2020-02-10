# Observer 패턴

옵저버 패턴은 한 객체의 상태가 바뀌면 그 객체에 의존하는 모든 객체들에게 연락이 가고 자동으로 내용이 갱신되는 One-to-Many의 의존성을 정의하는 패턴이다.

![210C973452957FA212](https://user-images.githubusercontent.com/43809168/74140237-10aeeb80-4c38-11ea-83af-dbc5c864d88b.png)

가령 습도,온도,압력 센서를 받아오는 기상스테이션이 있다고 가정하자.

각각의 디스플레이 장비들은 다른 정보를 표시해주는데, 이 때 이 세개의 장비는 기상스테이션에 의존하게 된다.

이러한 패턴을 위해 옵저버 패턴이 존재하는 것이며, 1:N의 의존관계에서 옵저버 패턴을 사용할 수 있다.

## 자바 Oberserver API

![221E813452957FC50A](https://user-images.githubusercontent.com/43809168/74140371-4a7ff200-4c38-11ea-8191-b70b550d4067.jpeg)


자바에서는 java.util.Observable과 java.util.Observer에서 옵저버 패턴을 제공하고 있다.

이러한 자바 내장 API는 얼핏 봐서는 이것을 사용해서 구현하면 옵저버 패턴을 쉽게 사용할 수 있을 것 같지만, 몇가지 단점이 존재한다.

1. Oberserverable은 클래스다.

디자인 원칙에 미루어 볼 때 인터페이스가 아닌 Oberserverable이 클래스라는 것은 치명적 단점이다.

Oberserverable이 클래스이기 때문에 서브클래스에서 이를 상속받아 확장해야 사용할 수 있다.

만약 해당 Subject가 다른 상위 클래스를 상속받고 있는 경우라면 Oberserverable를 사용할 수 없기 때문이다.

Oberserverable라는 인터페이스가 없기 때문에 내장 Observer API와 잘 맞는 클래스를 직접 구현하는 것이 불가능하다. 

2. Oberserverable 클래스의 핵심 메소드를 외부에서 호출할 수 없다.

Oberserverable API의 메소드는 protected로 선언되어 서브 클래스에서만 사용 가능하다.

때문에 이런 디자인은 상속 대신 구성을 사용한다는 디자인 원칙에도 위배된다.

그럼에도 이러한 옵저버 패턴을 사용하는 가장 큰 이유는, Subject와 옵저버로 나눔으로써 객체 사이에 느슨한 결합을 가질 수 있다는 장점이 있기 때문이다.

옵저버는 옵저버 인터페이스를 구현하고 있기 때문에 Subject는 Observer의 구현체를 몰라도 Observer 인터페이스라는 것만 알면 Subject에 옵저버를 추가할 수 있다.

이는 객체지향 디자인에서 느스한 결합을 만드는 아주 좋은 패턴이다.

## Reference

https://hyeonstorage.tistory.com/165

Head First DesignPattern