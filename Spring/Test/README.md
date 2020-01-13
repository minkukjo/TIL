# Spring Boot Test

<img width="1263" alt="스크린샷 2020-01-14 오전 3 56 04" src="https://user-images.githubusercontent.com/43809168/72283525-4d5fe500-3682-11ea-9626-4b147798e39b.png">


Spring Boot에서 위와 같은 오류를 만나고 2시간 삽질

```java
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
```

근데 이거 달자 해결

허탈하기도 하고 억울하기도 하고, 제대로 Annotation의 기능을 모르고 쓰는 것 같다고 느낌

아는 만큼 보인다고 Test Annotation을 공부하기 위한 저장소

## @DataJpaTest

**spring-boot-test-starter**에 포함된 테스트 모듈 중에서도 JPA를 테스트할 때 사용하는 어노테이션

기본적으로 in-memory embedded database를 생성하고 @Entity 클래스를 스캔한다.

다른 일반적인 컴포넌트들은 스캔하지 않는다.

DataJpaTest는 기본적으로 테스트가 완료되면 자동 롤백을 하기 때문에 **@Transactional** 어노테이션이 필요없다고 한다.

**@Transactional 부가 설명**

**@Transactional** 어노테이션은 일반적인 DB에서의 ACID 원칙을 적용하는 작업의 단위라고 할 수 있겠다.

스프링에서는 비즈니스 로직에 트랜잭션을 보장해줄 수 있는 **@Transactional** 어노테이션이 존재한다.

즉, 이 어노테이션을 달면 해당 비즈니스 로직에 트랜잭션 처리가 가능하다는 이야기다. 

물론 여기서는 **@DataJpaTest**가 롤백 처리를 해주므로 필요 없다.

## @RunWith

**@RunWith**는 JUnit 프레임워크의 테스트 실행 방법을 확장할 때 사용하는 어노테이션이다.

이때 **@RunWith**는 **SpringRunner::class**와 함께 사용하는데 이 클래스가 기본 JUnit 프레임 워크 테스트 실행 방법을 결정한다.

**SpringRunner::class**는 SpringTestContextFramework와 Junit 테스트 라이브러리를 결합한 SpringJUnit4ClassRunner의 별칭이다.

## @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

문제의 '그' 녀석이다.

이 어노테이션은 DataJpaTest 어노테이션을 달면 생성되는 in-memory embedded database를 사용하지 않고 real database를 사용하고자 할 경우 설정할 수 있는 어노테이션이다.


## Reference

http://zetcode.com/spring/springrunner/
https://hyper-cube.io/2017/08/10/spring-boot-test-2/