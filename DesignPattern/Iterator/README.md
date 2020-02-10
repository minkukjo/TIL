# Iterator

Iterator 패턴은 반복작업을 캡슐화 하는 패턴이다.

Java의 컬렉션 프레임워크를 순회할 때 Iterator를 제공하는데, 이 Iterator가 Iterator 디자인 패턴을 사용하여 만들어졌다.

만약 ArrayList를 순회하는 코드가 있다고 해보자.

```java

ArrayList<Integer> arr = new ArrayList<>();
arr.add(1);
arr.add(2);
arr.add(3);

for(int i=0; i<arr.size(); i++){
  arr.get(i);
}
```

위 코드는 arr이라는 ArrayList를 순회하는 코드이다.

ArrayList라는 컬렉션 프레임워크를 사용하기 위해서 우리는 arr의 size를 알기 위한 size() 메소드를 알아야 하며, 해당 리스트 인덱스에 접근하기 위해 get() 메소드를 알아야 한다.

즉, 컬렉션 순회를 위해서는 해당 컬렉션의 기능을 반드시 알아야 한다.

Iterator 패턴은 이러한 컬렉션 프레임워크에서 반복적으로 해당 자원에 접근하는 기능을 패턴화 해놓은 것이다.

모든 컬렉션 프레임워크의 최상위 인터페이스인 **Collection<E>** 인터페이스는 **Iterable<E>** 인터페이스를 상속받고 있다.

때문에 컬렉션 인터페이스를 상속받아 구현된 모든 컬렉션 프레임워크는 Iterator를 이용해 순회가 가능하다.

컬렉션 프레임워크는 Iterator 인터페이스를 구현하고 있는 Iterator를 반환하는 각자의 Iterator() 메소드를 갖고 있다.

List 인터페이스를 구현하는 ArrayList의 경우 Iterator() 메소드의 반환 값은 **ListIterator<E>**이다.

Iterator를 활용하여 다음과 같이 컬렉션을 순회할 수 있다.

```java

ArrayList<Integer> arr = new ArrayList<>();
arr.add(1);
arr.add(2);
arr.add(3);
for(Iterator<Integer> iter = arr.iterator(); iter.hasnext()){
  int temp = iter.next();
  System.out.println(temp);
}
```

```java

ArrayList<Integer> arr = new ArrayList<>();
arr.add(1);
arr.add(2);
arr.add(3);
for(int a : arr){
  System.out.println(a);
}
```

java의 for-each 또한 내부적으로 iterator를 사용한다.
때문에 Iterable 인터페이스가 구현되지 않으면 사용할 수 없다.


## Reference

https://gdtbgl93.tistory.com/143