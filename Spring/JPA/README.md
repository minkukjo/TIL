# JPA란

JPA란 Java Persistence API의 줄임말로써 자바 진영의 표준 ORM 기술이다.

ORM이란 Object Relational Mapping의 약자로 객체와 관계형 데이터베이스를 매핑하는 기술을 의미한다.

즉 JPA는 자바의 객체와 데이터베이스를 매핑하는 표준 기술이다.

ORM 프레임워크는 객체/관계형 데이트베이스 패러다임의 불일치를 개발자 대신 해결해준다.

ORM 프레임워크를 사용하면 개발자는 객체를 저장하거나 조회할때 SQL문을 사용할 필요가 없다.

객체를 자바 컬렉션에 저장하듯이 ORM 프레임워크에 저장하면 프레임워크가 대신 적절한 SQL 문을 생성하여 데이터베이스에 저장해준다.

JPA는 ORM 기술 표준이다.

즉, 자바에서 ORM 기술을 이용하기 위한 표준 API 명세이자 인터페이스들의 집합이라고 할 수 있다.

그래서 가장 유명한 JPA의 구현체로는 **Hibernate**가 있다.

기존의 JDBC API를 사용하면 유사한 CRUD SQL을 작성해줘야 했고, 객체를 단순히 데이터 전달 목적으로 사용할 뿐 객체지향적이지 못하기 때문에 문제가 있었다.

그래서 객체와 테이블을 매핑해주는 ORM이 주목 받기 시작했고, 자바 진영에서는 JPA가 표준 스펙이 되었다.

## JPA의 특징

### 장점

1. 생산성

SQL을 직접 안짜도 되기 때문에 생산성이 높아진다.

2. 유지보수

테이블 컬럼이 변경되는 경우, Mybatis에서는 DAO의 파라미터, 결과, SQL 모두 확인하여 수정해야하지만, JPA를 사용하면 JPA가 대신 해주기 때문에 유지보수 측면에서 좋다.

3. 특정 벤더에 종속적이지 않다.

JPA는 추상화된 데이터 접근 계층을 제공하기 때문에 특정 DB 벤더에 종속적이지 않다.

즉 설정 파일에서 JPA에게 현재 어떤 DB를 사용하고 있는지만 알려주면 얼마든지 DB를 바꿀 수 있다.

### 단점

1. 성능

당연히 SQL을 직접 짜는 것보단 메소드 호출만으로 쿼리를 수행하는 JPA의 SQL이 성능이 더 떨어질 수 밖에 없을 것이다.

실제로 초기의 ORM은 쿼리 성능이 나빴으나 최근에는 많이 발전하여 좋아졌다고 한다.

2. 세밀함

메소드 호출로 DB 데이터를 조작하기 때문에 세밀한 조작이 어렵다.

3. 러닝 커브

JPA를 제대로 사용하기 위해서는 알아야할 것이 많다. 즉 러닝커브가 높다.

## 결론

그럼에도 불구하고 Hibernate는 전셰게적으로 압도적인 점유율을 보여주고 있다.

물론 우리나라에서는 시장 대부분이 금융, SI이기 때문에 Mybatis를 많이 사용하고 있다.

하지만 네이버나 카카오 배민 같은 회사들은 기술력 있는 회사들에선 JPA를 쓰고 있다.

나도 개인 프로젝트로 JPA를 쓰고 있는데 아직 단순한 기능 구현이긴 하지만 확실히 SQL문을 직접 안써도 되서 편리하다고 느끼고 있다.

생산성이 높은 ORM 기술이 나는 마음에 든다.

만약 회사에 취직하게 된다면 이 JPA에 대해서 더 깊게 공부해보고 싶다.

## Reference

https://victorydntmd.tistory.com/195

https://do-study.tistory.com/58