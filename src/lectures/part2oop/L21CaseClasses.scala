package lectures.part2oop

object L21CaseClasses extends App {
  /*
  class with object companion
  equals, hashCode, toString
   */
  case class Person(name: String, age: Int)

  // 1. class params are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  println(jim)

  // 3. equals and hashcode implemented OOTB (out of the box)
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)
  println(jim.hashCode())

  // 4. copy method, with args
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23) // delegated factory

  // 6. are serializable
  // eg Akka lib

  // 7. case classes have extractor methods, thus can be used in PATTERN MATCHING

  // 8. case object (one singleton, same features)
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
  exercise: add case classes/objects to MyList
   */
}
