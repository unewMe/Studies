sealed trait Tree3[+A]
case object TNil extends Tree3[Nothing]
case class TNode[A](value: A, child1: Option[Tree3[A]], child2: Option[Tree3[A]], child3: Option[Tree3[A]]) extends Tree3[A]


def mapTree3[A,B](f:(A=>B))(tr3:Tree3[A]) : Tree3[B] =
{
    def mapTree3Helper(node:Option[Tree3[A]]) =
    {
        node match
          case None => None
          case Some(value) => Some(mapTree3(f)(value))
    }

    tr3 match
      case TNil => TNil
      case TNode(value, child1, child2, child3) =>  TNode(f(value),mapTree3Helper(child1),mapTree3Helper(child2),mapTree3Helper(child3))
}

val pow = (x: Int) => x * x

val x = TNode(2,
  Some(TNode(3, None, None, None)),
  Some(TNode(4, None, None, None)),
  Some(TNode(5, None, None, None))
)

val mappedX1 = mapTree3(pow)(x)
val mappedX2 = mapTree3((x: Int) => x + 2)(x)