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
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[A >: Nothing](x: A): MyList[A] = new Const[A](x, Empty)
  override def printElements: String = ""
}
class Const[+A](headVal: A, val tailVal: MyList[A] = Empty) extends MyList[A] {
  override def head: A = headVal
  override def tail: MyList[A] = tailVal
  override def isEmpty: Boolean = false
  override def add[B >: A](x: B): MyList[B] = new Const[B](x, this)
  override def printElements: String =
    if (tailVal.isEmpty) headVal.toString
    else tailVal.printElements + ", " + headVal.toString
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
}
