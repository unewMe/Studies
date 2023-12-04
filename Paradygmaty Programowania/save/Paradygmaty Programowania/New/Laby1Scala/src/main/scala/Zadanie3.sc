def isPerfect(n:Int) : Boolean =
{
    def divList(pDiv:Int) : List[Int] =
      {
        if pDiv == 1 then pDiv::Nil
        else
          if n % pDiv == 0 then pDiv :: divList(pDiv-1)
          else
            divList(pDiv-1)
      }
     def divSum(listOfDiv:List[Int]) : Int =
       {
         if listOfDiv == Nil then 0
         else listOfDiv.head + divSum(listOfDiv.tail)
       }
    divSum(divList(n/2)) == n

}

isPerfect(2)