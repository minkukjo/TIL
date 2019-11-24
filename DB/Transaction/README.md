# Transaction?

**1줄 요약**

데이터 베이스의 상태를 변화시기 위해 수행하는 '작업의 단위'

**부가 설명**

질의어 (SQL)을 이용하여 DB에 접근하는 것을 의미합니다. (Select,Insert,Delete,Update)
물론 Transaction이란 SQL을 의미하는 것은 아닙니다.
가령, 게시판으로 예를 들어봅시다.
사용자가 게시판에 글쓰기를 누르고 글을 다 쓰고 완료를 누를 시, 해당 글에 대한 정보가 Insert 되어집니다. 
이후 사용자에게 Insert된 데이터를 포함하여 게시글을 보여주기 위해 Select를 수행하게 되고, Insert + Select가 하나의 Transaction이 되게 됩니다.
이것이 Transaction입니다.

## Transaction의 특징

Transaction은 4가지 특징을 갖고 있습니다.
지금부터 Transaction이 어떻게 ```ACID```라고 불리우는 4가지 특성을 보장하는지 알아보도록 하겠습니다.

## Atomicity (원자성)
Transaction에서 원자성은 단 두가지의 상태만을 갖습니다.
성공했거나? 실패했거나?
원자성은  Transaction에 의해 변경된 내용을 유지하면서, 이전 commit된 상태를 임시 영역에 저장합니다. 
즉, 현재 수행하고 있는 Transaction에 오류가 발생하게 될 경우, 변경사항을 모두 날리고 임시 영역에 저장해둔 내용으로 RollBack 합니다.

이전 commit 상태를 저장하고 있는 임시영역은 ```Rollback Segment```라고 부릅니다.
현재 수행하고 있는 Transaction에 의해 새롭게 변경되는 영역을 ```Database Table```이라고 부릅니다.

결론적으로 Transaction에서 원자성은 ```Rollback Segment```가 보장한다고 할 수 있겠습니다.

그런데 이러한 원자성에도 특수한 상황이 있을 수 있습니다.
만약, Transaction 매우 긴 상황에서 Rollback이 될 경우가 있다고 가정합시다.
다시 Transaction을 진행하게 될 경우 이미 성공했던 부분도 다시 진행해야하는 문제가 있을 수 있습니다.
이는 DB에 부하로 이어질 수 있기 때문에, 이미 한번 성공한 쿼리에 대해서는 Rollback하지않도록, Save Point를 지정할 수 있습니다. Save Point를 지정하면 Rollback시 Save Point 이전은 성공했다고 가정하고 이후부터 Transaction을 진행하게 됩니다.

## Consistency (일관성)
Transaction에서 Consistency란 Transaction 수행 전, 후 모든 제약조건(Primary Key, Foreign Key, Domain, Domain 제약조건)을 만족하는 것을 통해 보장합니다.

Transaction은 이벤트와 조건이 발생했을 때 ```Trigger```를 통해 보장합니다.

```SQL
Create trigger id_check
After update of id_check on Employee

...

for each row
 ...
begin
 ...
end
```

Create를 통해 Trigger를 생성하고 After는 Trigger가 실행되기 위한 Event를 정의합니다.

e.q) 자금 이체를 할 시 두 계좌의 잔고의 합은 이체를 실행하기 전,후가 같아야 합니다.
e.q) 게시판의 글자수 제한이 255인 경우 Transaction이 성공하려면 이 조건을 만족해야만 합니다.
만약 위의 조건을 만족하지 않는 Transaction은 실패처리 합니다.

## Isolation (고립성)
Transaction 수행 시 다른 Transaction이 작업에 끼어들지 못하도록 보장하는 것을 의미합니다.
Transaction은 서로 간섭할 수 없습니다.

**Isolaiton Level이란**

이러한 Isolation을 가장 쉽게 보장할 수 있는 방법은 무엇일까요?
그렇습니다. 바로 모든 Transaction을 ```순차적```으로 실행하면 됩니다.
하지만 이렇게 ```순차적```으로 Transaction을 수행하면 속도가 느려지게 됩니다.
그래서 DBMS에서는 이 Isolation을 Level로 나누어 제공해줍니다.

기본적으로 4가지의 Isolation을 제공합니다.
1. Read Uncommitted
2. Read Committed
3. Repeatable Read
4. Serializable

지금부터 각각의 Isolation Level에 대해 알아보도록 하겠습니다.

**Read Uncommitted**

<img width="667" alt="Read_Uncommited" src="https://user-images.githubusercontent.com/43809168/69421197-bfdbd500-0d63-11ea-8bcb-0fc6fc50bedd.png">

- 가장 낮은 격리 수준
- ```Commit```이나 ```Rollback```여부에 관계 없이 다른 Transaction의 값을 읽을 수 있습니다.

Read Uncommitted에서는 ```Dirty Read``` 문제가 발생할 수 있습니다.

**Dirty Read란?**
Transaction1이 실행하고 있는 Transaction이 끝나지도 않았는데 Transaction2가 변경 사항을 읽어내는 것을 ```Dirty Read```라고 합니다.
만약 이 경우 Transaction1이 ```Rollback```되어 버리면 Transaction2가 존재하지 않는 데이터를 읽고 있는 것이 되고, 이를 Transaction2가 ```Dirty``` 데이터를 갖고있다 라고 말합니다.

**Read Committed**

<img width="666" alt="Read_Committed" src="https://user-images.githubusercontent.com/43809168/69421478-763fba00-0d64-11ea-9ebb-c6b62a80177b.png">

- RDB에서 대부분 기본 옵션으로 Read Committed를 사용하고 있습니다.
- ```Dirty Read```가 발생하지 않습니다.
- 실제 데이터를 가져오는 것이 아닌, Undo에 백업된 데이터를 가져옵니다.

**Read Committed의 문제점**

<img width="651" alt="Read_Commited문제점" src="https://user-images.githubusercontent.com/43809168/69421618-dd5d6e80-0d64-11ea-8066-927f19f1356a.png">

- Transaction1이 완료되기 전 Transaction2는 Busan을 읽지만, Transaction1이 완료된 후 Transaction2는 Jeju를 읽습니다. 이는 하나의 Transaction 내에서 ```SELECT```를 수행하였을 때 쿼리 결과가 다르게 됩니다. 이를 ```Unrepeatable Read```라고 부릅니다.
- ```Unrepeatable Read```가 발생하면 데이터 정합성이 깨지게 됩니다.

**Repeatable Read**

<img width="739" alt="Repeatable_Read" src="https://user-images.githubusercontent.com/43809168/69421916-a5a2f680-0d65-11ea-9f17-8ea34dc06fb9.png">

- Transaction에 ID를 부여하여 Transaction ID보다 작은 ID가 변경한 값만 읽게 합니다.
- Undo에 백업해두고 실제 레코드 값을 변경합니다
	- 백업 데이터는 주기적으로 삭제합니다.
	- 백업 데이터가 많아질 경우 성능이 느려질 수 있습니다.
- 이 방식을 MVCC(Multi Version Concurrency Control)이라고 부릅니다.

**Repeatable Read의 문제점**

<img width="746" alt="PHANTOM_READ" src="https://user-images.githubusercontent.com/43809168/69422048-0fbb9b80-0d66-11ea-8099-6e2f9e3a4916.png">

- ```Phantom Read```문제의 발생
	- ```Phantom Read```란 다른 Transaction에 의해 데이터가 보였다 안보였다 하는 현상 (마치 귀신처럼)
	- 이를 방지하기 위해서는 쓰기 잠금을 걸어야 합니다.

**Serializable**

- 최고 수준의 격리 수준 입니다.
- 모든 격리수준 중 동시성 처리성능이 가장 느립니다.
- ```Phantom Read``` 현상 조차 발생하지 않습니다만 거의 사용하지 않습니다.

**Isolation Level 정리**

|Isolation Level|Dirty Read|Repeatable Read|Phantom Read|
|:---:|:---:|:---:|:---:|
|Read Uncommitted|O|O|O
|Read Committed|-|O|O
|Repeatable Read|-|-|O
|Serializable|-|-|-

격리 수준이 Read Uncommitted에서 Serializable로 올라갈 수록 Concurrency는 높아지지만 속도는 느려집니다.
즉, 격리수준에서 Concurrency와 성능은 Trade Off 관계 이므로 상황에 맞게 적절한 Isolation Level을 설정하는 것이 중요합니다.

## Durability (지속성)
Transaction이 성공적으로 수행되고 난 이후 변경 사항은 영구적으로 반영되어야 합니다.
시스템 장애가 발생한다면 어떤 형태로든 복구할 수 있어야 합니다.
그래서 DBMS는 일반적으로 로그를 남기고 장애가 발생하면 이전 상태로 되돌아 갑니다. Transaction은 로그에 모든 것이 저장 된 후에만 commit된 상태로 간주합니다.

## Reference
[https://nesoy.github.io/articles/2019-05/Database-Transaction-isolation](https://nesoy.github.io/articles/2019-05/Database-Transaction-isolation)
[https://effectivesquid.tistory.com/entry/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-Isolation-Level](https://effectivesquid.tistory.com/entry/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-Isolation-Level)