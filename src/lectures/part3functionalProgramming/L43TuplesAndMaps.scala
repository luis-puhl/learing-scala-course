package lectures.part3functionalProgramming

object L43TuplesAndMaps extends App {
  val tuples = (1, "Hello, Scala")
  val tuplesExplicit: (Int, String) = Tuple2[Int, String](1, "Hello, Scala")
  println(tuples)
  println(tuples._1)
  println(tuples.copy(_2 = "goodbye Java"))
  println(tuples.swap)

  val aMap: Map[String, Int] = Map()
  println({
    try aMap("some")
    catch {
      case e: Exception => e
    }
  })
  val phoneBook: Map[String, Int] = Map(("Jim", 555), "Daniel" -> 789).withDefault(_ => -1)
  // a -> b is sugar for (a, b)
  println(phoneBook)
  val newPair = "Mary" -> 678
  val newPhoneBook = phoneBook + newPair
  println(newPhoneBook)

  // funcitonal on Maps
  // map, flatMap, filter
  println(newPhoneBook.map(pair => pair._1.toLowerCase -> pair._2))
  // filterKeys
  println(phoneBook.filterKeys(x => x.startsWith("J")))
  // mapValues
  println(phoneBook.mapValues(x => "0245-" + x ))

  // to other collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
}
