package lectures.beginners.part2oop

object L22Exceptions extends App {
  var x: String = null
  // println(x.length)
  // this will cause a NPE

  // 1. throwing
  // val aWeirdValue: String = throw new NullPointerException

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int 4 u")
    else 42

  val potentialFail = try
    getInt(true)
  catch {
    // case e: NullPointerException => println("Caught NullPointerException")
    // case e: RuntimeException => println("Caught RuntimeException")
    case e: RuntimeException => 43
  } finally  {
    println("finally")
    // this bloc does not influence the expression value
    // use it to do side-effects, like log
  }
  println(potentialFail)

  // 3. custom exceptions
  class MyException extends Exception
  val exception = new Exception
  // throw exception

  def crashWithOutOfMemoryError(i: Int = 0, v: => Array[Int] = new Array[Int](Int.MaxValue)): Int = {
    crashWithOutOfMemoryError(i+1, v)
    println(i)
    i
  }
  //  crashWithOutOfMemoryError()
  //  Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
  // at lectures.beginners.part2oop.L22Exceptions$.crashWithOutOfMemoryError$default$2(L22Exceptions.scala:33)

  // OOM
  // val arr = Array.ofDim(Int.MaxValue)

  // SO
  def infinite: Int = 1 + infinite
  // val noLim = infinite

  def crashWithSOError(): Int = crashWithOutOfMemoryError(0, new Array[Int](0))

  /*
  crashWithSOError()
  Exception in thread "main" java.lang.StackOverflowError
  at lectures.beginners.part2oop.L22Exceptions$.crashWithOutOfMemoryError(L22Exceptions.scala:34)
   */

  object OverflowException extends RuntimeException
  object UnderflowException extends RuntimeException
  object MathCalculationException extends RuntimeException
  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw OverflowException
      else if (x < 0 && y < 0 && result > 0) throw UnderflowException
      else result
    }
    def subtract(x: Int, y: Int): Int = add(x, -y)
    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x == 0 || y == 0) 0
      else if (x >= 0 && y >= 0 && result <= 0) throw OverflowException
      else if (x <= 0 && y <= 0 && result <= 0) throw OverflowException
      else if ((x <= 0 || y <= 0) && result >= 0) throw UnderflowException
      else result
    }
    def divide(x: Int, y: Int): Int = if (y == 0) throw MathCalculationException else x /  y
  }
  def safe(v: => Int) = try v catch {
    case e: Exception => e.getClass
  }
  println("add(2, 2)) =>               " + safe(PocketCalculator.add(2, 2)))
  println("add(2, Int.MaxValue =>      " + safe(PocketCalculator.add(2, Int.MaxValue)))

  println("subtract(2, 2)) =>          " + safe(PocketCalculator.subtract(2, 2)))
  println("subtract(-2, Int.MaxValue => " + safe(PocketCalculator.subtract(Int.MinValue, 2)))

  println("multiply(2, 2)) =>          " + safe(PocketCalculator.multiply(2, 2)))
  println("multiply(2, Int.MaxValue => " + safe(PocketCalculator.multiply(2, Int.MaxValue)))
  println("multiply(2, Int.MinValue => " + safe(PocketCalculator.multiply(2, Int.MinValue)))

  println("divide(2, 2)) =>            " + safe(PocketCalculator.divide(2, 2)))
  println("divide(1, 0)) =>            " + safe(PocketCalculator.divide(1, 0)))
}

