def insert[A](list:List[A],element:A,pos:Int) : List[A] =
{
    def changeList(index:Int,listPom:List[A]) : List[A] =
      {
          if listPom != Nil then
            if index == pos then
              element :: changeList(index+1,listPom)
            else
              listPom.head :: changeList(index+1,listPom.tail)
          else
            if index <= pos then
              element :: Nil
            else
              Nil

      }
    if pos < 0 then
      element::changeList(0,list)
    else
      changeList(0,list)
}

insert(List(1,2,3,5),4,0);
insert(List(1,2,3,5),4,4);
insert(List(1,2,3,5),4,3);

insert(List(1,2,3,5),4,-7);
insert(List(1,2,3,5),4,7);

insert(List(),4,0);
insert(List(),4,-1);
insert(List(),4,1);