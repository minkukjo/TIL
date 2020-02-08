# HttpMessageConverter

HttpMessageConverter란 사용자의 요청 본문에 담긴 내용을 Controller에서 사용하기 위한 Argument로 변환하거나, 서버에서 생성한 응답 결과를 응답 본문에 작성할 때 사용된다.

```java
@GetMapping("/")
@ResponseBody
public Person message(@RequestBody Person person){
  return person;
}
```

위의 @ResponseBody, @RequestBody를 사용할 때 HttpMessageConverter가 사용된다.

```java
@Controller
@RestController
public class MainController{
  @GetMapping("/")
  //@ResponseBody
  public Person message(@RequestBody Person person){
    return person;
  }
}
```

RestController 어노테이션을 붙이면 모든 메소드에 @ResponseBody를 부여한 것과 같다.

HttpMessageConverter는 이렇듯 우리가 HTTP 요청 본문을 객체로 변경하거나, 객체를 HTTP 응답 본문으로 변경할 때 사용된다.

HttpMessageConverter의 종류는 여러가지이며 어떤 요청을 받았는지, 어떤 응답을 보내는지에 따라 각기 다른 HttpMessageConverter를 사용하게 된다.

가령 객체를 리턴할때는 기본적으로 JsonMessageConverter가 사용되며 String을 반환할 때는 StringMessageConverter가 사용된다.

## Reference

https://galid1.tistory.com/558

https://ict-nroo.tistory.com/98