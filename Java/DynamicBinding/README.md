# 동적 바인딩

동적 바인딩은 다형성을 사용하여 메소드를 호출할 때 발생하는 현상이다.

동적바인딩은 실행시간(Runtime) 즉, 파일이 실행하는 시점에 결정되며, 정적 바인딩은 컴파일(Compile) 시간에 성격이 결정된다.

```java
class Polymorphism{
  public static void main(String[] args){
    SuperClass sup = new SubClass();

    sup.methodA(); // 동적 바인딩은 Runtime에 결정된다. 여기서는 SubClass의 메소드가 호출된다.

    sup.staticMethodA(); // 정적 바인딩 static method는 컴파일 타임에 결정된다. 즉 SuperClass의 메소드가 호출된다.
  }
}

class SuperClass{
  void methodA() { System.out.println("Super");}
  static void staticMethodA() { System.out.println("Super");}
}

class SubClass extends SuperClass{
  void methodA() { System.out.println("Sub");}
  static void staticMethodA() { System.out.println("Sub");}
}

```

메소드가 instance 메소드인지, static 메소드인지에 따라 달라진다.

static의 경우 정적 바인딩, intance 메소드의 오버라이딩의 경우 Runtime에 결정된다.

## Reference

https://baekjungho.github.io/java-binding/