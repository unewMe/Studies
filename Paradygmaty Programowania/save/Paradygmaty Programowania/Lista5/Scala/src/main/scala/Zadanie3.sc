sealed trait lBT[+A]
case object LEmpty extends lBT[Nothing]
case class LNode[+A](elem:A, left:()=>lBT[A], right:()=>lBT[A]) extends lBT[A]

def lTree(n:Int):LNode[Int] = LNode(n,()=>lTree(2*n),()=>lTree(2*n+1))

def dfs[A](tree:LNode[A]):Stream[A] =
{
    def helper(queue:List[lBT[A]]):Stream[A] =
      {
        queue match
          case h::t =>
            h match
              case LNode(elem,left,right) => elem#::helper(t:::List(left(),right()))
              case LEmpty => helper(t)
          case _ => Stream.empty[A]
      }
    helper(List(tree))

}

dfs(lTree(1)).take(21).toList