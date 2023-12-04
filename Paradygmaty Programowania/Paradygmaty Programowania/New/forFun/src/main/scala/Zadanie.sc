def select[A](list:List[List[A]],indexList:List[Int]) : List[A] =
{
  def selectPom(list:List[List[A]],indexList:List[Int])  : List[List[A]] =
  {
    if list != List() && indexList != List() then
    {
      if indexList.head>=0 && list.head != List() then selectHelper(list.head,list.head.head,indexList.head,0) :: selectPom(list.tail,indexList.tail)
      else selectPom(list.tail,indexList.tail)
    }
    else
    {
      Nil
    }
  }

  def selectHelper(list:List[A],current:A,pos:Int,index:Int) : List[A] =
  {
    if list != List() then
      if index == pos then List(current)
      else
      {
        if list.tail != List() then selectHelper(list.tail,list.tail.head,pos,index+1)
        else List()
      }
    else
      List()
  }
  def removeEmpty(list:List[List[A]]) : List[A] =
  {
    if list != List() then
      if list.head != List() then list.head.head :: removeEmpty(list.tail)
      else
        removeEmpty(list.tail)
    else
      Nil
  }

  val res = selectPom(list,indexList)
  removeEmpty(res)



}

select(List(List(1,5,3,8),List(2,7,4,1)),List(-4,-2))