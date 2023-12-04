def tryToMatch[A](list:List[A]): A =
{
    list match
    {
      case _::_::x::_ => x
    }
}

def tryToMatch2[A](list:List[(A,A)]): A =
{
    list match
    {
      case _::(x,_)::_ => x
    }
}



val match1: (List[Int] => Int) =
{
  case _ :: _ :: x :: _ => x
}
val match2: (List[(Int,Int)] => Int) =
{
    case _::(x,_)::_ => x
}

match1(List(-2,-1,0,1,2))
match2(List((1,2),(0,3)))
tryToMatch(List(-2,-1,0,1,2))
tryToMatch2(List((1,2),(0,1)))