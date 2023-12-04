def ifSegement[A](xs: List[A], ys: List[A]) : Boolean =
{
    (xs,ys) match
    {
      case (List(), _) => true
      case (h1 :: t1, h2 :: t2) if h1 == h2 => ifSegement(t1, t2)
      case _ => false
    }
}

ifSegement(List(1,2,3,3),List(1,2,3,3,5))