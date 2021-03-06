# HTTPS

HTTP의 보안을 강화하기위한 프로토콜로써 S는 Secure라는 의므를 갖는다.

HTTPS는 SSL 프로토콜을 기반으로 만들어진 프로토콜이다.

SSL과 TLS는 일반적으로 혼용해서 말한다.

## SSL?

SSL 인증서는 클라이언트와 서버간의 통신을 제 3자가 보장해주는 문서이다.

클라이언트가 서버에 접속한 후 서버는 클라이언트에게 이 문서를 제공한다.

그 후 클라이언트는 이 인증서를 보고 신뢰할 수 있는 사이트인지 확인 후 데이터를 보내는 다음 절차를 수행하게 된다.

### 장점

- HTTP에서는 패킷을 제 3자가 가로채서 볼 수 있었지만 HTTPS에서는 불가능

- 클라이언트가 접속하려는 서버가 신뢰할 수 있는 서버인지 알 수 있다.

- 전달되는 패킷의 내용이 제 3자에 의해 변질되는 것을 막을 수 있다.

## SSL의 암호화 종류

### 대칭키

암호화를 할때 사용하는 일종의 비밀번호를 키(Key)라고 한다.

이 키에 따라서 암호화된 결과가 달라지기 때문에 키를 모른다면 암호를 푸는 행위인 복호화도 불가능하다.

이 중 대칭키 방식은 동일한 키로 암호화와 복호화를 할 수 있는 기법을 말한다.

대칭키의 단점은 발송자와 수신자가 서로 대화하려면 둘 다 대칭키를 갖고 있어야 한다.

암호를 주고 받을 때 이 키로 암호화하라고 사용자에게 전달하는 것이 어렵다.

왜냐하면 중간에 대칭키가 유출되면 키를 공격한 공격자는 암호의 내용을 복호화하여 무슨 데이터를 전달하려고 하는지 알 수 있기 때문에 HTTPS를 사용하는 이유가 사라진다.

그래서 나온 방법이 공개키 방식이다.

### 공개키

공개키 방식은 대칭키와 다르게 두 개의 키를 가진다.

하나를 비밀키라고 부르며 하나를 공개키라고 부른다.

비밀키는 자신만 알고 있으며 공개키는 다른 사람에게 제공한다.

공개키로 암호화하면 비밀키로 복호화를 할 수 있고

비밀키로 암호화하면 공개키로 복호화할 수 있다.

공개키는 공개되어 있으며 보통 인증서안에 포함되어 있다.

서버가 발행한 공개키를 받은 클라이언트는 공개키를 이용하여 암호화를 하고 암호화된 정보를 비밀키를 갖고 있는 서버에게 전달하면 서버는 그 정보를 복호화하여 확인할 수 있다.

이러한 방식을 우리는 전자서명이라고 부른다.

## SSL 통신 과정

HTTP(TCP 기반)에서는 일반적으로 핸드쉐이크->세션->세션 종료의 과정을 거친다.

암호화된 HTTP 메시지를 교환하기 전에 클라이언트와 서버는 SSL 핸드쉐이크를 진행한다.

1단계

Client Hello : 클라이언트가 브라우저 or 다른 TCP 통신을 통해 서버에 접속한다.

이때 클라이언트는 랜덤한 데이터를 생성하여 서버에게 전달한다.

또한 클라이언트가 SSL 통신을 하기 위해 현재 지원 가능한 암호화 방식을 서버에게 전달한다.

2단계

Server Hello : Client Hello에 대한 응답으로 서버에서 Server Hello를 한다.

이번엔 서버에서 생성한 랜덤한 데이터를 클라이언트에게 전송한다.

또한 클라이언트가 지원가능한 암호화 방식에 맞춰 서버에서 제공할 수 있는 가장 안전한 암호화 수단 방식을 클라이언트로 전달한다.

그리고 서버가 클라이언트에게 인증서를 전달한다.

3단계

클라이언트는 서버가 보내준 인증서가 어떤 CA가 발급한 것인지 확인하기 위해 클라이언트에 내장된 CA리스트를 확인한다.

인증서가 어떤 CA에 의해서 발급된 것인지 확인하기 위해 클라이언트에 내장된 CA의 공개키를 이용하여 복호화를 한다.

CA리스트에 없는 인증서라면 사용자에게 경고 메세지를 보낸다.

복호화를 정상적으로 성공했다면 해당 인증서는 CA의 비밀키로 암호화한 문서임이 보증되었고 이를 통해 서버를 신뢰할 수 있게 된다.

4단계

클라이언트는 2단계에서 받은 랜덤 데이터와 클라이언트가 생성한 랜덤 데이터를 조합하여 Pre Master Secret이라는 키를 생성한다.

이 키는 세션 단계에서 데이터를 주고 받을 때 사용한다.

이 Pre Master Secret 키는 제 3자에게 노출되서는 안된다.

3단계에서 받은 인증서 안에 있는 공개키를 이용하여 Pre Master Secret 키를 암호화하여 다시 서버로 전송한다.

서버는 클라이언트가 전송한 Pre Master Secret 값을 자신의 비밀키로 복호화한다.

이로써 서버와 클라이언트는 모두 Pre Master Secret 값을 공유하게 되었다.

서버와 클라이언트는 일련의 과정을 거쳐서 Pre Master Secret 값을 Master Secret 값으로 만든다.

Master Secret은 Session Key를 생성하는데 이 Session Key를 이용하여 대칭키 방식으로 암호화하여 주고 받는다.

## Reference

https://goodgid.github.io/TLS-SSL/