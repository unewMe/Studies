def cutAndMend[A](down: Int, up: Int)(lista: List[A]): List[A] =
{
  def cutAndMendHelper( lst: List[A], index: Int ): List[A] =
    ( lst, index ) match
    {
      case ( h :: t, x ) if down <= x && x <= up => cutAndMendHelper( t, index + 1 )
      case ( h :: t, _ ) => h :: cutAndMendHelper( t, index + 1 )
      case ( Nil, _ ) => Nil
    }

  cutAndMendHelper( lista, 0 )
}

def cutAndMend15[A](list:List[A]) = cutAndMend(1,5)(list)

cutAndMend ( 3,7 ) ( List(1,2,3,4,5,6,7,8,9,10) )

cutAndMend ( 3,-7 ) ( List(1,2,3,4,5,6,7,8,9,10) )

cutAndMend ( 3,17 ) ( List(1,2,3,4,5,6,7,8,9,10) )

cutAndMend (3,7 ) ( List(1,2,3,4,5,6,7,8,9,10) )

cutAndMend ( 3,3 )( List(1,2,3,4,5,6,7,8,9,10) )

cutAndMend ( -3,10 )( List(1,2,3,4,5,6,7,8,9,10) )

cutAndMend ( 7,10 ) ( List(1) )