# GSON VS Jackson

GSON과 Jackson은 자바 진영에서 JSON 파싱 및 변환을 위해 가장 많이 사용하는 라이브러이이다.

GSON의 경우 Google에서 개발하였으며 Jackson의 경우 FasterXML에서 개발하였다.

두 라이브러리의 가장 큰 차이는 "용도"이다.

일반적으로 Android에서는 GSON을 많이 사용하며 Spring에서 Jackson을 주로 사용한다.

그 이유는 둘의 장점과 단점이 뚜렷하기 때문이다.

**Jackson**의 경우 대용량 파일 파싱에 가장 빠른 성능을 보인다.

 **GSON**의 경우 작은 파일을 파싱할때의 속도가 가장 빠르다.

 아래의 벤치마크를 보자

 ![6106059371](https://user-images.githubusercontent.com/43809168/74086848-4c2ba780-4aca-11ea-9339-7a856d687408.jpg)

위의 벤치마크는 190MB의 JSON 파일을 파싱하는 속도를 측정했다.

총 10번을 수행하였음을 알 수 있다.

여기서 JACKSON과 GSON 외에도 Oracle 사에서 만든 JSONP와 Yidong Fang이 만든 JSON.simple도 같이 비교를 하였다.

Jackson이 대용량 파싱에 가장 좋은 성능을 보이는 것을 알 수 있다.

그렇다면 이번엔 작은 파일의 파싱속도를 보도록 하자.

1kb 크기의 JSON 데이터를 10000번씩 수행하였다.

![9759700626](https://user-images.githubusercontent.com/43809168/74086905-cceaa380-4aca-11ea-971c-034dd54633b9.jpg)

여기서는 Jackson이 가장 성능이 떨어지며 GSON의 성능이 가장 뛰어난 것을 볼 수 있다.


## 결론

 때문에 일반적으로 빅데이터 처리와 같이 큰 사이즈의 JSON을 처리해야하는 경우라면 Jackson을 선택하는게 맞다.

Spring에서 Jackson을 사용하는 이유이기도 하다.

용량이 작은 많은 Json을 처리하는 경우라면 GSON이 더 나은 선택이다.

이처럼 라이브러리 하나를 쓰더라도 왜 쓰는지, 다른 제품과 비교했을 때 어떻게 성능이 다른지를 비교 분석하여 사용하는 개발자가 되자

## Reference

http://www.yunsobi.com/blog/entry/java-json-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EB%B3%84-parser-%EC%86%8D%EB%8F%84-%EB%B9%84%EA%B5%90

https://blog.overops.com/the-ultimate-json-library-json-simple-vs-gson-vs-jackson-vs-json/