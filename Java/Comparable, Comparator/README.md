# Comparable, Comparator

Java에서는 정렬에 대한 두가지 인터페이스를 제공해준다.

Comparable과 Comparator이다.

직접 예제를 보며 어떤 차이가 있는지 알아보자.

```java
public class Player{
  private String name;
  private int score;

  public Player(String name, int score){
    this.name = name;
    this.score = score;
  }

  // Getter, Setter 생략
}
```

Player 클래스의 멤버들을 score가 높은 순으로 정렬하고 싶은 상황이다.

```java
  List<Player> players = new ArrayList<>();
  players.add(new Player("Alice", 899));
  players.add(new Player("Bob", 982));
  players.add(new Player("Chloe", 1090));
  players.add(new Player("Dale", 982));
  players.add(new Player("Eric", 1018));
```

이를 위해 ArrayList에 플레이어 5명을 추가하였다.

만약 스코어가 높은 순으로 정렬을 수행한다면 Chole가 1등으로, Alice가 꼴찌가 될 것이다.

Primitive 타입의 경우 일반적인 크기의 비교나 사전 순서 비교를 통해 정렬을 수행할 수 있지만 

사용자 정의 클래스의 경우 Name과 Score 중 어떤 것을 기준으로 정렬을 해야할지 컴파일러는 알 수 없다.

Comparable 인터페이스나 Comparator 인터페이스를 사용하지 않고 sort를 사용한다면 아래와 오류가 발생한다.

```java
Collections.sort(players); // Error!
```

# Comparable 인터페이스 구현

```java
public class Player implements Comparable<Player> {
    // Fields, Getters, Setters 생략

    @Override
    public int compareTo(Player o) {
        return o.getScore() - getScore();
    }
}
```

Comparable Interface는 위와 같이 구현 가능하다.

compareTo 메소드를 오버라이딩하여 사용할 수 있다.

매개변수인 Player o가 더 큰경우 양수를, 작은 경우 음수를 반환할 것 이므로 Score가 큰 값으로 정렬이 가능하게 된다.

이처럼 위와같은 소팅 메소드를 사용하기 위해서 클래스에 Comparable 인터페이스의 compareTo 메소드를 구현하였다.

## Comparator 구현

그러나 정렬 대상 클래스를 직접 수정할 수 없는 경우가 있다고 해보자.

기존에 객체에 정의된 정렬기준과 다른 정렬기준을 적용시키고 싶을 수가 있다.

이때 사용하는 것이 Comparator이며 기존 정렬 기준을 무시하고 새로운 정렬 기준으로 객체를 정렬 할 수 있다.

```java
Comparator<Player> comparator = new Comparator<Player>() {
    @Override
    public int compare(Player a, Player b) {
        return b.getScore() - a.getScore();
    }
};

Collections.sort(players, comparator);
```

구현은 위와 같은 형태로 가능하다.

Comparator는 Comparable과는 달리, 기존 객체의 정렬 방법이 아닌 새롭게 정의된 정렬방법을 사용하고 싶은 경우 주로 사용한다.

## Lambda

Comparator는 자바 8버전부터 제공하는 Lambda 함수로 대체 가능하다.

```java
Collections.sort(players, (a, b) -> b.getScore() - a.getScore());
```

## Stream

역시나 마찬가지로 자바 8버전부터 제공하는 Stream으로 정렬도 가능하다.

```java
List<Player> sortedPlayers = players.stream()
        .sorted((a, b) -> b.getScore() - a.getScore())
        .collect(Collectors.toList());
```

즉, Comparator는 기존 객체의 정렬 기준을 변경하지 않고 새로운 정렬 기준으로 객체를 정렬하고 싶을 때 사용하는 인터페이스이다.

# Reference 

https://www.daleseo.com/java-comparable-comparator/

https://dev-daddy.tistory.com/23
