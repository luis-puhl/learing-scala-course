package lectures.part1basics

object L2Expressions extends App {
  val x = 1 + 2 // expression
  println(x)

  println(2 + 3 + 4)
  // + - * /
  // & | ^ << >> >>> (right shift with zero extension)

  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3
  println(aVariable)
  // += -= *= /=

  /* Instructions vs Expressions */
  // expressions are evaluated (returns a value)
  // instructions are executed (like java/bash)
  // scala favours EXPRESSIONS

  val aCondition = true
  val ifExpressionCondition = if (aCondition) 5 else 3 // if expression
  println(ifExpressionCondition)
  println(if(aCondition) 5 else 3)
  println(1 + 3)

  /** DON'T: while (and for) are side-effects only */
  var i = 0
  while (i < 10) {
    println(i)
    i+=1
  }

  // everything in scala is an Expression!
  val aWeirdValue = (aVariable = 3)  // Unit == void
  println(aWeirdValue)
  // prints "()"

  // **side effects**: println(), whiles, reassigning


  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "Hello" else "goodbye"
  }
  println(aCodeBlock)

  // val anoterValue = z + 1
  // fails, z is undefided
}
