def isPerfect(n:Int) : Boolean =
{

    if n > 1 then
      def divList(pDiv:Int) : List[Int] =
        {
          if pDiv == 1 then
            pDiv::Nil
          else
            if n % pDiv == 0 then
              val temp = n/pDiv
              pDiv :: temp :: divList(pDiv-1)
            else
              divList(pDiv-1)
        }
      def divSum(listOfDiv:List[Int]) : Int =
       {
          if listOfDiv == Nil then
            0
          else
            listOfDiv.head + divSum(listOfDiv.tail)
       }
      divSum(divList(Math.sqrt(n).toInt)) == n
    else
      false

}

isPerfect(496)
isPerfect(6)
isPerfect(28)
isPerfect(0)
isPerfect(1)
isPerfect(2)
isPerfect(3)
isPerfect(-5)
isPerfect(188)
isPerfect(235)