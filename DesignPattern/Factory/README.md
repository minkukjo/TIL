# Factory

new 연산은 새로운 인스턴스를 만드는 것이다.

만약 피자라는 인터페이스가 있고 이를 구현하는 다양한 타입의 피자가 있다고 해보자.

```java

class PizzaStore{
  Pizza pizza;

  Pizza orderPizza(String type){
      if(type.equal("combination")){
        pizza = new CombinationPizza();
      }else if(type.equal("bulgogi")){
        pizza = new BulgogiPizza();
      }else if(type.equal("pineapple")){
      // 우욱.. 파인애플 피자라니..
        pizza = new PineapplePizza();
      }
      pizza.prepare();
      pizza.bake();
      pizza.cut();
      pizza.box();
      return pizza;
  }
}
```

Pizza의 인터페이스를 구현하는 다양한 클래스의 인스턴스를 만듦으로써 객체지향의 다형성을 표현해보았다.

문제는 위의 객체 생성 과정에 새로운 피자 종류가 생긴다면 추가를 해주어야 하기 때문에 상당히 번거롭다.

디자인 원칙에도 **바뀔 수 있는 부분을 찾아내서 바뀌지 않는 부분과 분리하라**는 원칙에도 위배된다.

이를 위해 Factory Pattern이 등장하게 되었는데, 사실 간단한 Factory Pattern은 디자인패턴이라고 하기 보다는 관용구에 가깝다고 할 수 있다.

때문에 피자 객체를 생성을 전담하는 PizzaFactory 클래스를 만들어서 이를 극복해보자.

```java
class PizzaFactory{
  public Pizza createPizza(String type){
    if(type.equal("combination")){
        pizza = new CombinationPizza();
      }else if(type.equal("bulgogi")){
        pizza = new BulgogiPizza();
      }else if(type.equal("pineapple")){
      // 우욱.. 파인애플 피자라니..
        pizza = new PineapplePizza();
      }
  }
}
```

```java
class PizzaStore{
  PizzaFactory pizzaFactory;

  PizzaStore(PizzaFactory pizzaFactory){
    this.pizzaFactory = pizzaFactory;
  }

  Pizza orderPizza(String type){
      Pizza pizza;
      pizza = pizzaFactory.createPizza(type);
      pizza.prepare();
      pizza.bake();
      pizza.cut();
      pizza.box();
      return pizza;
  }
}
```

위와 같이 수정함으로써 객체 생성의 역할을 PizzaFactory에서 담당하게 하였다.

단순한 Factory의 경우 디자인 패턴 보다는 관용어에 가깝다.

객체 생성에 관한 부분을 PizzaFactory 클래스가 담당하게 되면서, 변경이 발생했을 때 PizzaFactory 클래스만 변경하면 되기 때문에 유지보수성이 올라간다는 장점이 있다.

또한 변하는 부분과 변하지 않는 부분을 나눠야 한다는 디자인 원칙에도 부합하는 디자인 패턴이다.