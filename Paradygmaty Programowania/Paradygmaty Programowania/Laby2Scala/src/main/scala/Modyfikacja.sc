def substituteIfIn[A] (mainList:List[A])(matchList:List[A])(element:A) : List[A] =
{
  def listContains(list: List[A], element: A): Boolean =
  {
    list match
      case h ::_ if h == element => true
      case _::t => listContains(t,element)
      case _ => false

  }
    mainList match
      case h::t if listContains(matchList,h) => element :: substituteIfIn(t)(matchList)(element)
      case h::t => h::substituteIfIn(t)(matchList)(element)
      case _ => Nil

}

substituteIfIn(List(1,2,3,4,5,6,7))(List(2,4))(0)