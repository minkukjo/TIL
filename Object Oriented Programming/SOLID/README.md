# 객체지향 5 원칙

SOLID 원칙

객체지향 설계에서 지켜야할 5개의 원칙의 앞글자를 따서 일반적으로 SOLID 원칙이라고 부른다.

그렇다면 SOLID 원칙을 왜 만들었고 왜 지켜야할까?

그 이유는 **시스템에 예상치 못한 변경사항이 발생하더라도, 유연하게 대처하고 이후에 확장성 있는 시스템 구조를 설계하기 위함이다.**

그래서 좋은 설계란 **시스템에 새로운 요구사항이나 변경사항이 발생하더라도 유연하게 대처할 수 있는 시스템**을 설계하는 것을 의마한다.

## 1. SRP (Single Responsibility Principle)

**객체가 단 하나의 책임만 가져야한다는 원칙**

SRP에 따른 설계를 하면 **응집도는 높고**, **결합도는 낮은** 설계가 가능하다.

SRP의 구체적인 예를 들기 위해 아래와 같은 계산기 클래스가 있다고 해보자.

```java
class Calculator{
  public int add(){
    // do Something
  }
  public int sub(){
    // do Something
  }
  public int multiply(){
    // do Something
  }
  public int division(){
    // do Something
  }
  public int alarm(){
    // do Something
  }
}
```

위의 클래스 설계에서 이상한 부분을 눈치챘는가?

계산기에 alarm 기능이 추가되어 있다.

이는 SRP 원칙을 위반한 것이 된다.

그렇다면 SRP 원칙에 맞게 다시 설계를 해보자.

```java
class Calculator{
  public int add(){
    // do Something
  }
  public int sub(){
    // do Something
  }
  public int multiply(){
    // do Something
  }
  public int division(){
    // do Something
  }
}
```

우선 계산기 클래스에서 alarm 메소드를 삭제한다.

```java

class Alarm{
  public void setTime(){
    // do Something
  }
  public void ring(){
    // do Something
  }
}
```

삭제한 Alarm은 별도의 Alarm 클래스를 만들어 그 기능을 수행하게 한다.

계산기와 알람 객체가 각각 **단일 책임**을 완수하고 있는 것을 볼 수 있다.

따라서, 이렇게 여러 객체들이 하나의 책임을 갖도록 분배한다면, 시스템에 변화가 생기더라도 그 영향을 최소화 할 수 있게 된다.

## 2. OCP (Open Closed Principle)

확장에는 열려있고(Open) 기존의 코드를 변경하지 않게(Closed) 설계가 되어야하는 원칙을 말한다.

즉, 확장에서는 개방적이며 수정에 대해서는 폐쇄적이어한다는 의미를 갖는다.

OCP를 만족하기 위해서는 캡슐화를 통해 여러 객체에서 사용하는 같은 기능을 인터페이스로 정의하는 방법이 있다.

가령 Aniaml 인터페이스에 crying() 메소드가 있다고 했을때 이를 구현하는 Cat,Dog,Bird 같은 클래스들이 crying() 메소드를 구현하게 하면 OCP 원칙을 지킬 수 있다.

새롭게 추가되는 동물은 Animal 인터페이스를 구현하면 되고, crying() 메소드의 구현은 Animal 인터페이스를 구현하는 클래스에서 메소드 오버라이딩으로 다형성을 표현하면 되기 때문이다.

## 3. LSP (Liskov Substitution Principle)

자식 클래스는 부모 클래스에서 행할 수 있는 행위를 수행할 수 있어야한다는 설계 원칙이다.

즉, 자식 클래스는 부모 클래스의 역할을 대체할 수 있어야함을 의미한다.

자식 클래스는 부모 클래스의 책임을 무시하거나 재정의하지 않고 확장만 수행하도록 해야한다.

예를들어 아래의 코드를 보자.

```java
class Rectangle {
    private int width;
    private int height;

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public int area() {
        return this.width * this.height;
    }
}
```

```java
class Square extends Rectangle {
    @Override
    public void setHeight(int value) {
        this.width = value;
        this.height = value;
    }

    @Override
    public void setWidth(int value) {
        this.width = value;
        this.height = value;
    }
}
```

이를 간단히 테스트해보자.

```java
class Test {
    static boolean checkAreaSize(Rectangle r) {
        r.setWidth(5);
        r.setHeight(4);

        if(r.area() != 20 ){ // Error Size
            return false;
        }

        return true;
    }
    public static void main(String[] args){
        Test.checkAreaSize(new Rectangle()); // true
        Test.checkAreaSize(new Square()); // false
    }
}
```

사각형 클래스의 경우 위의 테스트코드에서 true를 만족하지만 Square의 경우 그렇지 않음을 볼 수 있다.

왜냐하면 Square는 항상 height과 width가 같아야하기 때문이다.

때문에 위와 같은 경우에는 과연 Square가 Rectangle을 상속받게 하는 것이 맞는지를 의심해봐야 한다.

자식 클래스는 최소한 자신의 부모 클래스에서 가능한 행위는 수행할 수 있어야하기 때문이다.

물론 오버라이딩으로 이를 해결할 수 있지만 이는 위에서 언급한 **부모 클래스의 책임을 무시하거나 재정의하는 행위**이기 때문에 LSP를 따르는 설계를 위해서 가급적 오버라이딩을 남용하는 것은 좋지 않다고 할 수 있겠다.

## 4. ISP (Interface Segregation Principle)

**클라이언트가 자신이 사용하지 않는 메서드에 의존하지 않아야 한다는 설계 원칙**이다.

인터페이스 분리 원칙은 큰 덩어리의 인터페이스들을 구체적이고 작은 단위로 분리시킴으로써 클라이언트들이 꼭 필요한 메서드만 이용할 수 있게 한다.

예를 들어 여러 클라이언트가 복합기를 사용하는 시나리오를 봐보도록 하자.

![스크린샷 2020-02-12 오전 5 12 44](https://user-images.githubusercontent.com/43809168/74274954-56fa6c80-4d56-11ea-8fdd-244075f6af24.png)

하나의 복합기에 여러 클라이언트가 기능을 사용하고 있다.

만약 프린터 클라이언트가 팩스 기능 변경으로 인해 발생하는 문제에 영향을 받지 않으려면 어떻게 해야할까?

클라이언트와 무관하게 발생한 변화로 클라이언트가 영향을 받지 않으려면 범용 인터페이스 보다는 클라이언트에 특화된 인터페이스를 사용해야 한다.

![스크린샷 2020-02-12 오전 5 12 52](https://user-images.githubusercontent.com/43809168/74274960-58c43000-4d56-11ea-973a-b68b4b455a5f.png)

그래서 ISP는 말 그대로 인터페이스를 클라이언트에 특화되도록 분리시키라는 설계 원칙이라고 할 수 있다.

이렇게 설계하면 각각의 클라이언트는 자신이 사용하지않는 메서드에 생긴 변화로 인한 영향을 받지 않게 된다.

## 5. DIP (Dependency Inversion Principle)

의존 역전 원칙은 **고수준 모듈이 저수준 모듈에 의존해서는 안된다**라는 원칙을 갖고 있다.

이는 다시 말하면 **자신보다 변하기 쉬운 것에 의존하지 마라**라는 원칙을 갖고 있다.

즉 추상 클래스, 상위 클래스는 하위 클래스에 의존적이게 되어선 안된다.

![스크린샷 2020-02-12 오전 5 22 30](https://user-images.githubusercontent.com/43809168/74275672-adb47600-4d57-11ea-9cf8-996572df5073.png)

만약 벤츠가 스노우 타이어를 장착하고 있음을 표현하기 위해 다음과 같은 구조를 갖는다고 해보자.

하지만 스노우 타이어는 계절에 영향을 받기 때문에 겨울이 아닌 경우라면 다른 타이어로 교체하는게 맞다.

때문에 벤츠라는 클래스가 자신보다 더 자주 변하하는 스노우 타이어를 **의존**하는 구조를 갖게 된다.

이는 DIP를 위배하게 되는 것이며 추후에 변경 사항이 발생할 때 마다 벤츠를 수정해주어야 하는 문제가 발생한다.

![스크린샷 2020-02-12 오전 5 24 02](https://user-images.githubusercontent.com/43809168/74275800-e3f1f580-4d57-11ea-8bd4-5421deb8878a.png)

이를 해결하기 위해 타이어를 추상화 시킨 인터페이스로 만들고, 벤츠는 이 인터페이스에 의존하게 하였다.

벤츠는 이제 스노우 타이어와 같이 구체적이고 변화하기 쉬운 클래스에 의존하는 것이 아닌, 추상적인 타이어 인터페이스에 의존 시킴으로써 변화에 영향을 받지 않게 하였다.

상위로 갈 수 록 더 추상적이고 변화에 민감하지 않으며 수정 가능성이 낮아지기 때문이다.

이는 OCP와도 상당히 흡사함을 보인다.

## 결론

객체지향 설계 원칙을 잘 지키면 유지보수 성이 뛰어난 소프트웨어를 만들 수 있다.

하지만 카카오 헤어샵의 SOLID라는 글만 봐도 (https://brunch.co.kr/@cg4jins/3) 

카카오 개발자같이 뛰어난 개발자들도 실제로 이를 프로덕션에 적용하는 것은 상당히 어렵다는 것을 알 수 있다.

하지만 SOLID라는 것을 알아야 내가 만든 객체지향 설계가 제대로 되었는지 알 수 있으며, 장기적으로 유지보수가 쉬운 설계가 가능하기 때문에 반드시 알아야할 필요가 있어서 공부하였다.

그리고 개인적으로 위의 카카오 헤어샵 SOLID 글에서 인상깊었던 점은 카카오 헤어샵이 만든 객체지향 프로그램의 SOLID 원칙을 얼마나 잘 지켰는지 점수를 주고 평가를 하는 부분이 있는데, 나는 이 부분이 정말 인상 깊었다.

자신이 만든 소프트웨어를 자신이 냉정하게 평가하고 더 나은 소프트웨어를 만들기 위해 노력하는 모습은 마치 장인 같았다.

그렇다 소프트웨어 장인. 내가 꿈꾸는 영역이기도 하다.

나도 SOLID 원칙을 잘 이해해서 설계를 잘하는 개발자가 되고 싶다.


## Reference

https://victorydntmd.tistory.com/291

https://nesoy.github.io/articles/2018-03/LSP

https://defacto-standard.tistory.com/114

https://kim-gorilla-lab.tistory.com/18

https://limkydev.tistory.com/77