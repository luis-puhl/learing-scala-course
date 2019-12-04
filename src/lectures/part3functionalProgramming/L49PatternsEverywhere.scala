package lectures.part3functionalProgramming

object L49PatternsEverywhere extends App {
  val bigIdeia1 = try {
    //code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "else"
  }
  // catches are actually MATCHES

  val list = List(1, 3, 4, 6)
  val bigIdeia2 = for {
    x <- list if x % 2 == 0
  } yield 10 * x
  // generator are based on pattern matching
  val tuples = List((1, 3), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // case classes, :: operators, ...

  val tuple = (1,2,4)
  val (a,b,c) = tuple
  // multi value definition based on pattern matching
  val head :: tail = list
  println(head)
  println(tail)

  val bigIdeia4 = list.map {
    case v if v %2==0 => v + " is even"
    case 1 => "the one"
    case _ => "else"
  }
  // partial function
  val partialFuncExplicit = list.map {x => x match {
    case v if v %2==0 => v + " is even"
    case 1 => "the one"
    case _ => "else"
  }}

}

