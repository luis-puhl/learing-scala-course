package lectures.beginners.part2oop

import exercices.{Const, Empty => EmptyList}

object L23PackagingImports extends App {

  //
  val writer = new Writer("Daniel", "RockTheJVM", 2019)

  // FQ = Fully Qualified
  val p = exercices.Const
  // grouping import exercices.{Empty, Const}
  val q = Const
  val r = EmptyList
  // aliasing
  val s = EmptyList

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // default imports
  // - java.lang: String, Object, Exception
  // - scala: Int Nothing, Function
  // - scala.Predf: print
}
