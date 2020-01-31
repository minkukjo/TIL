# OAuth 2.0

OAuth는 외부 서비스의 인증 및 권한부여를 관리하는 범용 프로토콜이다.

일반적으로 SNS 로그인 기능을 사용할 때 이 OAtuh 2.0을 쓴다.

조심해야할 점은 기존의 인증 절차인 세션/쿠키, 토큰 기반 인증방식을 대체하는 것을 의미하지 않는다는 점이다.

OAuth 2.0이 나왔다는 얘기는 1.0도 있었다는 얘기다.

2007년에 처음으로 1.0버전이 등장하였으며 2012년도에 2.0 버전이 등장하였다.

크게 바뀐점은 세가지다.

1. 모바일 어플리케이션에서도 사용 가능

2. HTTPS를 사용하기 때문에 보안이 강화

3. Access Token의 만료 기간이 생김

OAuth 2.0의 인증방식은 여러가지가 있지만 가장 많이 쓰이는 **Authorization Code Grant** 방법에 대해 알아보자

## 동작 순서

![스크린샷 2020-02-01 오전 4 45 56](https://user-images.githubusercontent.com/43809168/73569458-d7df7b80-44ad-11ea-9d66-ec3b01e8eff5.png)

Resource Ownwer : 사용자

Client : 어플리케이션 서버

Authorization Server : 권한을 관리하는 서버. Access Token, Refresh Token을 발급하는 주체

Resource Server : OAuth 2.0을 관리하는 서버, OAuth 2.0 관리 서버 자체 API를 의미한다.

![9945F13F5B6EECC02A](https://user-images.githubusercontent.com/43809168/73569727-54725a00-44ae-11ea-86b4-5e2f7c94e953.png)

작동 순서는 다음과 같다.

1. 사용자가 서버에 인증 요청을 보낸다.

2. 서버는 Authorization Request를 통해 사용자에게 인증할 수단(Facebook, Goolge, Kakao 로그인 url)을 보낸다.

3. 사용자는 해당 Request를 통해 인증을 진행하고 인증을 완료했다는 신호로 Authorization Grant를 url에 실어 서버에게 보낸다.

4. 서버는 해당 권한 증서를 Authorization Server로 보낸다.

5. Authorization Server는 권한 증서 확인 후 유저가 맞으면 서버에게 Access Token과 Refresh Token 그리고 유저의 프로필 정보(id) 등을 발급해준다.

6. 서버는 해당 Access Token을 DB에 저장하거나 사용자에게 넘긴다.

7. 사용자가 Resource Server에 자원이 필요하다면, 서버는 Access Token을 담아 Resource Server에 보낸다.

8. Resource Server는 Access Token이 유효한지 확인 후 서버에게 자원을 보내준다.

9. 만약 Access Token이 만료되거나 위조되었다면 서버는 Authorization Server에 Refresh Token을 보내 Access Token을 재발급 받는다.

10. 그 후 다시 Resource Server에 요청한다.

11. 만일 Refresh Token도 만료되었다면 사용자는 새로운 Authorization Grant를 서버에 넘겨야한다. 이는 다시 말하면 사용자가 다시 로그인을 하라는 의미이다.


Authorization Code Grant 방식에 대해서 알아보았다.

이론적인 내용이다 보니 처음 봤을때는 한눈에 내용이 들어오지 않아서 찬찬히 살펴봐야 한다.

그래서 실제 SNS 로그인은 어떤 방식으로 작동하는지도 알아보면서 OAuth 2.0을 이해해보도록 해보자

## SNS 로그인

![스크린샷 2020-02-01 오전 4 59 14](https://user-images.githubusercontent.com/43809168/73570372-9f40a180-44af-11ea-80ec-d20168311c57.png)

SNS 로그인은 OAuth 2.0 + 서버인증(세션/쿠키, 토큰 기반)으로 구성된다.

위의 예시는 페이스북 로그인을 예로 들었다.

1. 사용자가 서버에게 로그인을 요청한다.

2. 사용자는 페이스북 로그인 URL을 사용자에게 넘겨준다.

3. 사용자가 로그인 완료 후 Authorization Code Grant를 서버에 전달한다.

4. 서버는 받은 Authorization Code Grant를 Facebook의 Autorization Server로 권한 증서 확인을 요청한다.

5. 페이스북 Authorization Server가 권한 증서를 확인 후 해당 유저의 정보가 맞다면 Access Token과 Refresh Token 그리고 유저의 정보(고유 id)값을 돌려준다.

6. 받은 고유 id를 키값으로 해서 DB에 유저가 있다면 로그인, 없다면 회원가입을 진행시킨다.

7. 로그인이 완료되면 세션/쿠키 or 토큰 기반 인증 방식을 사용해 사용자의 인증을 처리한다.

## Reference

https://tansfil.tistory.com/60?category=255594