package lectures.beginners.part1basics

object L8DefaultArgs extends App {
  @scala.annotation.tailrec
  def trFact(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else trFact(n - 1, n * acc)

  val fac10 = trFact(10)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println(s"saving $format $width x $height")

  savePicture(width = 800)
  savePicture(width = 800, height = 100, format = "bmp")
}
