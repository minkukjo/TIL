# JUnit Framework

Spring 프로젝트에서 TDD를 편하게 하는 방법을 알아보다가,

JUnit의 존재를 알게 되었다.

물론 TDD를 하는데 있어 도구가 중요한 것은 아니지만,

TDD를 편하게 해주는 도구가 있다면 사용하는 것이 맞다고 생각한다.

JUnit 단위 테스트 프레임워크에 대해 알아보자.

JUnit은 Java에서 독립된 단위 테스트를 지원해주는 프레임워크이다.

JUnit을 사용하지 않으면 일일이 **System.out.println**으로 테스틀 해봐야 한다.

하지만 이는 시간도 많이 걸리고 정확한 테스트가 되지 않을 가능성이 높다.

그래서 단위 테스트를 이용하여 특정 로직을 단위별로 테스트할 수 있게 도와주는 JUnit을 사용하는 것이다.

지금부터 그 JUnit 어노테이션을 살펴보도록 하자.

## JUnit의 특징

assert 메서드로 테스트 케이스의 수행 결과 판별한다.

JUnit 4버전 부터는 테스트를 지원하는 어노테이션 사용 가능 (@Test,@Before,@After)

@Test 메서드가 호출할 때 마다 새로운 인스턴스를 생성하여 독립적인 테스트가 이루어진다.

## JUnit이 지원하는 어노테이션

### @Test

이 어노테이션이 선언된 메서드는 테스트를 수행하는 메소드가 된다.

JUnit은 각각의 테스트가 서로 영향을 주지 않고 독립적으로 실행됨을 원칙으로 한다.

때문에 @Test 어노테이션 마다 객체를 생성한다.

### @Ignore

이 어노테이션이 선언된 메서드는 테스트를 진행하지 않는다.

### @Before

이 어노테이션이 선언된 메서드는 @Test 메서드가 실행되기 전에 반드시 실행된다.

일반적으로 @Test에 공통적으로 사용하는 코드를 @Before에 넣어 사용한다.

### @After

@After가 선언된 메서드는 @Test 메소드가 종료 후 실행된다.

### @BeforeClass

이 어노테이션은 @Test 메소드 보다 먼저 **한번만** 수행되어야 할 때 사용한다.

### @AfterClass

이 어노테이션은 @Test 메소드 보다 나중에 **한번만** 수행되어야 할 때 사용한다.

## Reference

https://shlee0882.tistory.com/202