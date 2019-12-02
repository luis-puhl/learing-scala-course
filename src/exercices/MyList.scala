package exercices

/**
 * Holds int
 * - head == first
 * - tail == remainder
 * - isEmpty
 * - add(int)
 * - toString
 */
abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](x: B): MyList[B]
  def +[B >: A](x: B): MyList[B] = add(x)
  def printElements: String
  override def toString: String = s"[$printElements]"
  def filter(t: A => Boolean): MyList[A]
  def map[B](t: A => B): MyList[B]
  def flatMap[B](t: A => MyList[B]): MyList[B]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[A >: Nothing](x: A): MyList[A] = new Const[A](x, Empty)
  override def printElements: String = ""
  override def filter(t: Nothing => Boolean): MyList[Nothing] = Empty
  override def map[B](t: Nothing => B): MyList[B] = Empty
  override def flatMap[B](t: Nothing => MyList[B]): MyList[B] = Empty
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}
case class Const[+A](headVal: A, tailVal: MyList[A] = Empty) extends MyList[A] {
  override def head: A = headVal
  override def tail: MyList[A] = tailVal
  override def isEmpty: Boolean = false
  override def add[B >: A](x: B): MyList[B] = new Const[B](x, this)
  override def printElements: String =
    if (tailVal.isEmpty) headVal.toString
    else headVal.toString + ", " + tailVal.printElements
  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(headVal)) new Const(headVal, tailVal.filter(predicate))
    else tailVal.filter(predicate)
  override def map[B](transformer: A => B): MyList[B] =
    new Const(transformer(headVal), tailVal.map(transformer))
  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(headVal) ++ tailVal.flatMap(transformer)
  override def ++[B >: A](list: MyList[B]): MyList[B] =
    new Const[B](headVal, tailVal ++ list)
  override def +[B >: A](x: B): Const[B] = new Const[B](x, this)
}

/*
1. Generic trait MyPredicate[T]
2. Generic trait MyTransformer[A, B]
3. MyList:
 - map(transformer) => MyList
 - filter(predicate) => MyList
 - flatMap(transformer from A to MyList[B]) => MyList[B]
 */
// trait MyPredicate[-T] {
//   def test(t: T): Boolean
// }
// trait MyTransformer[-A, B] {
//   def transform(a: A): B
// }

// class StringToIntTransformer extends MyTransformer[String, Int] {
//   override def transform(a: String): Int = a.trim.toInt
// }
// class NplusOneFlatMap extends MyTransformer[Int, MyList[Int]] {
//   override def transform(a: Int): MyList[Int] =
//     new Const[Int](a, new Const[Int](a+1))
// }

object ListTest extends App {
  def test[A](l: MyList[A]) = {
    try println(l.head) catch {
      case _: Throwable => println("no head")
    }
    try println(l.tail) catch {
      case _: Throwable => println("no tail")
    }
    println(l.isEmpty)
    println(l.toString)
  }
  println("Testing empty")
  test(Empty)
  println("Testing 1")
  val l1 = new Const(1)
  test(l1)

  println("Testing 2")
  val l2 = new Const(2, l1)
  test(l2)
  test(l2 add 3 add 4)

  println("Testing 3")
  val l3 = new Const("")
  test(l3)
  val l4 = l3 add "a" add "b"
  test(l4)
  val l5 = l4.add(10).add(20)
  test(l5)

  val ints: MyList[Int] = new Const(1).add(2).add(3)
  println("Testing 4 Filter")
  println(ints.toString + " => " + ints.filter(new Function1[Int, Boolean] {
    override def apply(t: Int): Boolean = t % 2 == 0
  }).toString)
  println(ints.toString + " => " + ints.filter((t: Int) => t % 2 == 0).toString)

  println("Testing 5 Map")
  val strs: MyList[String] = new Const("  1  ").add("  2  ").add("  3  ")
//  println(strs.toString + " => " + strs.map(new StringToIntTransformer).toString)
  println(ints.toString + " => " + ints.map[Int]((t: Int) => t * 2).toString)

  println("Testing 6 Flat Map")
//  println(ints.toString + " => " + ints.flatMap(new NplusOneFlatMap).toString)
  println(ints.toString + " => " + ints.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(a: Int): MyList[Int] = new Const[Int](a, new Const[Int](a))
  }).toString)

  println("Testing 7 concat with ++")
  println(ints.toString + " => " + (ints ++ strs).toString)

  val otherInts: Const[Int] = Const(1) + 2 + 3 + 4
  // val cloned = otherInts.clone()
  // println(cloned)
}

