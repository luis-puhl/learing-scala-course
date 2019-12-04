package lectures.part3functionalProgramming

import exercices.{MyList, Const, Empty}

object L48AllThePatters extends App {
  // 1 - Constants
  val x: Any = "Scala"
  val costants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The truth"
    case L48AllThePatters => "A singleton object"
  }

  // 2 - Match anything
  val matchAny = x match {
    // 2.1 - wildcard
    case _ =>
    // 2.2 - variable
    case constants => s"I've found $constants"
  }

  // 3 - tuples
  val aTuple: (Int, Any) = (1,2)
  val maTu = aTuple match {
    case (1, 2) =>
    case (x, 2) => s"I've found $x"
    // nested tuple
    case (_, (2, x)) => s"I've found $x"
  }

  // 4 - case classes
  val aList: MyList[Int] = Const(1, Const(2, Empty))
  val matchAlist = aList match {
    case Empty =>
    case Const(head, Const(subHead, subTail)) =>
  }

  // 5 - List patterns
  val aStdList = List(1, 2, 3, 42)
  val  aStdListMathc = aStdList match {
    case List(1, _, _) => "Advanced extractor"
    case List(1, _*) => "any length list"
    case 1 :: List(_) => "infix patter"
    case List(1, 2, 3) :+ 42 => "infix patter as well"
  }

  // 6 - type specifiers
  val unk: Any = 2
  val unkMatch = unk match {
    case list: List[Int] => "explicit A list of int"
    case _ =>
  }

  // 7 - name binding
  val nameBound = aList match {
    case nonEmptyList@Const(_, _) => s"$nonEmptyList being reused"
    case Const(1, rest@Const(0, _)) => s"$rest being reused"
      // 8 - multi pattern
    case Empty | Const(0, _) => "Compound multi-pattern"
      // 9 - if guards
    case Const(_, Const(specialElement, _)) if specialElement % 2 == 0 => "Filter predicate"
  }

  /*
  Question
   */
  val typeErasureFail = List(1, 3, 4) match {
    case listOfStrings: List[String] => "strings"
    case listOfStrings: List[int] => "ints"
    case _ => "none"
  }
  /*
  Generics was introduced in Java 5, so jdk deletes the types after checking types, so JVM is oblivious of generics
   */
}
