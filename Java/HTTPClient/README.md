# Java HTTPClient

Apache HttpClient라고 불리우는 이 라이브러리는 자바 내장 HTTP 라이브러리이다.

나는 인턴때 멍청하게도 이 라이브러리를 사용했는데, 사실 이걸 사용하면 굉장히 번거롭고 까다롭다.

그러니 가능하면 스프링 내장 **Rest Template** 라이브러리를 사용하도록 하자

~~머리가 나쁘면 손발이 고생한다~~

```java
public class Httpclient1 {
    
    private static final String USER_AGENT = "Mozila/5.0";
    private static final String GET_URL = "http://www.google.com";    
 
    public static void sendGet() throws ClientProtocolException, IOException {
        
        //http client 생성
        CloseableHttpClient httpClient = HttpClients.createDefault();
    }
}
```

1. http client를 생성을 한다.

```java
public class Httpclient1 {
    
    private static final String USER_AGENT = "Mozila/5.0";
    private static final String GET_URL = "http://www.google.com";    
 
    public static void sendGet() throws ClientProtocolException, IOException {
        
        //http client 생성
        CloseableHttpClient httpClient = HttpClients.createDefault();
 
        //get 메서드와 URL 설정
        HttpGet httpGet = new HttpGet(GET_URL);
 
        //agent 정보 설정
        httpGet.addHeader("User-Agent", USER_AGENT);
    }
}
```

2. HttpGet/HttpPost 인스턴스를 생성한다.

3. addHeader를 통해 헤더 정보를 추가해준다.

```java
public class Httpclient1 {
    
    private static final String USER_AGENT = "Mozila/5.0";
    private static final String GET_URL = "http://www.google.com";    
 
    public static void sendGet() throws ClientProtocolException, IOException {
        
        //http client 생성
        CloseableHttpClient httpClient = HttpClients.createDefault();
 
        //get 메서드와 URL 설정
        HttpGet httpGet = new HttpGet(GET_URL);
 
        //agent 정보 설정
        httpGet.addHeader("User-Agent", USER_AGENT);
        
        //get 요청
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        
        System.out.println("::GET Response Status::");
        
        //response의 status 코드 출력
        System.out.println(httpResponse.getStatusLine().getStatusCode());
 
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));
 
        String inputLine;
        StringBuffer response = new StringBuffer();
 
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        
        reader.close();
 
        //Print result
        System.out.println(response.toString());
        httpClient.close();
    }
}
```

excute 메소드를 사용해 request를 보낸다.

## RestTemplate

그러나 위의 HttpClient는 상당히 번거롭다.

그래서 Spring 3버전 부터 RestTemplate이라는 라이브러리를 지원하는데, 반복되는 코드를 최대한으로 줄여주는 라이브러리이다.

```java
String result = restTemplate.getForObject("http://example.com/hotels/{hotel}/bookings/{booking}", String.class, "42", "21");
```

단 한줄이면 Http request를 보낼 수 있다.

## 결론

Spring으로 REST API 통신할 때는 RestTemplate 쓰자

오늘의 교훈 : 네이버 인턴때 머리가 나빠서 손발이 고생했다.


## Reference

https://eddyplusit.tistory.com/51

https://vnthf.github.io/blog/Java-RestTemplate%EC%97%90-%EA%B4%80%ED%95%98%EC%97%AC/