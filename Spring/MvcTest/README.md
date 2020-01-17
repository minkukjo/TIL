# Mvc Test

@WebMvcTest는 Spring MVC 컴포넌트에만 초점을 둔 Spring MVC 테스트 어노테이션이다.

기본적으로 Spring Security를 자동으로 구성하며 MVC 단위 테스트를 하려는 경우 주로 사용한다.

# MockMvc

MockMvc는 웹 어플리케이션을 어플리케이션 서버에 배포하지 않고, 스프링 MVC 기능을 재현할 수 있는 클래스다.

MockMvc의 작동 순서는 다음과 같다.

**Given**

1. 테스트 케이스의 메소드는 DispathcherServlet에 요청할 데이터를 설정한다.

**When**

2. MockMvc는 DispathcherServlet에 요청을 보낸다. 참고로 DispathcherServlet는 테스트용으로 확장된 TestDispathcherServlet이다.

3. DispathcherServlet은 요청을 받아 매핑 정보를 보고 그에 맞는 핸들러(컨트롤러) 메소드를 요청한다.

**Then**

4. 테스트 케이스 메소드는 MockMvc가 반환하는 실행결과를 받아 맞는기 점증한다.

컨트롤러를 호출할 때 필요한 요청 데이터를 설정한 후 MockMvc에 요청을 의뢰해보자.

## 실전

### 컨트롤러 핸들러 메소드
```java
@RequestMapping(value = "/", method = RequestMethod.GET)
public String home() {
    return "index";
}
```

### 테스트 메소드
```java
@Test
public void testHome() throws Exception {
    mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("/WEB-INF/index.jsp"));
}
```

perform()의 수행 동작은 다음과 같다.

DispathcherServlet에 요청을 의뢰한다. ("/")에 해당하는 핸들러를 찾음.

MockMvcRequestBuilders를 사용해 설정한 요청 데이터를 perform()의 인수로 전달
perform()에서 반환된 ResultActios() 호출
실행 결과 검증

## 실제 테스트 환경에서?

@MockMvc는 사용시 설정들을 자동으로 올리지 않기 때문에

@Repository나 @Service, @Component 등을 사용할 수 없다.

그래서 만약 실제 환경 MVC 테스트를 진행하고 싶다면

@AutoConfigureMockMvc를 사용해야한다.

@AutoConfigureMockMvc는 @SpringBootTest와 같이 사용해야 한다.



# Reference

https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/web/servlet/WebMvcTest.html

https://itmore.tistory.com/entry/MockMvc-%EC%83%81%EC%84%B8%EC%84%A4%EB%AA%85