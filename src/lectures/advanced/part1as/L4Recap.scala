package lectures.advanced.part1as

import scala.annotation.tailrec
object L4Recap extends App {
  val aCondition: Boolean = false
  val aConditionalVal = if (aCondition) 42 else 65
  // instruction vs expressions

  // compilers infers types
  val aCodeBlock = {
    if (aCondition) 54
    56
  }

  // Unit = void
  val theUnit = println("Hello, scala")
  // sub-typing polymorphism
  val aDog: Animal = new Dog
  // method notations
  val aCroc = new Crocodile
  // anonymous classes
  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println("roar!")
  }
  // exceptions
  //  val throwsException = throw new RuntimeException // Nothing
  val aPotenticalFailure = try {
    throw new RuntimeException
  } catch {
    case e: Exception => "caught"
  } finally {
    println("To the logs!@!")
  }
  // functional programming
  val incrementer = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  val anonymousIncrementer = (x: Int) => x + 1
  // for-comprehension
  val pairs = for {
    num <- List(1, 2, 3)
    char <- List('a', 'b', 'c')
  } yield num + "-" + char
  // Scala Collections:
  //    Seq, Array, List, Vector, Map, Tuple
  val aMap = Map(
    "Daniel" -> 789,
    "Jess" -> 555,
  )
  aCroc.eat(aDog)
  //  Postfix Notation: discourage
  // Infix Notation
  aCroc eat aDog
  // """collections""": Option, Try
  val anOption = Some(2)
  // pattern matching
  val x = 2
  val order = x match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => x + "th"
  }
  val bob = Person("Bob", 22)
  val greeting = bob match {
    case Person(n, _) => s"Hi, my name is $n"
    case _ =>
  }

  // packaging and imports

  // functions
  def aFunction(x: Int): Int = x + 1

  incrementer(1)

  // recursion: stack and tail
  @tailrec
  def factorial(n: Int, accumulator: Int): Int =
    if (n <= 0) accumulator
    else factorial(n - 1, n * accumulator)

  List(1, 2, 4).map(anonymousIncrementer) // HOF
  // map, flatMap, filter

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  // generics
  // +A variance and its problems will be attended
  abstract class MyList[+A]

  // object-oriented programming
  class Animal

  class Dog extends Animal

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println("crunch!")
  }

  // case classes
  case class Person(name: String, age: Int)

  // singletons and companions
  object MyList

  println(greeting)
}
