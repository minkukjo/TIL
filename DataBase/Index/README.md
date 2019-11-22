# Index란?

RDBMS에서 검색 속도를 높이기 위해 사용하는 기술 입니다.

Index는 색인이라는 의미로 Table의 Column을 색인화(File로 저장) 하여 검색시 해당 Table을 Full Scan하는 것이 아닌, 색인화 된 Index File을 참조하여 검색 속도를 빠르게 합니다.

Index는 Tree구조로 되어 있습니다. RDBMS에서는 Balanced Search Tree 사용하고 있습니다.

실제 RDBMS에서 사용되는 자료구조는 B-Tree에서 파생된 B+ Tree를 사용하고 있습니다.

또한 DB 내부에는 전체 크기의 10%정도 크기의 Index를 위한 공간을 따로 마련해두고 있습니다.

# Index를 고르는 조건

컬럼에 Index를 걸때는 Cardinality가 높은 것을 골라야 합니다.

Cardinality란 해당 컬럼의 중복된 수치를 나타냅니다.

성별, 학년 등은 카디널리티가 낮다고 표현합니다.

그에 비해 주민등록번호, 계좌번호는 카디널리티가 높습니다.

인덱스로 최대한의 효율을 뽑아내려면 해당 인덱스로 데이터를 많이 걸러내야하기 때문입니다. 성별을 인덱스로 고른다면 50%밖에 걸러내지 못하지만,

고유값인 주민등록번호나 계좌번호의 경우 데이터의 대부분을 걸러낼 수 있기 때문에 빠르게 검색이 가능합니다.

그래서 일반적으로는 Primary Key를 Index로 설정하게 됩니다.


# Index의 단점

Index 쓰면 Search 속도를 높일 수 있는 장점이 있습니다.

그러나 Index는 상황에 맞게 사용해야 합니다.

Index는 Select문에서 where와 join에 뛰어난 성능을 보이지만,

insert, update, delete에서는 오히려 Index를 사용하지 않는 것 보다 사용하는 쪽의 성능이 더 떨어지게 됩니다.

그러면 왜 Index를 사용하면 insert, update, delete에서는 오히려 성능이 더 떨어지는 것 일까요?

**Insert**

- 새로운 데이터를 삽입하면 DB Table 뿐만 아니라 Index Table에도 생성 해주어야 한다.
- Index의 Leaf Block이 꽉 찬 상태에서 Block 중간에 새로운 값이 삽입되는 경우 새롭게 Tree를 재구성해야하기 때문에 여기에 추가적인 비용이 들어가게 된다.
- 즉, Insert를 수행할 때 기존 데이터에 추가하는 것 외에도 Index Table에도 해당 데이터를 추가해주어야 하므로 Insert의 성능이 느려질 수 밖에 없다.
 
**Delete**

- 기존 DB Table에서 레코드를 삭제하면 그 공간을 다른 레코드가 사용할 수 있지만, Index Table에서는 사용하지않는다고 표시만 하고, 데이터를 지우지 않는다.
- 삭제가 빈번하게 일어날 경우 삭제된 데이터에 대한 Index는 삭제되지 않고 Index Table에 남게 되고 이는 Index Table의 크기를 키우는 결과를 초래한다.
- 또한 Delete를 수행할 때 DB 데이터를 삭제하는 것 외에도, Index에 해당 데이터를 사용하지않음으로 표시해야하는 작업도 수행해야하기때문에 더 느리다.

**Update**

- Update는 Delete와 Insert가 동시에 발생하기 때문에 다른 DML 보다 더 큰 부하를 주게 된다.



## Reference

[https://jeong-pro.tistory.com/114](https://jeong-pro.tistory.com/114)
[https://lalwr.blogspot.com/2016/02/db-index.html](https://lalwr.blogspot.com/2016/02/db-index.html)
[https://lalwr.blogspot.com/2016/02/db-index.html](https://lalwr.blogspot.com/2016/02/db-index.html)
[https://majesty76.tistory.com/55](https://majesty76.tistory.com/55)
[https://jojoldu.tistory.com/243](https://jojoldu.tistory.com/243)
[https://maskkwon.tistory.com/218](https://maskkwon.tistory.com/218)
