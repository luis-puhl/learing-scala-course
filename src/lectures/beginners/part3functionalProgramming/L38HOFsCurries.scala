package lectures.beginners.part3functionalProgramming

object L38HOFsCurries extends App {
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // higher order function (HOF)

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  def nTimesExtraHOF(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesExtraHOF(f, n-1)(f(x))

  val plus10 = nTimesExtraHOF(plusOne, 10)
  println(plus10(1))

  val printStack = (x: Int) => {
    println("Strack size: " + Thread.currentThread().getStackTrace().size)
    x + 1
  }
  println(nTimesExtraHOF(printStack, 10)(1))

  // curried functions
  // see nTimes

  // functions with multiple params lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)
  val stdFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")
  println(stdFormat(Math.PI))
  println(preciseFormat(Math.PI))

  def toCurry[T](f: (T, T) => T): T => T => T =
    (v1: T) => (v2: T) => f(v1, v2)
  def fromCurry[T](f: T => T => T): (T, T) => T =
    (v1: T, v2: T) => f(v1)(v2)

  val f = (x: Int, y: Int) => x + y
  println("\n\tto/from curry test")
  println("base: " + f(1, 2))
  println("toCurry: " + toCurry[Int](f)(1)(2))
  println("fromCurry: " + fromCurry[Int](toCurry[Int](f))(1, 2))

  println("\n\tCompose test")
  def compose[T](f: T=>T, g: T=>T): T=>T = (x: T) => f(g(x))
  def andThen[T](f: T=>T, g: T=>T): T=>T = (x: T) => g(f(x))
  val f1 = (x: Int) => x +2
  val f2 = (x: Int) => x *2
  println("f1(2) : " + f1(2))
  println("f2(2) : " + f2(2))
  println("fog(2) : " + compose(f1, f2)(2))
  println("gof(2) : " + andThen(f1, f2)(2))
}
