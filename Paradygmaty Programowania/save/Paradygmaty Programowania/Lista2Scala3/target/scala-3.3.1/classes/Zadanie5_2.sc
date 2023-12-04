def ifSegement[A](pair:(List[A],List[A])) : Boolean =
{
    pair match
      case (h::t,List()) => false
      case (List(),_) => true
      case (h1::t1,h2::t2) =>
        h1 match
        {
          case `h2` if h1==h2 => ifSegement(t1,t2)
          case _ => false
        }

}

ifSegement(List(1,2,3,4),List(1,2,3,3,5))