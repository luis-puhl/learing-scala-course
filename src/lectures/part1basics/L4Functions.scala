package lectures.part1basics

object L4Functions extends App {
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
  def greet(name: String, age: Int): String = s"Hi, my name is $name and I am $age years old"
  println("greet", greet("David", 12))

  def factorial(n: Int): Int = if (n > 1) n * factorial(n - 1) else 1
  println("factorial(5)", factorial(5))

  def fibonacci(n: Int): Int = if (n == 1 || n == 2) 1 else if (n < 1) 0 else fibonacci(n -1) + fibonacci(n -2)
  println("fibonacci(8)", fibonacci(8))

  def isPrime(n: BigInt): Boolean = {
    def isDivisible(a: BigInt, b: BigInt): Boolean = a % b == 0
    def isSubPrime(a: BigInt, b: BigInt): Boolean = if (a < b*b) true else if (isDivisible(a, b)) false else isSubPrime(a, b+1)
    if (n <= 1) false else isSubPrime(n, 2)
  }
  def testPrime(n: Int): Unit = {
    println(n + " => " + isPrime(n))
    if (n > 1) testPrime(n-1)
  };
  testPrime(20)
  // too big
  // val belfegor: BigInt =  BigInt("1000000000000066600000000000001")
  def outTest(i: BigInt): Unit = println(i + " => " + isPrime(i))
  def plusOneTest(i: BigInt): Unit = {
    outTest(i)
    outTest(i+1)
  }
  plusOneTest(62219L)
  plusOneTest(104729L)
  plusOneTest(5915587277L)
  plusOneTest(100000000003L)
  plusOneTest(1000000000537L)
}
