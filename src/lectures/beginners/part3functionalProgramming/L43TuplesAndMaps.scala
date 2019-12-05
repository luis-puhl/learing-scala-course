package lectures.beginners.part3functionalProgramming

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

  /*
    1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900
    if you map to upper or lower case keys, they overlap and delete.
   */

  /*
    2. Overly simplified social network based on maps (graph)
      person = String
      - add person
      - remove
      - friend
      - unFriend
      stats
      - number of friends of a person
      - person with most friends
      - how many people have NO friends
      - if there is a social connection between 2 people (direct or not)
   */
  type Network = Map[String, Set[String]]
  def add(network: Network, person: String): Network =
    network + (person -> Set())
  def friend(network: Network, a: String, b: String): Network =
    network + (a -> network(a).+(b)) + (b -> network(b).+(a))
  def unFriend(network: Network, a: String, b: String): Network =
    network + (a -> network(a).-(b)) + (b -> network(b).-(a))
  def remove(network: Network, person: String): Network =
    network(person)
      .map(b => b -> network(b).-(person))
      .foldLeft[Network](network)((n, t) => n.+(t._1 -> t._2))
      .-(person)

  //  List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  var n: Network = Map[String, Set[String]]()
  n = add(n, "Angela")
  n = add(n, "Bob")
  n = add(n, "James")
  n = add(n, "Mary")
  n = add(n, "Daniel")
  n = add(n, "Jim")
  n = add(n, "Nolan")
  println(n)
  n = friend(n, "Angela", "Bob")
  n = friend(n, "Angela", "James")
  n = friend(n, "Angela", "Mary")
  n = friend(n, "Bob", "Mary")
  n = friend(n, "Bob", "Daniel")
  n = friend(n, "Jim", "Daniel")
  println(n)

  // number of friends of a person
  def nFriends(n: Network, person: String): Int =
    if (n.contains(person)) n(person).size
    else 0
  println(nFriends(n, "Bob"))
  // person with most friends
  def mostFriends(n: Network): String =
    n.maxBy(pair => pair._2.size)._1
  println("mostFriends " + mostFriends(n))
  // how many people have NO friends
  def nPeopleNoFriends(network: Network): Int =
    n.count(_._2.isEmpty)
  println("nPeopleNoFriends " + nPeopleNoFriends(n))
  // if there is a social connection between 2 people (direct or not)
  def hasConnection(network: Network, a: String, b: String): Boolean = {
    @scala.annotation.tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean =
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople ++ network(person))
      }
    if (!network.contains(a) || !network.contains(b)) false
    else bfs(b, Set(), network(a) + a)
  }

  println("hasConnection(n, Bob, Mary) " + hasConnection(n, "Bob", "Mary"))
  n = unFriend(n, "Bob", "Mary")
  println("unFriend(n, Bob, Mary)")
  println(n)
  println("hasConnection(n, Bob, Mary) " + hasConnection(n, "Bob", "Mary"))
  n = remove(n, "Angela")
  println("remove(n, Angela)")
  println(n)
}
