package lectures.part3functionalProgramming

import scala.util.Random

object L47PatternMatching extends App {

  val random = new Random()
  val x = random.nextInt()
  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else"
  }
  println(x)
  println(description)

  // 1. decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)
  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }
  println(greeting)

  // PM on sealed
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greet: String) extends Animal
  val animal: Animal = Dog("Labrador")
  animal match {
    case Dog(breed) => println(s"Matched a $breed dog.")
    case Parrot(greet) => println(s"Matched parrot saying $greet.")
  }

  /*
  Exercise
  simple function to convert Expr to human readable
   */
  trait Expr {
    override def toString: String = {
      def parenthesis(e: Expr): String =
        e match {
          case Sum(i, j) => s"($e)"
          case _ => s"$e"
        }
    this match {
      case Number(i) => s"$i"
      case Sum(i, j) => s"$i + $j"
      case Product(a, b) => s"${parenthesis(a)} * ${parenthesis(b)}"
      case _ => s""
    }
    }
  }
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Product(e1: Expr, e2: Expr) extends Expr
  val expression = Product(Sum(Number(1), Number(10)), Product(Number(2), Number(20)))
  println(expression)
}
