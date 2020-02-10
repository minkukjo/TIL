# Singleton

싱글톤 패턴은 어떤 클래스를 최초 한번만 메모리를 할당한 후 사용하는 디자인 패턴이다.

즉 생성자가 여러차례 호출되더라도 처음에 만든 인스턴스를 넘겨주게 된다.

즉 싱글톤 패턴은 단 하나의 인스턴스를 생성해 사용하는 디자인 패턴이다.

싱글톤 인스턴스는 전역 인스턴스이기 때문에 다른 클래스들이 싱글톤 인스턴스를 공유하기가 쉽다는 장점이 있다.

커넥션풀이나 사용자 설정, 레지스트리 설정 등에 주로 사용된다.

그러나 이러한 편리한 싱글톤에도 문제점이 있다.

만약 하나의 싱글톤 인스턴스가 너무 많은 일을 하게 되거나 많은 데이터를 공유시킬 경우 다른 클래스들의 결합도가 높아지게 되고 이는 OCP(Open Closed Priciple)을 위배하게 된다.

또한 멀티 쓰레딩에 대한 처리를 해주지 않으면 싱글톤 인스턴스 생성을 할 때 인스턴스가 2개 생성되는 문제가 있으므로 반드시 동기화 처리를 해주어야 한다.

## 멀티쓰레딩에서 Thread-Safe 싱글톤 인스턴스를 만드는 방법

### 1

```java
public class ThreadSafeLazyInitialization{
 
    private static ThreadSafeLazyInitialization instance;
 
    private ThreadSafeLazyInitialization(){}
     
    public static synchronized ThreadSafeLazyInitialization getInstance(){
        if(instance == null){
            instance = new ThreadSafeLazyInitialization();
        }
        return instance;
    }
 
}
```
instance를 static 변수로 만든 후 private로 외부에서의 생성을 막았다.

인스턴스를 반환하는 getInstance 메소드에 synchronized 키워드를 사용해 thread-safe하게 만들었다.

하지만 synchronized 특성상 성능저하가 발생하기 때문에 권장하는 방법은 아니다.

### 2

```java
public class ThreadSafeLazyInitialization {
 
    private volatile static ThreadSafeLazyInitialization instance;
 
    private ThreadSafeLazyInitialization(){}
     
    public static ThreadSafeLazyInitialization getInstance(){
        
        if(instance == null){
            synchronized (ThreadSafeLazyInitialization.class) {
                if(instance == null)
                    instance = new ThreadSafeLazyInitialization();
            }
 
        }
        return instance;
    }
}
```
volatile 키워드로 instance 변수를 메인 메모리에 적재한 뒤 메소드에 synchronized 키워드를 붙이는게 아닌, 첫번째 if문으로 instance의 인스턴스 여부를 확인 후 다시 체크할 때 동기화 시켜 인스턴스를 생성하기 때문에 이전 방법보다 성능저하를 완화했다.

### 3

```java
public class Something {
    private Something() {
    }
 
    private static class LazyHolder {
        public static final Something INSTANCE = new Something();
    }
 
    public static Something getInstance() {
        return LazyHolder.INSTANCE;
    }
}
```

JVM의 Class Loader 매커니즘과 Class가 로드되는 시점을 이용한 방법이다.

일반적으로 이 방법을 주로 사용한다.

JVM이 클래스 초기화 과정에서 LazyHolder 클래스 내부에 있는 INSTANCE 객체를 인스턴스화 하기때문에 해당 싱글톤 객체는 자바 어플리케이션이 실행될 때 최초로 생성된 이후 절대 변하지 않을 것이다.(final)

가장 많이 사용하며 일반적인 싱글톤 인스턴스 생성 방법이다.


## Reference

https://jeong-pro.tistory.com/86