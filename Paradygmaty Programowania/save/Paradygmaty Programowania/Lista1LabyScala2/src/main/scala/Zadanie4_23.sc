def insert[A](list:List[A],element:A,pos:Int) : List[A] =
{
    def changeList(index:Int,pos:Int,listPom:List[A]) : List[A] =
      {
          if pos < 0 then element :: changeList(index+1,0,listPom)
          else
            if listPom != Nil then
              if index == pos then element :: changeList(index+1,pos,listPom)
              else listPom.head :: changeList(index+1,pos,listPom.tail)
            else
              if index <= pos then element :: Nil
              else Nil

      }
    changeList(0,pos,list)
}

val x = insert(List(),4,-3)