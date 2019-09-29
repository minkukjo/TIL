fun apply():Person{
    val person = Person().apply {
        name = "MinKuk"
        age = 26
    }
    return person
}
fun also(person: Person){
    person.also {
        println("name: ${it.name} age : ${it.age}")
    }
}

fun applyNullable(person:Person):Person?{
    return person
    //return null
}

fun scopeFunctions(){
    val person = apply()

    also(person)

    val nullablePerson = applyNullable(person)
    applyNullable(person)?.let {
        println("NullablePerson name : ${it.name} NullablePerson age : ${it.age}")
    }

    // person?.run -> Error!!
    person.run {
        age += 1
        println("Person age : $age")
    }

    with(nullablePerson){
        println(nullablePerson?.name)
    }
}

fun main(){
    scopeFunctions()
}