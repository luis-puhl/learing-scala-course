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

/*
Novel and Writer

Writer: first name, surname, year
 - fullname

Novel: name, year of release, author
 - authorAge
 - isWrittenBy
 - copy (new year of release) = new instance of Novel
 */
class Writer(val name: String, val surname: String, val year: Int) {
  def fullName(): String = s"${surname.toUpperCase()}, ${name.take(1)}"
}
class Novel(val name: String, val author: Writer, val yearOfRelease: Int) {
  def authorAnge(): Int = author.year - yearOfRelease
  def isWrittenBy(author: Writer): Boolean = this.author == author
  def copy(yearOfRelease: Int): Novel = new Novel(name, author, yearOfRelease)
}

/*
Counter class
 - receives an int value
 - method current count
 - method to increment/decrement => new Counter
 - overload inc/dec to receive an amount
 */
class Counter(val value: Int = 0) {
  def current(): Int = value
  def inc(i: Int = 1) = new Counter(value + i)
  def dec(i: Int = 1) = new Counter(value - i)
}