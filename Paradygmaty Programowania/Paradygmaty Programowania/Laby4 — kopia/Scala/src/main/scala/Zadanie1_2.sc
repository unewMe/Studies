sealed trait Tree3[+A]
case object TNil extends Tree3[Nothing]
case class TNode[A](value: A, child1: Tree3[A], child2: Tree3[A], child3: Tree3[A]) extends Tree3[A]


def mapTree3[A,B](f:(A=>B))(tr3:Tree3[A]) : Tree3[B] =
{
    tr3 match
      case TNil => TNil
      case TNode(value, child1, child2, child3) =>  TNode(f(value),mapTree3(f)(child1),mapTree3(f)(child2),mapTree3(f)(child3))
}

val pow = (x: Int) => x * x

val x = TNode(2,
  TNode(3, TNil, TNil, TNil),
  TNode(4, TNil, TNil, TNil),
  TNode(5, TNil, TNil, TNil)
)

val mappedX1 = mapTree3(pow)(x)
val mappedX2 = mapTree3((x: Int) => x + 2)(x)