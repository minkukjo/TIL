# Proxy

<img width="652" alt="스크린샷 2020-01-05 오전 1 44 29" src="https://user-images.githubusercontent.com/43809168/71768780-ef821d80-2f5c-11ea-8f24-17ee46438451.png">

프록시 패턴은 디자인 패턴의 한 종류이다.

**Proxy**는 대리인이라는 뜻을 가진다.

자바에서의 **Proxy**는 어떤 클래스의 수행을 대신 해준다는 의미를 갖게 된다.

- Client : Proxy 패턴을 사용
- subject : Proxy와 RealSubject가 가져야할 공통 인터페이스
- Proxy : subject를 구현하며 RealSubject의 수행을 대신 담당
- RealSubject : subject를 구현하며 실제 클래스에 해당

Proxy는 내부적으로 RealSubject 인스턴스를 생성하여 특정 수행을 담당하게 된다.

이러한 Proxy 기법은 클래스의 객체 생성에 시간이 많이 걸리는 경우 일을 분업하여 Proxy 클래스에서 처리할 수 있는 부분은 처리하고, Proxy에서 처리가 힘든 작업에 대해서만 실제 클래스의 객체를 생성하고 위임하는 방식을 취한다.

때문에 매우 유용하지만 단점도 있다.

## Proxy의 단점

1. 매번 새로운 클래스 정의 필요, Proxy 클래스는 구현 클래스의 Interface를 모두 구현해야한다.

2. 타깃의 인터페이스를 구현하고 위임하는 코드를 작성하는 것이 번거로움.

3. 부가기능 코드의 중복 위험성 존재

이러한 단점을 극복하기 위해서 **Dynamic Proxy**가 등장하였고, 이는 Spring에서 AOP를 구현할때 사용된다.

## Dynamic Proxy

다이나믹 프록시는 기존 프록시의 단점을 극복하기 위해 등장하였다.

특징으로는 Java의 리플렉션을 이용해 프록시 객체를 동적으로 생성한다.

타깃 인터페이스와 동일한 형태로 만들어지며, 팩토리빈을 통해 생성된다.

다이나믹 프록시 오브젝트는 일반 스프링의 빈(Bean)으로 등록할 수가 없다.

스프링은 내부적으로 리플렉션 API를 이용하여 빈(Bean) 정의에 나오는 클래스 이름으로 Bean Object를 생성한다.

그러나 다이나믹 프록시 오브젝트는 Bean으로 등록할 수 없기때문에 팩토리 빈이 IoC Container를 대신해 오브젝트의 생성 로직을 담당한다.

InvocationHandler 인터페이스의 invoke 메소드를 구현하면

다이나믹 프록시 오브젝트의 메소드가 몇개이든 간에 부가기능을 손쉽게 작성하여 적용할 수 있다.



## Reference

https://lktprogrammer.tistory.com/34

https://ooz.co.kr/205