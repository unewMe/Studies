def isPerfect(n:Int) : Boolean =
{

    if n > 1 then
      def root(value: Double): Double =
      {
        val e = 0.000001

        def rootHelper(acc: Double): Double =
        {
          val temp = value / acc
          if Math.abs(acc - temp) >= e then
            rootHelper((acc + temp) / 2)
          else
            (acc + temp) / 2
        }

        rootHelper(1)
      }
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
      divSum(divList(root(n).toInt)) == n
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