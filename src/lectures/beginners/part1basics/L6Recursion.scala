package lectures.beginners.part1basics

import scala.annotation.tailrec

object L6Recursion extends App {
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println(s"enter factorial $n")
      val result = n * factorial(n - 1)
      println(s"out $n")
      result
    }
  }
  // factorial(5000)
  // fails


  def factorial2(n: BigInt): BigInt = {
    @tailrec
    def aux(x: BigInt, acc: BigInt): BigInt =
      if (x <=1 ) acc
      else aux(x-1, acc * x)
    // as the recursion call is the last, it's TAIL RECURSIVE
    // this saves the stack-frame, doesn't overflow the stack
    aux(n, 1)
  }
  println(factorial2(5000))

  /**
   * 1. Concatenate a String n times
   * 2. isPrime function tail recursive
   * 3. Fibonacci func, tail recursive
   */


  @tailrec
  def concat(s: String, n: Int, acc: String = ""): String = {
    if (n <= 1) acc
    else concat(s, n - 1, acc + s)
  }
  println(concat("-", 80))

  @tailrec
  def isPrime(n: BigInt, curr: BigInt = 2): Boolean =
    if ( n <= 1) false
    else if (n <= 3 && n > 1) true
    else if (n % curr == 0) false
    else if (curr*curr > n) true
    else isPrime(n, curr+1)
  println(isPrime(1000000000537L))
  println(isPrime(1000000000538L))
  println(isPrime(2003L))
  println(isPrime(629L))
  (0 to 20).foreach(i => println(i + "\t" + isPrime(i)))
  val prime0to100 = (0 to 100).map(i => (i, isPrime(i), 1)).count(t => t._2)
  println(prime0to100 + " = 25")
  println((0 to 1000).map(i => (i, isPrime(i))).count(t => t._2) + " = 168")


  def isPrime2(n: BigInt): Boolean = {
    @tailrec
    def aux(t: BigInt, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (n < 1) false
      else if (t <= 1) true
      else aux(t -1, n % t !=0 && isStillPrime)
    aux(n/2, true)
  }
  println("isPrime2")
  println((1 to 1000).map(i => (i, isPrime2(i))).count(t => t._2) + " = 168")

  @tailrec
  def isPrime3(n: BigInt, t: BigInt = 2, acc: Boolean = true): Boolean =
    if (!acc) false
    else if (n < 1) false
    else if (n <= t*t) true
    else isPrime3(n, t+1, n % t !=0 && acc)
  println("isPrime3")
  println((1 to 1000).map(i => (i, isPrime3(i))).count(t => t._2) + " = 168")

  def fib(n: BigInt): BigInt = {
    @tailrec
    def aux(i: BigInt, last: BigInt, nextToLast: BigInt): BigInt =
      if (i >= n) last
      else aux(i + 1, last + nextToLast, last)
    if (n <= 2) 1
    else aux(2, 1, 1)
  }
  println(fib(8) + " == 21")
  /*
  n=8
  i, 1, 2
  2, 1, 1
  3, 2, 1
  4, 3, 2
  5, 5,

   */

  // 1  1  2  3 5 8 13
  // n n1 n2
  //   n  n1 n2
  def fil(n: BigInt, iter: BigInt = 2, n1: BigInt = 1, n2: BigInt = 1): BigInt =
    if (n <= 2) 1
    else if (iter >= n) n1
    else fil(n, iter +1, n1 + n2, n1)
  println(fil(8) + " == 21")
}
