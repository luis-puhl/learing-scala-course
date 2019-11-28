package lectures.part2oop

object L16AbstractDataTypes extends App {
  abstract class Animal {
    def eat: Unit
    val creatureType: String = "wild"
  }

  class Dog(override val creatureType: String = "Canine") extends Animal {
    override def eat: Unit = println("crunch crunch")
  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat"
  }
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"
    override def eat: Unit = println("nom nom nom")
    override def eat(animal: Animal): Unit = println(s"Iqm a $creatureType and I'm eating ${animal.creatureType}.")
  }

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile.eat(dog)

  // traits do not have constructor params
  // multiple traits inherited by one class
  // trait = behavior, abstract class = "thing"
}
