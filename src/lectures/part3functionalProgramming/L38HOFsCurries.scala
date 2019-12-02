package lectures.part3functionalProgramming

object L38HOFsCurries extends App {
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // higher order function (HOF)

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

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
}
