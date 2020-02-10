# Decorator Pattern

<img width="501" alt="스크린샷 2020-02-10 오후 7 41 31" src="https://user-images.githubusercontent.com/43809168/74143011-58844180-4c3d-11ea-8eab-fc95774f359a.png">

데코레이터 패턴이란 객체에 추가적인 요건을 동적으로 첨가할때 사용한다.

서브클래스를 만드는 것으로 기능을 유연하게 확장하는 디자인 패턴이다.

## 실제 예제

<img width="459" alt="스크린샷 2020-02-10 오후 7 42 18" src="https://user-images.githubusercontent.com/43809168/74143065-75207980-4c3d-11ea-98ba-00a360663eb3.png">

커피 주문 시스템을 떠올려 보자

커피를 주문할 때는 스팀 우유나 두유, 모카를 추가하고 그 위에 휘핑을 얹을 수 있다.

즉 커피에 **Decoration**이 가능하다.

각각을 추가할 때 마다 가격이 올라가기 때문에 주문 시스템에서는 그러한 부분까지 고려해야한다.

<img width="471" alt="스크린샷 2020-02-10 오후 7 43 41" src="https://user-images.githubusercontent.com/43809168/74143183-ac8f2600-4c3d-11ea-8cb9-cfba0c233269.png">

모든 경우의 수를 고려해서 클래스를 만들었더니 상당히 복잡해진 것을 볼 수 있다.

이렇게 되면 유지보수도 어렵고, 토핑의 가짓수가 늘어나거나 신 메뉴가 나올 때 마다 클래스를 계속해서 만들어주어야한다는 문제가 있다.

<img width="256" alt="스크린샷 2020-02-10 오후 7 45 08" src="https://user-images.githubusercontent.com/43809168/74143266-dc3e2e00-4c3d-11ea-854a-3ca7ce21c391.png">

그러면 슈퍼 클래스에다가 토핑 여부를 인스턴스 변수로 갖게 하면 어떨까?

이렇게 하면 메뉴가 추가되더라도, 토핑 여부를 슈퍼클래스에서 갖게 되니까 괜찮아 보인다.

하지만 만약 토핑의 가격이 바뀌거나 새로운 토핑이 추가된다면?

그때마다 슈퍼클래스는 변경되어야한다.

그러나 객체지향의 5원칙 중 다음과 같은 디자인 원칙이 존재한다.

```
OCP(Open-Closed Principle)
클래스는 확장에 대해서는 열려(Open)있어야 하며 변경에 대해서는 닫혀(Closed) 있어야 한다.
```

기존의 코드를 건들지 않고 확장을 통해 새로운 토핑을 추가해야한다.

<img width="830" alt="스크린샷 2020-02-10 오후 7 48 17" src="https://user-images.githubusercontent.com/43809168/74143518-4bb41d80-4c3e-11ea-8ebc-f3bd37e58878.png">

이를 위해 Beverage 클래스를 상속받는 CodimentDecorator 클래스를 만들고

토핑들을 이 클래스를 상속받게 한다.

이 토핑들은 Wrapper 처럼 클래스를 감싸게 된다.

클래스를 감싸게 되면서 OCP를 만족하고 유연한 디자인을 만들어내는 것이 데코레이터 패턴이다.


## Reference

https://jusungpark.tistory.com/9

Head First DesignPattern