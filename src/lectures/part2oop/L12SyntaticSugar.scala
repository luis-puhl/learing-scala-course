package lectures.part2oop

object L12SyntaticSugar extends App {
  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangsOutWith(p: Person): String = s"${this.name} is hanging out with ${p.name}"
    def +(p: Person): String = this.hangsOutWith(p)
    def unary_! : String =  s"$name: what the heck?!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie."
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  // infix notation = operator notation
  println(mary likes "Inception")

  val tom = new Person("Tom", "Fight Club")
  println(mary.hangsOutWith(tom))
  // operators
  println(mary + tom)

  // prefix notation
  val x = -1
  val y = 1.unary_-
  // "unary_" prefix only works with - + ~ !
  println(!mary)
  println(mary.unary_!)

  // postfix notation
  // only to no arg methods
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply)
  println(mary())
}
