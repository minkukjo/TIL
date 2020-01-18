# ApplicationContext

오늘 테스트 코드 작성하는데 오류에 이 친구가 자꾸 등장하기에 궁금해서 찾아보았다.

한번 공부할 수 있는데 까지 깊게 탐구해보자.

Spring에서는 DI를 하기 위해 객체들을 **Bean**으로 만들고 이를 Container에서 관리한다.

여기서 말하는 Container란 IoC Container, 스프링 컨테이너라고도 불리운다.

Spring은 기본적으로 2가지 Container를 제공한다.

하나는 BeanFactory이고 다른 하나는 Applciation Context이다.

둘의 차이는 뒤에 알아보는걸로 하고, 우선 Application Context의 하는 역할에 대해서 알아보자.

Application Context는 스프링 설정파일(applicationContext.xml)에 등록된 `<bean>` 객체를 생성하고, 관리하는 기능을 담당한다.

그래서 Container가 객체를 싱글톤으로 갖고 있다가 의존성 사용자가 필요할 때 해당 Bean을 의존성 주입을 통해 객체에 주입시켜주고 사용하게 하는 것이다.

이러한 의존성 주입 기술을 통해 스프링은 모듈 간의 결합도를 낮추고 유연성을 높이는 결과를 얻었다.

의존성 주입 방법에는 일반적으로 @Autowried 어노테이션 주입 방법과 생성자 주입 방법을 사용한다.


# Application Context or BeanFactory?

Application Context는 BeanFactory Interface의 차이에 대해서 알아보자.

BeanFactory Interface는 최상위 IoC 컨테이너 인터페이스이며 Application Context는 BeanFactory를 상속받고 있다.

그래서 일반적인 상황이라면 ApplicationContext를 사용하지만 특정 상황에서는 BeanFactory를 사용한다.

특정 상황이라고 한다면, 메모리 소비가 중요한 리소스가 제한된 디바이스 환경에서 어플리케이션을 실행할 때는 Bean Factory가  더 적합하다고 한다.

그래서 일반적인 상황에서 Application Context는 빈팩토리보다 좀 더 많은 역할을 수행할 수 있기 때문에 주로 Application Context를 사용한다.

둘의 차이는 객체 생성 시점이 다른데, BeanFactory의 경우 컨테이너가 구동될 때 객체를 생성하는 것이 아닌, 클라이언트의 요청이 들어올 때만 객체를 생성한다.

이를 **lazy loading**이라고 부른다.

BeanFactory를 확장한 Application Context는 BeanFactory에서 제공하는 기본적인 컨테이너 기능들을 포함해서 트랜잭션 관리나 메세지 기반의 다국어 처리등 다양한 기능을 제공해준다.

또한 컨테이너가 구동되는 시점에 클래스들을 로딩한다.

# Application은 정말로 BeanFactory 상속받고 있을까?

궁금해서 직접 Application Context의 내부를 한번 들여다 보았다.

<img width="777" alt="스크린샷 2020-01-19 오전 5 21 24" src="https://user-images.githubusercontent.com/43809168/72670050-318e8180-3a7c-11ea-9a93-1a5dab6c22ca.png">

<img width="577" alt="스크린샷 2020-01-19 오전 5 21 28" src="https://user-images.githubusercontent.com/43809168/72670051-318e8180-3a7c-11ea-8f47-fdaaeccde342.png">

BeanFactory를 상속받고 있는 **ListableBeanFactory** 인터페이스와 **HierachicalBeanFactory**를 상속받고 있었다.

**ListableBeanFactory**는 빈팩토리를 구현한 인터페이스 이며, 사용자 요청이 들어왔을 때 객체를 하나씩 보는것이 아닌 모든 빈 인스턴스 전체를 열거할 수 있는 빈팩토리라고 한다.

그렇다는 것은, 기존 빈팩토리에서는 사용자 요청이 들어올 때 마다 객체를 하나씩 확인했겠다는 것을 알 수 있다.

결국 Application Context가 BeanFactory 보다 다양한 기능을 가지는건 당연한 이야기겠다.

**HierachicalBeanFactory** 역시 BeanFactory를 구현하고 있으며 계층의 하위가 되는 인터페이스라고 한다.
메소드가 1개만 정의된 것으로 봐서 계층화를 위해 존재한 인터페이스라고 봐도 무방할 것 같다.

<img width="756" alt="스크린샷 2016-02-16 00 56 55" src="https://user-images.githubusercontent.com/43809168/72670238-ab276f00-3a7e-11ea-915a-4c8a5bbdb6db.png">



# Reference

https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/beans/factory/BeanFactory.html

https://adunhansa.tistory.com/198

https://www.slipp.net/wiki/pages/viewpage.action?pageId=25527815