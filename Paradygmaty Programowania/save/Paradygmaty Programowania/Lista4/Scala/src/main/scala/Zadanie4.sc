{
  sealed trait BT[+A]
  case object Empty extends BT[Nothing]
  case class Node[+A](elem: A, left: BT[A], right: BT[A]) extends BT[A]
}


def insideLength[A](tree:BT[A]) =
{
  def insideLengthHelper(current:BT[A], depth:Int) : Int =
  {
    current match
      case Empty => 0
      case Node(_, left, right) => depth + insideLengthHelper(left, depth + 1) + insideLengthHelper(right, depth + 1)
  }
  insideLengthHelper(tree, 0)
}

def outsideLength[A](tree:BT[A]) =
{
  def outsideLengthHelper(current:BT[A], depth:Int) : Int =
  {
    current match
      case Empty => depth
      case Node(_, left, right) => outsideLengthHelper(left,depth+1) + outsideLengthHelper(right,depth+1)
  }
  outsideLengthHelper(tree, 0)
}

val tt = Node(1,
  Node(2,
    Node(4,
      Empty,
      Empty
    ),
    Empty
  ),
  Node(3,
    Node(5,
      Empty,
      Node(6,
        Empty,
        Empty
      )
    ),
    Empty
  )
)

insideLength(tt)
outsideLength(tt)