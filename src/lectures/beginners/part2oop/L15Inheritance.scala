package lectures.beginners.part2oop

object L15Inheritance extends App {

  sealed class Animal {
    def eat = println("nomnomnom")

    private val filo = "filo"
    protected val species = "species"
    val creatureType = "wild"
    /* preventing overrides:
      - final val
      - final class
      - sealed class (allows for override in this source file only)
     */
    final val fin = 10
  }
  // single class inheritance
  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }
  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int)
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    override def eat: Unit = println("crunch crunch")
    def feast: Unit = {
      super.eat
      println("crunch crunch")
    }
    // override val creatureType: String = "domestic"
    // override val creatureType: String = creatureType
  }
  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("k9")
  unknownAnimal.eat

  // overRIDING vs overLOADING
}
