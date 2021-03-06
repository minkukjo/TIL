# HTTP란?

Hyper-Text Transfer Protocol over TCP/IP의 약자로 웹상의 네트워크 프로토콜을 의미한다.

# Server/Client 구조

<img width="403" alt="스크린샷 2020-01-09 오전 12 36 55" src="https://user-images.githubusercontent.com/43809168/71991748-33846380-3278-11ea-865d-9198e4dae3b1.png">

HTTP는 서버/클라이언트 구조이다.

자원을 갖고 있는 쪽을 서버, 자원을 요청하는 쪽을 클라이언트라고 부른다.

클라이언트는 서버에 자원을 요청(Request)하며, 서버는 그 요청에 대한 응답(Response)를 해주는 구조이다.



# HTTP 특징

1. Connectionless
- 연결을 유지하지 않는다. 데이터 송,수신이 끝나면 연결을 끊음
- 브라우저와 서버가 계속 연결되어 있지 않음.

2. Request-Response
- 한 컴퓨터가 요청을 보내면 다른 컴퓨터는 요청에 대한 응답을 보내는 방식을 의미한다.

3. Stateless
- 요청을 독립적인 트랜잭션으로 처리하는 것을 의미한다. 

- 즉, 요청이 들어왔을 때 서버는 통신 파트너에 대한 세션 정보나 상태를 보관하지 않는 것을 의미한다. 

- 이전 요청과 다음 요청간의 관계가 없기 때문에, 유저가 로그인을 한 후 페이지 이동을 하게 되면 유저는 다시 로그인을 해야한다. 이를 방지하기 위해 Stateful한 시스템을 개발해야 했기 때문에 쿠키와 세션이 등장하게 되었다.

# Stateless와 Connectionless의 문제점

HTTP 프로토콜은 **Connectionless**하며 **Stateless**한 특성을 갖는다.

이러한 특성 때문에 Server는 요청을 보낸 Client가 누구인지 식별할 수 없었다.

예를들어 쇼핑몰에 로그인을 하였는데 다음 페이지로 넘어갈때 마다 로그인을 새로 해야하는 현상이 발생하는 것이다. 

얼마나 짜증나겠는가?

때문에 이를 위해 쿠키와 세션이 등장하게 되었다.

이 쿠키와 세션을 이용하여 **Stateful**한 시스템을 개발할 수 있게 되는 것 이다.

# 쿠키 (Cookie)

쿠키는 클라이언트에 저장되는 키와 값이 들어있는 작은 데이터 파일이다.

이 쿠키는 사용자 정보를 컴퓨터에 파일로 남기기 때문에 누군가가 클라이언트의 컴퓨터에 접근하게 된다면 이 쿠키 정보를 열어볼 수 있는 **보안상 문제**가 발생할 수 있게 된다.

# 세션 (Session)

세션은 서버 메모리에 저장되는 연결 정보다.

서버에 저장되기 때문에 쿠키와 달리 노출 위험이 적다는 장점이 있다.

세션을 이용한 로그인 처리 과정은 다음과 같다.

1. 사용자가 로그인 페이지에서 ID/PW를 입력하고 로그인 버튼을 클릭

2. 서버에서 사용자가 보낸 ID/PW가 존재한다면 서버 메모리에 유니크한 세션 ID를 생성하고 ID와 매핑하여 저장

3. 클라이언트에서 세션 ID를 쿠키로 저장

4. 요청 시 서버는 Request Header에서 쿠키 정보(세션 ID)를 읽어 서버 메모리에 저장된 세션 ID와 매핑되는 사용자 ID를 인식

즉, 위와 같은 과정에서도 쿠키는 세션 ID를 저장하기 위해 사용된다.

## HTTP 1.1


HTTP 1.1 프로토콜은 클라이언트와 서버간 통신을 다음과 같이 수행한다.

![http1-1e8d6f2a-403x600](https://user-images.githubusercontent.com/43809168/73050815-63607780-3ec4-11ea-8618-43df4cd1da3a.png)

HTTP 1.1은 기본적으로 Connection 당 하나의 요청을 처리하도록 설계되어 있다.

그래서 순차적으로 html,css,js파일이 하나씩 전송되는 것을 볼 수 있다.

때문에 다수의 리소스를 처리하려면 그만큼 Latency가 길어지게 된다.

당연히 연결이 하나의 리소스를 보낼 때 마다 맺어지고 끊어지기 때문에 TCP의 3-way-handshake가 반복적으로 일어나게 되고, 이는 결국 RTT(Round Trip Time)의 증가로 이어지며 성능의 저하를 가져오게 되었다.

이러한 HTTP 1.1의 단점을 극복하기 위해 **HTTP 2**가 등장하게 되었다.

## HTTP 2

![스크린샷 2020-01-24 오전 12 44 49](https://user-images.githubusercontent.com/43809168/72999461-c01b4e00-3e42-11ea-83b7-ce4b21e2a761.png)

위 그림은 HTTP 1.1과 2의 차이를 가장 잘 보여주는 그림이다.

HTTP2에서는 하나의 연결에도 여러 리소스를 보낼 수 있게 되었다.

즉, HTTP 1.1의 고질적인 문제를 해결했다고 볼 수 있다.

그렇다면 HTTP 1.1에서 극복할 수 없었던

**하나의 커넥션으로 동시에 여러개 메세지를 주고받는 방식**이 어떻게 가능했던 걸까?

### MultiPlexed Streams 개선


![http2_streams](https://user-images.githubusercontent.com/43809168/73051304-c9013380-3ec5-11ea-947f-58425b0abdbb.png)

HTTP 2에서는 HTTP 1.1의 멀티플렉싱을 개선하였다.

HTTP2에서는 하나의 Connection에서 다수의 입출력이 가능하게끔 지원한다.

어떻게 이러한 기술이 가능할까?

그 이유는 HTTP/2는 패킷을 Frame 단위로 세분화할 수 있게 되었기 때문이다. 

이러한 패킷을 Frame 단위로 세분화하는 기술을 **Binary Protocol**이라고 부른다.

<img width="410" alt="다운로드" src="https://user-images.githubusercontent.com/43809168/73052155-c99ac980-3ec7-11ea-88c6-3c983227db9e.png">

HTTP 1.1에서는 요청의 내용을 한꺼번에 보내지만,

HTTP 2에서는 Header와 Data Frame으로 나누어 전송하는 기술을 사용한다.

HTTP 1.1에서 HTTP의 요청과 응답은 메세지라는 단위로 구성되어 있었다. 

메세지는 Head와 Body로 구성되어있다.

그런데 HTTP2에서는 Frame과 Stream이라는 개념이 추가되었다.

Frame은 HTTP2 통신에서 주고 받는 최소 단위이며 Header Frame과 Data Frame으로 나누어져있다.

HTTP2에서의 메세지는 다수의 Frame들로 구성되어 있다.

그리고 Stream은 클라이언트와 서버 사이에 맺어진 연결을 통해 양방향 데이터를 주고받는 한개 이상의 메세지를 의미한다.

**Frame이 모여 Message가 되고, Message가 모여 Stream이 된다**

Frame -> Message -> Stream 왼쪽에서 오른쪽으로 갈수록 크기가 커지는 구조이다.

<img width="410" alt="다운로드 (1)" src="https://user-images.githubusercontent.com/43809168/73052483-986ec900-3ec8-11ea-8a10-b706db20ff86.png">

HTTP 1때는 요청과 응답이 메세지라는 단위로 완벽히 구분되었다.

그러나 HTTP 2에 오면서 스트림이라는 단위로 요청과 응답이 묶일 수 있게 되었다.

스트림 하나가 다수의 메세지를 갖고 있기 때문에 순서에 관계없이 만들어진 순서대로 스트림을 주고받을 수 있게 되었다.

받는 쪽에서 조립하는 방식을 사용하기 때문에 HTTP 1.1에서 불가능했던 1 Connection Multi Plexing이 가능하게 된 것이다.

때문에 HTTP/2에서는 요청과 응답을 병렬로 전달할 수 있고 하나의 Connection에서 여러 응답/요청을 받을 수 있게 된 것이다.

## HTTP/3

HTTP/2 프로토콜은 성공적인 프로토콜이었고 여전히 많이 쓰이는 대중적인 웹 프로토콜이 되었다.

그러다 작년 HTTP/3가 등장하였다.

HTTP/3는 UDP 기반의 QUIC 프로토콜을 사용하여 통신하는 프로토콜이다.

HTTP/1에서 HTTP/2로 넘어가는데 15년이 걸린것에 비하면

4년만에 HTTP/3가 등장하는 것은 여러 사람들을 놀라게 만들었다. (이것이 과학기술의 발전...!)

그리고 여전히 전세계에서 HTTP/2가 점유율이 40%대라는 것만 봐도 여전히 HTTP/2가 나온지도 얼마 되지않았다는 것이다.

HTTP/3는 HTTP/2 보다 더 빠른 성능의 신뢰성있는 웹 프로토콜을 위해 등장하였다.

HTTP/2는 TCP 기반이기 때문에 기본적으로 Connection을 맺을 때 3-way-handshake를 수행하며 통신을 마칠 때는 4-way-handshake의 과정을 거친다.

또한 TCP 기반의 HTTP/1과 HTTP/2에는 HOLB(Header of Line Blocking)문제가 여전히 존재했었다.

TCP에서의 패킷은 반드시 순서대로 처리되어야 한다는 전제를 깔고 있다.

수신 측은 송신측과 주고 받은 시퀀스 번호를 참고하여 패킷을 재조립해야하기 때문이다.

때문에 중간에 패킷이 손실되면 완전한 데이터로 조합이 안되므로 절대로 그냥 넘어가지않는다.

송신측은 패킷을 제대로 받았다고 확인을 하고, 수신측이 패킷을 제대로 못받았다면 **재전송**하여 패킷을 다시 보내줘야한다.

또한 패킷이 처리되는 순서가 정해져있기 때문에 이전에 받은 패킷을 파싱하기 전까지는 다음 패킷을 처리할 수 없다.

이렇게 패킷이 중간에 유실되거나 패킷 파싱속도가 느려지게 되면 통신에 병목 현상이 발생하게 되고 이러한 현상을 HOLB라고 부른다.

이 HOLB는 TCP 기반의 HTTP/1과 HTTP/2에 나타나는 특징이었다.

결국 이러한 문제들을 해결하고자 ( 더 빠른 전송과 응답을 위해 ) HTTP/3는 UDP를 기반으로 만든 프로토콜인 QUIC을 사용하는 것이다.

QUIC은 TCP의 핸드쉐이크 과정을 최적화하는 것에 초점을 맞춘 구글에서 만든 UDP 기반 프로토콜이다.

그렇다면 UDP를 사용한다는 의미는 기존의 TCP가 가지던 신뢰성과 패킷의 무결성도 사라지게 되는 걸까 라는 의문이 생긴다.

하지만 그렇지 않다.

구글이 UDP를 기반으로 QUIC을 만든 것은 UDP가 TCP보다 **커스터 마이징**하기에 용이하기 떄문이다.

즉, 구글은 UDP를 기반으로 QUIC이라는 프로토콜을 만들었고, 이 프로토콜은 TCP가 기존에 제공하던 신뢰성과 패킷 무결성을 UDP를 커스터 마이징에 구현한 프로토콜인 것이다. (구글 짱짱)

### HTTP/3가 UDP를 사용하며 기존 프로토콜보다 나아진 점

#### 연결 설정 Latency 감소

<img width="432" alt="스크린샷 2020-01-24 오후 5 05 45" src="https://user-images.githubusercontent.com/43809168/73053693-c4d81480-3ecb-11ea-9a2b-6c62149a9a82.png">

앞서 QUIC은 3-way-handshake 과정을 개선하기 위해 등장했다고 하였다.

위 그림은 HTTPS에서의 TCP + TLS와 QUIC의 RTT를 보여준다.

RTT는 클라이언트가 서버로 요청을 보내고 서버가 요청을 처리 후 응답을 다시 클라이언트에게 보내주는 사이클을 의미한다.

TCP는 기본적으로 연결을 위해 1RTT(3-way-handshake 과정을 생각해보라)가 필요하며 여기에 TLS 암호화까지 하려면 TLS의 자체 핸드쉐이크까지 더해져 총 3RTT가 필요하다.

반면 QUIC은 1 RTT만에 이 모든 과정을 끝낸다.

QUIC이 이것이 가능한 이유는 TCP연결과 TLS에 필요한 데이터를 바로 보내버리기 때문이다.

또한 한번 연결설정을 해놓았다면 서버는 그 설정을 캐시에 저장해서 다음 연결때는 캐싱 해놓은 설정을 사용하면 되기 때문에 0RTT로 바로 통신을 시작하는 것 또한 가능하다.

물론 TLS 1.3버전이 나오면서 기존의 TCP에서도 QUIC과 비슷한 방법으로 연결을 설정함으로써 TCP를 사용하더라도 성능상 이점을 가질 수 있게 되었다. (Java 11에서 TLS 1.3이 적용되었다.)




## Reference

https://dalkomit.tistory.com/134

https://ourcstory.tistory.com/71

https://www.popit.kr/%EB%82%98%EB%A7%8C-%EB%AA%A8%EB%A5%B4%EA%B3%A0-%EC%9E%88%EB%8D%98-http2/

https://jins-dev.tistory.com/entry/HTTP2-%ED%8A%B9%EC%A7%95%EB%93%A4%EC%97%90-%EB%8C%80%ED%95%9C-%EC%A0%95%EB%A6%AC

https://americanopeople.tistory.com/115