def findMaxIndex(list:List[Int]) : List[Int] =
{
  if list != List() then
    def findMaxIndexPom(list:List[Int],maxValue:Int,index:Int) : (Int,List[Int]) =
      {
        if list != List() then
          if list.head == maxValue then
            val pair = findMaxIndexPom(list.tail,maxValue, index+1)
            if pair._1 == maxValue then
              (maxValue,index::pair._2)
            else
              (maxValue,pair._2)
          else
            if list.head > maxValue then
              findMaxIndexPom(list,list.head,index)
            else
              findMaxIndexPom(list.tail,maxValue,index+1)
        else
          (maxValue,List())
      }
    findMaxIndexPom(list,list.head,0)._2
  else
    List()
}

findMaxIndex(List(1,5,3,8,2,7,8,1))
findMaxIndex(List())
findMaxIndex(List(1))
findMaxIndex(List(1,5,3,8,2,7,8,1,8,5,5,4,3,8,7,6))