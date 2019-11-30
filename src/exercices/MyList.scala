package exercices

import scala.runtime.Nothing$

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
  def printElements: String
  override def toString: String = s"[$printElements]"
  def filter(t: MyPredicate[A]): MyList[A]
  def map[B](t: MyTransformer[A, B]): MyList[B]
  def flatMap[B](t: MyTransformer[A, MyList[B]]): MyList[B]
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[A >: Nothing](x: A): MyList[A] = new Const[A](x, Empty)
  override def printElements: String = ""
  override def filter(t: MyPredicate[Nothing]): MyList[Nothing] = Empty
  override def map[B](t: MyTransformer[Nothing, B]): MyList[B] = Empty
  override def flatMap[B](t: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
}
class Const[+A](headVal: A, val tailVal: MyList[A] = Empty) extends MyList[A] {
  override def head: A = headVal
  override def tail: MyList[A] = tailVal
  override def isEmpty: Boolean = false
  override def add[B >: A](x: B): MyList[B] = new Const[B](x, this)
  override def printElements: String =
    if (tailVal.isEmpty) headVal.toString
    else tailVal.printElements + ", " + headVal.toString
  override def filter(t: MyPredicate[A]): MyList[A] =
    if (t.test(headVal)) new Const[A](headVal, tailVal.filter(t))
    else tailVal.filter(t)
  override def map[B](t: MyTransformer[A, B]): MyList[B] =
    new Const[B](t.transform(headVal), tailVal.map(t))
  override def flatMap[B](t: MyTransformer[A, MyList[B]]): MyList[B] = {
    val ntail: MyList[B] = tailVal.flatMap(t)
    val nhead: MyList[B] = t.transform(headVal)
    def aux(tail: MyList[B], head: MyList[B]): MyList[B] = {
      val ntail = tail.add(head.head)
      if (head.tail.isEmpty) ntail
      else aux(ntail, head.tail)
    }
    aux(ntail, nhead)
  }
}

/*
1. Generic trait MyPredicate[T]
2. Generic trait MyTransformer[A, B]
3. MyList:
 - map(transformer) => MyList
 - filter(predicate) => MyList
 - flatMap(transformer from A to MyList[B]) => MyList[B]
 */
trait MyPredicate[-T] {
  def test(t: T): Boolean = ???
}
trait MyTransformer[-A, B] {
  def transform(a: A): B = ???
}

class EvenPredicate extends MyPredicate[Int] {
  override def test(t: Int): Boolean = t % 2 == 0
}
class StringToIntTransformer extends MyTransformer[String, Int] {
  override def transform(a: String): Int = a.trim.toInt
}
class NplusOneFlatMap extends MyTransformer[Int, MyList[Int]] {
  override def transform(a: Int): MyList[Int] =
    new Const[Int](a, new Const[Int](a+1))
}

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
  println(ints.toString + " => " + ints.filter(new EvenPredicate).toString)

  println("Testing 5 Map")
  val strs: MyList[String] = new Const("  1  ").add("  2  ").add("  3  ")
  println(strs.toString + " => " + strs.map(new StringToIntTransformer).toString)

  println("Testing 6 Flat Map")
  println(ints.toString + " => " + ints.flatMap(new NplusOneFlatMap).toString)
  println(ints.toString + " => " + ints.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(a: Int): MyList[Int] = new Const[Int](a, new Const[Int](a))
  }).toString)
}

