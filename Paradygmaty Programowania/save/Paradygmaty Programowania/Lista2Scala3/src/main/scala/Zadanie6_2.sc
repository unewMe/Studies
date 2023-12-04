def replaceNth[A](list:List[A],pos:Int,element:A) : List[A] =
{
      def replaceHelp(list:List[A],index:Int) : List[A] =
      {
        (list,index) match
          case (h::t,x) if x == pos => element :: replaceHelp(list.tail,index+1)
          case (h::t,_) => list.head :: replaceHelp(list.tail,index+1)
          case _ => List()
      }
      replaceHelp(list,0)
}

replaceNth(List("o","l","a","m","a","k","o","t","a"),7,"s")