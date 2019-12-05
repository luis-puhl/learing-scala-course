package lectures.beginners.part1basics

object L2ValuesVariablesTypes extends App {
  val x: Int = 42
  println(x)

  // x = 2 // !error
  // vals are immutable

  val y = 42
  // types can be inferred

  val text: String = "Hello";
  // instruction separator ";" OR "newline". former is preferred.
  val isGood: Boolean = true
  val key: Char = 'c'
  val short2Bytes: Short = 2
  val int4Bytes: Int = 1324123
  val long8Bytes: Long =  121231231324L
  val float: Float = 2.0f
  val duble: Double = 3.14

  // variables
  var aVar: Int = 10
  aVar = 20
  // side effects
}
