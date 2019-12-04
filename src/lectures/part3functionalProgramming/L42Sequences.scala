package lectures.part3functionalProgramming

import scala.util.Random

object L42Sequences extends App {
  // seq
  val aSeq = Seq(1, 2, 3, 4)
  println(aSeq)
  println(aSeq.reverse)
  println(aSeq(2))
  println(aSeq ++ Seq(7, 5, 6))
  println(aSeq.sorted)

  // ranges
  val oneTo10: Seq[Int] = 1 to 10
  print("oneTo10 =\t")
  oneTo10.foreach(n => print(s"$n, "))
  print("\n1 until 10 =\t")
  (1 until 10).foreach(n => print(s"$n, "))
  println()

  // lists
  val aList = List(1, 2, 3)
  val prepended = 42 +: aList :+ 89
  println(prepended)

  val apples = List.fill(5)("apple")
  println(apples)
  println(aList.mkString("-.-"))

  // arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements =  Array.ofDim[String](10)
  threeElements.foreach(println)
  // mutation
  numbers(2) = 0
  println(numbers)
  println(numbers.mkString(" "))
  // arrays and seq, implicit conversion
  val numbersSeq: Seq[Int] = numbers
  println(numbersSeq)

  // vectors
  val vect: Vector[Int] = Vector(1,2,3)
  println(vect)

  // vectors vs lists
  val maxRuns = 1000
  val maxCapa = 1000000
  def getWriteTime(col: Seq[Int]): Double = {
    val r = new Random(100666001)
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      col.updated(r.nextInt(maxCapa), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }
  val numbersList = getWriteTime((1 to maxCapa).toList)
  val numbersVect = getWriteTime((1 to maxCapa).toVector)
  println(f"List: $numbersList%e ns")
  println(f"Vect: $numbersVect%e ns")
}
