package lectures.beginners.part2oop

object L18Generics extends App {

  class MyList[A] {
    // use the type A
    def add(a: A): MyList[A] = ???
  }

  class MyMap[Key, Value]

  val a = new MyList[Int]
  val b = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val c = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // List[Cat] extends List[Animal] ???

  // Covariance
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
//  animalList.add(new Dog)
  // hard question
  // pollutes the typed list

  // Invariance
  class InvariantList[A]
  val invar: InvariantList[Animal] = new InvariantList[Animal]

  // ContraVariance
  class ContravariantList[-A]
  val contra: ContravariantList[Cat] = new ContravariantList[Animal]
  // another example
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // accepts only subclass of animal
  // A is a child of Animal
  class Cage[A <: Animal] (animal: A)
  val cage = new Cage(new Dog)

  class Car
  // generic type needs proper bounded type
  // val cageFail = new Cage(new Car)

  class MyListCovariant[+A] {
    // B is parent of A
    def add[B >: A](a: B): MyList[B] = ???
    /*
    A = Cat
    B = Animal
     */
  }
}
