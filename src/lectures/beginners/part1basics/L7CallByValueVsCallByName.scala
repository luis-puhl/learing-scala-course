package lectures.beginners.part1basics

object L7CallByValueVsCallByName extends App {
  def byValue(x: Long): Unit = {
    println(s"by value: $x")
    println(s"by value: $x")
  }

  def byName(x: => Long): Unit = {
    println(s"by name: $x")
    println(s"by name: $x")
  }
  // prints 2x same time
  byValue(System.nanoTime())
  // prints 2 different values
  byName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def print1s(x: Int, y: => Int): Unit = println(x)

  // breaks, infinite call
//  print1s(infinite(), 12)

  // does not break because the y is eval is delayed
  // and as y is not used, infinite is never called
  print1s(12, infinite())

  def manyUses(x: => Int) = x + x
  def sideEffectInt(x: Int) = {
    println(s"sideEffectInt $x")
    x
  }
  manyUses(sideEffectInt(10))
}
