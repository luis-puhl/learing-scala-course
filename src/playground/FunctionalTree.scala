package playground

object FunctionalTree extends App {
  case class Node[T](value: T, father: Option[Node[T]] = Nil, children: List[Node[T]] = List[Node[T]]()) {
    def add(value: T): Node[T] = {
      if (this.contains(value)) throw new RuntimeException("Already Exists")
      else Node(this.value, father, Node(value) :: children)
    }
    def contains(value: T): Boolean = this.find(value, this.children) != EmptyNode
    def find(value: T, nodes: List[Node[T]] = this.children): Node[T] =
      if (value == this.value) this
      else if (nodes.exists(p => p.value == value)) nodes.filter(p => p.value == value)(0)
      else find(value, this.children.flatMap(n => n.children))
    def +(value: T): Node[T] = add(value)
  }
  object EmptyNode extends Node(None)
  var root: Node[Int] = Node(0) + 10 + 20
  root.find(10)
}


