package lectures.beginners.part2oop

object L19AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahhaahhahhaha")
  }
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHy: Unit = println(s"Hi, my name is $name, how can I help you?")
  }
  val jim = new Person("Jim") {
    override def sayHy: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }
  println(jim.getClass)
  jim.sayHy
}
