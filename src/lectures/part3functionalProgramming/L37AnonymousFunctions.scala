package lectures.part3functionalProgramming

object L37AnonymousFunctions extends App {
  // anonymous function (LAMBDA - formalized by Alonzo Church 1930s)
  val doubler = (x: Int) => x * 2

  val doublerExplicit: Int => Int = (x: Int) => x * 2

  // multiple params
  val additive: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // function itself (ref)
  println(justDoSomething()) // function call

  // curly braces with lambdas
  val strToInt = { (str: String) =>
    str.toInt
  }

  // moar sugar
  val niceIncrementer: Int => Int = _ + 1
  val niceAdder: (Int, Int) => Int = _ + _

  // curried function
  val adder = (x: Int) => (v: Int) => x + v
  val adder3 = adder(3)
  println(adder3(3))
  println(adder(3)(3))
}
