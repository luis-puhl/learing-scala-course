package lectures.part2oop

object L10ObjectOrientedBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  person.greet("Dave")
  person.greet()
}

// constructor (name: String, age: Int)
// class parameters ar not fields
class Person(name: String, val age: Int) {
  // body
  // runs at instantiation
  val x = 2

  println(1 + 3)

  // overloaded methods
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name.")
  def greet(): Unit = println(s"Hi, I am $name.")

  // overloaded constructors
  // not really useful as it can only be defined in terms of other constructor
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}