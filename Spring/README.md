# Spring Framework

본 문서는 Spring Framework를 학습하기 위한 문서 입니다.

## Spring이란 무엇인가?

![thisisspring](https://user-images.githubusercontent.com/43809168/71762594-75c64180-2f14-11ea-8ee2-068d9bf12ff2.png)

위는 Spring의 구성요소에 대한 그림이다.

필자가 가장 좋아하는 그림이자 Spring이 무엇인지 쉽게 이해할 수 있는 그림이기도 하여 가져와 보았다.

POJO 객체를 중심으로 개발하는 것이 Spring의 주요한 특징이다.

Spring 프레임워크는 자바 엔터프라이즈 개발을 편하게 하기 위해**경량급 오픈소스 웹어플리케이션 프레임워크**이다.

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

일반적으로는 이 BeanFactory를 확장한 Applciation Context를 주로 사용한다. 




## Reference
https://asfirstalways.tistory.com/334