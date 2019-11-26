package lectures.part3functionalProgramming

object WhatIsAFunction extends App {
  val doubler = new Action[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(10))

  val str2int = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(str2int("10") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  println(adder(2, 2))

  /*
    1. a function takes 2 str and concatenates
    2. transform MyPredicate and MyTransformer into function types
    3. define a function that takes an int and returns a function
      3.a. define the type
  */

  val conc = (v1: String, v2: String) => v1.concat(v2)
  println(conc("Hello ", "Functions"))

  val highOrderExplicit: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(amount : Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(value: Int): Int = amount + value
    }
  }
  val highOrder: (Int) => ((Int) => Int) = (amount: Int) => (value: Int) => value + amount

  val adder3 = highOrder(3)
  println(adder3(3))
  // curried function
  println(highOrderExplicit(3)(3))

}

trait Action[A, B] {
  def apply(element: A): B
}