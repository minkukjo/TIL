# 템플릿 메소드 패턴

템플릿 메소드 패턴은 메소드에서 알고리즘의 골격을 정의하는 것을 의미한다.

알고리즘의 여러 단계 중 일부는 서브 클래스에서 구현할 수 있다.

템플릿 메소드를 이용하면 알고리즘의 구조는 그대로 이용하면서 서브 클래스에서 특정 단계를 재정의해서 구현할 수 있다.

예제 코드를 보며 템플릿 메소드 패턴을 이해해보자

```java
public class Main{
  public static void main(String[] args){
    // 전사는 전투 준비를 위해
    // 체력을 단련하고 갑옷을 입는다.
    Warrior warrior = new Warrior();
    warrior.readyToBattle();

    // 궁수는 전투 준비를 위해
    // 체력을 단련하고, 활을 손질하고, 화살을 준비한다.
    Archer archer = new Archer();
    archer.readyToBattle();

    // 마법사는 전투 준비를 위해
    // 체력을 단련하고, 지팡이를 준비하고, 로브를 입는다.
    Wizard wizard = new Wizard();
    wizard.readyToBattle();
  }
}
```

전사,궁수,마법사 모두 인간이므로 인간이라는 공통 추상 클래스를 정의한다.

```java
public abstract class Person{
  void readyToBattle(){
    startBodyTraining();
    prepareWeapon();
    prepareArmor();
    if( isUsingPrepareOther() ){
      prepareOther();
    }
  }

  // 상속받은 클래스에서 재정의할 수 없게 final 키워드를 붙였다.
  final void startBodyTraining(){
    System.out.println("체력을 단련한다.");
  }

  abstract void prepareWeapon();
  abstract void prepareArmor();

  // 다른 무언가를 사용하려면 true로 바꿔야 한다.
  // 이 메소드를 "후킹(Hooking)" 용도로 사용한다.
  boolean isUsingPrepareOther(){
    return false;
  }

  // 만약 다른 기능이 필요하다면 사용
  void prepareOther(){};

}
```

위 클래스는 전사,궁수,마법사가 공통으로 상속받을 Person 추상 클래스이다.

체력단련의 경우 세 직업 모두 공통으로 사용하기 때문에 final로 명시하였다.

isUsingPrepareOther의 경우 후킹(Hooking)의 용도로 사용하는데, 필요한 경우 상속받은 클래스에서 재정의하여 사용할 수 있다.

이 Hoock Method는 abstract가 아니기때문에 반드시 구현할 필요가 없다. 상속받은 클래스에서 선택적으로 오버라이딩이 가능하다는 얘기이다.

## 세부 클래스 - 전사

```java
public class Warrior extends Person{
  @Override
  void prepareWeapon(){
    System.out.println("검을 닦습니다.");
  }

    @Override
  void prepareArmor(){
    System.out.println("갑옷을 입습니다.");
  }
}
```

전사 클래스는 검을 닦고 갑옷을 입는 메소드를 재정의해준다.

## 세부 클래스 - 궁수

```java
public class Archer extends Person{
  
  @Override
  void prepareWeapon(){
    System.out.println("활을 손질합니다.");
  }

  @Override
  void prepareArmor(){}

  @Override
  boolean isUsingPrepareOther(){
    return true;
  }

  @Override
  void prepareOther(){
    System.out.println("화살을 준비합니다.");
  }

}
```

궁수 클래스에서는 후킹(Hooking)이 필요하게 된다.

화살을 준비하려면 부모 추상 클래스의 prepareOther 메소드를 재정의 해주어야한다.

따라서 isUsingPrepareOther()의 메소드를 true로 재정의 해주었다.

다만 궁수는 갑옷을 입지 않으므로 비워놓았다.

## 세부 클래스 - 마법사

```java
public class Wizard extends Person{
  @Override
  void prepareWeapon(){
    System.out.println("지팡이를 준비합니다.");
  }

    @Override
  void prepareArmor(){
    System.out.println("로브를 입습니다.");
  }
}
```

마법사 클래스는 전사와 동일하게 작성한다.

## 실행

```
[전사]
체력을 단련합니다.
검을 닦습니다.
갑옷을 입습니다.
-------------
[궁수]
체력을 단련합니다.
활을 손질합니다.
화살을 준비합니다.
-------------
[마법사]
체력을 단련합니다.
지팡이를 준비합니다.
로브를 입습니다.
```

예제를 통해 템플릿 메소드 패턴을 왜 사용하는지를 알아보았다.

템플릿 메소드 패턴은 전체 레이아웃을 통일시키고 상속받는 클래스로 하여금 어느정도 유연성을 갖게하는 디자인 패턴의 한 종류이다.

추상 메소드와 훅 메소드를 적절히 사용해서 전체적인 알고리즘의 뼈대를 유지하되 유연하게 기능을 변경할 수 있도록 하고자 할때 사용한다.


## Referece

https://jdm.kr/blog/116