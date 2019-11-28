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
abstract class MyList {
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(x: Int): MyList
  def printElements: String
  override def toString: String = s"[$printElements]"
}

object Empty extends MyList {
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(x: Int): MyList = new Const(x, Empty)
  override def printElements: String = ""
}
class Const(val headVal: Int, val tailVal: MyList = Empty) extends MyList {
  override def head: Int = headVal
  override def tail: MyList = tailVal
  override def isEmpty: Boolean = false
  override def add(x: Int): MyList = new Const(x, this)
  override def printElements: String =
    if (tailVal.isEmpty) headVal.toString
    else tailVal.printElements + ", " + headVal.toString
}

object ListTest extends App {
  def test(l: MyList) = {
    try println(l.head) catch {
      case _: Throwable => println("no head")
    }
    try println(l.tail) catch {
      case _: Throwable => println("no tail")
    }
    println(l.isEmpty)
    println(l.add(10).toString)
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
}
