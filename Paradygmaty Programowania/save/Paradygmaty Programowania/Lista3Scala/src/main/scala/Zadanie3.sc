def sumProd(list:List[Int]) =
{
  list match
    case _::_ => list.foldLeft((0,1))((acc:(Int,Int),element:Int)=>(acc._1+element,acc._2*element))
    case Nil => (0,0)

}

sumProd(List(1))