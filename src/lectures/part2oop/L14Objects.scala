package lectures.part2oop

object L14Objects extends App {
  // scala does not have "static", it has objects (singleton
  object Person {
    val N_EYES = 2
    def canFly: Boolean = false

    // factory
    def apply(mother: Person, father: Person): Person = new Person(mother.name.split(" ").last + " " + father.name.split(" ").last)
  }
  class Person(val name: String) {
    // instance-level func
  }
  // companions (same scope and same name)

  println(Person.N_EYES)
  println(Person.canFly)

  val a = Person
  val b = Person
  println(a == b)

  val mary = new Person("Mary Woonka")
  val john = new Person("John Wallace")
  println(mary == john)

  val son = Person(mary, john)
  println(son.name)
}
