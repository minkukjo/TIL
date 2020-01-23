# 추상클래스와 인터페이스

추상클래스는 클래스 내부에 '추상 메소드'가 하나 이상 포함되어 있는 클래스를 의미한다.

extends 키워드를 사용하여 상속받아 사용할 수 있다.

그리고 추상 클래스는 인스턴스화 할 수 없으며, 추상 클래스를 상속받은 클래스는 추상 메소드를 오버라이딩하여 구현해야만 인스턴스화 될 수 있다.

인터페이스 또한 추상 메소드로 이루어져 있다.

인터페이스는 추상 클래스와는 달리 추상 메소드'로만' 이루어져 있다.

추상클래스는 추상 메소드를 포함하되, 일반 메소드를 정의하는 것이 가능하다는 의미이다.

추상 클래스와 인터페이스의 차이는 위와 같은 '외형적' 차이를 보인다.

그렇다면 정말로 추상 클래스와 인터페이스는 어떨때 사용하는 걸까?

지금부터 이 둘의 본질적인 차이에 대해서 알아보도록 하자.

## 본질적 차이

이 둘의 본질적 차이를 이해하기 위해서는 우선 **상속**의 특성을 이해할 필요가 있다.

자바에서는 기본적으로 **다중 상속**을 지원하지 않는다.

즉, 추상 클래스를 상속받는다는 의미는, 클래스 간의 규약을 만든다는 의미이며, 부모 클래스의 기능을 확장하기 위해 사용된다.

반면 인터페이스는 해당 인터페이스를 구현한 객체들이 동일한 동작을 수행하는 것을 약속하기 위해 존재한다.

때문에 인터페이스가 다중상속을 허용하는 것 또한, 약속을 구현하는 것이기 때문에, 혹여 둘 이상의 인터페이스에서 같은 이름의 메소드가 정의되더라도, 이들은 추상 메소드이기 때문에

이를 구현한 클래스 입장에서는 어떤 것을 구현하더라도 상관이 없다.

# Reference

https://brunch.co.kr/@kd4/6