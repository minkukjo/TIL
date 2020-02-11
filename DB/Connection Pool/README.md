# Connection Pool이란?

![스크린샷 2020-01-14 오전 12 51 47](https://user-images.githubusercontent.com/43809168/72270193-209ed400-3668-11ea-8d23-29b56d8cb5d4.png)

WAS에서 DB 서버에 접근하여 데이터를 가져오기 까지의 단계를 미리 만들어놓고 제공하는 기술을 **Connection Pool**이라고 한다.

WAS가 DB 서버에 접근하기 위해서는 다음과 같은 과정을 거친다.

1. DB 서버 접속을 위해 JDBC 드라이버를 로드한다.
2. DB 접속 정보와 DriverManager.getConnection() 메소드를 통해 DB Connection 객체를 얻는다.
3. Connection 객체로부터 쿼리를 수행하기 위한 PreparedStatement 객체를 받는다.
4. executeQuery를 수행하여 그 결과로 ResultSet 객체를 받아 데이터를 처리한다.
5. 처리가 완료되면 처리에 사용된 리소스를 close하여 반환한다.

위 예시에서 DB로부터 원하는 데이터를 얻기까지 가장 시간이 오래 걸리는 부분은 웹 서버에서 물리적으로 DB 서버에 최초로 연결되어 Connection 객체를 생성하는 부분이다.

웹 어플리케이션은 HTTP 요청에 따라 Thread를 생성하고 대부분의 비즈니스 로직은 DB 서버로부터 데이터를 얻게 된다.

이러한 과정을 거치지 않기 위한 기술이 바로 **DBCP**(Database Connection Pool)이다.

Connection Pool의 구현체는 다음과 같은 역할을 한다.

1. WAS가 실행되면서 미리 일정량의 DB Connection 객체를 생성하고 Pool 이라는 공간에 저장한다.
2. HTTP 요청에 따라 필요할 때 Pool에서 Connection 객체를 가져다 쓰고 반환한다.
3. 이와 같은 방식으로 HTTP 요청 마다 Connection 객체를 넘겨주기 때문에 객체 생성 비용이 줄어들게 된다.


## Reference

https://brownbears.tistory.com/289

https://www.holaxprogramming.com/2013/01/10/devops-how-to-manage-dbcp/