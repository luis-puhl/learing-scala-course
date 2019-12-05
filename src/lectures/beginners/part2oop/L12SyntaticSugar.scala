package lectures.beginners.part2oop

object L12SyntaticSugar extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 20) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangsOutWith(p: Person): String = s"${this.name} is hanging out with ${p.name}"

    def +(p: Person): String = this.hangsOutWith(p)

    def +(s: String): Person = new Person(s"$name ($s)", favoriteMovie)

    def unary_! : String = s"$name: what the heck?!"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie."
    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
    def unary_+ : Person = new Person(name, favoriteMovie, age+1)
    def learns(what: String): String = f"$name learns $what"
    def learnsScala: String = learns("Scala")
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

  /*
  1. Overload the + operator
      mary + "the rockstar" => new person "Mary (the rockstar)"
   */
  println((mary + "movie lover").apply())

  /*
    2. Add an age to person class
     - unary operator + => new person with age+1
     */
  val maryb = +mary
  println(maryb.age)

  /*
  3. Add learns method, returns "Mary learns Scala"
   - add overload with Scala use with postfix
   */
  println(mary.learns("java"))
  println(mary learns "js")
  println(mary learnsScala)

  /*
  4. Overload the apply method
    - mary(2) => "Mary watched inception 2 times"
   */
  println(maryb(10))
}
