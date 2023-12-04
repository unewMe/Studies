def split2Rec[A] ( list:List[A] ) : ( List[A], List[A] ) =

  list match
  {
    case h1 :: h2 :: t =>
      val ( l1, l2 ) = split2Rec( t )
      ( h1 :: l1, h2 :: l2 )
    case _ => ( Nil, Nil )
  }


def split2Tail[A] ( list:List[A] ) : ( List[A], List[A] ) =

  def split2TailHelper( list:List[A], acc:( List[A], List[A] ) ) : ( List[A], List[A] ) =

    list match

      case h1::h2::t => split2TailHelper( t,(h1::(acc._1), h2::(acc._2)) )
      case _ => acc

  split2TailHelper( list, (Nil,Nil) )

split2Rec  ( List(1,2,3,4,5,6,7,8,9,10,11) )
split2Tail ( List(1,2,3,4,5,6,7,8,9,10,11) )


split2Rec  ( List(1,2) )
split2Tail ( List(1,2) )

split2Rec  ( List(1) )
split2Tail ( List(1) )

split2Rec  ( List() )
split2Tail ( List() )