![image](https://user-images.githubusercontent.com/43809168/66479431-739c4480-ead7-11e9-9dc3-a374faf9d948.png)

## Partial Upadte

현재 저는 네이버 예약팀에서 진행하는 프로젝트를 위해 ElasticSearch(ES)를 사용하고 있습니다.

본 프로젝트에서 중요한 부분 중 하나는 프로덕션 서버의 RDB와 ES간의 싱크를 맞추는 것 입니다.

프로덕션 서버의 RDB에서 변경 사항이 발생할 경우 Kafka를 이용해 바뀐 날짜와 시간을 알려주고

ES에 바뀐 필드를 **부분적**으로 업데이트해야 합니다.

하지만 저는 이 **부분적** 업데이트의 가능성 유무와 성능에 의구심이 생겼습니다.

그래서 제가 나름대로 찾아보고 공부한 내용을 공유해보려고 합니다.


## Update의 비용과 Immutable Document

![image](https://user-images.githubusercontent.com/43809168/66479434-74cd7180-ead7-11e9-8a00-ccc89de4448f.png)

ElasticSearch의 공식문서에 따르면, ElasticSearch에서 Document는 Immutable한 성질을 갖고 있다고 나와있습니다.

이는 Elastic Search가 루씬 라이브러를 사용해 개발된 것과 관련이 있습니다.

루씬은 기본적으로 Segment 단위로 문서와 인덱스들을 저장합니다.

이 Segment들은 아래와 같이 관리되어집니다.

![image](https://user-images.githubusercontent.com/43809168/66479438-772fcb80-ead7-11e9-979d-77e18c096c31.png)

루씬 내부에서는 Update가 존재하지않고, Delete와 Create만이 존재합니다.

문서들은 삭제가 되었을 시 실제로 삭제하지 않고 표시만 하고 남깁니다.

삭제된 문서를 제외하고, 나머지 문서를 모아 새로운 Segment 만들어냅니다.

이때 Segment 병합에는 디스크 I/O가 필요하기 때문에 비용이 아주 비쌉니다.

![image](https://user-images.githubusercontent.com/43809168/66479441-7860f880-ead7-11e9-9cf3-462cb060e2b9.png)

때문에, 나중에 업데이트된 Segment 내의 문서를 새로 생성된 Segment 옮기는 것은 불가능 합니다.

이것이 Elastic Search에서 Document가 Immutable 하다고 말하는 이유입니다. (사실은 Segment가 Immutable하다고 하는 것이 더 정확합니다. )

하지만 Elastic Search에서는 Update라는 기능을 제공하고 있습니다. 이게 어떻게 된 일일까요?

사실 Elastic Search에서 제공하는 Update는 다음의 과정을 거쳐서 Update 됩니다.

검색 -> 변경 -> 재인덱싱

즉, 다시 말하면 인덱싱을 새롭게 한다고 볼 수 있는데, 때문에 Update는 비용이 비쌉니다.

그러나 검색 - 변경 - 재인덱싱이 샤드내에서 발생하므로 여러 요청에 의한 네트워크 오버헤드를 피할 수 있다는 장점이 있습니다.

제가 진행하는 프로젝트를 위해서는 이 Update 기능을, 특히 Partial Update를 사용해야 했고,

이를 위해 Elastic Search에서는 다음과 같은 기능을 제공하고 있다는 것을 알게 되었습니다.

## Painless Script

Painless Script Language는 ElasticSearch에서 제공하는 동적 스크립트 언어입니다.

문법은 Groovy와 매우 흡사하며, 이 스크립트를 이용해 Partial 업데이트를 할 수 있었습니다.

![image](https://user-images.githubusercontent.com/43809168/66479601-d68ddb80-ead7-11e9-8946-20055b8e57bd.png)

                                  Painless로 작성된 스크립트의 예시

하지만 위에서 언급한 Update의 비싼 비용과 마찬가지로, 이 스크립트 쿼리 또한 비용이 비싸다는 것을 알 수 있습니다.

스크립트 쿼리가 비싼 이유는, 업데이트된 문서를 로드하여 구문을 분석하고, 언어의 컴파일 타임까지 고려하면
그다지 좋은 선택은 아니라는 것을 알 수 있습니다.

그럼에도 불구하고, 스크립트 쿼리를 사용하는 것은 사실 '성능'을 위해서라기 보다는 

사용자 정의의 업데이트를 사용하기 위함에 더 가깝다고 할 수있습니다.

## 결론

```기존 Update의 비싼 비용 + Script Query의 비싼 비용 = 파산```


때문에 ElasticSearch는 데이터의 변경이 잦은 시스템에서 사용하기에는 **부적합**하다는 사실을 알 수 있습니다.

그럼에도 불구하고, 이러한 과정을 거쳐서까지 ElasticSearch를 사용하는 가장 큰 이유는,

엘라스틱 서치는 ***대용량 데이터***를 다루는데 최적화 되어 있습니다.

저는 현재 굉장히 많은 양의 데이터를 다루고 있고,

이것을 이용해 네이버 검색엔진을 사용하지않고, 엘라스틱 서치의 자체 검색엔진을 이용해

질의까지 수행하게 하는 요구사항도 있기 때문에 엘라스틱 서치를 선택하였습니다.

Partial Update 성능에 관한 이슈를 알아보기 위한 과제이기도 한 만큼, 실제 프로덕션 서버의 데이터와 연동하였을 때 어느정도의 성능을 보일지가 앞으로 ES를 선택할지 말지를 가르는 관건이 아닐까 싶습니다.

지금까지 긴 글 읽어주셔서 감사합니다.

## 출처

https://stackoverflow.com/questions/30151566/why-are-documents-in-elasticsearch-immutable

https://discuss.elastic.co/t/partial-update-and-nested-type-performance/110534

https://discuss.elastic.co/t/partial-updates-of-nested-documents/90055

https://www.elastic.co/guide/en/elasticsearch/guide/current/partial-updates.html

이미지 : https://helloino.tistory.com/114
