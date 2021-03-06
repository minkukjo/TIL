# Dispatcher Servlet

![스크린샷 2020-02-08 오후 10 38 10](https://user-images.githubusercontent.com/43809168/74086173-b856dd00-4ac3-11ea-8664-a3024901c67e.png)

### 한줄 요약
```Sevlet Container에서 HTTP 프로토콜로 들어오는 모든 요청을 가장 최전방에서 처리하는 Controller```

Spring MVC는 Dispatcher Servlet를 통해 사용자의 요청을 처리한다.

그렇다면 Dispatcher Servlet은 정확히 어떻게 동작하는지 알아보자.

### Step 1

Dispatcher Servlet은 MVC 모델에서 사용자의 요청을 Dispatcher Servlet이 가로채게 된다.

### Step 2

가로챈 정보를 HandlerMapping에게 보내 요청을 처리할 수 있는 Controller를 찾아낸다. 

### Step 3

HandlerMapping이 요청을 처리할 Controller를 찾아낸다면 요청을 Controller에게 넘겨준다.

### Step 4

Controller는 해당 요청을 처리한 후 요청을 응답받을 View의 이름을 Dispatcher Servlet에게 반환한다.

### Step 5

Dispatcher Servlet은 Controller가 보내온 View의 이름을 ViewResolver에게 넘긴다.

ViewResolver는 이름에 해당하는 View가 있는지 찾는다.

### Step 6

View가 존재한다면 처리결과를 View에 보낸다.

### Step 7

View는 그 결과를 다시 Dispatcher Servlet에 전달한다.

### Step 8

Dispatcher Servlet은 최종 결과를 클라이언트에게 넘겨준다.




## Reference

https://doohong.github.io/2019/03/16/Spring-dispatcherServlet/

http://egloos.zum.com/springmvc/v/504151