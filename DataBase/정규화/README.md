# 정규화(Normalization)란?

데이터베이스의 설계를 재구성하는 기술입니다.

정규화를 통해 불필요한 데이터를 없앨 수 있고 데이터의 중복을 최소화 할 수 있습니다.

또한 삽입/삭제/갱신 시 발생할 수 있는 각종 이상 현상을 방지할 수 있습니다.

데이터베이스 정규화의 목적은 두가지 입니다.

1. 불필요한 데이터를 제거한다.
2. 데이터 저장을 ```논리적```으로 한다.

<img width="870" alt="1" src="https://user-images.githubusercontent.com/43809168/69430063-4d292480-0d78-11ea-905e-5f3fa01957e6.png">

위 테이블은 정규화가 되지 않은 과목 수강 신청에 대한 테이블입니다.

현재 S_Name에 Adam이 두번 들어가 있는 것을 볼 수 있습니다.

위 테이블에 DML 문제점에 대해서 몇가지 짚어보도록 하겠습니다.

1. Update : Adam의 주소가 Update될 경우 Adam에 해당하는 모든 데이터를 업데이트 해주어야 한다.
2. Insert : 만약 어떤 학생도 수강하지 않는다면 Subject_opted에 Null이 들어가게 된다.
3. Delete : Alex가 과목을 수강 취소하면 Alex에 대한 레코드가 지워진다.

위와 같이 정규화가 제대로 되지 않은 경우 갱신/삽입/삭제에 이상이 발생할 수 있다는 것을 알 수 있습니다.

때문에 지금부터 1,2,3차 정규화를 통해 위 테이블을 정규화 해보도록 하겠습니다.

일반적으로 3차 정규화까지 진행되면 ```정규화가 완료되었다.``` 라고 말합니다.

## 1차 정규화 (1NF, First Normal Form)

1차 정규화에서는 각 Row마다 Column이 하나의 값을 가져야 만족할 수 있습니다.

이를 Column이 원자값을 갖는다고 표현합니다.

하나의 속성이 단일 값을 갖도록 설계해야 합니다.

<img width="884" alt="2" src="https://user-images.githubusercontent.com/43809168/69430659-71393580-0d79-11ea-8b61-f72a3e44911c.png">

위의 Student Table에서 Adam은 Subject Column에서 2개의 값을 갖고 있는 것을 볼 수 있습니다.

이는 1차 정규화를 만족하지 않습니다.

<img width="869" alt="3" src="https://user-images.githubusercontent.com/43809168/69430667-739b8f80-0d79-11ea-9677-330b98e2956c.png">

1차 정규화를 만족하기 위해 Adam을 쪼개어 1차 정규화를 만족하였습니다.

그러나 이 과정에서 데이터 중복이 발생한 것을 볼 수 있습니다.

데이터의 논리적 구성을 위해 이를 희생하였습니다.

## 2차 정규화 (2NF, Second Normal Form)

2차 정규화부터는 본격적인 정규화의 시작이라고 볼 수 있습니다.

```2차 정규화가 되기 위해서는 기본키가 아닌 모든 속성이 기본키에 완전 함수 종속성이 되야 합니다.```





## 3차 정규화 (3NF, Third Normal Form)


## Reference

[https://beansberries.tistory.com/entry/데이터-종속성과-정규화](https://beansberries.tistory.com/entry/%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%A2%85%EC%86%8D%EC%84%B1%EA%B3%BC-%EC%A0%95%EA%B7%9C%ED%99%94)
[https://3months.tistory.com/193](https://3months.tistory.com/193)