{
  sealed trait BT[+A]
  case object Empty extends BT[Nothing]
  case class Node[+A](elem: A, left: BT[A], right: BT[A]) extends BT[A]
}

def breadthBT[A](tree:BT[A]) =
{
    def breadthBTHelper(visited:List[BT[A]],queue:List[BT[A]]) : List[A] =
    {
        queue match
          case Nil => Nil
          case h::t =>
            h match
              case Empty => breadthBTHelper(visited,t)
              case Node(v,left,right) => v :: breadthBTHelper(h::visited,t ::: List(left,right))
    }
    breadthBTHelper(Nil,List(tree))
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

breadthBT(tt);