package lectures.part1basics

object L3Functions extends App {
  def aFunction(a: String, b: Int): String =
    a + " " + b
  // def is a single expression
  def aFunction2(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("Hello", 1))

  def aParamaterlessFunction(): Int = 42
  println(aParamaterlessFunction())
  println(aParamaterlessFunction)

  /** When you need loops, use recursions */
  def aRepeteadFunction(a: String, n: Int): String = {
    if (n==1) a
    else a + aRepeteadFunction(a, n -1)
  }
  println(aRepeteadFunction("-", 80))

  def aFuncWithSideEffects(a: String): Unit = println(a)

  def bigFunc(n: Int): Int = {
    def aSmallFunc(a: Int, b: Int): Int = a + b
    aSmallFunc(n, n-1)
  }

  /**
   * Construct:
   * 1. A greeting func (name, age) => "Hi, my name is $name and I am $age years old"
   * 2. Factorial func
   * 3. Fibonacci func
   * 4. is prime number func
   */
}
