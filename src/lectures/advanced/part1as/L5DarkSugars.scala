package lectures.advanced.part1as

import scala.util.Try

object L5DarkSugars extends App {
  val description = singleArgMethod {
    // complex code
    42
  }
  val aTryInstance = Try {
    // looks like java try { ... }
    throw new RuntimeException
  }
  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }
  List(1, 2, 3).map { x =>
    x + 1
  }
  val anInstance2: Action = (x: Int) => x + 1
  // example: Runnables
  val aTread = new Thread(new Runnable {
    override def run(): Unit = println("Hello, scala")
  })
  val aSweetTread = new Thread(() => println("Hello, scala"))
  val anAbstractInstance: AnAbstractType = (a: Int) => println(s"sweet $a")
  // 3 - :: and #:: methods
  val prependedList = 2 :: List(3, 4)
  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]
  val lilly = new TeenGirl("Lilly")
  val composite: Int Composite String = new Composite[Int, String]
  // 2.::(List(3, 4)) expected
  // List(3, 4).::(2)  actual
  // ':' suffix indicates right-associativity of method
  // left-associative otherwise
  1 :: 2 :: List(3, 4)
  List(3, 4).::(1).::(2)
  val towards: Int --> String = new -->[Int, String]
  // 6 - update() is very special, like apply()
  val anArray = Array(1, 2, 4)

  // 1 - methods with single arg
  def singleArgMethod(arg: Int): String = s"$arg little ducks..."

  // 2 - Single abstract method
  trait Action {
    def act(x: Int): Int
  }

  lilly `and then said` "Scala is sweet"

  abstract class AnAbstractType {
    def implemented: Int = 23

    def f(a: Int): Unit
  }

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this
  }

  // 4 - Multi-word method names
  class TeenGirl(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }

  // 5 - infix types
  class Composite[A, B]

  class -->[A, B]

  anArray(2) = 7
  anArray.update(2, 7)

  // 7 - setters for mutable containers
  class Mutable {
    val aMutableContainer = new Mutable
    // private for OO encapsulation
    private var internalMember: Int = 0

    // getter
    def member: Int = internalMember

    // setter
    def member_=(value: Int): Unit =
      internalMember = value

    aMutableContainer.member = 42
    aMutableContainer.member_=(42)

  }

}
