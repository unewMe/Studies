def insert[A](krotka:(List[A],A,Int)) : List[A] =
{
    val (list,element,pos) = krotka
    def changeList(index:Int,listPom:List[A]) : List[A] =
      {

          if listPom != Nil then
            if index == pos then element :: changeList(index+1,listPom)
            else listPom.head :: changeList(index+1,listPom.tail)
          else
            if index <= pos then element :: Nil
            else Nil

      }
    if pos < 0 then element::changeList(0,list)
    else changeList(0,list)
}

val x = insert(List(1,2,3,5),4,-7)