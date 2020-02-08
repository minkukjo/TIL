# Join

### Join의 한줄 정의

```2개 이상의 테이블이나 데이터베이스를 연결하여 데이터를 검색하는 방법```

Join은 크게 inner join과 outer join으로 나뉜다.

## INNER JOIN

<img width="388" alt="_2019-08-28__1 23 22" src="https://user-images.githubusercontent.com/43809168/74088862-43dd6780-4ade-11ea-8237-59996091a2c4.png">

쉽게 생각하면 고등학교때 배운 교집합을 떠올리면 된다.

기준 테이블과 Join한 테이블의 중복된 값을 보여준다.

A 테이블과 B 테이블이 모두 갖고 있는 데이터만 검색된다.

## OUTER JOIN

### LEFT OUTER JOIN

<img width="356" alt="_2019-08-28__1 25 01" src="https://user-images.githubusercontent.com/43809168/74088863-463fc180-4ade-11ea-920f-7ecf8df84881.png">

**기준 테이블 값 + 기준 테이블과 Join 테이블의 중복된 값**

왼쪽 테이블을 기준으로 Join을 수행한 경우 A 테이블의 모든 내용과 B 테이블과의 교집합된 내용이 포함되서 나온다.

### RIGHT OUTER JOIN

<img width="366" alt="_2019-08-28__1 25 53" src="https://user-images.githubusercontent.com/43809168/74088865-46d85800-4ade-11ea-9abb-645de106412f.png">

오른쪽을 기준으로 Join을 한 경우이다.

위의 경우와 반대

### FULL OUTER JOIN

<img width="372" alt="_2019-08-28__1 30 48" src="https://user-images.githubusercontent.com/43809168/74088868-4770ee80-4ade-11ea-9272-19c47a48b00c.png">

여기서는 합집합을 떠올리면 된다.

A 테이블이 가진 데이터와 B 테이블이 가진 데이터 모두 검색된다.

사실상 기준 테이블의 의미가 사라진다.

## Reference

https://clairdelunes.tistory.com/22