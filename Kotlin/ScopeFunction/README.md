## What is Scope Functions in Kotlin

코틀린에서는 Scope Functions라고 하는 5개의 기본 함수들을 제공합니다.

이 Scope Functions는 이름 그대로 범위를 지정하는 함수들 입니다.

기본적으로 5개의 함수의 역할이 매우 비슷하기 때문에 지금부터 하나씩 자세히 알아보도록 하겠습니다.

학습에 대한 내용의 코드는 Kotlin 프로젝트 안에 있으니 참고해주시기 바랍니다.

```kotlin
class Person{
    lateinit var name:String
    var age: Int = 0
}
```
Person 객체는 위와같이 선언되어 있습니다.

## Context Object : this와 it

scope function를 설명하기에 앞서 기본 지식이 되는 Context Object가 무엇인지를 먼저 알아보도록 하겠습니다.
scope function의 람다에 내에서 context object는 이름 대신 짧은 참조를 사용할 수 있습니다.
각각의 scope function들은 context object에 접근하기 위해 두가지 방법을 사용합니다.
하나는 lambda receiver인 this와 lambda argument인 it 입니다.
두가지 모두 같은 기능을 제공합니다. 지금부터 it과 this가 무엇이고 언제 쓰이는지 알아봅시다.

## this

```kotlin
fun apply():Person{
    val name = "dragon"
    val age = 300
    val person = Person().apply {
        this.name = "MinKuk"
        this.age = 26
    }
    return person
}
```

this는 run,with,apply는 lambda receiver인 this를 context object로 제공합니다.
일반적으로 this는 클래스의 this와 마찬가지로 생략할 수 있습니다.
하지만 this가 생략되면 scope 범위 밖의 변수들과 헷갈릴 수 있습니다.
그래서 일반적으로 this로 참조할 것을 레퍼런스에서는 권장하고 있습니다.

## it

```kotlin
fun also(person: Person){
    person.also {
        println("name: ${it.name} age : ${it.age}")
    }
}
```

let과 also는 lambda argument로 context object를 갖습니다.
argument의 이름이 주어지지 않으면 자동적으로 it이 됩니다.
it은 this보다 짧고, 읽기 더 쉽게 표현되어질 수 있습니다.
하지만 this처럼 암시적으로 객체의 함수나 프로퍼티를 부를 수 없습니다.
때문에 context object는 그 object가 함수의 argument로 사용되어질때 사용하는 것이 좋습니다.

# Context Object

```kotlin
val numlist = mutableListOf<Double>()
numlist.also { println("List")}
    .apply {
        add(2.71)
        add(3.14)
        add(4.3)
    }
    .also { println("Sorting List")}
    .sort()
```

apply와 also의 반환값은 context object 그 자체입니다.
때문에 위와 같이 동일한 객체에서 계속해서 함수를 호출하는 것 또한 가능합니다.

## lambda result

```kotlin
val numbers = mutableListOf("one", "two", "three")
val countEndsWithE = numbers.run { 
    add("four")
    add("five")
    count { it.endsWith("e") }
}
println("There are $countEndsWithE elements that end with e.")
```

let,run 그리고 with는 lambda result를 반환합니다.
lambda result는 lambda의 마지막 결과를 변수에 할당하거나 결과에 대한 체이닝 작업이 가능합니다.

## Functions

지금부터 Kotlin에서 제공하는 Scope Function들에 대해 알아보도록 하겠습니다.

## apply

```kotlin
fun apply():Person{
    val person = Person().apply {
        name = "MinKuk"
        age = 26
    }
    return person
}
```
처음으로 알아볼 함수는 apply 입니다.
apply는 일반적으로 객체 초기화에 사용되어 집니다.
람다로 받아온 객체를 그대로 반환합니다.

## also

```kotlin
fun also(person: Person){
    person.also {
        println("name: ${it.name} age : ${it.age}")
    }
}
```
also는 apply와 그 기능이 유사합니다.
차이점은 also에서는 it을 람다에서 받기 때문에 구체적인 이름을 지어주는 것이 가능합니다.
코틀린 공식 레퍼런스에선 also를 객체 속성을 바꾸는데 사용하기 보다는 디버그 로그 정보 출력과 같이 context object의 값을 참조하는데 사용하길 권장하고 있습니다.

## let

```kotlin
fun applyNullable(person:Person):Person?{
    return person
    //return null
}
```

```kotlin
    applyNullable(person)?.let {
        println("NullablePerson name : ${it.name} NullablePerson age : ${it.age}")
    }
```

let은 null이 아닌 경우에 특정 코드를 실행할때 주로 사용됩니다.
또한 nullable 객체를 다른 nullable 객체로 변환할때도 사용할 수 있습니다.
왜냐하면 let은 lambda result를 반환하기 때문에 context object와 타입이 다른 객체를 변수에 할당할 수 있습니다.

## run

```kotlin
    person.run {
        age += 1
        println("Person age : $age")
    }
```
run은 일반적으로 람다안에서 객체 초기화와 반환이 함께 이루어지는 경우 유용합니다.
내부적으로 context object는 this로 사용되어집니다.
반환 값은 람다의 결과 입니다.

## with

```kotlin
    with(nullablePerson){
        println(nullablePerson?.name)
    }
```
레퍼런스에서는 람다 결과를 제공하지않고 context object에서 함수를 호출하는 것을 추천하고 있습니다.
즉, with는 "이 context object를 갖고서 이런 이런 행동을 해라"로 이해할 수 있습니다.



# 출처

[Kotlin-Scoping-Fuctions](https://medium.com/@fatihcoskun/kotlin-scoping-functions-apply-vs-with-let-also-run-816e4efb75f5)

[Kotlin-공식-Reperence](https://kotlinlang.org/docs/reference/scope-functions.html#with)

