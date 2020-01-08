# Rest API란?

웹의 장점을 최대한 활용할 수 있는 아키텍쳐 스타일이며 이를 잘 지킨 아키텍쳐를 **Restful**하다 라고 일반적으로 이야기 한다.

웹에 존재하는 모든 자원(이미지,동영상,DB 자원 등)에 고유한 URI를 부여하여 사용하는 것을 말한다.

때문에 **Restful**하다는 것은 REST의 특징을 지키면서 API를 제공하는 것을 의미한다.

# REST의 구성요소

REST의 구성은 다음과 같다.

- 자원 - URI
- 행위 - Method
- 표현

예를 들어, 특정 게시판에 여러 글들이 있다고 하자.

각각의 게시글은 고유한 ID값을 갖고 있다고 한다면,

```
/board/1
/board/2
/board/3
...
```
위와 같은 URI가 나타날 것 이다.

즉 자원을 URI로 표현하는 것이다.

그 후 해당 자원의 행위를 Method로 명시하는데,

이때 일반적인 HTTP Method(생성,조회,업데이트,삭제)를 이용하여 행위를 명시한다.

즉 고유 URI값에 행위를 표시하는 이 약속이 잘 지켜졌을때 그 아키텍쳐는 **RESTful** 하다고 표현한다.

# REST의 특징

REST는 5가지 특징을 갖는다.

1. Uniform(유니폼 인터페이스)

HTTP 표준만 따른다면 안드로이드/IOS 등 어떤 플랫폼을 가리지 않고 특정 언어나 기술에 종속되지 않고 사용이 가능하다. URI로 지정한 리소스에 대한 조작이 가능한 아키텍쳐 스타일을 의미한다.

2. Stateless (무상태성)

이는 HTTP의 특성이기도 하다. REST는 HTTP 프로토콜 인프라를 사용하기 때문에 당연한 이야기이다.
API 서버는 들어오는 요청만 처리하면 되기 때문에 세션과 같은 컨텍스트 정보를 신경쓸 필요가 없기 때문에 구현이 단순해진다는 장점이 있다.

3. Cacheable (캐시 처리 가능)

웹 표준 HTTP를 그대로 사용하므로 웹에서 사용하는 기존 인프라를 그대로 사용할 수 있다. 

즉 HTTP가 가진 강력한 기능 중 하나인 캐싱 기능을 사용할 수 있다.

웹 캐시란 자주 쓰이는 문서를 자동으로 보관하는 HTTP 장치이다. 웹 요청이 캐시에 도착했을 때, 캐시된 로컬 사본이 존재한다면 그 문서는 서버에 요청을 보내서 받아오는 것이 아닌 캐시로부터 받아오게 된다.

때문에 불필요한 데이터 전송을 줄여 **네트워크 비용이 줄어들게 되고**, 대역폭을 늘리지 않고도 **네트워크 병목 현상을 줄여주며**, 원 서버에 대한 요청을 줄여주기 때문에 **서버 부하를 줄일 수 있다.**

캐싱의 구현은 HTTP 표준 프로토콜에서 사용하는 아래의 두 기술을 사용하여 구현 가능하다.

**E-Tag** : 메시지에 담겨있는 엔터티를 위한 엔터티 태그를 제공한다. 이를 활용하여 리소스를 식별할 수 있다. 

**Last-Modified** : 엔터티가 마지막으로 변경된 시각에 대한 정보를 제공한다. (파일인 경우 파일 시스템이 제공해 준 최근 변경 시각, 동적으로 생성된 리소스라면 응답이 만들어진 시간)

4.  Self-descriptiveness (자체 표현 구조)

동사(Method) + 명사(URI)로 구성되어 있어 어떤 자원에 무슨 행위를 하는지 알 수 있으며 메시지 포멧 역시 JSON을 이용하여 직관적으로 이해할 수 있다는 특징이 있다. 

5. Client - Server 구조

자원을 가진 쪽이 - Server, 자원을 요청하는 쪽이 Client가 되는 구조를 갖고 있다.

Rest Server는 API를 제공하며 비즈니스 로직 처리와 저장을 담당한다.

Client는 사용자 인증이나 context(로그인정보, 세션) 등을 직접 관리하고 책임진다.

이러한 구조는 서로간의 의존성을 낮추는 결과를 낳는다.

## 번외 URI와 URL의 차이

URL은 Uniform Resource Locator

URI는 Uniform Resource Identifier

URI가 URL보다 상위 개념이며 URL은 해당 자원의 위치를 나타내고, URI는 해당 자원의 식별자를 나타낸다.

아래는 좋은 예시가 있어 가져와 보았다.

https://www.google.co.kr/search?q=uri 아래와같은 주소가 있다고하자. 

https프로토콜을 가지고있고 호스트이름을 가지고있다. 

하지만 그 뒤에 /search?q=uri와 같은 문자열이 붙은걸 알 수 있는데 이 아이는 query string인 q의 값에 따라 여러가지 결과값을 가져올 수 있다. 

위 주소에서 URL은 https://www.google.co.kr/search까지이고, 내가 원하는 정보를 얻기위해서는 q=uri라는 식별자가 필요하므로,

https://www.google.co.kr/search?q=uri 이 주소는 URI이지만 URL은 아니다.

출처 : 영이블로그




## Reference

https://m.blog.naver.com/PostView.nhn?blogId=jhc9639&logNo=221005860507&proxyReferer=https%3A%2F%2Fwww.google.com%2F

https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html

https://brainbackdoor.tistory.com/53

https://lambdaexp.tistory.com/39 (URI와 URL의 차이)

http://sunychoi.github.io/java/2015/04/27/uri-url.html (URI,URL 예시)