# Spring Framework

본 문서는 Spring Framework를 학습하기 위한 문서 입니다.

## Spring이란 무엇인가?

![thisisspring](https://user-images.githubusercontent.com/43809168/71762594-75c64180-2f14-11ea-8ee2-068d9bf12ff2.png)

위는 Spring의 구성요소에 대한 그림이다.

필자가 가장 좋아하는 그림이자 Spring이 무엇인지 쉽게 이해할 수 있는 그림이기도 하여 가져와 보았다.

POJO 객체를 중심으로 개발하는 것이 Spring의 주요한 특징이다.

Spring 프레임워크는 자바 엔터프라이즈 개발을 편하게 하기 위해 개발된 **경량급 오픈소스 웹어플리케이션 프레임워크**이다.

경량급이라는 표현이 붙은 이유는 빠르고 간편하게 어플리케이션을 작성할 수 있고, 생산성과 품질이 뛰어다나는 이유때문에 이러한 특징을 보이고 있다.

그럼 Spring Framework를 이루는 구성요소를 하나씩 알아보도록 하자.

## POJO

Plain Old Java Object라는 의미로 직역하자면 '평범한 구식 자바 객체'정도의 의미를 갖고 있다.

SpringFrameowrk는 POJO 기반 프레임워크이다.

과거 자바로 웹 어플리케이션을 설계하기 위해서는 Servlet 클래스를 상속받아 구현해야만 했다. 

그러나 Spring에서는 이 Servlet 클래스를 직접 작성하지 않고, 

POJO만으로 웹 어플리케이션을 구축할 수 있다는 것이 스프링의 특징이다.

POJO 기반의 SpringFramework는 특정 환경, 규약에 종속되지 않고 객체지향의 기본 원리에 충실한 어플리케이션 개발을 할 수 있게 되는 것 이다.

## IoC / DI

Inversion of Controll의 약자로 직역하면 제어의 역전이라는 의미이다.

말 그대로, 메소드나 객체의 호출을 개발자가 결정하는 것이 아닌 '외부에서 결정'된다.

IoC라는 의미는 대다수 프레임워크에 이를 적용한다.

이는 어플리케이션의 흐름을 개발자가 제어하는 것이 아닌, 프레임워크가 제어권을 가진다는 것을 의미한다.

개발자는 프레임워크에게 규약에 맞게 메소드를 만들면, 그 메소드를 호출하는 시점은 프레임워크가 결정하기 때문에 제어의 역전이라고 표현한다.

그런데 Spring에서는 여타 프레임워크와는 달리 **의존관계 주입**이라는 특이한 방식을 사용한다.

이를 DI(Dependency Injection)이라 하고 의존성 주입이라고 한다.

의존성 주입은 제어의 역행이 발생할 때 스프링 프레임워크가 특정 객체에 필요한 객체를 IoC Container가 주입해주는 방식을 사용한다.

즉, 이 DI로 인해 개발자는 직접 new를 이용해 객체를 생성하지 않으며,

해당 객체가 필요한 시점에 IoC Container가 해당 객체를 주입해준다.

이때 IoC Container가 수집할 대상의 객체를 Bean이라고 부른다.

그리고 IoC Container 또한 BeanFactory라는 최상위 인터페이스로 구현되어져 있다.

일반적으로는 이 BeanFactory를 확장한 Appliciation Context를 주로 사용한다. 

## AOP

<img width="477" alt="스크린샷 2020-01-05 오전 1 33 38" src="https://user-images.githubusercontent.com/43809168/71768655-6f0eed00-2f5b-11ea-9508-c893603f05ec.png">

Aspect Oriented Programming의 약자로 관점지향 프로그래밍라고 부른다.

관점 지향 프로그래밍이란 어떤 로직을 기준으로 **핵심적 관점**과 **부가적 관점**으로 나누어 각각 모듈화하겠다는 것이다.

**핵심적 관점**이란 비즈니스로직을 의미한다. 

**부과적 관점**이란 핵심 비즈니스로직을 실행하기 위해 행해지는 데이터베이스 연결,로깅, 파일 입출력 등을 예로 들 수 있다.

좀 더 구체적인 예시를 들어보자면,

특정 비즈니스 로직의 실행시간을 알고싶다고 하자.

그러면 비즈니스 로직의 앞,뒤에 실행시간을 계산하는 코드가 들어가게 된다.

그러나 이러한 실행시간을 계산하는 코드는 재사용될 여지가 많으며 매 비즈니스 로직마다 이러한 코드를 구현하는 것은 시간이 낭비된다.

때문에 실행시간 계산 로직을 모듈화 하여 사용할 수 있게하는 것이 AOP이다.

스프링의 AOP는 프록시 패턴을 기반으로 한다.

## 특징

Spring AOP의 특징은 다음과 같다.

```

Bean만 AOP가 적용 가능하다.

AOP를 사용하는 이유는 부가기능을 추가하기 위함이다.

JDK Dynamic Proxy와 CGlib를 사용한다.

JDK Dynamic Proxy와 CGlib의 차이는 무엇일까?

```

## JDK Dynamic Proxy

```java
public class Proxy implements Something
```

JDK Dynamic Proxy는 Java Reflection 패키지의 Proxy 클래스를 통해 생성된 객체를 의미한다.

JDK Dynamic Proxy는 인터페이스로 구현되어져야 하며 타겟클래스와 프록시 클래스 모두 인터페이스에 의해 구현되어야 한다.

## CGlib

```java
public class Proxy
```

CGlib의 경우 인터페이스가 필요하지 않다. 왜냐하면 프록시 클래스는 타겟 클래스의 자식이 되기 때문이다.

CGlib가 JDK Dynamic Proxy보다 성능이 더 좋다고 한다.

그래서 Spring boot에서도 trasaction 대상의 AOP는 기본 CGlib이다.

## 실제 사용

```java
@Component
@Aspect
public class PerfAspect {

  @Around("execution(* com.saelobi..*.EventService.*(..))")
  public Object logPerf(ProceedingJoinPoint pjp) throws Throwable{
    long begin = System.currentTimeMillis();
    Object retVal = pjp.proceed(); // 메서드 호출 자체를 감쌈
    System.out.println(System.currentTimeMillis() - begin);
    return retVal;
  }
}
```

로직의 수행성능을 측정하는 AOP 예제이다.

@Around는 타겟 메서드를 감싸서 특정 기능을 수행하기 위함이다.

execution(* com.saelobi..*.EventService.*(..))는

com.saelobi 아래 패키지 경로의 EventService 객체 내의 모든 메서드에 이 Aspect를 적용하겠다는 의미이다.

## PSA

PSA란 **Portable Service Abstraction**의 약자로 환경의 변화와 관계 없이 항상 일관된 방식의 기술로의 접근 환경을 제공하려는 추상화 구조를 의미한다.

POJO로 개발된 코드는 특정 환경이나 구현 방식에 종속적이지 않아야 한다.

이 PSA는 Spring의 핵심 철학을 가장 잘 보여주는 기술이다.

예를들어, 과거에는 HttpServlet을 상속받아서 doGet(),doPost()를 오버라이딩 하여 사용했었다. 

그런데 Spring Web MVC 의존성 추가를 하면 @Controller 어노테이션으로 훨씬 편하게 개발을 할 수 있게 되었다.

Spring Web MVC는 PSA의 좋은 예이다.

HttpServlet이 어떻게 구현되어있는지 몰라도 Spring Web MVC는 @Controller, @GetMapping 어노테이션을 이용해 개발을 쉽게 할 수 있게 해준다.

또한 특정 기술에 독립적일 수 있는데, Spring Web MVC는 기본적으로 내장 톰캣을 사용한다.

그런데 Spring 5부터 webflux라는 기술이 도입되었다.

Servlet 대신 Reactive로 컨트롤러를 작성할 수 있으며 webflux는 톰캣 대신 네티를 사용한다.

Web MVC의 추상화 계층 덕분에 기존 코드 수정 없이 네티로 변경이 가능하다.

즉 스프링은 특정 기술에 종속되어 개발하는 것을 싫어하기 때문에

우리는 스프링을 이용해서 편하게 웹어플리케이션 개발을 할 수 있는 것 이다!

## Reference
https://asfirstalways.tistory.com/334

https://engkimbs.tistory.com/746

https://stackoverflow.com/questions/10664182/what-is-the-difference-between-jdk-dynamic-proxy-and-cglib

https://jins-dev.tistory.com/entry/Spring-PSAPortable-Service-Abstraction%EC%9D%98-%EA%B0%9C%EB%85%90
